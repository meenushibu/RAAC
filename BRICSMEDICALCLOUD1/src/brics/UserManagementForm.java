/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package brics;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.io.*;
import java.net.*;
/**
 *
 * @author Anju
 */
public class UserManagementForm   extends JDialog implements WindowListener , ActionListener{
   private Container container=getContentPane(); 
   private Cloud parent; 
   private JLabel lblNewUsers=new JLabel("New User Requests");
   private JLabel lblApprovedUsers=new JLabel("Approved Users");
   
   private DefaultListModel modelNewUsers=new DefaultListModel();
   private DefaultListModel modelApprovedUsers=new DefaultListModel();
   
   
   private JList lstNewUsers=new JList(modelNewUsers);
   private JList lstApprovedUsers=new JList(modelApprovedUsers);
  
   
   private JScrollPane paneNewUsers = new JScrollPane(lstNewUsers);
   private JScrollPane paneApprovedUsers = new JScrollPane(lstApprovedUsers);
   
   
   private JTextArea txtConsole=new JTextArea();
   private JScrollPane txtPane=new JScrollPane(txtConsole);
   
   private JButton btnAccept=new JButton("Accept User");
   private JButton btnRevoke=new JButton("Revoke User");
   private int basePort=5555;
   
   public UserManagementForm(Cloud parent,String title , boolean flag)
    {
       super(parent,title,flag);
       this.parent=parent;
       container.setLayout(null);
       container.setBackground(Color.WHITE);
       
       this.addWindowListener(this);
       this.setSize(910, 350);
       this.setResizable(false);
       final Toolkit toolkit = Toolkit.getDefaultToolkit();
                final Dimension screenSize = toolkit.getScreenSize();
                final int x = (screenSize.width - this.getWidth()) / 2;
                final int y = (screenSize.height - this.getHeight()) / 2;
                this.setLocation(x, y);
                
        lblNewUsers.setSize(200, 30); 
        lblNewUsers.setLocation(10,10);
        container.add(lblNewUsers);
        
        paneNewUsers.setSize(200, 200);
        paneNewUsers.setLocation(10,40);
        container.add(paneNewUsers);
        
        
        btnAccept.setSize(120, 30);
        btnAccept.setLocation(220,100);
        container.add(btnAccept);
        btnAccept.addActionListener(this);
        
        btnRevoke.setSize(120, 30);
        btnRevoke.setLocation(350,260);
        container.add(btnRevoke);
        btnRevoke.addActionListener(this);
        
        lblApprovedUsers.setSize(200, 30); 
        lblApprovedUsers.setLocation(350,10);
        container.add(lblApprovedUsers);
        
        paneApprovedUsers.setSize(200, 200);
        paneApprovedUsers.setLocation(350, 40);
        container.add(paneApprovedUsers);
        
       
        
        populateNewUsersList();
        populateApprovedUsersList();
        
        txtPane.setSize(300, 200);
        txtPane.setLocation(580, 40);
        container.add(txtPane);
        
        this.setVisible(true);
    }
   public void populateNewUsersList()
   {
       ArrayList list=Data.getNewUsers();
       modelNewUsers.clear();
       for(int i=0;i<list.size();i++)
       {
           
           modelNewUsers.addElement(list.get(i).toString().trim());
       }
   }
   public void populateApprovedUsersList()
   {
       ArrayList list=Data.getApprovedUsers();
       modelApprovedUsers.clear();
       for(int i=0;i<list.size();i++)
       {
           modelApprovedUsers.addElement(list.get(i).toString().trim());
       }
   }
  
   public void actionPerformed(ActionEvent e)
   {
       if(e.getSource()==btnAccept)
       {
           if(lstNewUsers.getSelectedValue()!=null)
           {
           String selected=lstNewUsers.getSelectedValue().toString();
           
           
           int dialogButton = JOptionPane.YES_NO_OPTION;
           int dialogResult = JOptionPane.showConfirmDialog (this, "Would You Like to Accept the user "+selected+" ?","Warning",dialogButton);
           if(dialogResult == JOptionPane.YES_OPTION)
           {
               Packet rep_country=sendPacket("127.0.0.1",(basePort+AttributeType.COUNTRY),new Packet(PacketType.VERIFY_COUNTRY,selected.trim()));
               Packet rep_special=sendPacket("127.0.0.1",(basePort+AttributeType.SPECIALIZATION),new Packet(PacketType.VERIFY_SPECIALIZATION,selected.trim()));
               if(rep_country.getPacketType()==PacketType.REPLY_COUNTRY && rep_special.getPacketType()==PacketType.REPLY_SPECIALIZATION)
               {
                    String secretKey=new String(rep_country.getPacketContent().trim()+rep_special.getPacketContent().trim());
                    txtConsole.append("\nSecret Key : "+secretKey);
                    Data.approveNewUser(selected.trim(),secretKey);  
                    ECC ecc = new ECC(selected.trim(),this.txtConsole);
                    populateNewUsersList();
                    populateApprovedUsersList();
               }
             
           }
           }
           else
           {
               JOptionPane.showMessageDialog(this, "No Users Selected for Approval");
           }
           
       }
       else if(e.getSource()==btnRevoke)
       {
           if(lstApprovedUsers.getSelectedValue()!=null)
           {
           String selected=lstApprovedUsers.getSelectedValue().toString().trim();
           
           
           int dialogButton = JOptionPane.YES_NO_OPTION;
           int dialogResult = JOptionPane.showConfirmDialog (this, "Would You Like to Revoke the user "+selected+" ?","Warning",dialogButton);
                if(dialogResult == JOptionPane.YES_OPTION)
                {
                    Packet rep_country=sendPacket("127.0.0.1",(basePort+AttributeType.COUNTRY),new Packet(PacketType.USER_REVOKED,selected.trim()));
                    Packet rep_special=sendPacket("127.0.0.1",(basePort+AttributeType.SPECIALIZATION),new Packet(PacketType.USER_REVOKED,selected.trim()));
                    Data.revokeUser(selected);
                    modelApprovedUsers.removeElement(selected);
                    JOptionPane.showMessageDialog(this, "User Revoked Successfully");
                }
           }
           else
           {
               JOptionPane.showMessageDialog(this, "No Users Selected for Revocation");
           }
           
       }
     
   }
   private Packet sendPacket(String host,int port,Packet packet)
   {
       Packet p=new Packet();
               try
               {
                   
                    Socket socket = new Socket(host, port);
                    ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                    ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                    out.writeObject(packet);
                    p=(Packet)in.readObject();
                    in.close();
                    out.close();
                    socket.close();
                    
               }catch(Exception ex)
               {
                   System.out.println(ex.getMessage());
                   
               }   
        return p;   
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
