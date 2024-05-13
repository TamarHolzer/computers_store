package storeManagerGUIView;

import controller.Backend_DAO_List;
import model.Product;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ManageCatalogForm {
    Backend_DAO_List bdl = Backend_DAO_List.get();

    private JList ListProduct;
    private JButton AddNewProductBtn;
    private JButton DeleteProductBtn;
    public JPanel panel;
    DefaultListModel<Product> AllProductsListModel;
    //רענון רשימת מוצרים
    //מציג בצורה משונה--------------------------------------------------------------------------------------
    public void RefreshProductList() {
        try {
            AllProductsListModel.clear();
            AllProductsListModel.addAll(bdl.getAllProduct());
        }
        catch (Exception ex) {
            System.out.println("שגיאה-מוצרים לא התרעננו!");
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("מוצרים");
        frame.setContentPane(new ManageCatalogForm().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
    private void p() {
        JFrame frame = new JFrame("AddNewProductForm");
        frame.setContentPane(new AddNewProductForm(this).panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public ManageCatalogForm()
    {
        AllProductsListModel=new DefaultListModel<Product>();
        ListProduct.setModel(AllProductsListModel);
        RefreshProductList();
        AddNewProductBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    p();
            }
        });
        //פונקציה שבעת לחיצה על כפתור המחיקה היא מוחקת מוצר
        DeleteProductBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                java.util. List<Product> selectedValuesList = ListProduct.getSelectedValuesList();
                for(Product p:selectedValuesList)
                {
                    AllProductsListModel.removeElement(p);
                    try {
                        Backend_DAO_List.get().RemoveProduct(p);
                    }
                    catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });

        AddNewProductBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("הוספת מוצר חדש");
                frame.setContentPane(new AddNewProductForm(ManageCatalogForm.this).panel);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);

            }
        });
    }
}
