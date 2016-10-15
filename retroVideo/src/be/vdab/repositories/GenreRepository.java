package be.vdab.repositories;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import be.vdab.entities.Genre;

public class GenreRepository extends AbstractRepository {
	private static final String BEGIN_SELECT = "select id, naam from genres ";
	private static final String FIND_ALL = BEGIN_SELECT + "order by naam";
	//private static final String READ = BEGIN_SELECT + "where id=?";

	public List<Genre> findAll() {
		try (Connection connection = dataSource.getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(FIND_ALL)) {
			List<Genre> genres = new ArrayList<>();
			while (resultSet.next()) {
				genres.add(resultSetRijNaarPizza(resultSet));
			}
			return genres;
		} catch (SQLException ex) {
			throw new RepositoryException(ex);
		}
	}

	private Genre resultSetRijNaarPizza(ResultSet resultSet) throws SQLException {
		return new Genre(resultSet.getInt("id"), resultSet.getString("naam"));
	}

}
