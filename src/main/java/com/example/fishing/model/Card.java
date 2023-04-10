package com.example.fishing.model;

import lombok.Getter;

import static com.example.fishing.util.RankUtil.getCardNumber;

public class Card implements Comparable<Card> {
    @Getter
    private Suit suit;
    @Getter
    private int number;

    public Card(Suit suit, int number) {
        this.suit = suit;
        this.number = number;
    }

    public String toString() {
        return suit.getShape() + getCardNumber(number);
    }

    @Override
    public int compareTo(Card other) {
        if (this.number != other.number) {
            return this.number - other.number;
        } else {
            return this.suit.getValue() - other.suit.getValue();
        }
    }
}
