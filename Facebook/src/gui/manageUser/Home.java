package gui.manageUser;

import javax.swing.*;
import java.awt.*;

public class Home extends JFrame {

    public Home() {
        // Setting the frame properties
        setTitle("Facebook Screen");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(59, 89, 152)); // Facebook color
        setLayout(new GridBagLayout()); // Using GridBagLayout for precise positioning

        GridBagConstraints gbc = new GridBagConstraints();
        
        // Adding Facebook logo text
        JLabel facebookLabel = new JLabel("Facebook");
        facebookLabel.setForeground(Color.WHITE);
        facebookLabel.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 0, 20, 0); // top, left, bottom, right padding
        add(facebookLabel, gbc);

        // Adding Register button
        JButton registerButton = new JButton("Register");
        registerButton.setPreferredSize(new Dimension(100, 100)); // Square shape
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(10, 10, 10, 10); // Padding around the button
        add(registerButton, gbc);

        // Adding Login button
        JButton loginButton = new JButton("Login");
        loginButton.setPreferredSize(new Dimension(100, 100)); // Square shape
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(loginButton, gbc);
        
        registerButton.addActionListener(e -> {
            SignUpForm signUpForm = new SignUpForm();
            dispose();
            signUpForm.setVisible(true);
        });

        // Adding action listener to Login button
        loginButton.addActionListener(e -> {
            Login loginForm = new Login();
            dispose();
            loginForm.setVisible(true);
           
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Home screen = new Home();
            screen.setVisible(true);
        });
    }
}
