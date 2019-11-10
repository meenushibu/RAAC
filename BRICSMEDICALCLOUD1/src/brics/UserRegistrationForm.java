/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package brics;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 *
 * @author Anju
 */
public class UserRegistrationForm extends JDialog implements WindowListener , ActionListener{
    private Container container=getContentPane(); 
    private UserArea parent;
    private JLabel lbName=new JLabel("Full Name");
    private JLabel lbNo=new JLabel("Reg. No");
    private JLabel lbYear=new JLabel("Reg Year");
    private JLabel lbCouncil=new JLabel("Medical Council");
    private JLabel lbCountry=new JLabel("brics Country");
    private JLabel lbSpecialization=new JLabel("Specialization");
    
    
    
    private JLabel lblUserID=new JLabel("User ID");
    private JLabel lblPassword=new JLabel("Password");
    
    private JTextField txtName=new JTextField(20);
    private JTextField txtNo=new JTextField(20);
    private JTextField txtYear=new JTextField(20);
    private JTextField txtCouncil=new JTextField(20);
    private JComboBox cmbCountry =new JComboBox();
    private JComboBox cmbSpecialization =new JComboBox();
    
    private JTextField txtUserID=new JTextField(20);
    private JPasswordField txtPass=new JPasswordField(20);
    
    private JButton btnRegister=new JButton("Register Now");
    
    public UserRegistrationForm(UserArea parent,String title , boolean flag)
    {
       super(parent,title,flag);
       this.parent=parent;
       container.setLayout(null);
       container.setBackground(Color.WHITE);
       
       this.addWindowListener(this);
       this.setSize(500, 450);
       this.setResizable(false);
       final Toolkit toolkit = Toolkit.getDefaultToolkit();
                final Dimension screenSize = toolkit.getScreenSize();
                final int x = (screenSize.width - this.getWidth()) / 2;
                final int y = (screenSize.height - this.getHeight()) / 2;
                this.setLocation(x, y);
                
                ImageIcon imgThisImg = new ImageIcon("images/register.png");
		JLabel label = new JLabel(imgThisImg);
                label.setSize(100, 100);
                label.setLocation(0, 0);
                container.add(label);
                
                lbName.setSize(80, 30);
                lbName.setLocation(120,10);
                container.add(lbName);
                
                txtName.setSize(200, 30);
                txtName.setLocation(240,10);
                container.add(txtName);
                
                lbNo.setSize(80, 30);
                lbNo.setLocation(120,50);
                container.add(lbNo);
                
                txtNo.setSize(200, 30);
                txtNo.setLocation(240,50);
                container.add(txtNo);
                
                lbYear.setSize(80, 30);
                lbYear.setLocation(120,90);
                container.add(lbYear);
                
                txtYear.setSize(200, 30);
                txtYear.setLocation(240,90);
                container.add(txtYear);
                
                lbCouncil.setSize(100, 30);
                lbCouncil.setLocation(120,130);
                container.add(lbCouncil);
                
                txtCouncil.setSize(200, 30);
                txtCouncil.setLocation(240,130);
                container.add(txtCouncil);
                
                lbCountry.setSize(100, 30);
                lbCountry.setLocation(120,170);
                container.add(lbCountry);
                
                cmbCountry=new JComboBox(Data.getbricsCountries());
                cmbCountry.setSize(200, 30);
                cmbCountry.setLocation(240,170);
                container.add(cmbCountry);
                
                lbSpecialization.setSize(100, 30);
                lbSpecialization.setLocation(120,210);
                container.add(lbSpecialization);
                
                cmbSpecialization=new JComboBox(Data.getSpecialization());
                cmbSpecialization.setSize(200, 30);
                cmbSpecialization.setLocation(240,210);
                container.add(cmbSpecialization);
                
                lblUserID.setSize(100, 30);
                lblUserID.setLocation(120,290);
                container.add(lblUserID);
                
                txtUserID.setSize(200, 30);
                txtUserID.setLocation(240,290);
                container.add(txtUserID);
                
                lblPassword.setSize(150, 30);
                lblPassword.setLocation(120,330);
                container.add(lblPassword);
                
                txtPass.setSize(200, 30);
                txtPass.setLocation(240,330);
                container.add(txtPass);
                
                btnRegister.addActionListener(this);
                btnRegister.setSize(140, 30);
                btnRegister.setLocation(300,370);
                container.add(btnRegister);
                
                this.setVisible(true);
    }
   public void actionPerformed(ActionEvent e)
   {
       if(e.getSource()==btnRegister)
       {
           String selectedUser=txtUserID.getText().trim();
           if(Data.isUserExist(selectedUser))
           {
               JOptionPane.showMessageDialog(this, "User Already Exists");
           }
           else
           {
               if(txtName.getText().trim().equals(""))
               {
                   JOptionPane.showMessageDialog(this,"You must enter Full Name");
               }
               else if(txtNo.getText().trim().equals(""))
               {
                   JOptionPane.showMessageDialog(this,"You must enter Regno");
               }
               else if(txtYear.getText().trim().equals(""))
               {
                   JOptionPane.showMessageDialog(this,"Please enter year");
               }
               else if(txtCouncil.getText().trim().equals(""))
               {
                   JOptionPane.showMessageDialog(this,"You must enter Medical council");
               }
               else if(txtUserID.getText().trim().equals(""))
                {
                    JOptionPane.showMessageDialog(this,"You must enter UserID");
                }
               else if(txtPass.getText().trim().equals(""))
               {
                     JOptionPane.showMessageDialog(this,"Enter password");
               }
               else
               {
               Data.saveUserInfo(txtName.getText().trim(), selectedUser, txtPass.getText().trim(),Integer.parseInt(txtNo.getText().trim()),Integer.parseInt(txtYear.getText().trim()),txtCouncil.getText().trim(),cmbCountry.getSelectedItem().toString().trim(),cmbSpecialization.getSelectedItem().toString().trim());
               JOptionPane.showMessageDialog(this, "User Added Successfully");
               dispose();
               }
           }
       }
   }
   public void windowActivated(WindowEvent e)
    {}
    public void windowDeactivated(WindowEvent e)
    {}
    public void windowOpened(WindowEvent e)
    {}
    public void windowClosed(WindowEvent e)
    {}
    public void windowClosing(WindowEvent e)
    { dispose(); }
    public void windowIconified(WindowEvent e)
    {}
    public void windowDeiconified(WindowEvent e)
    {}
}
