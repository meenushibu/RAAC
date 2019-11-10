/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package brics;

/**
 *
 * @author Anju
 */
public class CloudSettings {
    
    private static int serverPort=4445;
    private static String serverIP=new String("127.0.0.1");
    
    private static boolean isOnline=true;
    private static String remoteServer=new String("MYCLOUDBOX [REMOTE]");
    private static String localServer=new String("LOCALHOST [LOCAL]");
    private static String[] availableServers={remoteServer,localServer};
    public static void setOnline(boolean oStatus)
    {
        isOnline=oStatus;
    }
    public static boolean getOnline()
    {
        return isOnline;
    }
    public static String getLocal()
    {
        return localServer;
    }
    public static String getRemote()
    {
        return remoteServer;
    }
    public static String[] getAvalilableServrs()
    {
        return availableServers;
    }
    public static String getCurrentServer()
    {
        if(isOnline)
        {
            return remoteServer;
        }
        else
        {
             return localServer;
        }
    }
    public static String getConnectionString()
    {
        if(isOnline)
        {
            return new String("jdbc:mysql://cloudsql.mycloudbox.info:3306/raac");
        }
        else
        {
             return new String("jdbc:mysql://localhost:3306/raac");
        }
    }
    public static String getUserName()
    {
         if(isOnline)
        {
            return new String("raac");
        }
        else
        {
             return new String("root");
        }
    }
    public static String getPassword()
    {
         if(isOnline)
        {
            return new String("raacraac");
        }
        else
        {
             return new String("");
        }
    }
    
    public static int getServerPort()
    {
        return serverPort;
    }
    public static String getServerIP()
    {
        return serverIP;
    }
    
}
