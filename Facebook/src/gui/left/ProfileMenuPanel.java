package gui.left;

import javax.swing.*;

import gui.manageUser.PasswordChange;

import java.awt.*;
import java.awt.event.ActionListener;

public class ProfileMenuPanel extends JPanel {

    public ProfileMenuPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.WHITE); // 배경색을 흰색으로 설정

        // 구분자 제목 폰트
        Font separatorFont = new Font("Arial", Font.BOLD, 12);

        // "Main"에 대한 구분자 추가
        addSeparator("Main", separatorFont);

        // 주요 버튼 추가
        addMenuButton("뉴스 피드");
        addMenuButton("메시지");
        addMenuButton("이벤트");
        addMenuButton("사진");
        addMenuButton("친구들");

        // "More"에 대한 또 다른 구분자 추가
        addSeparator("설정", separatorFont);

        // 추가 버튼 추가
        addMenuButtonWithAction("비밀번호변경", e -> launchPasswordChange());

        addMenuButton("보안");
        addMenuButton("프로필 편집");
        addMenuButton("개인정보보호");
    }

    private void addMenuButton(String buttonText) {
        JButton button = new JButton(buttonText);
        button.setAlignmentX(Component.LEFT_ALIGNMENT);
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setMargin(new Insets(0, 0, 0, 0)); // 왼쪽 여백 추가
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
        separatorLabel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0)); // 왼쪽 여백 추가
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
