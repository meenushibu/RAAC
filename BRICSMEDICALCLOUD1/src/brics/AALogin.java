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
public class AALogin  extends JFrame implements WindowListener , ActionListener{
   private Container container=getContentPane(); 
   private JLabel lblUser=new JLabel("User ID");
   private JLabel lblPass=new JLabel("Password");
   private JLabel lblAgency=new JLabel("Agency");
   
   private JTextField txtUser=new JTextField(20);
   private JPasswordField txtPass=new JPasswordField(20);
   private String country[]={"Choose Agency","Country","Specialization"};        
   private JComboBox cmbAgency=new JComboBox(country);    
   
   private JButton btnLogin=new JButton("Login");
  
   public AALogin(String title)
   {
       super(title);
      
       container.setLayout(null);
       container.setBackground(Color.WHITE);
       this.addWindowListener(this);
       this.setSize(450, 220);
       this.setResizable(false);
       final Toolkit toolkit = Toolkit.getDefaultToolkit();
                final Dimension screenSize = toolkit.getScreenSize();
                final int x = (screenSize.width - this.getWidth()) / 2;
                final int y = (screenSize.height - this.getHeight()) / 2;
                this.setLocation(x, y);
                
                ImageIcon imgThisImg = new ImageIcon("images/att.png");
		JLabel label = new JLabel(imgThisImg);
                label.setSize(153, 153);
                label.setLocation(0, 20);
                container.add(label);
                
                lblUser.setSize(50, 30);
                lblUser.setLocation(170,10);
                container.add(lblUser);
                
                txtUser.setSize(150, 30);
                txtUser.setLocation(250,10);
                container.add(txtUser);
                
                lblPass.setSize(70, 30);
                lblPass.setLocation(170,50);
                container.add(lblPass);
                
                txtPass.setSize(150, 30);
                txtPass.setLocation(250,50);
                container.add(txtPass);
                
                lblAgency.setSize(150, 30);
                lblAgency.setLocation(170, 90);
                container.add(lblAgency);
                
                cmbAgency.setSize(150, 30);
                cmbAgency.setLocation(250,90);
                container.add(cmbAgency);
                
                btnLogin.addActionListener(this);
                btnLogin.setSize(150, 30);
                btnLogin.setLocation(250,140);
                container.add(btnLogin);
                this.setVisible(true);
   }
  
   public void actionPerformed(ActionEvent e)
   {
       if(e.getSource()==btnLogin)
       {
           String userName=txtUser.getText().trim();
           String userPass=txtPass.getText().trim();
           String userAgency=cmbAgency.getSelectedItem().toString().trim();
           if(userName.equals(""))
           {
               JOptionPane.showMessageDialog(this, "You must enter a username");
           }
           else if(userPass.equals(""))
           {
               JOptionPane.showMessageDialog(this, "You must enter a password");
           }
           else if(userAgency.equals("Choose Agency"))
           {
               JOptionPane.showMessageDialog(this, "You must select an Attribute Agency");
           }
           else
           {
               if(Data.doAttributeAgencyLogin(userName, userPass, userAgency))
               {
                   
                    if(userAgency.trim().equals("Country"))
                    {
                        AttributeAgency aa=new AttributeAgency("Attribute Agency for "+userAgency,AttributeType.COUNTRY);
                    }
                    else if(userAgency.trim().equals("Specialization"))
                    {
                
                        AttributeAgency aa=new AttributeAgency("Attribute Agency for "+userAgency,AttributeType.SPECIALIZATION);
                    }
                   dispose();
               }
               else
               {
                   JOptionPane.showMessageDialog(this, "Access Denied !!!");
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
    public static void main(String[] args)
    {
        AALogin aal=new AALogin("Attribute Agency Login");
    }
}
