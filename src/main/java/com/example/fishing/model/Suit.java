package com.example.fishing.model;

public enum Suit {
    DIAMOND("♦", 0),
    CLUB("♣", 1),
    HEART("♥", 2),
    SPADE("♠", 3);

    private final String shape;
    private final int value;

    Suit(String shape, int value) {
        this.shape = shape;
        this.value = value;
    }

    public String getShape() {
        return shape;
    }

    public int getValue() {
        return value;
    }
}
