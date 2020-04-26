package fmi.course.hcmi.animalshome.init;

import fmi.course.hcmi.animalshome.model.User;
import fmi.course.hcmi.animalshome.service.impl.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserService userService;


    @Override
    public void run(String... args) throws Exception {
        // Users init
        long usersCount = userService.usersCount();
        log.info("Users count: {}", usersCount);

        if (usersCount == 0) {
            List<User> defaultUsers = Arrays.asList(
                    new User( 1l,"admin", "admin", "admin", "admin", "088", "ROLE_ADMIN", "admin@email.com", "image_url", "address", "-", "-",true),
                    new User(2l, "user", "user", "user", "user", "0888", "ROLE_USER","user@email.com", "image_url","address2", "-", "-", true),
                    new User(3l, "shelter", "shelter",null, null, "088", "ROLE_SHELTER", "shelter@email.com", "image_url",
                            "53, Green Street, CA", "shelter_code_123","description", true)
            );

            userService.createUsersBatch(defaultUsers);

        }

        log.info("Querying for user records:");
        List<User> users = userService.findAllUsers();
        users.forEach(user -> log.info("{}", user.getUsername()));

    }

}
