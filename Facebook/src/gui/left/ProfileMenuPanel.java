package gui.left;

import javax.swing.*;

import gui.manageUser.PasswordChange;

import java.awt.*;
import java.awt.event.ActionListener;

public class ProfileMenuPanel extends JPanel {

    public ProfileMenuPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.WHITE); // ������ ������� ����

        // ������ ���� ��Ʈ
        Font separatorFont = new Font("Arial", Font.BOLD, 12);

        // "Main"�� ���� ������ �߰�
        addSeparator("Main", separatorFont);

        // �ֿ� ��ư �߰�
        addMenuButton("���� �ǵ�");
        addMenuButton("�޽���");
        addMenuButton("�̺�Ʈ");
        addMenuButton("����");
        addMenuButton("ģ����");

        // "More"�� ���� �� �ٸ� ������ �߰�
        addSeparator("����", separatorFont);

        // �߰� ��ư �߰�
        addMenuButtonWithAction("��й�ȣ����", e -> launchPasswordChange());

        addMenuButton("����");
        addMenuButton("������ ����");
        addMenuButton("����������ȣ");
    }

    private void addMenuButton(String buttonText) {
        JButton button = new JButton(buttonText);
        button.setAlignmentX(Component.LEFT_ALIGNMENT);
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setMargin(new Insets(0, 0, 0, 0)); // ���� ���� �߰�
        add(button);
    }
    
    // Method to add a button with a specific action listener
    private void addMenuButtonWithAction(String buttonText, ActionListener listener) {
        JButton button = new JButton(buttonText);
        configureButton(button);
        button.addActionListener(listener);
        add(button);
    }
    private void configureButton(JButton button) {
        button.setAlignmentX(Component.LEFT_ALIGNMENT);
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setMargin(new Insets(0, 0, 0, 0));
    }


    private void addSeparator(String title, Font font) {
        JLabel separatorLabel = new JLabel(title);
        separatorLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        separatorLabel.setFont(font);
        separatorLabel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0)); // ���� ���� �߰�
        add(separatorLabel);
    }
    
    // Placeholder method to simulate launching the PasswordChange functionality
    private void launchPasswordChange() {
        // Instantiate PasswordChange or call the necessary methods here
        // For example:
         PasswordChange passwordChange = new PasswordChange();
         passwordChange.showWindow();
    }
}
