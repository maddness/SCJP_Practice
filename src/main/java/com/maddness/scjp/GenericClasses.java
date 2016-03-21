package com.maddness.scjp;

import java.util.ArrayList;
import java.util.List;

public class GenericClasses {
    public static void main(String[] args) {

        GenericHolder<String> holder = new GenericHolder<>();
        holder.setInstance("hello");

        System.out.println(holder.getInstance());
        System.out.println(holder.getList(4.67));

    }
}

class GenericHolder<T> {
    private T instance;

    public T getInstance() {
        return instance;
    }

    public void setInstance(T instance) {
        this.instance = instance;
    }

    public <E extends Number> List<E> getList(E object) {
        ArrayList<E> list = new ArrayList<>();
        list.add(object);
        return list;
    }
}
