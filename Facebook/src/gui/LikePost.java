package gui;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class LikePost {

    public LikePost(String myID, String postID) {
        
        System.out.println("���ƿ� �����ðڽ��ϱ�?");
        Like(myID, postID);
    }

    private void Like(String myID, String postID) {
        // JDBC ���� ����
        if (notLike(myID, postID)) {
            String jdbcUrl = "jdbc:mysql://localhost/facebook";
            String username = "root";
            String password = "tjsals2620";

            try {
                // JDBC ����̹� �ε�
                Class.forName("com.mysql.cj.jdbc.Driver");

                // �����ͺ��̽��� ����
                Connection connection = DriverManager.getConnection(jdbcUrl, username, password);

                // SQL ���� ����
                String query1 = "INSERT INTO post_likes VALUES (?, ?)";
                PreparedStatement preparedStatement3 = connection.prepareStatement(query1);
                preparedStatement3.setString(1, myID);
                preparedStatement3.setString(2, postID);
                int update3 = preparedStatement3.executeUpdate();

                // ���� �޽����� ���Ե� ��ȭ ���� ǥ��
                JOptionPane.showMessageDialog(null, "���ƿ䰡 ��ϵǾ����ϴ�", "�˸�", JOptionPane.INFORMATION_MESSAGE);

            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
    }


    private Boolean notLike(String myID, String postID) {
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
            String query = "SELECT * FROM post_likes WHERE user_ID = ? AND post_ID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, myID);
            preparedStatement.setString(2, postID);
            ResultSet resultSet = preparedStatement.executeQuery();

            // resultSet�� ���� �ִ��� Ȯ��
            if (resultSet.next()) {
            	JOptionPane.showMessageDialog(null, "�̹� ���ƿ並 �������ϴ�", "�˸�", JOptionPane.INFORMATION_MESSAGE);
                return false;
            } else {
                System.out.println("���� ���ƿ並 ������ �ʾҽ��ϴ�");
                return true;
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        // ������ �߻��� ��� false ��ȯ
        return false;
    }
}
