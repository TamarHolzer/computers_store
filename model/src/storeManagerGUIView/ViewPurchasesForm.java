package storeManagerGUIView;

import controller.Backend_DAO_List;
import model.Customer;
import model.Product;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewPurchasesForm {
    Backend_DAO_List bdl = Backend_DAO_List.get();
    public JPanel panel;
    private JComboBox OrderList;
    private JList ProductInOrder;
    private JLabel PriceText;
    private JLabel Price;
    public ViewPurchasesForm(){
        DefaultListModel SelectedProductsListModel = new DefaultListModel();
        try {
            DefaultComboBoxModel modelCustomer = new DefaultComboBoxModel();
            modelCustomer.addAll(bdl.getAllCustomer().values());
            OrderList.setModel(modelCustomer);
        }
        catch (Exception e){

        }
        OrderList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    SelectedProductsListModel.addAll(bdl.getCustomersOrders((Customer) OrderList.getSelectedItem()));
                }
                catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                ProductInOrder.setModel(SelectedProductsListModel);
                try {
                    Product[] products = new Product[SelectedProductsListModel.size()];
                    SelectedProductsListModel.copyInto(products);
                    Float total = (float) bdl.CalcProductsTotalCost(products);
                    PriceText.setText(total.toString());
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("הזמנות");
        frame.setContentPane(new ViewPurchasesForm().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
