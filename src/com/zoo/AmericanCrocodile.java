package com.zoo;

/**
 * Created by nekobard on 03.06.16.
 */
public class AmericanCrocodile extends Crocodile{

    public AmericanCrocodile(String name) {
        super(name);
        this.setAnimalType("Krokodyl amerykanski");
    }

    public void eatHamburger(){
        System.out.println("Je hamburgera");
    }

    public void showEyes(){
        System.out.println("Wystawia oczy nad wode by lepiej widziec ofiare");
    }

    @Override
    public void getInfo(){
        System.out.format("%s %s:\n", this.getAnimalType(), this.getName());
        this.attackWithRotate();
        this.drinkWater();
        this.eatHamburger();
        this.showEyes();
    }
}
