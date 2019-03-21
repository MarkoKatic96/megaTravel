package megatravel.bezbednost.rsa;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class RSAExample {
	
	public byte[] encrypt(String plainText, PublicKey key) {
		try {			
			//Kada se definise sifra potrebno je navesti njenu konfiguraciju, sto u ovom slucaju ukljucuje:
			//	- Algoritam koji se koristi (RSA)
			//	- Rezim rada tog algoritma (ECB)
			//	- Strategija za popunjavanje poslednjeg bloka (PKCS1Padding)
			//	- Provajdera kriptografskih funckionalnosti (BC)
			Cipher rsaCipherEnc = Cipher.getInstance("RSA/ECB/PKCS1Padding", "BC");
			//inicijalizacija za sifrovanje
			rsaCipherEnc.init(Cipher.ENCRYPT_MODE, key);
			
			//sifrovanje
			byte[] cipherText = rsaCipherEnc.doFinal(plainText.getBytes());
			return cipherText;
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public byte[] decrypt(byte[] cipherText, PrivateKey key) {
		try {			
			Cipher rsaCipherDec = Cipher.getInstance("RSA/ECB/PKCS1Padding", "BC");
			//inicijalizacija za desifrovanje
			rsaCipherDec.init(Cipher.DECRYPT_MODE, key);
			
			//desifrovanje
			byte[] plainText = rsaCipherDec.doFinal(cipherText);
			return plainText;
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return null;
	}

}
