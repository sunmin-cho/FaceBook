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

public class LikesWindow extends JFrame {

    public LikesWindow(String userID, String ID) {
        // ���� ���̵�
        String postId = ID;

        ResultSet likes = getLikes(postId);

        // JFrame ����
        setTitle("Likes");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // �г� ����
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        try {
            // ResultSet���� ������ �б�
            while (likes.next()) {
                String like_ID = likes.getString("user_ID");

                // ��ư ���� �� �гο� �߰�
                JButton button = new JButton("likes ID: " + like_ID);
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // �ȷ����ϴ� ���̵��� ������������ ��쵵�� �ٲ���Ѵ�. 
                    	SwingUtilities.invokeLater(() -> new UserPage(userID,like_ID));
                    }
                });
                panel.add(button);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // �г��� �����ӿ� �߰�
        add(panel);

        // ������ ǥ��
        setVisible(true);
        
        // ����â�� �������� ���� ����
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private ResultSet getLikes(String ID) {
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
            String query = "SELECT * FROM post_likes WHERE post_id = '" + ID + "'";
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

    public static void main(String[] args) {
        // Swing UI�� �̺�Ʈ ����ġ �����忡�� ����Ǿ�� �մϴ�.
        SwingUtilities.invokeLater(() -> new LikesWindow("11111","99999"));
    }
}
