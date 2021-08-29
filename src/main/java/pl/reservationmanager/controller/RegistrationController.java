package pl.reservationmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import pl.reservationmanager.entity.User;
import pl.reservationmanager.service.UserService;
import pl.reservationmanager.user.CrmUser;

import javax.validation.Valid;
import java.util.logging.Logger;


@Controller
@RequestMapping("/register")
public class RegistrationController {

    @Autowired
    private UserService userService;

    private Logger logger = Logger.getLogger(getClass().getName());

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {

        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping("/signup")
    public String showMyLoginPage(Model theModel) {

        theModel.addAttribute("crmUser", new CrmUser());

        return "signup-page";
    }

    @PostMapping("/processRegistrationForm")
    public String processRegistrationForm(
            @Valid @ModelAttribute("crmUser") CrmUser theCrmUser,
            BindingResult theBindingResult,
            Model theModel) {

        String userName = theCrmUser.getUserName();
        String phoneNumber = theCrmUser.getPhoneNumber();
        logger.info("Processing registration form for: " + userName);

        if (theBindingResult.hasErrors()){
            return "signup-page";
        }

        User usernameExisting = userService.findByUserName(userName);
        if (usernameExisting != null){
            theModel.addAttribute("crmUser", new CrmUser());
            theModel.addAttribute("registrationError", "User name already exists.");

            logger.warning("User name already exists.");

            return "signup-page";
        }

        User phoneExisting = userService.findByPhoneNumber(phoneNumber);
        if (phoneExisting != null){
            theModel.addAttribute("crmUser", new CrmUser());
            theModel.addAttribute("registrationError", "Phone number name already exists.");

            logger.warning("Phone number already exists.");

            return "signup-page";
        }

        userService.save(theCrmUser);
        logger.info("Successfully created user: " + userName);
        return "signup-confirmation";
    }
}
