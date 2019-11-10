/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package brics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Anju
 */
public class AttributeAgency extends JFrame implements WindowListener,ActionListener,Runnable
{
    private JTextArea txtConsole=new JTextArea();
    private JScrollPane txtPane=new JScrollPane(txtConsole);
    private int attributeType=0;
    private int basePort=5555;
    private ServerSocket listener = null;
    AttributeAgency(String title,int attributeType)
    {
        super(title);
        this.attributeType=attributeType;
        setSize(500,300);
                
                final Toolkit toolkit = Toolkit.getDefaultToolkit();
                final Dimension screenSize = toolkit.getScreenSize();
                final int x = (screenSize.width - this.getWidth()) / 2;
                final int y = (screenSize.height - this.getHeight()) / 2;
                this.setLocation(x, y);
                
                Container con=getContentPane();
                con.setLayout(new BorderLayout());
                addWindowListener(this);
                this.setResizable(false);
                
                txtConsole.setEditable(false);
                con.add(txtPane,BorderLayout.CENTER);
                this.setVisible(true);
                if(attributeType==AttributeType.COUNTRY)
                {
            try {
                listener=new ServerSocket((basePort+AttributeType.COUNTRY));
            } catch (IOException ex) {
                Logger.getLogger(AttributeAgency.class.getName()).log(Level.SEVERE, null, ex);
            }
                }
                else if(attributeType==AttributeType.SPECIALIZATION)
                {
            try {
                listener=new ServerSocket((basePort+AttributeType.SPECIALIZATION));
            } catch (IOException ex) {
                Logger.getLogger(AttributeAgency.class.getName()).log(Level.SEVERE, null, ex);
            }
                }
                Thread thisThread=new Thread(this);
                thisThread.start();
    }
    public void run()
    {
        try
        {
            txtConsole.append("\nServer Started and Ready to connect.");
            while(true)
            {
                Socket socket = listener.accept();
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                Packet packet=(Packet)in.readObject();
                
                if(packet.getPacketType()==PacketType.VERIFY_COUNTRY)
                {
                    txtConsole.append("\nReceived Verification request for user : "+packet.getPacketContent());
                    Packet rePack=new Packet(PacketType.REPLY_COUNTRY,RandomString.getRandomString());
                    out.writeObject(rePack);
                    txtConsole.append("\nVerifyed the user "+packet.getPacketContent()+" and send secret key");
                }
                else if(packet.getPacketType()==PacketType.VERIFY_SPECIALIZATION)
                {
                    txtConsole.append("\nReceived Verification request for user : "+packet.getPacketContent());
                    Packet rePack=new Packet(PacketType.REPLY_SPECIALIZATION,RandomString.getRandomString());
                    out.writeObject(rePack);
                    txtConsole.append("\nVerifyed the user "+packet.getPacketContent()+" and send secret key");
                }
                else if(packet.getPacketType()==PacketType.USER_REVOKED)
                {
                    txtConsole.append("\nUser "+packet.getPacketContent()+" Revoked");
                    Packet rePack=new Packet(PacketType.NOTIFY_REVOKE,"OK");
                    out.writeObject(rePack);
                }
               
            }
            
        }catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        
    }
    
    
    public void windowClosing(WindowEvent e)
    {
   // System.exit(0);
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
    public void actionPerformed(ActionEvent e)
    {}
    
    
    
}
