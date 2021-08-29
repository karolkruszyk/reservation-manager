package pl.reservationmanager.dao;

import pl.reservationmanager.entity.User;

public interface UserDao {
    User findByUserName(String userName);

    void save(User user);

}