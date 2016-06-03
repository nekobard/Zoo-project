package com.zoo;

/**
 * Created by nekobard on 03.06.16.
 */
public abstract class Crocodile extends Animal{

    public Crocodile(String name) {
        super(name);
    }

    public void attackWithRotate(){
        System.out.println("Wciaga ofiare pod wode i obraca sie");
    }

    public void attackFromWater(){
        System.out.println("Atakuje z wody");
    }
}
