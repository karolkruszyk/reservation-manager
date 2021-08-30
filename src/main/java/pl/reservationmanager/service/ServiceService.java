package pl.reservationmanager.service;

import pl.reservationmanager.entity.Service;

import java.util.List;

public interface ServiceService {
    public List<Service> getServices();
    public void addService(Service theService);
}
