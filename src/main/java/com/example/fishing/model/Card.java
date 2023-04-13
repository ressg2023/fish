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

    public boolean isNext(Card card) {
        if (card.rank == Rank.TWO && this.rank == Rank.ACE) {
            return true;
        }
        return this.rank.getValue() + 1 == card.rank.getValue();
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
