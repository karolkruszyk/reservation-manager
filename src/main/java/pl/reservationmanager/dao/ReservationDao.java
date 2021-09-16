package pl.reservationmanager.dao;

import pl.reservationmanager.entity.Reservation;

import java.util.List;

public interface ReservationDao {
    Reservation getReservation(Long reservationId);
    List<Reservation> getReservations();
    void addReservation(Reservation reservation);
    void deleteReservation(Long reservationId);
}
