package pl.reservationmanager.dao;

import pl.reservationmanager.entity.User;

import java.util.List;

public interface UserDao {
    User findByUserName(String userName);
    User findByPhoneNumber(String phoneNumber);

    void save(User user);


    List<User> getUsers();

    void update(Long theId);

    Long getUserId();
}