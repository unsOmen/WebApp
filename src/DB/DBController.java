package DB;

/**
 * Created by OmeN on 02.05.2016.
 */

import java.io.PrintWriter;
import java.sql.*;
public final class DBController {
    private static Connection dbConn;

    private static final String URL = "jdbc:hsqldb:file:C:/Users/OmeN/IdeaProjects/SpringMVC1/TestWebApp/TestDB";
    private static final String USER = "sa";
    private static final String PASS = "";

    private static void conn() throws ClassNotFoundException, SQLException {
        Class.forName("org.hsqldb.jdbc.JDBCDriver");
        dbConn = DriverManager.getConnection(URL, USER, PASS);
    }

    public static void add(String login, String pass) throws ClassNotFoundException, SQLException {
        conn();
        Statement stat = dbConn.createStatement();
        stat.executeUpdate("INSERT INTO users VALUES ('" + login + "', '"+ pass + "')");
        ResultSet resultSet = stat.executeQuery("SELECT login, pass FROM USERS");
        while (resultSet.next()) {
            System.out.print(resultSet.getString(1));
            System.out.print(" | ");
            System.out.println(resultSet.getString(2));
        }
        resultSet.close();
        stat.close();
        if(dbConn!=null)
            dbConn.close();
    }

    public static void listUsers(PrintWriter printWriter) throws ClassNotFoundException, SQLException {
        conn();
        Statement statement = dbConn.createStatement();
        ResultSet result = statement.executeQuery("SELECT login FROM USERS");
        while (result.next()) {
            printWriter.println(result.getString(1) + "<br>");
        }
        result.close();
        statement.close();

        if(dbConn!=null)
            dbConn.close();
    }

}
