package ua.petstore.client.requests;

import ua.petstore.client.models.Order;

import java.io.IOException;

public class StoreRequests implements Request {

    private static StoreRequests instance;
    private static final String URL_getInventories = "/store/inventory";
    private static final String URL_order = "/store/order";
    private static final String URL_findById = "/store/order/{orderId}";
    private static final String URL_deleteOrder = "/store/order/{orderId}";

    public static StoreRequests getInstance() {
        if (instance == null) {
            instance = new StoreRequests();
        }
        return instance;
    }

    public String newOrder(Order order) throws IOException {
        return sendPost(order, HttpService.HOST + URL_order);
    }

    public String findById(Order order) throws IOException {
        return sendGet(HttpService.HOST + URL_findById.replaceAll("\\{orderId}", order.getId().toString()));
    }

    public String deleteById(Order order) throws IOException {
        return sendDelete(HttpService.HOST + URL_deleteOrder.replaceAll("\\{orderId}", order.getId().toString()));
    }

    public String getInventories() throws IOException {
        return sendGet(HttpService.HOST + URL_getInventories);
    }

}