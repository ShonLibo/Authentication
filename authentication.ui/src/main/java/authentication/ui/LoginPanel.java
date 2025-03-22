package authentication.ui;

import authentication.database.UserRepository;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginPanel extends JPanel {
    private UserAuthApp app;
    private JTextField emailField;
    private JPasswordField passwordField;

    public LoginPanel(UserAuthApp app) {
        this.app = app;
        setBackground(new Color(2, 2, 46));
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Title
        JLabel titleLabel = new JLabel("Login", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(titleLabel, gbc);

        // Email
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        emailLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        add(emailLabel, gbc);

        emailField = new JTextField(20);
        emailField.setFont(new Font("Arial", Font.PLAIN, 14));
        emailField.setBackground(new Color(224, 224, 224));
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(emailField, gbc);

        // Password
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        passwordLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(passwordLabel, gbc);

        passwordField = new JPasswordField(20);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 14));
        passwordField.setBackground(new Color(224, 224, 224));
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(passwordField, gbc);

        // Buttons
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        buttonPanel.setBackground(new Color(2, 2, 46));

        JButton signInButton = new JButton("Sign In");
        signInButton.setFont(new Font("Arial", Font.BOLD, 14));
        signInButton.setBackground(new Color(0, 123, 255));
        signInButton.setForeground(Color.WHITE);
        signInButton.setFocusPainted(false);
        signInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText().trim();
                String password = new String(passwordField.getPassword()).trim();

                if (email.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(LoginPanel.this, "Please fill in all fields", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    UserRepository userRepo = new UserRepository();
                    ResultSet rs = userRepo.getUserByEmailAndPassword(email, password);
                    if (rs.next()) {
                        String name = rs.getString("name");
                        String tel = rs.getString("tel");
                        String userEmail = rs.getString("email");

                        // Pass user data to DashboardPanel
                        DashboardPanel dashboardPanel = (DashboardPanel) app.getPanel("DASHBOARD");
                        dashboardPanel.setUserInfo(name, tel, userEmail);

                        // Switch to Dashboard Panel
                        app.showPanel("DASHBOARD");
                    } else {
                        JOptionPane.showMessageDialog(LoginPanel.this, "Invalid email or password", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        JButton signUpButton = new JButton("Sign Up");
        signUpButton.setFont(new Font("Arial", Font.BOLD, 14));
        signUpButton.setBackground(new Color(40, 167, 69));
        signUpButton.setForeground(Color.WHITE);
        signUpButton.setFocusPainted(false);
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Clear login fields before switching to Register Panel
                clearFields();
                app.showPanel("REGISTER");
            }
        });

        buttonPanel.add(signInButton);
        buttonPanel.add(signUpButton);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        add(buttonPanel, gbc);
    }

    // Method to clear login fields
    public void clearFields() {
        emailField.setText("");
        passwordField.setText("");
    }
}

