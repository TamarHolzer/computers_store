package storeManagerGUIView;

import controller.Backend_DAO_List;
import model.Customer;
import model.Product;
import model.PurchaseOrder;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;


public class PlaceOrderForm {
    Backend_DAO_List bdl = Backend_DAO_List.get();
    private JComboBox CustomersList;
    private JComboBox ProductList;
    private JButton AddToOrderBTN;
    private JList ListProductSelected;
    private JButton FinishOrderBTN;
    private JButton CalculateOrderBTN;
    private JLabel SumOrder;
    public JPanel panel;
    private JButton DeleteProductBTN;

    public PlaceOrderForm(){
        DefaultListModel SelectedProductsListModel = new DefaultListModel();
        ListProductSelected.setModel(SelectedProductsListModel);
        try {
            //איתחול רשימת הלקוחות
            DefaultComboBoxModel CustomerModel=new DefaultComboBoxModel();
            CustomerModel.addAll(bdl.getAllCustomer().values());
            CustomersList.setModel(CustomerModel);

            //איתחול רשימת המוצרים
            DefaultComboBoxModel ProductModel=new DefaultComboBoxModel();
            ProductModel.addAll(bdl.getAllProduct());
            ProductList.setModel(ProductModel);
        }catch (Exception ex){
            Logger.getLogger(PlaceOrderForm.class.getName()).log(Level.SEVERE,null, ex);
        }

        AddToOrderBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (CustomersList.getSelectedIndex()>=0){
                    SelectedProductsListModel.addElement((Product)ProductList.getSelectedItem());
                }
                else {
                    JOptionPane.showMessageDialog(panel,"חובה לבחור לקוח!");
                }
            }
        });
        DeleteProductBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (Object objProduct : ListProductSelected.getSelectedValues()) {
                    SelectedProductsListModel.removeElement(objProduct);
                }
                /*java.util.List<Product> selectedValuesList = ListProductSelected.getSelectedValuesList();
                for (Product p :selectedValuesList) {
                    SelectedProductsListModel.removeElement(p);
                }*/
            }
        });
        FinishOrderBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (ProductList.getSelectedIndex()>=0){
                    try{
                        PurchaseOrder NewOrder=  new PurchaseOrder();
                        NewOrder.setProductsList(new ArrayList(Arrays.asList(SelectedProductsListModel.toArray())));
                        NewOrder.setOrderingCustomer((Customer) CustomersList.getSelectedItem());
                        bdl.PlaceOrder(NewOrder);
                        JOptionPane.showMessageDialog(panel, "ההזמנה נוספה בהצלחה!");
                    }catch (Exception ex){
                        JOptionPane.showMessageDialog(null, "שגיאה!-ההזמנה לא נוספה!", "Error",JOptionPane.ERROR_MESSAGE);
                        Logger.getLogger(PlaceOrderForm.class.getName()).log(Level.SEVERE,null, ex);
                    }
                }
                else {
                    JOptionPane.showMessageDialog(panel,"חובה לבחור מוצר!");
                }

            }
        });
        CalculateOrderBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Product[] products = new Product[SelectedProductsListModel.size()];
                    SelectedProductsListModel.copyInto(products);
                    Float total = (float) bdl.CalcProductsTotalCost(products);
                    SumOrder.setText(total.toString());
                } catch (Exception ex) {
                    Logger.getLogger(PlaceOrderForm.class.getName()).log(Level.SEVERE,null, ex);
                }

            }
        });

    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("הזמנה חדשה");
        frame.setContentPane(new PlaceOrderForm().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
