package com.zoo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by nekobard on 03.06.16.
 */
public class App {
    private List<Enclosure> enclosures;

    private void recognizeCommand(String userCommand){
        String[] commands = userCommand.split("\\s+");
        if(commands[0].equals("new")){
            if(commands[1].equals("enclosure")){
                for(int i = 2; i < commands.length; i++){
                    this.addEnclosure(commands[i]);
                }
            } else if(commands[1].equals("animal")){



            }
        } else if(commands[0].equals("get")){
            if(commands[1].equals("enclosures")){
                this.getEnclosures();
            }
        } else if(commands[0].equals("exit")){
            System.out.println("Papa");
        }
    }

    private void getEnclosures(){
        System.out.println("Wybiegi:");
        this.enclosures.forEach(enclosure -> {
            System.out.println(enclosure.getName());
        });
    }

    private void addEnclosure(String enclosureName){
        int j=0;
        boolean nameFound = false;
        while(j < this.enclosures.size() && !nameFound){
            if(this.enclosures.get(j).getName().equals(enclosureName)){
                nameFound = true;
            }
            j++;
        }
        if(!nameFound){
            Enclosure enclosure = new Enclosure(enclosureName);
            enclosures.add(enclosure);
            System.out.format("Stworzono wybieg %s\n", enclosureName);
        }else{
            System.out.format("Wybieg %s juz istnieje\n", enclosureName);
        }
    }

    public App(){
        this.enclosures = new ArrayList<Enclosure>();
    }

    public void start(){
        System.out.println("Witaj w wirtualnym Zoo. Mozesz tworzyc nowe wybiegi i umieszczac tam zwierzeta");

        String userCommand = "";
        Scanner sc = new Scanner(System.in);

        while(!userCommand.equals("exit")){
            userCommand = sc.nextLine();
            //System.out.println(userCommand);
            this.recognizeCommand(userCommand);

        }

        sc.close();

    }

    private void manual(){

    }
}
