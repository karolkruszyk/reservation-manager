package pl.reservationmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.reservationmanager.entity.Service;
import pl.reservationmanager.service.ServiceService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

@Transactional
@Controller
@RequestMapping("/reserve")
public class ReservationController {

    @Autowired
    ServiceService serviceService;

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

        theModel.addAttribute("service", serviceService.getService(serviceId));
        theModel.addAttribute("date", dateObj);
        theModel.addAttribute("time", timeObj);

        return "confirm-reservation";
    }

    @RequestMapping("/processReservation")
    public String processReservation(Model theModel) {
        return "reservation-confirmation";
    }

}
