package org.hospital.Dao;

import org.hospital.view.MenuView;

import java.sql.*;

public class DBConnection {
    private final String URL = "jdbc:postgresql://tai.db.elephantsql.com:5432/hgtxbbkn";
    private final String USER = "hgtxbbkn";
    private final String PASSWORD = "eK-GilTDNg6bu9XtFkf9LyRnq7gip5vh";


    public Connection connect(){
        Connection connection = null;
        try{
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

}
