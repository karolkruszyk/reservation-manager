package pl.reservationmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.reservationmanager.crm.CrmUser;
import pl.reservationmanager.entity.User;
import pl.reservationmanager.service.UserService;

import javax.validation.Valid;


@Controller
@RequestMapping("/register")
public class RegistrationController {


    @Autowired
    private UserService userService;

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @RequestMapping ("/signup")
    public String showMyLoginPage(Model theModel) {
        theModel.addAttribute("crmUser", new CrmUser());
        return "signup-page";
    }

    @RequestMapping ("/processRegistrationForm")
    public String processRegistrationForm(
            @Valid @ModelAttribute("crmUser") CrmUser theCrmUser,
            BindingResult theBindingResult,
            Model theModel) {

        String userName = theCrmUser.getUserName();
        String phoneNumber = theCrmUser.getPhoneNumber();

        if (theBindingResult.hasErrors()){
            return "signup-page";
        }

        User usernameExisting = userService.findByUserName(userName);
        if (usernameExisting != null){
            theModel.addAttribute("crmUser", new CrmUser());
            theModel.addAttribute("registrationError", "User name already exists.");

            return "signup-page";
        }

        User phoneExisting = userService.findByPhoneNumber(phoneNumber);
        if (phoneExisting != null){
            theModel.addAttribute("crmUser", new CrmUser());
            theModel.addAttribute("registrationError", "Phone number name already exists.");

            return "signup-page";
        }

        userService.save(theCrmUser);
        return "signup-confirmation";
    }
}
