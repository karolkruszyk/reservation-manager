package pl.reservationmanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.reservationmanager.crm.CrmReservation;
import pl.reservationmanager.dao.ReservationDao;
import pl.reservationmanager.entity.Reservation;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService{

    @Autowired
    ReservationDao reservationDao;


    @Override
    public Reservation getReservation(Long reservationId) {
        Reservation reservation = reservationDao.getReservation(reservationId);
        return reservation;
    }

    @Override
    public List<Reservation> getReservations() {
        List<Reservation> reservations = reservationDao.getReservations();
        return reservations;
    }

    @Override
    public void addReservation(CrmReservation crmReservation) {
        Reservation reservation = new Reservation();
        reservation.setUserId(crmReservation.getUserId());
        reservation.setServiceId(crmReservation.getServiceId());
        reservation.setStatus(crmReservation.getStatus());
        reservation.setDateTime(LocalDateTime.of(crmReservation.getDate(), crmReservation.getTime()));
        reservationDao.addReservation(reservation);
    }

    @Override
    public void deleteReservation(Long reservationId) {
        reservationDao.deleteReservation(reservationId);
    }
}
