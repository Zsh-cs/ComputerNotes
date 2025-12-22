import com.zsh.dao.BookDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {
        ApplicationContext context=new ClassPathXmlApplicationContext("applicationContextPlus.xml");
//        DataSource dataSource= (DataSource) context.getBean("dataSource");
//        System.out.println(dataSource);
//        ComboPooledDataSource comboPooledDataSource= (ComboPooledDataSource) context.getBean("comboPooledDataSource");
//        System.out.println(comboPooledDataSource);
        BookDao bookDao=(BookDao) context.getBean("bookDao");
        bookDao.save();

    }
}
