package org.example.week6and7.HotelAPI.DAO;

import org.example.week6and7.HotelAPI.Security.User;
import org.example.week6and7.HotelAPI.exceptions.AuthException;

import org.example.week6and7.HotelAPI.Security.Role;


public interface ISecurityDAO {
    User getVerifiedUser(String username, String password) throws AuthException;
    User createUser(String username, String password);
    Role createRole(String role);
    User addUserRole(String username, String role);
}
