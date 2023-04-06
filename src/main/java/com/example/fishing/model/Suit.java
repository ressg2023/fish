package com.example.fishing.model;

public enum Suit {
    SPADE("♠", 0),
    HEART("♥", 1),
    CLUB("♣", 2),
    DIAMOND("♦", 3);

    private String shape;
    private int value;

    Suit(String shape, int value) {
        this.shape = shape;
        this.value = value;
    }

    public String getShape() {
        return shape;
    }
}
