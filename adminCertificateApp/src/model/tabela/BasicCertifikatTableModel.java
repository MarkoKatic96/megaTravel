package model.tabela;

import java.math.BigInteger;
import java.security.cert.X509Certificate;

import model.TipCertifikata;

public class BasicCertifikatTableModel extends AbstractTableModel {
	private TipCertifikata tipCertifikata;
	private BigInteger nadcertifikat;
	
	public BasicCertifikatTableModel() {
		super();
	}

	/**
	 * @param id
	 * @param certifikat
	 * @param tipCertifikata
	 * @param nadcertifikat
	 * @throws Throwable 
	 */
	public BasicCertifikatTableModel(Long id, X509Certificate certifikat, TipCertifikata tipCertifikata,
			BigInteger nadcertifikat) throws Throwable {
		super();
		
		if (tipCertifikata != TipCertifikata.APLIKACIJA && tipCertifikata != TipCertifikata.DOMEN && tipCertifikata != TipCertifikata.OPREMA && tipCertifikata != TipCertifikata.ORGANIZACIJA && tipCertifikata != TipCertifikata.OSOBA) {
			Exception e = new IllegalArgumentException();
			throw new Throwable("Pokusaj instanciranja BasicCertifikatTableModel sa pogresnim parametrom ipCertifikata: " + tipCertifikata.toString(), e);
		}
		
		super.setId(id);
		super.setCertifikat(certifikat);
		this.tipCertifikata = tipCertifikata;
		this.nadcertifikat = nadcertifikat;
	}

	public TipCertifikata getTipCertifikata() {
		return tipCertifikata;
	}

	public void setTipCertifikata(TipCertifikata tipCertifikata) throws Throwable {
		if (tipCertifikata != TipCertifikata.APLIKACIJA && tipCertifikata != TipCertifikata.DOMEN && tipCertifikata != TipCertifikata.OPREMA && tipCertifikata != TipCertifikata.ORGANIZACIJA && tipCertifikata != TipCertifikata.OSOBA) {
			Exception e = new IllegalArgumentException();
			throw new Throwable("Pokusaj instanciranja BasicCertifikatTableModel sa pogresnim parametrom 'tipCertifikata'=" + tipCertifikata.toString(), e);
		}
		
		this.tipCertifikata = tipCertifikata;
	}

	public BigInteger getNadcertifikat() {
		return nadcertifikat;
	}

	public void setNadcertifikat(BigInteger nadcertifikat) {
		this.nadcertifikat = nadcertifikat;
	}
}
