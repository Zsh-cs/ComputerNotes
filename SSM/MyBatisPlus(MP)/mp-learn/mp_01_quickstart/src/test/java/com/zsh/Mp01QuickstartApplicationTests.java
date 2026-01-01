package com.zsh;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsh.dao.UserDao;
import com.zsh.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class Mp01QuickstartApplicationTests {

    @Autowired
    private UserDao userDao;

    @Test
    void testInsert() {
        User user = new User("zsh", "666999", 18, "842951084");
        userDao.insert(user);
    }

    @Test
    void testDelete() {
        userDao.deleteById(2006633799126016002L);
    }

    @Test
    void testUpdate() {
        User user = new User();
        user.setId(1L);
        user.setName("Tommy");

        // 提供哪些字段的新值，MP就只会修改这些字段
        userDao.updateById(user);
    }

    @Test
    void testGetById() {
        User user = userDao.selectById(2L);
        System.out.println(user);
    }

    @Test
    void testGetAll() {
        List<User> users = userDao.selectList(null);
        System.out.println(users);
    }

    @Test
    void testGetByPage() {
        // 首先要配置好MP的分页拦截器——config/MpConfig
        IPage page = new Page(2, 3);// 查第2页，每页显示3条记录
        userDao.selectPage(page, null);
        System.out.println("当前页码值： " + page.getCurrent());
        System.out.println("每页记录数： " + page.getSize());
        System.out.println("总页数： " + page.getPages());
        System.out.println("总记录数： " + page.getTotal());
        System.out.println("page data: " + page.getRecords());
    }

}
