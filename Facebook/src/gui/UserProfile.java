package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.*;

import gui.left.SmallProfilePanel;

public class UserProfile extends JPanel {

    public UserProfile(String ID) {
        String userId = ID;

        ResultSet userInfo = getUserInfo(userId);
        
        // Set up layout manager
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10)); // Center alignment and margins

        try {
            // Read data from ResultSet
            if (userInfo.next()) {
                String userName = userInfo.getString("user_name");

                // Add SmallProfilePanel
                SmallProfilePanel smallProfilePanel = new SmallProfilePanel(userId, userName, "path_to_small_profile_image.jpg");
                add(smallProfilePanel);

                // Other user details
                String birthday = userInfo.getString("birthday");
                String address = userInfo.getString("address");
                String age = userInfo.getString("age");
                String following = userInfo.getString("following");
                String follower = userInfo.getString("follower");

                // Add remaining details to the panel
                add(new JLabel("Birthday: " + birthday));
                add(new JLabel("Address: " + address));
                add(new JLabel("Age: " + age));
                add(new JLabel("Following: " + following));
                add(new JLabel("Follower: " + follower));

                // Create and add "Show Profile" button
                JButton button = new JButton("Show profile");
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Implement the action to display the user page
                        SwingUtilities.invokeLater(() -> new UserPage(ID,userId)); // Assuming UserPage is a class you have defined
                    }
                });
                add(button);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Set the preferred size of the panel
        setPreferredSize(new Dimension(250, 400)); // Set to desired size
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
            String query = "SELECT * FROM user WHERE user_id = '" + ID + "'";
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
    
    
    // 크기 조절된 이미지 아이콘 생성 메서드
    protected ImageIcon createResizedImageIcon(String path, int width, int height) {
        ImageIcon originalIcon = createImageIcon(path);
        if (originalIcon != null) {
            Image originalImage = originalIcon.getImage();
            Image resizedImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            return new ImageIcon(resizedImage);
        } else {
            return null;
        }
    }

    // 이미지 아이콘 생성 메서드
    protected ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("이미지를 찾을 수 없습니다: " + path);
            return null;
        }
    }
}
