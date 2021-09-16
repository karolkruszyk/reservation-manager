package pl.reservationmanager.service;

import pl.reservationmanager.crm.CrmReservation;
import pl.reservationmanager.entity.Reservation;

import java.util.List;

public interface ReservationService {
    public Reservation getReservation(Long reservationId);
    public List<Reservation> getReservations();
    public void addReservation(CrmReservation crmReservation);
    public void deleteReservation(Long reservationId);
}
