package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context =
              new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User ivan = new User("ivan", "ivanov", "ivan@mail.ru");
      User petr = new User("petr", "petrov", "petr@mail.ru");
      User igor = new User("igor", "igorev", "igor@mail.ru");

      ivan.setCar(new Car("BMW", 1));
      petr.setCar(new Car("Merc", 2));
      igor.setCar(new Car("Vaz", 3));

        userService.add(ivan);
        userService.add(petr);
        userService.add(igor);

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println("Car = " + user.getCar());
            System.out.println();
        }
        System.out.println(userService.findUser("BMW", 1));
        context.close();
    }
}
