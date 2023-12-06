package gui.posting;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentGui extends JFrame {

    private String postID;
    private String loggedInUserID;
    private JTextArea newCommentTextArea;

    public CommentGui(String userID, String ID) {
        this.loggedInUserID = userID;
        this.postID = ID;

        setTitle("댓글 창");
        setSize(1200, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        java.util.List<JPanel> commentPanels = createPostCommentsPanel(userID, postID);
        JPanel commentPanel = createCommentsPanel(commentPanels);
        JScrollPane commentScrollPane = new JScrollPane(commentPanel);
        commentScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        newCommentTextArea = new JTextArea(3, 20);
        newCommentTextArea.setLineWrap(true);
        newCommentTextArea.setWrapStyleWord(true);
        JScrollPane textAreaScrollPane = new JScrollPane(newCommentTextArea);

        JButton submitButton = new JButton("댓글 작성");
        submitButton.addActionListener(e -> submitComment());

        JButton refreshButton = new JButton("새로고침");
        refreshButton.addActionListener(e -> refreshComments());

        JPanel newCommentPanel = new JPanel(new BorderLayout());
        newCommentPanel.add(textAreaScrollPane, BorderLayout.CENTER);
        newCommentPanel.add(submitButton, BorderLayout.EAST);
        newCommentPanel.add(refreshButton, BorderLayout.WEST); // 새로고침 버튼을 왼쪽에 추가

        mainPanel.add(commentScrollPane, BorderLayout.CENTER);
        mainPanel.add(newCommentPanel, BorderLayout.SOUTH);

        getContentPane().add(mainPanel);
        setVisible(true);
    }

    private void submitComment() {
        String commentText = newCommentTextArea.getText();
        if (!commentText.isEmpty()) {
            EditorComment.saveCommentToDatabase(loggedInUserID, postID, commentText);
            newCommentTextArea.setText(""); // Clear the text area
        } else {
            JOptionPane.showMessageDialog(this, "댓글을 입력하세요.");
        }
    }

    private void refreshComments() {
        // 새로고침 버튼을 클릭하면 댓글을 다시 로드하고 화면을 업데이트합니다.
        java.util.List<JPanel> commentPanels = createPostCommentsPanel(loggedInUserID, postID);
        JPanel commentPanel = createCommentsPanel(commentPanels);
        JScrollPane commentScrollPane = new JScrollPane(commentPanel);
        commentScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        getContentPane().removeAll();
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(commentScrollPane, BorderLayout.CENTER);

        JPanel newCommentPanel = new JPanel(new BorderLayout());
        newCommentPanel.add(newCommentTextArea, BorderLayout.CENTER);
        newCommentPanel.add(createSubmitButton(), BorderLayout.EAST);
        newCommentPanel.add(createRefreshButton(), BorderLayout.WEST);

        mainPanel.add(newCommentPanel, BorderLayout.SOUTH);

        getContentPane().add(mainPanel);
        revalidate();
        repaint();
    }

    private java.util.List<JPanel> createPostCommentsPanel(String myID, String ID) {
        java.util.List<JPanel> commentPanels = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/facebook", "root", "tjsals2620")) {
            String sql = "select * from comment left join (select comment_ID, count(user_ID)as likes from comment_likes group by comment_ID)as c_likes using(comment_ID) WHERE post_ID = ? order by comment_date desc;";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, ID);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        String commentID = resultSet.getString("comment_ID");
                        String commentContent = resultSet.getString("comment_content");
                        String userID = resultSet.getString("user_ID");
                        String commentLikes = resultSet.getString("likes");
                        String commentDate = resultSet.getString("comment_date");

                        JPanel commentPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
                        
                        commentPanel.add(new JLabel("User ID: " + userID));
                        commentPanel.add(new JLabel(commentDate));
                        commentPanel.add(new JLabel("Content: " + commentContent));

                        JButton likesButton = new JButton("Likes: " + commentLikes);
                        likesButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                SwingUtilities.invokeLater(() -> new LikeComment(userID, commentID));
                            }
                        });
                        commentPanel.add(likesButton);

                        commentPanels.add(commentPanel);
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return commentPanels;
    }

    private JPanel createCommentsPanel(java.util.List<JPanel> commentPanels) {
        JPanel commentsPanel = new JPanel();
        commentsPanel.setLayout(new BoxLayout(commentsPanel, BoxLayout.Y_AXIS));
        for (JPanel panel : commentPanels) {
            commentsPanel.add(panel);
        }
        return commentsPanel;
    }

    private JButton createSubmitButton() {
        JButton submitButton = new JButton("댓글 작성");
        submitButton.addActionListener(e -> submitComment());
        return submitButton;
    }

    private JButton createRefreshButton() {
        JButton refreshButton = new JButton("새로고침");
        refreshButton.addActionListener(e -> refreshComments());
        return refreshButton;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CommentGui commentGui = new CommentGui("11111", "99995");
            commentGui.setVisible(true);
        });
    }
}
