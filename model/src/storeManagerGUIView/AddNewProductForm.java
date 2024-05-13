package storeManagerGUIView;
import controller.Backend_DAO_List;

import model.HardwareProduct;
import model.Product;
import model.ProductType;
import model.SoftwareProduct;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class AddNewProductForm extends Component {
    Backend_DAO_List bdl = Backend_DAO_List.get();
    private JTextField NameProductT;
    private JTextField DescriptionT;
    private JTextField PriceProductT;
    private JTextField barkodT;
    private JComboBox comboBox;
    private JButton AddBtn;
    private JLabel NameProduct;
    public JPanel panel;
    public ManageCatalogForm manageCatalogForm;
    private JLabel Description;
    private JLabel PriceProduct;
    private JLabel barkod;


    public AddNewProductForm(ManageCatalogForm m){
        DefaultComboBoxModel model = new DefaultComboBoxModel(ProductType.values());
        comboBox.setModel(model);
        comboBox.setSelectedIndex(0);
        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            barkod.setText(typeCombo(comboBox.getSelectedIndex()));
            }
        });
        barkodT.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (!Character.isDigit(e.getKeyChar()) || barkodT.getText().length()>=5)
                    e.consume();
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        PriceProductT.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (!Character.isDigit(e.getKeyChar()) )
                    e.consume();
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        //בלחיצה על הוסף
        AddBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try{
                    Product product;
                    if (NameProductT.getText().isBlank())
                        throw new Exception("חובה להכניס שם מוצר");

                    if(isInHardwareMode()){
                        product= new HardwareProduct(NameProductT.getText(), DescriptionT.getText(),Float.parseFloat(PriceProductT.getText()),Integer.parseInt(barkodT.getText()));

                    }
                    else {
                        product= new SoftwareProduct(NameProductT.getText(), DescriptionT.getText(),Float.parseFloat(PriceProductT.getText()),Integer.parseInt(barkodT.getText()));

                    }
                    //todo
                    bdl.AddProduct(product);
                    m.RefreshProductList();
                    JOptionPane.showMessageDialog(panel,"המוצר התווסף בהצלחה");
                    System.out.println(product);

                }catch (Exception ex){
                    JOptionPane.showMessageDialog(panel,ex.getMessage());
                }



            }
        });

    }
    //מה זה אומר הדבר הזה?⬇️
    private String typeCombo(int i) {
        return i==0?"מספר משתמשים":"שנות אחריות";
    }
    private boolean isInHardwareMode() {
        return comboBox.getSelectedItem().equals(ProductType.HARDWARE)?true:false;
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("הוספת מוצר חדש");
        frame.setContentPane(new AddNewProductForm(null).panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(500, 500));
        frame.pack();
        frame.setVisible(true);
    }
}
