package com.example.presence;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    public static Connection connectDb(){

        try{

            Class.forName("com.mysql.jdbc.Driver");

            Connection connect =
                    DriverManager.getConnection("jdbc:mysql://localhost/marco?", "root", "advay2003");

            return connect;

        }catch(Exception e){e.printStackTrace();}

        return null;
    }
}


