package com.example.fishing.model;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HandTest {
    @Test
    void testConstructor() {
        Hand actualHand = new Hand();
        assertEquals("", actualHand.getDescription());
        assertNull(actualHand.getRank());
    }

    @Test
    void testAssignCard() {
        Hand hand = new Hand();
        hand.assignCard(new Card(Suit.DIAMOND, 10));
        assertEquals(1, hand.getCards().size());
        hand.assignCard(new Card(Suit.DIAMOND, 10));
        hand.assignCard(new Card(Suit.DIAMOND, 10));
        hand.assignCard(new Card(Suit.DIAMOND, 10));
        hand.assignCard(new Card(Suit.DIAMOND, 10));
        assertEquals(5, hand.getCards().size());
        hand.assignCard(new Card(Suit.DIAMOND, 10));
        assertEquals(5, hand.getCards().size());
    }

    @Test
    void testCalculate() {
        Hand hand = new Hand();
        hand.assignCard(new Card(Suit.SPADE, 11));
        hand.assignCard(new Card(Suit.SPADE, 12));
        hand.assignCard(new Card(Suit.SPADE, 13));
        hand.assignCard(new Card(Suit.SPADE, 14));
        hand.assignCard(new Card(Suit.SPADE, 10));
        hand.calculate();
        assertEquals(Rank.ROYAL_FLUSH, hand.getRank());
        assertEquals(0B1000, hand.getFirstComparator());
        assertTrue(hand.isSuitMoreImportant());

        hand = new Hand();
        hand.assignCard(new Card(Suit.HEART, 11));
        hand.assignCard(new Card(Suit.HEART, 7));
        hand.assignCard(new Card(Suit.HEART, 8));
        hand.assignCard(new Card(Suit.HEART, 9));
        hand.assignCard(new Card(Suit.HEART, 10));
        hand.calculate();
        assertEquals(Rank.STRAIGHT_FLUSH, hand.getRank());
        assertEquals(0B0100, hand.getFirstComparator());
        assertTrue(hand.isSuitMoreImportant());

        hand = new Hand();
        hand.assignCard(new Card(Suit.DIAMOND, 4));
        hand.assignCard(new Card(Suit.DIAMOND, 3));
        hand.assignCard(new Card(Suit.DIAMOND, 2));
        hand.assignCard(new Card(Suit.DIAMOND, 5));
        hand.assignCard(new Card(Suit.DIAMOND, 14));
        hand.calculate();
        assertEquals(Rank.STRAIGHT_FLUSH, hand.getRank());
        assertEquals(0B0001, hand.getFirstComparator());
        assertTrue(hand.isSuitMoreImportant());

        hand = new Hand();
        hand.assignCard(new Card(Suit.HEART, 4));
        hand.assignCard(new Card(Suit.SPADE, 4));
        hand.assignCard(new Card(Suit.CLUB, 4));
        hand.assignCard(new Card(Suit.DIAMOND, 4));
        hand.assignCard(new Card(Suit.DIAMOND, 3));
        hand.calculate();
        assertEquals(Rank.FOUR_OF_A_KIND, hand.getRank());
        assertEquals(4, hand.getFirstComparator());
        assertFalse(hand.isSuitMoreImportant());

        hand = new Hand();
        hand.assignCard(new Card(Suit.HEART, 4));
        hand.assignCard(new Card(Suit.SPADE, 4));
        hand.assignCard(new Card(Suit.CLUB, 4));
        hand.assignCard(new Card(Suit.CLUB, 3));
        hand.assignCard(new Card(Suit.DIAMOND, 3));
        hand.calculate();
        assertEquals(Rank.FULL_HOUSE, hand.getRank());
        assertEquals(0B1110, hand.getFirstComparator());
        assertEquals(4, hand.getSecondComparator());
        assertTrue(hand.isSuitMoreImportant());

        hand = new Hand();
        hand.assignCard(new Card(Suit.HEART, 4));
        hand.assignCard(new Card(Suit.HEART, 2));
        hand.assignCard(new Card(Suit.HEART, 7));
        hand.assignCard(new Card(Suit.HEART, 5));
        hand.assignCard(new Card(Suit.HEART, 3));
        hand.calculate();
        assertEquals(Rank.FLUSH, hand.getRank());
        assertEquals(0B0100, hand.getFirstComparator());
        assertEquals(7, hand.getSecondComparator());
        assertTrue(hand.isSuitMoreImportant());

        hand = new Hand();
        hand.assignCard(new Card(Suit.HEART, 3));
        hand.assignCard(new Card(Suit.DIAMOND, 5));
        hand.assignCard(new Card(Suit.HEART, 4));
        hand.assignCard(new Card(Suit.SPADE, 6));
        hand.assignCard(new Card(Suit.CLUB, 7));
        hand.calculate();
        assertEquals(Rank.STRAIGHT, hand.getRank());
        assertEquals(7, hand.getFirstComparator());
        assertEquals(0B0010, hand.getSecondComparator());
        assertFalse(hand.isSuitMoreImportant());

        hand = new Hand();
        hand.assignCard(new Card(Suit.HEART, 3));
        hand.assignCard(new Card(Suit.DIAMOND, 5));
        hand.assignCard(new Card(Suit.HEART, 4));
        hand.assignCard(new Card(Suit.SPADE, 14));
        hand.assignCard(new Card(Suit.CLUB, 2));
        hand.calculate();
        assertEquals(Rank.STRAIGHT, hand.getRank());
        assertEquals(5, hand.getFirstComparator());
        assertEquals(0B1000, hand.getSecondComparator());
        assertFalse(hand.isSuitMoreImportant());

        hand = new Hand();
        hand.assignCard(new Card(Suit.HEART, 3));
        hand.assignCard(new Card(Suit.DIAMOND, 3));
        hand.assignCard(new Card(Suit.HEART, 4));
        hand.assignCard(new Card(Suit.SPADE, 3));
        hand.assignCard(new Card(Suit.CLUB, 2));
        hand.calculate();
        assertEquals(Rank.THREE_OF_A_KIND, hand.getRank());
        assertEquals(3, hand.getFirstComparator());
        assertFalse(hand.isSuitMoreImportant());

        hand = new Hand();
        hand.assignCard(new Card(Suit.HEART, 3));
        hand.assignCard(new Card(Suit.DIAMOND, 3));
        hand.assignCard(new Card(Suit.HEART, 4));
        hand.assignCard(new Card(Suit.SPADE, 4));
        hand.assignCard(new Card(Suit.CLUB, 2));
        hand.calculate();
        assertEquals(Rank.TWO_PAIRS, hand.getRank());
        assertEquals(4, hand.getFirstComparator());
        assertEquals(0B1100, hand.getSecondComparator());
        assertFalse(hand.isSuitMoreImportant());

        hand = new Hand();
        hand.assignCard(new Card(Suit.HEART, 3));
        hand.assignCard(new Card(Suit.DIAMOND, 5));
        hand.assignCard(new Card(Suit.HEART, 4));
        hand.assignCard(new Card(Suit.SPADE, 4));
        hand.assignCard(new Card(Suit.CLUB, 2));
        hand.calculate();
        assertEquals(Rank.ONE_PAIR, hand.getRank());
        assertEquals(4, hand.getFirstComparator());
        assertEquals(0B1100, hand.getSecondComparator());
        assertFalse(hand.isSuitMoreImportant());

        hand = new Hand();
        hand.assignCard(new Card(Suit.HEART, 3));
        hand.assignCard(new Card(Suit.DIAMOND, 5));
        hand.assignCard(new Card(Suit.HEART, 4));
        hand.assignCard(new Card(Suit.SPADE, 7));
        hand.assignCard(new Card(Suit.CLUB, 2));
        hand.calculate();
        assertEquals(Rank.NOTHING, hand.getRank());
        assertEquals(7, hand.getFirstComparator());
        assertEquals(0B1000, hand.getSecondComparator());
        assertFalse(hand.isSuitMoreImportant());
    }

    @Test
    void testGetDescription() {
        assertEquals("", (new Hand()).getDescription());

        Hand hand = new Hand();
        hand.setFirstComparator(10);
        hand.setSuitMoreImportant(false);
        assertEquals("rank 10", hand.getDescription());

        hand = new Hand();
        hand.setFirstComparator(0B0010);
        hand.setSuitMoreImportant(true);
        assertEquals("suit ♣", hand.getDescription());

        hand = new Hand();
        hand.setFirstComparator(14);
        hand.setSecondComparator(0B1000);
        hand.setSuitMoreImportant(false);
        assertEquals("rank A and suit ♠", hand.getDescription());

        hand = new Hand();
        hand.setFirstComparator(0B0100);
        hand.setSecondComparator(10);
        hand.setSuitMoreImportant(true);
        assertEquals("suit ♥ and rank 10", hand.getDescription());
    }

    @Test
    void testCompareTo() {
        Hand hand = new Hand();
        hand.setRank(Rank.ROYAL_FLUSH);
        Hand other = new Hand();
        other.setRank(Rank.STRAIGHT);
        assertTrue(hand.compareTo(other) > 0);

        hand.setRank(Rank.FOUR_OF_A_KIND);
        hand.setFirstComparator(12);
        other.setRank(Rank.FOUR_OF_A_KIND);
        other.setFirstComparator(11);
        assertTrue(hand.compareTo(other) > 0);

        hand.setRank(Rank.FULL_HOUSE);
        hand.setFirstComparator(0B1000);
        hand.setSecondComparator(14);
        other.setRank(Rank.FULL_HOUSE);
        other.setFirstComparator(0B1000);
        other.setSecondComparator(13);
        assertTrue(hand.compareTo(other) > 0);

        hand.setRank(Rank.TWO_PAIRS);
        hand.setFirstComparator(0B1000);
        hand.setSecondComparator(14);
        other.setRank(Rank.TWO_PAIRS);
        other.setFirstComparator(0B1000);
        other.setSecondComparator(14);
        assertEquals(0, hand.compareTo(other));
    }
}

