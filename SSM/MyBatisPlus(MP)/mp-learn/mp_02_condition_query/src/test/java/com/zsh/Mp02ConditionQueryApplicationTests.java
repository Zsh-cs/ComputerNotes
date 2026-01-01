package com.zsh;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zsh.dao.UserDao;
import com.zsh.domain.User;
import com.zsh.query.UserQuery;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
class Mp02ConditionQueryApplicationTests {

    @Autowired
    private UserDao userDao;

    @Test
    void test1() {
        // 方式一：按条件查询
        QueryWrapper qw = new QueryWrapper();
        qw.lt("age", 18);
        List<User> users = userDao.selectList(qw);
        System.out.println(users);
    }

    @Test
    void test2() {
        // 方式二：Lambda格式的按条件查询，可以防止字段名写错
        QueryWrapper<User> qw = new QueryWrapper<>();
        qw.lambda().lt(User::getAge, 10);
        List<User> users = userDao.selectList(qw);
        System.out.println(users);
    }

    @Test
    void test3() {
        // 方式三：Lambda格式的按条件查询——简化版
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        lqw.ge(User::getAge, 25);
        List<User> users = userDao.selectList(lqw);
        System.out.println(users);
    }

    @Test
// 多条件查询，支持链式编程
    void test4() {
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();

        // 18<age<22
//        lqw.gt(User::getAge,18).lt(User::getAge,22);
        // age<10 || age>30
        lqw.lt(User::getAge, 10).or().gt(User::getAge, 30);

        List<User> users = userDao.selectList(lqw);
        System.out.println(users);
    }

    @Test
    void test5() {
        // 模拟前端页面传递过来的查询数据
        UserQuery userQuery = new UserQuery();
        userQuery.setAge(18);
        // userQuery.setMaxAge(22);

        // null值处理
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        /*
            先判断第一个入参是否为true，如果为true就连接当前条件
            等价于：
            if (userQuery.getAge() != null) {
                lqw.gt(User::getAge, userQuery.getMaxAge());
            }
         */
        lqw.gt(userQuery.getAge() != null, User::getAge, userQuery.getAge());
        lqw.lt(userQuery.getMaxAge() != null, User::getAge, userQuery.getMaxAge());

        List<User> users = userDao.selectList(lqw);
        System.out.println(users);
    }

    @Test
    // 查询投影
    void test6(){
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        lqw.select(User::getId,User::getName,User::getAge);
        List<User> users = userDao.selectList(lqw);
        System.out.println(users);
    }

    @Test
    void test7(){
        QueryWrapper<User> lqw = new QueryWrapper<>();
        lqw.select("count(*) as count, tel");
        lqw.groupBy("tel");
        List<Map<String, Object>> users=userDao.selectMaps(lqw);
        System.out.println(users);
    }

    @Test
    void test7Plus(){
        List<Map<String, User>> telGroup = userDao.getTelGroup();
        System.out.println(telGroup);
    }

}
