package controller;

import model.Customer;
import model.Product;
import model.PurchaseOrder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public interface Backend {
    void AddCustomer(Customer c) throws Exception;

    void AddProduct(Product p) throws Exception;

    HashMap<Long, Customer> getAllCustomer() throws Exception;

    HashSet<Product> getAllProduct() throws Exception;

    void PlaceOrder(PurchaseOrder po) throws Exception;

    void RemoveProduct(Product p) throws Exception;

    ArrayList<Product> getCustomersOrders(Customer c) throws Exception;

    double CalcProductsTotalCost(Product[] products) throws Exception;
}
