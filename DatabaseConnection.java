package com.example.sample2;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    static Connection connect = null;

    public static Connection connectDb() {

        /*try{

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connect =
                    DriverManager.getConnection("jdbc:mysql://localhost:3308/ams2.0", "root", "Saibabakijai6@");
            System.out.println("db connected");



        }catch(Exception e){e.printStackTrace();
            System.out.println("db connect failure");}
        return connect;

    }

         */
        String databaseName = "ams2.0";
        String databaseUser = "root";
        String databasePassword = "Saibabakijai6@";
        String url = "jdbc:mysql://localhost:3308/" + databaseName;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connect = DriverManager.getConnection(url, databaseUser, databasePassword);
        } catch (Exception e) {
            e.getStackTrace();
            e.getCause();
        }
        return connect;
    }
}


