package gui.posting;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EditorComment extends JFrame {

    private JTextArea commentTextArea;
    private String loggedInUserID;
    private String postID;
    private List<String> taggedUsers;

    public EditorComment(String loggedInUserID, String postID) {
        this.loggedInUserID = loggedInUserID;
        this.postID = postID;
        this.taggedUsers = new ArrayList<>();

        // 창 속성 설정
        setTitle("댓글 작성");
        setSize(500, 250);
        setLocationRelativeTo(null);

        // 댓글 편집 컴포넌트 생성
        commentTextArea = new JTextArea();
        JButton submitButton = new JButton("업로드");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                savePostToDatabase();
            }
        });

        // 컴포넌트를 담을 패널 생성
        JPanel postPanel = new JPanel();
        postPanel.setLayout(new BorderLayout());
        postPanel.add(new JScrollPane(commentTextArea), BorderLayout.CENTER);
        postPanel.add(submitButton, BorderLayout.SOUTH);

        // EditorComment 설정
        add(postPanel);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 250);
        setVisible(true);
    }

    private void savePostToDatabase() {
        // 현재 텍스트 영역의 내용을 가져옵니다.
        String commentContent = commentTextArea.getText();
        saveCommentToDatabase(loggedInUserID, postID, commentContent);
    }

    public static void saveCommentToDatabase(String loggedInUserID, String postID, String commentContent) {
        // 데이터베이스 연결 및 댓글 저장 로직
        String url = "jdbc:mysql://localhost/facebook";
        String username = "root";
        String password = "tjsals2620";
        String commentID = UUID.randomUUID().toString();
        Timestamp commentDate = new Timestamp(System.currentTimeMillis());

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String sqlComment = "INSERT INTO comment (comment_ID, user_ID, comment_content, comment_date, post_ID) VALUES (?, ?, ?, ?, ?)";

            try (PreparedStatement preparedStatementComment = connection.prepareStatement(sqlComment)) {
                preparedStatementComment.setString(1, commentID);
                preparedStatementComment.setString(2, loggedInUserID);

                // 태그된 사용자 정보 추출
                List<String> taggedUsers = getTaggedUsers(commentContent);

                preparedStatementComment.setString(3, commentContent);
                preparedStatementComment.setTimestamp(4, commentDate);
                preparedStatementComment.setString(5, postID);
                preparedStatementComment.executeUpdate();

                // 태그된 사용자 정보 저장
                saveTaggedUsers(connection, commentID, loggedInUserID, taggedUsers);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            // 오류 처리
        }
    }

    private static List<String> getTaggedUsers(String commentContent) {
        List<String> taggedUsers = new ArrayList<>();
        Pattern pattern = Pattern.compile("@([\\w]+)");
        Matcher matcher = pattern.matcher(commentContent);

        while (matcher.find()) {
            taggedUsers.add(matcher.group(1));
        }
        return taggedUsers;
    }

    private static void saveTaggedUsers(Connection connection, String commentID, String loggedInUserID, List<String> taggedUsers) throws SQLException {
        String sqlTaggedUser = "INSERT INTO comment_tag (comment_ID, user_ID, tagged_ID) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatementTaggedUser = connection.prepareStatement(sqlTaggedUser)) {
            for (String taggedUser : taggedUsers) {
                preparedStatementTaggedUser.setString(1, commentID);
                preparedStatementTaggedUser.setString(2, loggedInUserID);
                preparedStatementTaggedUser.setString(3, taggedUser);
                preparedStatementTaggedUser.executeUpdate();
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new EditorComment("11111", "99996"));
    }
}