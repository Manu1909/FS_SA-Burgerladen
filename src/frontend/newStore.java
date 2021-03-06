package frontend;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
//import java.util.Locale.LanguageRange;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import backend.Datenbank;

public class newStore extends JFrame implements ActionListener, MouseListener {
	private int n;

	private JFrame frame = new JFrame("Burger im Quadrat -  Laden gründen");
	private JPanel contentPane = new JPanel();

	private String name = "";

	private String[] standortOptionen = { Datenbank.standorte[0].getLage(), Datenbank.standorte[1].getLage(),
			Datenbank.standorte[2].getLage(), Datenbank.standorte[3].getLage() };
	private String[] innenausstattungOptionen = { Datenbank.i[0].getBezeichnung(), Datenbank.i[1].getBezeichnung(),
			Datenbank.i[2].getBezeichnung() };
	private String[] lagerraumOptionen = { "Kühlraum 1", "Kühlraum 2", "Kühlraum 3" };
	private String[] kreditOptionen = { "" + Datenbank.k[0].getHoehe(), "" + Datenbank.k[1].getHoehe(),
			"" + Datenbank.k[2].getHoehe(), "" + 0 };

	private JList listStandort = new JList(standortOptionen);
	private JList listInnenausstattung = new JList(innenausstattungOptionen);
	private JList listKredit = new JList(kreditOptionen);
	private JList listKuehlraum = new JList(lagerraumOptionen);

	private JButton btnConfirm = new JButton("Bestätigen");

	private business.Unternehmen un;

	// Werte für den Standort
	private double[] miete = { Datenbank.standorte[0].getMiete(), Datenbank.standorte[1].getMiete(),
			Datenbank.standorte[2].getMiete(), Datenbank.standorte[3].getMiete() };
	private int traffic[] = { Datenbank.standorte[0].getTraffic(), Datenbank.standorte[1].getTraffic(),
			Datenbank.standorte[2].getTraffic(), Datenbank.standorte[3].getTraffic() };
	private int[] bekanntheit = { Datenbank.standorte[0].getBekanntheit(), Datenbank.standorte[1].getBekanntheit(),
			Datenbank.standorte[2].getBekanntheit(), Datenbank.standorte[3].getBekanntheit() };

	// Werte für den Kredit
	private double zins[] = { Datenbank.k[0].getZinssatz(), Datenbank.k[1].getZinssatz(),
			Datenbank.k[2].getZinssatz() };
	private int laufzeit[] = { Datenbank.k[0].getLaufzeit(), Datenbank.k[1].getLaufzeit(),
			Datenbank.k[2].getLaufzeit() };

	// Werte für das Mobiliar
	private double[] kostenMobiliar = { Datenbank.i[0].getKosten(), Datenbank.i[1].getKosten(),
			Datenbank.i[2].getKosten() };

	// Werte für das Lager
	private int[] kapazitaet = { Datenbank.kuehlraeume[0].getLagerGroesse(), Datenbank.kuehlraeume[1].getLagerGroesse(),
			Datenbank.kuehlraeume[2].getLagerGroesse() };
	private double[] lagerMiete = { Datenbank.kuehlraeume[0].getMietZusatzKosten(),
			Datenbank.kuehlraeume[1].getMietZusatzKosten(), Datenbank.kuehlraeume[2].getMietZusatzKosten() };

	private JLabel tipLocations = new JLabel();
	private JLabel tipInterior = new JLabel();
	private JLabel tipCredit = new JLabel();
	private JLabel tipStorage = new JLabel();

	public static void main(String[] args) {
		// newStore store = new newStore("");
	}

	public newStore(String name, int n) {
		this.name = name;
		this.n = n;
		this.un = Controller.Controller.getUnternehmen(n);
		buildWindow();
	}

	///////////// Frame aufstellen////////////
	public void buildWindow() {
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		frame.setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JLabel heading = new JLabel("<html><body><h2>" + name + "</h2></body></html>");
		heading.setBounds(381, 0, 220, 49);
		panel.add(heading);

		// Abschnitt Standort
		JLabel infoLocation = new JLabel(
				"<html><body><h3>Schritt 2:</h3><p>Wählen Sie hier den Standort aus:</p></body></html>");
		infoLocation.setBounds(250, 39, 257, 82);
		panel.add(infoLocation);

		listStandort.setBounds(250, 109, 90, 75);
		listStandort.setSelectedIndex(0);
		listStandort.addMouseListener(this);
		panel.add(listStandort);

		int p = listStandort.getSelectedIndex();
		tipLocations.setText("<html><body><p>Traffic: " + traffic[p] + "<br> Bekanntheitgrad: " + bekanntheit[p]
				+ "<br> Miete: " + miete[p] + "€</p></body></hmtl>");
		tipLocations.setBounds(350, 117, 138, 55);
		panel.add(tipLocations);

		listKuehlraum.setBounds(520, 122, 112, 54);
		listKuehlraum.addMouseListener(this);
		listKuehlraum.setSelectedIndex(0);
		panel.add(listKuehlraum);

		int x = listKuehlraum.getSelectedIndex();
		tipStorage.setBounds(640, 115, 130, 60);
		tipStorage.setText("<html><body><p>Lagereinheiten: " + kapazitaet[x] + "<br>Mietzusatz: " + lagerMiete[x]
				+ "</p></body></html>");
		panel.add(tipStorage);

		/////// Abschnitt Inneneinrichtung
		JLabel infoInterior = new JLabel("<html><body><p>Wählen Sie die Inneneinrichtung:</p></body></html>");
		infoInterior.setBounds(250, 170, 257, 82);
		panel.add(infoInterior);

		listInnenausstattung.setBounds(250, 222, 90, 56);
		listInnenausstattung.setSelectedIndex(0);
		listInnenausstattung.addMouseListener(this);
		panel.add(listInnenausstattung);

		int n = listInnenausstattung.getSelectedIndex();
		tipInterior.setText("<html><body><p>Kosten: " + kostenMobiliar[n] + "€</p></body></hmtl>");
		tipInterior.setBounds(350, 220, 138, 55);
		panel.add(tipInterior);

		// Abschnitt Kredit
		JLabel infoCredit = new JLabel("<html><body><p>Optional: Nehmen Sie einen Kredit auf:</p></body></html>");
		infoCredit.setBounds(250, 268, 257, 82);
		panel.add(infoCredit);

		listKredit.setSelectedIndex(0);
		listKredit.addMouseListener(this);
		listKredit.setBounds(250, 320, 90, 72);
		panel.add(listKredit);

		int m = listKredit.getSelectedIndex();
		tipCredit.setBounds(350, 326, 138, 55);
		tipCredit.setText("<html><body><p>Zins: " + zins[m] + "<br> Laufzeit: " + laufzeit[m] + "</p></body></hmtl>");
		panel.add(tipCredit);

		/////////////////////
		btnConfirm.setBounds(380, 420, 95, 23);
		btnConfirm.addActionListener(this);
		panel.add(btnConfirm);

		try {
			BufferedImage io = ImageIO.read(getClass().getResource("/resources/logoJavaBG.png"));
			ImageIcon icon = new ImageIcon(io);
			JLabel label = new JLabel(icon);
			label.setBounds(0,0, icon.getIconWidth(), icon.getIconHeight());
			panel.add(label);
		} catch (Exception e) {
		}
		
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 900, 500);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	///////////////// Listener-Methoden
	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) { //Infotexte gemäß Listenauswahl ändenr
		Object s = e.getSource();
		if (s == listStandort) {
			int p = listStandort.getSelectedIndex();
			tipLocations.setText("<html><body><p>Traffic: " + traffic[p] + "<br> Bekanntheitgrad: " + bekanntheit[p]
					+ "<br> Miete: " + miete[p] + "€</p></body></hmtl>");
		}
		if (s == listInnenausstattung) {
			int p = listInnenausstattung.getSelectedIndex();
			tipInterior.setText("<html><body><p>Kosten: " + kostenMobiliar[p] + "€</p></body></hmtl>");
		}
		if (s == listKredit) {
			int p = listKredit.getSelectedIndex();
			if (p < 3) {
				tipCredit.setText(
						"<html><body><p>Zins: " + zins[p] + "<br> Laufzeit: " + laufzeit[p] + "</p></body></hmtl>");
			} else {
				tipCredit.setText("");
			}
		}
		if (s == listKuehlraum) {
			int p = listKuehlraum.getSelectedIndex();
			tipStorage.setText("<html><body><p>Lagereinheiten: " + kapazitaet[p] + "<br>Mietzusatz: " + lagerMiete[p]
					+ "</p></body></html>");
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object s = e.getSource();
		if (s == btnConfirm) {
			Controller.Controller.getUnternehmen(n).setName(name);
			Controller.Controller.getUnternehmen(n)
					.setStandort(Controller.Controller.waehleStandort(listStandort.getSelectedIndex()));
			Controller.Controller.getUnternehmen(n).getStandort()
					.setInnenausstattung(Controller.Controller.waehleInnenausstattung(listInnenausstattung.getSelectedIndex()));
			if (listKredit.getSelectedIndex() <= 2)
				Controller.Controller.getUnternehmen(n)
						.setKredit(Controller.Controller.waehleKredit(listKredit.getSelectedIndex()));
			else
				 Controller.Controller.getUnternehmen(n).setKredit(Controller.Controller.waehleKredit(-1));
			Controller.Controller.getUnternehmen(n).getStandort()
					.setKuehlraum(Controller.Controller.waehleKuhlraum(listKuehlraum.getSelectedIndex()));
			un = Controller.Controller.getUnternehmen(n);
			Overview overview = new Overview(n);
			frame.setVisible(false);
			frame.dispose();
		}
	}
}
