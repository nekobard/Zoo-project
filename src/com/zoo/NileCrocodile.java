package com.zoo;

/**
 * Created by nekobard on 03.06.16.
 */
public class NileCrocodile extends Crocodile{

    public NileCrocodile(String name) {
        super(name);
        this.setAnimalType("Krokodyl nilowy");
    }

    public void eatEgyptian(){
        System.out.println("Zjada egipcjanina");
    }

    @Override
    public void getInfo(){
        System.out.format("%s %s:\n", this.getAnimalType(),this.getName());
        this.attackFromWater();
        this.eatFood();
        this.attackWithRotate();
        this.drinkWater();
        this.eatEgyptian();
    }
}
