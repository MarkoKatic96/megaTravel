package gui;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
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
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTree;
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
import model.tabela.CertificateTabelModel;

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
	private JTextField txtNazivAplikacije;
	private JTextField txtOrganizacijaAplikacije;
	private JTextField txtVerzija;
	private JTable table;
	private JTextField txtUid;
	private JTextField txtIzdavac;
	private JTextField txtSubjekat;
	private JTextField textField;
	private JTextField textField_1;
	private JTable table_1;
	private JTable table_2;

	/**
	 * Create the application frame.
	 */
	public MainGUI() {
		initialize();
	}
	
	public JFrame getMainFrame() {
		return frmAdminCertificateManagement;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initialize() {
		frmAdminCertificateManagement = new JFrame();
		frmAdminCertificateManagement.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Boris\\eclipse-workspace\\Admin Certificate Management\\data\\images\\certificate.png"));
		frmAdminCertificateManagement.setTitle("Admin Certificate Management");
		frmAdminCertificateManagement.setBounds(100, 100, 1080, 580);
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
		
		JPanel komunikacijaPanel = new JPanel();
		tabbedPane.addTab("Komunikacija", null, komunikacijaPanel, null);
		GridBagLayout gbl_komunikacijaPanel = new GridBagLayout();
		gbl_komunikacijaPanel.columnWidths = new int[] {90, 700, 0, 0};
		gbl_komunikacijaPanel.rowHeights = new int[] {30, 150, 40, 150};
		gbl_komunikacijaPanel.columnWeights = new double[]{1.0, 1.0, 1.0};
		gbl_komunikacijaPanel.rowWeights = new double[]{1.0, 1.0, 0.0, 1.0, 0.0, 0.0};
		komunikacijaPanel.setLayout(gbl_komunikacijaPanel);
		
		JPanel panel_5 = new JPanel();
		GridBagConstraints gbc_panel_5 = new GridBagConstraints();
		gbc_panel_5.insets = new Insets(0, 0, 5, 5);
		gbc_panel_5.fill = GridBagConstraints.BOTH;
		gbc_panel_5.gridx = 0;
		gbc_panel_5.gridy = 0;
		komunikacijaPanel.add(panel_5, gbc_panel_5);
		
		JLabel lblIzaberiteIzTabela = new JLabel("Izaberite iz tabela 2 certifikata koja smeju da komuniciraju");
		panel_5.add(lblIzaberiteIzTabela);
		
		CertificateTabelModel model_1 = new CertificateTabelModel();
		CertificateTabelModel model_2 = new CertificateTabelModel();
		table_1 = new JTable(model_1);
		table_2 = new JTable(model_2);
		table_1.setAutoCreateRowSorter(true);
		table_2.setAutoCreateRowSorter(true);
		
		JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,new JScrollPane(table_1),new JScrollPane(table_2));
		splitPane.setEnabled(false);
		splitPane.setResizeWeight(0.5);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		GridBagConstraints gbc_splitPane = new GridBagConstraints();
		gbc_splitPane.gridwidth = 2;
		gbc_splitPane.insets = new Insets(0, 0, 0, 5);
		gbc_splitPane.gridheight = 8;
		gbc_splitPane.fill = GridBagConstraints.BOTH;
		gbc_splitPane.gridx = 1;
		gbc_splitPane.gridy = 0;
		komunikacijaPanel.add(splitPane, gbc_splitPane);
		
		JPanel cert1_infoPanel = new JPanel();
		GridBagConstraints gbc_cert1_infoPanel = new GridBagConstraints();
		gbc_cert1_infoPanel.insets = new Insets(0, 0, 5, 5);
		gbc_cert1_infoPanel.fill = GridBagConstraints.BOTH;
		gbc_cert1_infoPanel.gridx = 0;
		gbc_cert1_infoPanel.gridy = 1;
		komunikacijaPanel.add(cert1_infoPanel, gbc_cert1_infoPanel);
		cert1_infoPanel.setLayout(new GridLayout(4, 1, 0, 0));
		
		JLabel lblPodaciOIzdavau = new JLabel("  Podaci o Izdavaču Certifikata 1:");
		cert1_infoPanel.add(lblPodaciOIzdavau);
		
		txtIzdavac = new JTextField();
		txtIzdavac.setEditable(false);
		cert1_infoPanel.add(txtIzdavac);
		txtIzdavac.setColumns(10);
		
		JLabel lblPodaciOSubjektu = new JLabel("  Podaci o Subjektu Cerifikata 1:");
		cert1_infoPanel.add(lblPodaciOSubjektu);
		
		txtSubjekat = new JTextField();
		txtSubjekat.setEditable(false);
		cert1_infoPanel.add(txtSubjekat);
		txtSubjekat.setColumns(10);
		
		JPanel cert2_infoPanel = new JPanel();
		GridBagConstraints gbc_cert2_infoPanel = new GridBagConstraints();
		gbc_cert2_infoPanel.insets = new Insets(0, 0, 5, 5);
		gbc_cert2_infoPanel.fill = GridBagConstraints.BOTH;
		gbc_cert2_infoPanel.gridx = 0;
		gbc_cert2_infoPanel.gridy = 3;
		komunikacijaPanel.add(cert2_infoPanel, gbc_cert2_infoPanel);
		cert2_infoPanel.setLayout(new GridLayout(4, 1, 0, 0));
		
		JLabel lblPodaciOIzdavau_1 = new JLabel("  Podaci o Izdavaču Certifikata 2:");
		cert2_infoPanel.add(lblPodaciOIzdavau_1);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setColumns(10);
		cert2_infoPanel.add(textField);
		
		JLabel lblPodaciOSubjektu_1 = new JLabel("  Podaci o Subjektu Cerifikata 2:");
		cert2_infoPanel.add(lblPodaciOSubjektu_1);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		cert2_infoPanel.add(textField_1);
		
		JPanel panel_8 = new JPanel();
		GridBagConstraints gbc_panel_8 = new GridBagConstraints();
		gbc_panel_8.insets = new Insets(0, 0, 5, 5);
		gbc_panel_8.fill = GridBagConstraints.VERTICAL;
		gbc_panel_8.gridx = 0;
		gbc_panel_8.gridy = 5;
		komunikacijaPanel.add(panel_8, gbc_panel_8);
		panel_8.setLayout(new BoxLayout(panel_8, BoxLayout.X_AXIS));
		
		JButton btnPoveiCertifikate = new JButton("Poveži certifikate");
		GridBagConstraints gbc_btnPoveiCertifikate = new GridBagConstraints();
		gbc_btnPoveiCertifikate.insets = new Insets(0, 0, 5, 5);
		gbc_btnPoveiCertifikate.gridx = 0;
		gbc_btnPoveiCertifikate.gridy = 6;
		komunikacijaPanel.add(btnPoveiCertifikate, gbc_btnPoveiCertifikate);
			
		JPanel povuciPanel = new JPanel();
		tabbedPane.addTab("Povuci certifikat", null, povuciPanel, null);
		GridBagLayout gbl_povuciPanel = new GridBagLayout();
		gbl_povuciPanel.columnWidths = new int[] {140, 160, 30};
		gbl_povuciPanel.rowHeights = new int[] {0, 0, 0, 0, 0};
		gbl_povuciPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_povuciPanel.rowWeights = new double[]{0.0, 0.0, 1.0, 1.0};
		povuciPanel.setLayout(gbl_povuciPanel);
		
		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.gridwidth = 2;
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 0;
		povuciPanel.add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[] {250, 250, 0, 20, 0};
		gbl_panel_1.rowHeights = new int[] {30, 30, 0, 20, 0};
		gbl_panel_1.columnWeights = new double[]{1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JLabel lblOdaberiteCertifikatKoji = new JLabel("Odaberite certifikat koji zelite da povucete. OPREZ: povlacenje certifikata je nepovratna operacija! Svi certifikati potpisani ovim certifikatom mogu postati NEVALIDNI!");
		lblOdaberiteCertifikatKoji.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblOdaberiteCertifikatKoji = new GridBagConstraints();
		gbc_lblOdaberiteCertifikatKoji.gridheight = 2;
		gbc_lblOdaberiteCertifikatKoji.gridwidth = 5;
		gbc_lblOdaberiteCertifikatKoji.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lblOdaberiteCertifikatKoji.insets = new Insets(0, 0, 5, 5);
		gbc_lblOdaberiteCertifikatKoji.gridx = 0;
		gbc_lblOdaberiteCertifikatKoji.gridy = 0;
		panel_1.add(lblOdaberiteCertifikatKoji, gbc_lblOdaberiteCertifikatKoji);
		
		JPanel panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.gridwidth = 2;
		gbc_panel_2.anchor = GridBagConstraints.NORTHWEST;
		gbc_panel_2.insets = new Insets(0, 0, 5, 0);
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 1;
		povuciPanel.add(panel_2, gbc_panel_2);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{75, 190, 75, 86, 75, 109, 0};
		gbl_panel_2.rowHeights = new int[]{23, 0};
		gbl_panel_2.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panel_2.setLayout(gbl_panel_2);
		
		JLabel lblUnesiteUidCertifikata = new JLabel("Unesite UID certifikata kog zelite povuci");
		GridBagConstraints gbc_lblUnesiteUidCertifikata = new GridBagConstraints();
		gbc_lblUnesiteUidCertifikata.anchor = GridBagConstraints.WEST;
		gbc_lblUnesiteUidCertifikata.insets = new Insets(0, 0, 0, 5);
		gbc_lblUnesiteUidCertifikata.gridx = 1;
		gbc_lblUnesiteUidCertifikata.gridy = 0;
		panel_2.add(lblUnesiteUidCertifikata, gbc_lblUnesiteUidCertifikata);
		lblUnesiteUidCertifikata.setHorizontalAlignment(SwingConstants.CENTER);
		
		txtUid = new JTextField();
		GridBagConstraints gbc_txtUid = new GridBagConstraints();
		gbc_txtUid.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtUid.gridwidth = 3;
		gbc_txtUid.insets = new Insets(0, 0, 0, 5);
		gbc_txtUid.gridx = 2;
		gbc_txtUid.gridy = 0;
		panel_2.add(txtUid, gbc_txtUid);
		txtUid.setToolTipText("UID Certifikata");
		txtUid.setColumns(10);
		
		JButton btnPovuci = new JButton("Povuci certifikat");
		GridBagConstraints gbc_btnPovuci = new GridBagConstraints();
		gbc_btnPovuci.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnPovuci.gridx = 5;
		gbc_btnPovuci.gridy = 0;
		panel_2.add(btnPovuci, gbc_btnPovuci);
		
		JPanel panel_3 = new JPanel();
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.insets = new Insets(0, 0, 5, 0);
		gbc_panel_3.fill = GridBagConstraints.BOTH;
		gbc_panel_3.gridx = 0;
		gbc_panel_3.gridy = 2;
		povuciPanel.add(panel_3, gbc_panel_3);
		
		JPanel panel_4 = new JPanel();
		GridBagConstraints gbc_panel_4 = new GridBagConstraints();
		gbc_panel_4.fill = GridBagConstraints.BOTH;
		gbc_panel_4.gridx = 0;
		gbc_panel_4.gridy = 3;
		povuciPanel.add(panel_4, gbc_panel_4);
		tabbedPane.setEnabledAt(2, true);
		
		JPanel postojeciPanel = new JPanel();
		tabbedPane.addTab("Postojeći certifikati", null, postojeciPanel, null);
		
		JTree tree = new JTree();
		//GridBagConstraints gbc_tree = new GridBagConstraints();
		//gbc_tree.gridheight = 2;
		//gbc_tree.insets = new Insets(0, 0, 5, 5);
		//gbc_tree.fill = GridBagConstraints.BOTH;
		//gbc_tree.gridx = 0;
		//gbc_tree.gridy = 0;
		//postojeciPanel.add(tree, gbc_tree);
		
		table = new JTable();
		postojeciPanel.setLayout(new BoxLayout(postojeciPanel, BoxLayout.X_AXIS));
		//GridBagConstraints gbc_table = new GridBagConstraints();
		//gbc_table.insets = new Insets(0, 0, 5, 0);
		//gbc_table.gridheight = 2;
		//gbc_table.fill = GridBagConstraints.BOTH;
		//gbc_table.gridx = 1;
		//gbc_table.gridy = 0;
		//postojeciPanel.add(table, gbc_table);
		
		JSplitPane splitPane_1 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, tree, table);
		postojeciPanel.add(splitPane_1);
	    
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
	    
	    JPanel panelAplikacija = new JPanel();
	    cardPanel.add(panelAplikacija, TipCertifikata.APLIKACIJA.toString());
	    GridBagLayout gbl_panelAplikacija = new GridBagLayout();
	    gbl_panelAplikacija.columnWidths = new int[] {140, 160, 0};
	    gbl_panelAplikacija.rowHeights = new int[] {30, 30, 25, 0};
	    gbl_panelAplikacija.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
	    gbl_panelAplikacija.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
	    panelAplikacija.setLayout(gbl_panelAplikacija);
	    
	    JLabel lblNazivAplikacije = new JLabel("Naziv aplikacije");
	    lblNazivAplikacije.setHorizontalAlignment(SwingConstants.CENTER);
	    GridBagConstraints gbc_lblNazivAplikacije = new GridBagConstraints();
	    gbc_lblNazivAplikacije.fill = GridBagConstraints.BOTH;
	    gbc_lblNazivAplikacije.insets = new Insets(0, 0, 5, 5);
	    gbc_lblNazivAplikacije.gridx = 0;
	    gbc_lblNazivAplikacije.gridy = 0;
	    panelAplikacija.add(lblNazivAplikacije, gbc_lblNazivAplikacije);
	    
	    txtNazivAplikacije = new JTextField();
	    GridBagConstraints gbc_txtNazivAplikacije = new GridBagConstraints();
	    gbc_txtNazivAplikacije.insets = new Insets(0, 0, 5, 0);
	    gbc_txtNazivAplikacije.fill = GridBagConstraints.BOTH;
	    gbc_txtNazivAplikacije.gridx = 1;
	    gbc_txtNazivAplikacije.gridy = 0;
	    panelAplikacija.add(txtNazivAplikacije, gbc_txtNazivAplikacije);
	    txtNazivAplikacije.setColumns(10);
	    
	    JLabel lblOrganizacija_4 = new JLabel("Organizacija");
	    lblOrganizacija_4.setHorizontalAlignment(SwingConstants.CENTER);
	    GridBagConstraints gbc_lblOrganizacija_4 = new GridBagConstraints();
	    gbc_lblOrganizacija_4.fill = GridBagConstraints.BOTH;
	    gbc_lblOrganizacija_4.insets = new Insets(0, 0, 5, 5);
	    gbc_lblOrganizacija_4.gridx = 0;
	    gbc_lblOrganizacija_4.gridy = 1;
	    panelAplikacija.add(lblOrganizacija_4, gbc_lblOrganizacija_4);
	    
	    txtOrganizacijaAplikacije = new JTextField();
	    GridBagConstraints gbc_txtOrganizacijaAplikacije = new GridBagConstraints();
	    gbc_txtOrganizacijaAplikacije.insets = new Insets(0, 0, 5, 0);
	    gbc_txtOrganizacijaAplikacije.fill = GridBagConstraints.BOTH;
	    gbc_txtOrganizacijaAplikacije.gridx = 1;
	    gbc_txtOrganizacijaAplikacije.gridy = 1;
	    panelAplikacija.add(txtOrganizacijaAplikacije, gbc_txtOrganizacijaAplikacije);
	    txtOrganizacijaAplikacije.setColumns(10);
	    
	    JLabel lblVerzijaAplikacije = new JLabel("Verzija aplikacije");
	    lblVerzijaAplikacije.setHorizontalAlignment(SwingConstants.CENTER);
	    GridBagConstraints gbc_lblVerzijaAplikacije = new GridBagConstraints();
	    gbc_lblVerzijaAplikacije.fill = GridBagConstraints.BOTH;
	    gbc_lblVerzijaAplikacije.insets = new Insets(0, 0, 0, 5);
	    gbc_lblVerzijaAplikacije.gridx = 0;
	    gbc_lblVerzijaAplikacije.gridy = 2;
	    panelAplikacija.add(lblVerzijaAplikacije, gbc_lblVerzijaAplikacije);
	    
	    txtVerzija = new JTextField();
	    GridBagConstraints gbc_txtVerzija = new GridBagConstraints();
	    gbc_txtVerzija.fill = GridBagConstraints.BOTH;
	    gbc_txtVerzija.gridx = 1;
	    gbc_txtVerzija.gridy = 2;
	    panelAplikacija.add(txtVerzija, gbc_txtVerzija);
	    txtVerzija.setColumns(10);
		
	    JButton btnNadcertifikat = new JButton("...");
	    JLabel lblNadcertifikat = new JLabel("Nadcertifikat");
	    
	    cbTipCertifikata.setAction(new Action() {
	    	
	    	@Override
	    	public void actionPerformed(ActionEvent e) {
	    		TipCertifikata selected = (TipCertifikata) cbTipCertifikata.getSelectedItem();
	    		System.out.println(selected);
	    		CardLayout cl = (CardLayout)(cardPanel.getLayout());
	    		
	    		if (selected==TipCertifikata.CA_APLIKACIJA || selected==TipCertifikata.CA_DOMEN || selected==TipCertifikata.CA_OPREMA || selected==TipCertifikata.CA_ORGANIZACIJA || selected==TipCertifikata.CA_OSOBA) {
	    	    	lblNadcertifikat.setText("Root certifikat");
	    	    } else {
	    	    	lblNadcertifikat.setText("Nadcertifikat");
	    	    }
	    		
	    		if (selected==TipCertifikata.ROOT) {
	    	    	btnNadcertifikat.setVisible(false);
	    	    	lblNadcertifikat.setVisible(false);
	    	    	txtNadcertifikat.setVisible(false);
	    	    } else {
	    	    	btnNadcertifikat.setVisible(true);
	    	    	lblNadcertifikat.setVisible(true);
	    	    	txtNadcertifikat.setVisible(true);
	    	    }
	    		
	    		if (selected==TipCertifikata.CA_APLIKACIJA || selected==TipCertifikata.CA_DOMEN || selected==TipCertifikata.CA_OPREMA || selected==TipCertifikata.CA_ORGANIZACIJA || selected==TipCertifikata.CA_OSOBA) {
	    			selected = TipCertifikata.ROOT;
	    		}
	    		
	    	    cl.show(cardPanel, selected.toString());
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
	    btnKeyStore.setToolTipText("Lokacija za čuvanje novog privatnog ključa");
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
	    
	    JPanel panelBtn = new JPanel();
	    GridBagConstraints gbc_panelBtn = new GridBagConstraints();
	    gbc_panelBtn.insets = new Insets(0, 0, 5, 5);
	    gbc_panelBtn.fill = GridBagConstraints.BOTH;
	    gbc_panelBtn.gridx = 0;
	    gbc_panelBtn.gridy = 2;
	    kreirajPanel.add(panelBtn, gbc_panelBtn);
	    
	    JButton btnKreiraj = new JButton("Kreiraj certifikat");
	    btnKreiraj.addActionListener(new KreirajCertActionListener(frmAdminCertificateManagement,this));
	    panelBtn.add(btnKreiraj);
	    
	    
	    //setovanje pocetnog layouta
	    ((CardLayout) cardPanel.getLayout()).show(cardPanel, TipCertifikata.APLIKACIJA.toString());

	    
	}
	
	public Certifikat getCertifikatData() {
		switch ((TipCertifikata)cbTipCertifikata.getSelectedItem()) {
			case APLIKACIJA:
				return getAppCertifikat();
			case DOMEN:
				return getDomenCertifikat();
			case OPREMA:
				return getOpremaCertifikat();
			case ORGANIZACIJA:
				return getOrganizacijaCertifikat();
			case OSOBA:
				return getOsobaCertifikat();
			case CA_APLIKACIJA:
				return getCAAplikacija();
			case CA_DOMEN:
				return getCADomen();
			case CA_OPREMA:
				return getCAOprema();
			case CA_ORGANIZACIJA:
				return getCAOrganizacija();
			case CA_OSOBA:
				return getCAOsoba();
			default:
				return getRootCertifikat();
		}
	}

	private Certifikat getCAOsoba() {
		// TODO Auto-generated method stub
		return null;
	}

	private Certifikat getCAOrganizacija() {
		// TODO Auto-generated method stub
		return null;
	}

	private Certifikat getCAOprema() {
		// TODO Auto-generated method stub
		return null;
	}

	private Certifikat getCADomen() {
		// TODO Auto-generated method stub
		return null;
	}

	private Certifikat getCAAplikacija() {
		// TODO Auto-generated method stub
		return null;
	}

	private Certifikat getAppCertifikat() {
		// TODO Auto-generated method stub
		return null;
	}

	private CertifikatRoot getRootCertifikat() {
		// TODO Auto-generated method stub
		return null;
	}

	private CertifikatOsoba getOsobaCertifikat() {
		// TODO Auto-generated method stub
		return null;
	}

	private CertifikatOrganizacija getOrganizacijaCertifikat() {
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
