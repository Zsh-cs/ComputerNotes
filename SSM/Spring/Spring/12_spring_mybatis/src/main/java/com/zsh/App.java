package com.zsh;

import com.zsh.dao.AccountDao;
import com.zsh.domain.Account;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class App {
    public static void main(String[] args) throws IOException {
        // 1.创建SqlSessionFactoryBuilder对象
        SqlSessionFactoryBuilder factoryBuilder=new SqlSessionFactoryBuilder();

        // 2.加载SqlMapConfig.xml配置文件
        InputStream is= Resources.getResourceAsStream("mybatis-config.xml");

        // 3.创建SqlSessionFactory对象：核心对象
        SqlSessionFactory factory=factoryBuilder.build(is);

        // 4.获取SqlSession对象
        SqlSession sqlSession=factory.openSession();

        // 5.调用SqlSession对象到数据库执行查询，获取Account对象
        AccountDao accountDao=sqlSession.getMapper(AccountDao.class);
        Account account=accountDao.findById(1);
        System.out.println(account);

        // 6.释放资源
        sqlSession.close();
    }
}
