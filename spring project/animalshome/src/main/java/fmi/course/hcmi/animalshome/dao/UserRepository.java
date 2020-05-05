package fmi.course.hcmi.animalshome.dao;

import fmi.course.hcmi.animalshome.model.Shelter;
import fmi.course.hcmi.animalshome.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    /**
     * Method to retrieve all users in the system.
     * @return all users
     */
    List<User> findAll();

    /**
     * Method used to retrieve user by username.
     * It is used when authenticating user.
     * @param username
     * @return user
     */
    Optional<User> findByUsername(String username);

    /**
     *Method to retrieve shelter user by shelter code
     *
     * @param shelterCode
     * @return user
     */
    Optional<Shelter> findByShelterCode(String shelterCode);
}
