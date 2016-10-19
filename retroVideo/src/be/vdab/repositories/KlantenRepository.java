package be.vdab.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import be.vdab.entities.Klant;

public class KlantenRepository extends AbstractRepository {
	private static final String BEGIN_SELECT = "select id, familienaam, voornaam, straatNummer, postcode, gemeente from klanten ";
	private static final String FIND_ALL = BEGIN_SELECT + "order by familienaam";
	private static final String READ_FAMILIENAAM = BEGIN_SELECT + "where familienaam REGEXP ?";
	private static final String READ = BEGIN_SELECT + "where id=?";

	public List<Klant> findAll() {
		try (Connection connection = dataSource.getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(FIND_ALL)) {
			List<Klant> klanten = new ArrayList<>();
			while (resultSet.next()) {
				klanten.add(resultSetRijNaarKlant(resultSet));
			}
			return klanten;
		} catch (SQLException ex) {
			throw new RepositoryException(ex);
		}
	}

	public Klant read(int id) {
		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(READ)) {
			statement.setInt(1, id);
			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					return resultSetRijNaarKlant(resultSet);
				}
				return null;
			}
		} catch (SQLException ex) {
			throw new RepositoryException(ex);
		}
	}

	public List<Klant> findKlantByFamilienaam(int id) {
		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(READ_FAMILIENAAM)) {
			List<Klant> klanten = new ArrayList<>();
			statement.setInt(1, id);
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					klanten.add(resultSetRijNaarKlant(resultSet));
				}
				return klanten;
			}
		} catch (SQLException ex) {
			throw new RepositoryException(ex);
		}
	}

	private Klant resultSetRijNaarKlant(ResultSet resultSet) throws SQLException {
		return new Klant(resultSet.getInt("id"), resultSet.getString("familienaam"), resultSet.getString("voornaam"),
				resultSet.getString("straatNummer"), resultSet.getString("postcode"), resultSet.getString("gemeente"));
	}

}
