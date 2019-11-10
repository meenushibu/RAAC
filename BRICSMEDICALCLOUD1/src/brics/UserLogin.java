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
public class UserLogin  extends JDialog implements WindowListener , ActionListener{
   private Container container=getContentPane(); 
   private JLabel lblUser=new JLabel("User ID");
   private JLabel lblPass=new JLabel("Password");
   
   private JTextField txtUser=new JTextField(20);
   private JPasswordField txtPass=new JPasswordField(20);
   
   private JButton btnLogin=new JButton("Login");
   private UserArea parent;
   public UserLogin(UserArea parent,String title , boolean flag)
   {
       super(parent,title,flag);
       this.parent=parent;
       container.setLayout(null);
       container.setBackground(Color.WHITE);
       this.setTitle("Doctor Login");
       this.addWindowListener(this);
       this.setSize(350, 160);
       this.setResizable(false);
       final Toolkit toolkit = Toolkit.getDefaultToolkit();
                final Dimension screenSize = toolkit.getScreenSize();
                final int x = (screenSize.width - this.getWidth()) / 2;
                final int y = (screenSize.height - this.getHeight()) / 2;
                this.setLocation(x, y);
                
                ImageIcon imgThisImg = new ImageIcon("images/doc.png");
		JLabel label = new JLabel(imgThisImg);
                label.setSize(135, 135);
                label.setLocation(0, 0);
                container.add(label);
                
                lblUser.setSize(50, 30);
                lblUser.setLocation(150,10);
                container.add(lblUser);
                
                txtUser.setSize(100, 30);
                txtUser.setLocation(230,10);
                container.add(txtUser);
                
                lblPass.setSize(70, 30);
                lblPass.setLocation(150,50);
                container.add(lblPass);
                
                txtPass.setSize(100, 30);
                txtPass.setLocation(230,50);
                
                container.add(txtPass);
                
                btnLogin.addActionListener(this);
                btnLogin.setSize(100, 30);
                btnLogin.setLocation(230,90);
                container.add(btnLogin);
                this.setVisible(true);
   }
  
   public void actionPerformed(ActionEvent e)
   {
       if(e.getSource()==btnLogin)
       {
          if( Data.doUserLogin(txtUser.getText().trim(), txtPass.getText().trim()))
          {
              UserSettings.setLoginUser(txtUser.getText().trim());
              UserSettings.setLoginPass(txtPass.getText().trim());
              UserSettings.setLoginStatus(true);
              parent.enableUserMenus(true);
              dispose();
          }
          else
          {
              JOptionPane.showMessageDialog(this, "Login Failed");
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
