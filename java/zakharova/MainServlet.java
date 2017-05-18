package zakharova;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

public class MainServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        out.print("<h1>Hello Servlet</h1>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (getServletContext().getAttribute(req.getParameter("login"))
                .equals(req.getParameter("uuid"))) {
            resp.setStatus(200);
            resp.setHeader("text/html", "application/json;charset=UTF-8");
            try {
                String jsonString = new Database().createJSON(new Timestamp
                        (new Date(req.getParameter("date")).getTime()));
                resp.getWriter().write(jsonString);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
