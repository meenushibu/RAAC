/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package brics;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.*;
import sun.misc.*;
import javax.crypto.spec.SecretKeySpec;
/**
 *
 * @author Anju
 */
public class TheBestSecretKey
{
    private static final String ALGO = "AES";
    private static  byte[] keyValue = new byte[] { 'T', 'h', 'e', 'B', 'e', 's', 't','S', 'e', 'c', 'r','e', 't', 'K', 'e', 'y' };
    private static void setKeyValue(String kValue)
    {
        keyValue=kValue.getBytes();
    }
    public static String encrypt(String Data) throws Exception {
        Key key = generateKey();
        Cipher c = Cipher.getInstance(ALGO);
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] encVal = c.doFinal(Data.getBytes());
        String encryptedValue = new BASE64Encoder().encode(encVal);
        return encryptedValue;
    }
    public static String decrypt(String encryptedData) throws Exception {
        Key key = generateKey();
        Cipher c = Cipher.getInstance(ALGO);
        c.init(Cipher.DECRYPT_MODE, key);
        byte[] decordedValue = new BASE64Decoder().decodeBuffer(encryptedData);
        byte[] decValue = c.doFinal(decordedValue);
        String decryptedValue = new String(decValue);
        return decryptedValue;
    }
    private static Key generateKey() throws Exception
    {
        Key key = new SecretKeySpec(keyValue, ALGO);
        return key;
    }
    public static void main(String[] args)
    {
        try {
            
            System.out.println(encrypt("Hello World, I love you god for this"));
            System.out.println(decrypt("EaekT/SzVASbcfH7V9jiedlI0nbL4EEte/iM5HE5Y/JprOWTvt+sJiZFYxP2p3d4"));
        } catch (Exception ex) {
            Logger.getLogger(TheBestSecretKey.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
