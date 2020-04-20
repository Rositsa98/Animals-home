package fmi.course.hcmi.animalshome.dao;

import fmi.course.hcmi.animalshome.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    /**
     * Method to retrieve all users in the system.
     * @return
     */
    List<User> findAll();

    /**
     * Method used to retrieve user by username.
     * It is used when authentication user.
     * @param username
     * @return
     */
    Optional<User> findByUsername(String username);
}
