/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brics;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.imageio.ImageIO;
/**
 *
 * @author Anju
 */
public class ControlPanel  extends JFrame implements WindowListener,ActionListener
{
    private ImageIcon imgAdmin = new ImageIcon(); 
    private JButton lblAdmin=new JButton();
    
    private ImageIcon imgUsers = new ImageIcon();
    private JButton lblUsers=new JButton(); 
    
    private ImageIcon imgAuditor = new ImageIcon(); 
    private JButton lblAuditor=new JButton(); 
    
    private Container content=getContentPane();
    
    private Font fnt=new Font("Helvetica",Font.BOLD,20);
    private JButton btnAdmin=new JButton("Cloud Admin");
    private JButton btnUsers=new JButton("Doctors");
    private JButton btnAuditor=new JButton("Attribute Manager");
    
    public ControlPanel(String title)
    {
        super(title);
        setSize(855,380);
        setResizable(false);
        final Toolkit toolkit = Toolkit.getDefaultToolkit();
                final Dimension screenSize = toolkit.getScreenSize();
                final int x = (screenSize.width - this.getWidth()) / 2;
                final int y = (screenSize.height - this.getHeight()) / 2;
                this.setLocation(x, 10);
                
                addWindowListener(this);
                content.setLayout(null);
                try
                {
                    imgAdmin=new ImageIcon(ImageIO.read(getClass().getResource("/images/cloudadmin.png")));
                    lblAdmin=new JButton(imgAdmin);
                    imgUsers=new ImageIcon(ImageIO.read(getClass().getResource("/images/cloudusers.png")));
                    lblUsers=new JButton(imgUsers);
                    imgAuditor=new ImageIcon(ImageIO.read(getClass().getResource("/images/cloudauditor.png")));
                    lblAuditor=new JButton(imgAuditor);
                    
                }catch(Exception ex)
                {
                    System.out.println(ex.getMessage());
                }
                
                lblAdmin.setSize(256, 256);
                lblAdmin.setLocation(20, 20);
                lblAdmin.addActionListener(this);
                content.add(lblAdmin);
                
                lblUsers.setSize(256, 256);
                lblUsers.setLocation(296, 20);
                lblUsers.addActionListener(this);
                content.add(lblUsers);
                
                lblAuditor.setSize(256, 256);
                lblAuditor.setLocation(572, 20);
                lblAuditor.addActionListener(this);
                content.add(lblAuditor);
                
                
                btnAdmin.setSize(256, 50);
                btnAdmin.setLocation(20, 280);
                btnAdmin.setFont(fnt);
                btnAdmin.addActionListener(this);
                content.add(btnAdmin);
                
                btnUsers.setSize(256, 50);
                btnUsers.setLocation(296, 280);
                btnUsers.setFont(fnt);
                btnUsers.addActionListener(this);
                content.add(btnUsers);
                
                btnAuditor.setSize(256, 50);
                btnAuditor.setLocation(572, 280);
                btnAuditor.setFont(fnt);
                btnAuditor.addActionListener(this);
                content.add(btnAuditor);
                
                setVisible(true);
    }
   /* public static void main(String[] args) {
        // TODO code application logic here
        ControlPanel ua=new ControlPanel("CONTROL PANEL");
    }*/
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==lblAdmin || e.getSource()==btnAdmin)
        {
            Cloud c=new Cloud("Cloud Environment");
        }
        else if(e.getSource()==lblUsers || e.getSource()==btnUsers)
        {
            UserArea ua=new UserArea("User Environment");
        }
        else if(e.getSource()==lblAuditor || e.getSource()==btnAuditor)
        {
           // lblAuditor.setEnabled(false);
           // btnAuditor.setEnabled(false);
            //TPA tpa=new TPA("TPA Login");
            AALogin aal=new AALogin("Attribute Agency Login");
        }
    }
    public void windowClosing(WindowEvent e)
    {
    System.exit(0);
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
