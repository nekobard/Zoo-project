package com.zoo;

/**
 * Created by nekobard on 29.05.16.
 */
public abstract class Animal {

    private String name;
    private boolean thirsty;
    private boolean hungry;
    private String animalType;

    public Animal(String name){
        this.setName(name);
        this.setHungry(true);
        this.setThirsty(true);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isThirsty() {
        return thirsty;
    }

    public void setThirsty(boolean thirsty) {
        this.thirsty = thirsty;
    }

    public boolean isHungry() {
        return hungry;
    }

    public void setHungry(boolean hungry) {
        this.hungry = hungry;
    }

    public String getAnimalType() {
        return animalType;
    }

    public void setAnimalType(String animalType) {
        this.animalType = animalType;
    }
    // Functions of animal

    public void eatFood(){
        System.out.println("Je");
    }

    public void drinkWater(){
        System.out.println("Pije");
    }

    public void getInfo(){};

}
