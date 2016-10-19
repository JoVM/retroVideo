package be.vdab.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import be.vdab.entities.Film;

public class FilmRepository extends AbstractRepository {
	private static final String BEGIN_SELECT = "select id, genreid, titel, voorraad, gereserveerd, prijs from films ";
	private static final String FIND_ALL = BEGIN_SELECT + "order by titel";
	private static final String READ_GENRE = BEGIN_SELECT + "where genreid=?";
	private static final String READ = BEGIN_SELECT + "where id=?";
	private static final String UPDATE_GERESERVEERD = "update films set gereserveerd = gereserveerd + 1 where id = ?";

	public void updateGereserveerd(int id) {
		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_GERESERVEERD,
						Statement.NO_GENERATED_KEYS)) {
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			statement.setInt(1, id);
			statement.executeUpdate();
		} catch (SQLException ex) {
			throw new RepositoryException(ex);
		}
	}

	public List<Film> findAll() {
		try (Connection connection = dataSource.getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(FIND_ALL)) {
			List<Film> films = new ArrayList<>();
			while (resultSet.next()) {
				films.add(resultSetRijNaarFilm(resultSet));
			}
			return films;
		} catch (SQLException ex) {
			throw new RepositoryException(ex);
		}
	}

	public Film read(int id) {
		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(READ)) {
			statement.setInt(1, id);
			connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					return resultSetRijNaarFilm(resultSet);
				}
				return null;
			}
		} catch (SQLException ex) {
			throw new RepositoryException(ex);
		}
	}

	public List<Film> findFilmByGenreId(int id) {
		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(READ_GENRE)) {
			List<Film> films = new ArrayList<>();
			statement.setInt(1, id);
			connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					films.add(resultSetRijNaarFilm(resultSet));
				}
				return films;
			}
		} catch (SQLException ex) {
			throw new RepositoryException(ex);
		}
	}

	private Film resultSetRijNaarFilm(ResultSet resultSet) throws SQLException {
		return new Film(resultSet.getInt("id"), resultSet.getInt("genreid"), resultSet.getString("titel"),
				resultSet.getInt("voorraad"), resultSet.getInt("gereserveerd"), resultSet.getBigDecimal("prijs"));
	}

}
