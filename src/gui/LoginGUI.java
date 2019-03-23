package gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import actions.ProveriKredencijaleActionListener;

public class LoginGUI {

	private JFrame frame;
	private JPasswordField txtPass;
	private JTextField txtEmail;

	/**
	 * Create the application.
	 */
	public LoginGUI() {
		initialize();
	}
	
	public JFrame getMainFrame() {
		return frame;
	}
	
	public void close() {
		getMainFrame().setVisible(false);
		getMainFrame().dispose();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 369, 260);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblUnosKredencijala = new JLabel("UNOS KREDENCIJALA");
		lblUnosKredencijala.setBounds(118, 30, 159, 14);
		frame.getContentPane().add(lblUnosKredencijala);
		
		JLabel lblAdminEmail = new JLabel("ADMIN EMAIL:");
		lblAdminEmail.setHorizontalAlignment(SwingConstants.TRAILING);
		lblAdminEmail.setBounds(45, 82, 93, 14);
		frame.getContentPane().add(lblAdminEmail);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(148, 79, 153, 20);
		frame.getContentPane().add(txtEmail);
		txtEmail.setColumns(10);
		
		JLabel lblAdminPassword = new JLabel("ADMIN PASSWORD:");
		lblAdminPassword.setHorizontalAlignment(SwingConstants.TRAILING);
		lblAdminPassword.setBounds(10, 137, 132, 14);
		frame.getContentPane().add(lblAdminPassword);
		
		txtPass = new JPasswordField();
		txtPass.setBounds(148, 134, 153, 20);
		txtPass.setColumns(2);
		frame.getContentPane().add(txtPass);
		
		JButton btnPotvrdi = new JButton("LOGIN");
		btnPotvrdi.setBounds(132, 198, 79, 23);
		btnPotvrdi.addActionListener(new ProveriKredencijaleActionListener(this));
		frame.getContentPane().add(btnPotvrdi);
	}

	public JPasswordField getTxtPass() {
		return txtPass;
	}

	public void setTxtPass() {
		this.txtPass.setText("");
	}

	public JTextField getTxtEmail() {
		return txtEmail;
	}

	public void setTxtEmail() {
		this.txtEmail.setText("");
	}

}
