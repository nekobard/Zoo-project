package com.zoo;

/**
 * Created by nekobard on 11.06.16.
 */
public class Lemur extends Monkey{
    public Lemur(String name){
        super(name);
    }

    public void dance(){
        System.out.println("Wygina smialo cialo");
    }

    @Override
    public void getInfo(){
        System.out.format("%s %s:\n", this.getAnimalType(),this.getName());
        this.eatFood();
        this.dance();
        this.drinkWater();
        this.climbTree();
    }
}
