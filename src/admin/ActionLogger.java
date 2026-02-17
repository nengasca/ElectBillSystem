package admin;

import config.config;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ActionLogger {

    private static config db = new config();

    /**
     * Logs an action performed by a user/admin.
     * @param userID The user ID of the user/admin performing the action.
     * @param action The action description.
     */
    public static void logAction(int userID, String action) {
        String queryCheck = "SELECT id FROM users WHERE id = ?";
        String queryInsert = "INSERT INTO logs (userID, action) VALUES (?, ?)";
        try (Connection con = db.getConnection()) {
            // Check if user exists
            try (PreparedStatement pstCheck = con.prepareStatement(queryCheck)) {
                pstCheck.setInt(1, userID);
                try (ResultSet rs = pstCheck.executeQuery()) {
                    if (!rs.next()) {
                        
                        System.err.println("Failed to log action: User ID " + userID + " does not exist.");
                        return;
                    }
                }
            }
            // Insert log
            try (PreparedStatement pstInsert = con.prepareStatement(queryInsert)) {
                pstInsert.setInt(1, userID);
                pstInsert.setString(2, action);
                pstInsert.executeUpdate();
            }
        } catch (SQLException e) {
            System.err.println("Failed to log action: " + e.getMessage());
        }
    }
}
