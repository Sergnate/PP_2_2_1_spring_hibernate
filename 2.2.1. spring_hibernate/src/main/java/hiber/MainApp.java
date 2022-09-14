package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.NoResultException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);
        UserService userService = context.getBean(UserService.class);
        userService.add(new User("User1", "Lastname1", "user1@mail.ru", new Car("BMW", 10)));
        userService.add(new User("User2", "Lastname2", "user2@mail.ru", new Car("AUDI", 120)));
        userService.add(new User("User3", "Lastname3", "user3@mail.ru", new Car("MERCEDES_BENZ", 121)));
        userService.add(new User("User4", "Lastname4", "user4@mail.ru", new Car("MITSUBISHI", 120)));
        userService.add(new User("User5", "Lastname5", "user5@mail.ru", new Car("TESLA", 22)));


        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println("Car = " + user.getCar());
        }

        try {
            Car car = new Car("Tesla", 22);
            System.out.println("User from Car: " + userService.getFromCar(car));
        } catch (NoResultException exception) {
            throw new NoResultException("Car not found!");
        }

        context.close();

    }
}
