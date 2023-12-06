package gui;

import javax.swing.*;

import gui.manageUser.Logout;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPageBanner extends JFrame {

    public MainPageBanner() {
        setTitle("�Ҽ� ��Ʈ��ũ Ȩ������");
        setSize(1000, 800); // â ũ�⸦ 1000 x 800���� ����
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout()); // ���� �����ӿ� BorderLayout ���

        // BorderLayout�� ����ϴ� ��� ��� �г� ����
        JPanel topBannerPanel = new JPanel();
        topBannerPanel.setBackground(new Color(59, 89, 152)); // ���̽����� �Ķ���
        topBannerPanel.setLayout(new BorderLayout(10, 0)); // ������Ʈ �� ���� ���� ����
        topBannerPanel.setPreferredSize(new Dimension(getWidth(), 50)); // ��� ����� ���� ����

        // Facebook ���� ���ΰ� ���� ������ �߰��ϴ� �г� ����
        JPanel labelPanel = new JPanel(new BorderLayout());
        labelPanel.setBackground(new Color(59, 89, 152)); // ��� ��ʿ� ������ ���� ����

        JLabel facebookLabel = new JLabel("Facebook");
        facebookLabel.setForeground(Color.WHITE); // �ؽ�Ʈ ������ ������� ����
        facebookLabel.setFont(new Font("Arial", Font.BOLD, 24)); // �󺧿� ���� ��Ʈ ����

        // ���� ���� �гο� �߰�
        labelPanel.add(facebookLabel, BorderLayout.WEST);

        // �˻� ��ư�� �ؽ�Ʈ �ʵ带 �����ϴ� �˻� �г�
        JPanel searchPanel = new JPanel(new BorderLayout());
        searchPanel.setBackground(new Color(59, 89, 152)); // ��ʿ� ������ ����
        JTextField searchBar = new JTextField("Search"); // �⺻ �ؽ�Ʈ ����
        searchBar.setPreferredSize(new Dimension(200, 25)); // �˻� ���� �ʺ� ����
        JButton searchButton = new JButton("Search");

        // �˻� ���� �ܰ� ����
        searchBar.setForeground(Color.GRAY); // �⺻ �ؽ�Ʈ ���� ����
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

        // �˻� �гο� �˻� �ٿ� ��ư �߰�
        searchPanel.add(searchBar, BorderLayout.CENTER);
        searchPanel.add(searchButton, BorderLayout.EAST);

        // ������ ��ư �г� ����
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(59, 89, 152)); // ��ʿ� ������ ���� ����
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 0)); // ��ư�� ������ �����ϰ� ���� ���� ����
        buttonPanel.setAlignmentY(Component.CENTER_ALIGNMENT);

        String[] buttonLabels = {"Home", "Profile", "Friends", "Messages", "Logout"};
        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.setForeground(Color.BLACK); // ��ư �ؽ�Ʈ ������ ���������� ����
            button.setBackground(new Color(59, 89, 152)); // ���̽��� �Ķ������� ���� ����
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                	SwingUtilities.invokeLater(() -> new Logout());
                }
            });
            // ��ư �׵θ� ����
            button.setBorder(BorderFactory.createEmptyBorder());

            buttonPanel.add(button);
        }

        // ��� ��� �гο� ������Ʈ �߰�
        topBannerPanel.add(facebookLabel, BorderLayout.WEST); // ���ʿ� Facebook �ؽ�Ʈ
        topBannerPanel.add(searchPanel, BorderLayout.CENTER); // �߾ӿ� �˻� �г�
        topBannerPanel.add(buttonPanel, BorderLayout.EAST); // �����ʿ� ��ư �г�

        // ���� �������� ���� ���� �г� ����
        JTextArea mainContent = new JTextArea("���⿡ ���� �������� ǥ�õ˴ϴ�...");
        mainContent.setEditable(false);

        // ���� ������Ʈ�� ���� �ϴ� �г� ����
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
