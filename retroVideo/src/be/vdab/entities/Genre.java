package be.vdab.entities;

public class Genre {
	private int id;
	private String naam;

	public Genre() {

	}

	public Genre(int id, String naam) {
		setId(id);
		setNaam(naam);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		if (id <= 0) {
			throw new IllegalArgumentException("genre: id moet positief zijn.");
		}
		this.id = id;
	}

	public String getNaam() {
		return naam;
	}

	public void setNaam(String naam) {
		if (naam == null || naam.isEmpty()) {
			throw new IllegalArgumentException("genre: naam moet ingevuld zijn.");
		}
		this.naam = naam;
	}

}
