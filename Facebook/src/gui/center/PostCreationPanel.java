package gui.center;

import javax.swing.*;

import gui.PostEditor;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PostCreationPanel extends JPanel {

    private JTextArea postTextArea; // Declare postTextArea at class level

    public PostCreationPanel(String userID) {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE); // Set background color to white

        // "News Feed" label
        JLabel newsFeedLabel = new JLabel("News Feed");
        newsFeedLabel.setFont(new Font("Arial", Font.BOLD, 16)); // Facebook uses Arial, bold style
        newsFeedLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // Add margin
        add(newsFeedLabel, BorderLayout.NORTH);

        // Create a text area for writing posts
        postTextArea = new JTextArea(); // Initialize postTextArea
        postTextArea.setRows(4);
        postTextArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        JScrollPane textAreaScrollPane = new JScrollPane(postTextArea); // Wrap the text area with a scroll panel
        textAreaScrollPane.setPreferredSize(new Dimension(1000, postTextArea.getPreferredSize().height));
        textAreaScrollPane.setBorder(null); // Remove border to save space

        // Panel to contain text area with left and right margins
        JPanel textAreaContainer = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0)); // Left and right margins 10 pixels
        textAreaContainer.setBackground(Color.WHITE);
        textAreaContainer.add(textAreaScrollPane);

        add(textAreaContainer, BorderLayout.CENTER);

        // Panel for attachments and post buttons
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.setBackground(Color.WHITE); // Set background color to white

        // Panel for attachment icons (add icons as needed)
        JPanel attachmentPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        attachmentPanel.setBackground(Color.WHITE);
        bottomPanel.add(attachmentPanel, BorderLayout.EAST);

        // "Create new post" button
        JButton postButton = new JButton("Create new post");
        postButton.setBackground(new Color(66, 103, 178)); // Blue on Facebook
        postButton.setForeground(Color.black); // Set text color to white
        postButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PostEditor editor = new PostEditor(userID); // Replace "loggedInUserID" with the actual user ID
                editor.savePostToDatabase(getPostText());
            }
        });
        bottomPanel.add(postButton, BorderLayout.WEST);

        // Add bottom panel to main panel
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private String getPostText() {
        return postTextArea.getText();
    }
}
