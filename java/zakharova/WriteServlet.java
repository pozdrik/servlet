package zakharova;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by Masha on 12.05.2017.
 */
public class WriteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String message = req.getParameter("message");
        String login = req.getParameter("login");
        WriteToDatabase writeToDatabase = new WriteToDatabase();
        if (getServletContext().getAttribute(login)
                .equals(req.getParameter("uuid"))) {
            try {
                writeToDatabase.write(message, login);
                resp.setStatus(200);
            } catch (SQLException e) {
                resp.setStatus(501);
                e.printStackTrace();
            }
        }
    }
}
