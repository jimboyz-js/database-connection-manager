package com.jimboyz;

import com.jimboyz.controller.AppController;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        setLaf();
        new AppController();
    }

    public static void setLaf() {

        try {
            for(UIManager.LookAndFeelInfo lafInfo : UIManager.getInstalledLookAndFeels()) {
                if("Windows".equals(lafInfo.getName())) {

                    UIManager.setLookAndFeel(lafInfo.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                 | UnsupportedLookAndFeelException e) {

            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            throw new RuntimeException(e);
        }
    }
}