package com.zhengqing;

import com.zhengqing.config.Constants;
import com.zhengqing.modules.system.entity.User;
import com.zhengqing.modules.system.repository.UserRepository;
import com.zhengqing.utils.PasswordUtils;
import lombok.RequiredArgsConstructor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@RequiredArgsConstructor
public class ApplicationTests {

    private final UserRepository userRepository;

    @Test
    public void contextLoads() {
    }

    /**
     * 对密码加密
     *
     * @param :
     * @return: void
     */
    @Test
    public void test()  {
        List<User> users = userRepository.findAll();
        users.forEach(e -> {
            e.setPassword(PasswordUtils.encodePassword(e.getPassword(), Constants.SALT));
            e.setSalt(Constants.SALT);
            userRepository.save(e);
        });
    }

}
