package storeManagerGUIView;

import model.Customer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Calendar;
import javax.swing.JFrame;
import controller.Backend_DAO_List;


public class AddNewCustomerForm extends JFrame {
    Backend_DAO_List bdl = Backend_DAO_List.get();
    DefaultListModel   AllCustomerListModel;
    private JButton jButtonOK;
    private JLabel  jLabelID;
    private JLabel  jLabelName;
    private JLabel  jLabelAddress;
    private JTextField    jTextFieldID;
    private JTextField   jTextFieldName;
    private JTextField   jTextFieldAddress;
    private JButton jButtonSeelist;
    private JList<Customer> jlitcust;

    public AddNewCustomerForm() throws Exception {
        jButtonOK = new JButton("OK");
        jLabelID = new JLabel("ID:");
        jLabelName = new JLabel("Name:");
        jLabelAddress = new JLabel("Address:");
        jTextFieldID = new JTextField();
        jTextFieldName = new JTextField();
        jTextFieldAddress = new JTextField();
        //כדי לראות את רשימת הלקוחות
        jButtonSeelist = new JButton("seeList");
        jlitcust = new JList();


        getContentPane().add(jLabelID);
        getContentPane().add(jTextFieldID);
        getContentPane().add(jLabelName);
        getContentPane().add(jTextFieldName);
        getContentPane().add(jLabelAddress);
        getContentPane().add(jTextFieldAddress);
        getContentPane().add(jButtonOK);
        getContentPane().add(jButtonSeelist);
        getContentPane().add(jlitcust);

        this.setPreferredSize(new Dimension(500, 300));
        getContentPane().setLayout(new GridLayout(0,1,10,10));
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.pack();
        AllCustomerListModel=new DefaultListModel<>();
        jlitcust.setModel(AllCustomerListModel);
        RefreshCustomerList();
        jButtonOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    long id = Long.parseLong(jTextFieldID.getText().trim());
                    if (jTextFieldName.getText().isBlank())
                        throw new Exception("חובה להכניס שם");
                    if (bdl.getAllCustomer().containsKey(id))
                        throw new Exception("מספר זהות קיים במערכת");
                    Customer c = new Customer();
                    //Integer.parseInt(jTextFieldID.getText()), jTextFieldName.getText(), jTextFieldAddress.getText());
                    c.setId(id);
                    c.setName(jTextFieldName.getText());
                    c.setAddress(jTextFieldAddress.getText());
                    bdl.AddCustomer(c);
                    JOptionPane.showMessageDialog(AddNewCustomerForm.this, "הלקוח התווסף בהצלחה");
                    System.out.println(bdl.getAllCustomer());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(AddNewCustomerForm.this,"שגיאה! הלקוח לא התווסף");


                }
            }
        });
        jButtonSeelist.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RefreshCustomerList();
            }
        });
        jTextFieldID.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

                if (!Character.isDigit(e.getKeyChar()) || jTextFieldID.getText().length()>=9)
                    e.consume();

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }


    public void RefreshCustomerList() {
        try {
            AllCustomerListModel.clear();
            AllCustomerListModel.addAll(bdl.getAllCustomer().values());
        } catch (Exception ex) {
            System.out.println("שגיאה");
        }
    }
    public static void main(String[] args) throws Exception {
        AddNewCustomerForm form = new AddNewCustomerForm();
        form.setVisible(true);
    }

}
