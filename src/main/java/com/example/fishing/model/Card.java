package com.example.fishing.model;

import lombok.Getter;

public class Card implements Comparable<Card> {
    @Getter
    private Suit suit;
    @Getter
    private Rank rank;

    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public String toString() {
        return suit.getShape() + rank.getName();
    }

    @Override
    public int compareTo(Card other) {
        if (this.rank != other.rank) {
            return this.rank.getValue() - other.rank.getValue();
        } else {
            return this.suit.getValue() - other.suit.getValue();
        }
    }
}
