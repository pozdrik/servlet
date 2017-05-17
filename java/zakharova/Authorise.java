package zakharova;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.UUID;

public class Authorise extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setStatus(200);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String log = req.getParameter("login");
        String pass = req.getParameter("pass");
        try {
            if(new Database().checkData(log, pass)){
                UUID uuid = UUID.randomUUID();
                getServletContext().setAttribute(log, uuid.toString());
                //HttpSession session = req.getSession(true);
                //session.setAttribute("isFerst", "true");
                resp.setStatus(200);
                resp.getWriter().write(uuid.toString());
            }
            else {
                resp.setStatus(401);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
