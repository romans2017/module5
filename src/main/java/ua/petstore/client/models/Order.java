package ua.petstore.client.models;

import java.util.Date;

public class Order implements ApiModel {

    private Integer id;
    private Integer petId;
    private Integer quantity;
    private Date shipDate;
    private String status;
    private Boolean complete;

    public Integer getId() {
        return id;
    }

    public static Order.Builder builder() {
        return new Order.Builder();
    }

    public static class Builder {
        private final Order newOrder;

        private Builder() {
            newOrder = new Order();
        }

        public Order.Builder withId(Integer id) {
            newOrder.id = id;
            return this;
        }

        public Order.Builder withPetId(Integer petId) {
            newOrder.petId = petId;
            return this;
        }

        public Order.Builder withQuantity(Integer quantity) {
            newOrder.quantity = quantity;
            return this;
        }

        public Order.Builder withShipDate(Date shipDate) {
            newOrder.shipDate = shipDate;
            return this;
        }

        public Order.Builder withStatus(String status) {
            newOrder.status = status;
            return this;
        }

        public Order.Builder withComplete(Boolean complete) {
            newOrder.complete = complete;
            return this;
        }

        public Order build() {
            return newOrder;
        }
    }

}
