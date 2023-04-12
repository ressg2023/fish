package com.example.fishing.util;

import com.example.fishing.model.Card;
import com.example.fishing.model.Rank;
import com.example.fishing.model.Suit;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HandUtilTest {
    @Test
    void testIsSequence() {
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(Suit.SPADE, Rank.EIGHT));
        cards.add(new Card(Suit.DIAMOND, Rank.TEN));
        cards.add(new Card(Suit.CLUB, Rank.JACK));
        cards.add(new Card(Suit.HEART, Rank.QUEEN));
        cards.add(new Card(Suit.SPADE, Rank.KING));
        assertFalse(HandUtil.isSequence(cards));

        cards = new ArrayList<>();
        cards.add(new Card(Suit.SPADE, Rank.NINE));
        cards.add(new Card(Suit.DIAMOND, Rank.TEN));
        cards.add(new Card(Suit.CLUB, Rank.JACK));
        cards.add(new Card(Suit.HEART, Rank.QUEEN));
        cards.add(new Card(Suit.SPADE, Rank.KING));
        assertTrue(HandUtil.isSequence(cards));

        cards = new ArrayList<>();
        cards.add(new Card(Suit.DIAMOND, Rank.TEN));
        cards.add(new Card(Suit.CLUB, Rank.JACK));
        cards.add(new Card(Suit.HEART, Rank.QUEEN));
        cards.add(new Card(Suit.SPADE, Rank.KING));
        cards.add(new Card(Suit.SPADE, Rank.ACE));
        assertTrue(HandUtil.isSequence(cards));

        cards = new ArrayList<>();
        cards.add(new Card(Suit.DIAMOND, Rank.TWO));
        cards.add(new Card(Suit.CLUB, Rank.THREE));
        cards.add(new Card(Suit.HEART, Rank.FOUR));
        cards.add(new Card(Suit.SPADE, Rank.FIVE));
        cards.add(new Card(Suit.SPADE, Rank.ACE));
        assertTrue(HandUtil.isSequence(cards));
    }

    @Test
    void testGetCountOfTopNumber() {
        ArrayList<Card> cards = new ArrayList<>();
        assertEquals(0, HandUtil.getCountOfTopRank(cards));

        cards.add(new Card(Suit.SPADE, Rank.NINE));
        cards.add(new Card(Suit.DIAMOND, Rank.TEN));
        cards.add(new Card(Suit.CLUB, Rank.JACK));
        cards.add(new Card(Suit.HEART, Rank.QUEEN));
        cards.add(new Card(Suit.SPADE, Rank.KING));
        assertEquals(1, HandUtil.getCountOfTopRank(cards));

        cards = new ArrayList<>();
        cards.add(new Card(Suit.SPADE, Rank.TEN));
        cards.add(new Card(Suit.DIAMOND, Rank.TEN));
        cards.add(new Card(Suit.CLUB, Rank.JACK));
        cards.add(new Card(Suit.HEART, Rank.QUEEN));
        cards.add(new Card(Suit.SPADE, Rank.KING));
        assertEquals(2, HandUtil.getCountOfTopRank(cards));

        cards = new ArrayList<>();
        cards.add(new Card(Suit.SPADE, Rank.TEN));
        cards.add(new Card(Suit.DIAMOND, Rank.TEN));
        cards.add(new Card(Suit.CLUB, Rank.TEN));
        cards.add(new Card(Suit.HEART, Rank.QUEEN));
        cards.add(new Card(Suit.SPADE, Rank.QUEEN));
        assertEquals(3, HandUtil.getCountOfTopRank(cards));

        cards = new ArrayList<>();
        cards.add(new Card(Suit.SPADE, Rank.TEN));
        cards.add(new Card(Suit.DIAMOND, Rank.TEN));
        cards.add(new Card(Suit.CLUB, Rank.TEN));
        cards.add(new Card(Suit.HEART, Rank.TEN));
        cards.add(new Card(Suit.SPADE, Rank.QUEEN));
        assertEquals(4, HandUtil.getCountOfTopRank(cards));
    }

    @Test
    void testGetCountOfSuit() {
        ArrayList<Card> cards = new ArrayList<>();
        assertEquals(0, HandUtil.getCountOfSuit(cards));

        cards.add(new Card(Suit.SPADE, Rank.NINE));
        cards.add(new Card(Suit.SPADE, Rank.TEN));
        cards.add(new Card(Suit.SPADE, Rank.JACK));
        cards.add(new Card(Suit.SPADE, Rank.QUEEN));
        cards.add(new Card(Suit.SPADE, Rank.KING));
        assertEquals(1, HandUtil.getCountOfSuit(cards));

        cards = new ArrayList<>();
        cards.add(new Card(Suit.SPADE, Rank.NINE));
        cards.add(new Card(Suit.SPADE, Rank.TEN));
        cards.add(new Card(Suit.SPADE, Rank.JACK));
        cards.add(new Card(Suit.SPADE, Rank.QUEEN));
        cards.add(new Card(Suit.DIAMOND, Rank.KING));
        assertEquals(2, HandUtil.getCountOfSuit(cards));

        cards = new ArrayList<>();
        cards.add(new Card(Suit.SPADE, Rank.NINE));
        cards.add(new Card(Suit.SPADE, Rank.TEN));
        cards.add(new Card(Suit.SPADE, Rank.JACK));
        cards.add(new Card(Suit.HEART, Rank.QUEEN));
        cards.add(new Card(Suit.DIAMOND, Rank.KING));
        assertEquals(3, HandUtil.getCountOfSuit(cards));

        cards = new ArrayList<>();
        cards.add(new Card(Suit.SPADE, Rank.NINE));
        cards.add(new Card(Suit.SPADE, Rank.TEN));
        cards.add(new Card(Suit.CLUB, Rank.JACK));
        cards.add(new Card(Suit.HEART, Rank.QUEEN));
        cards.add(new Card(Suit.DIAMOND, Rank.KING));
        assertEquals(4, HandUtil.getCountOfSuit(cards));
    }

    @Test
    void testGetCountOfNumber() {
        ArrayList<Card> cards = new ArrayList<>();
        assertEquals(0, HandUtil.getCountOfRank(cards));

        cards.add(new Card(Suit.SPADE, Rank.NINE));
        cards.add(new Card(Suit.SPADE, Rank.TEN));
        cards.add(new Card(Suit.CLUB, Rank.TEN));
        cards.add(new Card(Suit.HEART, Rank.TEN));
        cards.add(new Card(Suit.DIAMOND, Rank.TEN));
        assertEquals(2, HandUtil.getCountOfRank(cards));

        cards = new ArrayList<>();
        cards.add(new Card(Suit.SPADE, Rank.NINE));
        cards.add(new Card(Suit.SPADE, Rank.EIGHT));
        cards.add(new Card(Suit.CLUB, Rank.TEN));
        cards.add(new Card(Suit.HEART, Rank.TEN));
        cards.add(new Card(Suit.DIAMOND, Rank.TEN));
        assertEquals(3, HandUtil.getCountOfRank(cards));

        cards = new ArrayList<>();
        cards.add(new Card(Suit.SPADE, Rank.NINE));
        cards.add(new Card(Suit.SPADE, Rank.EIGHT));
        cards.add(new Card(Suit.CLUB, Rank.SEVEN));
        cards.add(new Card(Suit.HEART, Rank.TEN));
        cards.add(new Card(Suit.DIAMOND, Rank.TEN));
        assertEquals(4, HandUtil.getCountOfRank(cards));

        cards = new ArrayList<>();
        cards.add(new Card(Suit.SPADE, Rank.NINE));
        cards.add(new Card(Suit.SPADE, Rank.EIGHT));
        cards.add(new Card(Suit.CLUB, Rank.SEVEN));
        cards.add(new Card(Suit.HEART, Rank.SIX));
        cards.add(new Card(Suit.DIAMOND, Rank.TEN));
        assertEquals(5, HandUtil.getCountOfRank(cards));
    }

    @Test
    void testGetFourOfAKindNumber() {
        ArrayList<Card> cards = new ArrayList<>();
        assertEquals(-1, HandUtil.getFourOfAKindRank(cards));

        cards.add(new Card(Suit.DIAMOND, Rank.TEN));
        cards.add(new Card(Suit.SPADE, Rank.TEN));
        cards.add(new Card(Suit.CLUB, Rank.TEN));
        cards.add(new Card(Suit.HEART, Rank.EIGHT));
        cards.add(new Card(Suit.DIAMOND, Rank.NINE));
        assertEquals(-1, HandUtil.getFourOfAKindRank(cards));

        cards = new ArrayList<>();
        cards.add(new Card(Suit.DIAMOND, Rank.TEN));
        cards.add(new Card(Suit.SPADE, Rank.TEN));
        cards.add(new Card(Suit.CLUB, Rank.TEN));
        cards.add(new Card(Suit.HEART, Rank.TEN));
        cards.add(new Card(Suit.DIAMOND, Rank.NINE));
        assertEquals(10, HandUtil.getFourOfAKindRank(cards));
    }

    @Test
    void testGetSuitsForFullHouse() {
        ArrayList<Card> cards = new ArrayList<>();
        cards.add(new Card(Suit.DIAMOND, Rank.TEN));
        cards.add(new Card(Suit.CLUB, Rank.TEN));
        cards.add(new Card(Suit.SPADE, Rank.TEN));
        cards.add(new Card(Suit.DIAMOND, Rank.JACK));
        cards.add(new Card(Suit.SPADE, Rank.JACK));
        assertEquals(0B1011, HandUtil.getSuitsForFullHouse(cards));

        cards = new ArrayList<>();
        cards.add(new Card(Suit.DIAMOND, Rank.EIGHT));
        cards.add(new Card(Suit.CLUB, Rank.EIGHT));
        cards.add(new Card(Suit.SPADE, Rank.TEN));
        cards.add(new Card(Suit.DIAMOND, Rank.TEN));
        cards.add(new Card(Suit.HEART, Rank.TEN));
        assertEquals(0B1101, HandUtil.getSuitsForFullHouse(cards));
    }

    @Test
    void testGetNumberForRank() {
        ArrayList<Card> cards = new ArrayList<>();
        cards.add(new Card(Suit.DIAMOND, Rank.EIGHT));
        cards.add(new Card(Suit.DIAMOND, Rank.TEN));
        cards.add(new Card(Suit.SPADE, Rank.TEN));
        cards.add(new Card(Suit.HEART, Rank.TEN));
        cards.add(new Card(Suit.DIAMOND, Rank.JACK));
        assertEquals(10, HandUtil.getRankNumber(cards));

        cards = new ArrayList<>();
        cards.add(new Card(Suit.DIAMOND, Rank.EIGHT));
        cards.add(new Card(Suit.DIAMOND, Rank.TEN));
        cards.add(new Card(Suit.SPADE, Rank.TEN));
        cards.add(new Card(Suit.HEART, Rank.JACK));
        cards.add(new Card(Suit.DIAMOND, Rank.QUEEN));
        assertEquals(10, HandUtil.getRankNumber(cards));

        cards = new ArrayList<>();
        cards.add(new Card(Suit.DIAMOND, Rank.EIGHT));
        cards.add(new Card(Suit.DIAMOND, Rank.TEN));
        cards.add(new Card(Suit.SPADE, Rank.TEN));
        cards.add(new Card(Suit.HEART, Rank.JACK));
        cards.add(new Card(Suit.DIAMOND, Rank.JACK));
        assertEquals(11, HandUtil.getRankNumber(cards));
    }

    @Test
    void testGetSuitsForRank2() {
        ArrayList<Card> cards = new ArrayList<>();
        cards.add(new Card(Suit.DIAMOND, Rank.EIGHT));
        cards.add(new Card(Suit.DIAMOND, Rank.TEN));
        cards.add(new Card(Suit.SPADE, Rank.TEN));
        cards.add(new Card(Suit.HEART, Rank.JACK));
        cards.add(new Card(Suit.DIAMOND, Rank.QUEEN));
        assertEquals(0B1001, HandUtil.getSuitsForRank(cards));

        cards = new ArrayList<>();
        cards.add(new Card(Suit.DIAMOND, Rank.EIGHT));
        cards.add(new Card(Suit.DIAMOND, Rank.TEN));
        cards.add(new Card(Suit.SPADE, Rank.TEN));
        cards.add(new Card(Suit.HEART, Rank.JACK));
        cards.add(new Card(Suit.DIAMOND, Rank.JACK));
        assertEquals(0B0101, HandUtil.getSuitsForRank(cards));
    }
}

