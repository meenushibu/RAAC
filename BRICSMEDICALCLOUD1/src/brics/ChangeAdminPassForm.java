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
public class ChangeAdminPassForm  extends JDialog implements WindowListener , ActionListener{
    private Container container=getContentPane(); 
    private Cloud parent;
    private JLabel lblOldPass=new JLabel("Old Password");
    private JLabel lblNewPass=new JLabel("New Password");
    private JLabel lblReNewPass=new JLabel("Retype New Password");
   
    private JPasswordField txtOldPass=new JPasswordField(20);
    private JPasswordField txtNewPass=new JPasswordField(20);
    private JPasswordField txtReNewPass=new JPasswordField(20);
    private JButton btnChange=new JButton("Change Password");
    public ChangeAdminPassForm(Cloud parent,String title , boolean flag)
    {
       super(parent,title,flag);
       this.parent=parent;
       container.setLayout(null);
       container.setBackground(Color.WHITE);
       
       this.addWindowListener(this);
       this.setSize(380, 200);
       this.setResizable(false);
       final Toolkit toolkit = Toolkit.getDefaultToolkit();
                final Dimension screenSize = toolkit.getScreenSize();
                final int x = (screenSize.width - this.getWidth()) / 2;
                final int y = (screenSize.height - this.getHeight()) / 2;
                this.setLocation(x, y);
                
                ImageIcon imgThisImg = new ImageIcon("images/cp.jpg");
		JLabel label = new JLabel(imgThisImg);
                label.setSize(100, 100);
                label.setLocation(0, 0);
                container.add(label);
                
                lblOldPass.setSize(80, 30);
                lblOldPass.setLocation(100,10);
                container.add(lblOldPass);
                
                txtOldPass.setSize(100, 30);
                txtOldPass.setLocation(240,10);
                container.add(txtOldPass);
                
                lblNewPass.setSize(100, 30);
                lblNewPass.setLocation(100,50);
                container.add(lblNewPass);
                
                txtNewPass.setSize(100, 30);
                txtNewPass.setLocation(240,50);
                container.add(txtNewPass);
                
                lblReNewPass.setSize(150, 30);
                lblReNewPass.setLocation(100,90);
                container.add(lblReNewPass);
                
                txtReNewPass.setSize(100, 30);
                txtReNewPass.setLocation(240,90);
                container.add(txtReNewPass);
                
                btnChange.addActionListener(this);
                btnChange.setSize(140, 30);
                btnChange.setLocation(200,130);
                container.add(btnChange);
                
                this.setVisible(true);
   }
    public void actionPerformed(ActionEvent e)
   {
       if(e.getSource()==btnChange)
       {
           if(txtOldPass.getText().trim().equals(""))
           {
               JOptionPane.showMessageDialog(this, "Please enter the old password");
           }
           else if(txtNewPass.getText().trim().equals(""))
           {
               JOptionPane.showMessageDialog(this, "Please enter the new password");
           }
           else if(txtReNewPass.getText().trim().equals(""))
           {
               JOptionPane.showMessageDialog(this, "Please Re-enter the new password");
           }
           else if(!txtReNewPass.getText().trim().equals(txtNewPass.getText().trim()))
           {
               JOptionPane.showMessageDialog(this, "Password Mismatch");
           }
           else if(!txtOldPass.getText().trim().equals(AdminSettings.getLoginPass().trim()))
           {
               JOptionPane.showMessageDialog(this, "Access Denied !!!");
           }
           else
           {
               String tmpUser=AdminSettings.getLoginUser().trim();
               String tmpPass=txtOldPass.getText().trim();
               String newPass=txtNewPass.getText().trim();
               if(Data.doChangeAdminPassword(tmpUser, tmpPass, newPass))
               {
                   JOptionPane.showMessageDialog(this, "Admin Password changed Successfully");
               }
               else
               {
                    JOptionPane.showMessageDialog(this, "Admin Password Not Changed");
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
