package megatravel.bezbednost.model;

import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Date;

public class DataSum {
	private String x500Name;
	private BigInteger serialNumber;
	private Date startDate;
	private Date endDate;
	//private KeyPair pair;
	private byte[] publicKey;
	private byte[] privateKey;
	private BigInteger rootSerialNumber;
	
	public DataSum() {
		// TODO Auto-generated constructor stub
	}

	public DataSum(String x500Name, BigInteger serialNumber, Date startDate, Date endDate, PublicKey publicKey,
			PrivateKey privateKey, BigInteger rootSerialNumber) {
		super();
		this.x500Name = x500Name;
		this.serialNumber = serialNumber;
		this.startDate = startDate;
		this.endDate = endDate;
		this.publicKey = publicKey.getEncoded();
		this.privateKey = privateKey.getEncoded();
		this.rootSerialNumber = rootSerialNumber;
	}

	public String getX500Name() {
		return x500Name;
	}

	public void setX500Name(String x500Name) {
		this.x500Name = x500Name;
	}

	public BigInteger getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(BigInteger serialNumber) {
		this.serialNumber = serialNumber;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public PublicKey getPublicKeyDecoded() throws InvalidKeySpecException, NoSuchAlgorithmException {
		/*KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
	    EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(publicKeyBytes);
	    PublicKey publicKey = keyFactory.generatePublic(publicKeySpec);
	    */
		/*X509EncodedKeySpec spec = new X509EncodedKeySpec(publicKey);
		KeyFactory kf = KeyFactory.getInstance("RSA");
		PublicKey pk = kf.generatePublic(spec);
		return pk;*/
		return KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(publicKey));
	}

	public void setPublicKeyEncoded(PublicKey publicKey) {
		this.publicKey = publicKey.getEncoded();
	}

	public PrivateKey getPrivateKeyDecoded() throws InvalidKeySpecException, NoSuchAlgorithmException {
		/*KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
	    EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
	    PrivateKey privateKey = keyFactory.generatePrivate(privateKeySpec);
*/
		/*X509EncodedKeySpec spec = new X509EncodedKeySpec(privateKey);
		KeyFactory kf = KeyFactory.getInstance("RSA");
		PrivateKey pk = kf.generatePrivate(spec);
		return pk;*/
		return KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(privateKey));
	}

	public void setPrivateKeyEncoded(PrivateKey privateKey) {
		this.privateKey = privateKey.getEncoded();
	}

	public BigInteger getRootSerialNumber() {
		return rootSerialNumber;
	}

	public void setRootSerialNumber(BigInteger rootSerialNumber) {
		this.rootSerialNumber = rootSerialNumber;
	}

	public void setPublicKey(byte[] publicKey) {
		this.publicKey = publicKey;
	}

	public void setPrivateKey(byte[] privateKey) {
		this.privateKey = privateKey;
	}

	public byte[] getPublicKey() {
		return publicKey;
	}

	public byte[] getPrivateKey() {
		return privateKey;
	}
	
	
}
