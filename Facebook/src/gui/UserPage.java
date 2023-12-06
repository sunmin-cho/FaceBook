package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserPage extends JFrame {

    private String myId;
    private String userId;
 
    public UserPage(String my_ID, String user_ID) {
        this.myId = my_ID;
        this.userId = user_ID;

        JPanel userInfoPanel = createUserInfoPanel(myId, userId);
        List<JPanel> userPostsPanels = createUserPostsPanel(myId, userId);

        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, userInfoPanel, createPostsPanel(userPostsPanels));
        splitPane.setDividerLocation(110);

        setTitle("User Information and Posts");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().add(splitPane);
        setSize(400, 500);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private JPanel createUserInfoPanel(String myID, String userID) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        ResultSet userInfo = getUserInfo(userID);

        try {
            if (userInfo.next()) {
                String userIdFromDB = userInfo.getString("user_ID");
                String userName = userInfo.getString("user_name");
                String birthday = userInfo.getString("birthday");
                String address = userInfo.getString("address");
                String following = userInfo.getString("following");
                String follower = userInfo.getString("follower");

                panel.add(new JLabel("User ID: " + userIdFromDB));
                panel.add(new JLabel("Username: " + userName));
                panel.add(new JLabel("Birthday: " + birthday));
                panel.add(new JLabel("Address: " + address));

                JButton followingButton = new JButton("Following: " + following);
                followingButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        SwingUtilities.invokeLater(() -> new Following(userID));
                    }
                });
                panel.add(followingButton);

                JButton followerButton = new JButton("Follower: " + follower);
                followerButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        SwingUtilities.invokeLater(() -> new Follower(userID));
                    }
                });
                panel.add(followerButton);

                JButton newFollowing = new JButton("Follow");
                newFollowing.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        SwingUtilities.invokeLater(() -> new FollowUser(myID, userID));
                    }
                });
                panel.add(newFollowing);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        JButton refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refreshDataAndGUI(myID, userID);
            }
        });
        panel.add(refreshButton);

        return panel;
    }

    private List<JPanel> createUserPostsPanel(String myId, String ID) {
        List<JPanel> postPanels = new ArrayList<>();
        ResultSet posts = getPosts(ID);

        try {
            while (posts.next()) {
                String postId = posts.getString("post_ID");
                String postDate = posts.getString("post_date");
                String postContent = posts.getString("body_text");
                String likes = posts.getString("likes");
                String comments = posts.getString("comments");

                JPanel postPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
                postPanel.add(new JLabel("Post ID: " + postId));
                postPanel.add(new JLabel(postDate));
                postPanel.add(new JLabel("Content: " + postContent));

                JButton showPostButton = new JButton("show post");
                showPostButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        SwingUtilities.invokeLater(() -> new Post(myId, postId));
                    }
                });
                postPanel.add(showPostButton);

                JButton likesButton = new JButton("Likes: " + likes);
                likesButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Implement the likes action here
                    }
                });
                postPanel.add(likesButton);

                JButton commentsButton = new JButton("Comments: " + comments);
                commentsButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Implement the comments action here
                    }
                });
                postPanel.add(commentsButton);

                postPanels.add(postPanel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return postPanels;
    }

    private JPanel createPostsPanel(List<JPanel> postPanels) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        for (JPanel postPanel : postPanels) {
            panel.add(postPanel);
        }

        return panel;
    }

    private ResultSet getUserInfo(String ID) {
        String jdbcUrl = "jdbc:mysql://localhost/facebook";
        String username = "root";
        String password = "tjsals2620";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
            String query = "SELECT * FROM user WHERE user_ID = '" + ID + "'";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            return resultSet;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    private ResultSet getPosts(String ID) {
        String jdbcUrl = "jdbc:mysql://localhost/facebook";
        String username = "root";
        String password = "tjsals2620";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
            String query = "select * from post left join (select post_ID, count(user_ID)as likes from post_likes group by post_ID)as p_likes using(post_ID) left join (select post_ID, count(comment_ID)as comments from comment group by post_ID)as p_comments using(post_ID) WHERE author_ID = '" + ID + "' order by post_date desc";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            return resultSet;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    private void refreshDataAndGUI(String myId, String userId) {
        JPanel userInfoPanel = createUserInfoPanel(myId, userId);
        List<JPanel> userPostsPanels = createUserPostsPanel(myId, userId);
        JPanel postsPanel = createPostsPanel(userPostsPanels);

        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, userInfoPanel, postsPanel);
        splitPane.setDividerLocation(100);

        getContentPane().removeAll();
        getContentPane().add(splitPane);
        revalidate();
        repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new UserPage("11111", "11111"));
    }
}
