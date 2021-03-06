package pl.reservationmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.reservationmanager.entity.Service;
import pl.reservationmanager.service.ServiceService;

import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {


    @Autowired
    ServiceService serviceService;

    @RequestMapping("")
    public String showHome(Model theModel) {
        List<Service> services = serviceService.getServices();
        theModel.addAttribute("services", services);


        return "home";
    }



}
