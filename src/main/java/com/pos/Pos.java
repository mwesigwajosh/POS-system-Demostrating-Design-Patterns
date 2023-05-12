package com.pos;
import javax.swing.JFrame;

public class Pos extends JFrame{

    public static void main(String[] args) {
        LoginForm loginForm = new LoginForm();
        loginForm.setLocationRelativeTo(null); // Center the frame
        loginForm.setVisible(true);
    }
}
