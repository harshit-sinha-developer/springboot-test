package com.example.dependencyInjection;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class PerformTests {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        for(int i = 1; i < 20; i++) {
            list.add(i * 2 + 1);
        }

        for(int a: list) {
            System.out.println(a);
        }

        list.stream().forEach((a) -> {
            System.out.println("forEach a: " + a);
        });

        list.stream().map((a) -> {
//            System.out.println("hey");
            return a * 2;
        }).filter((a) -> a % 3 == 0).forEach(output -> {
            System.out.println(output);
        });


    }
}
