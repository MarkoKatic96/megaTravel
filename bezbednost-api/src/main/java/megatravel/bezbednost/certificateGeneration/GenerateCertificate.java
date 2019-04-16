package megatravel.bezbednost.certificateGeneration;

import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Security;
import java.security.SignatureException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x500.X500NameBuilder;
import org.bouncycastle.asn1.x500.style.BCStyle;
import org.bouncycastle.cert.jcajce.JcaX509CertificateHolder;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import megatravel.bezbednost.data.IssuerData;
import megatravel.bezbednost.data.SubjectData;
import megatravel.bezbednost.keyStore.KeyStoreReader;
import megatravel.bezbednost.model.CertifikatAplikacija;
import megatravel.bezbednost.model.CertifikatDomen;
import megatravel.bezbednost.model.CertifikatOprema;
import megatravel.bezbednost.model.CertifikatOrganizacija;
import megatravel.bezbednost.model.CertifikatOsoba;
import megatravel.bezbednost.model.CertifikatRoot;
import megatravel.bezbednost.model.TipCertifikata;

public class GenerateCertificate {
	
	public GenerateCertificate() {
		Security.addProvider(new BouncyCastleProvider());
	}
	
	public boolean verifyCertificate(X509Certificate certifikat, PublicKey publicKey) {
		try {
			certifikat.verify(publicKey);
			return true;
		} catch (InvalidKeyException | CertificateException | NoSuchAlgorithmException | NoSuchProviderException | SignatureException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	
	public KeyPair generateKeyPair() {
        try {
			KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA"); 
			SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");
			keyGen.initialize(2048, random);
			return keyGen.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		}
        return null;
	}
	
	public X509Certificate generateCertificate(X509Certificate nadcertifikat, TipCertifikata tipNadcertifikata, SubjectData subjectData, KeyPair keyPair) throws InvalidKeyException, CertificateException, NoSuchAlgorithmException, NoSuchProviderException, SignatureException {

		//iz KeyStora uzimam privatni kljuc od nadcertifikata
		KeyStoreReader ksr = new KeyStoreReader();
		PrivateKey privateKey = ksr.readPrivateKey("./files/repository/keystores/"+ tipNadcertifikata.toString() +".jks", "secretpassword", nadcertifikat.getSerialNumber().toString(), "secretpassword");
		if (privateKey==null) {
			privateKey = generateKeyPair().getPrivate();
		}
		IssuerData issuerData;
		try {
			issuerData = generateIssuerData(privateKey, nadcertifikat);
		} catch (CertificateEncodingException e) {
			issuerData = new IssuerData(privateKey, new X500Name(nadcertifikat.getIssuerX500Principal().getName()));
		}
	    
		//Generise se sertifikat za subjekta, potpisan od strane issuer-a
		CertificateGenerator cg = new CertificateGenerator();
		X509Certificate cert = cg.generateCertificate(subjectData, issuerData);
		
		//provera validnosti potpisa
		try{
			cert.verify(nadcertifikat.getPublicKey());
		}catch (Exception e) {}

		//skladistenje certifikata u bazi i kljuca u keystoru je ostavljeno za pozivaoca metode
		
		return cert;
	}
	
	public X509Certificate generateSelfSignedCertificate(SubjectData subjectData, KeyPair keyPair) throws InvalidKeyException, CertificateException, NoSuchAlgorithmException, NoSuchProviderException, SignatureException {

		//generisem Issuer-a od subjectData i keyPair-a
		IssuerData issuerData = new IssuerData(keyPair.getPrivate(), subjectData.getX500name());
	    
		//Generise se sertifikat za subjekta, potpisan od strane issuer-a
		CertificateGenerator cg = new CertificateGenerator();
		X509Certificate cert = cg.generateCertificate(subjectData, issuerData);
		
		//provera validnosti potpisa
		try{
			cert.verify(keyPair.getPublic());
		}catch (Exception e) {}

		//skladistenje certifikata u bazi i kljuca u keystoru je ostavljeno za pozivaoca metode
		
		return cert;
	}

	public IssuerData generateIssuerData(PrivateKey issuerKey, X509Certificate nadcertifikat) throws CertificateEncodingException {
		X500Name issuerName = new JcaX509CertificateHolder((X509Certificate) nadcertifikat).getSubject();
		return new IssuerData(issuerKey, issuerName);
	}

	public SubjectData generateAppData(CertifikatAplikacija certifikat) {
		try {
			KeyPair keyPairSubject = generateKeyPair();
			
			//Datumi od kad do kad vazi sertifikat
			SimpleDateFormat iso8601Formater = new SimpleDateFormat("yyyy-MM-dd");
			Date startDate = iso8601Formater.parse(certifikat.getPocetak().toString());
			Date endDate = iso8601Formater.parse(certifikat.getKraj().toString());
			if (startDate.compareTo(endDate)>=0) {
				throw new ParseException("Pocetak vazenja certifikata mora biti pre isteka vazenja istog!", 0);
			}
			
			//Serijski broj sertifikata
			String sn = getRandomBigInteger();
			//klasa X500NameBuilder pravi X500Name objekat koji predstavlja podatke o vlasniku
			X500NameBuilder builder = new X500NameBuilder(BCStyle.INSTANCE);
			builder.addRDN(BCStyle.CN, certifikat.getNazivAplikacije());
			builder.addRDN(BCStyle.O, certifikat.getOrganizacija());
		    builder.addRDN(BCStyle.UID, certifikat.getVerzija());
		    
		    //Kreiraju se podaci za sertifikat, sto ukljucuje:
		    // - javni kljuc koji se vezuje za sertifikat
		    // - podatke o vlasniku
		    // - serijski broj sertifikata
		    // - od kada do kada vazi sertifikat
		    return new SubjectData(keyPairSubject.getPublic(), builder.build(), sn, startDate, endDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public SubjectData generateRootData(CertifikatRoot certifikat) {
		try {
			KeyPair keyPairSubject = generateKeyPair();
			
			//Datumi od kad do kad vazi sertifikat
			SimpleDateFormat iso8601Formater = new SimpleDateFormat("yyyy-MM-dd");
			Date startDate = iso8601Formater.parse(certifikat.getPocetak().toString());
			Date endDate = iso8601Formater.parse(certifikat.getKraj().toString());
			if (startDate.compareTo(endDate)>=0) {
				throw new ParseException("Pocetak vazenja certifikata mora biti pre isteka vazenja istog!", 0);
			}
			
			//Serijski broj sertifikata
			String sn = getRandomBigInteger();
			//klasa X500NameBuilder pravi X500Name objekat koji predstavlja podatke o vlasniku
			X500NameBuilder builder = new X500NameBuilder(BCStyle.INSTANCE);
		    builder.addRDN(BCStyle.O, certifikat.getOrganizacija());

		    
		    //Kreiraju se podaci za sertifikat, sto ukljucuje:
		    // - javni kljuc koji se vezuje za sertifikat
		    // - podatke o vlasniku
		    // - serijski broj sertifikata
		    // - od kada do kada vazi sertifikat
		    return new SubjectData(keyPairSubject.getPublic(), builder.build(), sn, startDate, endDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public SubjectData generateDomenData(CertifikatDomen certifikat) {
		try {
			KeyPair keyPairSubject = generateKeyPair();
			
			//Datumi od kad do kad vazi sertifikat
			SimpleDateFormat iso8601Formater = new SimpleDateFormat("yyyy-MM-dd");
			Date startDate = iso8601Formater.parse(certifikat.getPocetak().toString());
			Date endDate = iso8601Formater.parse(certifikat.getKraj().toString());
			if (startDate.compareTo(endDate)>=0) {
				throw new ParseException("Pocetak vazenja certifikata mora biti pre isteka vazenja istog!", 0);
			}
			
			//Serijski broj sertifikata
			String sn = getRandomBigInteger();
			//klasa X500NameBuilder pravi X500Name objekat koji predstavlja podatke o vlasniku
			X500NameBuilder builder = new X500NameBuilder(BCStyle.INSTANCE);
		    builder.addRDN(BCStyle.CN, certifikat.getHttps());
		    builder.addRDN(BCStyle.O, certifikat.getOrganizacija());
		    //Kreiraju se podaci za sertifikat, sto ukljucuje:
		    // - javni kljuc koji se vezuje za sertifikat
		    // - podatke o vlasniku
		    // - serijski broj sertifikata
		    // - od kada do kada vazi sertifikat
		    return new SubjectData(keyPairSubject.getPublic(), builder.build(), sn, startDate, endDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public SubjectData generateOpremaData(CertifikatOprema certifikat) {
		
		try {
			KeyPair keyPairSubject = generateKeyPair();
			
			//Datumi od kad do kad vazi sertifikat
			SimpleDateFormat iso8601Formater = new SimpleDateFormat("yyyy-MM-dd");
			Date startDate = iso8601Formater.parse(certifikat.getPocetak().toString());
			Date endDate = iso8601Formater.parse(certifikat.getKraj().toString());
			if (startDate.compareTo(endDate)>=0) {
				throw new ParseException("Pocetak vazenja certifikata mora biti pre isteka vazenja istog!", 0);
			}
			
			//Serijski broj sertifikata
			String sn = getRandomBigInteger();
			//klasa X500NameBuilder pravi X500Name objekat koji predstavlja podatke o vlasniku
			X500NameBuilder builder = new X500NameBuilder(BCStyle.INSTANCE);
		    builder.addRDN(BCStyle.SN, certifikat.getMAC());
		    builder.addRDN(BCStyle.NAME, certifikat.getNazivOpreme());
		    builder.addRDN(BCStyle.O, certifikat.getOrganizacija());
		    builder.addRDN(BCStyle.OU, certifikat.getSuborganizacija());
		    builder.addRDN(BCStyle.C, certifikat.getDrzava());
		    //UID (USER ID) je ID opreme
		    builder.addRDN(BCStyle.UID, certifikat.getIdOpreme());
		    
		    //Kreiraju se podaci za sertifikat, sto ukljucuje:
		    // - javni kljuc koji se vezuje za sertifikat
		    // - podatke o vlasniku
		    // - serijski broj sertifikata
		    // - od kada do kada vazi sertifikat
		    return new SubjectData(keyPairSubject.getPublic(), builder.build(), sn, startDate, endDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return null;
	}
		
	public SubjectData generateOrganizacijaData(CertifikatOrganizacija certifikat) {
		
		try {
			KeyPair keyPairSubject = generateKeyPair();
			
			//Datumi od kad do kad vazi sertifikat
			SimpleDateFormat iso8601Formater = new SimpleDateFormat("yyyy-MM-dd");
			Date startDate = iso8601Formater.parse(certifikat.getPocetak().toString());
			Date endDate = iso8601Formater.parse(certifikat.getKraj().toString());
			if (startDate.compareTo(endDate)>=0) {
				throw new ParseException("Pocetak vazenja certifikata mora biti pre isteka vazenja istog!", 0);
			}
			
			//Serijski broj sertifikata
			String sn = getRandomBigInteger();
			//klasa X500NameBuilder pravi X500Name objekat koji predstavlja podatke o vlasniku
			X500NameBuilder builder = new X500NameBuilder(BCStyle.INSTANCE);
		    builder.addRDN(BCStyle.BUSINESS_CATEGORY,certifikat.getKategorija());
		    builder.addRDN(BCStyle.C,certifikat.getDrzava());
		    builder.addRDN(BCStyle.O,certifikat.getNazivOrganizacije());
		    builder.addRDN(BCStyle.POSTAL_CODE,certifikat.getPTT());
		    builder.addRDN(BCStyle.STREET,certifikat.getUlica());
		    
		    //Kreiraju se podaci za sertifikat, sto ukljucuje:
		    // - javni kljuc koji se vezuje za sertifikat
		    // - podatke o vlasniku
		    // - serijski broj sertifikata
		    // - od kada do kada vazi sertifikat
		    return new SubjectData(keyPairSubject.getPublic(), builder.build(), sn, startDate, endDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return null;
	}
		
	public SubjectData generateOsobaData(CertifikatOsoba certifikat) {
		
		try {
			KeyPair keyPairSubject = generateKeyPair();
			
			//Datumi od kad do kad vazi sertifikat
			SimpleDateFormat iso8601Formater = new SimpleDateFormat("yyyy-MM-dd");
			Date startDate = iso8601Formater.parse(certifikat.getPocetak().toString());
			Date endDate = iso8601Formater.parse(certifikat.getKraj().toString());
			if (startDate.compareTo(endDate)>=0) {
				throw new ParseException("Pocetak vazenja certifikata mora biti pre isteka vazenja istog!", 0);
			}
			
			//Serijski broj sertifikata
			String sn = getRandomBigInteger();
			//klasa X500NameBuilder pravi X500Name objekat koji predstavlja podatke o vlasniku
			X500NameBuilder builder = new X500NameBuilder(BCStyle.INSTANCE);
		    builder.addRDN(BCStyle.CN, certifikat.getIme() + " " + certifikat.getPrezime());
		    builder.addRDN(BCStyle.SURNAME, certifikat.getPrezime());
		    builder.addRDN(BCStyle.GIVENNAME, certifikat.getIme());
		    builder.addRDN(BCStyle.O, certifikat.getOrganizacija());
		    builder.addRDN(BCStyle.OU, certifikat.getSuborganizacija());
		    builder.addRDN(BCStyle.C, certifikat.getDrzava());
		    builder.addRDN(BCStyle.E, certifikat.getEmail());
		    //UID (USER ID) je ID korisnika
		    builder.addRDN(BCStyle.UID, certifikat.getIdZaposlenog());
		    
		    //Kreiraju se podaci za sertifikat, sto ukljucuje:
		    // - javni kljuc koji se vezuje za sertifikat
		    // - podatke o vlasniku
		    // - serijski broj sertifikata
		    // - od kada do kada vazi sertifikat
		    return new SubjectData(keyPairSubject.getPublic(), builder.build(), sn, startDate, endDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	private String getRandomBigInteger() {
		BigInteger bigInteger = new BigInteger("2000000000000");// uper limit
	    BigInteger min = new BigInteger("1000000000");// lower limit
	    BigInteger bigInteger1 = bigInteger.subtract(min);
	    Random rnd = new Random();
	    int maxNumBitLength = bigInteger.bitLength();

	    BigInteger aRandomBigInt;

	    aRandomBigInt = new BigInteger(maxNumBitLength, rnd);
	    if (aRandomBigInt.compareTo(min) < 0)
	      aRandomBigInt = aRandomBigInt.add(min);
	    if (aRandomBigInt.compareTo(bigInteger) >= 0)
	      aRandomBigInt = aRandomBigInt.mod(bigInteger1).add(min);
		
	    return aRandomBigInt.toString();
	    
	}
}
