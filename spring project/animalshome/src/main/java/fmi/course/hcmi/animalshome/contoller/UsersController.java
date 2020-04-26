package fmi.course.hcmi.animalshome.contoller;

import fmi.course.hcmi.animalshome.model.User;
import fmi.course.hcmi.animalshome.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UsersController {

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/roles", method = RequestMethod.GET)
    public String getUserRoles(@RequestHeader String username) {
        List<String> roles = userService.findUserRoles(username);

        for (String role : roles) {
            if (role.equals("ROLE_ADMIN")) {
                return "Admin";
            }
            if (role.equals("ROLE_OPERATOR")) {
                return "Operator";
            }
            if (role.equals("ROLE_SHELTER")) {
                return "Shelter";
            }
        }
        return "User";
    }

    @RequestMapping(value = "/registerUser", method = RequestMethod.POST)
    public ResponseEntity<User> registerUser(@RequestBody User user) {

        User resultUser = userService.addUser(user);

        if(resultUser == null){
            return ResponseEntity.badRequest().body(null);
        }

        return ResponseEntity.created(
                ServletUriComponentsBuilder.fromCurrentRequest().pathSegment("{id}").build(resultUser.getId()))
                .body(resultUser);
    }
}
