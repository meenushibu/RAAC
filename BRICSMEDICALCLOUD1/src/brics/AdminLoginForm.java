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
public class AdminLoginForm extends JDialog implements WindowListener , ActionListener{
   private Container container=getContentPane(); 
   private JLabel lblUser=new JLabel("User ID");
   private JLabel lblPass=new JLabel("Password");
   
   private JTextField txtUser=new JTextField(20);
   private JPasswordField txtPass=new JPasswordField(20);
   
   private JButton btnLogin=new JButton("Login");
   private Cloud parent;
   public AdminLoginForm(Cloud parent,String title , boolean flag)
   {
       super(parent,title,flag);
       this.parent=parent;
       container.setLayout(null);
       container.setBackground(Color.WHITE);
       this.setTitle("Login");
       this.addWindowListener(this);
       this.setSize(300, 160);
       this.setResizable(false);
       final Toolkit toolkit = Toolkit.getDefaultToolkit();
                final Dimension screenSize = toolkit.getScreenSize();
                final int x = (screenSize.width - this.getWidth()) / 2;
                final int y = (screenSize.height - this.getHeight()) / 2;
                this.setLocation(x, y);
                
                ImageIcon imgThisImg = new ImageIcon("images/admin.jpg");
		JLabel label = new JLabel(imgThisImg);
                label.setSize(98, 105);
                label.setLocation(0, 0);
                container.add(label);
                
                lblUser.setSize(50, 30);
                lblUser.setLocation(100,10);
                container.add(lblUser);
                
                txtUser.setSize(100, 30);
                txtUser.setLocation(180,10);
                container.add(txtUser);
                
                lblPass.setSize(70, 30);
                lblPass.setLocation(100,50);
                container.add(lblPass);
                
                txtPass.setSize(100, 30);
                txtPass.setLocation(180,50);
                
                container.add(txtPass);
                
                btnLogin.addActionListener(this);
                btnLogin.setSize(100, 30);
                btnLogin.setLocation(180,90);
                container.add(btnLogin);
                this.setVisible(true);
   }
   
   public void actionPerformed(ActionEvent e)
   {
       if(e.getSource()==btnLogin)
       {
          if( Data.doAdminLogin(txtUser.getText().trim(), txtPass.getText().trim()))
          {
              AdminSettings.setLoginUser(txtUser.getText().trim());
              AdminSettings.setLoginPass(txtPass.getText().trim());
              AdminSettings.setLoginStatus(true);
              parent.enableAdminLoginMenu(false);
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
