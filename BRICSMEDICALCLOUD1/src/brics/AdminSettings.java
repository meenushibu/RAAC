/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package brics;

/**
 *
 * @author Anju
 */
public class AdminSettings {
    private static boolean loginStatus=false;
    private static String loginUser=new String();
    private static String loginPass=new String();
    
    public static void setLoginStatus(boolean ls)
    {
        loginStatus=ls;
    }
    public static boolean getLoginStatus()
    {
        return loginStatus;
    }
    public static void setLoginUser(String lu)
    {
        loginUser=lu;
    }
    public static String getLoginUser()
    {
        return loginUser;
    }
    public static void setLoginPass(String lp)
    {
        loginPass=lp;
    }
    public static String getLoginPass()
    {
        return loginPass;
    }
}
