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
import pl.reservationmanager.crm.CrmUser;
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
import java.util.*;

@Transactional
@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    ServiceService serviceService;

    @Autowired
    ReservationService reservationService;

    @Autowired
    UserService userService;

    @RequestMapping("/accountDetails")
    public String accountDetails(Model theModel) {
        theModel.addAttribute("user", userService.getUserById(userService.getUserId()));
        return "account-details";
    }

    @RequestMapping("/changePhoneNumber")
    public String changePhoneNumber(@RequestParam("userId") Long userId, Model theModel) {
        theModel.addAttribute(new CrmUser());
        User user = userService.getUserById(userId);
        theModel.addAttribute(user);
        return "change-phone";
    }

    @RequestMapping("/processPhoneNumberForm")
    public String processPhoneNumberForm(@ModelAttribute("crmUser") CrmUser crmUser, Model theModel) {
        User theUser = userService.getUserById(userService.getUserId());
        theUser.setPhoneNumber(crmUser.getPhoneNumber());
        return "redirect:/customer/accountDetails";
    }

    @RequestMapping("/bookingPage")
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
        int skipMinutes = 15;

        workingHours.add(startingTime);

        while(workingHours.get(workingHours.size()-1).isBefore(endingTime)) {
            LocalTime tempHour;
            tempHour = workingHours.get(workingHours.size() - 1).plusMinutes(skipMinutes);
            workingHours.add(tempHour);
        }

        List<Reservation> reservations = reservationService.getReservations();
        List<LocalDateTime> reservationDates = new ArrayList<>();
        for(Reservation reservation : reservations) {
            reservationDates.add(reservation.getDateTime());
        }

        List<DetailedReservation> detailedReservations = getDetailedReservationList(reservations);

        theModel.addAttribute("detailedReservations", detailedReservations);
        theModel.addAttribute("reservationDates", reservationDates);
        theModel.addAttribute("now", LocalTime.now());
        theModel.addAttribute("today", LocalDate.now());
        theModel.addAttribute("workingHours", workingHours);
        theModel.addAttribute("datesToDisplay", datesToDisplay);
        theModel.addAttribute("service", service);


        Map<LocalDate, List<LocalTime>> dateTimeMap = new HashMap<>();
        for(LocalDate date : datesToDisplay) {
            dateTimeMap.put(date, workingHours);
        }



        for(DetailedReservation detailedReservation : detailedReservations) {
            for(LocalDate date : dateTimeMap.keySet()) {

                List<LocalTime> toDelete = new ArrayList<>();
                if(detailedReservation.getReservation().getDateTime().toLocalDate().equals(date)) {

                    for (int i = detailedReservation.getService().getDuration()-1; i >= 0; i -= 1) {
                        toDelete.add(detailedReservation.getReservation().getDateTime().toLocalTime().plusMinutes(i));
                    }
                    for (int i = service.getDuration()-1; i >= 0; i -= 1) {
                        toDelete.add(detailedReservation.getReservation().getDateTime().toLocalTime().minusMinutes(i));
                    }
                }

                if(date.equals(LocalDate.now())) {
                    List<LocalTime> hoursToDelete = new ArrayList<>();
                    if(startingTime.isBefore(LocalTime.now())) {
                        hoursToDelete.add(startingTime);
                        while(hoursToDelete.get(hoursToDelete.size()-1).isBefore(LocalTime.now())) {

                            hoursToDelete.add(hoursToDelete.get(hoursToDelete.size()-1).plusMinutes(skipMinutes));
                        }
                        toDelete.addAll(hoursToDelete);
                    }
                }

                List<LocalTime> editedList = new ArrayList<>(dateTimeMap.get(date));

                for(LocalTime varToDelete : toDelete) {
                    editedList.remove(varToDelete);
                }
                dateTimeMap.replace(date, editedList);
            }
        }

        System.out.println(LocalTime.now());

        theModel.addAttribute("map", dateTimeMap);


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
    public String showUserReservations(Model theModel) {
        Long userId = userService.getUserId();
        List<Reservation> userReservations = reservationService.getUserReservations(userId);
        List<DetailedReservation> detailedReservations = getDetailedReservationList(userReservations);
        theModel.addAttribute("detailedReservations", detailedReservations);
        return "user-reservations";
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
