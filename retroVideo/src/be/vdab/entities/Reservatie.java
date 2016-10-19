package be.vdab.entities;

//import java.util.Date;
import java.sql.Date;

public class Reservatie {
	private int klantid;
	private int filmid;
	private Date reservatieDatum;

	public Reservatie() {

	}

	public Reservatie(int klantid, int filmid, Date reservatieDatum) {
		setFilmid(filmid);
		setKlantid(klantid);
		setReservatieDatum(reservatieDatum);
	}

	public int getKlantid() {
		return klantid;
	}

	public void setKlantid(int klantid) {
		if (klantid < 0) {
			throw new IllegalArgumentException("reservatie: klantid moet positief zijn.");
		}
		this.klantid = klantid;
	}

	public int getFilmid() {
		return filmid;
	}

	public void setFilmid(int filmid) {
		if (filmid < 0) {
			throw new IllegalArgumentException("reservatie: fildmid moet positief zijn.");
		}
		this.filmid = filmid;
	}

	public Date getReservatieDatum() {
		return reservatieDatum;
	}

	public void setReservatieDatum(Date reservatieDatum) {
		if (reservatieDatum == null) {
			throw new IllegalArgumentException("reservatie: reservatieDatum mag niet leeg zijn.");
		}
		this.reservatieDatum = reservatieDatum;
	}

}
