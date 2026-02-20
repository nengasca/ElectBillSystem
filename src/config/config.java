package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector; // Add this import at the top
import javax.swing.table.DefaultTableModel; // Add this import at the top
import java.sql.ResultSetMetaData; // Add this import at the top


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

        // USE TRY-WITH-RESOURCES to ensure connections close automatically
    public void displayData(String sql, javax.swing.JTable table) {
        try (Connection conn = connectDB();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            Vector<String> columnNames = new Vector<>();
            for (int column = 1; column <= columnCount; column++) {
                columnNames.add(metaData.getColumnName(column));
            }

            Vector<Vector<Object>> data = new Vector<>();
            while (rs.next()) {
                Vector<Object> vector = new Vector<>();
                for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                    vector.add(rs.getObject(columnIndex));
                }
                data.add(vector);
            }
            table.setModel(new DefaultTableModel(data, columnNames));

        } catch (SQLException e) {
            System.out.println("Error displaying data: " + e.getMessage());
        }
        // The "try (...)" block closes everything automatically here!
    }

// Update insertData to close the connection properly
    public int insertData(String sql) {
        try (Connection conn = connectDB();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            int result = pst.executeUpdate();
            System.out.println("Data processed successfully!");
            return result; 
        } catch (SQLException e) {
            System.out.println("Database Error: " + e.getMessage());
            return 0;
        }
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
    public void populateTable(String query, javax.swing.JTable table) {
    try (Connection conn = connectDB();
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(query)) {

        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();

        // 1. Extract Column Names from ebs.db
        Vector<String> columnNames = new Vector<>();
        for (int column = 1; column <= columnCount; column++) {
            columnNames.add(metaData.getColumnName(column));
        }

        // 2. Extract Row Data
        Vector<Vector<Object>> data = new Vector<>();
        while (rs.next()) {
            Vector<Object> vector = new Vector<>();
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                vector.add(rs.getObject(columnIndex));
            }
            data.add(vector);
        }

        // 3. Apply to your JTable
        table.setModel(new DefaultTableModel(data, columnNames));

    } catch (SQLException e) {
        System.out.println("Error populating table: " + e.getMessage());
    }
}
}