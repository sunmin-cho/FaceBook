package gui.posting;
import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class LikeComment {

    public LikeComment(String myID, String commentID) {
        
        System.out.println("좋아요 누르시겠습니까?");
        Like(myID, commentID);
    }

    private void Like(String myID, String commentID) {
        
        // JDBC 연결 정보
        if (notLike(myID, commentID)) {
            String jdbcUrl = "jdbc:mysql://localhost/facebook";
            String username = "root"; 
            String password = "tjsals2620";

            try {
                // JDBC 드라이버 로드
                Class.forName("com.mysql.cj.jdbc.Driver");

                // 데이터베이스에 연결
                Connection connection = DriverManager.getConnection(jdbcUrl, username, password);

                // SQL 쿼리 실행

                String query1 = "INSERT INTO comment_likes VALUES (?, ?)";
                PreparedStatement preparedStatement3 = connection.prepareStatement(query1);
                preparedStatement3.setString(1, myID);
                preparedStatement3.setString(2, commentID);
                int update3 = preparedStatement3.executeUpdate();

                // Show a dialog box with a success message
                JOptionPane.showMessageDialog(null, "좋아요가 등록되었습니다", "알림", JOptionPane.INFORMATION_MESSAGE);

            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private Boolean notLike(String myID, String commentID) {
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
            String query = "SELECT * FROM comment_likes WHERE user_ID = ? AND comment_ID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, myID);
            preparedStatement.setString(2, commentID);
            ResultSet resultSet = preparedStatement.executeQuery();

            // Check if resultSet has any rows
            if (resultSet.next()) {
            	JOptionPane.showMessageDialog(null, "이미 좋아요를 눌렀습니다", "알림", JOptionPane.INFORMATION_MESSAGE);
                return false;
            } else {
                System.out.println("아직 좋아요를 누르지 않았습니다.");
                return true;
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        // 오류가 발생한 경우 false 반환
        return false;
    }
}

