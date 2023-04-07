package com.example.fishing.model;

import lombok.Getter;

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

    private String getCardNumber(int number) {
        if (number == 14) {
            return "A";
        } else if (number == 11) {
            return "J";
        } else if (number == 12) {
            return "Q";
        } else if (number == 13) {
            return "K";
        } else {
            return String.valueOf(number);
        }
    }

    @Override
    public int compareTo(Card other) {
        if (this.number != other.number) {
            return this.number - other.number;
        } else {
            return other.suit.getValue() - this.suit.getValue();
        }
    }
}
