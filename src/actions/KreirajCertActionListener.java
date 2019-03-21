package actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.KeyPair;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import app.main.Singleton;
import gui.MainGUI;
import https.model.CertifikatDTO;
import model.Certifikat;
import model.CertifikatAplikacija;
import model.CertifikatCA;
import model.CertifikatDomen;
import model.CertifikatOprema;
import model.CertifikatOrganizacija;
import model.CertifikatOsoba;
import model.DataSum;
import model.TipCertifikata;
import security.certificates.GenerateCertificate;
import security.data.SubjectData;

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
		CertifikatAplikacija certAplikacija;
		CertifikatDomen certDomen;
		CertifikatOprema certOprema;
		CertifikatOrganizacija certOrganizacija;
		CertifikatOsoba certOsoba;
		CertifikatCA certCA;

		if(cert.getPocetak() == null) {
			JOptionPane.showMessageDialog(frame, "Nije unesen datum pocetka vazenja sertifikata!","Kreiranje certifikata",JOptionPane.ERROR_MESSAGE);
			return;
		}
		if(cert.getKraj() == null) {
			JOptionPane.showMessageDialog(frame, "Nije unesen datum kraja vazenja sertifikata!","Kreiranje certifikata",JOptionPane.ERROR_MESSAGE);
			return;
		}
		if(cert.getPocetak().compareTo(cert.getKraj()) >= 0) {
			JOptionPane.showMessageDialog(frame, "Zavrsetak trajanja certifikata ne moze biti pre pocetka!","Kreiranje certifikata",JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		switch ((TipCertifikata)cert.getTipCertifikata()) {
		case APLIKACIJA:
			certAplikacija = (CertifikatAplikacija)main.getCertifikatData();
			if(certAplikacija.getNazivAplikacije().equals("")) {
				JOptionPane.showMessageDialog(frame, "Nije popunjen naziv aplikacije!","Kreiranje certifikata",JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(certAplikacija.getOrganizacija().equals("")) {
				JOptionPane.showMessageDialog(frame, "Nije popunjen naziv organizacije!","Kreiranje certifikata",JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(certAplikacija.getVerzija().equals("")) {
				JOptionPane.showMessageDialog(frame, "Nije popunjen verzija aplikacije!","Kreiranje certifikata",JOptionPane.ERROR_MESSAGE);
				return;
			}
		case DOMEN:
			certDomen = (CertifikatDomen)main.getCertifikatData();
			if(certDomen.getOrganizacija().equals("")) {
				JOptionPane.showMessageDialog(frame, "Nije popunjen naziv organizacije!","Kreiranje certifikata",JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(certDomen.getHttps().equals("")) {
				JOptionPane.showMessageDialog(frame, "Nije popunjen domen!","Kreiranje certifikata",JOptionPane.ERROR_MESSAGE);
				return;
			}
		case OPREMA:
			certOprema = (CertifikatOprema)main.getCertifikatData();
			if(certOprema.getMAC().equals("")) {
				JOptionPane.showMessageDialog(frame, "Nije popunjen MAC!","Kreiranje certifikata",JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(certOprema.getNazivOpreme().equals("")) {
				JOptionPane.showMessageDialog(frame, "Nije popunjen naziv opreme!","Kreiranje certifikata",JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(certOprema.getDrzava().equals("")) {
				JOptionPane.showMessageDialog(frame, "Nije popunjen naziv drzave!","Kreiranje certifikata",JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(certOprema.getOrganizacija().equals("")) {
				JOptionPane.showMessageDialog(frame, "Nije popunjen naziv organizacije!","Kreiranje certifikata",JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(certOprema.getSuborganizacija().equals("")) {
				JOptionPane.showMessageDialog(frame, "Nije popunjen naziv suborganizacije!","Kreiranje certifikata",JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(certOprema.getIdOpreme().equals("")) {
				JOptionPane.showMessageDialog(frame, "Nije popunjen id opreme!","Kreiranje certifikata",JOptionPane.ERROR_MESSAGE);
				return;
			}
		case ORGANIZACIJA:
			certOrganizacija = (CertifikatOrganizacija)main.getCertifikatData();
			if(certOrganizacija.getKategorija().equals("")) {
				JOptionPane.showMessageDialog(frame, "Nije popunjena kategorija!","Kreiranje certifikata",JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(certOrganizacija.getDrzava().equals("")) {
				JOptionPane.showMessageDialog(frame, "Nije popunjen naziv drzave!","Kreiranje certifikata",JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(certOrganizacija.getNazivOrganizacije().equals("")) {
				JOptionPane.showMessageDialog(frame, "Nije popunjen naziv organizacije!","Kreiranje certifikata",JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(certOrganizacija.getPTT().equals("")) {
				JOptionPane.showMessageDialog(frame, "Nije popunjen PTT!","Kreiranje certifikata",JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(certOrganizacija.getUlica().equals("")) {
				JOptionPane.showMessageDialog(frame, "Nije popunjeno ime ulice!","Kreiranje certifikata",JOptionPane.ERROR_MESSAGE);
				return;
			}
		case OSOBA:
			certOsoba = (CertifikatOsoba)main.getCertifikatData();
			if(certOsoba.getIme().equals("")) {
				JOptionPane.showMessageDialog(frame, "Nije popunjeno ime osobe!","Kreiranje certifikata",JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(certOsoba.getPrezime().equals("")) {
				JOptionPane.showMessageDialog(frame, "Nije popunjeno prezime osobe!","Kreiranje certifikata",JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(certOsoba.getDrzava().equals("")) {
				JOptionPane.showMessageDialog(frame, "Nije popunjen naziv drzave!","Kreiranje certifikata",JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(certOsoba.getOrganizacija().equals("")) {
				JOptionPane.showMessageDialog(frame, "Nije popunjen naziv organizacije!","Kreiranje certifikata",JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(certOsoba.getSuborganizacija().equals("")) {
				JOptionPane.showMessageDialog(frame, "Nije popunjen naziv suborganizacije!","Kreiranje certifikata",JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(certOsoba.getEmail().equals("")) {
				JOptionPane.showMessageDialog(frame, "Nije popunjen email!","Kreiranje certifikata",JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(certOsoba.getIdZaposlenog().equals("")) {
				JOptionPane.showMessageDialog(frame, "Nije popunjen id zaposlenog!","Kreiranje certifikata",JOptionPane.ERROR_MESSAGE);
				return;
			}
		default:
			certCA = (CertifikatCA)main.getCertifikatData();
	}
		if (certCA !=null) {
			if(certCA.getOrganizacija().equals("")) {
				JOptionPane.showMessageDialog(frame, "Nije popunjen naziv organizacije!","Kreiranje certifikata",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
		
		GenerateCertificate gen = new GenerateCertificate();
		KeyPair pair = gen.generateKeyPair();
		SubjectData subData = gen.generateCertificate(cert);
		
		boolean found = false;
		if(cert.getSeriskiBrojNadSert() != null) {
			for (CertifikatDTO certDto : Singleton.getInstance().getListaCertifikata()) {
				if(certDto.getSerijskiBroj() == cert.getSeriskiBrojNadSert()) {
					found = true;
					break;
				}
			}
		}
		if(!found) {
			JOptionPane.showMessageDialog(frame, "Nije pronadjen uneti sertifikat!","Kreiranje certifikata",JOptionPane.ERROR_MESSAGE);

		}
		DataSum sum = new DataSum(subData, pair, cert.getSeriskiBrojNadSert());
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
