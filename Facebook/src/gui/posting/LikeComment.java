package gui.posting;
import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class LikeComment {

    public LikeComment(String myID, String commentID) {
        
        System.out.println("���ƿ� �����ðڽ��ϱ�?");
        Like(myID, commentID);
    }

    private void Like(String myID, String commentID) {
        
        // JDBC ���� ����
        if (notLike(myID, commentID)) {
            String jdbcUrl = "jdbc:mysql://localhost/facebook";
            String username = "root"; 
            String password = "tjsals2620";

            try {
                // JDBC ����̹� �ε�
                Class.forName("com.mysql.cj.jdbc.Driver");

                // �����ͺ��̽��� ����
                Connection connection = DriverManager.getConnection(jdbcUrl, username, password);

                // SQL ���� ����

                String query1 = "INSERT INTO comment_likes VALUES (?, ?)";
                PreparedStatement preparedStatement3 = connection.prepareStatement(query1);
                preparedStatement3.setString(1, myID);
                preparedStatement3.setString(2, commentID);
                int update3 = preparedStatement3.executeUpdate();

                // Show a dialog box with a success message
                JOptionPane.showMessageDialog(null, "���ƿ䰡 ��ϵǾ����ϴ�", "�˸�", JOptionPane.INFORMATION_MESSAGE);

            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private Boolean notLike(String myID, String commentID) {
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
            String query = "SELECT * FROM comment_likes WHERE user_ID = ? AND comment_ID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, myID);
            preparedStatement.setString(2, commentID);
            ResultSet resultSet = preparedStatement.executeQuery();

            // Check if resultSet has any rows
            if (resultSet.next()) {
            	JOptionPane.showMessageDialog(null, "�̹� ���ƿ並 �������ϴ�", "�˸�", JOptionPane.INFORMATION_MESSAGE);
                return false;
            } else {
                System.out.println("���� ���ƿ並 ������ �ʾҽ��ϴ�.");
                return true;
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        // ������ �߻��� ��� false ��ȯ
        return false;
    }
}

