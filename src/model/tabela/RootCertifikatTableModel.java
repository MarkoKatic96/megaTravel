package model.tabela;

import java.security.cert.X509Certificate;

import model.TipCertifikata;

public class RootCertifikatTableModel extends AbstractTableModel {
	
	public RootCertifikatTableModel() {
		super();
	}
	
	/**
	 * @param id
	 * @param certifikat
	 * @param tipCertifikata
	 */
	public RootCertifikatTableModel(Long id, X509Certificate certifikat, TipCertifikata tipCertifikata) {
		super();
		super.setId(id);
		super.setCertifikat(certifikat);
	}

	public TipCertifikata getTipCertifikata() {
		return TipCertifikata.ROOT;
	}

	public void setTipCertifikata(TipCertifikata tipCertifikata) throws Throwable {
		if (tipCertifikata != TipCertifikata.ROOT) {
			Exception e = new IllegalArgumentException();
			throw new Throwable("Pokusaj instanciranja RootCertifikatTableModel sa pogresnim parametrom 'tipCertifikata'=" + tipCertifikata.toString(), e); 
		}
	}
}
