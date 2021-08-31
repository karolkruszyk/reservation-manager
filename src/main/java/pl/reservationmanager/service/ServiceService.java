package pl.reservationmanager.service;

import pl.reservationmanager.entity.Service;

import java.util.List;

public interface ServiceService {
    public Service getService(Long theId);
    public List<Service> getServices();
    public void addService(Service theService);
    public void deleteService(Long theId);
}
