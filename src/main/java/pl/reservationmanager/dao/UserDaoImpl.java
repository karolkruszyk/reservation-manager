package pl.reservationmanager.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import pl.reservationmanager.entity.User;

import java.util.List;

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

    @Override
    public List<User> getUsers() {
        Session session = sessionFactory.getCurrentSession();
        Query theQuery = session.createQuery("from User", User.class);
        List<User> users = theQuery.getResultList();

        return users;
    }

    @Override
    public void update(Long theId) {
        Session session = sessionFactory.getCurrentSession();
        Query preQuery = session.createSQLQuery("SELECT user_id from users_role where user_id=" + theId + " AND role_id=2");
        List<String> selected = preQuery.getResultList();


        if(selected.isEmpty()) {
            Query query = session.createSQLQuery("INSERT INTO users_role(user_id, role_id) VALUES (" + theId + ", 2)");
            query.executeUpdate();
        } else {
            Query query = session.createSQLQuery("DELETE FROM users_role WHERE user_id=" + theId + " AND role_id=2");
            query.executeUpdate();
        }

    }

    public Long getUserId() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Session session = sessionFactory.getCurrentSession();

        Integer id;
        if (principal instanceof UserDetails) {
            String username = ((UserDetails)principal).getUsername();
            Query query = session.createSQLQuery("SELECT id FROM user WHERE username='" + username + "'");

            id = (Integer) query.getSingleResult();

        } else {
            String username = principal.toString();
            Query query = session.createSQLQuery("SELECT id FROM user WHERE username=" + username);

            id = (Integer) query.getSingleResult();
        }

        return id.longValue();
    }

    @Override
    public User getUserById(Long userId) {
        User theUser;
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from User where id=:userId", User.class);
        query.setParameter("userId", userId);
        theUser = (User) query.getSingleResult();
        return theUser;
    }
}
