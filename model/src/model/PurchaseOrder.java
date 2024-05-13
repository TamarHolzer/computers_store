package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class PurchaseOrder {
    private Customer orderingCustomer;
    private ArrayList <Product> productsList;
    private LocalDate orderDate;

    public PurchaseOrder(Customer orderingCustomer, ArrayList<Product> productsList, LocalDate orderDate) {
        this.orderingCustomer = orderingCustomer;
        this.productsList = productsList;
        this.orderDate = orderDate;
    }

    public PurchaseOrder() {

    }

    public Customer getOrderingCustomer() {
        return orderingCustomer;
    }

    public void setOrderingCustomer(Customer orderingCustomer) {
        this.orderingCustomer = orderingCustomer;
    }

    public ArrayList<Product> getProductsList() {
        return productsList;
    }

    public void setProductsList(ArrayList<Product> productsList) {
        this.productsList = productsList;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    @Override
    public String toString() {
        return "PurchaseOrder{" +
                "orderingCustomer=" + orderingCustomer +
                ", productsList=" + productsList +
                ", orderDate=" + orderDate +
                '}';
    }
}
