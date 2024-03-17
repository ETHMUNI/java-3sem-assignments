package org.example.week6.HotelAPI.Controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.javalin.http.Context;
import jakarta.persistence.EntityManagerFactory;
import org.example.week6.HotelAPI.DAO.UserDAO;
import org.example.week6.HotelAPI.HibernateConfig;
import org.example.week6.HotelAPI.Security.User;
import org.example.week6.HotelAPI.Utils.TokenFactory;
import org.example.week6.HotelAPI.exceptions.ApiException;
import org.example.week6.HotelAPI.exceptions.AuthException;


import java.util.Set;

public class UserController {

    private final UserDAO userDao;
    private final TokenFactory tokenFactory = TokenFactory.getInstance();

    public UserController() {
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory();
        userDao = UserDAO.getInstance(emf);

    }

    public void login(Context ctx) throws ApiException, AuthException {
        String[] userInfos = getUserInfos(ctx, true);
        User user = getVerfiedOrRegisterUser(userInfos[0], userInfos[1], "", false);
        String token = getToken(userInfos[0], user.getRolesAsStrings());

        // Create response
        ctx.status(200);
        ctx.result(createResponse(userInfos[0], token));
    }

    public void register(Context ctx) throws ApiException, AuthException {
        String[] userInfos = getUserInfos(ctx, false);
        User user = getVerfiedOrRegisterUser(userInfos[0], userInfos[1], userInfos[2], true);
        String token = getToken(userInfos[0], user.getRolesAsStrings());

        // Create response
        ctx.res().setStatus(201);
        ctx.result(createResponse(userInfos[0], token));
    }

    private String createResponse(String username, String token) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode responseJson = mapper.createObjectNode();
        responseJson.put("username", username);
        responseJson.put("token", token);
        return responseJson.toString();
    }

    private String[] getUserInfos(Context ctx, boolean tryLogin) throws ApiException {
        String request = ctx.body();
        return tokenFactory.parseJsonObject(request, tryLogin);
    }

    private User getVerfiedOrRegisterUser(String username, String password, String role, boolean isCreate) throws AuthException {
        return isCreate ? userDao.registerUser(username, password, role) : userDao.getVerifiedUser(username, password);

    }

    private String getToken(String username, Set<String> userRoles) throws ApiException {
        return tokenFactory.createToken(username, userRoles);
    }
}