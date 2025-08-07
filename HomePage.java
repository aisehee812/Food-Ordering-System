import javax.swing.*;
import java.awt.*;
import java.sql.*;

class HomePage extends JFrame
{
    HomePage(String username)
    {

        Font f = new Font("SansSerif", Font.BOLD, 42);
        Font f2 = new Font("SansSerif", Font.ITALIC, 22);
        Font f3 = new Font("SansSerif", Font.BOLD, 16);

        JLabel t1 = new JLabel("Zomato Clone", JLabel.CENTER);
        JLabel t2 = new JLabel("Find your favorite food!", JLabel.CENTER);
        JTextField searchBar = new JTextField();
        JButton searchButton = new JButton("Search");

        JLabel L1 = new JLabel("Popular Categories");
        JButton b1 = new JButton("🍛 Biryani");
        JButton b2 = new JButton("🍕 Pizza");
        JButton b3 = new JButton("🍷 Drinks");
        JButton b4 = new JButton("🍔 Burgers");
        JButton b5 = new JButton("🍞 Sandwich");

        t1.setFont(f);
        t1.setForeground(new Color(220, 60, 60));
        t2.setFont(f2);
        searchBar.setFont(f2);
        searchButton.setFont(f3);
        L1.setFont(f3);
        b1.setFont(f2);
        b2.setFont(f2);
        b3.setFont(f2);
        b4.setFont(f2);
        b5.setFont(f2);

        Container c = getContentPane();
        c.setLayout(null);
        c.setBackground(Color.WHITE);

        JButton profileButton = new JButton("👤");
        profileButton.setFont(new Font("SansSerif", Font.PLAIN, 18));
        profileButton.setBounds(20, 20, 40, 40);
        profileButton.setFocusPainted(false);
        profileButton.setBackground(Color.WHITE);
        profileButton.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        profileButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        c.add(profileButton);

        profileButton.addActionListener(e ->
                JOptionPane.showMessageDialog(null, "Profile page coming soon!")
        );

        JButton cartButton = new JButton("🛒");
        cartButton.setFont(new Font("SansSerif", Font.PLAIN, 18));
        cartButton.setBounds(520, 20, 40, 40);
        cartButton.setFocusPainted(false);
        cartButton.setContentAreaFilled(true);
        cartButton.setBackground(Color.WHITE);
        cartButton.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        cartButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        cartButton.setToolTipText("View Cart");
        cartButton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));

        t1.setBounds(150, 30, 300, 40);
        t2.setBounds(150, 80, 300, 30);
        searchBar.setBounds(90, 140, 400, 30);
        searchButton.setBounds(500, 140, 90, 30);
        L1.setBounds(180, 200, 300, 30);

        b1.setBounds(50, 280, 140, 50);
        b2.setBounds(210, 280, 130, 50);
        b3.setBounds(360, 280, 130, 50);
        b4.setBounds(100, 380, 160, 50);
        b5.setBounds(270, 380, 170, 50);

        c.add(t1);
        c.add(t2);
        c.add(searchBar);
        c.add(searchButton);
        c.add(L1);
        c.add(b1);
        c.add(b2);
        c.add(b3);
        c.add(b4);
        c.add(b5);
        c.add(cartButton);



        searchButton.addActionListener(a -> {
            String input = searchBar.getText().trim().toLowerCase();

            if (input.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter a restaurant or food item to search.");
                return;
            }

            try {
                // JDBC setup
                String url = "jdbc:mysql://localhost:3306/zomato_clone";
                Connection con = DriverManager.getConnection(url, "root", "harshal18");

                // Check if input matches a restaurant name
                String checkRestaurant = "SELECT * FROM restaurants WHERE LOWER(name) = ?";
                PreparedStatement pst1 = con.prepareStatement(checkRestaurant);
                pst1.setString(1, input);
                ResultSet rs1 = pst1.executeQuery();

                if (rs1.next()) {
                    new MenuPage(input);  // Go to restaurant's full menu
                    dispose();
                } else {
                    // Else treat input as food item
                    new RestaurantListPage(input);  // Show all restaurants having the food item
                    dispose();
                }

                con.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        });


        b1.addActionListener(e -> {
            String searchItem = "Biryani";
            new RestaurantListPage(searchItem);
            dispose();  // Optional: close HomePage
        });

        b2.addActionListener(e -> {
            String searchItem = "Pizza";
            new RestaurantListPage(searchItem);
            dispose();  // Optional: close HomePage
        });

        b3.addActionListener(e -> {
            String searchItem = "Drinks";
            new RestaurantListPage(searchItem);
            dispose();  // Optional: close HomePage
        });

        b4.addActionListener(e -> {
            String searchItem = "Burgers";
            new RestaurantListPage(searchItem);
            dispose();  // Optional: close HomePage
        });

        b5.addActionListener(e -> {
            String searchItem = "Sandwich";
            new RestaurantListPage(searchItem);
            dispose();  // Optional: close HomePage
        });




        setTitle("Zomato Food Ordering System");
        setSize(600, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel footer = new JLabel("© 2025 Zomato Clone. All rights reserved.");
        footer.setFont(new Font("SansSerif", Font.PLAIN, 12));
        footer.setBounds(160, 600, 300, 30);
        add(footer);

        setVisible(true);

    }

    public static void main(String[] args) {

        new HomePage("Welcome");
    }
}