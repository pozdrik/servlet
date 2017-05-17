package zakharova;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class WriteToDatabase {
    public void write(String line, String login) throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection connection = DriverManager.getConnection("jdbc:postgresql://194.87.187.238/zakharova", "zakharova", "zakharova");
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO message (login, message) VALUES (?, ?) RETURNING id");
        preparedStatement.setString(1, login);
        preparedStatement.setString(2, line);
        preparedStatement.execute();
    }
}
