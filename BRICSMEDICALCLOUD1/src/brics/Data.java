/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package brics;

import java.sql.*;
import javax.swing.*;
import java.util.*;
/**
 *
 * 
 */
public class Data {
     public static Connection getConnection() throws SQLException, ClassNotFoundException
    {

        Class.forName("com.mysql.jdbc.Driver");
         Connection connect = DriverManager.getConnection(CloudSettings.getConnectionString(), CloudSettings.getUserName(), CloudSettings.getPassword());
        
        return connect;

    }
     public static boolean saveResource(String resourceName,String fileContent)
    {
        boolean flag=false;
        try
        {
            byte[] bContent=Encryption.encrypt(fileContent,UserSettings.getLoginUser());
            Connection cn=getConnection();
           
            //String sql="INSERT INTO RESOURCE (RESOURCENAME,RESOURCECONTENT,RESOURCEOWNER) VALUES('"+resourceName.trim()+"','"+bContent+"','"+UserSettings.getLoginUser()+"')";
            String sql="INSERT INTO resource(RESOURCENAME,RESOURCECONTENT,RESOURCEOWNER) VALUES(?,?,?)";
            PreparedStatement st=cn.prepareStatement(sql);
            st.setString(1, resourceName.trim());
            st.setBytes(2, bContent);
            st.setString(3, UserSettings.getLoginUser());
            st.execute();
            st.close();
            cn.close();
            flag=true;
        }catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        return flag;
    }
    public static ArrayList getResources()
    {
        ArrayList resList=new ArrayList();
        try
        {
            Connection cn=getConnection();
            Statement st=cn.createStatement();
            ResultSet rs=st.executeQuery("SELECT RESOURCENAME FROM resource");
            while(rs.next())
            {
                resList.add(rs.getString("RESOURCENAME"));
            }
            cn.close();
        }catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        
        return resList;
    }
    public static boolean isResourceExist(String resourceName)
    {
        boolean flag=false;
        try
        {
            Connection cn=getConnection();
            Statement st=cn.createStatement();
            String sql="SELECT * FROM  resource WHERE TRIM(RESOURCENAME)='"+resourceName.trim()+"' AND TRIM(RESOURCEOWNER)='"+UserSettings.getLoginUser().trim()+"'";
            ResultSet rs=st.executeQuery(sql);
            if(rs.next())
            {
                
                
                flag=true;
            }
        }catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        return flag;
    }
    public static int getResourceID(String resourceName)
    {
        int resourceID=0;
        try
        {
            Connection cn=getConnection();
            Statement st=cn.createStatement();
            String sql="SELECT RESOURCEID FROM  resource WHERE TRIM(RESOURCENAME)='"+resourceName.trim()+"' AND TRIM(RESOURCEOWNER)='"+UserSettings.getLoginUser().trim()+"'";
            ResultSet rs=st.executeQuery(sql);
            if(rs.next())
            {
                resourceID=rs.getInt("RESOURCEID");
            }
            rs.close();
            cn.close();
            
        }catch(Exception ex)
        {
            System.err.println(ex.getMessage());
        }
                
       return resourceID; 
    }
    public static int getResourceIDByName(String resourceName)
    {
        int resourceID=0;
        try
        {
            Connection cn=getConnection();
            Statement st=cn.createStatement();
            String sql="SELECT RESOURCEID FROM  resource WHERE TRIM(RESOURCENAME)='"+resourceName.trim()+"'";
            ResultSet rs=st.executeQuery(sql);
            if(rs.next())
            {
                resourceID=rs.getInt("RESOURCEID");
            }
            rs.close();
            cn.close();
            
        }catch(Exception ex)
        {
            System.err.println(ex.getMessage());
        }
                
       return resourceID; 
    }
     public static String getFullUserName()
     {
         String user=new String();
         try
         {
             Connection cn=getConnection();
             Statement st=cn.createStatement();
             String sql="SELECT FULLNAME FROM userlist WHERE TRIM(USERNAME)='"+UserSettings.getLoginUser() +"' AND TRIM(USERPASS)='"+UserSettings.getLoginPass()+"'";
             ResultSet rs=st.executeQuery(sql);
             if(rs.next())
             {
                 user=rs.getString("FULLNAME");
             }
         }catch(Exception ex)
         {
             System.out.println(ex.getMessage());
         }
         
         return user;
     }
     public static String getDoctorSpecialization()
     {
         String special=new String();
         try
         {
             Connection cn=getConnection();
             Statement st=cn.createStatement();
             String sql="SELECT SPECIALIZATION FROM userlist WHERE TRIM(USERNAME)='"+UserSettings.getLoginUser() +"' AND TRIM(USERPASS)='"+UserSettings.getLoginPass()+"'";
             ResultSet rs=st.executeQuery(sql);
             if(rs.next())
             {
                 special=rs.getString("SPECIALIZATION");
             }
         }catch(Exception ex)
         {
             System.out.println(ex.getMessage());
         }
         
         return special;
     }
     public static String getDoctorCountry()
     {
         String doccountry=new String();
         try
         {
             Connection cn=getConnection();
             Statement st=cn.createStatement();
             String sql="SELECT bricsCOUNTRY FROM userlist WHERE TRIM(USERNAME)='"+UserSettings.getLoginUser() +"' AND TRIM(USERPASS)='"+UserSettings.getLoginPass()+"'";
             ResultSet rs=st.executeQuery(sql);
             if(rs.next())
             {
                 doccountry=rs.getString("bricsCOUNTRY");
             }
         }catch(Exception ex)
         {
             System.out.println(ex.getMessage());
         }
         
         return doccountry;
     }
     public static void saveUserInfo(String fullName,String userName,String userPass,int regNo,int regYear,String medCouncil,String country,String special)
     {
         try
         {
             Connection cn=getConnection();
             Statement st=cn.createStatement();
             String sql="INSERT INTO userlist(FULLNAME,USERNAME,USERPASS,REGNO,REGYEAR,MEDICALCOUNCIL,bricsCOUNTRY,SPECIALIZATION) VALUES('"+fullName.trim()+"','"+userName.trim()+"','"+userPass.trim()+"',"+regNo+","+regYear+",'"+medCouncil.trim()+"','"+country.trim()+"','"+special.trim()+"')";
             st.executeUpdate(sql);
             st.close();
             cn.close();
         }catch(Exception ex)
         {
             System.out.println(ex.getMessage());
         }
     }
     public static boolean isUserExist(String user)
     {
         boolean flag=false;
         try
         {
             Connection cn=getConnection();
             Statement st=cn.createStatement();
             String sql="SELECT USERNAME FROM userlist WHERE TRIM(USERNAME)='"+user.trim()+"'";
             ResultSet rs=st.executeQuery(sql);
             if(rs.next())
             {
                 flag=true;
             }
             st.close();
             cn.close();
         }catch(Exception ex)
         {
             System.out.println(ex.getMessage());
         }
         
         return flag;
     }
     public static void approveNewUser(String newUser,String sKey)
     {
         try
         {
             Connection cn=getConnection();
             Statement st=cn.createStatement();
             String sql = "UPDATE userlist SET STATUS=1,SECRETKEY='"+sKey+"' WHERE TRIM(USERNAME)='"+newUser.trim()+"'";
             st.executeUpdate(sql);
             st.close();
             cn.close(); 
         }catch(Exception ex)
         {
             System.err.println(ex.getMessage());
         }
     }
     public static ArrayList getNewUsers()
     {
         ArrayList list=new ArrayList();
         try
         {
             Connection cn=getConnection();
             Statement st=cn.createStatement();
             String sql="SELECT USERNAME FROM userlist WHERE ROLE=0 AND STATUS=0";
             ResultSet rs=st.executeQuery(sql);
             while(rs.next())
             {
                 list.add(rs.getString("USERNAME"));
             }
             st.close();
             cn.close();
         }catch(Exception ex)
         {
             System.out.println(ex.getMessage());
         }
         return list;
     }
     public static ArrayList getApprovedUsers()
     {
         ArrayList list=new ArrayList();
         try
         {
             Connection cn=getConnection();
             Statement st=cn.createStatement();
             String sql="SELECT USERNAME FROM userlist WHERE ROLE=0 AND STATUS=1";
             ResultSet rs=st.executeQuery(sql);
             while(rs.next())
             {
                 list.add(rs.getString("USERNAME"));
             }
             st.close();
             cn.close();
         }catch(Exception ex)
         {
             System.out.println(ex.getMessage());
         }
         return list;
     }
     public static void revokeUser(String user)
     {
         try
         {
             Connection cn=getConnection();
             Statement st=cn.createStatement();
             String sql="UPDATE userlist SET STATUS=-1 WHERE TRIM(USERNAME)='"+user.trim()+"'";
             st.executeUpdate(sql);
             st.close();
             cn.close();
             
         }catch(Exception ex)
         {
             System.out.println(ex.getMessage());
         }
         
     }
     public static ArrayList getRevokedUsers()
     {
         ArrayList list=new ArrayList();
         try
         {
             Connection cn=getConnection();
             Statement st=cn.createStatement();
             String sql="SELECT USERNAME FROM userlist WHERE ROLE=0 AND STATUS=-1";
             ResultSet rs=st.executeQuery(sql);
             while(rs.next())
             {
                 list.add(rs.getString("USERNAME"));
             }
             st.close();
             cn.close();
         }catch(Exception ex)
         {
             System.out.println(ex.getMessage());
         }
         return list;
     }
     public static boolean doAdminLogin(String userName,String userpass)
     {
         boolean flag=false;
         try
         {
             Connection cn=getConnection();
             Statement st=cn.createStatement();
             String sql="SELECT * FROM userlist WHERE TRIM(USERNAME)='"+userName.trim()+"' AND TRIM(USERPASS)='"+userpass.trim()+"' AND ROLE=1 AND STATUS=1";
             ResultSet rs=st.executeQuery(sql);
             if(rs.next())
             {
                 flag=true;
             }
             st.close();
             cn.close();
         }catch(Exception ex)
         {
             System.out.println(ex.getMessage());
         }
         return flag;
     }
     public static boolean doChangeAdminPassword(String u,String op,String np)
     {
         boolean flag=false;
         try
         {
             Connection cn=getConnection();
             Statement st=cn.createStatement();
             String sql="UPDATE userlist SET USERPASS='"+np.trim()+"' WHERE TRIM(USERNAME)='"+u.trim()+"' AND TRIM(USERPASS)='"+op.trim()+"'";
            
             if(st.executeUpdate(sql)>0)
             {
                 flag=true;
             }
             st.close();
             cn.close();
         }catch(Exception ex)
         {
             System.err.println(ex.getMessage());
         }
         
         return flag;
     }
     public static boolean doUserLogin(String userName,String userpass)
     {
         boolean flag=false;
         try
         {
             Connection cn=getConnection();
             Statement st=cn.createStatement();
             String sql="SELECT * FROM userlist WHERE TRIM(USERNAME)='"+userName.trim()+"' AND TRIM(USERPASS)='"+userpass.trim()+"' AND ROLE=0 AND STATUS=1";
             ResultSet rs=st.executeQuery(sql);
             if(rs.next())
             {
                 flag=true;
             }
             st.close();
             cn.close();
         }catch(Exception ex)
         {
             System.out.println(ex.getMessage());
         }
         return flag;
     }
     public static String[] getbricsCountries()
     {
         ArrayList list=new ArrayList();
         try
         {
             Connection cn=getConnection();
             Statement st=cn.createStatement();
             String sql="SELECT COUNTRY FROM brics ORDER BY COUNTRY ASC";
             ResultSet rs=st.executeQuery(sql);
             while(rs.next())
             {
                 list.add(rs.getString("COUNTRY"));
             }
             st.close();
             cn.close();
         }catch(Exception ex)
         {
             System.out.println(ex.getMessage());
         }
         String[] s=new String[list.size()];
         for(int i=0;i<list.size();i++)
         {
             s[i]=list.get(i).toString();
         }
         return s;
     }
     
     public static String[] getSpecialization()
     {
         ArrayList list=new ArrayList();
         try
         {
             Connection cn=getConnection();
             Statement st=cn.createStatement();
             String sql="SELECT specialization FROM specialization ORDER BY specialization ASC";
             ResultSet rs=st.executeQuery(sql);
             while(rs.next())
             {
                 list.add(rs.getString("specialization"));
             }
             st.close();
             cn.close();
         }catch(Exception ex)
         {
             System.out.println(ex.getMessage());
         }
         String[] s=new String[list.size()];
         for(int i=0;i<list.size();i++)
         {
             s[i]=list.get(i).toString();
         }
         return s;
     }
     public static void setCountryAttribute(int resID,ArrayList bList)
     {
         try
         {
             Connection cn=getConnection();
             Statement st=cn.createStatement();
             for(int i=0;i<bList.size();i++)
             {
                 String sql="INSERT INTO res_country(RESID,RESCOUNTRY) VALUES("+resID+",'"+bList.get(i).toString()+"')";
                 st.executeUpdate(sql);
             }
             st.close();
             cn.close();
             
         }catch(Exception ex)
         {
             System.out.println(ex.getMessage());
         }
     }
     
     public static void setSpecializationAttribute(int resID,ArrayList sList)
     {
         try
         {
             Connection cn=getConnection();
             Statement st=cn.createStatement();
             for(int i=0;i<sList.size();i++)
             {
                 String sql="INSERT INTO res_special(RESID,RESSPECIAL) VALUES("+resID+",'"+sList.get(i).toString()+"')";
                 st.executeUpdate(sql);
             }
             st.close();
             cn.close();
             
         }catch(Exception ex)
         {
             System.out.println(ex.getMessage());
         }
     }
     public static boolean verifyAttributes(int resourceID,String usrCountry,String usrSpecial)
     {
         boolean flag=false;
         try
         {
             Connection cn=getConnection();
             Statement st=cn.createStatement();
             String cSql="SELECT * FROM res_country WHERE RESID="+resourceID+" AND TRIM(RESCOUNTRY)='"+usrCountry.trim()+"'";
             ResultSet rs=st.executeQuery(cSql);
             if(rs.next())
             {
                 String sSql="SELECT * FROM res_special WHERE RESID="+resourceID+" AND TRIM(RESSPECIAL)='"+usrSpecial.trim()+"'";
                 ResultSet r=st.executeQuery(sSql);
                 if(r.next())
                 {
                     flag=true;
                 }
             }
         }catch(Exception ex)
         {
             System.out.println(ex.getMessage());
         }
         
         return flag;
     }
     
     public static byte[] getResourceContent(int resourceID,String resourceName)
     {
         byte[] bData=null;
         try
         {
             Connection cn=getConnection();
             
             String sql="SELECT RESOURCECONTENT FROM resource WHERE RESOURCEID=? AND TRIM(RESOURCENAME)=?";
             
             PreparedStatement st=cn.prepareStatement(sql);
             st.setInt(1, resourceID);
             st.setString(2, resourceName);
             ResultSet rs=st.executeQuery();
             if(rs.next())
             {
                 //bData=rs.getBytes("RESOURCECONTENT");
                 Blob blob = rs.getBlob("RESOURCECONTENT");
                 int blobLength = (int) blob.length(); 
                 byte[] blobAsBytes = blob.getBytes(1, blobLength);
                 bData=blobAsBytes;
                 blob.free();
             }
         }catch(Exception ex)
         {
             System.err.println(ex.getMessage());
         }
         
         return bData;
     }
     
     public static String getResourceOwner(int resourceID)
     {
         String userName=new String();
         try
         {
             Connection cn=getConnection();
             Statement st=cn.createStatement();
             String sql="SELECT RESOURCEOWNER FROM resource WHERE RESOURCEID="+resourceID;
             ResultSet rs=st.executeQuery(sql);
             if(rs.next())
             {
                 userName=rs.getString("RESOURCEOWNER");
             }
         }catch(Exception ex)
         {
             System.err.println(ex.getMessage());
         }
         
         return userName;
     }
     
     public static boolean doAttributeAgencyLogin(String userName,String userpass,String userAgency)
     {
         boolean flag=false;
         int role=0;
         if(userAgency.trim().equals("Country"))
         {
              role=2;
         }
         else if(userAgency.trim().equals("Specialization"))
         {
              role=3;
         }
         try
         {
             Connection cn=getConnection();
             Statement st=cn.createStatement();
             String sql="SELECT * FROM userlist WHERE TRIM(USERNAME)='"+userName.trim()+"' AND TRIM(USERPASS)='"+userpass.trim()+"' AND ROLE="+role+" AND STATUS=1";
             ResultSet rs=st.executeQuery(sql);
             if(rs.next())
             {
                 flag=true;
             }
             st.close();
             cn.close();
         }catch(Exception ex)
         {
             System.out.println(ex.getMessage());
         }
         return flag;
     }
}
