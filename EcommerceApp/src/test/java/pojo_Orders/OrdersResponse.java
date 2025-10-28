package pojo_Orders;

import java.util.List;

public class OrdersResponse {
    private String message;
    private List<String> orders;
    private List<String> productOrderId;

    // no-arg constructor
    public OrdersResponse() {
    }

    // all-arg constructor
    public OrdersResponse(String message, List<String> orders, List<String> productOrderId) {
        this.message = message;
        this.orders = orders;
        this.productOrderId = productOrderId;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return the orders
     */
    public List<String> getOrders() {
        return orders;
    }

    /**
     * @param orders the orders to set
     */
    public void setOrders(List<String> orders) {
        this.orders = orders;
    }

    /**
     * @return the productOrderId
     */
    public List<String> getProductOrderId() {
        return productOrderId;
    }

    /**
     * @param productOrderId the productOrderId to set
     */
    public void setProductOrderId(List<String> productOrderId) {
        this.productOrderId = productOrderId;
    }

    @Override
    public String toString() {
        return "OrdersResponse [message=" + message + ", orders=" + orders + ", productOrderId=" + productOrderId + "]";
    }
}