package gui.manageUser;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


// ȸ������ ������ 
public class SignUpForm extends JFrame {


    public SignUpForm() {
        setTitle("Facebook - ȸ������");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(59, 89, 152)); // Facebook blue background

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 10, 5, 10);
        
        // ���̽��� �ΰ� 
        JLabel facebookLogo = new JLabel("Facebook", SwingConstants.CENTER);
        facebookLogo.setForeground(Color.WHITE);
        facebookLogo.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.insets = new Insets(10, 10, 20, 10);
        add(facebookLogo, gbc);
        gbc.insets = new Insets(5, 10, 5, 10);

        Font labelFont = new Font("Arial", Font.BOLD, 12);
        Font fieldFont = new Font("Arial", Font.PLAIN, 12);
        
        // ID
        JLabel IDLabel = new JLabel("ID:");
        IDLabel.setForeground(Color.WHITE);
        IDLabel.setFont(labelFont);
        add(IDLabel, gbc);

        JTextField IDField = new JTextField(20);
        IDField.setFont(fieldFont);
        add(IDField, gbc);
        
        // ���   
        JLabel passwordLabel = new JLabel("New Password:");
        passwordLabel.setForeground(Color.WHITE);
        passwordLabel.setFont(labelFont);
        add(passwordLabel, gbc);

        JPasswordField passwordField = new JPasswordField(20);
        passwordField.setFont(fieldFont);
        add(passwordField, gbc);

        
        //���Ȯ�� 
        JLabel passwordCheckLabel = new JLabel("Check password:");
        passwordCheckLabel.setForeground(Color.WHITE);
        passwordCheckLabel.setFont(labelFont);
        add(passwordCheckLabel, gbc);

        JPasswordField passwordCheckLabelField = new JPasswordField(20);
        passwordCheckLabelField.setFont(fieldFont);
        add(passwordCheckLabelField, gbc);
        
        // �̸� 
        JLabel nameLabel = new JLabel("Full Name:");
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setFont(labelFont);
        add(nameLabel, gbc);

        JTextField nameField = new JTextField(20);
        nameField.setFont(fieldFont);
        add(nameField, gbc);
        
        // ����ȣ
        JLabel PhoneNumLabel = new JLabel("Phone number:");
        PhoneNumLabel.setForeground(Color.WHITE);
        PhoneNumLabel.setFont(labelFont);
        add(PhoneNumLabel, gbc);

        JTextField PhoneNumField = new JTextField(20);
        PhoneNumField.setFont(fieldFont);
        add(PhoneNumField, gbc);
        
        
        // ���� 
        JLabel dobLabel = new JLabel("Date of Birth:");
        dobLabel.setForeground(Color.WHITE);
        dobLabel.setFont(labelFont);
        add(dobLabel, gbc);

        JTextField dobField = new JTextField(20);
        dobField.setFont(fieldFont);
        add(dobField, gbc);
        
        
        // �ּ�
        JLabel addressLabel = new JLabel("Your address");
        addressLabel.setForeground(Color.WHITE);
        addressLabel.setFont(labelFont);
        add(addressLabel, gbc);

        JTextField addressField = new JTextField(20);
        addressField.setFont(fieldFont);
        add(addressField, gbc);
        
        
     // ���Թ�ư
        JButton signUpButton = new JButton("Sign Up");
        signUpButton.setBackground(Color.WHITE);
        signUpButton.setForeground(new Color(59, 89, 152));
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // ����ڰ� �Է��� ���� ��������
                String userID = IDField.getText();
                String password = new String(passwordField.getPassword());
                String passwordConfirm = new String(passwordCheckLabelField.getPassword());
                String name = nameField.getText();
                String phoneNumber = PhoneNumField.getText();
                String birthday = dobField.getText();
                String address = addressField.getText();
                if(password.equals(passwordConfirm)) {
                	// getSignUp �޼��� ȣ��
                    getSignUp(userID, password, name, phoneNumber, birthday, address);
                    dispose(); // ���� â �ݱ�
                    new Login().setVisible(true);
                }
                else {
                	JOptionPane.showMessageDialog(SignUpForm.this, "��й�ȣ�� ��ġ���� �ʽ��ϴ�. �ٽ� Ȯ���ϼ���.", "��й�ȣ ����ġ", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        add(signUpButton, gbc);
        
        JButton backButton = new JButton("�ڷ� ����");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openHomePage();
            }
        });

        // GridBagConstraints ���� ����
        GridBagConstraints gbcBack = new GridBagConstraints();
        gbcBack.gridwidth = GridBagConstraints.REMAINDER;
        gbcBack.fill = GridBagConstraints.HORIZONTAL;
        gbcBack.anchor = GridBagConstraints.LINE_START; // ���� ����
        gbcBack.insets = new Insets(5, 10, 5, 10);
        
        add(backButton, gbcBack);
        
    } 
    
    private void openHomePage() {
        Home home = new Home();
        home.setVisible(true);
        dispose(); // ���� SignUpForm â �ݱ�
    }
    
    private void getSignUp(String ID, String password, String name, String phoneNumber, String birthday, String address) {
        // JDBC ���� ����
        String jdbcUrl = "jdbc:mysql://localhost/facebook";
        String username = "root";
        String DBpassword = "tjsals2620";

        try {
            // JDBC ����̹� �ε�
            Class.forName("com.mysql.cj.jdbc.Driver");

            // �����ͺ��̽��� ����
            Connection connection = DriverManager.getConnection(jdbcUrl, username, DBpassword);

            // SQL ���� ����
            String query = "INSERT INTO user (user_ID, password, user_name, phone_number, birthday, address, follower, following, phone_number2) VALUES (?, ?, ?, ?, ?, ?,null,null,null)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, ID);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, name);
            preparedStatement.setString(4, phoneNumber);
            preparedStatement.setString(5, birthday);
            preparedStatement.setString(6, address);
            preparedStatement.executeUpdate();  // executeQuery ��� executeUpdate�� ���

            System.out.println("ȸ������ �Ϸ�");

            // ���� �� �ڿ� ����
            preparedStatement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            // �������� ��� ����ڿ��� �˸��� �޽��� �Ǵ� �ٸ� ó�� �߰� ����
        }
    }

    

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
        	SignUpForm signUpForm = new SignUpForm();
            signUpForm.setVisible(true);
        });
    }
}