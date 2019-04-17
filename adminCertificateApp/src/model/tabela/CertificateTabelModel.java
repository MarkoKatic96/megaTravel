package model.tabela;

import java.io.IOException;
import java.math.BigInteger;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

import app.main.Singleton;
import https.controller.CertificateController;
import https.model.CertifikatDTO;
import model.TipCertifikata;

public class CertificateTabelModel extends AbstractTableModel {

	private static final long serialVersionUID = 7945151410354882137L;
	
	private List<CertifikatDTO> listaCertifikata = new ArrayList<>();
	private static final String[] zaglavlje = {"ID", "TIP", "DATUM POCETKA", "DATUM ZAVRSETKA", "SERIJSKI BROJ", "NADCERTIFIKAT"};
	@SuppressWarnings("rawtypes")
	private static final Class[] tipovi = {Long.class, TipCertifikata.class, Date.class, Date.class, BigInteger.class, BigInteger.class};
	
	public CertificateTabelModel() {
		CertificateController cc = new CertificateController();
		try {
			listaCertifikata = cc.getAllCertificates();
			Singleton.getInstance().setListaCertifikata(listaCertifikata);
			
		} catch (KeyManagementException | CertificateException | KeyStoreException | NoSuchAlgorithmException
				| InstantiationException | IllegalAccessException | IOException e) {
			JOptionPane.showMessageDialog(null, "Nije moguce inicijalizovati tabelu za komunikaciju certifikata", "Greska", JOptionPane.OK_OPTION);
			//e.printStackTrace();
		}
		
		
	}
	
    public String getColumnName(int column) {
        return zaglavlje[column];
    }
	
	public int getColumnCount() {
		return zaglavlje.length;
	}

	public int getRowCount() {
		return listaCertifikata.size();
	}

	
	public Object getValueAt(int row, int col) {
		if (col == 0) {
			return listaCertifikata.get(row).getId();
		}
		if (col == 1) {
			return listaCertifikata.get(row).getTipCertifikata();
		}
		if (col == 2) {
			return listaCertifikata.get(row).getDatumPocetka();
		}
		if (col == 3) {
			return listaCertifikata.get(row).getDatumKraja();
		}
		if (col == 4) {
			return listaCertifikata.get(row).getSerijskiBroj();
		}
		
		return listaCertifikata.get(row).getNadcertifikat();
	}
	
	public void setValueAt(Object value, int row, int col) {
	      CertifikatDTO data = (CertifikatDTO) (listaCertifikata.get(row));

	      switch (col) {
		      case 0:
		        data.setId((Long) value);
		        break;
		      case 1:
		        data.setTipCertifikata((TipCertifikata) value);
		        break;
		      case 2:
		        data.setDatumPocetka((Date) value);
		        break;
		      case 3:
		        data.setDatumKraja((Date) value);
		        break;
		      case 4:
			        data.setSerijskiBroj((BigInteger) value);
			        break;
		      case 5:
			        data.setNadcertifikat((BigInteger) value);
			        break;
	      }
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Class getColumnClass(int col) { 
		return tipovi[col];
	} 

	public void addElement(CertifikatDTO dto) {
		listaCertifikata.add(dto);
	}
	
	public void remove(CertifikatDTO dto) {
		listaCertifikata.remove(dto);
	}
}
