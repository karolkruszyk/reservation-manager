package pl.reservationmanager.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pl.reservationmanager.entity.User;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public User findByUserName(String theUserName) {
        Session currentSession = sessionFactory.getCurrentSession();

        Query<User> theQuery = currentSession.createQuery("from User where userName=:uName", User.class);
        theQuery.setParameter("uName", theUserName);
        User theUser = null;
        try {
            theUser = theQuery.getSingleResult();
        } catch (Exception e) {
            theUser = null;
        }

        return theUser;
    }

    @Override
    public User findByPhoneNumber(String phoneNumber) {
        Session currentSession = sessionFactory.getCurrentSession();

        Query<User> theQuery = currentSession.createQuery("from User where phoneNumber=:pNumber", User.class);
        theQuery.setParameter("pNumber", phoneNumber);
        User theUser = null;
        try {
            theUser = theQuery.getSingleResult();
        } catch (Exception e) {
            theUser = null;
        }

        return theUser;
    }

    @Override
    public void save(User theUser) {
        Session currentSession = sessionFactory.getCurrentSession();

        currentSession.saveOrUpdate(theUser);
    }

}
