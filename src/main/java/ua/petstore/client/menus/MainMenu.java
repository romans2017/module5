package ua.petstore.client.menus;

import ua.petstore.client.console.InputConsole;

public class MainMenu extends AbstractMenu {

    private static MainMenu instance;

    public static MainMenu getInstance() {
        if (instance == null) {
            instance = new MainMenu();
        }
        return instance;
    }

    private MainMenu() {
        menuModel = new MenuModel();
        menuModel.put("pet", param -> {
            PetMenu menu = PetMenu.getInstance();
            InputConsole.getInstance().setCurrentMenu(menu);
            menu.setPreviousMenu(this);
            menu.showMenu();
        });
        menuModel.put("store", param -> {
            StoreMenu menu = StoreMenu.getInstance();
            InputConsole.getInstance().setCurrentMenu(menu);
            menu.setPreviousMenu(this);
            menu.showMenu();
        });
        menuModel.put("user", param -> {
            UserMenu menu = UserMenu.getInstance();
            InputConsole.getInstance().setCurrentMenu(menu);
            menu.setPreviousMenu(this);
            menu.showMenu();
        });
        menuModel.put("exit", param -> System.exit(0));
    }

    @Override
    public void showMenu() {
        String textMenu = """
                
                ******** MAIN MENU ********            
                1.Pet-> (command 'pet') 
                2.Store-> (command 'store')
                3.User-> (command 'user')
                EXIT... (command 'exit')
                ******** ********
                """;
        System.out.println(textMenu);
    }
}
