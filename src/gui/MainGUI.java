package gui;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.util.Date;

import javax.swing.Action;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import com.toedter.calendar.JDateChooser;

import actions.KreirajCertActionListener;
import model.Certifikat;
import model.CertifikatDomen;
import model.CertifikatOprema;
import model.CertifikatOrganizacija;
import model.CertifikatOsoba;
import model.CertifikatRoot;
import model.TipCertifikata;

public class MainGUI {

	private JFrame frmAdminCertificateManagement;
	private JTextField txtIme;
	private JTextField txtPrezime;
	private JTextField textOrganizacija;
	private JTextField textSuborganizacija;
	private JTextField txtEmail;
	private JTextField txtZaposleniid;
	private JTextField txtSerijskibroj;
	private JTextField txtNazivOpreme;
	private JTextField txtLokacijaOpreme;
	private JTextField txtOrganizacijaOpreme;
	private JTextField txtSuborganizacijaopreme;
	private JTextField txtKategorija;
	private JTextField txtDrzava;
	private JTextField txtNazivOrganizacije;
	private JTextField txtGrad;
	private JTextField txtPtt;
	private JTextField txtUlica;
	private JTextField txtOrganizacija;
	private JTextField txtDomen;
	private JTextField txtNadcertifikat;
	private JTextField txtKeystore;
	private JTextField txtCertifikat;
	private JTextField txtOrganizacijaRoot;
	private JComboBox<TipCertifikata> cbTipCertifikata;
	private JDateChooser dateStart;
	private JDateChooser dateEnd;
	private JTextField txtIdopreme;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainGUI window = new MainGUI();
					window.frmAdminCertificateManagement.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainGUI() {
		initialize();
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initialize() {
		frmAdminCertificateManagement = new JFrame();
		frmAdminCertificateManagement.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Boris\\eclipse-workspace\\Admin Certificate Management\\data\\images\\certificate.png"));
		frmAdminCertificateManagement.setTitle("Admin Certificate Management");
		frmAdminCertificateManagement.setBounds(100, 100, 1000, 580);
		frmAdminCertificateManagement.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAdminCertificateManagement.getContentPane().setLayout(new BoxLayout(frmAdminCertificateManagement.getContentPane(), BoxLayout.X_AXIS));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frmAdminCertificateManagement.getContentPane().add(tabbedPane);
		
		JPanel kreirajPanel = new JPanel();
		tabbedPane.addTab("Kreiraj certifikat", null, kreirajPanel, null);
		tabbedPane.setEnabledAt(0, true);
		GridBagLayout gbl_kreirajPanel = new GridBagLayout();
		gbl_kreirajPanel.columnWidths = new int[] {140, 160, 0};
		gbl_kreirajPanel.rowHeights = new int[] {270, 200, 40, 30};
		gbl_kreirajPanel.columnWeights = new double[]{0.0, 1.0};
		gbl_kreirajPanel.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0};
		kreirajPanel.setLayout(gbl_kreirajPanel);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.anchor = GridBagConstraints.SOUTH;
		gbc_panel.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		kreirajPanel.add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] {140, 190};
		gbl_panel.rowHeights = new int[] {0, 30, 30, 30, 30, 30, 30, 25};
		gbl_panel.columnWeights = new double[]{1.0, 1.0};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0};
		panel.setLayout(gbl_panel);
		
		JLabel lblPodaciOCertifikatu = new JLabel("Podaci o certifikatu");
		GridBagConstraints gbc_lblPodaciOCertifikatu = new GridBagConstraints();
		gbc_lblPodaciOCertifikatu.insets = new Insets(0, 0, 5, 5);
		gbc_lblPodaciOCertifikatu.gridx = 0;
		gbc_lblPodaciOCertifikatu.gridy = 0;
		panel.add(lblPodaciOCertifikatu, gbc_lblPodaciOCertifikatu);
		
		JLabel lblTip = new JLabel("Tip certifikata");
		GridBagConstraints gbc_lblTip = new GridBagConstraints();
		gbc_lblTip.insets = new Insets(0, 0, 5, 5);
		gbc_lblTip.gridx = 0;
		gbc_lblTip.gridy = 2;
		panel.add(lblTip, gbc_lblTip);
		
		cbTipCertifikata = new JComboBox();
		GridBagConstraints gbc_cbTipCertifikata = new GridBagConstraints();
		gbc_cbTipCertifikata.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbTipCertifikata.insets = new Insets(0, 0, 5, 5);
		gbc_cbTipCertifikata.gridx = 1;
		gbc_cbTipCertifikata.gridy = 2;
		panel.add(cbTipCertifikata, gbc_cbTipCertifikata);
		cbTipCertifikata.setToolTipText("Izaberite domen");
		cbTipCertifikata.setModel(new DefaultComboBoxModel(TipCertifikata.values()));
			
		JPanel povuciPanel = new JPanel();
		tabbedPane.addTab("Povuci certifikat", null, povuciPanel, null);
		tabbedPane.setEnabledAt(1, true);
		
		JPanel postojeciCert = new JPanel();
		tabbedPane.addTab("Postoje\u0107i certifikat", null, postojeciCert, null);
		
		JPanel aplikacijaPanel = new JPanel();
		tabbedPane.addTab("Aplikacije", null, aplikacijaPanel, null);
	    
	    JPanel cardPanel = new JPanel();
	    GridBagConstraints gbc_cardPanel = new GridBagConstraints();
	    gbc_cardPanel.insets = new Insets(0, 0, 5, 5);
	    gbc_cardPanel.fill = GridBagConstraints.HORIZONTAL;
	    gbc_cardPanel.anchor = GridBagConstraints.NORTH;
	    gbc_cardPanel.gridx = 0;
	    gbc_cardPanel.gridy = 1;
	    kreirajPanel.add(cardPanel, gbc_cardPanel);
	    cardPanel.setLayout(new CardLayout(0, 0));
	    
	    JPanel panelOprema = new JPanel();
	    cardPanel.add(panelOprema, TipCertifikata.OPREMA.toString());
	    GridBagLayout gbl_panelOprema = new GridBagLayout();
	    gbl_panelOprema.columnWidths = new int[] {140, 160, 0};
	    gbl_panelOprema.rowHeights = new int[] {30, 30, 30, 30, 30, 25, 0};
	    gbl_panelOprema.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
	    gbl_panelOprema.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
	    panelOprema.setLayout(gbl_panelOprema);
	    
	    JLabel lblMAC = new JLabel("MAC opreme");
	    lblMAC.setHorizontalAlignment(SwingConstants.CENTER);
	    GridBagConstraints gbc_lblMAC = new GridBagConstraints();
	    gbc_lblMAC.fill = GridBagConstraints.VERTICAL;
	    gbc_lblMAC.insets = new Insets(0, 0, 5, 5);
	    gbc_lblMAC.gridx = 0;
	    gbc_lblMAC.gridy = 0;
	    panelOprema.add(lblMAC, gbc_lblMAC);
	    
	    txtSerijskibroj = new JTextField();
	    txtSerijskibroj.setToolTipText("");
	    GridBagConstraints gbc_txtSerijskibroj = new GridBagConstraints();
	    gbc_txtSerijskibroj.fill = GridBagConstraints.BOTH;
	    gbc_txtSerijskibroj.insets = new Insets(0, 0, 5, 0);
	    gbc_txtSerijskibroj.gridx = 1;
	    gbc_txtSerijskibroj.gridy = 0;
	    panelOprema.add(txtSerijskibroj, gbc_txtSerijskibroj);
	    txtSerijskibroj.setColumns(10);
	    
	    JLabel lblNaziivOpreme = new JLabel("Naziv opreme");
	    lblNaziivOpreme.setHorizontalAlignment(SwingConstants.CENTER);
	    GridBagConstraints gbc_lblNaziivOpreme = new GridBagConstraints();
	    gbc_lblNaziivOpreme.fill = GridBagConstraints.BOTH;
	    gbc_lblNaziivOpreme.insets = new Insets(0, 0, 5, 5);
	    gbc_lblNaziivOpreme.gridx = 0;
	    gbc_lblNaziivOpreme.gridy = 1;
	    panelOprema.add(lblNaziivOpreme, gbc_lblNaziivOpreme);
	    
	    txtNazivOpreme = new JTextField();
	    GridBagConstraints gbc_txtNazivOpreme = new GridBagConstraints();
	    gbc_txtNazivOpreme.fill = GridBagConstraints.BOTH;
	    gbc_txtNazivOpreme.insets = new Insets(0, 0, 5, 0);
	    gbc_txtNazivOpreme.gridx = 1;
	    gbc_txtNazivOpreme.gridy = 1;
	    panelOprema.add(txtNazivOpreme, gbc_txtNazivOpreme);
	    txtNazivOpreme.setColumns(10);
	    
	    JLabel lblLokacijaOpreme = new JLabel("Lokacija opreme");
	    lblLokacijaOpreme.setHorizontalAlignment(SwingConstants.CENTER);
	    GridBagConstraints gbc_lblLokacijaOpreme = new GridBagConstraints();
	    gbc_lblLokacijaOpreme.fill = GridBagConstraints.BOTH;
	    gbc_lblLokacijaOpreme.insets = new Insets(0, 0, 5, 5);
	    gbc_lblLokacijaOpreme.gridx = 0;
	    gbc_lblLokacijaOpreme.gridy = 2;
	    panelOprema.add(lblLokacijaOpreme, gbc_lblLokacijaOpreme);
	    
	    txtLokacijaOpreme = new JTextField();
	    GridBagConstraints gbc_txtLokacijaOpreme = new GridBagConstraints();
	    gbc_txtLokacijaOpreme.fill = GridBagConstraints.BOTH;
	    gbc_txtLokacijaOpreme.insets = new Insets(0, 0, 5, 0);
	    gbc_txtLokacijaOpreme.gridx = 1;
	    gbc_txtLokacijaOpreme.gridy = 2;
	    panelOprema.add(txtLokacijaOpreme, gbc_txtLokacijaOpreme);
	    txtLokacijaOpreme.setColumns(10);
	    
	    JLabel lblOrganizacija_1 = new JLabel("Organizacija");
	    lblOrganizacija_1.setHorizontalAlignment(SwingConstants.CENTER);
	    GridBagConstraints gbc_lblOrganizacija_1 = new GridBagConstraints();
	    gbc_lblOrganizacija_1.fill = GridBagConstraints.BOTH;
	    gbc_lblOrganizacija_1.insets = new Insets(0, 0, 5, 5);
	    gbc_lblOrganizacija_1.gridx = 0;
	    gbc_lblOrganizacija_1.gridy = 3;
	    panelOprema.add(lblOrganizacija_1, gbc_lblOrganizacija_1);
	    
	    txtOrganizacijaOpreme = new JTextField();
	    GridBagConstraints gbc_txtOrganizacijaOpreme = new GridBagConstraints();
	    gbc_txtOrganizacijaOpreme.fill = GridBagConstraints.BOTH;
	    gbc_txtOrganizacijaOpreme.insets = new Insets(0, 0, 5, 0);
	    gbc_txtOrganizacijaOpreme.gridx = 1;
	    gbc_txtOrganizacijaOpreme.gridy = 3;
	    panelOprema.add(txtOrganizacijaOpreme, gbc_txtOrganizacijaOpreme);
	    txtOrganizacijaOpreme.setColumns(10);
	    
	    JLabel lblSuborganizacija_1 = new JLabel("Suborganizacija");
	    lblSuborganizacija_1.setHorizontalAlignment(SwingConstants.CENTER);
	    GridBagConstraints gbc_lblSuborganizacija_1 = new GridBagConstraints();
	    gbc_lblSuborganizacija_1.fill = GridBagConstraints.BOTH;
	    gbc_lblSuborganizacija_1.insets = new Insets(0, 0, 5, 5);
	    gbc_lblSuborganizacija_1.gridx = 0;
	    gbc_lblSuborganizacija_1.gridy = 4;
	    panelOprema.add(lblSuborganizacija_1, gbc_lblSuborganizacija_1);
	    
	    txtSuborganizacijaopreme = new JTextField();
	    GridBagConstraints gbc_txtSuborganizacijaopreme = new GridBagConstraints();
	    gbc_txtSuborganizacijaopreme.insets = new Insets(0, 0, 5, 0);
	    gbc_txtSuborganizacijaopreme.fill = GridBagConstraints.BOTH;
	    gbc_txtSuborganizacijaopreme.gridx = 1;
	    gbc_txtSuborganizacijaopreme.gridy = 4;
	    panelOprema.add(txtSuborganizacijaopreme, gbc_txtSuborganizacijaopreme);
	    txtSuborganizacijaopreme.setColumns(10);
	    
	    JLabel lblIdOpreme = new JLabel("ID opreme");
	    lblIdOpreme.setHorizontalAlignment(SwingConstants.CENTER);
	    GridBagConstraints gbc_lblIdOpreme = new GridBagConstraints();
	    gbc_lblIdOpreme.fill = GridBagConstraints.BOTH;
	    gbc_lblIdOpreme.insets = new Insets(0, 0, 0, 5);
	    gbc_lblIdOpreme.gridx = 0;
	    gbc_lblIdOpreme.gridy = 5;
	    panelOprema.add(lblIdOpreme, gbc_lblIdOpreme);
	    
	    txtIdopreme = new JTextField();
	    GridBagConstraints gbc_txtIdopreme = new GridBagConstraints();
	    gbc_txtIdopreme.fill = GridBagConstraints.BOTH;
	    gbc_txtIdopreme.gridx = 1;
	    gbc_txtIdopreme.gridy = 5;
	    panelOprema.add(txtIdopreme, gbc_txtIdopreme);
	    txtIdopreme.setColumns(10);
	    
	    JPanel panelOrganizacija = new JPanel();
	    cardPanel.add(panelOrganizacija, TipCertifikata.ORGANIZACIJA.toString());
	    GridBagLayout gbl_panelOrganizacija = new GridBagLayout();
	    gbl_panelOrganizacija.columnWidths = new int[] {140, 160, 0};
	    gbl_panelOrganizacija.rowHeights = new int[] {30, 30, 30, 30, 30, 25, 0};
	    gbl_panelOrganizacija.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
	    gbl_panelOrganizacija.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
	    panelOrganizacija.setLayout(gbl_panelOrganizacija);
	    
	    JLabel lblKategorija = new JLabel("Kategorija");
	    lblKategorija.setHorizontalAlignment(SwingConstants.CENTER);
	    GridBagConstraints gbc_lblKategorija = new GridBagConstraints();
	    gbc_lblKategorija.fill = GridBagConstraints.BOTH;
	    gbc_lblKategorija.insets = new Insets(0, 0, 5, 5);
	    gbc_lblKategorija.gridx = 0;
	    gbc_lblKategorija.gridy = 0;
	    panelOrganizacija.add(lblKategorija, gbc_lblKategorija);
	    
	    txtKategorija = new JTextField();
	    GridBagConstraints gbc_txtKategorija = new GridBagConstraints();
	    gbc_txtKategorija.fill = GridBagConstraints.BOTH;
	    gbc_txtKategorija.insets = new Insets(0, 0, 5, 0);
	    gbc_txtKategorija.gridx = 1;
	    gbc_txtKategorija.gridy = 0;
	    panelOrganizacija.add(txtKategorija, gbc_txtKategorija);
	    txtKategorija.setColumns(10);
	    
	    JLabel lblDrzava = new JLabel("Dr\u017Eava");
	    lblDrzava.setHorizontalAlignment(SwingConstants.CENTER);
	    GridBagConstraints gbc_lblDrzava = new GridBagConstraints();
	    gbc_lblDrzava.fill = GridBagConstraints.BOTH;
	    gbc_lblDrzava.insets = new Insets(0, 0, 5, 5);
	    gbc_lblDrzava.gridx = 0;
	    gbc_lblDrzava.gridy = 1;
	    panelOrganizacija.add(lblDrzava, gbc_lblDrzava);
	    
	    txtDrzava = new JTextField();
	    GridBagConstraints gbc_txtDrzava = new GridBagConstraints();
	    gbc_txtDrzava.fill = GridBagConstraints.BOTH;
	    gbc_txtDrzava.insets = new Insets(0, 0, 5, 0);
	    gbc_txtDrzava.gridx = 1;
	    gbc_txtDrzava.gridy = 1;
	    panelOrganizacija.add(txtDrzava, gbc_txtDrzava);
	    txtDrzava.setColumns(10);
	    
	    JLabel lblNazivOrganizacije = new JLabel("Naziv organizacije");
	    lblNazivOrganizacije.setHorizontalAlignment(SwingConstants.CENTER);
	    GridBagConstraints gbc_lblNazivOrganizacije = new GridBagConstraints();
	    gbc_lblNazivOrganizacije.fill = GridBagConstraints.BOTH;
	    gbc_lblNazivOrganizacije.insets = new Insets(0, 0, 5, 5);
	    gbc_lblNazivOrganizacije.gridx = 0;
	    gbc_lblNazivOrganizacije.gridy = 2;
	    panelOrganizacija.add(lblNazivOrganizacije, gbc_lblNazivOrganizacije);
	    
	    txtNazivOrganizacije = new JTextField();
	    GridBagConstraints gbc_txtNazivOrganizacije = new GridBagConstraints();
	    gbc_txtNazivOrganizacije.fill = GridBagConstraints.BOTH;
	    gbc_txtNazivOrganizacije.insets = new Insets(0, 0, 5, 0);
	    gbc_txtNazivOrganizacije.gridx = 1;
	    gbc_txtNazivOrganizacije.gridy = 2;
	    panelOrganizacija.add(txtNazivOrganizacije, gbc_txtNazivOrganizacije);
	    txtNazivOrganizacije.setColumns(10);
	    
	    JLabel lblGrad = new JLabel("Grad");
	    lblGrad.setHorizontalAlignment(SwingConstants.CENTER);
	    GridBagConstraints gbc_lblGrad = new GridBagConstraints();
	    gbc_lblGrad.fill = GridBagConstraints.BOTH;
	    gbc_lblGrad.insets = new Insets(0, 0, 5, 5);
	    gbc_lblGrad.gridx = 0;
	    gbc_lblGrad.gridy = 3;
	    panelOrganizacija.add(lblGrad, gbc_lblGrad);
	    
	    txtGrad = new JTextField();
	    GridBagConstraints gbc_txtGrad = new GridBagConstraints();
	    gbc_txtGrad.fill = GridBagConstraints.BOTH;
	    gbc_txtGrad.insets = new Insets(0, 0, 5, 0);
	    gbc_txtGrad.gridx = 1;
	    gbc_txtGrad.gridy = 3;
	    panelOrganizacija.add(txtGrad, gbc_txtGrad);
	    txtGrad.setColumns(10);
	    
	    JLabel lblPtt = new JLabel("PTT");
	    lblPtt.setHorizontalAlignment(SwingConstants.CENTER);
	    GridBagConstraints gbc_lblPtt = new GridBagConstraints();
	    gbc_lblPtt.fill = GridBagConstraints.BOTH;
	    gbc_lblPtt.insets = new Insets(0, 0, 5, 5);
	    gbc_lblPtt.gridx = 0;
	    gbc_lblPtt.gridy = 4;
	    panelOrganizacija.add(lblPtt, gbc_lblPtt);
	    
	    txtPtt = new JTextField();
	    GridBagConstraints gbc_txtPtt = new GridBagConstraints();
	    gbc_txtPtt.fill = GridBagConstraints.BOTH;
	    gbc_txtPtt.insets = new Insets(0, 0, 5, 0);
	    gbc_txtPtt.gridx = 1;
	    gbc_txtPtt.gridy = 4;
	    panelOrganizacija.add(txtPtt, gbc_txtPtt);
	    txtPtt.setColumns(10);
	    
	    JLabel lblUlica = new JLabel("Ulica");
	    lblUlica.setHorizontalAlignment(SwingConstants.CENTER);
	    GridBagConstraints gbc_lblUlica = new GridBagConstraints();
	    gbc_lblUlica.fill = GridBagConstraints.BOTH;
	    gbc_lblUlica.insets = new Insets(0, 0, 0, 5);
	    gbc_lblUlica.gridx = 0;
	    gbc_lblUlica.gridy = 5;
	    panelOrganizacija.add(lblUlica, gbc_lblUlica);
	    
	    txtUlica = new JTextField();
	    GridBagConstraints gbc_txtUlica = new GridBagConstraints();
	    gbc_txtUlica.fill = GridBagConstraints.BOTH;
	    gbc_txtUlica.gridx = 1;
	    gbc_txtUlica.gridy = 5;
	    panelOrganizacija.add(txtUlica, gbc_txtUlica);
	    txtUlica.setColumns(10);
	    
	    JPanel panelOsoba = new JPanel();
	    cardPanel.add(panelOsoba, TipCertifikata.OSOBA.toString());
	    GridBagLayout gbl_panelOsoba = new GridBagLayout();
	    gbl_panelOsoba.columnWidths = new int[] {140, 160, 0};
	    gbl_panelOsoba.rowHeights = new int[] {30, 30, 30, 30, 30, 25, 0};
	    gbl_panelOsoba.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
	    gbl_panelOsoba.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
	    panelOsoba.setLayout(gbl_panelOsoba);
	    
	    JLabel lblIme = new JLabel("Ime");
	    lblIme.setHorizontalAlignment(SwingConstants.CENTER);
	    GridBagConstraints gbc_lblIme = new GridBagConstraints();
	    gbc_lblIme.fill = GridBagConstraints.BOTH;
	    gbc_lblIme.insets = new Insets(0, 0, 5, 5);
	    gbc_lblIme.gridx = 0;
	    gbc_lblIme.gridy = 0;
	    panelOsoba.add(lblIme, gbc_lblIme);
	    
	    txtIme = new JTextField();
	    GridBagConstraints gbc_txtIme = new GridBagConstraints();
	    gbc_txtIme.fill = GridBagConstraints.BOTH;
	    gbc_txtIme.insets = new Insets(0, 0, 5, 0);
	    gbc_txtIme.gridx = 1;
	    gbc_txtIme.gridy = 0;
	    panelOsoba.add(txtIme, gbc_txtIme);
	    txtIme.setColumns(10);
	    
	    JLabel lblPrezime = new JLabel("Prezime");
	    lblPrezime.setHorizontalAlignment(SwingConstants.CENTER);
	    GridBagConstraints gbc_lblPrezime = new GridBagConstraints();
	    gbc_lblPrezime.fill = GridBagConstraints.BOTH;
	    gbc_lblPrezime.insets = new Insets(0, 0, 5, 5);
	    gbc_lblPrezime.gridx = 0;
	    gbc_lblPrezime.gridy = 1;
	    panelOsoba.add(lblPrezime, gbc_lblPrezime);
	    
	    txtPrezime = new JTextField();
	    GridBagConstraints gbc_txtPrezime = new GridBagConstraints();
	    gbc_txtPrezime.fill = GridBagConstraints.BOTH;
	    gbc_txtPrezime.insets = new Insets(0, 0, 5, 0);
	    gbc_txtPrezime.gridx = 1;
	    gbc_txtPrezime.gridy = 1;
	    panelOsoba.add(txtPrezime, gbc_txtPrezime);
	    txtPrezime.setColumns(10);
	    
	    JLabel lblOrganizacija = new JLabel("Organizacija");
	    lblOrganizacija.setHorizontalAlignment(SwingConstants.CENTER);
	    GridBagConstraints gbc_lblOrganizacija = new GridBagConstraints();
	    gbc_lblOrganizacija.fill = GridBagConstraints.BOTH;
	    gbc_lblOrganizacija.insets = new Insets(0, 0, 5, 5);
	    gbc_lblOrganizacija.gridx = 0;
	    gbc_lblOrganizacija.gridy = 2;
	    panelOsoba.add(lblOrganizacija, gbc_lblOrganizacija);
	    
	    textOrganizacija = new JTextField();
	    GridBagConstraints gbc_textOrganizacija = new GridBagConstraints();
	    gbc_textOrganizacija.fill = GridBagConstraints.BOTH;
	    gbc_textOrganizacija.insets = new Insets(0, 0, 5, 0);
	    gbc_textOrganizacija.gridx = 1;
	    gbc_textOrganizacija.gridy = 2;
	    panelOsoba.add(textOrganizacija, gbc_textOrganizacija);
	    textOrganizacija.setColumns(10);
	    
	    JLabel lblSuborganizacija = new JLabel("Suborganizacija");
	    lblSuborganizacija.setHorizontalAlignment(SwingConstants.CENTER);
	    GridBagConstraints gbc_lblSuborganizacija = new GridBagConstraints();
	    gbc_lblSuborganizacija.fill = GridBagConstraints.BOTH;
	    gbc_lblSuborganizacija.insets = new Insets(0, 0, 5, 5);
	    gbc_lblSuborganizacija.gridx = 0;
	    gbc_lblSuborganizacija.gridy = 3;
	    panelOsoba.add(lblSuborganizacija, gbc_lblSuborganizacija);
	    
	    textSuborganizacija = new JTextField();
	    GridBagConstraints gbc_textSuborganizacija = new GridBagConstraints();
	    gbc_textSuborganizacija.fill = GridBagConstraints.BOTH;
	    gbc_textSuborganizacija.insets = new Insets(0, 0, 5, 0);
	    gbc_textSuborganizacija.gridx = 1;
	    gbc_textSuborganizacija.gridy = 3;
	    panelOsoba.add(textSuborganizacija, gbc_textSuborganizacija);
	    textSuborganizacija.setColumns(10);
	    
	    JLabel lblEmail = new JLabel("Email");
	    lblEmail.setHorizontalAlignment(SwingConstants.CENTER);
	    GridBagConstraints gbc_lblEmail = new GridBagConstraints();
	    gbc_lblEmail.fill = GridBagConstraints.BOTH;
	    gbc_lblEmail.insets = new Insets(0, 0, 5, 5);
	    gbc_lblEmail.gridx = 0;
	    gbc_lblEmail.gridy = 4;
	    panelOsoba.add(lblEmail, gbc_lblEmail);
	    
	    txtEmail = new JTextField();
	    GridBagConstraints gbc_txtEmail = new GridBagConstraints();
	    gbc_txtEmail.fill = GridBagConstraints.BOTH;
	    gbc_txtEmail.insets = new Insets(0, 0, 5, 0);
	    gbc_txtEmail.gridx = 1;
	    gbc_txtEmail.gridy = 4;
	    panelOsoba.add(txtEmail, gbc_txtEmail);
	    txtEmail.setColumns(10);
	    
	    JLabel lblZaposleniId = new JLabel("Zaposleni ID");
	    lblZaposleniId.setHorizontalAlignment(SwingConstants.CENTER);
	    GridBagConstraints gbc_lblZaposleniId = new GridBagConstraints();
	    gbc_lblZaposleniId.fill = GridBagConstraints.BOTH;
	    gbc_lblZaposleniId.insets = new Insets(0, 0, 0, 5);
	    gbc_lblZaposleniId.gridx = 0;
	    gbc_lblZaposleniId.gridy = 5;
	    panelOsoba.add(lblZaposleniId, gbc_lblZaposleniId);
	    
	    txtZaposleniid = new JTextField();
	    GridBagConstraints gbc_txtZaposleniid = new GridBagConstraints();
	    gbc_txtZaposleniid.fill = GridBagConstraints.BOTH;
	    gbc_txtZaposleniid.gridx = 1;
	    gbc_txtZaposleniid.gridy = 5;
	    panelOsoba.add(txtZaposleniid, gbc_txtZaposleniid);
	    txtZaposleniid.setColumns(10);
	    
	    JPanel panelDomen = new JPanel();
	    cardPanel.add(panelDomen, TipCertifikata.DOMEN.toString());
	    GridBagLayout gbl_panelDomen = new GridBagLayout();
	    gbl_panelDomen.columnWidths = new int[] {140, 160, 0};
	    gbl_panelDomen.rowHeights = new int[] {30, 25, 0};
	    gbl_panelDomen.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
	    gbl_panelDomen.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
	    panelDomen.setLayout(gbl_panelDomen);
	    
	    JLabel lblOrganizacija_2 = new JLabel("Organizacija");
	    lblOrganizacija_2.setHorizontalAlignment(SwingConstants.CENTER);
	    GridBagConstraints gbc_lblOrganizacija_2 = new GridBagConstraints();
	    gbc_lblOrganizacija_2.fill = GridBagConstraints.BOTH;
	    gbc_lblOrganizacija_2.insets = new Insets(0, 0, 5, 5);
	    gbc_lblOrganizacija_2.gridx = 0;
	    gbc_lblOrganizacija_2.gridy = 0;
	    panelDomen.add(lblOrganizacija_2, gbc_lblOrganizacija_2);
	    
	    txtOrganizacija = new JTextField();
	    txtOrganizacija.setToolTipText("Naziv organizacije");
	    GridBagConstraints gbc_txtOrganizacija = new GridBagConstraints();
	    gbc_txtOrganizacija.fill = GridBagConstraints.BOTH;
	    gbc_txtOrganizacija.insets = new Insets(0, 0, 5, 0);
	    gbc_txtOrganizacija.gridx = 1;
	    gbc_txtOrganizacija.gridy = 0;
	    panelDomen.add(txtOrganizacija, gbc_txtOrganizacija);
	    txtOrganizacija.setColumns(10);
	    
	    JLabel lblDomen = new JLabel("Domen");
	    lblDomen.setHorizontalAlignment(SwingConstants.CENTER);
	    GridBagConstraints gbc_lblDomen = new GridBagConstraints();
	    gbc_lblDomen.fill = GridBagConstraints.BOTH;
	    gbc_lblDomen.insets = new Insets(0, 0, 0, 5);
	    gbc_lblDomen.gridx = 0;
	    gbc_lblDomen.gridy = 1;
	    panelDomen.add(lblDomen, gbc_lblDomen);
	    
	    txtDomen = new JTextField();
	    txtDomen.setToolTipText("www.example.com");
	    GridBagConstraints gbc_txtDomen = new GridBagConstraints();
	    gbc_txtDomen.fill = GridBagConstraints.BOTH;
	    gbc_txtDomen.gridx = 1;
	    gbc_txtDomen.gridy = 1;
	    panelDomen.add(txtDomen, gbc_txtDomen);
	    txtDomen.setColumns(10);
	    
	    CardLayout cl = (CardLayout)(cardPanel.getLayout());
	    cl.show(cardPanel, TipCertifikata.DOMEN.toString());
	    
	    JPanel panelRoot = new JPanel();
	    cardPanel.add(panelRoot, TipCertifikata.ROOT.toString());
	    GridBagLayout gbl_panelRoot = new GridBagLayout();
	    gbl_panelRoot.columnWidths = new int[] {140, 160, 0};
	    gbl_panelRoot.rowHeights = new int[] {25, 30, 30};
	    gbl_panelRoot.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
	    gbl_panelRoot.rowWeights = new double[]{0.0, Double.MIN_VALUE};
	    panelRoot.setLayout(gbl_panelRoot);
	    
	    JLabel lblOrganizacija_3 = new JLabel("Organizacija");
	    lblOrganizacija_3.setHorizontalAlignment(SwingConstants.CENTER);
	    GridBagConstraints gbc_lblOrganizacija_3 = new GridBagConstraints();
	    gbc_lblOrganizacija_3.insets = new Insets(0, 0, 0, 5);
	    gbc_lblOrganizacija_3.fill = GridBagConstraints.BOTH;
	    gbc_lblOrganizacija_3.gridx = 0;
	    gbc_lblOrganizacija_3.gridy = 0;
	    panelRoot.add(lblOrganizacija_3, gbc_lblOrganizacija_3);
	    
	    txtOrganizacijaRoot = new JTextField();
	    GridBagConstraints gbc_txtOrganizacijaRoot = new GridBagConstraints();
	    gbc_txtOrganizacijaRoot.fill = GridBagConstraints.BOTH;
	    gbc_txtOrganizacijaRoot.gridx = 1;
	    gbc_txtOrganizacijaRoot.gridy = 0;
	    panelRoot.add(txtOrganizacijaRoot, gbc_txtOrganizacijaRoot);
	    txtOrganizacijaRoot.setColumns(10);
		
	    JButton btnNadcertifikat = new JButton("...");
	    JLabel lblNadcertifikat = new JLabel("Nadcertifikat");
	    
	    cbTipCertifikata.setAction(new Action() {
	    	
	    	@Override
	    	public void actionPerformed(ActionEvent e) {
	    		TipCertifikata selected = (TipCertifikata) cbTipCertifikata.getSelectedItem();
	    		System.out.println(selected);
	    		CardLayout cl = (CardLayout)(cardPanel.getLayout());
	    	    cl.show(cardPanel, selected.toString());
	    	    
	    	    if (selected==TipCertifikata.ROOT) {
	    	    	btnNadcertifikat.setVisible(false);
	    	    	lblNadcertifikat.setVisible(false);
	    	    	txtNadcertifikat.setVisible(false);
	    	    } else {
	    	    	btnNadcertifikat.setVisible(true);
	    	    	lblNadcertifikat.setVisible(true);
	    	    	txtNadcertifikat.setVisible(true);
	    	    }
	    	}
	    	
	    	@Override
	    	public void setEnabled(boolean b) {
	    		// TODO Auto-generated method stub
	    	}
	    	
	    	@Override
	    	public void removePropertyChangeListener(PropertyChangeListener listener) {
	    		// TODO Auto-generated method stub
	    	}
	    	
	    	@Override
	    	public void putValue(String key, Object value) {
	    		// TODO Auto-generated method stub
	    	}
	    	
	    	@Override
	    	public boolean isEnabled() {
	    		// TODO Auto-generated method stub
	    		return true;
	    	}
	    	
	    	@Override
	    	public Object getValue(String key) {
	    		// TODO Auto-generated method stub
	    		return key;
	    	}
	    	
	    	@Override
	    	public void addPropertyChangeListener(PropertyChangeListener listener) {
	    		// TODO Auto-generated method stub
	    	}
	    });
	    
	    JLabel lblPoetak = new JLabel("Po\u010Detak");
	    GridBagConstraints gbc_lblPoetak = new GridBagConstraints();
	    gbc_lblPoetak.insets = new Insets(0, 0, 5, 5);
	    gbc_lblPoetak.gridx = 0;
	    gbc_lblPoetak.gridy = 3;
	    panel.add(lblPoetak, gbc_lblPoetak);
	    
	    dateStart = new JDateChooser();
	    dateStart.setMinSelectableDate(new Date());	    
	    GridBagConstraints gbc_dateStart = new GridBagConstraints();
	    gbc_dateStart.fill = GridBagConstraints.BOTH;
	    gbc_dateStart.insets = new Insets(0, 0, 5, 5);
	    gbc_dateStart.gridx = 1;
	    gbc_dateStart.gridy = 3;
	    panel.add(dateStart, gbc_dateStart);
	    
	    JLabel lblKraj = new JLabel("Kraj");
	    GridBagConstraints gbc_lblKraj = new GridBagConstraints();
	    gbc_lblKraj.insets = new Insets(0, 0, 5, 5);
	    gbc_lblKraj.gridx = 0;
	    gbc_lblKraj.gridy = 4;
	    panel.add(lblKraj, gbc_lblKraj);
	    
	    dateEnd = new JDateChooser();
	    dateEnd.setMinSelectableDate(new Date());
	    GridBagConstraints gbc_dateEnd = new GridBagConstraints();
	    gbc_dateEnd.insets = new Insets(0, 0, 5, 5);
	    gbc_dateEnd.fill = GridBagConstraints.BOTH;
	    gbc_dateEnd.gridx = 1;
	    gbc_dateEnd.gridy = 4;
	    panel.add(dateEnd, gbc_dateEnd);
	    	    
	    GridBagConstraints gbc_lblNadcertifikat = new GridBagConstraints();
	    gbc_lblNadcertifikat.insets = new Insets(0, 0, 5, 5);
	    gbc_lblNadcertifikat.gridx = 0;
	    gbc_lblNadcertifikat.gridy = 5;
	    panel.add(lblNadcertifikat, gbc_lblNadcertifikat);
	    
	    Panel panelNadcertifikat = new Panel();
	    GridBagConstraints gbc_panelNadcertifikat = new GridBagConstraints();
	    gbc_panelNadcertifikat.fill = GridBagConstraints.BOTH;
	    gbc_panelNadcertifikat.insets = new Insets(0, 0, 5, 5);
	    gbc_panelNadcertifikat.gridx = 1;
	    gbc_panelNadcertifikat.gridy = 5;
	    panel.add(panelNadcertifikat, gbc_panelNadcertifikat);
	    panelNadcertifikat.setLayout(new BoxLayout(panelNadcertifikat, BoxLayout.X_AXIS));
	    
	    txtNadcertifikat = new JTextField();
	    txtNadcertifikat.setEditable(false);
	    txtNadcertifikat.setHorizontalAlignment(SwingConstants.LEFT);
	    panelNadcertifikat.add(txtNadcertifikat);
	    txtNadcertifikat.setColumns(12);
	    
	    btnNadcertifikat.setToolTipText("Prona\u0111i nadcertifikat");
	    btnNadcertifikat.setAlignmentY(0.4f);
	    btnNadcertifikat.setAlignmentX(0.4f);
	    btnNadcertifikat.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc = new JFileChooser();
				jfc.setFileHidingEnabled(true);
				jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
				jfc.setDialogTitle("Izaberite nadcertifikat");
				FileFilter filter = new FileNameExtensionFilter("Certificate", new String[] {"p8", "key", "p10", "csr", "cer", "crl", "p7c", "crt", "der", "pem", "p12", "pfx", "p7b", "spc", "p7r" });
				jfc.setFileFilter(filter);
				int retVal = jfc.showOpenDialog(frmAdminCertificateManagement);

				if (retVal == JFileChooser.APPROVE_OPTION){
					  System.out.println("You chose to open this certificate: " + jfc.getSelectedFile().getAbsolutePath());
					  //File selectedFile = new File(jfc.getSelectedFile().getAbsolutePath());
					  txtNadcertifikat.setText(jfc.getSelectedFile().getAbsolutePath());
				}
			}
		});
	    panelNadcertifikat.add(btnNadcertifikat);
	    
	    JLabel lblKeystore = new JLabel("KeyStore");
	    GridBagConstraints gbc_lblKeystore = new GridBagConstraints();
	    gbc_lblKeystore.insets = new Insets(0, 0, 5, 5);
	    gbc_lblKeystore.gridx = 0;
	    gbc_lblKeystore.gridy = 6;
	    panel.add(lblKeystore, gbc_lblKeystore);
	    
	    Panel panelKeyStore = new Panel();
	    GridBagConstraints gbc_panelKeyStore = new GridBagConstraints();
	    gbc_panelKeyStore.fill = GridBagConstraints.BOTH;
	    gbc_panelKeyStore.insets = new Insets(0, 0, 5, 5);
	    gbc_panelKeyStore.gridx = 1;
	    gbc_panelKeyStore.gridy = 6;
	    panel.add(panelKeyStore, gbc_panelKeyStore);
	    panelKeyStore.setLayout(new BoxLayout(panelKeyStore, BoxLayout.X_AXIS));
	    
	    txtKeystore = new JTextField();
	    txtKeystore.setEditable(false);
	    txtKeystore.setHorizontalAlignment(SwingConstants.LEFT);
	    panelKeyStore.add(txtKeystore);
	    txtKeystore.setColumns(12);
	    
	    JButton btnKeyStore = new JButton("...");
	    btnKeyStore.setToolTipText("Prona\u0111i KeyStore");
	    btnKeyStore.setAlignmentX(0.4f);
	    btnKeyStore.setAlignmentY(0.4f);
	    btnKeyStore.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc = new JFileChooser();
				jfc.setFileHidingEnabled(true);
				jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
				jfc.setDialogTitle("Izaberite KeyStore za smeštanje privatnog ključa");
				FileFilter filter = new FileNameExtensionFilter("KeyStore", new String[] {"jks"});
				jfc.setFileFilter(filter);
				int retVal = jfc.showOpenDialog(frmAdminCertificateManagement);

				if (retVal == JFileChooser.APPROVE_OPTION){
					  System.out.println("You chose to open this KeyStore: " + jfc.getSelectedFile().getAbsolutePath());
					  //File selectedFile = new File(jfc.getSelectedFile().getAbsolutePath());
					  txtKeystore.setText(jfc.getSelectedFile().getAbsolutePath());
				}
			}
		});
	    panelKeyStore.add(btnKeyStore);
	    
	    JLabel lblCertifikat = new JLabel("Certifikat");
	    GridBagConstraints gbc_lblCertifikat = new GridBagConstraints();
	    gbc_lblCertifikat.insets = new Insets(0, 0, 0, 5);
	    gbc_lblCertifikat.gridx = 0;
	    gbc_lblCertifikat.gridy = 7;
	    panel.add(lblCertifikat, gbc_lblCertifikat);
	    
	    Panel panelCertifikat = new Panel();
	    GridBagConstraints gbc_panelCertifikat = new GridBagConstraints();
	    gbc_panelCertifikat.fill = GridBagConstraints.BOTH;
	    gbc_panelCertifikat.insets = new Insets(0, 0, 0, 5);
	    gbc_panelCertifikat.gridx = 1;
	    gbc_panelCertifikat.gridy = 7;
	    panel.add(panelCertifikat, gbc_panelCertifikat);
	    panelCertifikat.setLayout(new BoxLayout(panelCertifikat, BoxLayout.X_AXIS));
	    
	    txtCertifikat = new JTextField();
	    txtCertifikat.setEditable(false);
	    txtCertifikat.setHorizontalAlignment(SwingConstants.LEFT);
	    panelCertifikat.add(txtCertifikat);
	    txtCertifikat.setColumns(12);
	    
	    JButton btnCertifikat = new JButton("...");
	    btnCertifikat.setToolTipText("Lokacija za \u010Duvanje novog certifikata");
	    btnCertifikat.setAlignmentY(0.4f);
	    btnCertifikat.setAlignmentX(0.4f);
	    btnCertifikat.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc = new JFileChooser();
				jfc.setFileHidingEnabled(true);
				jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				jfc.setDialogTitle("Izaberite lokaciju za \u010Duvanje novog certifikata");
				//FileFilter filter = new FileNameExtensionFilter("KeyStore", new String[] {"jks"});
				//jfc.setFileFilter(filter);
				int retVal = jfc.showOpenDialog(frmAdminCertificateManagement);

				if (retVal == JFileChooser.APPROVE_OPTION){
					  System.out.println("You chose to save new certificate in: " + jfc.getSelectedFile().getAbsolutePath());
					  //File selectedFile = new File(jfc.getSelectedFile().getAbsolutePath());
					  txtCertifikat.setText(jfc.getSelectedFile().getAbsolutePath());
				}
			}
		});
	    panelCertifikat.add(btnCertifikat);
	    panel.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{lblPodaciOCertifikatu, lblTip, cbTipCertifikata, lblPoetak, lblKraj, lblNadcertifikat, panelNadcertifikat, txtNadcertifikat, btnNadcertifikat, lblKeystore, panelKeyStore, txtKeystore, btnKeyStore, lblCertifikat, panelCertifikat, txtCertifikat, btnCertifikat, dateStart.getCalendarButton(), dateStart, dateEnd.getCalendarButton(), dateEnd}));
	    
	    JPanel panel_2 = new JPanel();
	    GridBagConstraints gbc_panel_2 = new GridBagConstraints();
	    gbc_panel_2.insets = new Insets(0, 0, 5, 5);
	    gbc_panel_2.fill = GridBagConstraints.BOTH;
	    gbc_panel_2.gridx = 0;
	    gbc_panel_2.gridy = 2;
	    kreirajPanel.add(panel_2, gbc_panel_2);
	    
	    JButton btnKreiraj = new JButton("Kreiraj certifikat");
	    btnKreiraj.addActionListener(new KreirajCertActionListener(frmAdminCertificateManagement,this));
	    panel_2.add(btnKreiraj);
	}
	
	public Certifikat getCertifikatData() {
		switch ((TipCertifikata)cbTipCertifikata.getSelectedItem()) {
		case DOMEN:
			return getDomenCertifikat();
		case OPREMA:
			return getOpremaCertifikat();
		case ORGANIZACIJA:
			return getDOrganizacijaCertifikat();
		case OSOBA:
			return getOsobaCertifikat();
		default:
			return getRootCertifikat();
		}
	}

	private CertifikatRoot getRootCertifikat() {
		// TODO Auto-generated method stub
		return null;
	}

	private CertifikatOsoba getOsobaCertifikat() {
		// TODO Auto-generated method stub
		return null;
	}

	private CertifikatOrganizacija getDOrganizacijaCertifikat() {
		// TODO Auto-generated method stub
		return null;
	}

	private CertifikatOprema getOpremaCertifikat() {
		// TODO Auto-generated method stub
		return null;
	}

	private CertifikatDomen getDomenCertifikat() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
