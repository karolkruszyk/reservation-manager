package pl.reservationmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.reservationmanager.entity.User;
import pl.reservationmanager.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Transactional
    @RequestMapping("/list")
    public String listUsers(Model theModel) {
        List<User> users = userService.getUsers();
        theModel.addAttribute("users", users);

        return "users-list";
    }

    @Transactional
    @RequestMapping("/giveManagerRole")
    public String giveManagerRole(@RequestParam("theId")Long theId) {

        userService.addRole(theId);
        return "redirect:/user/list";
    }


}
