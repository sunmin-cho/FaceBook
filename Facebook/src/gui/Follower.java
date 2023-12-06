package gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Follower extends JFrame {

    public Follower(String ID) {
        // 유저 아이디
        String userId = ID;

        ResultSet follower = getFollower(userId);

        // JFrame 설정
        setTitle("User Follower");
        setSize(400, 300);
        // 단일 창만 닫도록 설정 변경
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // 패널 생성
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        try {
            // ResultSet에서 데이터 읽기
            while (follower.next()) {
                String follower_ID = follower.getString("follower_ID");

                // 버튼 생성 및 패널에 추가
                JButton button = new JButton("Follower ID: " + follower_ID);
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // 팔로워의 유저 페이지를 띄우도록 구현
                        SwingUtilities.invokeLater(() -> new UserPage(ID,follower_ID));
                    }
                });
                panel.add(button);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // 패널을 프레임에 추가
        add(panel);

        // 프레임 표시
        setVisible(true);
    }

    private ResultSet getFollower(String ID) {
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
            String query = "SELECT follower_ID FROM follower WHERE user_id = '" + ID + "'";
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
        // Swing UI는 이벤트 디스패치 스레드에서 실행되어야 합니다.
        SwingUtilities.invokeLater(() -> new Follower("12222"));
    }
}
