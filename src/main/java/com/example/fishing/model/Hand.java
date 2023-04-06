package com.example.fishing.model;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Hand implements Comparable<Hand> {
    private Card[] cards;
    private int count;
    private Rank rank;

    public Hand() {
        cards = new Card[5];
        count = 0;
    }

    public void assignCard(Card card) {
        if (count == 5) {
            log.warn("Player already has 5 cards.");
            return;
        }
        cards[count++] = card;
    }

    public void calculateRank() {
        // rank;
    }

    @Override
    public int compareTo(Hand other) {
        return 0;
    }
}
