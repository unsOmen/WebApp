package DB;

/**
 * Created by OmeN on 02.05.2016.
 */

import java.io.PrintWriter;
import java.sql.*;
public final class DBController {
    private static Connection dbConn;

    private static final String URL = "jdbc:hsqldb:file:C:/Users/OmeN/IdeaProjects/SpringMVC1/TestWebApp/TestDB";
    private static final String DRIVER = "org.hsqldb.jdbc.JDBCDriver";
    private static final String USER = "sa";
    private static final String PASS = "";

    private static void conn() throws ClassNotFoundException, SQLException {
        Class.forName(DRIVER);
        dbConn = DriverManager.getConnection(URL, USER, PASS);
    }

    public static int add(String login, String pass) throws ClassNotFoundException, SQLException {
        conn();
        Statement stat = dbConn.createStatement();
        int updateCount = stat.executeUpdate("INSERT INTO users(LOGIN, PASS) VALUES ('" + login + "', '"+ pass + "')");

        ResultSet resultSet = stat.executeQuery("SELECT id, login, pass FROM USERS");
        while (resultSet.next()) {
            System.out.print(resultSet.getInt(1));
            System.out.print(" | ");
            System.out.print(resultSet.getString(2));
            System.out.print(" | ");
            System.out.println(resultSet.getString(3));
        }
        resultSet.close();
        stat.close();
        if(dbConn!=null)
            dbConn.close();

        return updateCount;
    }

    public static void listUsers(PrintWriter printWriter) throws ClassNotFoundException, SQLException {
        conn();
        Statement statement = dbConn.createStatement();
        ResultSet result = statement.executeQuery("SELECT id, login FROM USERS");
        while (result.next()) {
            printWriter.println(result.getInt(1) + " - " + result.getString(2) + "<br>");
        }
        result.close();
        statement.close();

        if(dbConn!=null)
            dbConn.close();
    }

}
