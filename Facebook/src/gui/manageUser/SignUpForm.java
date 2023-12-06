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


// 회원가입 페이지 
public class SignUpForm extends JFrame {


    public SignUpForm() {
        setTitle("Facebook - 회원가입");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(59, 89, 152)); // Facebook blue background

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 10, 5, 10);
        
        // 페이스북 로고 
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
        
        // 비번   
        JLabel passwordLabel = new JLabel("New Password:");
        passwordLabel.setForeground(Color.WHITE);
        passwordLabel.setFont(labelFont);
        add(passwordLabel, gbc);

        JPasswordField passwordField = new JPasswordField(20);
        passwordField.setFont(fieldFont);
        add(passwordField, gbc);

        
        //비번확인 
        JLabel passwordCheckLabel = new JLabel("Check password:");
        passwordCheckLabel.setForeground(Color.WHITE);
        passwordCheckLabel.setFont(labelFont);
        add(passwordCheckLabel, gbc);

        JPasswordField passwordCheckLabelField = new JPasswordField(20);
        passwordCheckLabelField.setFont(fieldFont);
        add(passwordCheckLabelField, gbc);
        
        // 이름 
        JLabel nameLabel = new JLabel("Full Name:");
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setFont(labelFont);
        add(nameLabel, gbc);

        JTextField nameField = new JTextField(20);
        nameField.setFont(fieldFont);
        add(nameField, gbc);
        
        // 폰번호
        JLabel PhoneNumLabel = new JLabel("Phone number:");
        PhoneNumLabel.setForeground(Color.WHITE);
        PhoneNumLabel.setFont(labelFont);
        add(PhoneNumLabel, gbc);

        JTextField PhoneNumField = new JTextField(20);
        PhoneNumField.setFont(fieldFont);
        add(PhoneNumField, gbc);
        
        
        // 생일 
        JLabel dobLabel = new JLabel("Date of Birth:");
        dobLabel.setForeground(Color.WHITE);
        dobLabel.setFont(labelFont);
        add(dobLabel, gbc);

        JTextField dobField = new JTextField(20);
        dobField.setFont(fieldFont);
        add(dobField, gbc);
        
        
        // 주소
        JLabel addressLabel = new JLabel("Your address");
        addressLabel.setForeground(Color.WHITE);
        addressLabel.setFont(labelFont);
        add(addressLabel, gbc);

        JTextField addressField = new JTextField(20);
        addressField.setFont(fieldFont);
        add(addressField, gbc);
        
        
     // 가입버튼
        JButton signUpButton = new JButton("Sign Up");
        signUpButton.setBackground(Color.WHITE);
        signUpButton.setForeground(new Color(59, 89, 152));
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 사용자가 입력한 정보 가져오기
                String userID = IDField.getText();
                String password = new String(passwordField.getPassword());
                String passwordConfirm = new String(passwordCheckLabelField.getPassword());
                String name = nameField.getText();
                String phoneNumber = PhoneNumField.getText();
                String birthday = dobField.getText();
                String address = addressField.getText();
                if(password.equals(passwordConfirm)) {
                	// getSignUp 메서드 호출
                    getSignUp(userID, password, name, phoneNumber, birthday, address);
                    dispose(); // 현재 창 닫기
                    new Login().setVisible(true);
                }
                else {
                	JOptionPane.showMessageDialog(SignUpForm.this, "비밀번호가 일치하지 않습니다. 다시 확인하세요.", "비밀번호 불일치", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        add(signUpButton, gbc);
        
        JButton backButton = new JButton("뒤로 가기");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openHomePage();
            }
        });

        // GridBagConstraints 설정 변경
        GridBagConstraints gbcBack = new GridBagConstraints();
        gbcBack.gridwidth = GridBagConstraints.REMAINDER;
        gbcBack.fill = GridBagConstraints.HORIZONTAL;
        gbcBack.anchor = GridBagConstraints.LINE_START; // 좌측 정렬
        gbcBack.insets = new Insets(5, 10, 5, 10);
        
        add(backButton, gbcBack);
        
    } 
    
    private void openHomePage() {
        Home home = new Home();
        home.setVisible(true);
        dispose(); // 현재 SignUpForm 창 닫기
    }
    
    private void getSignUp(String ID, String password, String name, String phoneNumber, String birthday, String address) {
        // JDBC 연결 정보
        String jdbcUrl = "jdbc:mysql://localhost/facebook";
        String username = "root";
        String DBpassword = "tjsals2620";

        try {
            // JDBC 드라이버 로드
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 데이터베이스에 연결
            Connection connection = DriverManager.getConnection(jdbcUrl, username, DBpassword);

            // SQL 쿼리 실행
            String query = "INSERT INTO user (user_ID, password, user_name, phone_number, birthday, address, follower, following, phone_number2) VALUES (?, ?, ?, ?, ?, ?,null,null,null)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, ID);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, name);
            preparedStatement.setString(4, phoneNumber);
            preparedStatement.setString(5, birthday);
            preparedStatement.setString(6, address);
            preparedStatement.executeUpdate();  // executeQuery 대신 executeUpdate를 사용

            System.out.println("회원가입 완료");

            // 연결 및 자원 해제
            preparedStatement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            // 실패했을 경우 사용자에게 알리는 메시지 또는 다른 처리 추가 가능
        }
    }

    

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
        	SignUpForm signUpForm = new SignUpForm();
            signUpForm.setVisible(true);
        });
    }
}