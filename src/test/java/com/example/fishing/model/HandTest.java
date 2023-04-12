package com.example.fishing.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HandTest {
    @Test
    void testConstructor() {
        Hand actualHand = new Hand();
        assertEquals("", actualHand.getDescription());
        assertNull(actualHand.getDeckRank());
    }

    @Test
    void testAssignCard() {
        Hand hand = new Hand();
        hand.assignCard(new Card(Suit.DIAMOND, Rank.TEN));
        assertEquals(1, hand.getCards().size());
        hand.assignCard(new Card(Suit.DIAMOND, Rank.TEN));
        hand.assignCard(new Card(Suit.DIAMOND, Rank.TEN));
        hand.assignCard(new Card(Suit.DIAMOND, Rank.TEN));
        hand.assignCard(new Card(Suit.DIAMOND, Rank.TEN));
        assertEquals(5, hand.getCards().size());
        hand.assignCard(new Card(Suit.DIAMOND, Rank.TEN));
        assertEquals(5, hand.getCards().size());
    }

    @Test
    void testCalculate() {
        Hand hand = new Hand();
        hand.assignCard(new Card(Suit.SPADE, Rank.JACK));
        hand.assignCard(new Card(Suit.SPADE, Rank.QUEEN));
        hand.assignCard(new Card(Suit.SPADE, Rank.KING));
        hand.assignCard(new Card(Suit.SPADE, Rank.ACE));
        hand.assignCard(new Card(Suit.SPADE, Rank.TEN));
        hand.calculate();
        assertEquals(DeckRank.ROYAL_FLUSH, hand.getDeckRank());
        assertEquals(0B1000, hand.getFirstComparator());
        assertTrue(hand.isSuitMoreImportant());

        hand = new Hand();
        hand.assignCard(new Card(Suit.HEART, Rank.JACK));
        hand.assignCard(new Card(Suit.HEART, Rank.SEVEN));
        hand.assignCard(new Card(Suit.HEART, Rank.EIGHT));
        hand.assignCard(new Card(Suit.HEART, Rank.NINE));
        hand.assignCard(new Card(Suit.HEART, Rank.TEN));
        hand.calculate();
        assertEquals(DeckRank.STRAIGHT_FLUSH, hand.getDeckRank());
        assertEquals(0B0100, hand.getFirstComparator());
        assertTrue(hand.isSuitMoreImportant());

        hand = new Hand();
        hand.assignCard(new Card(Suit.DIAMOND, Rank.FOUR));
        hand.assignCard(new Card(Suit.DIAMOND, Rank.THREE));
        hand.assignCard(new Card(Suit.DIAMOND, Rank.TWO));
        hand.assignCard(new Card(Suit.DIAMOND, Rank.FIVE));
        hand.assignCard(new Card(Suit.DIAMOND, Rank.ACE));
        hand.calculate();
        assertEquals(DeckRank.STRAIGHT_FLUSH, hand.getDeckRank());
        assertEquals(0B0001, hand.getFirstComparator());
        assertTrue(hand.isSuitMoreImportant());

        hand = new Hand();
        hand.assignCard(new Card(Suit.HEART, Rank.FOUR));
        hand.assignCard(new Card(Suit.SPADE, Rank.FOUR));
        hand.assignCard(new Card(Suit.CLUB, Rank.FOUR));
        hand.assignCard(new Card(Suit.DIAMOND, Rank.FOUR));
        hand.assignCard(new Card(Suit.DIAMOND, Rank.THREE));
        hand.calculate();
        assertEquals(DeckRank.FOUR_OF_A_KIND, hand.getDeckRank());
        assertEquals(4, hand.getFirstComparator());
        assertFalse(hand.isSuitMoreImportant());

        hand = new Hand();
        hand.assignCard(new Card(Suit.HEART, Rank.FOUR));
        hand.assignCard(new Card(Suit.SPADE, Rank.FOUR));
        hand.assignCard(new Card(Suit.CLUB, Rank.FOUR));
        hand.assignCard(new Card(Suit.CLUB, Rank.THREE));
        hand.assignCard(new Card(Suit.DIAMOND, Rank.THREE));
        hand.calculate();
        assertEquals(DeckRank.FULL_HOUSE, hand.getDeckRank());
        assertEquals(0B1110, hand.getFirstComparator());
        assertEquals(4, hand.getSecondComparator());
        assertTrue(hand.isSuitMoreImportant());

        hand = new Hand();
        hand.assignCard(new Card(Suit.HEART, Rank.FOUR));
        hand.assignCard(new Card(Suit.HEART, Rank.TWO));
        hand.assignCard(new Card(Suit.HEART, Rank.SEVEN));
        hand.assignCard(new Card(Suit.HEART, Rank.FIVE));
        hand.assignCard(new Card(Suit.HEART, Rank.THREE));
        hand.calculate();
        assertEquals(DeckRank.FLUSH, hand.getDeckRank());
        assertEquals(0B0100, hand.getFirstComparator());
        assertEquals(7, hand.getSecondComparator());
        assertTrue(hand.isSuitMoreImportant());

        hand = new Hand();
        hand.assignCard(new Card(Suit.HEART, Rank.THREE));
        hand.assignCard(new Card(Suit.DIAMOND, Rank.FIVE));
        hand.assignCard(new Card(Suit.HEART, Rank.FOUR));
        hand.assignCard(new Card(Suit.SPADE, Rank.SIX));
        hand.assignCard(new Card(Suit.CLUB, Rank.SEVEN));
        hand.calculate();
        assertEquals(DeckRank.STRAIGHT, hand.getDeckRank());
        assertEquals(7, hand.getFirstComparator());
        assertEquals(0B0010, hand.getSecondComparator());
        assertFalse(hand.isSuitMoreImportant());

        hand = new Hand();
        hand.assignCard(new Card(Suit.HEART, Rank.THREE));
        hand.assignCard(new Card(Suit.DIAMOND, Rank.FIVE));
        hand.assignCard(new Card(Suit.HEART, Rank.FOUR));
        hand.assignCard(new Card(Suit.SPADE, Rank.ACE));
        hand.assignCard(new Card(Suit.CLUB, Rank.TWO));
        hand.calculate();
        assertEquals(DeckRank.STRAIGHT, hand.getDeckRank());
        assertEquals(5, hand.getFirstComparator());
        assertEquals(0B1000, hand.getSecondComparator());
        assertFalse(hand.isSuitMoreImportant());

        hand = new Hand();
        hand.assignCard(new Card(Suit.HEART, Rank.THREE));
        hand.assignCard(new Card(Suit.DIAMOND, Rank.THREE));
        hand.assignCard(new Card(Suit.HEART, Rank.FOUR));
        hand.assignCard(new Card(Suit.SPADE, Rank.THREE));
        hand.assignCard(new Card(Suit.CLUB, Rank.TWO));
        hand.calculate();
        assertEquals(DeckRank.THREE_OF_A_KIND, hand.getDeckRank());
        assertEquals(3, hand.getFirstComparator());
        assertFalse(hand.isSuitMoreImportant());

        hand = new Hand();
        hand.assignCard(new Card(Suit.HEART, Rank.THREE));
        hand.assignCard(new Card(Suit.DIAMOND, Rank.THREE));
        hand.assignCard(new Card(Suit.HEART, Rank.FOUR));
        hand.assignCard(new Card(Suit.SPADE, Rank.FOUR));
        hand.assignCard(new Card(Suit.CLUB, Rank.TWO));
        hand.calculate();
        assertEquals(DeckRank.TWO_PAIRS, hand.getDeckRank());
        assertEquals(4, hand.getFirstComparator());
        assertEquals(0B1100, hand.getSecondComparator());
        assertFalse(hand.isSuitMoreImportant());

        hand = new Hand();
        hand.assignCard(new Card(Suit.HEART, Rank.THREE));
        hand.assignCard(new Card(Suit.DIAMOND, Rank.FIVE));
        hand.assignCard(new Card(Suit.HEART, Rank.FOUR));
        hand.assignCard(new Card(Suit.SPADE, Rank.FOUR));
        hand.assignCard(new Card(Suit.CLUB, Rank.TWO));
        hand.calculate();
        assertEquals(DeckRank.ONE_PAIR, hand.getDeckRank());
        assertEquals(4, hand.getFirstComparator());
        assertEquals(0B1100, hand.getSecondComparator());
        assertFalse(hand.isSuitMoreImportant());

        hand = new Hand();
        hand.assignCard(new Card(Suit.HEART, Rank.THREE));
        hand.assignCard(new Card(Suit.DIAMOND, Rank.FIVE));
        hand.assignCard(new Card(Suit.HEART, Rank.FOUR));
        hand.assignCard(new Card(Suit.SPADE, Rank.SEVEN));
        hand.assignCard(new Card(Suit.CLUB, Rank.TWO));
        hand.calculate();
        assertEquals(DeckRank.NOTHING, hand.getDeckRank());
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
        hand.setDeckRank(DeckRank.ROYAL_FLUSH);
        Hand other = new Hand();
        other.setDeckRank(DeckRank.STRAIGHT);
        assertTrue(hand.compareTo(other) > 0);

        hand.setDeckRank(DeckRank.FOUR_OF_A_KIND);
        hand.setFirstComparator(12);
        other.setDeckRank(DeckRank.FOUR_OF_A_KIND);
        other.setFirstComparator(11);
        assertTrue(hand.compareTo(other) > 0);

        hand.setDeckRank(DeckRank.FULL_HOUSE);
        hand.setFirstComparator(0B1000);
        hand.setSecondComparator(14);
        other.setDeckRank(DeckRank.FULL_HOUSE);
        other.setFirstComparator(0B1000);
        other.setSecondComparator(13);
        assertTrue(hand.compareTo(other) > 0);

        hand.setDeckRank(DeckRank.TWO_PAIRS);
        hand.setFirstComparator(0B1000);
        hand.setSecondComparator(14);
        other.setDeckRank(DeckRank.TWO_PAIRS);
        other.setFirstComparator(0B1000);
        other.setSecondComparator(14);
        assertEquals(0, hand.compareTo(other));
    }
}

