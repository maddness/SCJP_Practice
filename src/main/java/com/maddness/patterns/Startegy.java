package com.maddness.patterns;

/**
 * Created by maddness on 16/03/2016.
 */
public class Startegy {
//    https://ru.wikipedia.org/wiki/Стратегия_(шаблон_проектирования)

    public static void main(String[] args) {
        Bird bird = new Bird();
        Dog dog = new Dog();

        System.out.println(bird.tryToFly());
        System.out.println(dog.tryToFly());

        dog.setFlyingType(new FlyingHigh());
        System.out.println(dog.tryToFly());

    }
}

interface Flys {
    String fly();
}

class FlyingHigh implements Flys {

    @Override
    public String fly() {
        return "Flying high!";
    }
}

class CantFly implements Flys {

    @Override
    public String fly() {
        return "I can't fly";
    }
}


class Animal {
    private Flys flyingType;

    public void setFlyingType (Flys flyingType) {
        this.flyingType = flyingType;
    }

    public String tryToFly() {
        return flyingType.fly();
    }
}

class Dog extends Animal {
    public Dog() {
        setFlyingType(new CantFly());
    }
}

class Bird extends Animal{
    public Bird() {
        setFlyingType(new FlyingHigh());
    }
}
