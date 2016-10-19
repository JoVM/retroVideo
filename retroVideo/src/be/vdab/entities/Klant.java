package be.vdab.entities;

public class Klant {
	private int id;
	private String familienaam;
	private String voornaam;
	private String straatNummer;
	private String postcode;
	private String gemeente;

	public Klant() {

	}

	public Klant(int id, String familienaam, String voornaam, String straatNummer, String postcode, String gemeente) {
		setFamilienaam(familienaam);
		setGemeente(gemeente);
		setId(id);
		setPostcode(postcode);
		setStraatNummer(straatNummer);
		setVoornaam(voornaam);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		if (id < 0) {
			throw new IllegalArgumentException("klant: id moet positief zijn.");
		}
		this.id = id;
	}

	public String getFamilienaam() {
		return familienaam;
	}

	public void setFamilienaam(String familienaam) {
		if (familienaam == null || familienaam.isEmpty()) {
			throw new IllegalArgumentException("klant: familienaam moet ingeuld zijn.");
		}
		this.familienaam = familienaam;
	}

	public String getVoornaam() {
		return voornaam;
	}

	public void setVoornaam(String voornaam) {
		if (voornaam == null || voornaam.isEmpty()) {
			throw new IllegalArgumentException("klant: voornaam moet ingevuld zijn.");
		}
		this.voornaam = voornaam;
	}

	public String getStraatNummer() {
		return straatNummer;
	}

	public void setStraatNummer(String straatNummer) {
		if (straatNummer == null || straatNummer.isEmpty()) {
			throw new IllegalArgumentException("klant: straatNummer moet ingevuld zijn.");
		}
		this.straatNummer = straatNummer;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		if (postcode == null || postcode.isEmpty()) {
			throw new IllegalArgumentException("klant: postcode moet ingevuld zijn.");
		}
		this.postcode = postcode;
	}

	public String getGemeente() {
		return gemeente;
	}

	public void setGemeente(String gemeente) {
		if (gemeente == null || gemeente.isEmpty()) {
			throw new IllegalArgumentException("klant: gemeente moet ingevuld zijn.");
		}
		this.gemeente = gemeente;
	}

	public static boolean isNaamValid(String naam) {
		return naam != null && !naam.isEmpty() && !naam.contains("*");
	}

}
