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
import pl.reservationmanager.entity.Reservation;
import pl.reservationmanager.entity.Service;
import pl.reservationmanager.entity.User;
import pl.reservationmanager.pojo.DetailedReservation;
import pl.reservationmanager.service.ReservationService;
import pl.reservationmanager.service.ServiceService;
import pl.reservationmanager.service.UserService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

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
            int minutesToAdd = 15;
            LocalTime tempHour;
            tempHour = workingHours.get(workingHours.size() - 1).plusMinutes(minutesToAdd);
            workingHours.add(tempHour);

        }

        theModel.addAttribute("now", LocalTime.now());
        theModel.addAttribute("today", LocalDate.now());
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

    @RequestMapping("/userReservations")
    public String showUsersReservations(Model theModel) {
        Long userId = userService.getUserId();
        List<Reservation> userReservations = reservationService.getUsersReservations(userId);
        List<DetailedReservation> detailedReservations = getDetailedReservationList(userReservations);
        theModel.addAttribute("detailedReservations", detailedReservations);
        return "user-reservations";
    }

    @RequestMapping("/reservationList")
    public String showReservationList(Model theModel, @RequestParam(required = false) String status) {




        List<Reservation> reservations = new ArrayList<>();
        if (status != null) {
            reservations = reservationService.getReservations(status);
        } else {
            reservations = reservationService.getReservations();
        }

        LocalDateTime now = LocalDateTime.now();
        for(Reservation reservation : reservations) {
            if(reservation.getDateTime().isBefore(now)){
                reservationService.deleteReservation(reservation.getReservationId());
            }
        }

        List<DetailedReservation> detailedReservations = getDetailedReservationList(reservations);
        theModel.addAttribute("detailedReservations", detailedReservations);
        return "reservation-list";
    }

    @RequestMapping("/confirm")
    public String confirm(@RequestParam("reservationId") Long reservationId, Model theModel) {
            reservationService.confirmReservation(reservationId);
            return "redirect:/reserve/reservationList";
    }

    @RequestMapping("/reject")
    public String reject(@RequestParam("reservationId") Long reservationId, Model theModel) {
        reservationService.deleteReservation(reservationId);
        return "redirect:/reserve/reservationList";
    }

    private List<DetailedReservation> getDetailedReservationList(List<Reservation> reservations) {
        List<DetailedReservation> detailedReservations = new ArrayList<>();
        for(Reservation reservation : reservations) {
            User user = userService.getUserById(reservation.getUserId());
            Service service = serviceService.getService(reservation.getServiceId());
            DetailedReservation detailedReservation = new DetailedReservation(reservation, user, service);
            detailedReservations.add(detailedReservation);
        }
        return detailedReservations;
    }

}
