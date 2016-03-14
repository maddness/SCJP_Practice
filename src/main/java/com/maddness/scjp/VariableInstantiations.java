package com.maddness.scjp;

/**
 * Hello world!
 *
 */
public class VariableInstantiations {
    {
        System.out.println("static " + this.a);
        this.a = 9;
    }

    int a = 5;

    public VariableInstantiations() {
        System.out.println("const start " + this.a);

        this.a = 1;

        System.out.println("const ends " + this.a);

    }

    public static void main(String[] args ) {
        new VariableInstantiations();
    }
}
