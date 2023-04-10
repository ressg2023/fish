package com.example.fishing.util;

import com.example.fishing.model.Player;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PrintUtil {
    public static void printLine(String line, Object... args) {
        System.out.printf((line) + "%n", args);
    }

    public static void printString(String str, Object... args) {
        System.out.printf(str, args);
    }

    public static void printResult(Player[] players) {
        printLine("Game completed. The result is shown below.");
        for (Player player: players) {
            printLine("%s won %d time(s)", player.getName(), player.getTotalWins());
        }
    }
}
