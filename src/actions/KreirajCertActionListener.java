package actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import gui.MainGUI;
import model.Certifikat;

public class KreirajCertActionListener implements ActionListener {

	private JFrame frame;
	private MainGUI main;
	
	public KreirajCertActionListener(JFrame frmAdminCertificateManagement, MainGUI mainGUI) {
		this.main = mainGUI;
		this.frame = frmAdminCertificateManagement;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//Step 0: provera validnosti unetih podataka
		
		Certifikat cert = main.getCertifikatData();
		if (cert==null) {
			JOptionPane.showMessageDialog(frame, "Nisu popunjeni svi podaci za izabran tip certifikata!","Kreiranje certifikata",JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		// TODO Auto-generated method stub
		// Step 1: ucitaj nadcertifikat ako je tip != root
		// Step 2: kreiraj certifikat
		// Step 3: sacuvaj private kljuc u KeyStore
		// Step 4: sacuvaj certifikat na zeljeno mesto
		// Step 5: enkriptuj certifikat sa public kljucem od aplikacije A
		// Step 6: posalji certifikat aplikaciji A
		// Step 7: cekaj odgovor da je certifikat uspesno primljen i sacuvan
		
		
		
		JOptionPane.showMessageDialog(frame, "Certifikat je uspešno kreiran!","Kreiranje certifikata",JOptionPane.INFORMATION_MESSAGE);
	}

}
