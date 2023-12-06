package gui.manageUser;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PasswordChange {
    private JFrame frame;
    private JButton changePasswordButton;
    private Connection connection;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                PasswordChange window = new PasswordChange();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public PasswordChange() {
        initialize();
        connectToDatabase();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 300, 200);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.getContentPane().add(panel, "Center");
        placeComponents(panel);

        changePasswordButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleChangePasswordButtonClick();
            }
        });
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        changePasswordButton = new JButton("Change Password");
        changePasswordButton.setBounds(10, 80, 150, 25);
        panel.add(changePasswordButton);
    }

    private void connectToDatabase() {
        // �����ͺ��̽� ���� ���� ����
    	String jdbcUrl = "jdbc:mysql://localhost/facebook";
        String user = "root";
        String password = "tjsals2620";

        try {
            // JDBC ����̹� �ε�
            Class.forName("com.mysql.cj.jdbc.Driver");
            // �����ͺ��̽� ����
            connection = DriverManager.getConnection(jdbcUrl, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void handleChangePasswordButtonClick() {
        String userId = JOptionPane.showInputDialog(frame, "Enter user ID:");
        if (userId != null) {
            String currentPassword = JOptionPane.showInputDialog(frame, "Enter current password:");
            if (currentPassword != null && checkPassword(userId, currentPassword)) {
                String newPassword = JOptionPane.showInputDialog(frame, "Enter new password:");
                if (newPassword != null && !newPassword.isEmpty()) {
                    updatePasswordInDatabase(userId, newPassword);
                    JOptionPane.showMessageDialog(frame, "Password changed successfully!");
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid new password.");
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid current password.");
            }
        }
    }
    private boolean checkPassword(String userId, String inputPassword) {
        String sql = "SELECT password FROM user WHERE user_ID = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, userId);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String storedPassword = resultSet.getString("password");
                return storedPassword.equals(inputPassword);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    private void updatePasswordInDatabase(String user_ID, String newPassword) {
        try {
            // SQL ���� �ۼ�
            String sql = "UPDATE user SET password = ? WHERE user_ID = ?";
            // PreparedStatement ����
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                // �Ķ���� ����
                preparedStatement.setString(1, newPassword);
                preparedStatement.setString(2, user_ID);
                // ���� ����
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

	public void showWindow() {
		frame.setVisible(true);
	}
}