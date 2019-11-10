/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package brics;
import javax.swing.*;
import java.util.*;

/**
 *
 * @author Anju
 */
public class CPABE {
    private String sFileName;
    private String sData;
    private ArrayList bList;
    private ArrayList sList;
    private UploadResourceForm parent;
    public CPABE(UploadResourceForm parent,String sFileName,String sData,ArrayList bList,ArrayList sList)
    {
        this.sFileName=sFileName;
        this.sData=sData;
        this.bList=bList;
        this.sList=sList;
        this.parent=parent;
    }
    public boolean encrypt()
    {
        boolean flag=false;
        if(Data.isResourceExist(sFileName))
        {
            JOptionPane.showMessageDialog(parent, "Resource Already Existes !!!");
        }
        else
        {
            Data.saveResource(sFileName, sData);
            int resourceID=Data.getResourceID(sFileName);
            Data.setCountryAttribute(resourceID, bList);
            Data.setSpecializationAttribute(resourceID, sList);
            flag=true;
            
            
        }
        return flag;      
    }
    public static String decript(int resourceID,String resourceName)
    {
        String resourceOwner=Data.getResourceOwner(resourceID);
        byte[] bContent=Data.getResourceContent(resourceID, resourceName);
        
        String sContent=Decryption.decrypt(bContent, resourceOwner);
        
        return sContent;
    }
    
}
