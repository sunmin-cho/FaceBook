package gui.manageUser;

import java.awt.*;
import javax.swing.*;

import gui.MainPage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Login extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;

    public Login() {
    	
        setTitle("Facebook - �α���");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);
        getContentPane().setBackground(new Color(59, 89, 152)); // Facebook's blue background

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(59, 89, 152));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(4, 4, 4, 4);
        
        // Facebook �ΰ�
        JLabel facebookLogo = new JLabel("Facebook", SwingConstants.CENTER);
        facebookLogo.setForeground(Color.WHITE);
        facebookLogo.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.insets = new Insets(10, 10, 20, 10);
        panel.add(facebookLogo, gbc);
        gbc.insets = new Insets(5, 10, 5, 10);

        Font labelFont = new Font("Arial", Font.BOLD, 12);
        Font fieldFont = new Font("Arial", Font.PLAIN, 12);


        // �����̸�? ���̵�
        JLabel usernameLabel = new JLabel("����ڸ�:");
        usernameLabel.setForeground(Color.WHITE);
        usernameField = new JTextField(15);
        panel.add(usernameLabel, gbc);
        panel.add(usernameField, gbc);

        // �н����� �Է�â
        JLabel passwordLabel = new JLabel("��й�ȣ:");
        passwordLabel.setForeground(Color.WHITE);
        passwordField = new JPasswordField(15);
        panel.add(passwordLabel, gbc);
        panel.add(passwordField, gbc);

        // �α��� ��ư
        JButton loginButton = new JButton("�α���");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performLogin();
            }
        });
        panel.add(loginButton, gbc);

        // ��� ��ư
        JButton cancelButton = new JButton("���");
        cancelButton.addActionListener(e -> dispose());
        panel.add(cancelButton, gbc);

        add(panel);
        setLocationRelativeTo(null);
        
        
        JButton backButton = new JButton("�ڷ� ����");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openHomePage();
            }
        });

        // Adjusting GridBagLayout constraints for the Back button
        gbc.gridx = 0; // Position at the first column
        gbc.gridy = GridBagConstraints.RELATIVE; // Next available row
        gbc.anchor = GridBagConstraints.LINE_START; // Align to start of line (left side)
        panel.add(backButton, gbc);
        
        
    }
    
    private void openHomePage() {
        Home home = new Home();
        home.setVisible(true);
        dispose(); // Close the Login screen
    }

    
    private void performLogin() {
        String username = usernameField.getText();
        char[] passwordChars = passwordField.getPassword();
        String password = new String(passwordChars);
        
        ResultSet user_info = getUserInfo(username);

        try {
        	if (user_info.next()) {
        		String user_password = user_info.getString("password");
    			
    	        if ( password.equals(user_password)) {
    	            dispose(); // ���� â �ݱ�
    	            System.out.println("username ="+username);
    	            openMainPage(username); // ���� ������ ����
    	        } else {
    	            JOptionPane.showMessageDialog(this, "�α��� ����. ����ڸ� �Ǵ� ��й�ȣ�� Ȯ���ϼ���.");
    	           
    	        }
        	}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

        // ��й�ȣ �ʵ� �ʱ�ȭ
        passwordField.setText("");
    }

    
    
   
    private ResultSet getUserInfo(String ID) {
        // JDBC ���� ����
        String jdbcUrl = "jdbc:mysql://localhost/facebook";
        String username = "root";
        String password = "tjsals2620";

        try {
            // JDBC ����̹� �ε�
            Class.forName("com.mysql.cj.jdbc.Driver");

            // �����ͺ��̽��� ����
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);

            // SQL ���� ����
            String query = "SELECT * FROM user WHERE user_ID = '" + ID + "'";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            // ResultSet�� ��ȯ
            return resultSet;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        // ������ �߻��� ��� null ��ȯ
        return null;
    }

    private void openMainPage(String username) {
        MainPage mainPage = new MainPage(username); // Adjust this if MainPage is in another package
        mainPage.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Login().setVisible(true));
    }
}
