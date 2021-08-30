package pl.reservationmanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import pl.reservationmanager.dao.ServiceDao;
import pl.reservationmanager.entity.Service;

import java.util.List;

@org.springframework.stereotype.Service
public class ServiceServiceImpl implements ServiceService{

    @Autowired
    ServiceDao serviceDao;

    @Override
    @Transactional
    public List<Service> getServices() {

        return serviceDao.getServices();
    }

    @Override
    public void addService(Service theService) {
        serviceDao.addService(theService);
    }
}
