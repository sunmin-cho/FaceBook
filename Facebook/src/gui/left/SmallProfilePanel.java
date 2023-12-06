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

    // 두 개의 매개변수를 가진 생성자
    public SmallProfilePanel(String userId, String userName) {
        this(userId, userName, "/Facebook/src/profile.jpeg"); // 기본 이미지 경로 사용
    }

    // 세 개의 매개변수를 가진 생성자
    public SmallProfilePanel(String userId, String userName, String imagePath) {
        this.userId = userId;
        this.userName = userName;
        this.profileImageIcon = createResizedImageIcon(imagePath, 50, 50); // 이미지를 50x50으로 크기 조정

        // 패널의 배경을 흰색으로 설정
        setBackground(Color.white);

        // 레이아웃 설정
        setLayout(new BorderLayout(10, 10)); 

        // 사용자 이미지와 이름을 포함한 상단 패널 생성
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        topPanel.setBackground(Color.WHITE); // 상단 패널의 배경을 흰색으로 설정
        JLabel profileLabel = new JLabel(this.profileImageIcon);
        JLabel nameLabel = new JLabel(this.userName);
        topPanel.add(profileLabel);
        topPanel.add(nameLabel);

        // "내 프로필 보기" 버튼 생성 및 추가
        JButton viewProfileButton = new JButton("내 프로필 보기");
        viewProfileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 사용자 페이지를 표시하는 액션 구현
                SwingUtilities.invokeLater(() -> new UserPage(userId,userId)); // UserPage가 정의된 클래스라고 가정
            }
        });

        // 패널에 컴포넌트 추가
        add(topPanel, BorderLayout.NORTH);
        add(viewProfileButton, BorderLayout.SOUTH);
    }

    // 크기 조정된 이미지 아이콘 생성 메소드
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

    // 경로에서 이미지 아이콘을 생성하는 메소드
    protected ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("이미지를 찾을 수 없음: " + path);
            return null;
        }
    }
}
