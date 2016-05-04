import DB.DBController;
import bean.Users;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

/**
 * Created by OmeN on 02.05.2016.
 */
@WebServlet(name = "ServletListUsers")
public class ServletListUsers extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter pw = response.getWriter();
        Servlet.insertIndex(pw);
        getUsers(pw);
    }

    void getUsers(PrintWriter printWriter) {
        try {
            DBController.listUsers(printWriter);
        } catch (ClassNotFoundException e) {
            System.err.println("Класс драйвера не найден: " + e);
        } catch (SQLException e) {
            System.err.println("SQL Ошибка: " + e);
        }
    }
}
