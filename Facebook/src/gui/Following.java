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

public class Following extends JFrame {

    public Following(String ID) {
        // ���� ���̵�
        String userId = ID;

        ResultSet following = getFollowing(userId);

        // JFrame ����
        setTitle("User following");
        setSize(400, 300);
        // ���� â�� �ݵ��� ���� ����
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // �г� ����
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        try {
            // ResultSet���� ������ �б�
            while (following.next()) {
                String following_ID = following.getString("following_ID");

                // ��ư ���� �� �гο� �߰�
                JButton button = new JButton("Following ID: " + following_ID);
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // �ȷ����ϴ� ���̵��� ������������ ��쵵�� �ٲ���Ѵ�.
                        SwingUtilities.invokeLater(() -> new UserPage(ID,following_ID));
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

    private ResultSet getFollowing(String ID) {
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
            String query = "SELECT following_ID FROM following WHERE user_id = '" + ID + "'";
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
        SwingUtilities.invokeLater(() -> new Following("12222"));
    }
}
