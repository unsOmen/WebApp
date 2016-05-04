package bean;

/**
 * Created by OmeN on 04.05.2016.
 */
public class Users {

    private int id;
    private String username;
    private String pass;

    public Users() {
    }

    public Users(String name, String pass) {
        this.username = name;
        this.pass = pass;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
