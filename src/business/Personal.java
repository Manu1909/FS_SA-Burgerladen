package business;

public class Personal {
	
	public int anzahlAngestellte;
	public int anzahlGefeuerte;
	public int hoechstanzahl = 15;
	public int mindestanzahl = 4;
	public int burgerKapazitaet = 200*3;
	public int kapazitaet;
	public int gehalt = 1400*3;
	public int kosten;
	
	public Personal(int anzahlAngestellte){
		this.anzahlAngestellte = anzahlAngestellte;
	}
	
	public void setAnzahlAngestellte(int anzahlAngestellte) {
		this.anzahlAngestellte = anzahlAngestellte;
	}
	public void setAnzahlGefeuerte(int anzahlGefeuerte) {
		this.anzahlGefeuerte = anzahlGefeuerte;
	}
	public void setBurgerKapazitaet(int burgerKapazitaet) {
		this.burgerKapazitaet = burgerKapazitaet;
	}
	public int getAnzahlAngestellte() {
		return anzahlAngestellte;
	}
	public int getAnzahlGefeuerte() {
		return anzahlGefeuerte;
	}
	public int getBurgerKapazitaet() {
		return burgerKapazitaet;
	}

	public int erhoeheAnzahl(int i){ //Einstellung von Personal wird durch eine bestimmte Höchstanzahl eingeschränkt
		if (anzahlAngestellte  + i <= hoechstanzahl){
			anzahlAngestellte = anzahlAngestellte + i;
		} else {
			System.out.println("Höchstanzahl an Personal erreicht. Anzahl der Angestellten bleibt gleich.");
		}
		return anzahlAngestellte;
	}
	public int berechneAnzahl(){ //wird zum Beginn der Periode aufgerufen und aktualisiert die Anzahl an Personal
		anzahlAngestellte = anzahlAngestellte-anzahlGefeuerte;
		return anzahlAngestellte;
	}
	public double berechneKosten(){
		kosten = anzahlAngestellte*gehalt;
		return kosten;
	}
	public int feuern(int anzahl){ //Feuern von Personal wird durch eine bestimmte Mindestanzahl eingeschränkt
		if (anzahlAngestellte - anzahl >= mindestanzahl){
			anzahlGefeuerte = anzahl;
		} else {
			System.out.println("Mindestanzahl an Personal unterschritten. Keiner wurde gefeuert.");
		}
		return anzahlGefeuerte;
	}
	public int berechneKapazitaet(){
		kapazitaet = anzahlAngestellte*burgerKapazitaet;
		return kapazitaet;
	}
}
