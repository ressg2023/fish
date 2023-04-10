package com.example.fishing.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class SuitTest {
    @Test
    void testFromValue() {
        assertNull(Suit.fromValue(42));
        assertEquals(Suit.CLUB, Suit.fromValue(1));
        assertEquals(Suit.HEART, Suit.fromValue(2));
        assertEquals(Suit.SPADE, Suit.fromValue(3));
        assertEquals(Suit.DIAMOND, Suit.fromValue(0));
    }
}

