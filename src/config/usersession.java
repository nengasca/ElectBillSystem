package config;

public class usersession {
    private static usersession instance;

    // Kining mga variables maoy mag-gunit sa data samtang nagdagan ang program
    private int id;
    private String firstname;
    private String lastname;
    private String username;
    private String role;
    private String status;
    private String accnum;

    // Private constructor aron dili ma-instantiate sa gawas
    usersession() {}

    // Singleton Pattern: Kini ang tawgon nimo pirme (usersession.getInstance())
    public static synchronized usersession getInstance() {
        if (instance == null) {
            instance = new usersession();
        }
        return instance;
    }

    // --- GETTERS AND SETTERS ---
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getFirstname() { return firstname; }
    public void setFirstname(String firstname) { this.firstname = firstname; }

    public String getLastname() { return lastname; }
    public void setLastname(String lastname) { this.lastname = lastname; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getAccNum() { return accnum; }
    public void setAccNum(String accnum) { this.accnum = accnum; }

    // Gamit kini inig Logout
    public void logout() {
        instance = null; // I-reset ang session
    }
}