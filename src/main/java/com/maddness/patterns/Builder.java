package com.maddness.patterns;

/**
 * Created by maddness on 16/03/2016.
 */
public class Builder {
    public static void main(String[] args) {
        Pizza pizza = new Pizza.PizzaBuilder(32).withOlives(5).withSalami(3).build();
        System.out.println(pizza);
    }
}

class Pizza {
    private final int size;
    private final int salami;

    @Override
    public String toString() {
        return "Pizza{" +
                "size=" + size +
                ", salami=" + salami +
                ", olives=" + olives +
                '}';
    }

    private final int olives;

    private Pizza(PizzaBuilder builder) {
        this.size = builder.size;
        this.salami = builder.salami;
        this.olives = builder.olives;
    }

    public static class PizzaBuilder {
        private int size;
        private int salami;
        private int olives;

        public PizzaBuilder(int size) {
            this.size = size;
        }

        public PizzaBuilder withSalami(int salami) {
            this.salami = salami;
            return this;
        }

        public PizzaBuilder withOlives(int olives) {
            this.olives = olives;
            return this;
        }

        public Pizza build() {
            return new Pizza(this);
        }
    }

}
