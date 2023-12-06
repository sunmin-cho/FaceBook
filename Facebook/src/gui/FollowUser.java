package gui;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class FollowUser {

    public FollowUser(String myID, String userID) {
        try {
            Follow(myID, userID);
        } catch (FollowException e) {
            // �ȷο� �� ������ �߻����� �� ���� â�� ���ϴ�.
            showErrorDialog("Follow Error", e.getMessage());
        }
    }

    private void Follow(String myID, String userID) throws FollowException {
        // Check if myID and userID are the same
        if (myID.equals(userID)) {
            // �ڱ� �ڽ��� �ȷο��� �� ���� ��� ���ܸ� �����ϴ�.
            throw new FollowException("�ڱ� �ڽ��� �ȷο� �� �� �����ϴ�.");
        }

        // JDBC ���� ����
        if (isNotFollowing(myID, userID)) {
        	String jdbcUrl = "jdbc:mysql://localhost/facebook";
            String username = "root";
            String password = "tjsals2620";

            try {
                // JDBC ����̹� �ε�
                Class.forName("com.mysql.cj.jdbc.Driver");

                // �����ͺ��̽��� ����
                Connection connection = DriverManager.getConnection(jdbcUrl, username, password);

                // SQL ���� ����
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
                
                JOptionPane.showMessageDialog(null, "�ȷο� ����", "�˸�", JOptionPane.INFORMATION_MESSAGE);
                System.out.println("Follow successful");

            } catch (ClassNotFoundException | SQLException e) {
                // Follow �� ���ܰ� �߻����� �� ���ܸ� �����ϴ�.
                throw new FollowException("�ȷο� �� ������ �߻��߽��ϴ�.");
            }
        }
        else {
        	throw new FollowException("�̹� �ȷο��ϰ� �ֽ��ϴ�.");
        }
    }

    private Boolean isNotFollowing(String myID, String userID) {
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
            String query = "SELECT * FROM following WHERE user_ID = ? AND following_ID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, myID);
            preparedStatement.setString(2, userID);
            ResultSet resultSet = preparedStatement.executeQuery();

            // Check if resultSet has any rows
            if (resultSet.next()) {
                System.out.println("�ȷο��ϰ��ֽ��ϴ�");
                return false;
            } else {
                System.out.println("�ȷο��ϰ������ʽ��ϴ�");
                return true;
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        // ������ �߻��� ��� false ��ȯ
        return false;
    }

    private void showErrorDialog(String title, String message) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FollowUser("11111", "22222"));
    }
}

// Follow �� ���ܸ� ��Ÿ���� ����� ���� ���� Ŭ����
class FollowException extends Exception {
    public FollowException(String message) {
        super(message);
    }
}