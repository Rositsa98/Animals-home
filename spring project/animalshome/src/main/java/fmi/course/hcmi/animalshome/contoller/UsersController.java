package fmi.course.hcmi.animalshome.contoller;

import fmi.course.hcmi.animalshome.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
}
