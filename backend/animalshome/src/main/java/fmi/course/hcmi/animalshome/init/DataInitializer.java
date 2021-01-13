package fmi.course.hcmi.animalshome.init;

import fmi.course.hcmi.animalshome.enums.Gender;
import fmi.course.hcmi.animalshome.model.Shelter;
import fmi.course.hcmi.animalshome.model.SingleUser;
import fmi.course.hcmi.animalshome.model.User;
import fmi.course.hcmi.animalshome.service.impl.UserService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Slf4j
@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserService userService;

    @Override
    public void run(String... args) throws Exception {
        //         Users init
        long usersCount = userService.usersCount();
        log.info("Users count: {}", usersCount);

        if (usersCount == 0) {
            List<User> defaultUsers = Arrays.asList(new SingleUser(1l,
                            "admin",
                            "admin",
                            "admin",
                            "admin",
                            "088",
                            "ROLE_ADMIN",
                            "admin@email.com",
                            "image_url",
                            "address",
                            true,
                            Collections.emptyList(),
                            "birthday",
                            Gender.MALE,
                            Collections.emptyList()),
                    new SingleUser(2l,
                            "user",
                            "user",
                            "user",
                            "user",
                            "0888",
                            "ROLE_USER",
                            "user@email.com",
                            "image_url",
                            "address2",
                            true,
                            Collections.emptyList(),
                            "-",
                            Gender.FEMALE,
                            Collections.emptyList()),
                    new Shelter(3l,
                            "shelter",
                            "shelter",
                            "088",
                            "ROLE_SHELTER",
                            "shelter@email.com",
                            "image_url",
                            "53, Green Street, CA",
                            true,
                            Collections.emptyList(),
                            "shelter_code_123",
                            "description",
                            null,
                            Collections.emptyList())

            );

            userService.createUsersBatch(defaultUsers);

        }

        log.info("Querying for user records:");
        final List<User> users = userService.findAllUsers();
        users.forEach(user -> log.info("{}", user.getUsername()));

    }
}
