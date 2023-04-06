package com.example.fishing.model;

public class Deck {
    private Card[] cards = new Card[52];

    private static int DEFAULT_SHUFFLE_TIMES = 100;

    public Deck() {
        int seq = 0;
        for (Suit suit: Suit.values()) {
            for (int i=1; i<=13; i++) {
                cards[seq++] = new Card(suit, i);
            }
        }
    }

    public void shuffle() {
        for (int i=0; i<DEFAULT_SHUFFLE_TIMES; i++) {
            //do shuffle
        }
    }

    public Card getCard(int index) {
        return cards[index];
    }
}
