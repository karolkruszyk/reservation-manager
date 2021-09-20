package pl.reservationmanager.dao;

import pl.reservationmanager.entity.Reservation;

import java.util.List;

public interface ReservationDao {
    Reservation getReservation(Long reservationId);
    List<Reservation> getReservations();
    List<Reservation> getReservations(String status);
    void addReservation(Reservation reservation);
    void deleteReservation(Long reservationId);

    List<Reservation> getUsersReservations(Long userId);
}
