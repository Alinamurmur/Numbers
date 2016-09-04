package com.example.alina.numbers;



public class Circle {

    public static final int INTRAD =50;
    private int x;
    private int y;
    private int radius;

    public Circle(int x, int y) {
        this.x = x;
        this.y = y;
        radius = INTRAD;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getRadius() {
        return radius;
    }
}
