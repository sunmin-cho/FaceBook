package gui;

import javax.swing.*;

import gui.manageUser.Logout;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPageBanner extends JFrame {

    public MainPageBanner() {
        setTitle("소셜 네트워크 홈페이지");
        setSize(1000, 800); // 창 크기를 1000 x 800으로 설정
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout()); // 메인 프레임에 BorderLayout 사용

        // BorderLayout을 사용하는 상단 배너 패널 생성
        JPanel topBannerPanel = new JPanel();
        topBannerPanel.setBackground(new Color(59, 89, 152)); // 페이스북의 파란색
        topBannerPanel.setLayout(new BorderLayout(10, 0)); // 컴포넌트 간 수평 간격 설정
        topBannerPanel.setPreferredSize(new Dimension(getWidth(), 50)); // 상단 배너의 높이 설정

        // Facebook 라벨을 감싸고 왼쪽 여백을 추가하는 패널 생성
        JPanel labelPanel = new JPanel(new BorderLayout());
        labelPanel.setBackground(new Color(59, 89, 152)); // 상단 배너와 동일한 배경색 설정

        JLabel facebookLabel = new JLabel("Facebook");
        facebookLabel.setForeground(Color.WHITE); // 텍스트 색상을 흰색으로 설정
        facebookLabel.setFont(new Font("Arial", Font.BOLD, 24)); // 라벨에 대한 폰트 설정

        // 라벨을 래퍼 패널에 추가
        labelPanel.add(facebookLabel, BorderLayout.WEST);

        // 검색 버튼과 텍스트 필드를 포함하는 검색 패널
        JPanel searchPanel = new JPanel(new BorderLayout());
        searchPanel.setBackground(new Color(59, 89, 152)); // 배너와 동일한 배경색
        JTextField searchBar = new JTextField("Search"); // 기본 텍스트 설정
        searchBar.setPreferredSize(new Dimension(200, 25)); // 검색 바의 너비 조절
        JButton searchButton = new JButton("Search");

        // 검색 바의 외관 조절
        searchBar.setForeground(Color.GRAY); // 기본 텍스트 색상 설정
        searchBar.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (searchBar.getText().equals("Search")) {
                    searchBar.setText("");
                    searchBar.setForeground(Color.BLACK);
                }
            }

            public void focusLost(java.awt.event.FocusEvent evt) {
                if (searchBar.getText().isEmpty()) {
                    searchBar.setForeground(Color.GRAY);
                    searchBar.setText("Search");
                }
            }
        });

        // 검색 패널에 검색 바와 버튼 추가
        searchPanel.add(searchBar, BorderLayout.CENTER);
        searchPanel.add(searchButton, BorderLayout.EAST);

        // 오른쪽 버튼 패널 생성
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(59, 89, 152)); // 배너와 동일한 배경색 설정
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 0)); // 버튼을 오른쪽 정렬하고 수평 간격 설정
        buttonPanel.setAlignmentY(Component.CENTER_ALIGNMENT);

        String[] buttonLabels = {"Home", "Profile", "Friends", "Messages", "Logout"};
        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.setForeground(Color.BLACK); // 버튼 텍스트 색상을 검은색으로 설정
            button.setBackground(new Color(59, 89, 152)); // 페이스북 파란색으로 배경색 설정
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                	SwingUtilities.invokeLater(() -> new Logout());
                }
            });
            // 버튼 테두리 제거
            button.setBorder(BorderFactory.createEmptyBorder());

            buttonPanel.add(button);
        }

        // 상단 배너 패널에 컴포넌트 추가
        topBannerPanel.add(facebookLabel, BorderLayout.WEST); // 왼쪽에 Facebook 텍스트
        topBannerPanel.add(searchPanel, BorderLayout.CENTER); // 중앙에 검색 패널
        topBannerPanel.add(buttonPanel, BorderLayout.EAST); // 오른쪽에 버튼 패널

        // 메인 컨텐츠를 위한 센터 패널 생성
        JTextArea mainContent = new JTextArea("여기에 메인 컨텐츠가 표시됩니다...");
        mainContent.setEditable(false);

        // 상태 업데이트를 위한 하단 패널 생성
        JPanel bottomPanel = new JPanel();
        bottomPanel.add(new JLabel("Status:"));
        bottomPanel.add(new JTextField(20));
        bottomPanel.add(new JButton("Post"));

        add(topBannerPanel, BorderLayout.NORTH);
        add(new JScrollPane(mainContent), BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainPageBanner mainPage = new MainPageBanner();
            mainPage.setVisible(true);
        });
    }
}
