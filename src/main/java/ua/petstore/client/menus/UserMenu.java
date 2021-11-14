package ua.petstore.client.menus;

import com.google.gson.JsonSyntaxException;
import ua.petstore.client.console.InputConsole;
import ua.petstore.client.models.User;
import ua.petstore.client.models.Users;
import ua.petstore.client.requests.HttpService;
import ua.petstore.client.requests.UserRequests;

import java.io.IOException;

public class UserMenu extends AbstractMenu {

    private static UserMenu instance;

    public static UserMenu getInstance() {
        if (instance == null) {
            instance = new UserMenu();
        }
        return instance;
    }

    private UserMenu() {

        menuModel = new MenuModel();
        menuModel.put("login", param -> {
            init(param);
            try {
                User user = HttpService.getInstance().getGson().fromJson(values, User.class);
                System.out.println(UserRequests.getInstance().login(user));
            } catch (IOException | JsonSyntaxException e) {
                e.printStackTrace();
            }
        });
        menuModel.put("create", param -> {
            init(param);
            try {
                User user = HttpService.getInstance().getGson().fromJson(values, User.class);
                System.out.println(UserRequests.getInstance().createUser(user));
            } catch (IOException | JsonSyntaxException e) {
                e.printStackTrace();
            }
        });
        menuModel.put("createlist", param -> {
            init(param);
            try {
                Users users = HttpService.getInstance().getGson().fromJson(values, Users.class);
                System.out.println(UserRequests.getInstance().createWithList(users));
            } catch (IOException | JsonSyntaxException e) {
                e.printStackTrace();
            }
        });
        menuModel.put("createarray", param -> {
            init(param);
            try {
                Users users = HttpService.getInstance().getGson().fromJson(values, Users.class);
                System.out.println(UserRequests.getInstance().createWithArray(users));
            } catch (IOException | JsonSyntaxException e) {
                e.printStackTrace();
            }
        });
        menuModel.put("update", param -> {
            init(param);
            try {
                User user = HttpService.getInstance().getGson().fromJson(values, User.class);
                System.out.println(UserRequests.getInstance().updateUser(user));
            } catch (IOException | JsonSyntaxException e) {
                e.printStackTrace();
            }
        });
        menuModel.put("getbyusername", param -> {
            init(param);
            String[] params = values.split("--", -1);

            try {
                User user = User.builder().withUserName(params[1].trim()).build();
                System.out.println(UserRequests.getInstance().getByUsername(user));
            } catch (IOException | JsonSyntaxException | NumberFormatException | ArrayIndexOutOfBoundsException e) {
                e.printStackTrace();
            }
        });
        menuModel.put("logout", param -> {
            try {
                System.out.println(UserRequests.getInstance().logout());
            } catch (IOException | JsonSyntaxException | NumberFormatException | ArrayIndexOutOfBoundsException e) {
                e.printStackTrace();
            }
        });
        menuModel.put("delete", param -> {
            init(param);
            String[] params = values.split("--", -1);

            try {
                User user = User.builder().withUserName(params[1].trim()).build();
                System.out.println(UserRequests.getInstance().deleteByUsername(user));
            } catch (IOException | JsonSyntaxException | NumberFormatException | ArrayIndexOutOfBoundsException e) {
                e.printStackTrace();
            }
        });
        menuModel.put("up", param -> {
            Menu menu = getPreviousMenu();
            InputConsole.getInstance().setCurrentMenu(menu);
            menu.showMenu();
        });
        menuModel.put("exit", param -> System.exit(0));
    }

    @Override
    public void showMenu() {
        String textMenu = """
                
                ******** USER MENU ********
                1.Log in (command 'login {
                                            "username": "string",
                                            "password": "string"
                                          }')            
                2.Create user (command 'create {
                                                "id": 0,
                                                "username": "string",
                                                 "firstName": "string",
                                                 "lastName": "string",
                                                 "email": "string",
                                                 "password": "string",
                                                 "phone": "string",
                                                 "userStatus": 0
                                                 }') 
                3.Create user with list (command 'createList [
                                                                {
                                                                "id": 0,
                                                                "username": "string",
                                                                "firstName": "string",
                                                                "lastName": "string",
                                                                "email": "string",
                                                                "password": "string",
                                                                "phone": "string",
                                                                "userStatus": 0
                                                                }
                                                            ]')
                4.Create user with array (command 'createArray [
                                                                {
                                                                "id": 0,
                                                                "username": "string",
                                                                "firstName": "string",
                                                                "lastName": "string",
                                                                "email": "string",
                                                                "password": "string",
                                                                "phone": "string",
                                                                "userStatus": 0
                                                                }
                                                            ]')                                                            
                5.Update user (command 'update {
                                                 "id": 0,
                                                 "username": "string",
                                                 "firstName": "string",
                                                 "lastName": "string",
                                                 "email": "string",
                                                 "password": "string",
                                                 "phone": "string",
                                                 "userStatus": 0
                                                 }')                                  
                6.Get user by username (command 'getByUsername --username')
                7.Log out (command 'logout')
                8.Delete user (command 'delete --username')
                UP... (command 'up')
                EXIT... (command 'exit')
                ******** ********
                """;
        System.out.println(textMenu);
    }
}
