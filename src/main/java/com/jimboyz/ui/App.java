package com.jimboyz.ui;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class App extends JDialog {

    private JTextField txtServerHost;
    private JTextField txtServerPort;
    private JTextField txtUrl;

    private JButton btnConnect;
    private JButton btnClear;
    private JButton btnCancel;
    private JButton btnTest;

    private JLabel editUrl;
    private JTabbedPane tabbedPane;

    private ConnectionComponents connectionComponents;
    private ConnectionComponents createDatabaseComponents;

    private int selectedIndex;

    public App() {
        this(null, "Connection Manager", true);
    }

    public App(Frame owner, String title, boolean modal) {
        super(owner, title, modal);
        init();
    }

    private void init() {
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.getContentPane().setLayout(new BorderLayout());
        this.setSize(new Dimension(637, 517));
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        this.add(design(), BorderLayout.WEST);
        this.add(gui(), BorderLayout.CENTER);
    }

    private JPanel design() {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(217, this.getHeight()));

        return panel;
    }

    private JPanel gui() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JPanel panel01 = new JPanel();
        panel01.setBackground(new Color(238, 238, 238));
        panel01.setPreferredSize(new Dimension(this.getWidth(), 50));
        panel01.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panel01.add(new JLabel("<html><h1 font face=\"Times New Roman\" color=black>SERVER CONNECTION</h1></html>"));
        panel.add(panel01, BorderLayout.NORTH);

        JLabel lblConn = new JLabel("Connection");
        lblConn.setFont(new Font("Dialog", Font.PLAIN, 13));
        lblConn.setForeground(Color.BLACK);
        lblConn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JLabel lblCreateDb = new JLabel("Create Database");
        lblCreateDb.setFont(new Font("Dialog", Font.PLAIN, 13));
        lblCreateDb.setForeground(Color.BLACK);
        lblCreateDb.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JLabel lblHttpConn = new JLabel("Http Connection");
        lblHttpConn.setFont(new Font("Dialog", Font.PLAIN, 13));
        lblHttpConn.setForeground(Color.BLACK);
        lblHttpConn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        tabbedPane = new JTabbedPane();
        tabbedPane.setBackground(this.getBackground());
        tabbedPane.setFocusable(false);
        tabbedPane.setTabPlacement(JTabbedPane.TOP);
        tabbedPane.addTab("Connection", null, connectionTab(), "Connect");
        tabbedPane.setTabComponentAt(0, lblConn);
        tabbedPane.addTab("Http Connection", null, httpConnectionTab(), "Connect through http");
        tabbedPane.setTabComponentAt(tabbedPane.getTabCount() - 1, lblHttpConn);
        tabbedPane.addTab("Create Database", null, createDbTab(), "Create Database");
        tabbedPane.setTabComponentAt(tabbedPane.getTabCount() - 1, lblCreateDb);
        panel.add(tabbedPane, BorderLayout.CENTER);

        panel.add(buttonComponentPanel(), BorderLayout.SOUTH);

        return panel;
    }

    private JPanel buttonComponentPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

        btnConnect = new JButton("Connect");
        btnConnect.setFont(new Font("Dialog", Font.PLAIN, 13));
        btnConnect.setName("Connect");
        btnConnect.setMnemonic('c');
        btnConnect.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnConnect.setActionCommand("connect");

        btnClear = new JButton("Clear");
        btnClear.setFont(new Font("Dialog", Font.PLAIN, 13));
        btnClear.setName("Clear");
        btnClear.setActionCommand("clear");
        btnClear.setMnemonic('l');
        btnClear.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnCancel = new JButton("Cancel");
        btnCancel.setFont(new Font("Dialog", Font.PLAIN, 13));
        btnCancel.setName("Cancel");
        btnCancel.setActionCommand("cancel");
        btnCancel.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnTest = new JButton("Test Connection");
        btnTest.setFont(new Font("Dialog", Font.PLAIN, 13));
        btnTest.setName("Test");
        btnTest.setActionCommand("test");
        btnTest.setMnemonic('t');
        btnTest.setCursor(new Cursor(Cursor.HAND_CURSOR));

        panel.add(btnConnect);
        panel.add(btnClear);
        panel.add(btnCancel);
        panel.add(btnTest);

        return panel;
    }

    private JPanel connectionTab() {
        JPanel panel = new JPanel(new BorderLayout());
        connectionComponents = new ConnectionComponents();
        panel.add(connectionComponents, BorderLayout.CENTER);

        return panel;
    }

    private JPanel httpConnectionTab() {
        JPanel panel = new JPanel(new MigLayout("hidemode 3", "[fill][fill]", "[][][][][][][][][]"));

        JLabel lblHost = new JLabel("Server Host:");
        lblHost.setForeground(Color.BLACK);
        lblHost.setFont(new Font("Dialog", Font.PLAIN, 13));

        JLabel lblPort = new JLabel("Server Port:");
        lblPort.setForeground(Color.BLACK);
        lblPort.setFont(new Font("Dialog", Font.PLAIN, 13));

        JLabel lblUrl = new JLabel("URL:");
        lblUrl.setForeground(Color.BLACK);
        lblUrl.setFont(new Font("Dialog", Font.PLAIN, 13));

        ImageIcon img = new ImageIcon(Toolkit.getDefaultToolkit().getImage(App.class.getResource("/com/jimboyz/images/edit.png")));
        Image image = img.getImage();
        Image image1 = image.getScaledInstance(12, 12, Image.SCALE_SMOOTH);
        ImageIcon img1 = new ImageIcon(image1);

        editUrl = new JLabel(img1);
        editUrl.setCursor(new Cursor(Cursor.HAND_CURSOR));
        editUrl.setHorizontalAlignment(SwingConstants.CENTER);
        editUrl.setText(" ");
        editUrl.setToolTipText("Edit");

        txtServerHost = new JTextField("192.168.0.1");
        txtServerHost.setForeground(Color.BLACK);
        txtServerHost.setFont(new Font("Dialog", Font.PLAIN, 13));
        txtServerHost.setColumns(20);

        txtServerPort = new JTextField("777");
        txtServerPort.setForeground(Color.BLACK);
        txtServerPort.setFont(new Font("Dialog", Font.PLAIN, 13));
        txtServerPort.setColumns(20);

        txtUrl = new JTextField("http://192.168.0.1:777/client.conf");
        txtUrl.setForeground(Color.BLACK);
        txtUrl.setFont(new Font("Dialog", Font.PLAIN, 13));
        txtUrl.setEditable(false);
        txtUrl.setColumns(20);

        panel.add(lblHost, "cell 0 0, gapy 30, wmax 150");
        panel.add(txtServerHost, "cell 1 0, wmax 300, pushx, growx");

        panel.add(lblPort, "cell 0 1, gapy 17, wmax 150");
        panel.add(txtServerPort, "cell 1 1, wmax 300, pushx, growx");

        panel.add(lblUrl, "cell 0 2, gapy 17, wmax 250");
        panel.add(txtUrl, "cell 1 2, wmax 300, pushx, growx");
        panel.add(editUrl, "cell 2 2, align center");

        handleKey();

        return panel;
    }

    private void handleKey() {

        txtServerHost.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                txtUrl.setText("http://" + txtServerHost.getText() + ":" + txtServerPort.getText() + "/" + "client.conf");
            }
        });

        txtServerPort.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                txtUrl.setText("http://" + txtServerHost.getText() + ":" + txtServerPort.getText() + "/" + "client.conf");
            }
        });

        editUrl.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(!txtUrl.isEditable()) {
                    txtUrl.setEditable(true);
                    editUrl.setToolTipText("Disable");
                } else {
                    txtUrl.setEditable(false);
                    editUrl.setToolTipText("Edit");
                }
            }
        });
    }

    private JPanel createDbTab() {
        JPanel panel = new JPanel(new BorderLayout());
        createDatabaseComponents = new ConnectionComponents();
        panel.add(createDatabaseComponents, BorderLayout.CENTER);
        clearCreateDbField();

        return panel;
    }

    public void clearField() {

        if(tabbedPane.getSelectedIndex() == 0) {
            clearConnectionField();
        } else if(tabbedPane.getSelectedIndex() == 1) {
            clearHttpConnectionTab();
        } else if(tabbedPane.getSelectedIndex() == 2) {
            clearCreateDbField();
        }
    }

    public void setDatabaseConnectionListener(ActionListener listener) {
        this.btnConnect.addActionListener(listener);
    }

    public void setCreateDatabaseListener(ActionListener listener) {
        this.btnConnect.addActionListener(listener);
    }

    public void clearFieldListener(ActionListener listener) {
        this.btnClear.addActionListener(listener);
    }

    public void cancel(ActionListener listener) {
        this.btnCancel.addActionListener(listener);
    }

    public void testConnection(ActionListener listener) {
        this.btnTest.addActionListener(listener);
    }

    public JButton getBtnConnect() {
        return btnConnect;
    }

    public JButton getBtnClear() {
        return btnClear;
    }

    public JButton getBtnCancel() {
        return btnCancel;
    }

    public JButton getBtnTest() {
        return btnTest;
    }

    public JTextField getTxtServerHost() {
        return txtServerHost;
    }

    public JTextField getTxtServerPort() {
        return txtServerPort;
    }

    public JTextField getTxtUrl() {
        return txtUrl;
    }

    public String getServerHost() {
        return txtServerHost.getText();
    }

    public String getServerPort() {
        return txtServerPort.getText();
    }

    public String getUrl() {
        return txtUrl.getText();
    }

    public JLabel getEditUrl() {
        return editUrl;
    }

    public JTabbedPane getTabbedPane() {
        return tabbedPane;
    }

    public void tab() {
        tabbedPane.addChangeListener(e -> {
            btnConf();
        });
    }

    private void btnConf() {
        selectedIndex = tabbedPane.getSelectedIndex();

        if(selectedIndex == 0) {
            btnConnect.setText("Connect");
            btnTest.setEnabled(true);
            btnTest.setActionCommand("test");
            btnConnect.setActionCommand("connect");
        } else if(selectedIndex == 1) {
            btnConnect.setText("Connect");
            btnTest.setEnabled(true);
            btnTest.setActionCommand("httptest");
            btnConnect.setActionCommand("httpconnect");
        } else if(selectedIndex == 2) {
            btnConnect.setText("Create");
            btnConnect.setActionCommand("create");
            btnTest.setEnabled(false);
        }
    }

    public void clearHttpConnectionTab() {
        txtServerHost.setText("");
        txtServerPort.setText("");
        txtUrl.setText("http://");
    }

    public void clearCreateDbField() {
        if(createDatabaseComponents != null) {
            createDatabaseComponents.clearFields();
        }
    }

    public void clearConnectionField() {
        if(connectionComponents != null) {
            connectionComponents.clearFields();
        }
    }

    public ConnectionComponents getConnectionComponents() {
        return connectionComponents;
    }

    public ConnectionComponents getCreateDatabaseComponents() {
        return createDatabaseComponents;
    }

    public int getSelectedIndex() {
        return selectedIndex;
    }

}