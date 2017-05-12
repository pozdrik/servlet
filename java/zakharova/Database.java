package zakharova;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.sql.*;

/**
 * Created by Masha on 12.05.2017.
 */
public class Database {
    public String createJSON () throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection connection = DriverManager.getConnection("jdbc:postgresql://194.87.187.238/zakharova", "zakharova", "zakharova");
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM message");
        ResultSet result = preparedStatement.executeQuery();
        JSONObject obj = new JSONObject();
        JSONArray array = new JSONArray();
        while (result.next()) {
            System.out.println(result.getString("login") + ":" + result.getString("message"));
            array.add(result.getString("login")  + ": ");
            array.add(result.getString("message"));
            array.add("\n");
        }
        obj.put("messages", array);
        return obj.toJSONString();
    }
}
