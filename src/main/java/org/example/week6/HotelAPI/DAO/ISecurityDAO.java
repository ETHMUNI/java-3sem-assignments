package org.example.week6.HotelAPI.DAO;

import io.javalin.validation.ValidationException;
import org.example.week6.HotelAPI.Security.User;

import javax.management.relation.Role;


public interface ISecurityDAO {
    User getVerifiedUser(String username, String password) throws ValidationException;
    User createUser(String username, String password);
    Role createRole(String role);
    User addUserRole(String username, String role);
}
