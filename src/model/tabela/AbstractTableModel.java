package model.tabela;

import java.math.BigInteger;
import java.security.cert.X509Certificate;
import java.util.Date;

public abstract class AbstractTableModel {
	private Long id;
	private X509Certificate certifikat;
	private Date datumPocetka;
	private Date datumKraja;
	private BigInteger serijskiBroj;

	public AbstractTableModel() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public X509Certificate getCertifikat() {
		return certifikat;
	}

	public void setCertifikat(X509Certificate certifikat) {
		this.certifikat = certifikat;
		this.datumPocetka = certifikat.getNotBefore();
		this.datumKraja = certifikat.getNotAfter();
		this.serijskiBroj = certifikat.getSerialNumber();
	}

	public Date getDatumPocetka() {
		return datumPocetka;
	}

	public Date getDatumKraja() {
		return datumKraja;
	}

	public BigInteger getSerijskiBroj() {
		return serijskiBroj;
	}
}
