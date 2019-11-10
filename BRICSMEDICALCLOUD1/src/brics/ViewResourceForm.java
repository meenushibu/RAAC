/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package brics;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.filechooser.*;
import java.io.*;
import java.util.*;
/**
 *
 * @author Anju
 */
public class ViewResourceForm extends JDialog implements WindowListener , ActionListener,MouseListener{
    private Container container=getContentPane(); 
    private UserArea parent;
    
   
    
    private JButton btnDecript =new JButton("Decript");
    private JTextArea txtFileContent=new JTextArea();
    private JScrollPane txtPane=new JScrollPane(txtFileContent);
    
    private JLabel lblNa=new JLabel("Name\0\0\0\0\0\0\0:\0");
    private JLabel lblSpeci=new JLabel("Specialization\0: ");
    private JLabel lblCo=new JLabel("Country\0\0\0\0\0\0:\0");
    
    private JLabel lblName=new JLabel("");
    private JLabel lblSpecialization=new JLabel("");
    private JLabel lblCountry=new JLabel("");
    private  DefaultListModel model = new DefaultListModel();

    private JList fileList=new JList(model);
    private JScrollPane listPane=new JScrollPane(fileList);
    
    private JLabel lblACLSpecialization=new JLabel("Resources In Server");
    private int resourceID=0;
    String resourceName=new String();
    
    
    
    public ViewResourceForm(UserArea parent,String title , boolean flag)
    {
       super(parent,title,flag);
       this.parent=parent;
       container.setLayout(null);
       container.setBackground(Color.WHITE);
       
       this.addWindowListener(this);
       this.setSize(900, 600);
       this.setResizable(false);
       final Toolkit toolkit = Toolkit.getDefaultToolkit();
                final Dimension screenSize = toolkit.getScreenSize();
                final int x = (screenSize.width - this.getWidth()) / 2;
                final int y = (screenSize.height - this.getHeight()) / 2;
                this.setLocation(x, y);
                
                ImageIcon imgThisImg = new ImageIcon("images/doctor_small.png");
                JLabel l=new JLabel(imgThisImg);
                l.setSize(140,125);
                l.setLocation(20, 10);
                container.add(l);
                
                lblNa.setSize(100,25);
                lblNa.setLocation(150,25);
                container.add(lblNa);
                
                lblName.setSize(150,25);
                lblName.setLocation(255,25);
                container.add(lblName);
                
                lblSpeci.setSize(100,25);
                lblSpeci.setLocation(150,55);
                container.add(lblSpeci);
                
                lblSpecialization.setSize(150,25);
                lblSpecialization.setLocation(255,55);
                container.add(lblSpecialization);
                
                lblCo.setSize(100,25);
                lblCo.setLocation(150,85);
                container.add(lblCo);
                
                lblCountry.setSize(150,25);
                lblCountry.setLocation(255,85);
                container.add(lblCountry);
                
                
                
                
                txtPane.setSize(500, 300);
                txtPane.setLocation(50,150);
                container.add(txtPane);
                
                listPane.setSize(200,300);
                listPane.setLocation(600,150);
                container.add(listPane);
                
                btnDecript.setSize(250, 25);
                btnDecript.setLocation(300,470);
                container.add(btnDecript);
                btnDecript.addActionListener(this);
                btnDecript.setEnabled(false);
                
                
                
                
                lblACLSpecialization.setSize(400, 40);
                lblACLSpecialization.setLocation(620,100);
                lblACLSpecialization.setFont(new Font("Helvetica",Font.BOLD,14));
                container.add(lblACLSpecialization);
                
                ArrayList arrList=Data.getResources();
                for(int i=0;i<arrList.size();i++)
                {
                    model.addElement(arrList.get(i));

                }
                if(UserSettings.getLoginStatus())
                {
                    lblName.setText(Data.getFullUserName());
                    lblSpecialization.setText(Data.getDoctorSpecialization());
                    lblCountry.setText(Data.getDoctorCountry());
                }
                fileList.addMouseListener(this);
                this.setVisible(true);
    }
   public void actionPerformed(ActionEvent e)
   {
       if(e.getSource()==btnDecript)
       {
           txtFileContent.setText(new String(CPABE.decript(resourceID,resourceName)));
           btnDecript.setEnabled(false);
           JOptionPane.showMessageDialog(this, "Data Decrypted Successfully");
           
       }
   }
   public void mouseClicked(MouseEvent e){
   if(e.getSource()==fileList)
   {
        if (e.getClickCount() == 2) {
            
           resourceName=fileList.getSelectedValue().toString().trim();
            
            resourceID=Data.getResourceIDByName(resourceName);
            String usrCountry=lblCountry.getText().trim();
            String usrSpecial=lblSpecialization.getText().trim();
            
            if(Data.verifyAttributes(resourceID, usrCountry, usrSpecial) || UserSettings.getLoginUser().trim().equals(Data.getResourceOwner(resourceID).trim()))
            {
                byte[] bContent=Data.getResourceContent(resourceID, resourceName);
                txtFileContent.setText(new String(bContent));
                btnDecript.setEnabled(true);
            }
            else
            {
                btnDecript.setEnabled(false);
                JOptionPane.showMessageDialog(this, "Access Denied");
            }
        }

   }
   }
   public void mouseEntered(MouseEvent e){}
   public void mouseExited(MouseEvent e){}
   public void mousePressed(MouseEvent e){}
   public void mouseReleased(MouseEvent e){}
   
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
