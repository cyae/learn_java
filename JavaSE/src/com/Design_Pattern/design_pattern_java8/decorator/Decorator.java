package com.Design_Pattern.design_pattern_java8.decorator;

@SuppressWarnings("all")
public class Decorator {

    public static void main(String[] args) {
        printSnap(new Camera(Color::brighter));
        printSnap(new Camera(Color::refresh));
        printSnap(new Camera(Color::darker));
        printSnap(new Camera(Color::darker, Color::refresh, Color::brighter));
    }

    private static void printSnap(Camera camera) {
        System.out.println(camera.snap(new Color(125, 125, 125)));
    }
}
