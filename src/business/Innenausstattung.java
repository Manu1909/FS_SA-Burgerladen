package business;

public class Innenausstattung {
	
	private String bezeichnung;
	private int groeßeKundenpool;
	private double kosten;
	
	
	public Innenausstattung(String bezeichnung, int kundenpool, int kosten){
		this.bezeichnung = bezeichnung;
		this.groeßeKundenpool = kundenpool;
		this.kosten = kosten;
	}
	
	public String getBezeichnung() {
		return bezeichnung;
	}

	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}

	public int getGroeßeKundenpool() {
		return groeßeKundenpool;
	}
	public void setGroeßeKundenpool(int groeßeKundenpool) {
		this.groeßeKundenpool = groeßeKundenpool;
	}
	public double getKosten() {
		return kosten;
	}
	public void setKosten(double kosten) {
		this.kosten = kosten;
	}
}
