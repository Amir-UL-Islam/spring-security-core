package com.zhengqing.config.security;

import com.zhengqing.config.MyProperties;
import com.zhengqing.config.security.filter.AdminAuthenticationProcessingFilter;
import com.zhengqing.config.security.filter.MyAuthenticationFilter;
import com.zhengqing.config.security.login.AdminAuthenticationEntryPoint;
import com.zhengqing.config.security.url.UrlAccessDecisionManager;
import com.zhengqing.config.security.url.UrlAccessDeniedHandler;
import com.zhengqing.config.security.url.UrlFilterInvocationSecurityMetadataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

/**
 *  <p> Security Core configuration class </p>
 *
 * @author：  zhengqing <br/>
 * @date：  2019/9/30$ 10:58$ <br/>
 * @version：  <br/>
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final MyProperties myProperties;

    /**
     * Access authentication - authentication token, signature...
     */
    private final MyAuthenticationFilter myAuthenticationFilter;
    /**
     * Access permission authentication exception handling
     */
    private final AdminAuthenticationEntryPoint adminAuthenticationEntryPoint;
    /**
     * User password verification filter
     */
    private final AdminAuthenticationProcessingFilter adminAuthenticationProcessingFilter;

    // The above is related to login authentication, and the following is related to url permissions. - ========================================================================================

    /**
     * Get the role information required to access the url
     */
    private final UrlFilterInvocationSecurityMetadataSource urlFilterInvocationSecurityMetadataSource;
    /**
     * Authentication permission processing
     * - Compare the role permissions obtained above with the role of the currently logged-in user.
     * If one of the roles is included, normal access can be achieved
     */
    private final UrlAccessDecisionManager urlAccessDecisionManager;
    /**
     * Customize the 403 response content when accessing the unauthorized interface
     */
    private final UrlAccessDeniedHandler urlAccessDeniedHandler;


    /**
     * Permission configuration
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry = http.antMatcher("/**").authorizeRequests();

        // Disable CSRF and enable cross-domain
        http.csrf().disable().cors();

        // Not logged in authentication exception
        http.exceptionHandling().authenticationEntryPoint(adminAuthenticationEntryPoint);

        // Customize the 403 response content when accessing an unauthorized interface after logging in
        http.exceptionHandling().accessDeniedHandler(urlAccessDeniedHandler);

        // URL authority authentication processing
        registry.withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
            @Override
            public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                o.setSecurityMetadataSource(urlFilterInvocationSecurityMetadataSource);
                o.setAccessDecisionManager(urlAccessDecisionManager);
                return o;
            }
        });

        // No session is created
        // - that is, the front-end passes the token to the back-end filter to verify whether access rights exist.
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // Login processing - when the front and back ends are integrated
        registry.and().formLogin().loginPage("/login").defaultSuccessUrl("/").permitAll()
//                // Customize the login username and password attribute names, the default is username and password
                .usernameParameter("username").passwordParameter("password")
//                // Exception handling
                .failureUrl("/login/error").permitAll()
//                // Log out
                .and().logout().permitAll();

        //To access the `/home` interface, you need to have the `ADMIN` role.
        registry.antMatchers("/home").hasRole("ADMIN");

        // The identification can only access the `/home` interface from the server's local IP [127.0.0.1 or localhost], and cannot be accessed from other IP addresses.
        registry.antMatchers("/home").hasIpAddress("127.0.0.1");

        // Allow anonymous URLs - can be understood as a release interface
        // - except for configuration files that ignore URLs,
        // all other requests must be authenticated and authorized
        for (String url : myProperties.getAuth().getIgnoreUrls()) {
            registry.antMatchers(url).permitAll();
        }
//        registry.antMatchers("/**").access("hasAuthority('admin')");
        // OPTIONS: Find communication options available for a specific URL resource.
        // Allows a client to determine resource-related options and/or requirements,
        // or capabilities of a server, without performing specific actions involving data transfer.
        registry.antMatchers(HttpMethod.OPTIONS, "/**").denyAll();

        // Automatic login - cookie storage method
        registry.and().rememberMe();

        // All other requests require authentication
        registry.anyRequest().authenticated();

        // Prevent iframe from causing cross-domain
        registry.and().headers().frameOptions().disable();

        // Custom filters authenticate username and password when logging in
        http.addFilterAt(adminAuthenticationProcessingFilter, UsernamePasswordAuthenticationFilter.class)
            .addFilterBefore(myAuthenticationFilter, BasicAuthenticationFilter.class);
    }

    /**
     * Ignore intercepting URLs or static resource folders - web.ignoring(): will filter the url directly - will not go through the Spring Security filter chain
     * http.permitAll(): will not bypass spring security verification, which is equivalent to allowing the path to pass
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(HttpMethod.GET,
                "/favicon.ico",
                "/**/*.png",
                "/**/*.ttf",
                "/*.html",
                "/**/*.css",
                "/**/*.js");
    }

}

