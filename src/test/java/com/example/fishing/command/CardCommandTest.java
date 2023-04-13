package com.example.fishing.command;

import com.example.fishing.model.Deck;
import com.example.fishing.model.DeckRank;
import com.example.fishing.model.Player;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CardCommandTest {
    @Test
    void testDealCards() {
        CardCommand cardCommand = new CardCommand();
        cardCommand.players = new Player[] {
                new Player("a"), new Player("b"), new Player("c"), new Player("d"), new Player("c")
        };
        cardCommand.deck = new Deck();
        cardCommand.deck.shuffle();
        for (int i=0; i<cardCommand.players.length; i++) {
            assertEquals(0, cardCommand.players[i].getCurrentHand().getCards().size());
        }
        cardCommand.dealCards();
        for (int i=0; i<cardCommand.players.length; i++) {
            assertEquals(5, cardCommand.players[i].getCurrentHand().getCards().size());
        }
    }

    @Test
    void testGetWinner() {
        CardCommand cardCommand = new CardCommand();
        Player playerA = new Player("a");
        playerA.getCurrentHand().setDeckRank(DeckRank.FULL_HOUSE);
        Player playerB = new Player("b");
        playerB.getCurrentHand().setDeckRank(DeckRank.FLUSH);
        Player playerC = new Player("c");
        playerC.getCurrentHand().setDeckRank(DeckRank.ROYAL_FLUSH);
        Player playerD = new Player("d");
        playerD.getCurrentHand().setDeckRank(DeckRank.TWO_PAIRS);
        Player playerE = new Player("e");
        playerE.getCurrentHand().setDeckRank(DeckRank.THREE_OF_A_KIND);
        cardCommand.players = new Player[]{
                playerA, playerB, playerC, playerD, playerE
        };
        assertEquals(2, cardCommand.getWinner());
    }
}

