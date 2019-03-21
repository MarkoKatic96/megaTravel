package security.certificates;

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
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.bouncycastle.asn1.x500.X500NameBuilder;
import org.bouncycastle.asn1.x500.style.BCStyle;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import model.Certifikat;
import model.CertifikatAplikacija;
import model.CertifikatDomen;
import model.CertifikatOprema;
import model.CertifikatOrganizacija;
import model.CertifikatOsoba;
import model.CertifikatRoot;
import security.data.IssuerData;
import security.data.SubjectData;


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
	
	public SubjectData generateCertificate(Certifikat certifikat) {
		SubjectData subjectData = null;
		switch (certifikat.getTipCertifikata()) {
			case APLIKACIJA:
				subjectData = generateAppData((CertifikatAplikacija) certifikat);
				break;
			
			case DOMEN:
				subjectData = generateDomenData((CertifikatDomen) certifikat);
				break;
			
			case OPREMA:
				subjectData = generateOpremaData((CertifikatOprema) certifikat);
				break;
				
			case ORGANIZACIJA:
				subjectData = generateOrganizacijaData((CertifikatOrganizacija) certifikat);
				break;
				
			case OSOBA:
				subjectData = generateOsobaData((CertifikatOsoba) certifikat);
				break;
				
			case ROOT:
				subjectData = generateRootData((CertifikatRoot) certifikat);
				break;
	
			default:
				break;
		}
		
		return subjectData;
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
			String sn = UUID.randomUUID().toString();
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
			String sn = UUID.randomUUID().toString();
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
			String sn = UUID.randomUUID().toString();
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
			String sn = UUID.randomUUID().toString();
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
			String sn = UUID.randomUUID().toString();
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
			String sn = UUID.randomUUID().toString();
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
		
}
