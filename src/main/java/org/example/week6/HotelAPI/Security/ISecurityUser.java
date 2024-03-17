package org.example.week6.HotelAPI.Security;

import javax.management.relation.Role;
import java.util.Set;

public interface ISecurityUser {
    Set<String> getRolesAsStrings();
    boolean verifyPassword(String pw);
    void addRole(Role role);
    void removeRole(String role);
}
