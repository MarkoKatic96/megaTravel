package model.tabela;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class CertificateTabelModel extends AbstractTableModel {

	private static final long serialVersionUID = 7945151410354882137L;
	
	private List<AbstractTableModel> listaCertifikata = new ArrayList<>();
	private static final String[] zaglavlje = {"ID", "TIP", "DATUM POCETKA", "DATUM ZAVRSETKA", "SERIJSKI BROJ", "NADCERTIFIKAT"};
	
	public CertificateTabelModel() {
		// TODO Get certificates from App A	
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
