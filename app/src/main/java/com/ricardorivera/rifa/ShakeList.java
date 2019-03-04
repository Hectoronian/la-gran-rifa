package com.ricardorivera.rifa;

import java.util.ArrayList;
import java.util.Random;

public class ShakeList {

    //attributes
    private ArrayList<String> array;
    private Random shaker = new Random();
    private int shakeIndex = 0;

    //methods
    ShakeList() {
        array = new ArrayList<>();
        array.add("Example Item");
    }

    public int length() {
        return array.size();
    }

    public String shakeArray() {
        if (array.size() >= 2) {
            shakeIndex = shaker.nextInt(array.size());
            return array.get(shakeIndex);
        } else {
            return "No Enough Item in the List";
        }
    }

    public void clearArray() {
        if (!array.isEmpty()) {
            array.clear();
        }
    }

    public String getItem(int i) {
       return array.get(i);
    }

    public void addItem(String s) {
        if (!s.isEmpty()) {
            array.add(s);
        }
    }

    // No used, but can be useful in the future
    public ArrayList<String> getShakeArray() {
        return array;
    }
    public void removeItem(int i) {
        array.remove(i);
    }
}
