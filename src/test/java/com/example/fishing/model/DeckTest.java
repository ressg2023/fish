package com.example.fishing.model;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeckTest {
    @Test
    void testConstructor() {
        Card card = (new Deck()).getCard(1);
        assertEquals(3, card.getNumber());
        assertEquals(Suit.DIAMOND, card.getSuit());
    }

    @Test
    void testShuffle() {
        Deck deck = new Deck();
        int changed = 0;
        for (int i=0; i<5; i++) {
            int random = (int) (Math.random() * 52);
            int originalNumber = random%13 + 2;
            Suit originalSuit = Suit.fromValue(random/13);
            if (deck.getCard(random).getNumber() != originalNumber || deck.getCard(random).getSuit() != originalSuit) {
                changed++;
            }
        }
        assertEquals(0, changed);

        deck.shuffle();
        // Randomly pick 5 cards, if anyone is changed, we assume it is shuffled
        changed = 0;
        for (int i=0; i<5; i++) {
            int random = (int) (Math.random() * 52);
            int originalNumber = random%13 + 2;
            Suit originalSuit = Suit.fromValue(random/13);
            if (deck.getCard(random).getNumber() != originalNumber || deck.getCard(random).getSuit() != originalSuit) {
                changed++;
            }
        }
        assertTrue(changed > 0);
    }

    @Test
    void testGetCard() {
        Deck deck = new Deck();
        assertEquals(14, deck.getCard(51).getNumber());
        assertEquals(Suit.SPADE, deck.getCard(51).getSuit());
    }

    @Test
    void testGetCard2() {
        assertNull((new Deck()).getCard(52));
    }
    @Test
    void testGetCard3() {
        assertNull((new Deck()).getCard(-1));
    }
}

