package com.example.fishing.command;

import com.example.fishing.model.Card;
import com.example.fishing.model.Deck;
import com.example.fishing.model.Hand;
import com.example.fishing.model.Player;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Scanner;

import static com.example.fishing.util.printUtil.*;

@ShellComponent
public class CardCommand {
    private Player[] players = null;
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
            String name = scanner.nextLine().substring(0, 10);
            players[i] = new Player(name);
        }

        current = 0;

        deck = new Deck();
        deck.shuffle();

        scanner.close();
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
        int winner = getWinner(players);
        players[winner].winRound(current);
        printLine("Winner of round %d is %s", current+1, players[winner]);

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

    private int getWinner(Player[] players) {
        int winner = -1;
        for (int i=0; i<players.length; i++) {
            players[i].calculateRank();
            if (winner != -1) {
                if (players[i].getCurrentHand() > players[winner].getCurrentHand()) {
                    winner = i;
                }
            } else {
                winner = i;
            }
        }
        return winner;
    }
}
