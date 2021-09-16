package pl.reservationmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.reservationmanager.crm.CrmReservation;
import pl.reservationmanager.entity.Service;
import pl.reservationmanager.service.ReservationService;
import pl.reservationmanager.service.ServiceService;
import pl.reservationmanager.service.UserService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

@Transactional
@Controller
@RequestMapping("/reserve")
public class ReservationController {

    @Autowired
    ServiceService serviceService;

    @Autowired
    UserService userService;

    @Autowired
    ReservationService reservationService;

    @RequestMapping("/booking")
    public String showBookingPage(@RequestParam("theId") Long theId, Model theModel){
        Service service = serviceService.getService(theId);

        LocalDate today = LocalDate.now();
        ArrayList<LocalDate> datesToDisplay = new ArrayList<>();
        for(int days=0; days<=7; days++) {
            datesToDisplay.add(today.plusDays(days));
        }


        LocalTime startingTime = LocalTime.parse("08:00:00");
        LocalTime endingTime = LocalTime.parse("18:00:00");
        ArrayList<LocalTime> workingHours = new ArrayList<>();

        workingHours.add(startingTime);

        while(workingHours.get(workingHours.size()-1).isBefore(endingTime)) {
            LocalTime tempHour;
            tempHour = workingHours.get(workingHours.size() - 1).plusMinutes(15);
            workingHours.add(tempHour);

        }

        theModel.addAttribute("workingHours", workingHours);
        theModel.addAttribute("datesToDisplay", datesToDisplay);
        theModel.addAttribute("service", service);

        return "booking-page";
    }

    @RequestMapping("/makeReservation")
    public String makeReservation(@RequestParam("date") String date,
                                  @RequestParam("hour") String time,
                                  @RequestParam("serviceId") Long serviceId,
                                  Model theModel) {

        LocalDate dateObj = LocalDate.parse(date);
        LocalTime timeObj = LocalTime.parse(time);

        CrmReservation crmReservation = new CrmReservation();
        crmReservation.setUserId(userService.getUserId());
        crmReservation.setServiceId(serviceId);
        crmReservation.setStatus("WAITING");
        crmReservation.setDate(dateObj);
        crmReservation.setTime(timeObj);

        theModel.addAttribute("service", serviceService.getService(serviceId));
        theModel.addAttribute("crmReservation", crmReservation);

        return "confirm-reservation";
    }

    @RequestMapping("/processReservation")
    public String processReservation(@ModelAttribute("crmReservation") CrmReservation crmReservation, BindingResult bindingResult, Model theModel) {
        reservationService.addReservation(crmReservation);
        return "reservation-confirmation";
    }

}
