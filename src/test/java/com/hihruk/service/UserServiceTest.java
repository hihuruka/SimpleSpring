package com.hihruk.service;

import com.hihruk.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.Test;

import java.util.Date;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@ContextConfiguration("classpath*:/simple-context.xml")
public class UserServiceTest extends AbstractTransactionalTestNGSpringContextTests {
    @Autowired
    private UserService userService;

    @Test
    public void testHasMatchUser() {
        boolean b1 = userService.hasMatchUser("admin", "123456");
        boolean b2 = userService.hasMatchUser("admin", "1111");
        assertTrue(b1);
        assertTrue(b2);
    }

    @Test
    public void testFindUserByUserName()throws Exception{
        for(int i =0; i< 100;i++) {
            User user = userService.findUserByUserName("admin");
//            System.out.println(user.getUsername());
            assertEquals(user.getUsername(), "admin");
        }
    }
    @Test
    public void testAddLoginLog(){
        User user=userService.findUserByUserName("admin");
        user.setUserId(1);
        user.setUsername("admin");
        user.setLastIp("127.0.0.1");
        user.setLastVisit(new Date());
        userService.loginSuccess(user);
    }

}
