package fmi.course.hcmi.animalshome.service;

import fmi.course.hcmi.animalshome.model.User;

import java.util.List;

public interface IUserService {
    User findUserByUsername(String username);
    List<String> findUserRoles(String username);
    List<User> findAllUsers();
    User addUser(User user);
    long usersCount();
}
