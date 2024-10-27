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


        Car car1 = new Car("audi", 1);
        Car car2 = new Car("skoda",2);
        Car car3 = new Car("bmw",3);
        Car car4 = new Car("cadilac", 4);

        userService.add(new User("Name1", "Surname1", "1@mail.ru",car1));
        userService.add(new User("Name2", "Surname1", "2@gmail.com",car2));
        userService.add(new User("Name3", "Surname1", "3@bk.ru",car3));
        userService.add(new User("Name4", "Surname1", "4@gmail.com",car4));


        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = "+user.getId());
            System.out.println("First Name = "+user.getFirstName());
            System.out.println("Last Name = "+user.getLastName());
            System.out.println("Email = "+user.getEmail());
            System.out.println("Car = "+user.getCar());
            System.out.println("---------------------");
            System.out.println("---------------------");
        }
        // поиск пользователя по модели и серии
        System.out.println(userService.getUserCar("bmw", 3));
        // если user не найден
        try {
           userService.getUserCar("test", 123);
        } catch (NoResultException e) {
            System.out.println("User не найден");
        }
        context.close();
    }
}
