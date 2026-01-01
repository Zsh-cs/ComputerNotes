package com.zsh;

import com.zsh.dao.UserDao;
import com.zsh.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class Mp03DmlApplicationTests {

    @Autowired
    private UserDao userDao;

    @Test
    void testInsert() {
        User user=new User("Arthur","atr-forever",99,"15122026");
        userDao.insert(user);
    }

    @Test
    void testDelete() {
        List<Long> ids = new ArrayList<>();
        ids.add(7L);
        ids.add(8L);
        ids.add(9L);
        userDao.deleteByIds(ids);
    }

}
