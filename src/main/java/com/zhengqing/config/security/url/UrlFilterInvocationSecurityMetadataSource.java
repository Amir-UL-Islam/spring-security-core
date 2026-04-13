package com.zhengqing.config.security.url;

import com.zhengqing.config.Constants;
import com.zhengqing.config.MyProperties;
import com.zhengqing.modules.system.entity.Menu;
import com.zhengqing.modules.system.entity.Role;
import com.zhengqing.modules.system.entity.RoleMenu;
import com.zhengqing.modules.system.repository.MenuRepository;
import com.zhengqing.modules.system.repository.RoleMenuRepository;
import com.zhengqing.modules.system.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * <p> Get the user role permission information required to access the url</p>
 *
 * @author : zhengqing
 * @description : After execution, go to `UrlAccessDecisionManager` to authenticate permissions
 * @date : 2019/10/15 14:36
 */
@Component
@RequiredArgsConstructor
public class UrlFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    private final MenuRepository menuRepository;
    private final RoleRepository roleRepository;
    private final RoleMenuRepository roleMenuRepository;
    private final MyProperties myProperties;

    /***
     * Returns the user permission information required for this URL
     *
     * @param object: Store request url information
     * @return: null：Identification does not require any permissions to access
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        //Get the current request url
        String requestUrl = ((FilterInvocation) object).getRequestUrl();
        // TODO If you ignore the URL, please put it here for filtering and release.
        for (String ignoreUrl : myProperties.getAuth().getIgnoreUrls()) {
            if (ignoreUrl.equals(requestUrl)) {
                return null;
            }
        }

        if (requestUrl.contains("/login")) {
            return null;
        }

        // All URLs in the database
        List<Menu> permissionList = menuRepository.selectList();
        for (Menu permission : permissionList) {
            // Obtain the permissions corresponding to the URL
            if (("/api" + permission.getUrl()).equals(requestUrl)) {
                List<RoleMenu> permissions = roleMenuRepository.findByMenuId(permission.getId());
                List<String> roles = new LinkedList<>();
                if (!CollectionUtils.isEmpty(permissions)) {
                    permissions.forEach(e -> {
                        Integer roleId = e.getRoleId();
                        Role role = roleRepository.selectById(roleId);
                        roles.add(role.getCode());
                    });
                }
                // Save the permission information for the role corresponding to the URL
                return SecurityConfig.createList(roles.toArray(new String[roles.size()]));
            }
        }
        // If no corresponding URL resource is found in the data,
        // it is illegal access, and the user is required to log in before operating
        return SecurityConfig.createList(Constants.ROLE_LOGIN);
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return FilterInvocation.class.isAssignableFrom(aClass);
    }
}
