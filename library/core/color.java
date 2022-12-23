package library.core;

import java.awt.Color;

public class color extends PFunctions {

    int r, g, b, a;

    public color(Color c) {
        this(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha());
    }

    public color(color c, double alpha) {
        this(c.r, c.g, c.b, alpha);
    }

    public color(double r, double g, double b, double alpha) {
        this.r = (int) constrain(r, 0, 255);
        this.g = (int) constrain(g, 0, 255);
        this.b = (int) constrain(b, 0, 255);
        this.a = (int) constrain(alpha, 0, 255);
    }

    public color(double r, double g, double b) {
        this(r, g, b, 255);
    }

    public color(double g, double a) {
        this(g, g, g, a);
    }

    public color(double g) {
        this(g, 255);
    }

    public color() {
        this(0);
    }

    public static color fromInt(int c) {
        return new color((c >> 16) & 0xFF, (c >> 8) & 0xFF, c & 0xFF, (c >> 24) & 0xFF);
    }

    public int getRGB() {
        Color c = new Color(r, g, b, a);
        return c.getRGB();
    }

    public float getRed() {
        return r;
    }

    public float getGreen() {
        return g;
    }

    public float getBlue() {
        return b;
    }

    public float getAlpha() {
        return a;
    }

    float[] RGBtoHSB() {
        Color c = new Color(r, g, b);
        float[] hsb = Color.RGBtoHSB(c.getRed(), c.getGreen(), c.getBlue(), null);
        return hsb;
    }

    public float getHue() {
        float[] hsb = RGBtoHSB();
        return hsb[0];
    }

    public float getSaturation() {
        float[] hsb = RGBtoHSB();
        return hsb[1];
    }

    public float getBrightness() {
        float[] hsb = RGBtoHSB();
        return hsb[2];
    }

    public String toString() {
        return "color(" + r + ", " + g + ", " + b + ", " + a + ")";
    }

    public Color toColor() {
        return new Color(r, g, b, a);
    }

}
