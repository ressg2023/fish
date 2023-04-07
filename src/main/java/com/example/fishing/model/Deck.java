package com.example.fishing.model;

public class Deck {
    private final Card[] cards = new Card[52];

    private static int DEFAULT_SHUFFLE_TIMES = 100;

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
        return cards[index];
    }
}
