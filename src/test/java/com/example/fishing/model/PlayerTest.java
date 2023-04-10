package com.example.fishing.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class PlayerTest {
    @Test
    void testConstructor() {
        Player actualPlayer = new Player("Name");
        assertEquals(0, actualPlayer.getTotalWins());
        assertEquals("Name", actualPlayer.getName());
    }

    @Test
    void testWinRound() {
        Player player = new Player("Name");
        player.winRound(1);
        assertEquals(1, player.getTotalWins());
    }

    @Test
    void testAssignCard() {
        Hand mockHand = mock(Hand.class);
        doNothing().when(mockHand).assignCard(any());

        Player player = new Player("Name");
        player.setCurrentHand(mockHand);
        Card card = new Card(Suit.DIAMOND, 10);
        player.assignCard(card);
        verify(mockHand).assignCard(card);
    }

    @Test
    void testGetTotalWins() {
        assertEquals(0, (new Player("Name")).getTotalWins());
    }

    @Test
    void testCalculate() {
        Hand currentHand = mock(Hand.class);
        doNothing().when(currentHand).calculate();

        Player player = new Player("Name");
        player.setCurrentHand(currentHand);
        player.calculate();
        verify(currentHand).calculate();
    }
}

