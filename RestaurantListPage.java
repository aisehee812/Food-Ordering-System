import javax.swing.*;
import java.awt.*;
import java.sql.*;

class RestaurantListPage extends JFrame {
    RestaurantListPage(String foodItem) {
        setTitle("Restaurants offering " + foodItem);
        setSize(600, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel header = new JLabel("Restaurants with: " + foodItem, JLabel.CENTER);
        header.setFont(new Font("SansSerif", Font.BOLD, 24));
        add(header, BorderLayout.NORTH);

        DefaultListModel<String> listModel = new DefaultListModel<>();
        JList<String> restaurantList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(restaurantList);
        add(scrollPane, BorderLayout.CENTER);

        // MySQL Connection
        String url = "jdbc:mysql://localhost:3306/Zomato_clone?useSSL=false&serverTimezone=UTC";
        String user = "root";             // Replace with your MySQL username
        String pass = "harshal18";    // Replace with your MySQL password

        try (Connection con = DriverManager.getConnection(url, user, pass)) {
            String query = "SELECT DISTINCT r.name AS restaurant_name " +
                    "FROM restaurants r " +
                    "JOIN menu rm ON r.id = rm.restaurant_id " +
                    "JOIN food_items fi ON fi.id = rm.food_item_id " +
                    "WHERE fi.name LIKE ?";

            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, "%" + foodItem + "%");
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                String restaurant = rs.getString("restaurant_name");
                listModel.addElement(restaurant);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Database error: " + e.getMessage());
        }

        restaurantList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                String selectedRestaurant = restaurantList.getSelectedValue();
                if (selectedRestaurant != null) {
                    new MenuPage(selectedRestaurant); // ← You should implement this
                    dispose();
                }
            }
        });

        setVisible(true);
    }
}