package com.jimboyz.model;

import javax.swing.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseManager {

    public void connect(String host, String port, String db_name, String user, String pass) {
        try(FileOutputStream fos = new FileOutputStream(FileConfig.getFileConfig())) {
            Properties properties = new Properties();

            DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + db_name, user, pass);

            properties.setProperty("host", host);
            properties.setProperty("port", port);
            properties.setProperty("db_name", db_name);
            properties.setProperty("user", user);

            properties.store(fos, "jimBoYz Ni ChOy!!!");

        } catch (IOException | SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            throw new RuntimeException(e);
        }
    }

    public void httpConnect(String url) {
        try (InputStream is = new URL(url).openStream();
            FileOutputStream fos = new FileOutputStream(FileConfig.getFileConfig())) {
            Properties properties = new Properties();
            properties.load(is);

            String host = properties.getProperty("host");
            String port = properties.getProperty("port");
            String db_name = properties.getProperty("db_name");
            String user = properties.getProperty("user");
            String pass = properties.getProperty("pass");

            connect(host, port, db_name, user, pass);

            properties = new Properties();
            properties.setProperty("url", url);
            properties.store(fos, "jimBoYz Ni ChOy!!!");
//

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            throw new RuntimeException(e);
        }
    }

    public void testConnection(String host, String port, String db_name, String user, String pass) {
        try {
            DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + db_name, user, pass);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            throw new RuntimeException(e);
        }
    }

    public void create(String host, String port, String db_name, String user, String pass) {
        try {
            String[] cmd = {"mysql","-u",user, "-h", host, "-P", port, "-e", "CREATE DATABASE "+db_name+";"};
            if (!pass.isEmpty()) {
                cmd = new String[]{"mysql", "-u", user, "-h", host, "-P", port, "-p" + pass, "-e", "CREATE DATABASE " + db_name + ";"};
            }
            ProcessBuilder pb = new ProcessBuilder(cmd);
            Process process = pb.start();
            int interval = process.waitFor();
            if(interval == 0) {
                JOptionPane.showOptionDialog(null, "Database successfully created...", "Message", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new String[] {"Ok"}, 1);
            }else {
                JOptionPane.showMessageDialog(null, "Cannot create Database. Process id: "+process.pid(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            throw new RuntimeException(e);
        }
    }
}
