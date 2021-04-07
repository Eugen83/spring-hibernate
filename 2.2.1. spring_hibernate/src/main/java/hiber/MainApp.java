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

/*   Car car1= new Car("model1",11);
      Car car2 = new Car("model2",22);
      Car car3 = new Car("model3",33);
       Car car4 =  new Car("model4",44);
      Car car5 = new Car("model5",55);*/


   /*     userService.add(new User("User1", "Lastname1", "user1@mail.ru").setCar(car1);
        userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
        userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
        userService.add(new User("User4", "Lastname4", "user4@mail.ru"));
        userService.add(new User("User5", "LastName5", "user5@mail.ru"));*/

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

        System.out.println(userService.getCarsUser("McLaren F",1));


        context.close();
    }
}
