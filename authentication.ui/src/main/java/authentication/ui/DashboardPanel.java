package authentication.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DashboardPanel extends JPanel {
    private UserAuthApp app;
    private JLabel nameLabel, telLabel, emailLabel;

    public DashboardPanel(UserAuthApp app) {
        this.app = app;
        setBackground(new Color(2, 2, 46));
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Title
        JLabel titleLabel = new JLabel("Dashboard", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(titleLabel, gbc);

        // Name
        nameLabel = new JLabel("Name: ");
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        nameLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        add(nameLabel, gbc);

        // Tel No
        telLabel = new JLabel("Tel No: ");
        telLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        telLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(telLabel, gbc);

        // Email
        emailLabel = new JLabel("Email: ");
        emailLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        emailLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(emailLabel, gbc);

        // Back Button
        JButton backButton = new JButton("Back To Sign In");
        backButton.setFont(new Font("Arial", Font.BOLD, 14));
        backButton.setBackground(new Color(40, 167, 69));
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                app.showPanel("LOGIN");
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        add(backButton, gbc);
    }

    // Method to update user information on the dashboard
    public void setUserInfo(String name, String tel, String email) {
        nameLabel.setText("Name: " + name);
        telLabel.setText("Tel No: " + tel);
        emailLabel.setText("Email: " + email);
    }
}

