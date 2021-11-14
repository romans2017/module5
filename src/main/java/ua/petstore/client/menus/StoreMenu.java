package ua.petstore.client.menus;

import com.google.gson.JsonSyntaxException;
import ua.petstore.client.console.InputConsole;
import ua.petstore.client.models.Order;
import ua.petstore.client.requests.HttpService;
import ua.petstore.client.requests.StoreRequests;

import java.io.IOException;

public class StoreMenu extends AbstractMenu {

    private static StoreMenu instance;

    public static StoreMenu getInstance() {
        if (instance == null) {
            instance = new StoreMenu();
        }
        return instance;
    }

    private StoreMenu() {

        menuModel = new MenuModel();
        menuModel.put("place", param -> {
            init(param);
            try {
                Order order = HttpService.getInstance().getGson().fromJson(values, Order.class);
                System.out.println(StoreRequests.getInstance().newOrder(order));
            } catch (IOException | JsonSyntaxException e) {
                e.printStackTrace();
            }
        });
        menuModel.put("findbyid", param -> {
            init(param);
            String[] params = values.split("--", -1);

            try {
                Order order = Order.builder().withId(Integer.parseInt(params[1].trim())).build();
                System.out.println(StoreRequests.getInstance().findById(order));
            } catch (IOException | JsonSyntaxException | NumberFormatException | ArrayIndexOutOfBoundsException e) {
                e.printStackTrace();
            }
        });
        menuModel.put("delete", param -> {
            init(param);
            String[] params = values.split("--", -1);

            try {
                Order order = Order.builder().withId(Integer.parseInt(params[1].trim())).build();
                System.out.println(StoreRequests.getInstance().deleteById(order));
            } catch (IOException | JsonSyntaxException | NumberFormatException | ArrayIndexOutOfBoundsException e) {
                e.printStackTrace();
            }
        });
        menuModel.put("inventory", param -> {
            try {
                System.out.println(StoreRequests.getInstance().getInventories());
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
                
                ******** STORE MENU ********            
                1.Place an order (command 'place {
                                                  "id": 0,
                                                  "petId": 0,
                                                  "quantity": 0,
                                                  "shipDate": "2021-11-14T18:00:37.903Z",
                                                  "status": "placed",
                                                  "complete": true
                                                  }') 
                2.Returns a map of status codes to quantities (command 'inventory')
                3.Finds purchase order by ID (command 'findById --order_id')
                4.Delete purchase order by ID (command 'delete --order_id')
                UP... (command 'up')
                EXIT... (command 'exit')
                ******** ********
                """;
        System.out.println(textMenu);
    }
}
