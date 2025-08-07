// CartPage.java
import javax.swing.*;
import java.awt.*;
import java.util.*;

class CartPage extends JFrame {
    CartPage(ArrayList<String> items, ArrayList<Double> prices) {
        Font f1 = new Font("SansSerif", Font.BOLD, 28);
        Font f2 = new Font("SansSerif", Font.PLAIN, 18);

        JButton backButton = new JButton("← Back");

        JLabel title = new JLabel("🛒 Your Cart", JLabel.CENTER);
        title.setFont(f1);

        DefaultListModel<String> listModel = new DefaultListModel<>();
        JList<String> cartList = new JList<>(listModel);
        cartList.setFont(f2);
        JScrollPane scrollPane = new JScrollPane(cartList);

        double total = 0.0;
        for (int i = 0; i < items.size(); i++) {
            listModel.addElement(items.get(i));
            total += prices.get(i);
        }

        JLabel totalLabel = new JLabel("Total: ₹" + total);

        totalLabel.setFont(f2);
        backButton.setFont(f2);

        JButton checkoutButton = new JButton("Checkout");

        Container c = getContentPane();
        c.setLayout(null);

        backButton.setBounds(20, 20, 100, 30);
        title.setBounds(100, 20, 400, 40);
        scrollPane.setBounds(100, 80, 400, 300);
        totalLabel.setBounds(100, 390, 200, 30);
        checkoutButton.setBounds(320, 390, 130, 30);

        c.add(title);
        c.add(scrollPane);
        c.add(totalLabel);
        c.add(checkoutButton);
        c.add(backButton);


        checkoutButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "Order placed successfully!");
        });



        setTitle("Cart Page");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}