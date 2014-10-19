	
package controllers;

import java.security.InvalidParameterException;
import java.sql.SQLException;
import java.util.Date;
import java.util.UUID;

import domain.UserEntity;
import requests.LoginRequest;
import requests.LoginResponse;

import org.apache.commons.lang3.StringUtils;

import play.Logger;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;

import com.fasterxml.jackson.databind.JsonNode;

import exceptions.ConflictException;


import daos.UserDao;



public class UserServiceREST extends Controller
{

    @BodyParser.Of(BodyParser.Json.class)
    @Transactional
    public static Result login() throws SQLException
    {
        JsonNode json = null;
        LoginRequest loginRequest = null;
        LoginResponse loginResponse = new LoginResponse();

        // cast json payload to object
        try
        {
            json = request().body().asJson();
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

            return internalServerError("Database access error!");
        }

        if (user == null)
        {
            return unauthorized("loginAccount or password is invalid, please check it!");
        }
        loginResponse.setEmail(user.getEmail());
        loginResponse.setNickname(user.getNickname());
        loginResponse.setUserId(user.getUserId());
        loginResponse.setGender(user.getGender());
        loginResponse.setPhone(user.getPhone());
        loginResponse.setLastname(user.getLastname());
        loginResponse.setFirstname(user.getFirstname());
        return ok(Json.toJson(loginResponse));
    }
}