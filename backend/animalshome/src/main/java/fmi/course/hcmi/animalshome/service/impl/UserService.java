package fmi.course.hcmi.animalshome.service.impl;

import fmi.course.hcmi.animalshome.dao.UserRepository;
import fmi.course.hcmi.animalshome.entity.WorkDay;
import fmi.course.hcmi.animalshome.exception.InvalidUserException;
import fmi.course.hcmi.animalshome.model.Shelter;
import fmi.course.hcmi.animalshome.model.SingleUser;
import fmi.course.hcmi.animalshome.model.User;
import fmi.course.hcmi.animalshome.model.VisitRequest;
import fmi.course.hcmi.animalshome.notifications.NotificationsServiceClient;
import fmi.course.hcmi.animalshome.service.IUserService;
import io.jsonwebtoken.lang.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private NotificationsServiceClient notificationsServiceClient;

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new InvalidUserException(String.format("User '%s' not found.", username)));
    }

    @Override
    public List<String> findUserRoles(String username) {
        User user = userRepository.findByUsername(username)
                .get();

        if (user != null) {
            String[] roles = user.getRoles()
                    .split(",");
            List<String> rolesList = Collections.arrayToList(roles);

            return rolesList;
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public SingleUser addUser(SingleUser user) {
        Optional<User> old = userRepository.findByUsername(user.getUsername());

        if (old.isPresent()) {
            return null;
        }
        if (user.getRoles() == null || user.getRoles()
                .split(",").length == 0) {
            user.setRoles("ROLE_USER");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public Shelter addShelter(Shelter user) {
        Optional<User> old = userRepository.findByUsername(user.getUsername());

        if (old.isPresent()) {
            return null;
        }
        if (user.getRoles() == null || user.getRoles()
                .split(",").length == 0) {
            user.setRoles("ROLE_SHELTER");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public long usersCount() {
        return userRepository.count();
    }

    @Override
    public Shelter findUserByShelterCode(String shelterCode) {
        return userRepository.findByShelterCode(shelterCode)
                .orElseThrow(() -> new BadCredentialsException(String.format("Shelter '%s' not found.", shelterCode)));
    }

    @Transactional
    public void createUsersBatch(List<User> users) {
        users.stream()
                .forEach(user -> {
                    if (user.getRoles()
                            .contains("ROLE_USER")) {
                        User resultUser = addUser((SingleUser) user);
                    } else if (user.getRoles()
                            .contains("ROLE_SHELTER")) {
                        User resUser = addShelter((Shelter) user);
                    }
                });
    }

    @Override
    public List<String> getNotificationsForUser(String username) {
        return notificationsServiceClient.getNotifications(username);
    }
}
