package fmi.course.hcmi.animalshome.config;

import fmi.course.hcmi.animalshome.model.User;
import fmi.course.hcmi.animalshome.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userService.findUserByUsername(username);

        return user;
    }

    public UserDetails loadUserByShelterCode(String shelterCode){

        User shelter = userService.findUserByShelterCode(shelterCode);

        return shelter;

    }

}
