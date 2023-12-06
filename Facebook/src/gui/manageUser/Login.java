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
    	
        setTitle("Facebook - 로그인");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);
        getContentPane().setBackground(new Color(59, 89, 152)); // Facebook's blue background

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(59, 89, 152));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(4, 4, 4, 4);
        
        // Facebook 로고
        JLabel facebookLogo = new JLabel("Facebook", SwingConstants.CENTER);
        facebookLogo.setForeground(Color.WHITE);
        facebookLogo.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.insets = new Insets(10, 10, 20, 10);
        panel.add(facebookLogo, gbc);
        gbc.insets = new Insets(5, 10, 5, 10);

        Font labelFont = new Font("Arial", Font.BOLD, 12);
        Font fieldFont = new Font("Arial", Font.PLAIN, 12);


        // 유저이름? 아이디
        JLabel usernameLabel = new JLabel("사용자명:");
        usernameLabel.setForeground(Color.WHITE);
        usernameField = new JTextField(15);
        panel.add(usernameLabel, gbc);
        panel.add(usernameField, gbc);

        // 패스워드 입력창
        JLabel passwordLabel = new JLabel("비밀번호:");
        passwordLabel.setForeground(Color.WHITE);
        passwordField = new JPasswordField(15);
        panel.add(passwordLabel, gbc);
        panel.add(passwordField, gbc);

        // 로그인 버튼
        JButton loginButton = new JButton("로그인");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performLogin();
            }
        });
        panel.add(loginButton, gbc);

        // 취소 버튼
        JButton cancelButton = new JButton("취소");
        cancelButton.addActionListener(e -> dispose());
        panel.add(cancelButton, gbc);

        add(panel);
        setLocationRelativeTo(null);
        
        
        JButton backButton = new JButton("뒤로 가기");
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
    	            dispose(); // 현재 창 닫기
    	            System.out.println("username ="+username);
    	            openMainPage(username); // 메인 페이지 열기
    	        } else {
    	            JOptionPane.showMessageDialog(this, "로그인 실패. 사용자명 또는 비밀번호를 확인하세요.");
    	           
    	        }
        	}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

        // 비밀번호 필드 초기화
        passwordField.setText("");
    }

    
    
   
    private ResultSet getUserInfo(String ID) {
        // JDBC 연결 정보
        String jdbcUrl = "jdbc:mysql://localhost/facebook";
        String username = "root";
        String password = "tjsals2620";

        try {
            // JDBC 드라이버 로드
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 데이터베이스에 연결
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);

            // SQL 쿼리 실행
            String query = "SELECT * FROM user WHERE user_ID = '" + ID + "'";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            // ResultSet을 반환
            return resultSet;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        // 오류가 발생한 경우 null 반환
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
