package com.example.fishing.model;

import lombok.Getter;

public enum DeckRank {
    NOTHING("Nothing", 0),
    ONE_PAIR("One pair", 1),
    TWO_PAIRS("Two pairs", 2),
    THREE_OF_A_KIND("Three of a kind", 3),
    STRAIGHT("Straight", 4),
    FLUSH("Flush", 5),
    FULL_HOUSE("Full house", 6),
    FOUR_OF_A_KIND("Four of a kind", 7),
    STRAIGHT_FLUSH("Straight flush", 8),
    ROYAL_FLUSH("Royal flush", 9);

    @Getter
    private final String name;
    @Getter
    private final int value;

    DeckRank(String name, int value) {
        this.name = name;
        this.value = value;
    }
}
