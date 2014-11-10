	
package controllers;

import java.security.InvalidParameterException;
import java.sql.SQLException;
import java.util.Date;
import java.util.UUID;

import domain.UserEntity;
import requests.LoginRequest;
import requests.LoginResponse;
import requests.RegisterRequest;
import requests.RegisterResponse;

import utils.GeoHashUtil;
import domain.SessionTokenEntity;

import org.apache.commons.lang3.StringUtils;

import play.Logger;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;

import com.fasterxml.jackson.databind.JsonNode;
import common.cache.LRUAuthToken;
import exceptions.DatabaseAccessException;

import exceptions.ConflictException;


import daos.SessionTokenDAO;
import daos.UserDao;



public class UserServiceREST extends Controller
{

    @BodyParser.Of(BodyParser.Json.class)
    @Transactional
    public static Result login() throws SQLException
    {
        LoginRequest loginRequest = null;
        final LoginResponse loginResponse = new LoginResponse();

        // cast json payload to object
        try
        {
            final JsonNode json = request().body().asJson();
            loginRequest = Json.fromJson(json, LoginRequest.class);

            Logger.info("Login request @ " + new Date().toGMTString() + "---->" + json.toString());
        }
        catch (Exception e)
        {
            Logger.error("Login request @ " + new Date().toGMTString() + "---->" + e.getMessage());
            return badRequest("Invalid login request json payload!");
        }

        // validate the login request payload
        if (StringUtils.isBlank(loginRequest.getLoginAccount()) || StringUtils.isBlank(loginRequest.getPassword()))
        {
            return badRequest(
                    "Missing loginAccount or password in login request json payload!");
        }
        UserEntity user = null;
        // validate the login request in database
        try
        {
            UserDao ud = new UserDao();
            user = ud.verifyUserCredencialByAccount(loginRequest);
        }
        catch (Exception e)
        {
            e.printStackTrace();

            return internalServerError(new DatabaseAccessException("Database access error!").toString());
        }

        if (user == null)
        {
            return unauthorized("loginAccount or password is invalid, please check it!");
        }

        final Double latitude = loginRequest.getLatitude();
        final Double longitude = loginRequest.getLongitude();
        final String gcmId = loginRequest.getRegId();
        String geoHash = null;
        if (latitude != null && longitude != null) {
            geoHash = GeoHashUtil.getGeohash(latitude, longitude);
        }
        SessionTokenDAO stDao = new SessionTokenDAO();
        SessionTokenEntity sessionToken = stDao.retrieveSessionTokenByUserId(user.getUserId());
        if (sessionToken == null)
        {
            sessionToken = new SessionTokenEntity();
            sessionToken.setValue(UUID.randomUUID().toString());
            sessionToken.setUserId(user.getUserId());
        }
        if (geoHash != null) {
            sessionToken.setLatitude((float) latitude.doubleValue());
            sessionToken.setLongitude((float) longitude.doubleValue());
            sessionToken.setGeohash(geoHash);
        }
        if (gcmId != null) {
            sessionToken.setGcmId(gcmId);
        }
        stDao.createOrUpdateSesstionToken(sessionToken);

        LRUAuthToken.getInstance().put(sessionToken.getValue(), user.getUserId());

        loginResponse.setEmail(user.getEmail());
        loginResponse.setNickname(user.getNickname());
        loginResponse.setUserId(user.getUserId());
        loginResponse.setGender(user.getGender());
        loginResponse.setPhone(user.getPhone());
        loginResponse.setLastname(user.getLastname());
        loginResponse.setFirstname(user.getFirstname());
        loginResponse.setAuthToken(sessionToken.getValue());
        return ok(Json.toJson(loginResponse));
    }
    
    @BodyParser.Of(BodyParser.Json.class)
    @Transactional
    public static Result register()
    {
        JsonNode json = null;
        RegisterResponse registerResponse = new RegisterResponse();
        UserEntity user = null;
        // cast json payload to object
        //try
        //{
            json = request().body().asJson();
            RegisterRequest registerRequest = Json.fromJson(json, RegisterRequest.class);

            user = new UserEntity();
            user.setNickname(registerRequest.getNickname());
            user.setEmail(registerRequest.getEmail());
            user.setGender(registerRequest.getGender());
            user.setPhone(registerRequest.getPhone());
            user.setPassword(registerRequest.getPassword());
            user.setFirstname(registerRequest.getFirstname());
            user.setLastname(registerRequest.getLastname());

            Logger.info("Register request@ " + new Date().toGMTString() + "---->" + json.toString());

       // }
        /*catch (Exception e)
        {
            Logger.error("Register request@ " + new Date().toGMTString() + "---->" + e.getMessage());
            return badRequest((new InvalidJsonException("Register json body is invalid!")).toString());
        }*/

        // validate the register request payload
        /*if (StringUtils.isBlank(user.getEmail()) || StringUtils.isBlank(user.getPassword()))
        {
            return badRequest(new MissingRequiredFieldException(
                    "Missing email or password in register request json payload!").toString());

        }*/

        // create the register request in database
        UserDao ud = null;
        // validate the login request in database
        try
        {

            ud = new UserDao();
            user = ud.createUser(user);

        }
        catch (ConflictException e)
        {
            return badRequest(e.toString());
        }

        catch (DatabaseAccessException e)
        {
            Logger.error(e.getMessage());
            return badRequest(e.toString());
        }

        registerResponse.setEmail(user.getEmail());
        registerResponse.setNickname(user.getNickname());
        registerResponse.setUserId(user.getUserId());
        registerResponse.setGender(user.getGender());
        registerResponse.setPhone(user.getPhone());
        registerResponse.setLastname(user.getLastname());
        registerResponse.setFirstname(user.getFirstname());
        //EasyMailUtil.sendWelcomeEmail(user.getEmail());

        SessionTokenDAO stDao = new SessionTokenDAO();
        SessionTokenEntity sessionToken = new SessionTokenEntity();
        sessionToken.setValue(UUID.randomUUID().toString());
        sessionToken.setUserId(user.getUserId());
        Logger.info("Session Auth token for register: " + sessionToken.getValue().toString());
        stDao.createSesstionToken(sessionToken);

        registerResponse.setAuthToken(sessionToken.getValue());
        LRUAuthToken.getInstance().put(sessionToken.getValue(), user.getUserId());
        return created(Json.toJson(registerResponse));
    }
}