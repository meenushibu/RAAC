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
 * @author Anju
 */
public class UserArea extends JFrame implements WindowListener,ActionListener{
    private JMenu mnuUser = new JMenu("Doctor");
    private JMenu mnuResourceManager = new JMenu("Resource Manager");
    private JMenuItem mnuLogin = new JMenuItem("Login");
    private JMenuItem mnuRegister = new JMenuItem("Registration");
    private JMenuItem mnuLogout = new JMenuItem("Logout");
    private JMenuItem mnuQuit = new JMenuItem("Quit");
    private JMenuBar bar = new JMenuBar();
    
    private JMenuItem mnuUploadResource = new JMenuItem("Upload Resource");
    private JMenuItem mnuViewResource = new JMenuItem("View Resource");
    public UserArea(String title)
    {
        super(title);
        setSize(1000,650);
                
                final Toolkit toolkit = Toolkit.getDefaultToolkit();
                final Dimension screenSize = toolkit.getScreenSize();
                final int x = (screenSize.width - this.getWidth()) / 2;
                final int y = (screenSize.height - this.getHeight()) / 2;
                this.setLocation(x, y);
                
                Container con=getContentPane();
                con.setBackground(Color.WHITE);
                ImageIcon imgThisImg = new ImageIcon("images/doctor.jpeg");
                JLabel l=new JLabel(imgThisImg);
    
                con.setLayout(new BorderLayout());
                con.add(l,BorderLayout.CENTER);
    
        addWindowListener(this);
        this.setResizable(false);
        
        mnuRegister.setMnemonic('R');
        mnuRegister.addActionListener(this);
        
        mnuLogin.setMnemonic('L');
        mnuLogin.addActionListener(this);
        
        mnuLogout.setMnemonic('L');
        mnuLogout.addActionListener(this);
        
        mnuQuit.setMnemonic('Q');
        mnuQuit.addActionListener(this);
        
        mnuUploadResource.setMnemonic('U');
        mnuUploadResource.addActionListener(this);
        
        mnuViewResource.setMnemonic('V');
        mnuViewResource.addActionListener(this);
        
        mnuUser.add(mnuRegister);
        mnuUser.add(mnuLogin);
        mnuUser.add(mnuLogout);
        mnuUser.add(mnuQuit);
        
        //mnuLogout.setEnabled(false);
        mnuResourceManager.add(mnuUploadResource);
        mnuResourceManager.add(mnuViewResource);
        
        setJMenuBar(bar);
        bar.add(mnuUser);
        bar.add(mnuResourceManager);
        mnuResourceManager.setVisible(false);
        mnuLogout.setVisible(false);   
        
        setVisible(true);
    }
    public static void main(String[] args) {
        // TODO code application logic here
        UserArea ua=new UserArea("User Environment");
    }
    public void actionPerformed(ActionEvent e)
				{
                                if(e.getSource()==mnuRegister) 
                                   {
                                       UserRegistrationForm admin=new UserRegistrationForm(this,"Doctor Registration Form",true);
                                   }
                                else if(e.getSource()==mnuLogin) 
                                   {
                                       UserLogin user=new UserLogin(this,"User Login",true);
                                   }
                                else if(e.getSource()==mnuLogout) 
                                   {
                                       UserSettings.setLoginUser(new String());
                                       UserSettings.setLoginPass(new String());
                                       UserSettings.setLoginStatus(false);
                                       JOptionPane.showMessageDialog(this, "This will end your user session . Thank you");
                                       dispose();
                                       System.exit(0);
                                       
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
                                else if(e.getSource()==mnuUploadResource) 
                                   {
                                       UploadResourceForm upRes=new UploadResourceForm(this,"Upload Resource Form",true);
                                   }
                                else if(e.getSource()==mnuViewResource)
                                   {
                                       ViewResourceForm upRes=new ViewResourceForm(this,"View Resource Form",true);
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
     public void enableUserMenus(boolean flag)
     {
       if(flag)
       {
          mnuLogout.setVisible(true);
          mnuLogin.setVisible(false);
          mnuRegister.setVisible(false);
          mnuResourceManager.setVisible(true);
       }
       else
       {
           
       }
    }
}
