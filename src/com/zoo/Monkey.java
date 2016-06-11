package com.zoo;

/**
 * Created by nekobard on 11.06.16.
 */
public abstract class Monkey extends Animal{
    public Monkey(String name){
        super(name);
        this.setAnimalType("Lemur");
    }

    public void climbTree(){
        System.out.println("Wspina sie na drzewo");
    }
}
