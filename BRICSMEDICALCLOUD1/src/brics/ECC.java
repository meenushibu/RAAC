/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package brics;
import java.io.*;
import java.security.*;
import java.security.spec.*;
import javax.swing.*;
/**
 *
 * @author Anju
 */
public class ECC {
    private String user=new String();
    private String path = new String("keystore");
    private JTextArea txtConsole;
    public ECC(String user,JTextArea txtConsole)
    {
        this.user=user;
        this.txtConsole=txtConsole;
        try {
			
 
			KeyPairGenerator keyGen = KeyPairGenerator.getInstance("EC");//use ecc
 
			keyGen.initialize(256);
			KeyPair generatedKeyPair = keyGen.genKeyPair();
 
			//System.out.println("Generated Key Pair");
                        txtConsole.append("\nGenerated Key Pair");
			dumpKeyPair(generatedKeyPair);
			SaveKeyPair(path, generatedKeyPair);
 
			KeyPair loadedKeyPair = LoadKeyPair(path, "EC");
			//System.out.println("Loaded Key Pair");
                        txtConsole.append("\nLoaded Key Pair");
			dumpKeyPair(loadedKeyPair);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
    }
    public static void main(String args[]) {
		//ECC ecc = new ECC("boss");
		
	}
 
	private void dumpKeyPair(KeyPair keyPair) {
		PublicKey pub = keyPair.getPublic();
		//System.out.println("Public Key: " + getHexString(pub.getEncoded()));
                txtConsole.append("\nPublic Key: " + getHexString(pub.getEncoded()));
 
		PrivateKey priv = keyPair.getPrivate();
		//System.out.println("Private Key: " + getHexString(priv.getEncoded()));
                txtConsole.append("\nPrivate Key: " + getHexString(priv.getEncoded()));
	}
 
	private String getHexString(byte[] b) {
		String result = "";
		for (int i = 0; i < b.length; i++) {
			result += Integer.toString((b[i] & 0xff) + 0x100, 16).substring(1);
		}
		return result;
	}
 
	public void SaveKeyPair(String path, KeyPair keyPair) throws IOException {
		PrivateKey privateKey = keyPair.getPrivate();
		PublicKey publicKey = keyPair.getPublic();
 
		// Store Public Key.
		X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(
				publicKey.getEncoded());
		FileOutputStream fos = new FileOutputStream(path + "/"+user.trim()+"_public.key");
		fos.write(x509EncodedKeySpec.getEncoded());
		fos.close();
 
		// Store Private Key.
		PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(
                privateKey.getEncoded());
		fos = new FileOutputStream(path + "/"+user.trim()+"_private.key");
		fos.write(pkcs8EncodedKeySpec.getEncoded());
		fos.close();
	}
 
	public KeyPair LoadKeyPair(String path, String algorithm)
			throws IOException, NoSuchAlgorithmException,
			InvalidKeySpecException {
		// Read Public Key.
		File filePublicKey = new File(path + "/"+user.trim()+"_public.key");
		FileInputStream fis = new FileInputStream(path + "/"+user.trim()+"_public.key");
		byte[] encodedPublicKey = new byte[(int) filePublicKey.length()];
		fis.read(encodedPublicKey);
		fis.close();
 
		// Read Private Key.
		File filePrivateKey = new File(path + "/"+user.trim()+"_private.key");
		fis = new FileInputStream(path + "/"+user.trim()+"_private.key");
		byte[] encodedPrivateKey = new byte[(int) filePrivateKey.length()];
		fis.read(encodedPrivateKey);
		fis.close();
 
		// Generate KeyPair.
		KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
		X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(
				encodedPublicKey);
		PublicKey publicKey = keyFactory.generatePublic(publicKeySpec);
 
		PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(
				encodedPrivateKey);
		PrivateKey privateKey = keyFactory.generatePrivate(privateKeySpec);
 
		return new KeyPair(publicKey, privateKey);
	}
}
