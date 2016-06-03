package com.zoo;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        Enclosure enclosure = new Enclosure("Wybieg krokodyli");

        enclosure.addAnimal("krokodyl-nilowy", "Heniek");
        enclosure.addAnimal("krokodyl-amerykanski", "Barbossa");
        enclosure.addAnimal("krokodyl-amerykanski", "Jack");
        enclosure.addAnimal("krokodyl-nilowy", "Kasia");
        enclosure.addAnimal("krokodyl-nilowy", "Bartek");
        enclosure.addAnimal("krokodyl-amerykanski", "Roman");

        enclosure.getAnimalsAmount();
    }
}
