package org.nb.hrm.mapper;


import org.junit.jupiter.api.Test;
import org.nb.hrm.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;





@SpringBootTest
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;
    @Test
    public void testAdd() {
        userMapper.register("15527320160",0);
    }

    @Test
    public void testLogin() {
        User user =userMapper.login("15527320160",0);
        System.out.println(user);
    }

    @Test
    public void testFindUser() {
        User user =userMapper.findUser("15527320160");
        System.out.println(user);
    }


}
