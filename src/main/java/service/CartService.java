package service;

import database.DatabaseConnection;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CartService {

    private static final Logger logger = Logger.getLogger(CartService.class.getName());

    public int saveCart(int totalItems, double totalCost, String language) {


        String sql = "INSERT INTO cart_records (total_items, total_cost, language) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, totalItems);
            stmt.setDouble(2, totalCost);
            stmt.setString(3, language);

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (Exception e) {
            logger.log(Level.SEVERE, e, () -> "Failed to save cart");
        }

        return -1;
    }

    public void saveItem(int cartId, int itemNumber, double price, int quantity) {
        String sql = "INSERT INTO cart_items (cart_record_id, item_number, price, quantity, subtotal) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            double subtotal = price * quantity;

            stmt.setInt(1, cartId);
            stmt.setInt(2, itemNumber);
            stmt.setDouble(3, price);
            stmt.setInt(4, quantity);
            stmt.setDouble(5, subtotal);

            stmt.executeUpdate();

        } catch (Exception e) {
            logger.log(Level.SEVERE, e, () -> "Failed to save cart item for cartId=" + cartId);
        }
    }
}