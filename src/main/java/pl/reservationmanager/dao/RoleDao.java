package pl.reservationmanager.dao;

import pl.reservationmanager.entity.Role;

public interface RoleDao {

    public Role findRoleByName(String theRoleName);

}
