package ua.petstore.client.console;

import ua.petstore.client.menus.MainMenu;
import ua.petstore.client.menus.Menu;

import java.util.Locale;
import java.util.function.Consumer;

public class InputConsole {

    private Menu currentMenu;

    private static InputConsole instance;

    public static InputConsole getInstance() {
        if (instance == null) {
            instance = new InputConsole();
        }
        return instance;
    }

    private InputConsole() {
        currentMenu = MainMenu.getInstance();
        currentMenu.showMenu();
    }

    public void processInput(String inputString) {
        //split to array with delimiter space
        String[] params = inputString.trim().toLowerCase(Locale.ROOT).split("\s+");

        //get first word. Consider that this is command
        String command = params[0];

        //get and execute command. User's menu is defined in the handler
        Consumer<String> currentCommand = currentMenu.getMenuModel().get(command);
        if (currentCommand != null) {
            currentCommand.accept(inputString);
        }
    }

    public void setCurrentMenu(Menu menu) {
        currentMenu = menu;
    }
}
