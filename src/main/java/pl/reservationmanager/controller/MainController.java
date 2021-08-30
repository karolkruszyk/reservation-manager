package pl.reservationmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.reservationmanager.entity.Service;
import pl.reservationmanager.service.ServiceService;

import java.util.List;

@Controller
public class MainController {
    @Autowired
    ServiceService serviceService;

    @GetMapping("/")
    public String showHome(Model theModel) {
        List<Service> services = serviceService.getServices();
        theModel.addAttribute("services", services);
        System.out.println(services);
        return "home";
    }

    @RequestMapping("/showServiceForm")
    public String showServiceForm(Model theModel) {
        Service newService = new Service();
        theModel.addAttribute(newService);
        return "add-service";
    }

    @Transactional
    @PostMapping("/processServiceForm")
    public String processServiceForm(@ModelAttribute("service") Service theService) {
        serviceService.addService(theService);
        return "redirect:/";
    }

}
