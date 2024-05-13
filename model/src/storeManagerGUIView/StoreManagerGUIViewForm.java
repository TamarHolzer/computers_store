package storeManagerGUIView;

import controller.Backend_DAO_List;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StoreManagerGUIViewForm {
    Backend_DAO_List bdl = Backend_DAO_List.get();
    private JButton AddCustomer;
    private JButton products;
    private JButton makeAnOrder;
    private JButton ViewOrders;
    private JPanel panel;

//מחני חלילי
    public StoreManagerGUIViewForm(){
        AddCustomer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //תמר הולצר
                //AddNewCustomerForm customerForm = new AddNewCustomerForm();

                 AddNewCustomerForm form = null;
                try {
                    form = new AddNewCustomerForm();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                form.setVisible(true);
            }
        });
        products.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("ManageCatalogForm");
                frame.setContentPane(new ManageCatalogForm().panel);
                frame.pack();
                frame.setVisible(true);
            }
        });
        makeAnOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("PlaceOrderForm");
                frame.setContentPane(new PlaceOrderForm().panel);
                frame.setPreferredSize(new Dimension(800, 800));
                frame.pack();
                frame.setVisible(true);
            }
        });
        ViewOrders.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("ViewPurchasesForm");
                frame.setContentPane(new ViewPurchasesForm().panel);
                frame.pack();
                frame.setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("חנות מחשבים");
        frame.setContentPane(new StoreManagerGUIViewForm().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(1000, 1000));
        frame.pack();
        frame.setVisible(true);
    }






//מתמר הולצר

    /*public StoreManagerGUIViewForm() {
        AddCustomer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddNewCustomerForm customerForm = new AddNewCustomerForm();

            }
        });


    }*/

    /*public static void main(String[] args) {
        JFrame frame = new JFrame("StoreManagerGUIViewForm");
        frame.setContentPane(new StoreManagerGUIViewForm().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(300,300);

    }*/
}
