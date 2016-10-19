package be.vdab.entities;

import java.math.BigDecimal;

public class Film {
	private int id;
	private int genreid;
	private String titel;
	private int voorraad;
	private int gereserveerd;
	private BigDecimal prijs;

	public Film(int id, int genreid, String titel, int voorraad, int gereserveerd, BigDecimal prijs) {
		setGenreid(genreid);
		setGereserveerd(gereserveerd);
		setPrijs(prijs);
		setId(id);
		setTitel(titel);
		setVoorraad(voorraad);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		if (id <= 0) {
			throw new IllegalArgumentException("film: id moet positief zijn.");
		}
		this.id = id;
	}

	public int getGenreid() {
		return genreid;
	}

	public void setGenreid(int genreid) {
		if (genreid <= 0) {
			throw new IllegalArgumentException("film: genreId moet positief zijn.");
		}
		this.genreid = genreid;
	}

	public String getTitel() {
		return titel;
	}

	public void setTitel(String titel) {
		if (titel == null || titel.isEmpty()) {
			throw new IllegalArgumentException("film: titel mag niet leeg zijn.");
		}
		this.titel = titel;
	}

	public int getVoorraad() {
		return voorraad;
	}

	public void setVoorraad(int voorraad) {
		if (voorraad < 0) {
			throw new IllegalArgumentException("film: voorraad moet positief zijn.");
		}
		this.voorraad = voorraad;
	}

	public int getGereserveerd() {
		return gereserveerd;
	}

	public void setGereserveerd(int gereserveerd) {
		if (gereserveerd < 0) {
			throw new IllegalArgumentException("film: gereserveerd moet positief zijn.");
		}
		this.gereserveerd = gereserveerd;
	}

	public BigDecimal getPrijs() {
		return prijs;
	}

	public void setPrijs(BigDecimal prijs) {
		if (prijs.compareTo(BigDecimal.ZERO) <= 0) {
			throw new IllegalArgumentException("film: prijs moet positief zijn.");
		}
		this.prijs = prijs;
	}

	public boolean isBeschikbaar() {
		if (voorraad - gereserveerd > 0) {
			return true;
		}
		return false;
	}

}
