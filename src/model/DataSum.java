package model;

import java.math.BigInteger;
import java.security.KeyPair;
import java.security.PublicKey;
import java.util.Date;

import org.bouncycastle.asn1.x500.X500Name;

public class DataSum {
	//private SubjectData subData;
	private PublicKey publicKey;
	private X500Name x500Name;
	private String serialNumber;
	private Date startDate;
	private Date endDate;
	private KeyPair pair;
	private BigInteger rootSerialNumber;
	public DataSum(PublicKey publicKey, X500Name x500Name, String serialNumber, Date startDate, Date endDate,
			KeyPair pair, BigInteger rootSerialNumber) {
		super();
		this.publicKey = publicKey;
		this.x500Name = x500Name;
		this.serialNumber = serialNumber;
		this.startDate = startDate;
		this.endDate = endDate;
		this.pair = pair;
		this.rootSerialNumber = rootSerialNumber;
	}
	public PublicKey getPublicKey() {
		return publicKey;
	}
	public void setPublicKey(PublicKey publicKey) {
		this.publicKey = publicKey;
	}
	public X500Name getX500Name() {
		return x500Name;
	}
	public void setX500Name(X500Name x500Name) {
		this.x500Name = x500Name;
	}
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
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
	public KeyPair getPair() {
		return pair;
	}
	public void setPair(KeyPair pair) {
		this.pair = pair;
	}
	public BigInteger getRootSerialNumber() {
		return rootSerialNumber;
	}
	public void setRootSerialNumber(BigInteger rootSerialNumber) {
		this.rootSerialNumber = rootSerialNumber;
	}
	
	
	
}
