package com.JNF.enumFuncional;

/**
 * Main
 */
public class Main {

    public static void main(String[] args) {

        Movie2 movie = new Movie2(Movie2.Type.REGULAR);
        PriceService ps = new PriceService();
        int price = ps.getPrice(movie.getType(), 2);
        System.out.println(price);
    }
}