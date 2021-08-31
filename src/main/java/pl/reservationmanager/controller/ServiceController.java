package pl.reservationmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.reservationmanager.entity.Service;
import pl.reservationmanager.service.ServiceService;

import java.util.List;

@Transactional
@Controller
@RequestMapping("/services")
public class ServiceController {

    @Autowired
    ServiceService serviceService;

    @RequestMapping("/editServiceList")
    public String editServiceList(Model theModel) {
        List<Service> services = serviceService.getServices();
        theModel.addAttribute("services", services);
        return "edit-services";
    }

    @RequestMapping("/showServiceForm")
    public String showServiceForm(Model theModel) {
        Service newService = new Service();
        theModel.addAttribute(newService);
        return "add-service";
    }

    @RequestMapping("/processServiceForm")
    public String processServiceForm(@ModelAttribute("service") Service theService) {
        serviceService.addService(theService);
        return "redirect:/services/editServiceList";
    }

    @RequestMapping("/delete")
    public String deleteService(@ModelAttribute("serviceId") Long theId) {
        serviceService.deleteService(theId);
        return "redirect:/services/editServiceList";
    }

    @GetMapping("/showUpdateForm")
    public String showUpdateForm(@RequestParam("serviceId") Long theId, Model theModel) {
        Service theService = serviceService.getService(theId);
        theModel.addAttribute("theService", theService);
        return "update-service";
    }


}
