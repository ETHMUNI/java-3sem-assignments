package org.example.week6and7.HotelAPI.Controllers;

import com.nimbusds.jose.JOSEException;
import io.javalin.http.Handler;
import org.example.week6and7.HotelAPI.Entities.UserDTO;
import org.example.week6and7.HotelAPI.exceptions.AuthException;

import java.text.ParseException;
import java.util.Set;

public interface ISecurityController {

    Handler register(); // creates a new User
    Handler login(); // logs in a user and returns a token
    String createToken(UserDTO user); // creates a token based on the UserDTO, sercret and expiration time
    boolean authorize(UserDTO user, Set<String> allowedRoles); // checks if the user has the required roles
    Handler authenticate(); // checks if the token is valid and adds the user (with roles) to the context
    UserDTO verifyToken(String token);
    boolean tokenIsValid(String token, String secret) throws ParseException, JOSEException, AuthException;
    boolean tokenNotExpired(String token) throws ParseException, AuthException;
    UserDTO getUserWithRolesFromToken(String token) throws ParseException;
    int timeToExpire(String token) throws ParseException, AuthException;
}
