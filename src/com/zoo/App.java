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
        if(commands[0] == "new"){
            if(commands[1] == "enclosure"){
                for(int i = 2; i < commands.length; i++){
                    int j=0;
                    boolean nameFound = false;
                    while(j < this.enclosures.size() && !nameFound){
                        if(this.enclosures.get(j).getName() == commands[i]){
                            nameFound = true;
                        }
                    }
                    if(!nameFound){
                        Enclosure enclosure = new Enclosure(commands[i]);
                        enclosures.add(enclosure);
                        System.out.format("Stworzono wybieg %s", commands[i]);
                    }else{
                        System.out.format("Wybieg %s juz istnieje\n", commands[i]);
                    }

                }
            } else if(commands[1] == "animal"){



            }
        }
    }

    public App(){
        this.enclosures = new ArrayList<Enclosure>();
    }

    public void start(){
        System.out.println("Witaj w wirtualnym Zoo. Mozesz tworzyc nowe wybiegi i umieszczac tam zwierzeta");

        String userCommand;

        Scanner sc = new Scanner(System.in);

        userCommand = sc.nextLine();

        sc.close();

        System.out.println(userCommand);
    }

    private void manual(){

    }
}
