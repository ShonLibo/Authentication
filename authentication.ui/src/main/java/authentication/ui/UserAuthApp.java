package authentication.ui;

import javax.swing.*;
import java.awt.*;

public class UserAuthApp extends JFrame {
    private JPanel cardPanel;
    private CardLayout cardLayout;

    public UserAuthApp() {
        setTitle("User Authentication");
        setSize(450, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        cardPanel = new JPanel();
        cardLayout = new CardLayout();
        cardPanel.setLayout(cardLayout);

        // Create panels and set their names
        LoginPanel loginPanel = new LoginPanel(this);
        loginPanel.setName("LOGIN"); // Set name for LoginPanel

        RegisterPanel registerPanel = new RegisterPanel(this);
        registerPanel.setName("REGISTER"); // Set name for RegisterPanel

        DashboardPanel dashboardPanel = new DashboardPanel(this);
        dashboardPanel.setName("DASHBOARD"); // Set name for DashboardPanel

        // Add panels to CardLayout
        cardPanel.add(loginPanel, "LOGIN");
        cardPanel.add(registerPanel, "REGISTER");
        cardPanel.add(dashboardPanel, "DASHBOARD");

        add(cardPanel);
        cardLayout.show(cardPanel, "LOGIN");
    }

    public void showPanel(String panelName) {
        if (panelName.equals("LOGIN")) {
            // Clear login fields when switching to Login Panel
            LoginPanel loginPanel = (LoginPanel) getPanel("LOGIN");
            if (loginPanel != null) {
                loginPanel.clearFields();
            }
        }
        cardLayout.show(cardPanel, panelName);
    }

    public JPanel getPanel(String panelName) {
        for (Component comp : cardPanel.getComponents()) {
            if (comp instanceof JPanel && comp.getName() != null && comp.getName().equals(panelName)) {
                return (JPanel) comp;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new UserAuthApp().setVisible(true));
    }
}

