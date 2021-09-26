package pl.reservationmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.reservationmanager.entity.Reservation;
import pl.reservationmanager.entity.Service;
import pl.reservationmanager.entity.User;
import pl.reservationmanager.pojo.DetailedReservation;
import pl.reservationmanager.service.ReservationService;
import pl.reservationmanager.service.ServiceService;
import pl.reservationmanager.service.UserService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Controller
@RequestMapping("/manager")
public class ManagerController {

    @Autowired
    ServiceService serviceService;

    @Autowired
    ReservationService reservationService;

    @Autowired
    UserService userService;

    @RequestMapping("/reservationList")
    public String showReservationList(Model theModel, @RequestParam(required = false) String status) {




        List<Reservation> reservations = new ArrayList<>();
        if (status != null && status != "") {
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

    @RequestMapping("/confirmReservation")
    public String confirmReservation(@RequestParam("reservationId") Long reservationId, Model theModel) {
        reservationService.confirmReservation(reservationId);
        return "redirect:/manager/reservationList?status=WAITING";
    }

    @RequestMapping("/rejectReservation")
    public String rejectReservation(@RequestParam("reservationId") Long reservationId, Model theModel) {
        reservationService.deleteReservation(reservationId);
        return "redirect:/manager/reservationList?status=WAITING";
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
