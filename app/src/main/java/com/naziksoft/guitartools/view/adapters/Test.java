package com.naziksoft.guitartools.view.adapters;

import android.util.Pair;

import java.util.ArrayList;

public class Test {
    Tes counter = new Tes();
    ArrayList<Tes> list = new ArrayList<>();

    public void test() {
        for (int i = 0; i < 10; i++) {
            Tes temp = new Tes();
            temp = counter;
            temp.test++;
            list.add(temp);
        }
        list.size();
    }


    public class Tes {
        Integer test = 0;
    }
}
