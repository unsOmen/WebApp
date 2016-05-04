import DB.DBController;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

/**
 * Created by OmeN on 02.05.2016.
 */
public class Servlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("doPost");
        response.setContentType("text/html;charset=UTF-8");

        PrintWriter pw = response.getWriter();
        insertIndex(pw);

        String str = request.getParameter("login");
        String str2 = request.getParameter("pass");

        if(addUser(str, str2)>0) {
            pw.println("Login = " + new String(str.getBytes("ISO-8859-1"), "UTF-8") + "<br>");
            pw.println("Pass = " + new String(str2.getBytes("ISO-8859-1"), "UTF-8") + "<br>");
        } else {
            pw.println("Ошибка при добавлении<br>");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("doGet");
    }

    static void insertIndex(PrintWriter printWriter) {
        printWriter.println("<a href=/>Index</a><br>");
    }

    int addUser(String login, String pass) {
        try {
            return DBController.add(login, pass);
        } catch (ClassNotFoundException e) {
            System.err.println("Класс драйвера не найден: " + e);
        } catch (SQLException e) {
            System.err.println("SQL Ошибка: " + e);
        }

        return 0;
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        System.out.println("init");
    }

    @Override
    public void destroy() {
        super.destroy();
        System.out.println("destroy");
    }
}
