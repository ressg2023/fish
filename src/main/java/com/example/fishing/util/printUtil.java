package com.example.fishing.util;

import com.example.fishing.model.Player;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static java.lang.String.format;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class printUtil {
    public static void printLine(String line, Object... args) {
        System.out.println(format(line, args));
    }

    public static void printString(String str, Object... args) {
        System.out.print(format(str, args));
    }

    public static void printResult(Player[] players) {
        printLine("Game completed. The result is shown below.");
        for (Player player: players) {
            printLine("%s won %d time(s)", player.getName(), player.getTotalWins());
        }
    }
}
