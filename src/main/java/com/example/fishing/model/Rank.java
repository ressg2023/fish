package com.example.fishing.model;

public enum Rank {
    ROYAL_FLUSH("Royal flush", 0),
    STRAIGHT_FLUSH("Straight flush", 1),
    FOUR_OF_A_KIND("Four of a kind", 2),
    FULL_HOUSE("Full house", 3),
    FLUSH("Flush", 4),
    STRAIGHT("Straight", 5),
    THREE_OF_A_KIND("Three of a kind", 6),
    TWO_PAIRS("Two pairs", 7),
    ONE_PAIR("One pair", 8),
    NOTHING("Nothing", 9);

    private String name;
    private int value;

    Rank(String name, int value) {
        this.name = name;
        this.value = value;
    }
}
