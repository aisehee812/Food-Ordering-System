import javax.swing.*;
import java.awt.*;

class Landing extends JFrame {
    Landing() {
        Font f = new Font("futura", Font.BOLD, 40);
        Font f2 = new Font("Calibri", Font.PLAIN, 22);

        JLabel l1 = new JLabel("Food Delivery Application", JLabel.CENTER);
        JButton b1 = new JButton("Existing Customer");
        JButton b2 = new JButton("New Customer");

        l1.setFont(f);
        b1.setFont(f2);
        b2.setFont(f2);

        Container c = getContentPane();
        c.setLayout(null);

        l1.setBounds(150, 50, 500, 50);
        b1.setBounds(300, 230, 200, 50);
        b2.setBounds(300, 310, 200, 50);

        c.add(l1);
        c.add(b1);
        c.add(b2);

        b1.addActionListener(
                a->{
                    new Elogin();
                    dispose();
                }
        );
        b2.addActionListener(
                a->{
                    new Nlogin();
                    dispose();
                }
        );

        setVisible(true);
        setSize(800, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Landing Page");
    }

    public static void main(String[] args) {
        Landing a = new Landing();
    }
}

