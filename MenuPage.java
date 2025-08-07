// MenuPage.java
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;

class MenuPage extends JFrame {
    ArrayList<String> cartItems = new ArrayList<>();
    ArrayList<Double> cartPrices = new ArrayList<>();

    MenuPage(String restaurantName) {
        Font f1 = new Font("SansSerif", Font.BOLD, 28);
        Font f2 = new Font("SansSerif", Font.PLAIN, 18);

        JLabel title = new JLabel(restaurantName + " Menu", JLabel.CENTER);
        title.setFont(f1);

        DefaultListModel<String> listModel = new DefaultListModel<>();
        JList<String> menuList = new JList<>(listModel);
        menuList.setFont(f2);
        JScrollPane scrollPane = new JScrollPane(menuList);

        JButton backButton = new JButton("← Back");
        backButton.setFont(f2);
        JButton addButton = new JButton("Add to Cart");
        JButton cartButton = new JButton("🛒 Cart");

        Container c = getContentPane();
        c.setLayout(null);

        backButton.setBounds(20, 20, 100, 30);
        title.setBounds(100, 20, 400, 40);
        scrollPane.setBounds(100, 80, 400, 300);
        addButton.setBounds(150, 400, 130, 40);
        cartButton.setBounds(310, 400, 130, 40);

        c.add(title);
        c.add(scrollPane);
        c.add(addButton);
        c.add(cartButton);
        c.add(backButton);


        try {
            // Connect to MySQL database
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/zomato_clone", "root", "harshal18");

            // Prepare SQL query to fetch items for given restaurant
            String sql = "SELECT fi.name AS item, m.price " +
                    "FROM menu m " +
                    "JOIN food_items fi ON m.food_item_id = fi.id " +
                    "JOIN restaurants r ON m.restaurant_id = r.id " +
                    "WHERE r.name = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, restaurantName);
            ResultSet rs = pst.executeQuery();


            // Loop through result and add to list model
            while (rs.next()) {
                String item = rs.getString("item");     // Get item name
                double price = rs.getDouble("price");   // Get item price
                listModel.addElement(item + " - ₹" + price); // Add item to list
            }

            con.close();                                // Close connection

        } catch (Exception e) {
            // Show error dialog if exception occurs
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }


        addButton.addActionListener(e -> {
            int index = menuList.getSelectedIndex();
            if (index != -1) {
                String selected = listModel.get(index);
                cartItems.add(selected);
                String[] parts = selected.split("₹");
                cartPrices.add(Double.parseDouble(parts[1].trim()));
                JOptionPane.showMessageDialog(null, "Added to cart");
            } else {
                JOptionPane.showMessageDialog(null, "Please select an item");
            }
        });

        cartButton.addActionListener(e -> {
            new CartPage(cartItems, cartPrices);
        });

        setTitle("Menu Page");
        setSize(600, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}