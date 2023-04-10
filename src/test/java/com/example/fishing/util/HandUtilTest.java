package com.example.fishing.util;

import com.example.fishing.model.Card;
import com.example.fishing.model.Suit;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HandUtilTest {
    @Test
    void testIsSequence() {
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(Suit.SPADE, 8));
        cards.add(new Card(Suit.DIAMOND, 10));
        cards.add(new Card(Suit.CLUB, 11));
        cards.add(new Card(Suit.HEART, 12));
        cards.add(new Card(Suit.SPADE, 13));
        assertFalse(HandUtil.isSequence(cards));

        cards = new ArrayList<>();
        cards.add(new Card(Suit.SPADE, 9));
        cards.add(new Card(Suit.DIAMOND, 10));
        cards.add(new Card(Suit.CLUB, 11));
        cards.add(new Card(Suit.HEART, 12));
        cards.add(new Card(Suit.SPADE, 13));
        assertTrue(HandUtil.isSequence(cards));

        cards = new ArrayList<>();
        cards.add(new Card(Suit.DIAMOND, 10));
        cards.add(new Card(Suit.CLUB, 11));
        cards.add(new Card(Suit.HEART, 12));
        cards.add(new Card(Suit.SPADE, 13));
        cards.add(new Card(Suit.SPADE, 14));
        assertTrue(HandUtil.isSequence(cards));

        cards = new ArrayList<>();
        cards.add(new Card(Suit.DIAMOND, 2));
        cards.add(new Card(Suit.CLUB, 3));
        cards.add(new Card(Suit.HEART, 4));
        cards.add(new Card(Suit.SPADE, 5));
        cards.add(new Card(Suit.SPADE, 14));
        assertTrue(HandUtil.isSequence(cards));
    }

    @Test
    void testGetCountOfTopNumber() {
        ArrayList<Card> cards = new ArrayList<>();
        assertEquals(0, HandUtil.getCountOfTopNumber(cards));

        cards.add(new Card(Suit.SPADE, 9));
        cards.add(new Card(Suit.DIAMOND, 10));
        cards.add(new Card(Suit.CLUB, 11));
        cards.add(new Card(Suit.HEART, 12));
        cards.add(new Card(Suit.SPADE, 13));
        assertEquals(1, HandUtil.getCountOfTopNumber(cards));

        cards = new ArrayList<>();
        cards.add(new Card(Suit.SPADE, 10));
        cards.add(new Card(Suit.DIAMOND, 10));
        cards.add(new Card(Suit.CLUB, 11));
        cards.add(new Card(Suit.HEART, 12));
        cards.add(new Card(Suit.SPADE, 13));
        assertEquals(2, HandUtil.getCountOfTopNumber(cards));

        cards = new ArrayList<>();
        cards.add(new Card(Suit.SPADE, 10));
        cards.add(new Card(Suit.DIAMOND, 10));
        cards.add(new Card(Suit.CLUB, 10));
        cards.add(new Card(Suit.HEART, 12));
        cards.add(new Card(Suit.SPADE, 12));
        assertEquals(3, HandUtil.getCountOfTopNumber(cards));

        cards = new ArrayList<>();
        cards.add(new Card(Suit.SPADE, 10));
        cards.add(new Card(Suit.DIAMOND, 10));
        cards.add(new Card(Suit.CLUB, 10));
        cards.add(new Card(Suit.HEART, 10));
        cards.add(new Card(Suit.SPADE, 12));
        assertEquals(4, HandUtil.getCountOfTopNumber(cards));
    }

    @Test
    void testGetCountOfSuit() {
        ArrayList<Card> cards = new ArrayList<>();
        assertEquals(0, HandUtil.getCountOfSuit(cards));

        cards.add(new Card(Suit.SPADE, 9));
        cards.add(new Card(Suit.SPADE, 10));
        cards.add(new Card(Suit.SPADE, 11));
        cards.add(new Card(Suit.SPADE, 12));
        cards.add(new Card(Suit.SPADE, 13));
        assertEquals(1, HandUtil.getCountOfSuit(cards));

        cards = new ArrayList<>();
        cards.add(new Card(Suit.SPADE, 9));
        cards.add(new Card(Suit.SPADE, 10));
        cards.add(new Card(Suit.SPADE, 11));
        cards.add(new Card(Suit.SPADE, 12));
        cards.add(new Card(Suit.DIAMOND, 13));
        assertEquals(2, HandUtil.getCountOfSuit(cards));

        cards = new ArrayList<>();
        cards.add(new Card(Suit.SPADE, 9));
        cards.add(new Card(Suit.SPADE, 10));
        cards.add(new Card(Suit.SPADE, 11));
        cards.add(new Card(Suit.HEART, 12));
        cards.add(new Card(Suit.DIAMOND, 13));
        assertEquals(3, HandUtil.getCountOfSuit(cards));

        cards = new ArrayList<>();
        cards.add(new Card(Suit.SPADE, 9));
        cards.add(new Card(Suit.SPADE, 10));
        cards.add(new Card(Suit.CLUB, 11));
        cards.add(new Card(Suit.HEART, 12));
        cards.add(new Card(Suit.DIAMOND, 13));
        assertEquals(4, HandUtil.getCountOfSuit(cards));
    }

    @Test
    void testGetCountOfNumber() {
        ArrayList<Card> cards = new ArrayList<>();
        assertEquals(0, HandUtil.getCountOfNumber(cards));

        cards.add(new Card(Suit.SPADE, 9));
        cards.add(new Card(Suit.SPADE, 10));
        cards.add(new Card(Suit.CLUB, 10));
        cards.add(new Card(Suit.HEART, 10));
        cards.add(new Card(Suit.DIAMOND, 10));
        assertEquals(2, HandUtil.getCountOfNumber(cards));

        cards = new ArrayList<>();
        cards.add(new Card(Suit.SPADE, 9));
        cards.add(new Card(Suit.SPADE, 8));
        cards.add(new Card(Suit.CLUB, 10));
        cards.add(new Card(Suit.HEART, 10));
        cards.add(new Card(Suit.DIAMOND, 10));
        assertEquals(3, HandUtil.getCountOfNumber(cards));

        cards = new ArrayList<>();
        cards.add(new Card(Suit.SPADE, 9));
        cards.add(new Card(Suit.SPADE, 8));
        cards.add(new Card(Suit.CLUB, 7));
        cards.add(new Card(Suit.HEART, 10));
        cards.add(new Card(Suit.DIAMOND, 10));
        assertEquals(4, HandUtil.getCountOfNumber(cards));

        cards = new ArrayList<>();
        cards.add(new Card(Suit.SPADE, 9));
        cards.add(new Card(Suit.SPADE, 8));
        cards.add(new Card(Suit.CLUB, 7));
        cards.add(new Card(Suit.HEART, 6));
        cards.add(new Card(Suit.DIAMOND, 10));
        assertEquals(5, HandUtil.getCountOfNumber(cards));
    }

    @Test
    void testGetFourOfAKindNumber() {
        ArrayList<Card> cards = new ArrayList<>();
        assertEquals(-1, HandUtil.getFourOfAKindNumber(cards));

        cards.add(new Card(Suit.DIAMOND, 10));
        cards.add(new Card(Suit.SPADE, 10));
        cards.add(new Card(Suit.CLUB, 10));
        cards.add(new Card(Suit.HEART, 8));
        cards.add(new Card(Suit.DIAMOND, 9));
        assertEquals(-1, HandUtil.getFourOfAKindNumber(cards));

        cards = new ArrayList<>();
        cards.add(new Card(Suit.DIAMOND, 10));
        cards.add(new Card(Suit.SPADE, 10));
        cards.add(new Card(Suit.CLUB, 10));
        cards.add(new Card(Suit.HEART, 10));
        cards.add(new Card(Suit.DIAMOND, 9));
        assertEquals(10, HandUtil.getFourOfAKindNumber(cards));
    }

    @Test
    void testGetSuitsForFullHouse() {
        ArrayList<Card> cards = new ArrayList<>();
        cards.add(new Card(Suit.DIAMOND, 10));
        cards.add(new Card(Suit.CLUB, 10));
        cards.add(new Card(Suit.SPADE, 10));
        cards.add(new Card(Suit.DIAMOND, 11));
        cards.add(new Card(Suit.SPADE, 11));
        assertEquals(0B1011, HandUtil.getSuitsForFullHouse(cards));

        cards = new ArrayList<>();
        cards.add(new Card(Suit.DIAMOND, 8));
        cards.add(new Card(Suit.CLUB, 8));
        cards.add(new Card(Suit.SPADE, 10));
        cards.add(new Card(Suit.DIAMOND, 10));
        cards.add(new Card(Suit.HEART, 10));
        assertEquals(0B1101, HandUtil.getSuitsForFullHouse(cards));
    }

    @Test
    void testGetNumberForRank() {
        ArrayList<Card> cards = new ArrayList<>();
        cards.add(new Card(Suit.DIAMOND, 8));
        cards.add(new Card(Suit.DIAMOND, 10));
        cards.add(new Card(Suit.SPADE, 10));
        cards.add(new Card(Suit.HEART, 10));
        cards.add(new Card(Suit.DIAMOND, 11));
        assertEquals(10, HandUtil.getNumberForRank(cards));

        cards = new ArrayList<>();
        cards.add(new Card(Suit.DIAMOND, 8));
        cards.add(new Card(Suit.DIAMOND, 10));
        cards.add(new Card(Suit.SPADE, 10));
        cards.add(new Card(Suit.HEART, 11));
        cards.add(new Card(Suit.DIAMOND, 12));
        assertEquals(10, HandUtil.getNumberForRank(cards));

        cards = new ArrayList<>();
        cards.add(new Card(Suit.DIAMOND, 8));
        cards.add(new Card(Suit.DIAMOND, 10));
        cards.add(new Card(Suit.SPADE, 10));
        cards.add(new Card(Suit.HEART, 11));
        cards.add(new Card(Suit.DIAMOND, 11));
        assertEquals(11, HandUtil.getNumberForRank(cards));
    }

    @Test
    void testGetSuitsForRank2() {
        ArrayList<Card> cards = new ArrayList<>();
        cards.add(new Card(Suit.DIAMOND, 8));
        cards.add(new Card(Suit.DIAMOND, 10));
        cards.add(new Card(Suit.SPADE, 10));
        cards.add(new Card(Suit.HEART, 11));
        cards.add(new Card(Suit.DIAMOND, 12));
        assertEquals(0B1001, HandUtil.getSuitsForRank(cards));

        cards = new ArrayList<>();
        cards.add(new Card(Suit.DIAMOND, 8));
        cards.add(new Card(Suit.DIAMOND, 10));
        cards.add(new Card(Suit.SPADE, 10));
        cards.add(new Card(Suit.HEART, 11));
        cards.add(new Card(Suit.DIAMOND, 11));
        assertEquals(0B0101, HandUtil.getSuitsForRank(cards));
    }
}

