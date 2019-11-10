/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package brics;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
/**
 *
 *@author Anju 
 */
public class Cloud  extends JFrame implements WindowListener,ActionListener{
    private JMenu mnuCloud = new JMenu("Admin");
    private JMenuItem mnuLogin = new JMenuItem("Login");
    private JMenuItem mnuChangePass = new JMenuItem("Change Password");
    private JMenuItem mnuUserAdmin = new JMenuItem("User Management");
    private JMenuItem mnuQuit = new JMenuItem("Quit");
    public Cloud(){}
    public Cloud(String title)
    {
        super(title);
        setSize(1000,600);
                
                final Toolkit toolkit = Toolkit.getDefaultToolkit();
                final Dimension screenSize = toolkit.getScreenSize();
                final int x = (screenSize.width - this.getWidth()) / 2;
                final int y = (screenSize.height - this.getHeight()) / 2;
                this.setLocation(x, y);
                
                Container con=getContentPane();
                con.setBackground(Color.WHITE);
                ImageIcon imgThisImg = new ImageIcon("images/mc.png");
                JLabel l=new JLabel(imgThisImg);
    
                con.setLayout(new BorderLayout());
                con.add(l,BorderLayout.CENTER);
    
        addWindowListener(this);
        this.setResizable(false);
        
        
        mnuCloud.setMnemonic('A');
        mnuUserAdmin.setMnemonic('M');
        mnuLogin.setMnemonic('L');
        mnuLogin.addActionListener(this);
        mnuQuit.setMnemonic('Q');
        mnuQuit.addActionListener(this);	
        
        mnuChangePass.setMnemonic('P');
        mnuChangePass.addActionListener(this);
        mnuChangePass.setEnabled(false);
        mnuUserAdmin.setEnabled(false);
        mnuUserAdmin.addActionListener(this);
        
        mnuCloud.add(mnuLogin);
        mnuCloud.add(mnuChangePass);    
        mnuCloud.add(mnuUserAdmin);  
	mnuCloud.add(mnuQuit);			
	
        
        JMenuBar bar = new JMenuBar();
        setJMenuBar(bar);
        bar.add(mnuCloud);
            
        
        setVisible(true);
    
    
    }
    public void enableAdminLoginMenu(boolean flag)
    {
        if(flag)
        {
            mnuLogin.setEnabled(true);
            mnuChangePass.setEnabled(false);
            mnuUserAdmin.setEnabled(false);
        }
        else
        {
            mnuLogin.setEnabled(false);
            mnuChangePass.setEnabled(true);
            mnuUserAdmin.setEnabled(true);
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Cloud c=new Cloud("Medical Cloud Environment");
    }
    public void actionPerformed(ActionEvent e)
				{
					
                                   if(e.getSource()==mnuLogin) 
                                   {
                                       AdminLoginForm admin=new AdminLoginForm(this,"Admin Login",true);
                                   }
                                   else if(e.getSource()==mnuChangePass) 
                                   {
                                      ChangeAdminPassForm cap=new ChangeAdminPassForm(this,"Change Admin Password",true);
                                   }
                                   else if(e.getSource()==mnuUserAdmin) 
                                   {
                                      UserManagementForm umf=new UserManagementForm(this,"User Management",true);
                                   }
                                   else if(e.getSource()==mnuQuit) 
                                   {
                                       if (JOptionPane.showConfirmDialog(null, "Do you want to Quit?", "WARNING",
        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
    dispose();
   // System.exit(0);
} else {
    // no option
}
                                   } 
                                        
				}
    public void windowClosing(WindowEvent e)
    {
    //System.exit(0);
        dispose();
    }
    public void windowClosed(WindowEvent e)
    {}
    public void windowOpened(WindowEvent e)
    {}
    public void windowActivated(WindowEvent e)
    {}
    public void windowDeactivated(WindowEvent e)
    {}
    public void windowIconified(WindowEvent e)
    {}
    public void windowDeiconified(WindowEvent e)
    {}
}
