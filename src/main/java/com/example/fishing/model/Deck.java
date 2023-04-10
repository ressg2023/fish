package com.example.fishing.model;

import lombok.Getter;

public class Deck {
    @Getter
    private final Card[] cards = new Card[52];

    static int DEFAULT_SHUFFLE_TIMES = 100;

    public Deck() {
        int seq = 0;
        for (Suit suit: Suit.values()) {
            for (int i=2; i<=14; i++) {
                cards[seq++] = new Card(suit, i);
            }
        }
    }

    public void shuffle() {
        for (int i=0; i<DEFAULT_SHUFFLE_TIMES; i++) {
            int first = (int) (Math.random() * 52);
            int second = (int) (Math.random() * 52);
            Card temp = cards[first];
            cards[first] = cards[second];
            cards[second] = temp;
        }
    }

    public Card getCard(int index) {
        if (index < 0 || index >= 52) {
            return null;
        }
        return cards[index];
    }
}
