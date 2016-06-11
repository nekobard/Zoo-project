package com.zoo;

/**
 * Created by nekobard on 11.06.16.
 */
public class NileCrocodileAlpha extends NileCrocodile{
    public NileCrocodileAlpha(String name){
        super(name);
        this.setAnimalType("Krokodyl nilowy alfa");
    }

    public void swimSync(){
        System.out.println("Zaczyna machac ogonem i wszystkie krokodyle zaczynaja plywac synchronicznie");
    }

    @Override
    public void getInfo(){
        System.out.format("%s %s:\n", this.getAnimalType(),this.getName());
        this.attackFromWater();
        this.eatFood();
        this.swimSync();
        this.drinkWater();
        this.eatEgyptian();
    }
}
