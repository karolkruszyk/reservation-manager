package pl.reservationmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.reservationmanager.crm.CrmService;
import pl.reservationmanager.entity.Service;
import pl.reservationmanager.entity.User;
import pl.reservationmanager.service.ServiceService;
import pl.reservationmanager.service.UserService;

import javax.validation.Valid;
import java.util.List;

@Transactional
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    ServiceService serviceService;

    @Autowired
    UserService userService;


    @RequestMapping("/editServiceList")
    public String editServiceList(Model theModel) {
        List<Service> services = serviceService.getServices();
        theModel.addAttribute("services", services);
        return "edit-services";
    }

    @RequestMapping("/showServiceForm")
    public String showServiceForm(Model theModel) {
        theModel.addAttribute("crmService", new CrmService());
        return "add-service";
    }

    @RequestMapping("/processServiceForm")
    public String processServiceForm(@Valid @ModelAttribute("crmService") CrmService theCrmService, BindingResult bindingResult, Model theModel) {
        if(bindingResult.hasErrors()) {
            return "add-service";
        }
        serviceService.addService(theCrmService);
        return "redirect:/admin/editServiceList";
    }

    @RequestMapping("/processUpdateServiceForm")
    public String processUpdateServiceForm(@Valid @ModelAttribute("crmService") CrmService theCrmService, BindingResult bindingResult, Model theModel) {
        if(bindingResult.hasErrors()) {
            return "update-service";
        }
        serviceService.updateService(theCrmService.getId(), theCrmService);
        return "redirect:/admin/editServiceList";
    }

    @RequestMapping("/deleteService")
    public String deleteService(@ModelAttribute("serviceId") Long theId) {
        serviceService.deleteService(theId);
        return "redirect:/admin/editServiceList";
    }

    @GetMapping("/showUpdateServiceForm")
    public String showUpdateServiceForm(@RequestParam("serviceId") Long theId, Model theModel) {
        CrmService crmService = new CrmService();
        Service service = serviceService.getService(theId);

        crmService.setId(service.getId());
        crmService.setName(service.getName());
        crmService.setDuration(service.getDuration().toString());
        crmService.setPrice(service.getPrice().toString());

        theModel.addAttribute("crmService", crmService);
        return "update-service";
    }

    @RequestMapping("/listUsers")
    public String listUsers(Model theModel) {
        List<User> users = userService.getUsers();
        theModel.addAttribute("users", users);

        return "users-list";
    }

    @RequestMapping("/giveManagerRole")
    public String giveManagerRole(@RequestParam("theId")Long theId) {

        userService.addRole(theId);
        return "redirect:/admin/listUsers";
    }

}
