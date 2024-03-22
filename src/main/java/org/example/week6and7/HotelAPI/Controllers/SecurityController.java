package org.example.week6and7.HotelAPI.Controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.SignedJWT;
import io.javalin.http.Handler;
import io.javalin.http.HttpStatus;
import io.javalin.validation.ValidationException;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.example.week6and7.HotelAPI.DAO.ISecurityDAO;
import org.example.week6and7.HotelAPI.Entities.TokenDTO;
import org.example.week6and7.HotelAPI.Entities.UserDTO;
import org.example.week6and7.HotelAPI.Security.User;
import org.example.week6and7.HotelAPI.Utils.TokenUtils;
import org.example.week6and7.HotelAPI.Utils.Utils;
import org.example.week6and7.HotelAPI.exceptions.ApiException;
import org.example.week6and7.HotelAPI.exceptions.AuthException;

import java.text.ParseException;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

public class SecurityController implements ISecurityController {

    ObjectMapper objectMapper = new ObjectMapper();
    private ISecurityDAO UserDAO;

    private TokenUtils tokenUtils;


    @Override
    public Handler register() {
        return (ctx) -> {
            ObjectNode returnObject = objectMapper.createObjectNode();
            try {
                UserDTO userInput = ctx.bodyAsClass(UserDTO.class);
                User created = UserDAO.createUser(userInput.getUsername(), userInput.getPassword());

                String token = createToken(new UserDTO(created));
                ctx.status(HttpStatus.CREATED).json(new TokenDTO(token, userInput.getUsername()));
            } catch (EntityExistsException e) {
                ctx.status(HttpStatus.UNPROCESSABLE_CONTENT);
                ctx.json(returnObject.put("msg", "User already exists"));
            }
        };
    }

    @Override
    public Handler login() {
        return (ctx) -> {
            ObjectNode returnObject = objectMapper.createObjectNode(); // for sending json messages back to the client
            try {
                UserDTO user = ctx.bodyAsClass(UserDTO.class);
                System.out.println("USER IN LOGIN: " + user);

                User verifiedUserEntity = UserDAO.getVerifiedUser(user.getUsername(), user.getPassword());
                String token = createToken(new UserDTO(verifiedUserEntity));
                ctx.status(200).json(new TokenDTO(token, user.getUsername()));

            } catch (EntityNotFoundException | ValidationException e) {
                ctx.status(401);
                System.out.println(e.getMessage());
                ctx.json(returnObject.put("msg", e.getMessage()));
            }
        };
    }

    @Override
    public String createToken(UserDTO user) {
        String ISSUER;
        String TOKEN_EXPIRE_TIME;
        String SECRET_KEY;

        if (System.getenv("DEPLOYED") != null) {
            ISSUER = System.getenv("ISSUER");
            TOKEN_EXPIRE_TIME = System.getenv("TOKEN_EXPIRE_TIME");
            SECRET_KEY = System.getenv("SECRET_KEY");
        } else {
            ISSUER = "Thomas Hartmann";
            TOKEN_EXPIRE_TIME = "1800000"; // 30 minutes in milliseconds
            SECRET_KEY = Utils.getPropertyValue("SECRET_KEY","config.properties");
        }
        return tokenUtils.createToken(user, ISSUER, TOKEN_EXPIRE_TIME, SECRET_KEY);
    }

    @Override
    public boolean authorize(UserDTO user, Set<String> allowedRoles) {
        // Called from the ApplicationConfig.setSecurityRoles

        AtomicBoolean hasAccess = new AtomicBoolean(false); // Since we update this in a lambda expression, we need to use an AtomicBoolean
        if (user != null) {
            user.getRoles().stream().forEach(role -> {
                if (allowedRoles.contains(role.toUpperCase())) {
                    hasAccess.set(true);
                }
            });
        }
        return hasAccess.get();
    }

    @Override
    public Handler authenticate() {
        // To check the users roles against the allowed roles for the endpoint (managed by javalins accessManager)
        // Checked in 'before filter' -> Check for Authorization header to find token.
        // Find user inside the token, forward the ctx object with userDTO on attribute
        // When ctx hits the endpoint it will have the user on the attribute to check for roles (ApplicationConfig -> accessManager)
        ObjectNode returnObject = objectMapper.createObjectNode();
        return (ctx) -> {
            if(ctx.method().toString().equals("OPTIONS")) {
                ctx.status(200);
                return;
            }
            String header = ctx.header("Authorization");
            if (header == null) {
                ctx.status(HttpStatus.FORBIDDEN).json(returnObject.put("msg", "Authorization header missing"));
                return;
            }
            String token = header.split(" ")[1];
            if (token == null) {
                ctx.status(HttpStatus.FORBIDDEN).json(returnObject.put("msg", "Authorization header malformed"));
                return;
            }
            UserDTO verifiedTokenUser = verifyToken(token);
            if (verifiedTokenUser == null) {
                ctx.status(HttpStatus.FORBIDDEN).json(returnObject.put("msg", "Invalid User or Token"));
            }
            System.out.println("USER IN AUTHENTICATE: " + verifiedTokenUser);
            ctx.attribute("user", verifiedTokenUser);
        };
    }

    @Override
    public UserDTO verifyToken(String token) {
        boolean IS_DEPLOYED = (System.getenv("DEPLOYED") != null);
        String SECRET = IS_DEPLOYED ? System.getenv("SECRET_KEY") : Utils.getPropertyValue("SECRET_KEY","config.properties");

        try {
            if (tokenUtils.tokenIsValid(token, SECRET) && tokenUtils.tokenNotExpired(token)) {
                return tokenUtils.getUserWithRolesFromToken(token);
            } else {
                throw new AuthException(403, "Token is not valid");
            }
        } catch (ParseException | JOSEException | AuthException e) {
            e.printStackTrace();
            throw new ApiException(HttpStatus.UNAUTHORIZED.getCode(), "Unauthorized. Could not verify token");
        }
    }

    @Override
    public boolean tokenIsValid(String token, String secret) throws ParseException, JOSEException, AuthException {
        SignedJWT jwt = SignedJWT.parse(token);
        if (jwt.verify(new MACVerifier(secret)))
            return true;
        else
            throw new AuthException(403, "Token is not valid");
    }

    @Override
    public boolean tokenNotExpired(String token) throws ParseException, AuthException {
        if (timeToExpire(token) > 0)
            return true;
        else
            throw new AuthException(403, "Token has expired");
    }
    @Override
    public UserDTO getUserWithRolesFromToken(String token) throws ParseException {
        return null;
    }

    @Override
    public int timeToExpire(String token) throws ParseException, AuthException {
        SignedJWT jwt = SignedJWT.parse(token);
        return (int) (jwt.getJWTClaimsSet().getExpirationTime().getTime() - new Date().getTime());
    }
}
