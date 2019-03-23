package megatravel.bezbednost.controller;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SignatureException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.bouncycastle.asn1.x500.X500Name;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import megatravel.bezbednost.certificateGeneration.GenerateCertificate;
import megatravel.bezbednost.data.SubjectData;
import megatravel.bezbednost.keyStore.KeyStoreWriter;
import megatravel.bezbednost.model.AdminModel;
import megatravel.bezbednost.model.CertificateModel;
import megatravel.bezbednost.model.DataSum;
import megatravel.bezbednost.model.TipCertifikata;
import megatravel.bezbednost.service.AdminService;
import megatravel.bezbednost.service.CertificateService;
import megatravel.bezbednost.token.JwtTokenUtils;

@RestController
public class CertificateController {
	
	@Autowired
	AdminService adminService;
	
	@Autowired
	JwtTokenUtils jwtTokenUtils;
	
	@Autowired
	CertificateService certificateService;
	
	@RequestMapping(value = "api/certificate/id/{id}", method = RequestMethod.GET)
	public ResponseEntity<CertificateModel> getCertifikatById(@PathVariable Long id, HttpServletRequest req) {
		System.out.println("getCertifikatById()");
		
		String token = jwtTokenUtils.resolveToken(req);
		String email = jwtTokenUtils.getUsername(token);
		
		AdminModel korisnik = adminService.findByEmail(email);
		if (korisnik == null) {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
		
		CertificateModel cert = certificateService.findOne(id);
		
		if (cert==null) {
			return new ResponseEntity<>(null, HttpStatus.UNPROCESSABLE_ENTITY);
		}
		
		return new ResponseEntity<>(cert, HttpStatus.OK);
	}
	
	@RequestMapping(value = "api/certificate/x509/{id}", method = RequestMethod.GET)
	public ResponseEntity<X509Certificate> getX509CertificateById(@PathVariable Long id, HttpServletRequest req) {
		System.out.println("getX509CertificateById()");
		
		String token = jwtTokenUtils.resolveToken(req);
		String email = jwtTokenUtils.getUsername(token);
		
		AdminModel korisnik = adminService.findByEmail(email);
		if (korisnik == null) {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
		
		CertificateModel cert = certificateService.findOne(id);
		if (cert==null) {
			return new ResponseEntity<>(null, HttpStatus.UNPROCESSABLE_ENTITY);
		}
		
		X509Certificate certificate;
		try {
			certificate = getX509Certificate(cert.getCertifikat());
			return new ResponseEntity<>(certificate, HttpStatus.OK);
		} catch (CertificateException e) {
		}
		
		return new ResponseEntity<>(null, HttpStatus.I_AM_A_TEAPOT);
	}
	
	private X509Certificate getX509Certificate(byte[] certifikat) throws CertificateException {
		CertificateFactory certFactory = CertificateFactory.getInstance("X.509");
		InputStream in = new ByteArrayInputStream(certifikat);
		X509Certificate cert = (X509Certificate)certFactory.generateCertificate(in);
		return cert;
	}
	
	@RequestMapping(value = "api/certificate/sn/{id}", method = RequestMethod.GET)
	public ResponseEntity<CertificateModel> getCertifikatBySN(@PathVariable BigInteger id, HttpServletRequest req) {
		System.out.println("getCertifikatBySN()");
		
		String token = jwtTokenUtils.resolveToken(req);
		String email = jwtTokenUtils.getUsername(token);
		
		AdminModel korisnik = adminService.findByEmail(email);
		if (korisnik == null) {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
		
		CertificateModel cert = certificateService.findBySerijskiBroj(id);
		
		return new ResponseEntity<>(cert, HttpStatus.OK);
	}
	
	@RequestMapping(value = "api/certificate/id/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> removeById(@PathVariable Long id, HttpServletRequest req) {
		System.out.println("removeById()");
		
		String token = jwtTokenUtils.resolveToken(req);
		String email = jwtTokenUtils.getUsername(token);
		
		AdminModel korisnik = adminService.findByEmail(email);
		if (korisnik == null) {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
		
		CertificateModel cert = certificateService.findOne(id);
		if (cert != null) {
			certificateService.remove(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
	}
	
	@RequestMapping(value = "api/certificate/sn/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> removeBySN(@PathVariable BigInteger id, HttpServletRequest req) {
		System.out.println("removeBySN()");
		
		String token = jwtTokenUtils.resolveToken(req);
		String email = jwtTokenUtils.getUsername(token);
		
		AdminModel korisnik = adminService.findByEmail(email);
		if (korisnik == null) {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
		
		CertificateModel cert = certificateService.findBySerijskiBroj(id);
		if (cert != null) {
			certificateService.remove(cert.getId());
			return new ResponseEntity<>(HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
	}
	
	@RequestMapping(value = "api/certificate/exists/{id}", method = RequestMethod.GET)
	public ResponseEntity<Void> exists(@PathVariable BigInteger id, HttpServletRequest req) {
		System.out.println("exists()");
		
		String token = jwtTokenUtils.resolveToken(req);
		String email = jwtTokenUtils.getUsername(token);
		
		AdminModel korisnik = adminService.findByEmail(email);
		if (korisnik == null) {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
		
		boolean exists = certificateService.existsBySerijskiBroj(id);
		if (exists) {
			return new ResponseEntity<>(HttpStatus.FOUND);
		} else {
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	@RequestMapping(value = "api/certificate/subcert/{id}", method = RequestMethod.GET, produces = {	MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<List<CertificateModel>> getSubCertificates(@PathVariable BigInteger id, HttpServletRequest req) {
		System.out.println("getSubCertificates()");
		
		String token = jwtTokenUtils.resolveToken(req);
		String email = jwtTokenUtils.getUsername(token);
		
		AdminModel korisnik = adminService.findByEmail(email);
		if (korisnik == null) {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
		
		final List<CertificateModel> certifikati = certificateService.findSubCertifikates(id);
		
		HttpHeaders headers = new HttpHeaders();
		long certTotal = certifikati.size();
		headers.add("X-Total-Count", String.valueOf(certTotal));
		
		return new ResponseEntity<>(certifikati, headers, HttpStatus.OK);
	}
	
	@RequestMapping(value = "api/certificate/all", method = RequestMethod.GET, produces = {	MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<List<CertificateModel>> getAllCertificates(HttpServletRequest req) {
		System.out.println("getAllCertificates()");
		
		String token = jwtTokenUtils.resolveToken(req);
		String email = jwtTokenUtils.getUsername(token);
		
		AdminModel korisnik = adminService.findByEmail(email);
		if (korisnik == null) {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
		 
		final List<CertificateModel> comm = certificateService.findAll();
		
		HttpHeaders headers = new HttpHeaders();
		long certTotal = comm.size();
		headers.add("X-Total-Count", String.valueOf(certTotal));
		
		return new ResponseEntity<>(comm, headers, HttpStatus.OK);
	}
	
	@RequestMapping(value = "api/certificate/create", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CertificateModel> createCertificate(@RequestBody DataSum dataSum, HttpServletRequest req) throws CertificateEncodingException, IOException {
		System.out.println("createCertificate()");
		
		String token = jwtTokenUtils.resolveToken(req);
		String email = jwtTokenUtils.getUsername(token);
		
		AdminModel korisnik = adminService.findByEmail(email);
		if (korisnik == null) {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
		 
		CertificateModel nadcertifikat = null;
		if (dataSum.getRootSerialNumber()!=null) {
			nadcertifikat = certificateService.findBySerijskiBroj(dataSum.getRootSerialNumber());
			if (nadcertifikat == null) {
				return new ResponseEntity<>(null, HttpStatus.UNPROCESSABLE_ENTITY);
			}
			// ako je nadcertifikat root onda je null inace je pronadjen i dovucen iz baze
		}
		
		SubjectData subData = null;
		try {
			subData = new SubjectData(dataSum.getPublicKeyDecoded(), new X500Name(dataSum.getX500Name()), dataSum.getSerialNumber().toString(), dataSum.getStartDate(), dataSum.getEndDate());
		} catch (InvalidKeySpecException | NoSuchAlgorithmException e2) {
			e2.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.UNPROCESSABLE_ENTITY);
		}
		
		//proveravam da li vec postoji cert sa istim sn
		boolean b = certificateService.existsBySerijskiBroj(dataSum.getSerialNumber());
		while (b) {
			dataSum.setSerialNumber(new BigInteger(getRandomBigInteger()));
			b = certificateService.existsBySerijskiBroj(dataSum.getSerialNumber());
		}
		
		TipCertifikata tipNadcertifikata = (nadcertifikat==null) ? TipCertifikata.ROOT : nadcertifikat.getTipCertifikata();
		TipCertifikata tipCertifikata = dataSum.getTipCertifikata();
		GenerateCertificate gc = new GenerateCertificate();
		X509Certificate nadcert = null;
		X509Certificate cert = null;
		
		if (nadcertifikat!=null) {
			try {
				nadcert = getX509Certificate(nadcertifikat.getCertifikat());
			} catch (CertificateException e) {
				System.out.println("Pukao zbog konverzije bita certifikata");
				e.printStackTrace();
				return new ResponseEntity<>(null, HttpStatus.UNPROCESSABLE_ENTITY);
			}
			
			//generisanje certifikata
			try {
				cert = gc.generateCertificate(nadcert, tipNadcertifikata, subData, new KeyPair(dataSum.getPublicKeyDecoded(), dataSum.getPrivateKeyDecoded()));
			} catch (InvalidKeyException | CertificateException | NoSuchAlgorithmException | NoSuchProviderException
					| SignatureException | InvalidKeySpecException e) {
				System.out.println("Pukao zbog kreiranja certifikata");
				e.printStackTrace();
				return new ResponseEntity<>(null, HttpStatus.UNPROCESSABLE_ENTITY);
			}
			
		} else {
			try {
				cert = gc.generateSelfSignedCertificate(subData, new KeyPair(dataSum.getPublicKeyDecoded(), dataSum.getPrivateKeyDecoded()));
			} catch (InvalidKeyException | CertificateException | NoSuchAlgorithmException | NoSuchProviderException
					| SignatureException | InvalidKeySpecException e) {
				System.out.println("Pukao zbog kreiranja certifikata");
				e.printStackTrace();
				return new ResponseEntity<>(null, HttpStatus.UNPROCESSABLE_ENTITY);
			}
			
		}
		
		//skladistim private key subjekta
		KeyStoreWriter ksw = new KeyStoreWriter();
		try {
			ksw.savePrivateKey(dataSum.getPrivateKeyDecoded(), cert, tipCertifikata);
		} catch (InvalidKeySpecException | NoSuchAlgorithmException e1) {
			System.out.println("Pukao zbog konverzije kljuca");
			e1.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.UNPROCESSABLE_ENTITY);
		}
		
		//skladistim certifikat u bazi i FOLDERU
		CertificateModel newCert = null;
		try {
			BigInteger sn = (nadcert==null) ? cert.getSerialNumber() : nadcertifikat.getSerijskiBroj();
			newCert = new CertificateModel(null, cert, tipCertifikata, sn);
		} catch (CertificateEncodingException e) {
			System.out.println("Pukao zbog kreiranja CertificateModel-a");
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.UNPROCESSABLE_ENTITY);
		}
		
		newCert = certificateService.save(newCert);
		
		//...
		saveCertificate(cert);
		
		return new ResponseEntity<>(newCert, HttpStatus.OK);
	}

	private TipCertifikata getTipCertifikata(TipCertifikata tipNadCertifikata) {
		switch (tipNadCertifikata) {
			case CA_APLIKACIJA:
				return TipCertifikata.APLIKACIJA;
			case CA_DOMEN:
				return TipCertifikata.DOMEN;
			case CA_OPREMA:
				return TipCertifikata.OPREMA;
			case CA_ORGANIZACIJA:
				return TipCertifikata.ORGANIZACIJA;
			case CA_OSOBA:
				return TipCertifikata.OSOBA;
			case ROOT:
			try {
				throw new Exception("Tu sam te cekao");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				return null;
			
			default:
				break;
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
	
	private void saveCertificate(X509Certificate cert) throws CertificateEncodingException, IOException {
		final FileOutputStream os = new FileOutputStream("./files/repository/certificates/"+cert.getSerialNumber()+".cer");
		//os.write("-----BEGIN CERTIFICATE-----\n".getBytes("US-ASCII"));
		//String encodedCert = Base64Utility.encode(cert.getEncoded());
		os.write(cert.getEncoded());
		//os.write("-----END CERTIFICATE-----\n".getBytes("US-ASCII"));
		os.close();
	}
}
