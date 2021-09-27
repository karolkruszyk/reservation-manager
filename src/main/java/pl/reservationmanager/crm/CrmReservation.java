package pl.reservationmanager.crm;

import java.time.LocalDate;
import java.time.LocalTime;

public class CrmReservation {

    private Long reservationId;
    private Long userId;
    private Long serviceId;
    private String status;
    private LocalDate date;
    private LocalTime time;
    private String noAccountName;
    private String noAccountPhone;

    public CrmReservation() {
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getNoAccountName() {
        return noAccountName;
    }

    public void setNoAccountName(String noAccountName) {
        this.noAccountName = noAccountName;
    }

    public String getNoAccountPhone() {
        return noAccountPhone;
    }

    public void setNoAccountPhone(String noAccountPhone) {
        this.noAccountPhone = noAccountPhone;
    }
}
