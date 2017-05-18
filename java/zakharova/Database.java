package zakharova;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.sql.*;

public class Database {
    private Connection connection;

    public Database() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        connection = DriverManager.getConnection("jdbc:postgresql://194.87.187.238/zakharova", "zakharova", "zakharova");
    }

    public boolean checkData(String login, String password) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM authorisation");
        ResultSet result = preparedStatement.executeQuery();
        while (result.next())
            if (login.equals(result.getString("login")) && password.equals(result.getString("password"))) {
                return true;
            }
        return false;
    }

    public String createJSON(Timestamp timestamp) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM messageTime WHERE time >= ?");
        preparedStatement.setTimestamp(1, timestamp);
        ResultSet result = preparedStatement.executeQuery();
        JSONObject obj = new JSONObject();
        JSONArray array = new JSONArray();
        while (result.next()) {
            array.add("[" + result.getTimestamp("time") + "]");
            array.add(result.getString("login") + ": ");
            array.add(result.getString("message"));
            array.add("\n");
        }
        obj.put("messages", array);
        return obj.toJSONString();
    }

    //cat 1234
    //dog 5678
}
