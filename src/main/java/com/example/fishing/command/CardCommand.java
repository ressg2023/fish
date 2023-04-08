package com.example.fishing.command;

import com.example.fishing.model.Card;
import com.example.fishing.model.Deck;
import com.example.fishing.model.Player;
import com.google.common.annotations.VisibleForTesting;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Scanner;

import static com.example.fishing.util.printUtil.*;

@ShellComponent
public class CardCommand {
    private Player[] players;
    private Deck deck = null;
    private boolean started = false;
    private int total = 0;
    private int current = 0;

    @ShellMethod(key = "start-game", value = "Start a new card game")
    public void startCardGame(
            @ShellOption(value = "-n", defaultValue = "5")
            @Min(value = 2, message = "We need at least 2 players")
            @Max(value = 8, message = "Max players is 8")
            int numberOfPlayers,
            @ShellOption(value = "-r", defaultValue = "5")
            @Min(value = 1, message = "We need to play at least 1 round")
            @Max(value = 10, message = "We will play at most 10 rounds")
            int numberOfRounds) {
        Scanner scanner = new Scanner(System.in);

        if (started) {
            printLine("Game is already started, do you want to reset?");
            printLine("Type 'y' to reset the game, or any other key to continue the current game.");
            String choice = scanner.nextLine();
            if (!"y".equals(choice)) {
                return;
            }
        }

        printLine("Starting %d players game with %d rounds", numberOfPlayers, numberOfRounds);
        total = numberOfRounds;

        // Get players' names
        players = new Player[numberOfPlayers];
        for (int i=0; i<numberOfPlayers; i++) {
            printLine("Please enter player %d's name (name will be truncated to at most 10 characters):", i+1);
            String line = scanner.nextLine();
            String name = line.substring(0, Math.min(10, line.length()));
            players[i] = new Player(name);
        }

        current = 0;

        deck = new Deck();
        deck.shuffle();

        started = true;
    }

    @ShellMethod(key = "start-round", value = "Start the next round of game")
    public void startRound() {
        if (!started) {
            printLine("Game has not started. Please issue a command 'start-game' to start a new game");
            return;
        }

        printLine("Start round %d", current+1);

        deck.shuffle();
        dealCards(deck, players);
        calculateRank(players);
        int winner = getWinner(players);
        players[winner].winRound(current);
        printLine("Winner of round %d is %s with a %s", current+1, players[winner].getName(), players[winner].getCurrentHand().getRank().getName());

        current++;
        if (current == total) {
            printResult(players);
            started = false;
        }
    }

    private void dealCards(Deck deck, Player[] players) {
        for (Player player: players) {
            player.resetHand();
            printString("%-20s", player.getName());
        }
        printLine("");
        for (int i=0; i<5*players.length; i++) {
            Card currentCard = deck.getCard(i);
            players[i%players.length].assignCard(currentCard);
            printString("%-20s", currentCard.toString());
            if (i%players.length == players.length-1) {
                printLine("");
            }
        }
    }

    private void calculateRank(Player[] players) {
        for (Player player : players) {
            player.calculate();
            printLine("%s has %s with %s", player.getName(), player.getCurrentHand().getRank().getName(), player.getCurrentHand().getDescription());
        }
    }

    @VisibleForTesting
    int getWinner(Player[] players) {
        int winner = -1;
        for (int i=0; i<players.length; i++) {
            if (winner != -1) {
                if (players[i].getCurrentHand().compareTo(players[winner].getCurrentHand()) > 0) {
                    winner = i;
                }
            } else {
                winner = i;
            }
        }
        return winner;
    }
}
