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
public class UploadResourceForm extends JDialog implements WindowListener , ActionListener{
    private Container container=getContentPane(); 
    private UserArea parent;
    private final JFileChooser  fileDialog = new JFileChooser();
    private JLabel lblFile =new JLabel("File");
    private JTextField txtFileName =new JTextField();
    private JButton btnBrowse =new JButton("Browse");
    private JButton btnUpload =new JButton("Encrypt and Upload");
    private JTextArea txtFileContent=new JTextArea();
    private JScrollPane txtPane=new JScrollPane(txtFileContent);
    
    private JLabel lblNa=new JLabel("Name\0\0\0\0\0\0\0:\0");
    private JLabel lblSpeci=new JLabel("Specialization\0: ");
    private JLabel lblCo=new JLabel("Country\0\0\0\0\0\0:\0");
    
    private JLabel lblName=new JLabel("");
    private JLabel lblSpecialization=new JLabel("");
    private JLabel lblCountry=new JLabel("");
    
    private JLabel lblAccessControl=new JLabel("Access Control Scheme Attributes");
    private JLabel lblACLCountry=new JLabel("1. BRICS Country Attribute Selection");
    private JLabel lblACLSpecialization=new JLabel("Resources In Server");
    
    private JCheckBox chkIndia=new JCheckBox("India");
    private JCheckBox chkBrazil=new JCheckBox("Brazil");
    private JCheckBox chkRussia=new JCheckBox("Russia");
    private JCheckBox chkChina=new JCheckBox("China");
    private JCheckBox chkSouthAfrica=new JCheckBox("South Africa");
    
    private JCheckBox chkGeneralPhysician=new JCheckBox("General Physician");
    private JCheckBox chkGynecologist=new JCheckBox("Gynecologist");
    private JCheckBox chkPediatrician=new JCheckBox("Pediatrician");
    private JCheckBox chkOphthalmologist=new JCheckBox("Ophthalmologist");
    private JCheckBox chkDermatologist=new JCheckBox("Dermatologist");
    private JCheckBox chkENTDoctor=new JCheckBox("ENT Doctor");
    private JCheckBox chkOrthopedic=new JCheckBox("Orthopedic");
    private JCheckBox chkCardiologist=new JCheckBox("Cardiologist");
    private JCheckBox chkNeurologist=new JCheckBox("Neurologist");
    private JCheckBox chkDentist=new JCheckBox("Dentist ");
    private JCheckBox chkUrologist =new JCheckBox("Urologist");
    private JCheckBox chkPsychiatrist=new JCheckBox("Psychiatrist");
    private JCheckBox chkPathologist=new JCheckBox("Pathologist");
    private JCheckBox chkRadiologists=new JCheckBox("Radiologists");
    private JCheckBox chkAnesthesiologist=new JCheckBox("Anesthesiologist");
    private JCheckBox chkGeneralSurgeon=new JCheckBox("General Surgeon");
    private JCheckBox chkOncologist=new JCheckBox("Oncologist");
    private JCheckBox chkNephrologist=new JCheckBox("Nephrologist");
    private JCheckBox chkEndocrinologist=new JCheckBox("Endocrinologist");
    private JCheckBox chkGastrologist=new JCheckBox("Gastrologist");
    private JCheckBox chkVeterinary=new JCheckBox("Veterinary");
    
    
    
    public UploadResourceForm(UserArea parent,String title , boolean flag)
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
                
                lblFile.setSize(50, 25);
                lblFile.setLocation(50,460);
                container.add(lblFile);
                
                txtFileName.setSize(340, 25);
                txtFileName.setLocation(90,460);
                container.add(txtFileName);
                
                btnBrowse.setSize(100, 25);
                btnBrowse.setLocation(450,460);
                container.add(btnBrowse);
                btnBrowse.addActionListener(this);
                
                txtPane.setSize(500, 300);
                txtPane.setLocation(50,150);
                container.add(txtPane);
                
                btnUpload.setSize(250, 25);
                btnUpload.setLocation(450,530);
                container.add(btnUpload);
                btnUpload.addActionListener(this);
                
                FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
                fileDialog.setFileFilter(filter);
                
                
                lblAccessControl.setSize(400, 40);
                lblAccessControl.setLocation(560,10);
                lblAccessControl.setFont(new Font("Helvetica",Font.BOLD,20));
                container.add(lblAccessControl);
                
                lblACLCountry.setSize(400, 40);
                lblACLCountry.setLocation(580,50);
                lblACLCountry.setFont(new Font("Helvetica",Font.BOLD,14));
                container.add(lblACLCountry);
                
                chkIndia.setSize(100, 30);
                chkIndia.setLocation(580,90);
                chkIndia.setBackground(Color.WHITE);
                container.add(chkIndia);
                
                chkBrazil.setSize(100, 30);
                chkBrazil.setLocation(680,90);
                chkBrazil.setBackground(Color.WHITE);
                container.add(chkBrazil);
                
                chkRussia.setSize(100, 30);
                chkRussia.setLocation(780,90);
                chkRussia.setBackground(Color.WHITE);
                container.add(chkRussia);
                
                chkChina.setSize(100, 30);
                chkChina.setLocation(580,120);
                chkChina.setBackground(Color.WHITE);
                container.add(chkChina);
                
                chkSouthAfrica.setSize(100, 30);
                chkSouthAfrica.setLocation(680,120);
                chkSouthAfrica.setBackground(Color.WHITE);
                container.add(chkSouthAfrica);
                
                lblACLSpecialization.setSize(400, 40);
                lblACLSpecialization.setLocation(580,150);
                lblACLSpecialization.setFont(new Font("Helvetica",Font.BOLD,14));
                container.add(lblACLSpecialization);
                
                chkGeneralPhysician.setSize(140, 30);
                chkGeneralPhysician.setLocation(580,190);
                chkGeneralPhysician.setBackground(Color.WHITE);
                container.add(chkGeneralPhysician);
                
                chkGynecologist.setSize(140, 30);
                chkGynecologist.setLocation(740,190);
                chkGynecologist.setBackground(Color.WHITE);
                container.add(chkGynecologist);
                
                chkOphthalmologist.setSize(140, 30);
                chkOphthalmologist.setLocation(580,220);
                chkOphthalmologist.setBackground(Color.WHITE);
                container.add(chkOphthalmologist);
                
                chkDermatologist.setSize(140, 30);
                chkDermatologist.setLocation(740,220);
                chkDermatologist.setBackground(Color.WHITE);
                container.add(chkDermatologist);
                
                chkENTDoctor.setSize(140, 30);
                chkENTDoctor.setLocation(580,250);
                chkENTDoctor.setBackground(Color.WHITE);
                container.add(chkENTDoctor);
                
                chkOrthopedic.setSize(140, 30);
                chkOrthopedic.setLocation(740,250);
                chkOrthopedic.setBackground(Color.WHITE);
                container.add(chkOrthopedic);
                
                chkCardiologist.setSize(140, 30);
                chkCardiologist.setLocation(580,280);
                chkCardiologist.setBackground(Color.WHITE);
                container.add(chkCardiologist);
                
                chkNeurologist.setSize(140, 30);
                chkNeurologist.setLocation(740,280);
                chkNeurologist.setBackground(Color.WHITE);
                container.add(chkNeurologist);
                
                chkDentist.setSize(140, 30);
                chkDentist.setLocation(580,310);
                chkDentist.setBackground(Color.WHITE);
                container.add(chkDentist);
                
                chkUrologist.setSize(140, 30);
                chkUrologist.setLocation(740,310);
                chkUrologist.setBackground(Color.WHITE);
                container.add(chkUrologist);
                
                chkPsychiatrist.setSize(140, 30);
                chkPsychiatrist.setLocation(580,340);
                chkPsychiatrist.setBackground(Color.WHITE);
                container.add(chkPsychiatrist);
                
                chkPathologist.setSize(140, 30);
                chkPathologist.setLocation(740,340);
                chkPathologist.setBackground(Color.WHITE);
                container.add(chkPathologist);
                
                chkRadiologists.setSize(140, 30);
                chkRadiologists.setLocation(580,370);
                chkRadiologists.setBackground(Color.WHITE);
                container.add(chkRadiologists);
                
                chkAnesthesiologist.setSize(140, 30);
                chkAnesthesiologist.setLocation(740,370);
                chkAnesthesiologist.setBackground(Color.WHITE);
                container.add(chkAnesthesiologist);
                
                chkGeneralSurgeon.setSize(140, 30);
                chkGeneralSurgeon.setLocation(580,400);
                chkGeneralSurgeon.setBackground(Color.WHITE);
                container.add(chkGeneralSurgeon);
                
                chkOncologist.setSize(140, 30);
                chkOncologist.setLocation(740,400);
                chkOncologist.setBackground(Color.WHITE);
                container.add(chkOncologist);
                
                chkNephrologist.setSize(140, 30);
                chkNephrologist.setLocation(580,430);
                chkNephrologist.setBackground(Color.WHITE);
                container.add(chkNephrologist);
                
                chkEndocrinologist.setSize(140, 30);
                chkEndocrinologist.setLocation(740,430);
                chkEndocrinologist.setBackground(Color.WHITE);
                container.add(chkEndocrinologist);
                
                chkGastrologist.setSize(140, 30);
                chkGastrologist.setLocation(580,460);
                chkGastrologist.setBackground(Color.WHITE);
                container.add(chkGastrologist);
                
                chkPediatrician.setSize(140, 30);
                chkPediatrician.setLocation(740,460);
                chkPediatrician.setBackground(Color.WHITE);
                container.add(chkPediatrician);
                
                chkVeterinary.setSize(140, 30);
                chkVeterinary.setLocation(580,490);
                chkVeterinary.setBackground(Color.WHITE);
                container.add(chkVeterinary);
                
                if(UserSettings.getLoginStatus())
                {
                    lblName.setText(Data.getFullUserName());
                    lblSpecialization.setText(Data.getDoctorSpecialization());
                    lblCountry.setText(Data.getDoctorCountry());
                }
                
                this.setVisible(true);
    }
   public void actionPerformed(ActionEvent e)
   {
       if(e.getSource()==btnBrowse)
       {
           int returnVal = fileDialog.showOpenDialog(this);
           if (returnVal == JFileChooser.APPROVE_OPTION) {
               File file = fileDialog.getSelectedFile();
               txtFileName.setText(file.getName());
               
               try
               {
                   BufferedReader reader = new BufferedReader(new FileReader(file));
                   String line=reader.readLine();
                   txtFileContent.setText("");
                   while(line!=null)
                   {
                       txtFileContent.append(line+"\n");
                       line=reader.readLine();
                   }
                   reader.close();
               }catch(Exception ex)
               {
                   System.err.println(ex.getMessage());
               }
               
            }
       }
       else if(e.getSource()==btnUpload)
       {
           String sData=txtFileContent.getText().trim();
           String sFileName=txtFileName.getText().trim();
           ArrayList bList=new ArrayList();
           ArrayList sList=new ArrayList();
           
           if(chkIndia.isSelected())
           {
               bList.add("India");
           }
           if(chkBrazil .isSelected())
           {
               bList.add("Brazil");
           }
           if(chkRussia .isSelected())
           {
               bList.add("Russia");
           }
           if(chkChina .isSelected())
           {
               bList.add("China");
           }
           if(chkSouthAfrica .isSelected())
           {
               bList.add("South Africa");
           }
           
           
           if(chkGeneralPhysician.isSelected())
           {
               sList.add("General Physician");
           }
           if(chkGynecologist .isSelected())
           {
               sList.add("Gynecologist");
           }
           if(chkPediatrician .isSelected())
           {
               sList.add("Pediatrician");
           }
           if(chkOphthalmologist.isSelected())
           {
               sList.add("Ophthalmologist");
           }
           if(chkDermatologist .isSelected())
           {
               sList.add("Dermatologist");
           }
           if(chkENTDoctor .isSelected())
           {
               sList.add("ENT Doctor");
           }
           if(chkOrthopedic .isSelected())
           {
               sList.add("Orthopedic");
           }
           if(chkCardiologist .isSelected())
           {
               sList.add("Cardiologist");
           }
           if(chkNeurologist .isSelected())
           {
               sList.add("Neurologist");
           }
           if(chkDentist .isSelected())
           {
               sList.add("Dentist");
           }
           if(chkUrologist .isSelected())
           {
               sList.add("Urologist");
           }
           if(chkPsychiatrist .isSelected())
           {
               sList.add("Psychiatrist");
           }
           if(chkPathologist .isSelected())
           {
               sList.add("Pathologist");
           }
           if(chkRadiologists .isSelected())
           {
               sList.add("Radiologists");
           }
           if(chkAnesthesiologist .isSelected())
           {
               sList.add("Anesthesiologist");
           }
           if(chkGeneralSurgeon .isSelected())
           {
               sList.add("General Surgeon");
           }
           if(chkOncologist .isSelected())
           {
               sList.add("Oncologist");
           }
           if(chkNephrologist .isSelected())
           {
               sList.add("Nephrologist");
           }
           if(chkEndocrinologist .isSelected())
           {
               sList.add("Endocrinologist");
           }
           if(chkGastrologist .isSelected())
           {
               sList.add("Gastrologist");
           }
           if(chkVeterinary .isSelected())
           {
               sList.add("Veterinary");
           }
          
           CPABE cpabe=new CPABE(this,sFileName,sData,bList,sList);
           if(cpabe.encrypt())
           {
               JOptionPane.showMessageDialog(parent, "Resource Encrypted and Saved Successfully");
               dispose();
           }
           else
           {
               JOptionPane.showMessageDialog(parent, "Error Occured");
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
