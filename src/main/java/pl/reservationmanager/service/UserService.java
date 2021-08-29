package pl.reservationmanager.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import pl.reservationmanager.entity.User;
import pl.reservationmanager.user.CrmUser;

public interface UserService extends UserDetailsService {

    User findByUserName(String userName);

    void save(CrmUser crmUser);
}
