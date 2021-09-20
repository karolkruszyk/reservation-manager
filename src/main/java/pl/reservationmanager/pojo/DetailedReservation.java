package pl.reservationmanager.pojo;

import pl.reservationmanager.entity.Reservation;
import pl.reservationmanager.entity.Service;
import pl.reservationmanager.entity.User;

public class DetailedReservation {
    private Reservation reservation;
    private User user;
    private Service service;

    public DetailedReservation(Reservation reservation, User user, Service service) {
        this.reservation = reservation;
        this.user = user;
        this.service = service;
    }

    public DetailedReservation() {
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }
}
