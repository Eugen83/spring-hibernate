package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
//import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);


        UserService userService = context.getBean(UserService.class);
        userService.truncateUsersTable();
        userService.truncateCarTable();


        User userI = new User("Ivan", "Ivanov", "Ivanov@mail.ru");
        User userP = new User("Petr", "Petrov", "Petya@mail.ru");
        User userS = new User("Sergey", "Sergeev", "seryi@gmail.com");

        Car carIv = new Car("VAZ", 2101);
        Car carPt = new Car("BMW", 325);
        Car carSr = new Car("McLaren F", 1);

        userI.setCar(carIv);
        userP.setCar(carPt);
        userS.setCar(carSr);

        userService.add(userI);
        userService.add(userP);
        userService.add(userS);

        List<User> users = userService.listUsers();
        for (User user : users) {

            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println("Car = " + user.getCar());
            System.out.println();
        }

        System.out.println(userService.getCarsUser("VAZ",2101));


        context.close();
    }
}
