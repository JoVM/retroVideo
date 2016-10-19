package be.vdab.repositories;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import be.vdab.entities.Reservatie;

public class ReservatieRepository extends AbstractRepository {
	private static final String BEGIN_SELECT = "select * from reservaties ";
	private static final String FIND_ALL = BEGIN_SELECT + "order by reservatieDatum";
	private static final String READ_DATUM = BEGIN_SELECT + "where reservatieDatum=?";
	private static final String READ = BEGIN_SELECT + "where id=?";

	public List<Reservatie> findAll() {
		try (Connection connection = dataSource.getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(FIND_ALL)) {
			List<Reservatie> reservaties = new ArrayList<>();
			while (resultSet.next()) {
				reservaties.add(resultSetRijNaarReservatie(resultSet));
			}
			return reservaties;
		} catch (SQLException ex) {
			throw new RepositoryException(ex);
		}
	}

	public Reservatie read(int id) {
		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(READ)) {
			statement.setInt(1, id);
			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					return resultSetRijNaarReservatie(resultSet);
				}
				return null;
			}
		} catch (SQLException ex) {
			throw new RepositoryException(ex);
		}
	}

	public List<Reservatie> findReservatieByDate(Date reservatieDatum) {
		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(READ_DATUM)) {
			List<Reservatie> reservaties = new ArrayList<>();
			statement.setDate(1, reservatieDatum);
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					reservaties.add(resultSetRijNaarReservatie(resultSet));
				}
				return reservaties;
			}
		} catch (SQLException ex) {
			throw new RepositoryException(ex);
		}
	}

	private Reservatie resultSetRijNaarReservatie(ResultSet resultSet) throws SQLException {
		return new Reservatie(resultSet.getInt("klantid"), resultSet.getInt("filmid"),
				resultSet.getDate("reservatieDatum"));
	}

}
