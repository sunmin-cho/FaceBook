package gui.center;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import gui.Post;

public class FollowingPost extends JPanel {

    private static final String JDBC_URL = "jdbc:mysql://localhost/facebook";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "tjsals2620";
    private JPanel postsPanel;
    private String loggedInUserId;

    public FollowingPost(String loggedInUserId) {
        this.loggedInUserId = loggedInUserId;
        setLayout(new GridBagLayout()); // GridBagLayout 사용

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.NONE; // 크기 변경을 위해 NONE 사용

        JButton refreshButton = new JButton("Refresh");
        styleRefreshButton(refreshButton);
        refreshButton.addActionListener(e -> refreshPosts());
        add(refreshButton, gbc); // GridBagConstraints 적용

        gbc.gridy++;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        postsPanel = new JPanel(new GridLayout(0, 1));
        JScrollPane scrollPane = new JScrollPane(postsPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrollPane, gbc);

        setPreferredSize(new Dimension(900, 600));
        refreshPosts();
    }



    private void styleRefreshButton(JButton button) {
        // Set the size of the button
        button.setPreferredSize(new Dimension(100,20));
        
        // Set the font smaller and cute
        button.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 10));

        // Optionally, you can set an icon for the button
        // Ensure the path is correct and the image file is available
        // button.setIcon(new ImageIcon("path/to/your/icon.png"));

        // Set button's tooltip for better UX
        button.setToolTipText("Click to refresh posts");
    }

    private void refreshPosts() {
        postsPanel.removeAll(); // Remove all existing posts
        fetchAndDisplayPosts(postsPanel, loggedInUserId);
        postsPanel.revalidate();
        postsPanel.repaint();
    }

    private static void fetchAndDisplayPosts(JPanel panel, String userId) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
            String postSql = "SELECT author_ID, post_date, body_text, post_ID FROM post " +
                             "WHERE author_ID = ? OR author_ID IN (SELECT following_ID FROM following WHERE user_ID = ?) " +
                             "ORDER BY post_date DESC"; // Order by post date in descending order

            try (PreparedStatement postStatement = connection.prepareStatement(postSql)) {
                postStatement.setString(1, userId);
                postStatement.setString(2, userId);

                try (ResultSet resultSet = postStatement.executeQuery()) {
                    while (resultSet.next()) {
                        String postid = resultSet.getString("post_ID");
                        String postDate = resultSet.getString("post_date");
                        String authorid = resultSet.getString("author_ID");
                        String content = resultSet.getString("body_text");

                        JButton postButton = createPostButton(userId, postid, postDate, authorid, content);
                        panel.add(postButton);
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private static JButton createPostButton(String myID, String postID, String postDate, String authorId, String content) {
        String buttonText = "Author ID: " + authorId + "<br>" + postDate + "<br>Content: " + content;
        JButton postButton = new JButton("<html>" + buttonText + "</html>");
        postButton.setHorizontalAlignment(SwingConstants.LEFT);
        postButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> new Post(myID, postID));
            }
        });

        return postButton;
    }

    public static void main(String[] args) {
        String loggedInUserId = "77771";
        SwingUtilities.invokeLater(() -> new FollowingPost(loggedInUserId));
    }
}
