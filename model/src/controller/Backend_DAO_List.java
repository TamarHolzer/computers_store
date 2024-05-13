package controller;

import model.Customer;
import model.Product;
import model.PurchaseOrder;

import java.util.*;
import java.util.stream.Collectors;

public class Backend_DAO_List implements Backend{
    private HashMap<Long, Customer> Customers;
    private HashSet<Product> Products;
    private List<PurchaseOrder> PurchaseOrders;
    private static Backend_DAO_List b = null;
    private Backend_DAO_List() {
        Products = new HashSet<>();
        Products.add(new Product("shoko","Beverage",3.5f) {
            @Override
            public float getPrice() {
                return 0;
            }
        });
        Products.add(new Product("cookies","pastry",6.7f) {
            @Override
            public float getPrice() {
                return 0;
            }
        });
        PurchaseOrders = new ArrayList<>();
        Customers=new HashMap<>();
    }
    public static  Backend_DAO_List get()
    {
        if(b == null)
        {
            b = new Backend_DAO_List();
            return b;
        }
        return b;
    }
    @Override
    public void AddCustomer(Customer c) throws Exception {
        Customers.put(c.getId(),c);
    }

    @Override
    public void AddProduct(Product p) throws Exception {
        Products.add(p);
    }

    @Override
    public HashMap<Long, Customer> getAllCustomer() throws Exception {
        return Customers;
    }

    @Override
    public HashSet<Product> getAllProduct() throws Exception {
        return Products;
    }

    @Override
    public void PlaceOrder(PurchaseOrder po) throws Exception {
        PurchaseOrders.add(po);
    }

    @Override
    public void RemoveProduct(Product p) throws Exception {
        Products.remove(p);
    }

    @Override
    public ArrayList<Product> getCustomersOrders(Customer c) throws Exception {
        ArrayList<Product> ret = new ArrayList<Product>();
        Iterator<PurchaseOrder> itr=PurchaseOrders.iterator();
        while (itr.hasNext())
        {
            PurchaseOrder i = itr.next();
            if(i.getOrderingCustomer().equals(c))
                ret=i.getProductsList();
        }
        return ret;
    }

    @Override
    public double CalcProductsTotalCost(Product[] products) throws Exception {
        return Arrays.stream(products).collect((Collectors.summarizingDouble(p->p.getPrice()))).getSum();
    }
}

