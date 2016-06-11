package com.zoo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * Created by nekobard on 11.06.16.
 */
public class SQLiteZoo {
    private Connection connection;
    private Statement statement;

    SQLiteZoo(){
        this.connection = null;
        this.statement = null;
    }

    public void start(){
        try {
            Class.forName("org.sqlite.JDBC");
            this.connection = DriverManager.getConnection("jdbc:sqlite:zoo.db");
            System.out.println("Opened database successfully");

            this.statement = this.connection.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS ENCLOSURES " +
                    "(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " NAME TEXT NOT NULL)";

            this.statement.executeUpdate(sql);
            this.statement.close();
            this.connection.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }

    public void saveEnclosure(String name){
        try {
            Class.forName("org.sqlite.JDBC");
            this.connection = DriverManager.getConnection("jdbc:sqlite:zoo.db");
            this.connection.setAutoCommit(false);
            System.out.println("Opened database successfully");

            this.statement = this.connection.createStatement();
            String sql = "INSERT INTO ENCLOSURES (NAME) " +
                    "VALUES ("+ name +");";
            this.statement.executeUpdate(sql);

            this.statement.close();
            this.connection.commit();
            this.connection.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Records created successfully");
    }

}
