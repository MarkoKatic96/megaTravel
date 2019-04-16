package megatravel.bezbednost.ecc;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Security;

import javax.crypto.Cipher;

import org.bouncycastle.jce.ECNamedCurveTable;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.spec.ECNamedCurveParameterSpec;

public class ECCExample {
	
	public ECCExample() {
		//TODO: Procitati analizu SunEC providera http://www.academicpub.org/PaperInfo.aspx?PaperID=14496 i istraziti alternative
		Security.addProvider(new BouncyCastleProvider());
	}

	/*KeyPair kp = generateKeys();
	PublicKey publicKey = kp.getPublic();
	PrivateKey privateKey = kp.getPrivate();*/

	private KeyPair generateKeys() {
		ECNamedCurveParameterSpec spec = ECNamedCurveTable.getParameterSpec("prime256v1");
		try 
		{
		    KeyPairGenerator g = KeyPairGenerator.getInstance("ECDH",BouncyCastleProvider.PROVIDER_NAME);
		    g.initialize(spec, new SecureRandom());
		    KeyPair keyPair = g.generateKeyPair();
		    PrivateKey privateKey = keyPair.getPrivate();
		    PublicKey publicKey = keyPair.getPublic();
		    //System.out.println("PublicKey:"+publicKey+"\n");
		    //System.out.println("PrivateKey:"+privateKey+"\n");
		    return keyPair;	
		} 
		catch (Exception e) 
		{
		    e.printStackTrace();
		    return null;
		}
		
	}
	
	private byte[] encrypt(String plainText, PublicKey key) {
		 try {
			    Cipher c = Cipher.getInstance("ECIES",BouncyCastleProvider.PROVIDER_NAME);
			    c.init(Cipher.ENCRYPT_MODE,key);
			    byte[] encodeBytes = c.doFinal(plainText.getBytes());
			    return encodeBytes;
			} catch (Exception e) {
			    e.printStackTrace();
			    return null;
			}
	}
	
	private byte[] decrypt(byte[] cipherText, PrivateKey key) {
		try
        {

            Cipher c = Cipher.getInstance("ECIES","BC");
            c.init(Cipher.DECRYPT_MODE,key);
            byte[] decodeBytes = c.doFinal(cipherText);
            
            return decodeBytes;
            
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
	}

}
