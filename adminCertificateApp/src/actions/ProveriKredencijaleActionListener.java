package actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.rmi.server.ServerNotActiveException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

import javax.security.auth.login.CredentialException;
import javax.swing.JOptionPane;

import org.apache.commons.validator.routines.EmailValidator;

import gui.LoginGUI;
import gui.MainGUI;
import https.controller.AdminController;

public class ProveriKredencijaleActionListener implements ActionListener {

	private LoginGUI main;
	private static int badAttepmts = 0;
	
	public ProveriKredencijaleActionListener(LoginGUI loginGUI) {
		main = loginGUI;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (praznaPolja()) {
			nullifyTextArea();
			JOptionPane.showMessageDialog(main.getMainFrame(), "Polja za unos ne smeju biti prazna!", "Login", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (!emailIspravan()) {
			nullifyTextArea();
			if (incrementBadAtt()) {
				//System.out.println("e" + badAttepmts);
				JOptionPane.showMessageDialog(main.getMainFrame(), "Uneti kredencijali su neispravni!", "Login", JOptionPane.ERROR_MESSAGE);
			}
			return;
		}
		if (!pswIspravan()) {
			nullifyTextArea();
			if (incrementBadAtt()) {
				//System.out.println("p" + badAttepmts);
				JOptionPane.showMessageDialog(main.getMainFrame(), "Uneti kredencijali su neispravni!", "Login", JOptionPane.ERROR_MESSAGE);
			}
			return;
		}
		
		//send credencials to backend for check
		AdminController adminController = new AdminController();

		try {
			//baca exceptions
			String token = adminController.login(main.getTxtEmail().getText(), new String(main.getTxtPass().getPassword()));
			if (token.startsWith("Bearer ")) {
				//System.out.println("true loged in");
				main.close();
				
				MainGUI window = new MainGUI();
				window.getMainFrame().setVisible(true);
				return;
			} else {
				//System.out.println("WTF: " + token);
				JOptionPane.showMessageDialog(main.getMainFrame(), "Token je nevalidan! Najbolje je da se ponovo logujete i dobijete novi token. Ukoliko nastavite dalje, mozda cete naici na probleme sa radom aplikacije.", "Login", JOptionPane.ERROR_MESSAGE);
				return;
			}

			
		} catch (IOException e1) {
			System.out.println("Server nije podignut?!");
			JOptionPane.showMessageDialog(main.getMainFrame(), "Nije moguce kontaktirati server za proveru kredencijala. Proverite da li imate internet konekciju. Ako imate internet konekciju pozovite administratora sistema ili pokusajte da se ulogujete malo kasnije!", "Login", JOptionPane.ERROR_MESSAGE);
			return;
		} catch (CredentialException e1) {
			System.out.println("Losi kredencijali!");
			nullifyTextArea();
			if (incrementBadAtt()) {
				JOptionPane.showMessageDialog(main.getMainFrame(), "Uneti kredencijali nisu ispravni! Pokusajte ponovo.", "Login", JOptionPane.ERROR_MESSAGE);
			}			
			return;
		} catch (ServerNotActiveException e1) {
			System.out.println("Server nije podignut!");
			JOptionPane.showMessageDialog(main.getMainFrame(), "Nije moguce kontaktirati server za proveru kredencijala.\nProverite da li imate internet konekciju. Ako imate internet \nkonekciju pozovite administratora sistema ili pokusajte da \nse ulogujete malo kasnije!", "Login", JOptionPane.ERROR_MESSAGE);
			return;
		} catch (KeyManagementException | CertificateException | KeyStoreException | NoSuchAlgorithmException
				| InstantiationException | IllegalAccessException e1) {
			System.out.println("Neka nepoznata greska!");
			JOptionPane.showMessageDialog(main.getMainFrame(), "Desila se greska sa serverom: " + e1.getMessage() + " \nPozovite administratora sistema ili pokusajte da se ulogujete malo kasnije!", "Login", JOptionPane.ERROR_MESSAGE);
			return;
		} catch (IllegalArgumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InvocationTargetException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NoSuchMethodException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SecurityException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

	private boolean incrementBadAtt() {
		badAttepmts++;
		if (badAttepmts>3) {
			main.close();
			JOptionPane.showMessageDialog(null, "Neispravni kredencijali su uneti vise od 3 puta!", "Login", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}

	private boolean pswIspravan() {
		if (main.getTxtPass().getPassword().length<4) {
			return false;
		}
		return true;
	}

	private boolean praznaPolja() {
		if (main.getTxtEmail().getText().isEmpty() || main.getTxtPass().getPassword().length==0) {
			return true;
		}
		return false;
	}

	private boolean emailIspravan() {
		EmailValidator emailValidator = EmailValidator.getInstance();
		return emailValidator.isValid(main.getTxtEmail().getText());
	}
	
	private void nullifyTextArea() {
		//main.setTxtEmail();
		main.setTxtPass();
	}

}
