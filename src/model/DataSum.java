package model;

import java.math.BigInteger;
import java.security.KeyPair;

import security.data.SubjectData;

public class DataSum {
	private SubjectData subData;
	private KeyPair pair;
	private BigInteger rootSerialNumber;
	
	public DataSum(SubjectData subData, KeyPair pair, BigInteger rootSerialNumber) {
		super();
		this.subData = subData;
		this.pair = pair;
		this.rootSerialNumber = rootSerialNumber;
	}

	public SubjectData getSubData() {
		return subData;
	}

	public KeyPair getPair() {
		return pair;
	}

	public BigInteger getRootSerialNumber() {
		return rootSerialNumber;
	}
	
}
