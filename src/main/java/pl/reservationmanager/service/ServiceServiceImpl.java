package pl.reservationmanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import pl.reservationmanager.crm.CrmService;
import pl.reservationmanager.dao.ServiceDao;
import pl.reservationmanager.entity.Service;

import java.util.List;

@org.springframework.stereotype.Service
public class ServiceServiceImpl implements ServiceService{

    @Autowired
    ServiceDao serviceDao;

    @Override
    public Service getService(Long theId) {
        Service theService = serviceDao.getService(theId);
        return theService;
    }

    @Override
    @Transactional
    public List<Service> getServices() {

        return serviceDao.getServices();
    }

    @Override
    public void addService(CrmService crmService) {
        Service service = new Service();
        service.setName(crmService.getName());
        service.setDuration(Integer.parseInt(crmService.getDuration()));
        service.setPrice(Integer.parseInt(crmService.getPrice()));
        serviceDao.saveOrUpdate(service);
    }

    @Override
    public void updateService(Long theId, CrmService crmService) {
        Service service = getService(theId);
        service.setName(crmService.getName());
        service.setPrice(Integer.parseInt(crmService.getPrice()));
        service.setDuration(Integer.parseInt(crmService.getDuration()));
        serviceDao.saveOrUpdate(service);
    }

    @Override
    public void deleteService(Long theId) {
        serviceDao.deleteService(theId);
    }

}
