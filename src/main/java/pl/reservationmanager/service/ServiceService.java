package pl.reservationmanager.service;

import pl.reservationmanager.crm.CrmService;
import pl.reservationmanager.entity.Service;

import java.util.List;

public interface ServiceService {
    public Service getService(Long theId);
    public List<Service> getServices();
    public void addService(CrmService crmService);
    public void deleteService(Long theId);
    public void updateService(Long theId, CrmService crmService);

}
