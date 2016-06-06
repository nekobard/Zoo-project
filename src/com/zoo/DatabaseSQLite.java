package com.zoo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * Created by nekobard on 05.06.16.
 */
public class DatabaseSQLite {

    private Connection connection;
    private Statement statement;

    public DatabaseSQLite(){
        this.connection = null;
        this.statement = null;
    }

    public void init(){
        try {
            Class.forName("org.sqlite.JDBC");
            this.connection = DriverManager.getConnection("jdbc:sqlite:data.db");
            System.out.println("Opened database successfully");

            this.statement = this.connection.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS ENCLOSURES " +
                    "(ID INT PRIMARY KEY     NOT NULL," +
                    " NAME           TEXT    NOT NULL, " +
                    " AGE            INT     NOT NULL, " +
                    " ADDRESS        CHAR(50), " +
                    " SALARY         REAL)";
            this.statement.executeUpdate(sql);
            this.statement.close();

        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }






}
