package gui;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class FollowUser {

    public FollowUser(String myID, String userID) {
        try {
            Follow(myID, userID);
        } catch (FollowException e) {
            // 팔로우 중 오류가 발생했을 때 오류 창을 띄웁니다.
            showErrorDialog("Follow Error", e.getMessage());
        }
    }

    private void Follow(String myID, String userID) throws FollowException {
        // Check if myID and userID are the same
        if (myID.equals(userID)) {
            // 자기 자신을 팔로우할 수 없는 경우 예외를 던집니다.
            throw new FollowException("자기 자신을 팔로우 할 수 없습니다.");
        }

        // JDBC 연결 정보
        if (isNotFollowing(myID, userID)) {
        	String jdbcUrl = "jdbc:mysql://localhost/facebook";
            String username = "root";
            String password = "tjsals2620";

            try {
                // JDBC 드라이버 로드
                Class.forName("com.mysql.cj.jdbc.Driver");

                // 데이터베이스에 연결
                Connection connection = DriverManager.getConnection(jdbcUrl, username, password);

                // SQL 쿼리 실행
                String query1 = "UPDATE user SET following = following + 1 WHERE user_ID = ?";
                PreparedStatement preparedStatement1 = connection.prepareStatement(query1);
                preparedStatement1.setString(1, myID);
                int update1 = preparedStatement1.executeUpdate();

                String query2 = "UPDATE user SET follower = follower + 1 WHERE user_ID = ?";
                PreparedStatement preparedStatement2 = connection.prepareStatement(query2);
                preparedStatement2.setString(1, userID);
                int update2 = preparedStatement2.executeUpdate();

                String query3 = "INSERT INTO following VALUES (?, ?)";
                PreparedStatement preparedStatement3 = connection.prepareStatement(query3);
                preparedStatement3.setString(1, myID);
                preparedStatement3.setString(2, userID);
                int update3 = preparedStatement3.executeUpdate();

                String query4 = "INSERT INTO follower VALUES (?, ?)";
                PreparedStatement preparedStatement4 = connection.prepareStatement(query4);
                preparedStatement4.setString(1, userID);
                preparedStatement4.setString(2, myID);
                int update4 = preparedStatement4.executeUpdate();
                
                JOptionPane.showMessageDialog(null, "팔로우 성공", "알림", JOptionPane.INFORMATION_MESSAGE);
                System.out.println("Follow successful");

            } catch (ClassNotFoundException | SQLException e) {
                // Follow 중 예외가 발생했을 때 예외를 던집니다.
                throw new FollowException("팔로우 중 오류가 발생했습니다.");
            }
        }
        else {
        	throw new FollowException("이미 팔로우하고 있습니다.");
        }
    }

    private Boolean isNotFollowing(String myID, String userID) {
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
            String query = "SELECT * FROM following WHERE user_ID = ? AND following_ID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, myID);
            preparedStatement.setString(2, userID);
            ResultSet resultSet = preparedStatement.executeQuery();

            // Check if resultSet has any rows
            if (resultSet.next()) {
                System.out.println("팔로우하고있습니다");
                return false;
            } else {
                System.out.println("팔로우하고있지않습니다");
                return true;
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        // 오류가 발생한 경우 false 반환
        return false;
    }

    private void showErrorDialog(String title, String message) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FollowUser("11111", "22222"));
    }
}

// Follow 중 예외를 나타내는 사용자 정의 예외 클래스
class FollowException extends Exception {
    public FollowException(String message) {
        super(message);
    }
}