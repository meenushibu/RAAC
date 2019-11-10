/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package brics;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.imageio.ImageIO;
/**
 *
 * @author Anju
 */
public class BRICS  extends JFrame implements WindowListener , ActionListener{
   private Container container=getContentPane(); 
   
   
   private JLabel lblChoose=new JLabel("Choose Cloud Server");
   private DefaultListModel  model = new DefaultListModel();

   private JList lstServers=new JList(model);
   private JScrollPane listPane = new JScrollPane(lstServers);

   private JButton btnConnect=new JButton("Connect");
   
   public BRICS(String title)
   {
       super(title);
       
       container.setLayout(null);
       container.setBackground(Color.WHITE);
       
       this.addWindowListener(this);
       this.setSize(400, 200);
       this.setResizable(false);
       final Toolkit toolkit = Toolkit.getDefaultToolkit();
                final Dimension screenSize = toolkit.getScreenSize();
                final int x = (screenSize.width - this.getWidth()) / 2;
                final int y = (screenSize.height - this.getHeight()) / 2;
                this.setLocation(x, y);
                
                
                ImageIcon imgThisImg=new ImageIcon();
       try {
           imgThisImg = new ImageIcon(ImageIO.read(getClass().getResource("/images/srvr.jpg")));
       } catch (IOException ex) {
           System.out.println(ex.getMessage());
       }
		JLabel label = new JLabel(imgThisImg);
                label.setSize(170, 149);
                label.setLocation(0, 0);
                container.add(label);
                
                                
                lblChoose.setSize(200, 30);
                lblChoose.setLocation(180,10);
                lblChoose.setFont(new Font("Helvetica",Font.BOLD,16));
                container.add(lblChoose);
                
                
                
                listPane.setSize(200, 60);
                listPane.setLocation(180,50);
                
                container.add(listPane);
                
                btnConnect.addActionListener(this);
                btnConnect.setSize(200, 30);
                btnConnect.setLocation(180,130);
                container.add(btnConnect);
                
                String[] availableServers=CloudSettings.getAvalilableServrs();
                model.clear();
                for(int i=0;i<availableServers.length;i++)
                {
                    model.addElement(availableServers[i]);
                }
                this.setVisible(true);
   }
  
   public void actionPerformed(ActionEvent e)
   {
       if(e.getSource()==btnConnect)
       {
           btnConnect.setEnabled(false);
           Object selectedObject=lstServers.getSelectedValue();
           if(selectedObject!=null)
           {
               String selectedServer=selectedObject.toString().trim();
               if(selectedServer.trim().equals(CloudSettings.getRemote().trim()))
               {
                   CloudSettings.setOnline(true);
               }
               else
               {
                   CloudSettings.setOnline(false);
               }
           
           try
           {
                if(Data.getConnection()!=null)
                {
                    dispose();
                     ControlPanel ua=new ControlPanel(CloudSettings.getCurrentServer());
                }
           }catch(Exception ex)
           {
               JOptionPane.showMessageDialog(this, ex.getMessage());
           }
           
           }
           else
           {
               JOptionPane.showMessageDialog(this, "Please choose a cloud server from the list");
           }
           btnConnect.setEnabled(true);
       }
   }
   public static void main(String[] args)
   {
       BRICS r=new BRICS("Choose Server");
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
