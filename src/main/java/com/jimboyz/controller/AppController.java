package com.jimboyz.controller;

import com.jimboyz.model.DatabaseManager;
import com.jimboyz.ui.App;
import javax.swing.*;

public class AppController {

    private final App app;
    private final DatabaseManager databaseManager;

    public AppController() {

        app = new App(null, "Connection", true);
        databaseManager = new DatabaseManager();

        app.tab();

        app.clearFieldListener(e -> app.clearField());

        app.cancel(e -> {
            int cancel = JOptionPane.showConfirmDialog(null, "Are you sure you want to cancel?", "Cancel", JOptionPane.YES_NO_OPTION);

            if (cancel == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });

        app.setDatabaseConnectionListener(e -> {

            if(app.getSelectedIndex() == 0) {

                if(app.getConnectionComponents().getDbName().isEmpty()) {
                    JOptionPane.showMessageDialog(app, "Please enter a database name!");
                    return;
                }

                if(app.getConnectionComponents().getHost().isEmpty()) {
                    JOptionPane.showMessageDialog(app, "Please enter a host!");
                    return;
                }

                if(app.getConnectionComponents().getUsername().isEmpty()) {
                    JOptionPane.showMessageDialog(app, "Please enter a username!");
                    return;
                }

                databaseManager.connect(app.getConnectionComponents().getHost(), app.getConnectionComponents().getPort(),
                        app.getConnectionComponents().getDbName(), app.getConnectionComponents().getUsername(),
                        app.getConnectionComponents().getPassword());
                JOptionPane.showMessageDialog(app.getParent(), "Successfully connected to "+app.getConnectionComponents().getDbName()+".", "Connected", JOptionPane.INFORMATION_MESSAGE);
            }

            if(app.getSelectedIndex() == 1) {

                databaseManager.httpConnect(app.getUrl());
                JOptionPane.showMessageDialog(app.getParent(), "Connected Successfully!", "Connected", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        app.setCreateDatabaseListener(e -> {
          if(app.getSelectedIndex() == 2) {
              databaseManager.create(app.getCreateDatabaseComponents().getHost(), app.getCreateDatabaseComponents().getPort(),
                      app.getCreateDatabaseComponents().getDbName(), app.getCreateDatabaseComponents().getUsername(),
                      app.getCreateDatabaseComponents().getPassword());
          }
        });

        app.testConnection(e-> {

            if(app.getSelectedIndex() == 0) {
                if(app.getConnectionComponents().getDbName().isEmpty()) {
                    JOptionPane.showMessageDialog(app, "Please enter a database name!");
                    return;
                }

                if(app.getConnectionComponents().getHost().isEmpty()) {
                    JOptionPane.showMessageDialog(app, "Please enter a host!");
                    return;
                }

                if(app.getConnectionComponents().getUsername().isEmpty()) {
                    JOptionPane.showMessageDialog(app, "Please enter a username!");
                    return;
                }

                databaseManager.testConnection(app.getConnectionComponents().getHost(), app.getConnectionComponents().getPort(),
                        app.getConnectionComponents().getDbName(), app.getConnectionComponents().getUsername(),
                        app.getConnectionComponents().getPassword());
                JOptionPane.showMessageDialog(app.getParent(), "Successfully connected to "+app.getConnectionComponents().getDbName()+".", "Connected", JOptionPane.INFORMATION_MESSAGE);
            }

            if(app.getSelectedIndex() == 1) {
                databaseManager.httpConnect(app.getUrl());
                JOptionPane.showMessageDialog(app.getParent(), "Connected Successfully!", "Connected", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        app.setVisible(true);
    }
}
