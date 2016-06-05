package com.zoo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nekobard on 30.05.16.
 */
public class Enclosure {

    private String name;
    private List<Animal> animals;
    private List<String> animalsTypes;

    private void addAnimalType(String animalType){
        if(!animalsTypes.contains(animalType)){
            animalsTypes.add(animalType);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Enclosure(String name){
        this.animals = new ArrayList<Animal>();
        this.animalsTypes = new ArrayList<String>();
        this.setName(name);
    }

    public void addAnimal(String animalType, String animalName){

        if(animalType.equals("krokodyl-nilowy")){

            NileCrocodile nileCrocodile = new NileCrocodile(animalName);
            this.addAnimalType(nileCrocodile.getAnimalType());
            this.animals.add(nileCrocodile);
            System.out.println("Wprowadzono na wybieg krokodyla nilowego");

        } else if(animalType.equals("krokodyl-amerykanski")){

            AmericanCrocodile americanCrocodile = new AmericanCrocodile(animalName);
            this.addAnimalType(americanCrocodile.getAnimalType());
            this.animals.add(americanCrocodile);
            System.out.println("Wprowadzono na wybieg krokodyla amerykanskiego");

        } else{
            System.out.println("Nie ma takiego gatunku");
        }
    }

    public void feedAnimals(){
        if(this.animals.size() > 0){
            this.animals.forEach(animal -> {
                animal.setHungry(false);
                animal.setThirsty(false);
            });

            System.out.format("Zawierzeta na wybiegu: %s nie sa juz glodne ani spragnione\n", this.getName());
        }else{
            System.out.format("Na wybiegu %s nie ma zwierzat\n", this.getName());
        }

    }

    public void getAnimalsState(){
        this.animals.forEach(animal -> {

            System.out.format("%s %s:\n", animal.getAnimalType(),animal.getName());

            String hungry;
            String thirsty;

            if(animal.isHungry()){
                hungry = "Jest glodny";
            }else{
                hungry = "Nie jest glodny";
            }
            if(animal.isThirsty()){
                thirsty = "Jest spragniony";
            }else{
                thirsty = "Nie jest spragniony";
            }

            System.out.println(hungry);
            System.out.println(thirsty);
            System.out.println();
        });
    }

    public void getAnimalsInfo(){
        if(this.animals.size() > 0){
            this.animals.forEach(animal -> {
                animal.getInfo();
                System.out.println();
            });
        } else{
            System.out.format("Na wybiegu %s nie ma jeszcze zwierzat\n", this.getName());
        }

    }


    public void getAnimalsAmount(){
        int animalsAll = this.animals.size();
        String enclosureName = this.getName();

        System.out.format("Zwierzat na wybiegu %s jest: %d\n", enclosureName, animalsAll);

        this.animalsTypes.forEach(animalType->{

            int animalsAmount = 0;
            for (Animal animal : this.animals) {
                if(animal.getAnimalType() == animalType){
                    animalsAmount++;
                }
            }

            System.out.format("%s: %d\n", animalType, animalsAmount);

        });
    }


}
