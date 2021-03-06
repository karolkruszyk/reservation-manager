package pl.reservationmanager.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.reservationmanager.entity.Reservation;

import java.util.List;

@Repository
public class ReservationDaoImpl implements ReservationDao{

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public Reservation getReservation(Long reservationId) {
        Session session = sessionFactory.getCurrentSession();
        Query<Reservation> query = session.createQuery("from Reservation where id=:resId", Reservation.class);
        query.setParameter("resId", reservationId);

        Reservation reservation;
        try {
            reservation = query.getSingleResult();
        } catch (Exception e) {
            reservation = null;
        }
        return reservation;
    }

    @Override
    public List<Reservation> getReservations() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Reservation order by date", Reservation.class);
        List<Reservation> reservations = query.getResultList();

        return reservations;
    }

    @Override
    public List<Reservation> getReservations(String status) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Reservation where status=:status order by date", Reservation.class);
        query.setParameter("status", status);
        List<Reservation> reservations = query.getResultList();
        return reservations;
    }

    @Override
    public void addReservation(Reservation reservation) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(reservation);
    }

    @Override
    public void deleteReservation(Long reservationId) {
        Reservation reservation = getReservation(reservationId);
        Session session = sessionFactory.getCurrentSession();
        session.delete(reservation);
    }

    @Override
    public List<Reservation> getUsersReservations(Long userId) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Reservation where userId=:userId order by date", Reservation.class);
        query.setParameter("userId", userId);
        List<Reservation> userReservations = query.getResultList();

        return userReservations;
    }
}
