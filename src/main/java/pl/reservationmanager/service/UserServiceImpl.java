package pl.reservationmanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.reservationmanager.crm.CrmUser;
import pl.reservationmanager.dao.RoleDao;
import pl.reservationmanager.dao.UserDao;
import pl.reservationmanager.entity.Role;
import pl.reservationmanager.entity.User;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public User findByUserName(String userName) {
        return userDao.findByUserName(userName);
    }

    @Override
    @Transactional
    public User findByPhoneNumber(String phoneNumber) {
        return userDao.findByPhoneNumber(phoneNumber);
    }

    @Override
    public List<User> getUsers() {
        return userDao.getUsers();
    }

    @Override
    @Transactional
    public void save(CrmUser crmUser) {
        User user = new User();

        user.setUserName(crmUser.getUserName());
        user.setPassword(passwordEncoder.encode(crmUser.getPassword()));
        user.setFirstName(crmUser.getFirstName());
        user.setLastName(crmUser.getLastName());
        user.setPhoneNumber(crmUser.getPhoneNumber());

        user.setRoles(Arrays.asList(roleDao.findRoleByName("ROLE_CUSTOMER")));

        userDao.save(user);
    }

    @Override
    public void addRole(Long theId) {
        userDao.update(theId);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userDao.findByUserName(userName);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
