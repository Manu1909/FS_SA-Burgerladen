package business;
import backend.Datenbank;;

public class Unternehmen {
	
	private double gewinn;
	private double kapital;
	private double umsatz;
	private int kunden;
	public int kundenAnteil;
	private int bekanntheit;
	private int kundenzufriedenheitsVeraenderung = 0;
	private int kundenzufriedenheit;
	private String name;
	private Standort standort;
	private Kredit kredit;
	private Bestellung bestellung;
	private Burger burger;
	private Catering catering;
	private Marketing marketing = null;
	private Personal personal;

	// Konstruktor mit �bergabe von name
	public Unternehmen(String name) {
		this.name = name;
		bestellung = new Bestellung();
		burger = new Burger();
		this.personal = new Personal(4);
		kapital = Datenbank.startKapital;
	}
	
	//Getter und Setter f�r alle Attribute
	public double getGewinn() {
		return gewinn;
	}

	public void setGewinn(double gewinn) {
		this.gewinn = gewinn;
	}

	public double getKapital() {
		return kapital;
	}

	public void setKapital(double kapital) {
		this.kapital = kapital;
	}

	public double getUmsatz() {
		return umsatz;
	}

	public void setUmsatz(double umsatz) {
		this.umsatz = umsatz;
	}

	public int getBekanntheit() {
		return bekanntheit;
	}

	public void setBekanntheit(int bekanntheit) {
		this.bekanntheit = bekanntheit;
	}

	public int getKundenzufriedenheit() {
		return kundenzufriedenheit;
	}


	public int getKundenzufriedenheitsVeraenderung() {
		return kundenzufriedenheitsVeraenderung;
	}

	public void setKundenzufriedenheitsVeraenderung(int kundenzufriedenheitsVeraenderung) {
		this.kundenzufriedenheitsVeraenderung = kundenzufriedenheitsVeraenderung;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setMarketing(Marketing marketing) {
		this.marketing = marketing;
	}

	public Marketing getMarketing() {
		return marketing;
	}

	public void setKunden(int kunden) {
		this.kunden = kunden;
	}

	public int getKunden() {
		return kunden;
	}

	public Standort getStandort() {
		return standort;
	}

	public void setStandort(Standort standort) {
		this.standort = standort;
		setBekanntheit(standort.getBekanntheit());
	}
	
	public Kredit getKredit() {
		return kredit;
	}
	
	public void setKredit(Kredit kredit) {
		this.kredit = kredit;
		berechneKapital(true);
	}

	public Bestellung getBestellung() {
		return bestellung;
	}

	public Burger getBurger() {
		return burger;
	}
	
	public Catering getCatering() {
		return catering;
	}

	public void setCatering(Catering catering) {
		this.catering = catering;
	}
	
	public Personal getPersonal() {
		return personal;
	}

	public void setPersonal(Personal personal) {
		this.personal = personal;
	}

	/**
	 * Hier wird das Kapital des Unternehmens berechnet unter Ber�cksichtung
	 * ob ein Kredit gew�hlt und bereits abbezahlt wurde
	 * @param kreditWahl
	 * @return kapital
	 */
	public double berechneKapital(boolean kreditWahl){
		kapital = kapital + gewinn;
		if(kreditWahl && kredit != null){
			kapital += kredit.getHoehe();
		}
		else if(kredit != null){
			kapital -= kredit.berechneBereinigteAnnuitaet();
		}
		kapital = Math.round(kapital*100)/100;
		return kapital;
	}
	
	public void waehleKredit(Kredit k){
		setKredit(kredit);
	}


	public int berechneBurgerQualitaet(){
		return burger.berechneQualitaet(bestellung.getFleischlieferant().getQualitaet(), bestellung.getBrotlieferant().getQualitaet(), bestellung.getSalatlieferant().getQualitaet(), bestellung.getSossenlieferant().getQualitaet());
	}

	//Diese Methode prüft, ob in dieser Runde Marketing gewählt wurde und berechnet abhängig vom Marketing die neue Bekanntheit und die Kundenzufriedenheit
	public void betreibeMarketing(){
		if(marketing!=null){
			bekanntheit = marketing.berechneBekanntheit(bekanntheit);
			if(bekanntheit>100){
				bekanntheit = 100;
			}
			kundenzufriedenheitsVeraenderung = marketing.berechneKundenzufriedenheit(kundenzufriedenheitsVeraenderung);
			if(kundenzufriedenheit >100){
				kundenzufriedenheit = 100;
			}
		}
	}

	/**
	 * Hier wird die Auswirkung des Caterings auf die Bekanntheit und Kundezufriedenheit berechnet
	 */
	public void berechneCatering(){
		if(catering!=null){
			bekanntheit = catering.berechneBekanntheit(bekanntheit);
			if(bekanntheit>100){
				bekanntheit = 100;
			}
			kundenzufriedenheitsVeraenderung = catering.berechneKundenzufriedenheit(kundenzufriedenheitsVeraenderung);
			if(kundenzufriedenheit >100){
				kundenzufriedenheit = 100;
			}
		}
}
	//hier wird der Kundenanteil berechnet wobei auch mit einbezogen wird, ob in dieser Rudnde Marketing gewählt wurde
	public int berechneKundenanteil(){
		kundenAnteil = (int)(0.25*bekanntheit + 0.33* kundenzufriedenheit + 0.17*standort.getTraffic() + 0.25*burger.berechnePreisPunkte());
		if(marketing!=null){
			kundenAnteil *= (1+marketing.getKundenprozentsatz());
		}
		return kundenAnteil;
	}
	
	public double berechneUmsatz(){
		umsatz = burger.preis * kunden;
		return umsatz;
	}

	//Bei der Gewinnberechnung müssen in der ersten Runde die Gründungskosten miteinbezogen werden.
	//Wenn ein Cateringauftrag angeommen wurde müssen diese Kosten abgezogen werden
	//In dieser Methode werden auch erst die Zutaten aus dem Lager genommen, da nur hier die Zwei für 1 Verkaufaktion richtig mit einbezogen werden kann
	public double berechneGewinn(int rundenZahl){
		if(rundenZahl == 0){
			gewinn = burger.preis * kunden - berechneGruendungsKosten();
		}
		else{
			gewinn = burger.preis * kunden - berechneRundenkosten();
		}
		if(catering != null){
			gewinn +=  berechneCateringKosten(catering);
		}



		if(marketing!=null){
			if(marketing.getBezeichnung().equals("Werbung21")){
				kunden *= 2;
			}
		}
		standort.getKuehlraum().wareEntnehmen(kunden);

		gewinn = Math.round(gewinn*100)/100;
		return gewinn;
	}


	public double berechneGruendungsKosten(){
		double kosten = standort.getMiete() + standort.getInnenausstattung().getKosten() + bestellung.berechneGesamtpreis()  + personal.berechneKosten();
		if(kredit != null){
			kosten += kredit.berechneZinsaufwand();
		}
		if(marketing != null){
			kosten += marketing.getKosten();
		}
		//berechnung eines DispoKredites mit 15% Zinse
		if(kapital<0){
			kosten -= kapital*0.15/4;
		}
		kosten = Math.round(kosten*100)/100;
		return kosten;
	}

	public double berechneRundenkosten(){
		double kosten = standort.getMiete() + bestellung.berechneGesamtpreis()  + personal.berechneKosten();
		if(kredit != null){
			kosten += kredit.berechneZinsaufwand();
		}
		if(marketing != null){
			kosten += marketing.getKosten();
		}
		//berechnung eines DispoKredites mit 15% Zinse
		if(kapital<0){
			kosten -= kapital*0.15/4;
		}

		kosten = Math.round(kosten*100)/100;
		return kosten;
	}

	public double berechneCateringKosten(Catering c){
		return c.getAnzahlBurger()*(bestellung.getFleischlieferant().getPreisProGut()+bestellung.getBrotlieferant().getPreisProGut()+bestellung.getSalatlieferant().getPreisProGut()+bestellung.getSossenlieferant().getPreisProGut());
	}

	//Die Kundenzufriedenheit setzt sich aus der Preisleistung und den im Spiel anfallenden Veränderungen zusammen
	public int berechneKundenzufriedenheit(){
		kundenzufriedenheit = burger.berechnePreisleistung() + kundenzufriedenheitsVeraenderung;
		return kundenzufriedenheit;
	}

}
