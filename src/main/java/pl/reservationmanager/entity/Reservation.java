package pl.reservationmanager.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long reservationId;

    @Column(name="user_id")
    private Long userId;

    @Column(name="service_id")
    private Long serviceId;

    @Column(name="status")
    private String status;

    @Column(name="date")
    private LocalDateTime dateTime;

    public Reservation() {
    }

    public Reservation(Long reservationId, Long userId, Long serviceId, String status, LocalDateTime dateTime) {
        this.reservationId = reservationId;
        this.userId = userId;
        this.serviceId = serviceId;
        this.status = status;
        this.dateTime = dateTime;
    }

    public Reservation(Long userId, Long serviceId, String status, LocalDateTime dateTime) {
        this.userId = userId;
        this.serviceId = serviceId;
        this.status = status;
        this.dateTime = dateTime;
    }

    public Long getReservationId() {
        return reservationId;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "reservationId=" + reservationId +
                ", userId=" + userId +
                ", serviceId=" + serviceId +
                ", status='" + status + '\'' +
                ", dateTime=" + dateTime +
                '}';
    }
}
