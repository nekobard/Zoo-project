package com.zoo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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

            this.statement = this.connection.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS ENCLOSURES " +
                    "(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " NAME TEXT NOT NULL)";

            this.statement.executeUpdate(sql);

            sql = "CREATE TABLE IF NOT EXISTS ANIMALS " +
                    "(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " ENCLOSURE_ID INTEGER NOT NULL," +
                    " IS_THIRSTY TEXT NOT NULL," +
                    " IS_HUNGRY TEXT NOT NULL," +
                    " ANIMAL_TYPE TEXT NOT NULL," +
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

            this.statement = this.connection.createStatement();
            String sql = "INSERT INTO ENCLOSURES (NAME) " +
                    "VALUES ('"+ name +"');";
            this.statement.executeUpdate(sql);

            this.statement.close();
            this.connection.commit();
            this.connection.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }

    }

    public void saveAnimal(String animalType, String name, String enclosureName){
        try {
            Class.forName("org.sqlite.JDBC");
            this.connection = DriverManager.getConnection("jdbc:sqlite:zoo.db");
            this.connection.setAutoCommit(false);

            this.statement = this.connection.createStatement();


            int enclosureId = -1;
            ResultSet rs = this.statement.executeQuery( "SELECT * FROM ENCLOSURES WHERE NAME='" + enclosureName + "' ;" );
            while ( rs.next() ) {
                enclosureId = rs.getInt("ID");
            }
            rs.close();

            String sql = "INSERT INTO ANIMALS (NAME, ANIMAL_TYPE, IS_THIRSTY, IS_HUNGRY, ENCLOSURE_ID) " +
                    "VALUES ('"+ name +"','"+ animalType +"', 'yes', 'yes', " + enclosureId + ");";
            this.statement.executeUpdate(sql);

            this.statement.close();
            this.connection.commit();
            this.connection.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }

    public List<Enclosure> loadEnclosures(){
        List<Enclosure> enclosures = new ArrayList<Enclosure>();

        try {
            Class.forName("org.sqlite.JDBC");
            this.connection = DriverManager.getConnection("jdbc:sqlite:zoo.db");
            this.connection.setAutoCommit(false);

            this.statement = this.connection.createStatement();
            ResultSet rs = this.statement.executeQuery( "SELECT * FROM ENCLOSURES;" );
            while ( rs.next() ) {
                String  name = rs.getString("NAME");
                Enclosure enclosure = new Enclosure(name);
                enclosures.add(enclosure);
            }
            rs.close();
            this.statement.close();
            this.connection.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }

        return enclosures;
    }

    private int enclosureId(String name){
        int id = -1;
        try {
            Class.forName("org.sqlite.JDBC");
            this.connection = DriverManager.getConnection("jdbc:sqlite:zoo.db");
            this.connection.setAutoCommit(false);

            this.statement = this.connection.createStatement();
            ResultSet rs = this.statement.executeQuery( "SELECT * FROM ENCLOSURES WHERE NAME='" + name +"';" );
            while ( rs.next() ) {
                id = rs.getInt("ID");

            }
            rs.close();
            this.statement.close();
            this.connection.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }

        return id;
    }

    public List<Animal> loadAnimals(String enclosureName){
        List<Animal> animals = new ArrayList<Animal>();

        int id = this.enclosureId(enclosureName);

        try {
            Class.forName("org.sqlite.JDBC");
            this.connection = DriverManager.getConnection("jdbc:sqlite:zoo.db");
            this.connection.setAutoCommit(false);

            this.statement = this.connection.createStatement();
            ResultSet rs = this.statement.executeQuery( "SELECT * FROM ANIMALS WHERE ENCLOSURE_ID=" + id +";" );
            while ( rs.next() ) {
                String  name = rs.getString("NAME");
                String animalType = rs.getString("ANIMAL_TYPE");
                String thirsty = rs.getString("IS_THIRSTY");
                String hungry = rs.getString("IS_HUNGRY");

                boolean isThirsty = false;
                boolean isHungry = false;
                if(thirsty.equals("yes")){
                    isThirsty = true;
                }
                if(hungry.equals("yes")){
                    isHungry = true;
                }

                if(animalType.equals("krokodyl-nilowy")){

                    NileCrocodile nileCrocodile = new NileCrocodile(name);
                    nileCrocodile.setThirsty(isThirsty);
                    nileCrocodile.setHungry(isHungry);
                    animals.add(nileCrocodile);

                } else if(animalType.equals("krokodyl-amerykanski")){

                    AmericanCrocodile americanCrocodile = new AmericanCrocodile(name);
                    americanCrocodile.setThirsty(isThirsty);
                    americanCrocodile.setHungry(isHungry);
                    animals.add(americanCrocodile);

                } else if(animalType.equals("krokodyl-nilowy-alfa")){

                    NileCrocodileAlpha nileCrocodileAlpha = new NileCrocodileAlpha(name);
                    nileCrocodileAlpha.setThirsty(isThirsty);
                    nileCrocodileAlpha.setHungry(isHungry);
                    animals.add(nileCrocodileAlpha);

                } else if(animalType.equals("lemur")){

                    Lemur lemur = new Lemur(name);
                    lemur.setThirsty(isThirsty);
                    lemur.setHungry(isHungry);
                    animals.add(lemur);

                }

            }
            rs.close();
            this.statement.close();
            this.connection.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }

        return animals;
    }

}
