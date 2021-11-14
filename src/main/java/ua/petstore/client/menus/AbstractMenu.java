package ua.petstore.client.menus;

abstract class AbstractMenu implements Menu {
    protected MenuModel menuModel;
    protected Menu previousMenu;

    protected String values;

    @Override
    public ua.petstore.client.menus.MenuModel getMenuModel() {
        return menuModel;
    }

    protected Menu getPreviousMenu() {
        return previousMenu;
    }

    protected void setPreviousMenu(Menu previousMenu) {
        this.previousMenu = previousMenu;
    }

    protected void init(String param) {
        param = param.trim();
        values = "";
        if (param.contains(" ")) {
            values = param.substring(param.indexOf(" ") + 1).trim();
        }
    }
}
