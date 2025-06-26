package com.jimboyz.ui;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class ConnectionComponents extends JPanel {

    private JTextField txtConnectionHost;
    private JTextField txtConnectionPort;
    private JTextField txtDatabaseName;
    private JTextField txtUsername;
    private JPasswordField txtPassword;


    protected ConnectionComponents() {
        init();
    }

    private void init() {

        this.setLayout(new MigLayout("hidemode 3", "[fill][fill]", "[][][][][][][][]"));

        txtConnectionHost = new JTextField("127.0.0.1");
        txtConnectionHost.setForeground(Color.BLACK);
        txtConnectionHost.setFont(new Font("Dialog", Font.PLAIN, 13));
        txtConnectionHost.setColumns(20);

        txtConnectionPort = new JTextField("3306");
        txtConnectionPort.setForeground(Color.BLACK);
        txtConnectionPort.setFont(new Font("Dialog", Font.PLAIN, 13));
        txtConnectionPort.setColumns(20);

        txtDatabaseName = new JTextField();
        txtDatabaseName.setForeground(Color.BLACK);
        txtDatabaseName.setFont(new Font("Dialog", Font.PLAIN, 13));
        txtDatabaseName.setColumns(20);

        txtUsername = new JTextField("root");
        txtUsername.setForeground(Color.BLACK);
        txtUsername.setFont(new Font("Dialog", Font.PLAIN, 13));
        txtUsername.setColumns(20);

        txtPassword = new JPasswordField();
        txtPassword.setForeground(Color.BLACK);
        txtPassword.setFont(new Font("Dialog", Font.PLAIN, 13));
        txtPassword.setColumns(20);

        JLabel lblHost = new JLabel("Host:");
        lblHost.setForeground(Color.BLACK);
        lblHost.setFont(new Font("Dialog", Font.PLAIN, 13));

        JLabel lblPort = new JLabel("Port:");
        lblPort.setForeground(Color.BLACK);
        lblPort.setFont(new Font("Dialog", Font.PLAIN, 13));

        JLabel lblDbName = new JLabel("Database Name:");
        lblDbName.setForeground(Color.BLACK);
        lblDbName.setFont(new Font("Dialog", Font.PLAIN, 13));

        JLabel lblUsername = new JLabel("Database Username:");
        lblUsername.setForeground(Color.BLACK);
        lblUsername.setFont(new Font("Dialog", Font.PLAIN, 13));

        JLabel lblPass = new JLabel("Database Password:");
        lblPass.setForeground(Color.BLACK);
        lblPass.setFont(new Font("Dialog", Font.PLAIN, 13));

        this.add(lblHost, "cell 0 0, gapy 30, wmax 150");
        this.add(txtConnectionHost, "cell 1 0, wmax 300, pushx, growx");

        this.add(lblPort, "cell 0 1, gapy 15, wmax 150");
        this.add(txtConnectionPort, "cell 1 1, wmax 300, pushx, growx");

        this.add(lblDbName, "cell 0 2, gapy 15, wmax 250");
        this.add(txtDatabaseName, "cell 1 2, wmax 300, pushx, growx");

        this.add(lblUsername, "cell 0 3, gapy 15, wmax 250");
        this.add(txtUsername, "cell 1 3, wmax 300, pushx, growx");

        this.add(lblPass, "cell 0 4, gapy 15, wmax 250");
        this.add(txtPassword, "cell 1 4, wmax 300, pushx, growx");

//        return panel;
    }

    public JTextField getTxtConnectionHost() {
        return txtConnectionHost;
    }

    public JTextField getTxtConnectionPort() {
        return txtConnectionPort;
    }

    public JTextField getTxtDatabaseName() {
        return txtDatabaseName;
    }

    public JTextField getTxtUsername() {
        return txtUsername;
    }

    public JPasswordField getTxtPassword() {
        return txtPassword;
    }

    public String getHost() {
        return txtConnectionHost.getText();
    }

    public String getPort() {
        return txtConnectionPort.getText();
    }

    public String getDbName() {
        return txtDatabaseName.getText();
    }

    public String getUsername() {
        return txtUsername.getText();
    }

    public String getPassword() {
        return new String(txtPassword.getPassword());
    }

    public void clearFields() {
        txtConnectionHost.setText("");
        txtConnectionPort.setText("");
        txtDatabaseName.setText("");
        txtUsername.setText("");
        txtPassword.setText("");
    }
}
