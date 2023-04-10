package com.example.fishing.model;

import lombok.Getter;

public enum Suit {
    DIAMOND("♦", 0),
    CLUB("♣", 1),
    HEART("♥", 2),
    SPADE("♠", 3);

    @Getter
    private final String shape;
    @Getter
    private final int value;

    Suit(String shape, int value) {
        this.shape = shape;
        this.value = value;
    }

    public static Suit fromValue(int value) {
        for (Suit suit: Suit.values()) {
            if (suit.getValue() == value) {
                return suit;
            }
        }
        return null;
    }
}
