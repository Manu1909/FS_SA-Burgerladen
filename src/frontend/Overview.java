package frontend;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import business.Datenbank;

public class Overview extends JFrame implements ActionListener, MouseListener {

	private String name = "";
	private String standort;
	private String innenausstattung;
	private String werbung;
	private int kredit;
	private int lagerPlatzGroesse;
	private int burgerPreis;
	private int anzahlPersonal;
	private double kapital;
	private double schulden;
	private double umsatz;
	private double gewinn;
	private static business.Unternehmen un;

	private JTextField txtKapital = new JTextField();
	private JTextField txtSchulden = new JTextField();
	private JPanel contentPane = new JPanel();
	private JFrame frame = new JFrame("Überblick für ");
	private JMenuBar bar = new JMenuBar();
	private JMenu menuCatering = new JMenu("Catering");
	private JMenu menuMarketing = new JMenu("Marketing");
	private JMenu menuPreis = new JMenu("Preis festlegen");
	private JMenu menuBestellung = new JMenu("Bestellungen");
	private JMenu menuPersonal = new JMenu("Personal");
	private JLabel lblStandort = new JLabel();
	private JLabel lblMarketing = new JLabel();
	private JLabel lblanzahlPersonal = new JLabel();
	private JPanel panel = new JPanel();
	private JTextPane txtLetztePeriode = new JTextPane();
	private JTextPane txtRangliste = new JTextPane();

	// Variablen Marketing
	private JButton btnConfirm = new JButton("Bestätigen");
	private JPanel contentPaneMarketing = new JPanel();
	private String[] marketingOptionen = { Datenbank.marketing[0].getBezeichnung(),
			Datenbank.marketing[1].getBezeichnung(), Datenbank.marketing[2].getBezeichnung() };
	private int[] bekanntheitsPlus = { Datenbank.marketing[0].getBekanntheitssteigerung(),
			Datenbank.marketing[1].getBekanntheitssteigerung(), Datenbank.marketing[2].getBekanntheitssteigerung() };
	private int[] zufriedenheitsPlus = { Datenbank.marketing[0].getKundenzufriednenheitssteigerung(),
			Datenbank.marketing[1].getKundenzufriednenheitssteigerung(),
			Datenbank.marketing[2].getKundenzufriednenheitssteigerung() };
	private double[] marketingKosten = { Datenbank.marketing[0].getKosten(), Datenbank.marketing[1].getKosten(),
			Datenbank.marketing[2].getKosten() };
	private JList listMarketing = new JList(marketingOptionen);
	private JTextPane txtEffekte = new JTextPane();
	private JFrame frameMarketing = new JFrame("Burger im Quadrat - Marketing");

	// Variablen Preisfenster
	private JFrame framePreis = new JFrame();
	private JPanel contentPanePreis = new JPanel();
	private JTextField txtPreis = new JTextField();
	private JButton btnPreis = new JButton();

	// Variabeln Personalfenster
	private JFrame framePersonal = new JFrame();
	private JPanel contentPanePersonal = new JPanel();
	private JTextField txtFeuern = new JTextField();
	private JTextField txtEinstellen = new JTextField();
	private JButton btnAbschicken = new JButton("Abschicken");

	// Variablen Catering-Fenster
	private JFrame frameCatering = new JFrame();
	private JPanel contentPaneCatering = new JPanel();
	private JButton btnAngebotAbgeben = new JButton();
	private JButton btnAuftragAblehnen = new JButton();
	private JTextField txtAngebotSumme = new JTextField();

	// Variablen Bestellungs-Fenster
	private JFrame frameBestellungen = new JFrame();
	private JPanel contentPaneBestellungen = new JPanel();
	private JTextField BurgerZahl = new JTextField(300);
	private JButton btnBestellungAbschicken = new JButton("Bestellung abschicken");
	private String[] fleischOpt = { "Sutro Großlieferant", "Metzgerei Zorn", "Ernas Fleischerei" };
	private JList listFleisch = new JList(fleischOpt);
	private JLabel lblFleischErk = new JLabel("");
	private String[] broetchenOpt = { "Sutro Großlieferant", "Brotfabrik Mannheim", "Tram GmbH" };
	private JList listBroetchen = new JList(broetchenOpt);
	private JLabel lblBroetchenErk = new JLabel("");
	private String[] salatOpt = { "Sutro Großlieferant", "Farmerland", "Tram GmbH" };
	private JList listSalat = new JList(salatOpt);
	private JLabel lblSalatErk = new JLabel("");;
	private String[] saucenOpt = { "Sutro Großlieferant", "Saucerie de Lyon", "Tram GmbH" };
	private JList listSauce = new JList(saucenOpt);
	private JLabel lblSaucenErk = new JLabel("");;
	private JButton btnGK = new JButton("Gesamtkosten berechnen");
	private JLabel lblGK = new JLabel("n/a");
	// Werte Fleisch
	private double[] kostenFl = { Datenbank.fl[0].getPreisProGut(), Datenbank.fl[1].getPreisProGut(),
			Datenbank.fl[2].getPreisProGut() };
	private int[] qualitaetFl = { Datenbank.fl[0].getQualitaet(), Datenbank.fl[1].getQualitaet(),
			Datenbank.fl[2].getQualitaet() };
	private int[] risikoquoteFl = { Datenbank.fl[0].getRisikoQuote(), Datenbank.fl[1].getRisikoQuote(),
			Datenbank.fl[2].getRisikoQuote() };
	// Werte Broetchen
	private double[] kostenBr = { Datenbank.bl[0].getPreisProGut(), Datenbank.bl[1].getPreisProGut(),
			Datenbank.bl[2].getPreisProGut() };
	private int[] qualitaetBr = { Datenbank.bl[0].getQualitaet(), Datenbank.bl[1].getQualitaet(),
			Datenbank.bl[2].getQualitaet(), };
	// Werte Salat
	private double[] kostenSa = { Datenbank.sal[0].getPreisProGut(), Datenbank.sal[1].getPreisProGut(),
			Datenbank.sal[2].getPreisProGut() };
	private int[] qualitaetSa = { Datenbank.sal[0].getQualitaet(), Datenbank.sal[1].getQualitaet(),
			Datenbank.sal[2].getQualitaet() };
	// Werte Sauce
	private double[] kostenSau = { Datenbank.sol[0].getPreisProGut(), Datenbank.sol[1].getPreisProGut(),
			Datenbank.sol[2].getPreisProGut() };
	private int[] qualitaetSau = { Datenbank.sol[0].getQualitaet(), Datenbank.sol[1].getQualitaet(),
			Datenbank.sol[2].getQualitaet() };

	public Overview(business.Unternehmen un) {
		this.un = un;
		this.name = un.getName();
		this.standort = un.getStandort().getLage();
		this.innenausstattung = un.getStandort().getInnenausstattung().getBezeichnung();
		this.kredit = un.getKredit().getHoehe();
		this.kapital = un.getKapital();
		this.anzahlPersonal = un.getPersonal().berechneAnzahl();
		try {
			this.umsatz = un.getUmsatz();
			this.gewinn = un.getGewinn();
			this.lagerPlatzGroesse = un.getStandort().getKuehlraum().getLagerGroesse();
			this.werbung = un.getMarketing().getBezeichnung();
			this.burgerPreis = un.getBurger().getPreis();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		zeigeFensterOverview();
	}

	public void zeigeFensterOverview() {
		frame.setTitle("Überblick für " + name);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));

		JLabel lblInfoText = new JLabel(
				"<html><body><p>Dieses Fenster bietet ihnen eine Übersicht" + " über die wichtigsten "
						+ "Statistiken ihres Unternehmens. " + "Neben Kapital und Schulden erhalten Sie einen "
						+ "Einblick in die Rangliste und k�nnen mit der oberen Men�leiste"
						+ " zwischen den einzelnen Optionen navigieren" + "</p></body></html>");
		lblInfoText.setBounds(180, 11, 250, 266);
		panel.add(lblInfoText);

		JLabel lblKapital = new JLabel("Kapital: ");
		lblKapital.setBounds(580, 66, 46, 14);
		panel.add(lblKapital);

		JLabel lblSchulden = new JLabel("Schulden: ");
		lblSchulden.setBounds(580, 100, 75, 14);
		panel.add(lblSchulden);

		lblStandort.setText("<html><body><p>Standort: " + standort + "</p></body></html>");
		lblStandort.setBounds(580, 140, 130, 50);
		panel.add(lblStandort);

		lblMarketing.setText("Marketing-Aktion: " + werbung);
		lblMarketing.setBounds(580, 160, 130, 50);
		panel.add(lblMarketing);
		
		lblanzahlPersonal.setText("Anzahl Mitarbeiter: " + anzahlPersonal);
		lblanzahlPersonal.setBounds(300, 200, 120, 30);
		panel.add(lblanzahlPersonal);

		JLabel lblRangliste = new JLabel("<html><body><h3>Rangliste:</h3></body></html>");
		lblRangliste.setBounds(640, 197, 75, 40);
		panel.add(lblRangliste);

		JLabel lblLetztePeriode = new JLabel("<html><body><h3>Letzte Periode:</h3></body></html>");
		lblLetztePeriode.setBounds(130, 210, 120, 50);
		panel.add(lblLetztePeriode);

		txtKapital = new JTextField();
		txtKapital.setText("" + kapital);
		txtKapital.setBounds(650, 63, 86, 20);
		txtKapital.setEditable(false);
		panel.add(txtKapital);
		txtKapital.setColumns(10);

		txtSchulden = new JTextField();
		txtSchulden.setBounds(650, 100, 86, 20);
		txtSchulden.setEditable(false);
		panel.add(txtSchulden);
		txtSchulden.setColumns(10);

		JButton btnRundeBeenden = new JButton("Runde beenden");
		btnRundeBeenden.setBounds(344, 390, 150, 23);
		panel.add(btnRundeBeenden);

		txtLetztePeriode.setText("Umsatz: " + umsatz + "€\nGewinn: " + gewinn + "€");
		txtLetztePeriode.setBounds(130, 250, 151, 80);
		txtLetztePeriode.setEditable(false);
		panel.add(txtLetztePeriode);

		txtRangliste.setText("1. Spieler 1 \n2. Spieler 2\n3. Spieler 2\n4. Spieler 4");
		txtRangliste.setBounds(640, 248, 150, 69);
		txtRangliste.setEditable(false);
		panel.add(txtRangliste);

		bar.setBounds(280, 10, 335, 20);
		menuCatering.addMouseListener(this);
		menuPreis.addMouseListener(this);
		menuMarketing.addMouseListener(this);
		menuBestellung.addMouseListener(this);
		menuPersonal.addMouseListener(this);
		bar.add(menuCatering);
		bar.add(menuPreis);
		bar.add(menuMarketing);
		bar.add(menuBestellung);
		bar.add(menuPersonal);
		panel.add(bar);

		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		frame.setContentPane(contentPane);
		frame.setResizable(false);
		frame.add(panel);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setBounds(1, 1, 900, 500);
		frame.setVisible(true);
	}

	public void zeigeFensterPreis() {
		contentPanePreis = new JPanel();
		contentPanePreis.setBorder(new EmptyBorder(5, 5, 5, 5));
		framePreis.setContentPane(contentPanePreis);
		contentPanePreis.setLayout(null);

		txtPreis.setBounds(43, 43, 40, 30);
		contentPanePreis.add(txtPreis);

		btnPreis.setText("Bestätigen");
		btnPreis.setBounds(100, 50, 120, 40);
		btnPreis.addActionListener(this);
		contentPanePreis.add(btnPreis);

		frame.setFocusableWindowState(false);
		framePreis.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		framePreis.setBounds(100, 100, 450, 300);
		framePreis.setVisible(true);
	}

	public void zeigeFensterMarketing() {
		contentPaneMarketing = new JPanel();
		contentPaneMarketing.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPaneMarketing.setLayout(null);

		listMarketing.setBounds(106, 62, 80, 54);
		listMarketing.setSelectedIndex(0);
		listMarketing.addMouseListener(this);
		contentPaneMarketing.add(listMarketing);

		int p = listMarketing.getSelectedIndex();
		txtEffekte.setText("Bekanntheitsgrad: +" + bekanntheitsPlus[p] + "\nKundenzufriedenheit: +"
				+ zufriedenheitsPlus[p] + "\nKosten: " + marketingKosten[p] + "€");
		txtEffekte.setBounds(192, 11, 150, 50);
		txtEffekte.setEditable(false);
		contentPaneMarketing.add(txtEffekte);

		btnConfirm.setBounds(171, 175, 100, 23);
		btnConfirm.addActionListener(this);
		contentPaneMarketing.add(btnConfirm);

		frame.setFocusableWindowState(false);
		frameMarketing.setContentPane(contentPaneMarketing);
		frameMarketing.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameMarketing.setBounds(100, 100, 450, 300);
		frameMarketing.setVisible(true);
	}

	private void zeigeFensterPersonal() {
		contentPanePersonal = new JPanel();
		contentPanePersonal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPanePersonal);
		contentPanePersonal.setLayout(null);

		JLabel lblPersonal = new JLabel("Personal");
		lblPersonal.setHorizontalAlignment(SwingConstants.CENTER);
		lblPersonal.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPersonal.setBounds(10, 11, 442, 14);
		contentPanePersonal.add(lblPersonal);

		JLabel lblPersonalerk = new JLabel("PersonalErk");
		lblPersonalerk.setBounds(77, 63, 301, 61);
		contentPanePersonal.add(lblPersonalerk);

		JLabel lblEinstellen = new JLabel("Personal einstellen");
		lblEinstellen.setBounds(59, 134, 112, 14);
		contentPanePersonal.add(lblEinstellen);

		txtEinstellen = new JTextField();
		txtEinstellen.setText("0");
		txtEinstellen.setBounds(240, 131, 161, 20);
		contentPanePersonal.add(txtEinstellen);
		txtEinstellen.setColumns(10);

		JLabel lblFeuern = new JLabel("Personal feuern");
		lblFeuern.setBounds(59, 178, 97, 14);
		contentPanePersonal.add(lblFeuern);

		txtFeuern = new JTextField();
		txtFeuern.setText("0");
		txtFeuern.setBounds(240, 175, 161, 20);
		contentPanePersonal.add(txtFeuern);
		txtFeuern.setColumns(10);

		btnAbschicken.setBounds(199, 269, 89, 23);
		btnAbschicken.addActionListener(this);
		contentPanePersonal.add(btnAbschicken);

		frame.setFocusableWindowState(false);
		framePersonal.setContentPane(contentPanePersonal);
		framePersonal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		framePersonal.setBounds(100, 100, 501, 364);
		framePersonal.setVisible(true);
	}

	private void zeigeFensterCatering() {

	}

	private void zeigeFensterBestellung() {
		// contentPane-Settings
		contentPaneBestellungen = new JPanel();
		contentPaneBestellungen.setBackground(UIManager.getColor("FormattedTextField.inactiveBackground"));
		contentPaneBestellungen.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPaneBestellungen.setLayout(null);

		// Überschrift
		JLabel lblBestellungen = new JLabel("Bestellungen");
		lblBestellungen.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblBestellungen.setBounds(5, 5, 574, 33);
		lblBestellungen.setHorizontalAlignment(SwingConstants.CENTER);
		contentPaneBestellungen.add(lblBestellungen);

		// Burger
		JLabel lblBestellung = new JLabel("Ich bestelle für");
		lblBestellung.setBounds(27, 76, 116, 14);
		contentPaneBestellungen.add(lblBestellung);
		BurgerZahl.setBounds(120, 73, 86, 20);
		contentPaneBestellungen.add(BurgerZahl);
		BurgerZahl.setColumns(10);
		JLabel lblBurger = new JLabel("Burger");
		lblBurger.setBounds(240, 76, 46, 14);
		contentPaneBestellungen.add(lblBurger);

		// Fleisch
		listFleisch.setBounds(120, 104, 137, 62);
		listFleisch.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listFleisch.addMouseListener(this);
		contentPaneBestellungen.add(lblFleischErk);
		listFleisch.setSelectedIndex(0);
		int a = listFleisch.getSelectedIndex();
		lblFleischErk.setText("<html><body><p>" + "Kosten pro Burger: " + kostenFl[a] + " €" + "<br> Qualität: "
				+ qualitaetFl[a] + " Punkte" + "<br> Gammelfleischrisiko: " + risikoquoteFl[a] + " Prozent "
				+ "</p><body></html>");
		contentPaneBestellungen.add(listFleisch);
		JLabel lblFleisch = new JLabel("Fleisch");
		lblFleisch.setBounds(27, 101, 83, 14);
		contentPaneBestellungen.add(lblFleisch);
		lblFleischErk.setBounds(284, 104, 202, 48);

		// Brötchen
		JLabel lblBroetchen = new JLabel("Brötchen");
		lblBroetchen.setBounds(27, 193, 83, 14);
		contentPaneBestellungen.add(lblBroetchen);
		listBroetchen.setBounds(120, 193, 137, 62);
		listBroetchen.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listBroetchen.addMouseListener(this);
		listBroetchen.setSelectedIndex(0);
		int b = listBroetchen.getSelectedIndex();
		lblBroetchenErk.setText("<html><body><p>Kosten pro Burger: " + kostenBr[b] + " €" + "<br> Qualität: "
				+ qualitaetBr[b] + " Punkte. <body></html>");
		contentPaneBestellungen.add(listBroetchen);
		lblBroetchenErk.setBounds(284, 193, 202, 62);
		contentPaneBestellungen.add(lblBroetchenErk);

		// Salatwahl
		JLabel lblSalat = new JLabel("Salat");
		lblSalat.setBounds(27, 299, 46, 14);
		contentPaneBestellungen.add(lblSalat);
		listSalat.setBounds(120, 287, 137, 62);
		listSalat.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listSalat.addMouseListener(this);
		listSalat.setSelectedIndex(0);
		int c = listSalat.getSelectedIndex();
		lblSalatErk.setText("<html><body><p>Kosten pro Burger: " + kostenSa[c] + " €" + "<br> Qualität: "
				+ qualitaetSa[c] + " Punkte. <body></html>");
		contentPaneBestellungen.add(listSalat);
		lblSalatErk.setBounds(284, 287, 202, 62);
		contentPaneBestellungen.add(lblSalatErk);

		// Sauce
		JLabel lblSauce = new JLabel("Sauce");
		lblSauce.setBounds(27, 398, 46, 14);
		contentPaneBestellungen.add(lblSauce);
		listSauce.setBounds(120, 389, 137, 62);
		listSauce.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listSauce.addMouseListener(this);
		listSauce.setSelectedIndex(0);
		int d = listSauce.getSelectedIndex();
		lblSaucenErk.setText("<html><body><p>Kosten pro Burger: " + kostenSau[d] + " €" + "<br> Qualität: "
				+ qualitaetSau[d] + " Punkte. <body></html>");
		contentPaneBestellungen.add(listSauce);
		lblSaucenErk.setBounds(284, 389, 202, 62);
		contentPaneBestellungen.add(lblSaucenErk);

		// Gesamtkosten
		btnGK.setBounds(120, 493, 179, 23);
		contentPaneBestellungen.add(btnGK);
		lblGK.setBounds(392, 497, 46, 14);
		contentPaneBestellungen.add(lblGK);

		// Abschicken
		btnBestellungAbschicken.setBounds(222, 552, 147, 23);
		contentPaneBestellungen.add(btnBestellungAbschicken);

		// Frame-Settings
		frame.setFocusableWindowState(false);
		frameBestellungen.setContentPane(contentPaneBestellungen);
		frameBestellungen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameBestellungen.setBounds(100, 100, 600, 650);
		frameBestellungen.setVisible(true);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		Object s = e.getSource();
		// Fenster öffnen
		if (s == menuMarketing) {
			zeigeFensterMarketing();
		}
		if (s == menuPreis) {
			zeigeFensterPreis();
		}
		if (s == menuBestellung) {
			zeigeFensterBestellung();
		}
		if (s == menuCatering) {
			zeigeFensterCatering();
		}
		if (s == menuPersonal) {
			zeigeFensterPersonal();
		}

		//
		if (s == listMarketing) {
			int p = listMarketing.getSelectedIndex();
			txtEffekte.setText("Bekanntheitsgrad: +" + bekanntheitsPlus[p] + "\nKundenzufriedenheit: +"
					+ zufriedenheitsPlus[p] + "\nKosten: " + marketingKosten[p] + "€");
		}
		if (s == listFleisch) {
			int a = listFleisch.getSelectedIndex();
			lblFleischErk.setText("<html><body><p>Kosten pro Burger: " + kostenFl[a] + " €" + "<br> Qualität: "
					+ qualitaetFl[a] + " Punkte" + "<br> Gammelfleischrisiko: " + risikoquoteFl[a]
					+ " Prozent </p><body></html>");
		} else if (s == listBroetchen) {
			int b = listBroetchen.getSelectedIndex();
			lblBroetchenErk.setText("<html><body><p>Kosten pro Burger: " + kostenBr[b] + " €" + "<br> Qualität: "
					+ qualitaetBr[b] + " Punkte. <body></html>");
		} else if (s == listSalat) {
			int c = listSalat.getSelectedIndex();
			lblSalatErk.setText("<html><body><p>Kosten pro Burger: " + kostenSa[c] + " €" + "<br> Qualität: "
					+ qualitaetSa[c] + " Punkte. <body></html>");
		} else if (s == listSauce) {
			int d = listSauce.getSelectedIndex();
			lblSaucenErk.setText("<html><body><p>Kosten pro Burger: " + kostenSau[d] + " €" + "<br> Qualität: "
					+ qualitaetSau[d] + " Punkte. <body></html>");
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object s = e.getSource();
		if (s == btnConfirm) {
			frameMarketing.setVisible(false);
			un.setMarketing(Controller.Controller.waehleMarketing(listMarketing.getSelectedIndex()));
			this.werbung = un.getMarketing().getBezeichnung();
			lblMarketing.setText("Marketing-Aktion:" + werbung);
			frame.setFocusableWindowState(true);
			frameMarketing.dispose();
		}
		if (s == btnPreis) {
			framePreis.setVisible(false);
			un.getBurger().setPreis(Integer.parseInt(txtPreis.getText()));
			frame.setFocusableWindowState(true);
			framePreis.dispose();
		}
		if(s==btnAbschicken)
		{
			framePersonal.setVisible(false);
//			int alt = un.getPersonal().getAnzahlAngestellte();
//			int neu = Integer.parseIntInteger.parseInt(txtFeuern.getText());
			un.getPersonal().erhoeheAnzahl(Integer.parseInt(txtEinstellen.getText()));
			un.getPersonal().feuern(Integer.parseInt(txtFeuern.getText()));
			
//			lblanzahlPersonal.setText("Personal: " + un.getPersonal().berechneAnzahl());
			frame.setFocusableWindowState(true);
			framePersonal.dispose();
		}
//		if(s == btn)
	}
}
