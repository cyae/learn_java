package com.Design_Pattern.design_pattern_java8.decorator;

public class Color {

    private Integer R;
    private Integer G;
    private Integer B;

    public Color(Integer R, Integer G, Integer B) {
        this.R = R;
        this.G = G;
        this.B = B;
    }

    @Override
    public String toString() {
        return "Color [R=" + R + ", G=" + G + ", B=" + B + "]";
    }

    public static Color brighter(Color color) {
        return new Color(color.R + 10, color.G + 10, color.B + 10);
    }

    public static Color refresh(Color color) {
        return new Color(color.R, color.G, color.B);
    }

    public static Color darker(Color color) {
        return new Color(color.R - 10, color.G - 10, color.B - 10);
    }

}
