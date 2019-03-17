package gui;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import https.requests.GetRequest;

public class CertificateTabelModel extends AbstractTableModel {

	private static final long serialVersionUID = 7945151410354882137L;
	
	private List<AbstractTableModel> listaCertifikata = new ArrayList<>();
	private static final String[] zaglavlje = {"ID", "TIP", "DATUM POCETKA", "DATUM ZAVRSETKA", "SERIJSKI BROJ", "NADCERTIFIKAT"};
	
	public CertificateTabelModel() {
		// TODO Get certificates from App A
		try {
			new GetRequest().execute("Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJib3Jpc2JpYmljMTk5NkBnbWFpbC5jb20iLCJhdXRoIjpbeyJhdXRob3JpdHkiOiJhZG1pbmlzdHJhdG9yQ2VydGlmaWthdGEifV0sImlhdCI6MTU1MjgyNzI2NywiZXhwIjoxNTUyOTEzNjY3fQ.Um1K8E8KHeWIRLH_imnQ6Ph2RpttJrREo1PcCr43yb4");
		} catch (KeyManagementException | CertificateException | KeyStoreException | NoSuchAlgorithmException
				| IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
    @Override
    public String getColumnName(int column) {
        return zaglavlje[column];
    }
	
	@Override
	public int getColumnCount() {
		return zaglavlje.length;
	}

	@Override
	public int getRowCount() {
		return listaCertifikata.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		return listaCertifikata.get(row);
	}

}
