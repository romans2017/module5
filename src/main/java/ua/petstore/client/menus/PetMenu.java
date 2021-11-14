package ua.petstore.client.menus;

import com.google.gson.JsonSyntaxException;
import ua.petstore.client.console.InputConsole;
import ua.petstore.client.models.Pet;
import ua.petstore.client.requests.HttpService;
import ua.petstore.client.requests.PetRequests;

import java.io.IOException;

public class PetMenu extends AbstractMenu {

    private static PetMenu instance;

    public static PetMenu getInstance() {
        if (instance == null) {
            instance = new PetMenu();
        }
        return instance;
    }

    private PetMenu() {

        menuModel = new MenuModel();
        menuModel.put("add", param -> {
            init(param);
            try {
                Pet pet = HttpService.getInstance().getGson().fromJson(values, Pet.class);
                System.out.println(PetRequests.getInstance().newPet(pet));
            } catch (IOException | JsonSyntaxException e) {
                e.printStackTrace();
            }
        });
        menuModel.put("update", param -> {
            init(param);
            try {
                Pet pet = HttpService.getInstance().getGson().fromJson(values, Pet.class);
                System.out.println(PetRequests.getInstance().updatePet(pet));
            } catch (IOException | JsonSyntaxException e) {
                e.printStackTrace();
            }
        });
        menuModel.put("updateform", param -> {
            init(param);
            try {
                Pet pet = HttpService.getInstance().getGson().fromJson(values, Pet.class);
                System.out.println(PetRequests.getInstance().updatePetWithForm(pet));
            } catch (IOException | JsonSyntaxException e) {
                e.printStackTrace();
            }
        });
        menuModel.put("upload", param -> {
            init(param);
            String[] params = values.split("--", -1);

            try {
                Pet pet = Pet.builder().withId(Integer.parseInt(params[1].trim())).build();
                System.out.println(PetRequests.getInstance().uploadImage(pet, params[2], params[3]));
            } catch (IOException | JsonSyntaxException | NumberFormatException | ArrayIndexOutOfBoundsException e) {
                e.printStackTrace();
            }
        });
        menuModel.put("findbystatus", param -> {
            init(param);
            String[] params = values.split("--", -1);

            try {
                System.out.println(PetRequests.getInstance().findByStatus(params[1].split(",")));
            } catch (IOException | JsonSyntaxException | NumberFormatException | ArrayIndexOutOfBoundsException e) {
                e.printStackTrace();
            }
        });
        menuModel.put("findbyid", param -> {
            init(param);
            String[] params = values.split("--", -1);

            try {
                Pet pet = Pet.builder().withId(Integer.parseInt(params[1].trim())).build();
                System.out.println(PetRequests.getInstance().findById(pet));
            } catch (IOException | JsonSyntaxException | NumberFormatException | ArrayIndexOutOfBoundsException e) {
                e.printStackTrace();
            }
        });
        menuModel.put("delete", param -> {
            init(param);
            String[] params = values.split("--", -1);

            try {
                Pet pet = Pet.builder().withId(Integer.parseInt(params[1].trim())).build();
                System.out.println(PetRequests.getInstance().deleteById(pet));
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
                
                ******** PET MENU ********             
                1.Add a new pet to the store (command 'add {
                                                                "id": 0,
                                                                "category": {
                                                                  "id": 0,
                                                                  "name": "string"
                                                                },
                                                                "name": "doggie",
                                                                "photoUrls": [
                                                                  "string"
                                                                ],
                                                                "tags": [
                                                                  {
                                                                    "id": 0,
                                                                    "name": "string"
                                                                  }
                                                                ],
                                                                "status": "available"
                                                              }') 
                2.Update a pet (command 'update {
                                                     "id": 0,
                                                     "category": {
                                                       "id": 0,
                                                       "name": "string"
                                                     },
                                                     "name": "doggie",
                                                     "photoUrls": [
                                                       "string"
                                                     ],
                                                     "tags": [
                                                       {
                                                         "id": 0,
                                                         "name": "string"
                                                       }
                                                     ],
                                                     "status": "available"
                                                   }')
                3.Update a pet from form (command 'updateForm {
                                                     "id": 0,
                                                     "name": "doggie",
                                                     "status": "available"
                                                   }')                                   
                4.Upload image (command 'upload --pet_id --additionalMetadata --filepath')                                  
                5.Finds pets by status (command 'findByStatus --status('available' or/and 'pending' or/and 'sold')')
                6.Finds pets by ID (command 'findById --pet_id')
                7.Deletes a pet (command 'delete --pet_id')
                UP... (command 'up')
                EXIT... (command 'exit')
                ******** ********
                """;
        System.out.println(textMenu);
    }
}
