package pl.reservationmanager.dao;

import pl.reservationmanager.entity.Service;

import java.util.List;

public interface ServiceDao {
    public Service findServiceByName(String serviceName);
    public List<Service> getServices();
    public void saveOrUpdate(Service theService);
    public void deleteService(Long theId);

    Service getService(Long theId);
}
