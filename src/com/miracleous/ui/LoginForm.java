package com.miracleous.ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginForm {
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton loginButton;
    private JPanel rootPanel;

    public LoginForm() {
        loginButton.addActionListener(e -> {
            String username = txtUsername.getText();
            String password = String.valueOf(txtPassword.getPassword());
            if (username.equals("miracleous") && password.equals("12345")) {
                JOptionPane.showMessageDialog(rootPanel, "Welcome " + username + "!", "Info", JOptionPane.INFORMATION_MESSAGE);
                MainController mainController = new MainController();
                mainController.createUI();
                SwingUtilities.getWindowAncestor(rootPanel).dispose();
            } else {
                JOptionPane.showMessageDialog(rootPanel, "Wrong Username or Password", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                 UnsupportedLookAndFeelException e) {
            throw new RuntimeException(e);
        }
        JFrame frame = new JFrame("Login Form");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new LoginForm().rootPanel);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

}
