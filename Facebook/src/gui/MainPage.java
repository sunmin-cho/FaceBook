package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;
import gui.left.ProfileMenuPanel;
import gui.left.SmallProfilePanel;
import gui.manageUser.Logout;
import gui.center.FollowingPost;
import gui.center.PostCreationPanel;

public class MainPage extends JFrame {

    public MainPage(String userID) {
        setTitle("Main Screen");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        try {
        // Create an instance of MainPageBanner and get its top banner panel
        MainPageBanner bannerPage = new MainPageBanner();
        JPanel topBannerPanel = (JPanel) bannerPage.getContentPane().getComponent(0);

        // Add the top banner panel from MainPageBanner to this frame
        add(topBannerPanel, BorderLayout.NORTH);
        
        ResultSet userInfo = getUserInfo(userID);
        String userName = null;

            // ResultSet에서 데이터 읽기
         if (userInfo.next()) {
                userName = userInfo.getString("user_name");
         }
              
        // Create the left side panel
        JPanel leftSidePanel = new SmallProfilePanel(userID, userName, "profile.jpeg");

        // Create ProfileMenuPanel for the second part of the left side panel
        ProfileMenuPanel profileMenuPanel = new ProfileMenuPanel();

        // Split the left side panel into two parts
        JSplitPane leftSideSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, leftSidePanel, profileMenuPanel);
        leftSideSplitPane.setResizeWeight(0.05); // Adjust as needed

        // Create PostCreationPanel for the upper part of the right side
        PostCreationPanel postCreationPanel = new PostCreationPanel(userID);

        // Create FollowingPost for the lower part
        FollowingPost followingPostPanel = new FollowingPost(userID); // Assuming "11111" is the logged-in user ID

        // Split the right side panel into two parts
        JSplitPane rightSideSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, postCreationPanel, followingPostPanel);
        rightSideSplitPane.setResizeWeight(0.1); // Adjust as needed for the size of PostCreationPanel

        // Main split pane for the layout
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftSideSplitPane, rightSideSplitPane);
        splitPane.setResizeWeight(0.2); // Adjust as needed

        add(splitPane, BorderLayout.CENTER);
        }catch(Exception E) {
        	
        }
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
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainPage("11111").setVisible(true));
    }
}
