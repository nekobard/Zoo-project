package com.zoo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



/**
 * Created by nekobard on 03.06.16.
 */
public class App {
    private List<Enclosure> enclosures;

    private int findEnclosure(String enclosureName){
        int k=0;
        boolean enclosureFound = false;
        int enclosureIndex = 0;
        while(k < this.enclosures.size() && !enclosureFound){
            if(enclosures.get(k).getName().equals(enclosureName)){
                enclosureFound = true;
                enclosureIndex = k;
            }else{
                k++;
            }
        }
        if(enclosureFound){
            return enclosureIndex;
        }else{
            return -1;
        }
    }

    private SQLiteZoo sqLiteZoo;

    private void recognizeCommand(String userCommand){
        String[] commands = userCommand.split("\\s+");
        try{
            if(commands[0].equals("new")){

                if(commands[1].equals("enclosure")){
                    for(int i = 2; i < commands.length; i++){
                        this.addEnclosure(commands[i]);
                        this.sqLiteZoo.saveEnclosure(commands[i]);
                    }
                } else if(commands[1].equals("animal")){

                    int indexFound = findEnclosure(commands[4]);
                    if(indexFound != -1){
                        this.enclosures.get(indexFound).addAnimal(commands[2], commands[3]);
                        this.sqLiteZoo.saveAnimal(commands[2], commands[3], commands[4]);
                    } else {
                        System.out.println("Nie ma takiego wybiegu");
                    }
                } else{
                    System.out.println("Zla skladnia dla new");
                }

            } else if(commands[0].equals("feed")){
                if(commands.length == 1){
                    this.feedAnimals();
                } else if(commands.length == 2){
                    int enclosureIndex = 0;
                    enclosureIndex = this.findEnclosure(commands[1]);
                    if (enclosureIndex != -1) {
                        this.feedAnimals(enclosureIndex);
                    }
                }else{
                    System.out.println("Zla skladnia dla feed");
                }
            }else if(commands[0].equals("save")){
                this.save();
            }else if(commands[0].equals("get")){
                if(commands[1].equals("enclosures")){
                    this.getEnclosures();
                }else if(commands[1].equals("animals-info")){
                    if(this.enclosures.size() > 0) {
                        if (commands.length == 2) {
                            System.out.println("Co robia zwierzeta we wszystkich wybiegach:");
                            this.getAnimalsInfo();
                        } else if (commands.length == 3) {
                            System.out.format("Co robia zwierzeta na wybiegu %s:\n", commands[2]);
                            int enclosureIndex = 0;
                            enclosureIndex = this.findEnclosure(commands[2]);
                            if (enclosureIndex != -1) {
                                this.getAnimalsInfo(enclosureIndex);
                            }
                        } else {
                            System.out.println("Zla skladnia dla get animals-info");
                        }
                    }else{
                        System.out.println("Nie ma jeszcze wybiegow");
                    }
                }else if(commands[1].equals("animals-amount")) {
                    if (this.enclosures.size() > 0) {
                        if (commands.length == 2) {
                            this.getAnimalsAmount();
                        } else if (commands.length == 3) {
                            int enclosureIndex = 0;
                            enclosureIndex = this.findEnclosure(commands[2]);
                            if (enclosureIndex != -1) {
                                this.getAnimalsAmount(enclosureIndex);
                            }
                        } else {
                            System.out.println("Zla skladnia dla get animals-info");
                        }
                    } else {
                        System.out.println("Nie ma jeszcze wybiegow");
                    }
                }else if(commands[1].equals("animals-state")){
                    if (commands.length == 2) {
                        System.out.println("Stan zwierzat:");
                        this.getAnimalsState();
                    } else if (commands.length == 3) {
                        System.out.format("Stan zwierzat na wybiegu %s:\n", commands[2]);
                        int enclosureIndex = 0;
                        enclosureIndex = this.findEnclosure(commands[2]);
                        if (enclosureIndex != -1) {
                            this.getAnimalsState(enclosureIndex);
                        }
                    } else {
                        System.out.println("Zla skladnia dla get animals-state");
                    }
                }else{
                    System.out.println("Zla skladnia dla get");
                }
            } else if(commands[0].equals("exit")){
                System.out.println("Papa");
            } else{
                System.out.println("Nieznana komenda");
            }

        } catch(ArrayIndexOutOfBoundsException ex){
            System.out.println("Zla liczba argumentow");
        }
    }

    private void getAnimalsState(){
        this.enclosures.forEach(enclosure -> {
            System.out.format("Wybieg %s:\n", enclosure.getName());
            enclosure.getAnimalsState();
        });
    }

    private void getAnimalsState(int index){
        this.enclosures.get(index).getAnimalsState();
    }

    private void getAnimalsAmount(){
        this.enclosures.forEach(enclosure -> {
            System.out.format("Wybieg %s:\n", enclosure.getName());
            enclosure.getAnimalsAmount();
        });
    }

    private void getAnimalsAmount(int index){
        this.enclosures.get(index).getAnimalsAmount();
    }

    private void getAnimalsInfo(){
        this.enclosures.forEach(enclosure -> {
            System.out.format("Wybieg %s:\n", enclosure.getName());
            enclosure.getAnimalsInfo();
        });
    }

    private void getAnimalsInfo(int index){
        this.enclosures.get(index).getAnimalsInfo();
    }

    private void getEnclosures(){
        System.out.println("Wybiegi:");
        this.enclosures.forEach(enclosure -> {
            System.out.println(enclosure.getName());
        });
    }

    private void addEnclosure(String enclosureName){
        if(this.findEnclosure(enclosureName) == -1){
            Enclosure enclosure = new Enclosure(enclosureName);
            enclosures.add(enclosure);
            System.out.format("Stworzono wybieg %s\n", enclosureName);
        }else{
            System.out.format("Wybieg %s juz istnieje\n", enclosureName);
        }
    }

    private void feedAnimals(){
        this.enclosures.forEach(enclosure -> {
            enclosure.feedAnimals();
        });
    }

    private void feedAnimals(int index){
        this.enclosures.get(index).feedAnimals();
    }


    public App(){
        this.enclosures = new ArrayList<Enclosure>();
        this.sqLiteZoo = new SQLiteZoo();
    }

    public void start(){
        this.sqLiteZoo.start();
        this.enclosures = this.sqLiteZoo.loadEnclosures();
        this.enclosures.forEach(enclosure -> {
            enclosure.animals = this.sqLiteZoo.loadAnimals(enclosure.getName());
            List<String> animalsTypes = new ArrayList<String>();
            enclosure.animals.forEach(animal->{
                if(!animalsTypes.contains(animal.getAnimalType())){
                    animalsTypes.add(animal.getAnimalType());
                }
            });
            enclosure.animalsTypes = animalsTypes;
        });
        System.out.println("Witaj w wirtualnym Zoo. Mozesz tworzyc nowe wybiegi i umieszczac tam zwierzeta");

        String userCommand = "";
        Scanner sc = new Scanner(System.in);


        while(!userCommand.equals("exit")){

            userCommand = sc.nextLine();

            this.recognizeCommand(userCommand);

        }

        sc.close();

    }
    
}
