package megatravel.bezbednost.signature;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;

public class SignatureExample {

	public byte[] sign(byte[] data, PrivateKey privateKey) {//copy
		try {
			//Kreiranje objekta koji nudi funkcionalnost digitalnog potpisivanja
			//Prilikom getInstance poziva prosledjujemo algoritam koji cemo koristiti
			//U ovom slucaju cemo generisati SHA-1 hes kod koji cemo potpisati upotrebom RSA asimetricne sifre
			Signature sig = Signature.getInstance("SHA1withRSA");
			//Navodimo kljuc kojim potpisujemo 
			sig.initSign(privateKey);
			//Postavljamo podatke koje potpisujemo
			sig.update(data);
			
			//Vrsimo potpisivanje
			return sig.sign();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (SignatureException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean verify(byte[] data, byte[] signature, PublicKey publicKey) {//copy
		try {
			//Kreiranje objekta koji nudi funkcionalnost digitalnog potpisivanja
			//Prilikom getInstance poziva prosledjujemo algoritam koji cemo koristiti
			//U ovom slucaju cemo generisati SHA-1 hes kod koji cemo potpisati upotrebom RSA asimetricne sifre
			Signature sig = Signature.getInstance("SHA1withRSA");
			//Navodimo kljuc sa kojim proveravamo potpis 
			sig.initVerify(publicKey);
			//Postavljamo podatke koje potpisujemo
			sig.update(data);
			
			//Vrsimo proveru digitalnog potpisa
			return sig.verify(signature);
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (SignatureException e) {
			e.printStackTrace();
		}
		return false;
	}
	
}
