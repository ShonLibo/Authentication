package authentication.ui;

import authentication.database.UserRepository;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class RegisterPanel extends JPanel {
    private UserAuthApp app;
    private JTextField nameField, telField, emailField;
    private JPasswordField passwordField, confirmPasswordField;

    public RegisterPanel(UserAuthApp app) {
        this.app = app;
        setBackground(new Color(2, 2, 46));
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Title
        JLabel titleLabel = new JLabel("Register", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(titleLabel, gbc);

        // Name
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        nameLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        add(nameLabel, gbc);

        nameField = new JTextField(20);
        nameField.setFont(new Font("Arial", Font.PLAIN, 14));
        nameField.setBackground(new Color(224, 224, 224));
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(nameField, gbc);

        // Tel
        JLabel telLabel = new JLabel("Tel:");
        telLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        telLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(telLabel, gbc);

        telField = new JTextField(20);
        telField.setFont(new Font("Arial", Font.PLAIN, 14));
        telField.setBackground(new Color(224, 224, 224));
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(telField, gbc);

        // Email
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        emailLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(emailLabel, gbc);

        emailField = new JTextField(20);
        emailField.setFont(new Font("Arial", Font.PLAIN, 14));
        emailField.setBackground(new Color(224, 224, 224));
        gbc.gridx = 1;
        gbc.gridy = 3;
        add(emailField, gbc);

        // Password
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        passwordLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(passwordLabel, gbc);

        passwordField = new JPasswordField(20);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 14));
        passwordField.setBackground(new Color(224, 224, 224));
        gbc.gridx = 1;
        gbc.gridy = 4;
        add(passwordField, gbc);

        // Confirm Password
        JLabel confirmPasswordLabel = new JLabel("Confirm Password:");
        confirmPasswordLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        confirmPasswordLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 5;
        add(confirmPasswordLabel, gbc);

        confirmPasswordField = new JPasswordField(20);
        confirmPasswordField.setFont(new Font("Arial", Font.PLAIN, 14));
        confirmPasswordField.setBackground(new Color(224, 224, 224));
        gbc.gridx = 1;
        gbc.gridy = 5;
        add(confirmPasswordField, gbc);

        // Buttons
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        buttonPanel.setBackground(new Color(2, 2, 46));

        JButton signUpButton = new JButton("Sign Up");
        signUpButton.setFont(new Font("Arial", Font.BOLD, 14));
        signUpButton.setBackground(new Color(40, 167, 69));
        signUpButton.setForeground(Color.WHITE);
        signUpButton.setFocusPainted(false);
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText().trim();
                String tel = telField.getText().trim();
                String email = emailField.getText().trim();
                String password = new String(passwordField.getPassword()).trim();
                String confirmPassword = new String(confirmPasswordField.getPassword()).trim();

                if (name.isEmpty() || tel.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                    JOptionPane.showMessageDialog(RegisterPanel.this, "Please fill in all fields", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (!password.equals(confirmPassword)) {
                    JOptionPane.showMessageDialog(RegisterPanel.this, "Passwords do not match", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    UserRepository userRepo = new UserRepository();
                    if (userRepo.isEmailRegistered(email)) {
                        JOptionPane.showMessageDialog(RegisterPanel.this, "Email already registered", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    userRepo.registerUser(name, tel, email, password);

                    // Clear registration fields
                    clearFields();

                    // Show success message
                    JOptionPane.showMessageDialog(RegisterPanel.this, "Registration successful", "Success", JOptionPane.INFORMATION_MESSAGE);

                    // Switch to Login Panel
                    app.showPanel("LOGIN");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        JButton backButton = new JButton("Back to Sign In");
        backButton.setFont(new Font("Arial", Font.BOLD, 14));
        backButton.setBackground(new Color(0, 123, 255));
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Clear registration fields
                clearFields();

                // Clear login fields
                LoginPanel loginPanel = (LoginPanel) app.getPanel("LOGIN");
                if (loginPanel != null) {
                    loginPanel.clearFields();
                }

                // Switch to Login Panel
                app.showPanel("LOGIN");
            }
        });

        buttonPanel.add(signUpButton);
        buttonPanel.add(backButton);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        add(buttonPanel, gbc);
    }

    // Method to clear all registration fields
    private void clearFields() {
        nameField.setText("");
        telField.setText("");
        emailField.setText("");
        passwordField.setText("");
        confirmPasswordField.setText("");
    }
}

