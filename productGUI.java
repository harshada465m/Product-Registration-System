import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class ProductGUI {

    static final String URL = "jdbc:mysql://localhost:3306/product_db";
    static final String USER = "root";
    static final String PASS = "";

    JTextField nameField, categoryField, priceField, quantityField, dateField, idField;
    JTextArea output;

    Connection con;

    ProductGUI() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(URL, USER, PASS);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

        JFrame frame = new JFrame("Product Registration System");
        frame.setSize(600, 500);
        frame.setLayout(new FlowLayout());

        // Fields
        idField = new JTextField(5);
        nameField = new JTextField(10);
        categoryField = new JTextField(10);
        priceField = new JTextField(10);
        quantityField = new JTextField(10);
        dateField = new JTextField(10);

        // Buttons
        JButton insertBtn = new JButton("Insert");
        JButton displayBtn = new JButton("Display");
        JButton updateBtn = new JButton("Update Price");
        JButton deleteBtn = new JButton("Delete");
        JButton searchBtn = new JButton("Search");

        output = new JTextArea(15, 50);

        // Add Components
        frame.add(new JLabel("ID:"));
        frame.add(idField);
        frame.add(new JLabel("Name:"));
        frame.add(nameField);
        frame.add(new JLabel("Category:"));
        frame.add(categoryField);
        frame.add(new JLabel("Price:"));
        frame.add(priceField);
        frame.add(new JLabel("Quantity:"));
        frame.add(quantityField);
        frame.add(new JLabel("Date:"));
        frame.add(dateField);

        frame.add(insertBtn);
        frame.add(displayBtn);
        frame.add(updateBtn);
        frame.add(deleteBtn);
        frame.add(searchBtn);

        frame.add(new JScrollPane(output));

        // Insert
        insertBtn.addActionListener(e -> {
            try {
                String sql = "INSERT INTO products(name, category, price, quantity, reg_date) VALUES(?,?,?,?,?)";
                PreparedStatement ps = con.prepareStatement(sql);

                ps.setString(1, nameField.getText());
                ps.setString(2, categoryField.getText());
                ps.setDouble(3, Double.parseDouble(priceField.getText()));
                ps.setInt(4, Integer.parseInt(quantityField.getText()));
                ps.setString(5, dateField.getText());

                ps.executeUpdate();
                output.setText("Inserted Successfully!");
            } catch (Exception ex) {
                output.setText(ex.toString());
            }
        });

        // Display
        displayBtn.addActionListener(e -> {
            try {
                ResultSet rs = con.createStatement().executeQuery("SELECT * FROM products");
                output.setText("");
                while (rs.next()) {
                    output.append(rs.getInt(1) + " " +
                            rs.getString(2) + " " +
                            rs.getString(3) + " " +
                            rs.getDouble(4) + " " +
                            rs.getInt(5) + " " +
                            rs.getDate(6) + "\n");
                }
            } catch (Exception ex) {
                output.setText(ex.toString());
            }
        });

        // Update
        updateBtn.addActionListener(e -> {
            try {
                PreparedStatement ps = con.prepareStatement(
                        "UPDATE products SET price=? WHERE id=?");

                ps.setDouble(1, Double.parseDouble(priceField.getText()));
                ps.setInt(2, Integer.parseInt(idField.getText()));

                ps.executeUpdate();
                output.setText("Updated Successfully!");
            } catch (Exception ex) {
                output.setText(ex.toString());
            }
        });

        // Delete
        deleteBtn.addActionListener(e -> {
            try {
                PreparedStatement ps = con.prepareStatement(
                        "DELETE FROM products WHERE id=?");

                ps.setInt(1, Integer.parseInt(idField.getText()));
                ps.executeUpdate();

                output.setText("Deleted Successfully!");
            } catch (Exception ex) {
                output.setText(ex.toString());
            }
        });

        // Search (LIKE)
        searchBtn.addActionListener(e -> {
            try {
                PreparedStatement ps = con.prepareStatement(
                        "SELECT * FROM products WHERE name LIKE ?");
                ps.setString(1, "%" + nameField.getText() + "%");

                ResultSet rs = ps.executeQuery();
                output.setText("");
                while (rs.next()) {
                    output.append(rs.getString("name") + "\n");
                }
            } catch (Exception ex) {
                output.setText(ex.toString());
            }
        });

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new ProductGUI();
    }
}