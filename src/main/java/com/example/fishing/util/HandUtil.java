package com.example.fishing.util;

import com.example.fishing.model.Card;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class HandUtil {
    public static boolean isSequence(List<Card> sortedCards) {
        if (sortedCards.size() != 5) {
            return false;
        }
        return sortedCards.get(0).getNumber() + 1 == sortedCards.get(1).getNumber()
                && sortedCards.get(1).getNumber() + 1 == sortedCards.get(2).getNumber()
                && sortedCards.get(2).getNumber() + 1 == sortedCards.get(3).getNumber()
                && (sortedCards.get(3).getNumber() + 1 == sortedCards.get(4).getNumber() || (sortedCards.get(3).getNumber() == 5 && sortedCards.get(4).getNumber() == 14));
    }

    public static int getCountOfTopNumber(List<Card> sortedCards) {
        Map<Integer, Long> map = sortedCards.stream().collect(Collectors.groupingBy(Card::getNumber, Collectors.mapping(Card::getSuit, Collectors.counting())));
        long result = 0;
        for (Map.Entry<Integer, Long> entry: map.entrySet()) {
            if (entry.getValue() > result) {
                result = entry.getValue();
            }
        }
        return (int) result;
    }

    public static int getCountOfSuit(List<Card> sortedCards) {
        return (int) sortedCards.stream().map(Card::getSuit).distinct().count();
    }

    public static int getCountOfNumber(List<Card> sortedCards) {
        return (int) sortedCards.stream().map(Card::getNumber).distinct().count();
    }

    public static int getFourOfAKindNumber(List<Card> sortedCards) {
        if (getCountOfTopNumber(sortedCards) != 4) {
            return -1;
        }
        for (int i=0; i<sortedCards.size()-1; i++) {
            if (sortedCards.get(i).getNumber() == sortedCards.get(i+1).getNumber()) {
                return sortedCards.get(i).getNumber();
            }
        }
        return -1;
    }

    public static int getSuitsForFullHouse(List<Card> sortedCards) {
        int suits = 0;
        if (sortedCards.get(1).getNumber() == sortedCards.get(2).getNumber()) {
            suits += 1 << sortedCards.get(0).getSuit().getValue();
            suits += 1 << sortedCards.get(1).getSuit().getValue();
        } else {
            suits += 1 << sortedCards.get(3).getSuit().getValue();
            suits += 1 << sortedCards.get(4).getSuit().getValue();
        }
        suits += 1 << sortedCards.get(2).getSuit().getValue();
        return suits;
    }

    public static int getNumberForRank(List<Card> sortedCards) {
        for (int i=4; i>0; i--) {
            if (sortedCards.get(i).getNumber() == sortedCards.get(i-1).getNumber()) {
                return sortedCards.get(i).getNumber();
            }
        }
        return -1;
    }

    public static int getSuitsForRank(List<Card> sortedCards) {
        for (int i=4; i>0; i--) {
            if (sortedCards.get(i).getNumber() == sortedCards.get(i-1).getNumber()) {
                int suits = 0;
                suits += 1 << sortedCards.get(i).getSuit().getValue();
                suits += 1 << sortedCards.get(i-1).getSuit().getValue();
                return suits;
            }
        }
        return 0;
    }
}
