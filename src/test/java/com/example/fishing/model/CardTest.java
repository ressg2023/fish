package com.example.fishing.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class CardTest {
    @Test
    void testConstructor() {
        assertEquals("♦10", (new Card(Suit.DIAMOND, Rank.TEN)).toString());
    }
    @Test
    void testIsNext() {
        Card card = new Card(Suit.DIAMOND, Rank.TWO);
        Card other = new Card(Suit.DIAMOND, Rank.TWO);
        assertFalse(card.isNext(other));

        card = new Card(Suit.DIAMOND, Rank.TWO);
        other = new Card(Suit.DIAMOND, Rank.THREE);
        assertTrue(card.isNext(other));

        card = new Card(Suit.DIAMOND, Rank.ACE);
        other = new Card(Suit.DIAMOND, Rank.TWO);
        assertTrue(card.isNext(other));
    }

    @Test
    void testCompareTo() {
        Card card = new Card(Suit.DIAMOND, Rank.TEN);
        Card other = new Card(Suit.DIAMOND, Rank.TEN);
        assertEquals(0, card.compareTo(other));

        card = new Card(Suit.DIAMOND, Rank.QUEEN);
        other = new Card(Suit.DIAMOND, Rank.TEN);
        assertTrue(card.compareTo(other) > 0);

        card = new Card(Suit.SPADE, Rank.TEN);
        other = new Card(Suit.HEART, Rank.TEN);
        assertTrue(card.compareTo(other) > 0);
    }
}

