package com.maddness.jdbc;

import java.sql.*;

public class TestConnection {
    public static void main(String[] args) {

        try (Connection conn = DriverManager.getConnection("url", "user", "pass")) {
            String query = "select * from users";

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                rs.getInt(0);
                rs.getString(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void executeSomeQuery(String sql) throws SQLException {
        try (Connection connection = DriverManager.getConnection("url", "user", "pass")) {
            connection.setAutoCommit(false);

            try (PreparedStatement statement = connection.prepareStatement(sql)) {

                // Fire transactional queries here.
                statement.execute();

                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                throw e;
            }
        }
    }
}
