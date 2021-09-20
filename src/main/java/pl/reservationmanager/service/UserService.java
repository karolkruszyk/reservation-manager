package pl.reservationmanager.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import pl.reservationmanager.crm.CrmUser;
import pl.reservationmanager.entity.User;

import java.util.List;

public interface UserService extends UserDetailsService {

    User findByUserName(String userName);
    User findByPhoneNumber(String phoneNumber);
    List<User> getUsers();
    User getUserById(Long userId);

    void save(CrmUser crmUser);

    void addRole(Long theId);
    Long getUserId();
}
