package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import db.DB;
import db.DbIntegrityException;

public class Program {

	public static void main(String[] args) {
		Connection conn = null;
		Statement st = null;

		try {
			conn = DB.getConnection();
			conn.setAutoCommit(false);

			st = conn.createStatement();

			int rows1 = st.executeUpdate("UPDATE seller SET BaseSalary = 2090 WHERE DepartmentId = 1");
			st.executeUpdate("DELETE FROM department WHERE id = 2");
			int rows2 = st.executeUpdate("UPDATE seller SET BaseSalary = 3090 WHERE DepartmentId = 2");
			
			conn.commit();
			System.out.println("Done! Rows affected: " + rows1);
			System.out.println("Done! Rows affected: " + rows2);
		} catch (SQLException e) {
			try {
				conn.rollback();
				throw new DbIntegrityException("Transaction rolled back! Caused by: " + e.getMessage());
			} catch (SQLException e1) {
				throw new DbIntegrityException("Error trying to rollback! Caused by: " + e1.getMessage());
			}
		} finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}
	}
	
	public void delete() {
		Connection conn = null;
		PreparedStatement st = null;

		try {
			conn = DB.getConnection();

			st = conn.prepareStatement(
					"DELETE FROM department WHERE id = ?");

			
			st.setInt(1, 5);

			int rowsAffected = st.executeUpdate();

			System.out.println("Done! Rows affected: " + rowsAffected);
		} catch (SQLException e) {
			throw new DbIntegrityException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}
	}
	
	public void update() {
		Connection conn = null;
		PreparedStatement st = null;

		try {
			conn = DB.getConnection();

			st = conn.prepareStatement(
					"UPDATE seller " + "SET BaseSalary = BaseSalary + ? " + "WHERE " + "(DepartmentId = ? )");

			st.setDouble(1, 200);
			st.setInt(2, 2);

			int rowsAffected = st.executeUpdate();

			System.out.println("Done! Rows affected: " + rowsAffected);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}
	}

	public void insert() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			conn = DB.getConnection();

			st = conn.prepareStatement("INSERT INTO seller " + "(Name, Email, BirthDate, BaseSalary, DepartmentID) "
					+ "VALUES " + "(?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);

			st.setString(1, "Carl Purple");
			st.setString(2, "carl@gmail.com");
			st.setDate(3, new java.sql.Date(sdf.parse("22/04/1995").getTime()));
			st.setDouble(4, 3000);
			st.setInt(5, 4);

			int rowsAffected = st.executeUpdate();

			if (rowsAffected > 0) {
				rs = st.getGeneratedKeys();
				while (rs.next()) {
					int id = rs.getInt(1);
					System.out.println("Done! Id = " + id);
				}
			} else {
				System.out.println("No ");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} finally {
			DB.closeStatement(st);
			DB.closeResulSet(rs);
			DB.closeConnection();
		}
	}

	public void lerDados() {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			conn = DB.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery("select * from department");

			while (rs.next()) {
				System.out.println(rs.getInt("Id") + ", " + rs.getString("Name"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DB.closeResulSet(rs);
			DB.closeStatement(st);
			DB.closeConnection();
		}
	}

}
