package com.example.fishing.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class CardTest {
    @Test
    void testConstructor() {
        assertEquals("♦10", (new Card(Suit.DIAMOND, 10)).toString());
    }

    @Test
    void testCompareTo() {
        Card card = new Card(Suit.DIAMOND, 10);
        Card other = new Card(Suit.DIAMOND, 10);
        assertEquals(0, card.compareTo(other));

        card = new Card(Suit.DIAMOND, 12);
        other = new Card(Suit.DIAMOND, 10);
        assertTrue(card.compareTo(other) > 0);

        card = new Card(Suit.SPADE, 10);
        other = new Card(Suit.HEART, 10);
        assertTrue(card.compareTo(other) > 0);
    }
}

