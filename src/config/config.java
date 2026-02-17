package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import net.proteanit.sql.DbUtils;


public class config {

    
    public static Connection connectDB() {
        Connection con = null;
        try {
            Class.forName("org.sqlite.JDBC"); 
            con = DriverManager.getConnection("jdbc:sqlite:ebs.db"); 
            System.out.println("Connection Successful");
        } catch (Exception e) {
            System.out.println("Connection Failed: " + e);
        }
        return con;
    }
    

    public static String getID() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    public static String hashPassword(String oldPassRaw) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    public static String getName() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    // Method used by admin_dashboard to retrieve result sets
   public java.sql.ResultSet getData(String query) throws java.sql.SQLException {
    java.sql.Connection conn = connectDB();
    java.sql.Statement stmt = conn.createStatement();
    return stmt.executeQuery(query);
}
    public Connection getConnection() {
        return connectDB();
    }

    public void addRecord(String sql, Object... values) {
        try (Connection conn = connectDB(); 
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            for (int i = 0; i < values.length; i++) {
                pstmt.setObject(i + 1, values[i]);
            }
            pstmt.executeUpdate();
            System.out.println("Record added successfully!");
        } catch (SQLException e) {
            System.out.println("Error adding record: " + e.getMessage());
        }
    }

        public void displayData(String sql, javax.swing.JTable table, Object... values) {
    try (Connection conn = connectDB();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        
        // Set the parameters for the search
        for (int i = 0; i < values.length; i++) {
            pstmt.setObject(i + 1, values[i]);
        }

        try (ResultSet rs = pstmt.executeQuery()) {
            // Automatically maps the filtered ResultSet to your JTable
            table.setModel(DbUtils.resultSetToTableModel(rs));
        }
        
    } catch (SQLException e) {
        System.out.println("Error filtering data: " + e.getMessage());
    }
}

    // Method para sa INSERT, UPDATE, ug DELETE sa database
     public int insertData(String sql) {
    int result = 0;
    try {
        Connection conn = connectDB();
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.executeUpdate();
        System.out.println("Data inserted successfully!");
        result = 1; // Mobalik og 1 kung malampuson
        pst.close();
        conn.close();
    } catch (SQLException e) {
        System.out.println("Insert Error: " + e.getMessage());
        result = 0; // Mobalik og 0 kung naay error
    }
    return result;
}

    public static class usersession {
        private static usersession instance;
        private int id;
        private String fname, lname, role, accNum;

        private usersession() {}

        public static usersession getInstance() {
            if (instance == null) {
                instance = new usersession();
            }
            return instance;
        }

        public int getId() { return id; }
        public void setId(int id) { this.id = id; }

        public String getFirstname() { return fname; }
        public void setFirstname(String firstname) { this.fname = firstname; }

        public String getLastname() { return lname; }
        public void setLastname(String lastname) { this.lname = lastname; }

        public String getRole() { return role; }
        public void setRole(String role) { this.role = role; }

        public String getAccNum() { return accNum; }
        public void setAccNum(String accNum) { this.accNum = accNum; }

        public void setImage(String destination) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public void setPassword(String newPassHashed) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }

    public static class billsmodel {

        public billsmodel() {
        }
    }
}