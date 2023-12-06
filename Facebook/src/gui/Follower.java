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
        // ���� ���̵�
        String userId = ID;

        ResultSet follower = getFollower(userId);

        // JFrame ����
        setTitle("User Follower");
        setSize(400, 300);
        // ���� â�� �ݵ��� ���� ����
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // �г� ����
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        try {
            // ResultSet���� ������ �б�
            while (follower.next()) {
                String follower_ID = follower.getString("follower_ID");

                // ��ư ���� �� �гο� �߰�
                JButton button = new JButton("Follower ID: " + follower_ID);
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // �ȷο��� ���� �������� ��쵵�� ����
                        SwingUtilities.invokeLater(() -> new UserPage(ID,follower_ID));
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
    }

    private ResultSet getFollower(String ID) {
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
            String query = "SELECT follower_ID FROM follower WHERE user_id = '" + ID + "'";
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
        SwingUtilities.invokeLater(() -> new Follower("12222"));
    }
}
