package ru.t1.pmorozov;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import ru.t1.pmorozov.config.AppConfig;
import ru.t1.pmorozov.data.User;
import ru.t1.pmorozov.service.UserService;

@ComponentScan
public class Application {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Application.class);
        UserService userService = context.getBean(UserService.class);
        User testUser = new User(null, "Test User");

        userService.insert(testUser);
        System.out.println(userService.findById(testUser.getId()));

        userService.update(new User(testUser.getId(), "New Test User"));
        System.out.println(userService.readAll());

        userService.delete(testUser);
        System.out.println(userService.readAll());

        userService.deleteAll();
        System.out.println(userService.readAll());
    }
}