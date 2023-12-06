package gui.left;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import gui.UserPage;

public class SmallProfilePanel extends JPanel {
    
    private String userId;
    private String userName;
    private ImageIcon profileImageIcon;

    // �� ���� �Ű������� ���� ������
    public SmallProfilePanel(String userId, String userName) {
        this(userId, userName, "/Facebook/src/profile.jpeg"); // �⺻ �̹��� ��� ���
    }

    // �� ���� �Ű������� ���� ������
    public SmallProfilePanel(String userId, String userName, String imagePath) {
        this.userId = userId;
        this.userName = userName;
        this.profileImageIcon = createResizedImageIcon(imagePath, 50, 50); // �̹����� 50x50���� ũ�� ����

        // �г��� ����� ������� ����
        setBackground(Color.white);

        // ���̾ƿ� ����
        setLayout(new BorderLayout(10, 10)); 

        // ����� �̹����� �̸��� ������ ��� �г� ����
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        topPanel.setBackground(Color.WHITE); // ��� �г��� ����� ������� ����
        JLabel profileLabel = new JLabel(this.profileImageIcon);
        JLabel nameLabel = new JLabel(this.userName);
        topPanel.add(profileLabel);
        topPanel.add(nameLabel);

        // "�� ������ ����" ��ư ���� �� �߰�
        JButton viewProfileButton = new JButton("�� ������ ����");
        viewProfileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // ����� �������� ǥ���ϴ� �׼� ����
                SwingUtilities.invokeLater(() -> new UserPage(userId,userId)); // UserPage�� ���ǵ� Ŭ������� ����
            }
        });

        // �гο� ������Ʈ �߰�
        add(topPanel, BorderLayout.NORTH);
        add(viewProfileButton, BorderLayout.SOUTH);
    }

    // ũ�� ������ �̹��� ������ ���� �޼ҵ�
    protected ImageIcon createResizedImageIcon(String path, int width, int height) {
        ImageIcon originalIcon = createImageIcon(path);
        if (originalIcon != null) {
            Image originalImage = originalIcon.getImage();
            Image resizedImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            return new ImageIcon(resizedImage);
        } else {
            return null;
        }
    }

    // ��ο��� �̹��� �������� �����ϴ� �޼ҵ�
    protected ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("�̹����� ã�� �� ����: " + path);
            return null;
        }
    }
}
