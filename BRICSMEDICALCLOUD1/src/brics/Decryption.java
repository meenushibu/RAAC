/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package brics;
import java.io.File;
import java.io.FileInputStream;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.security.spec.ECParameterSpec;
import java.security.spec.EllipticCurve;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;
import javax.crypto.KeyAgreement;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
/**
 *
 * @author Anju
 */
public class Decryption {
  
 
 
 public static String decrypt(byte[] cypher,String userName)
 {
     KeyPairGenerator kpg;
  EllipticCurve curve;
  ECParameterSpec ecSpec;
  KeyPair aKeyPair;
  KeyAgreement aKeyAgree;
  KeyPair bKeyPair;
  KeyAgreement bKeyAgree;
  KeyFactory keyFac; 
  Security.addProvider(new BouncyCastleProvider());
  String text=new String();
  
  try{
   
   
   
   File filePublicKey = new File("keystore/"+userName.trim()+"_public.key");
   FileInputStream fis = new FileInputStream(filePublicKey);
   byte[] encodedPublicKey = new byte[(int) filePublicKey.length()];
   fis.read(encodedPublicKey);
   fis.close();
  
   // Read Private Key.
   File filePrivateKey = new File("keystore/"+userName.trim()+"_private.key");
   fis = new FileInputStream(filePrivateKey);
   byte[] encodedPrivateKey = new byte[(int) filePrivateKey.length()];
   fis.read(encodedPrivateKey);
   fis.close();
  
   // Generate KeyPair.
   KeyFactory keyFactory = KeyFactory.getInstance("ECDH");
   X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(
     encodedPublicKey);
   PublicKey publicKey = keyFactory.generatePublic(publicKeySpec);
  
   PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(
     encodedPrivateKey);
   PrivateKey privateKey = keyFactory.generatePrivate(privateKeySpec);   
   
   
   
      aKeyAgree = KeyAgreement.getInstance("ECDH", "BC");
      aKeyAgree.init(privateKey);
      aKeyAgree.doPhase(publicKey, true);     
   
 
      byte[] aBys = aKeyAgree.generateSecret(); 
      KeySpec aKeySpec = new DESKeySpec(aBys);
      SecretKeyFactory aFactory = SecretKeyFactory.getInstance("DES");
      Key aSecretKey = aFactory.generateSecret(aKeySpec);

      Cipher aCipher = Cipher.getInstance(aSecretKey.getAlgorithm());   
      aCipher.init(Cipher.DECRYPT_MODE, aSecretKey);  
     byte[] decText = aCipher.doFinal(cypher);
     text = new String(decText);
      
      //System.out.println("Decoded="+text);
     
   
  }
  catch(Exception e)
  {
   e.printStackTrace();
  }
  return text;
 }  
}
