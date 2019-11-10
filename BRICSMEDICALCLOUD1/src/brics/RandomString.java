/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package brics;
import java.util.Random;
/**
 *
 * @author Anju
 */
public class RandomString {
    
    public static StringBuffer sb=new StringBuffer();
    public static String str="ABCDEFGHIJKLMNOPQRATUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    public static String getRandomString()
    {
        Random randomGenerator = new Random();
        for(int i=0;i<8;i++)
        {
         sb.append(str.charAt(randomGenerator.nextInt(str.length())));
        }
        
        return sb.toString();
    }
    
   
}
