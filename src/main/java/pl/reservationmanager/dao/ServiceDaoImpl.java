package pl.reservationmanager.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.reservationmanager.entity.Service;

import java.util.List;

@Repository
public class ServiceDaoImpl implements ServiceDao{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Service findServiceByName(String serviceName) {
        Session currentSession = sessionFactory.getCurrentSession();

        Query<Service> theQuery = currentSession.createQuery("from Service where name=:serviceName", Service.class);
        theQuery.setParameter("serviceName", serviceName);

        Service theService = null;

        try {
            theService = theQuery.getSingleResult();
        } catch (Exception e) {
            theService = null;
        }

        return theService;
    }

    @Override
    public List<Service> getServices() {
        Session currentSession = sessionFactory.getCurrentSession();

        Query theQuery = currentSession.createQuery("from Service", Service.class);

        List<Service> services = theQuery.getResultList();
        return services;
    }

    @Override
    public void addService(Service theService) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(theService);
    }

    @Override
    public void deleteService(Long theId) {
        Session session = sessionFactory.getCurrentSession();
        Service theService = session.get(Service.class, theId);
        session.delete(theService);
    }

    @Override
    public Service getService(Long theId) {
        Session session = sessionFactory.getCurrentSession();
        Service theService = session.get(Service.class, theId);
        return theService;
    }
}
