package com.enricojr.stdlib;

import com.enricojr.stdlib.sequences.DynamicArray;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        DynamicArray<Integer> dai = new DynamicArray<>(1, 2, 3, 4, 5, 6, 7, 8);
        System.out.println("Hello world!");
        System.out.println(dai);

        dai.deleteLast();
        dai.deleteLast();
        dai.deleteLast();
        dai.deleteLast();
        dai.deleteLast();
        dai.deleteLast();
        System.out.println(dai);

        dai.insertLast(3);
        System.out.println(dai);
        dai.insertLast(4);
        System.out.println(dai);
        dai.insertLast(5);
        System.out.println(dai);
        dai.insertLast(6);
        System.out.println(dai);
        dai.insertLast(7);
        System.out.println(dai);
        dai.insertLast(8);
        System.out.println(dai);
        dai.insertLast(9);
        System.out.println(dai);
        dai.insertLast(10);
        System.out.println(dai);
        dai.insertLast(11);
        System.out.println(dai);
        dai.insertLast(12);
        System.out.println(dai);

        System.out.println(dai.getArraySize());
    }
}
