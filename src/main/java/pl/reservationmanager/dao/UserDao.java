package pl.reservationmanager.dao;

import pl.reservationmanager.entity.User;

public interface UserDao {
    User findByUserName(String userName);
    User findByPhoneNumber(String phoneNumber);

    void save(User user);


}