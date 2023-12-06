package gui.manageUser;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Logout extends JFrame {
    public Logout() {
        JPanel panel = new JPanel();

        JButton logoutButton = new JButton("로그아웃 하시겠습니까?");
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleLogin();
            }
        });

        panel.add(logoutButton);
        add(panel);

        setSize(200, 150);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void handleLogin() {
        dispose();

        // Login 클래스에서 setVisible(true)를 호출하여 로그인 페이지를 표시
        SwingUtilities.invokeLater(() -> {
            Login login = new Login();
            login.setVisible(true);
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Logout());
    }
}
