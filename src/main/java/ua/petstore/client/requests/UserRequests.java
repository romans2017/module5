package ua.petstore.client.requests;

import ua.petstore.client.models.User;
import ua.petstore.client.models.Users;

import java.io.IOException;

public class UserRequests implements Request {

    private static UserRequests instance;
    private static final String URL_createUser = "/user";
    private static final String URL_createWithList = "/user/createWithList";
    private static final String URL_createWithArray = "/user/createWithArray";
    private static final String URL_getByUsername = "/user/{username}";
    private static final String URL_updateUser = "/user/{username}";
    private static final String URL_deleteUser = "/user/{username}";
    private static final String URL_login = "/user/login";
    private static final String URL_logout = "/user/logout";

    public static UserRequests getInstance() {
        if (instance == null) {
            instance = new UserRequests();
        }
        return instance;
    }

    public String createUser(User user) throws IOException {
        return sendPost(user, HttpService.HOST + URL_createUser);
    }

    public String createWithList(Users users) throws IOException {
        return sendPost(users, HttpService.HOST + URL_createWithList);
    }

    public String createWithArray(Users users) throws IOException {
        return sendPost(users, HttpService.HOST + URL_createWithArray);
    }

    public String updateUser(User user) throws IOException {
        return sendPut(user, HttpService.HOST + URL_updateUser.replaceAll("\\{username}", user.getUsername()));
    }

    public String getByUsername(User user) throws IOException {
        return sendGet(HttpService.HOST + URL_getByUsername.replaceAll("\\{username}", user.getUsername()));
    }

    public String deleteByUsername(User user) throws IOException {
        return sendDelete(HttpService.HOST + URL_deleteUser.replaceAll("\\{username}", user.getUsername()));
    }

    public String login(User user) throws IOException {
        return sendGet(HttpService.HOST + URL_login + "?username=" + user.getUsername() + "&password=" + user.getPassword());
    }

    public String logout() throws IOException {
        return sendGet(HttpService.HOST + URL_logout);
    }

}