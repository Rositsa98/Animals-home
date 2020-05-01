package fmi.course.hcmi.animalshome.service;

import fmi.course.hcmi.animalshome.model.Shelter;
import fmi.course.hcmi.animalshome.model.SingleUser;
import fmi.course.hcmi.animalshome.model.User;

import java.util.List;

public interface IUserService {
    User findUserByUsername(String username);
    List<String> findUserRoles(String username);
    List<User> findAllUsers();
    SingleUser addUser(SingleUser user);
    Shelter addShelter(Shelter user);
    long usersCount();

    Shelter findUserByShelterCode(String shelterCode);

}
