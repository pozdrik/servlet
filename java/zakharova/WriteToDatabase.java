package zakharova;

import java.sql.*;

public class WriteToDatabase {
    public void write(String line, String login, Timestamp timestamp) throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection connection = DriverManager.getConnection("jdbc:postgresql://194.87.187.238/zakharova", "zakharova", "zakharova");
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO messageTime (login, message, time) VALUES (?, ?, ?) RETURNING id");
        preparedStatement.setString(1, login);
        preparedStatement.setString(2, line);
        preparedStatement.setTimestamp(3, timestamp);
        preparedStatement.execute();
    }
}
