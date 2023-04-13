package com.example.fishing.util;

import com.example.fishing.model.Card;
import com.example.fishing.model.Rank;
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
        return sortedCards.get(0).isNext(sortedCards.get(1))
                && sortedCards.get(1).isNext(sortedCards.get(2))
                && sortedCards.get(2).isNext(sortedCards.get(3))
                && (sortedCards.get(3).isNext(sortedCards.get(4)) || (sortedCards.get(3).getRank() == Rank.FIVE && sortedCards.get(4).getRank() == Rank.ACE));
    }

    public static int getCountOfTopRank(List<Card> sortedCards) {
        Map<Rank, Long> map = sortedCards.stream().collect(Collectors.groupingBy(Card::getRank, Collectors.mapping(Card::getSuit, Collectors.counting())));
        long result = 0;
        for (Map.Entry<Rank, Long> entry: map.entrySet()) {
            if (entry.getValue() > result) {
                result = entry.getValue();
            }
        }
        return (int) result;
    }

    public static int getCountOfSuit(List<Card> sortedCards) {
        return (int) sortedCards.stream().map(Card::getSuit).distinct().count();
    }

    public static int getCountOfRank(List<Card> sortedCards) {
        return (int) sortedCards.stream().map(Card::getRank).distinct().count();
    }

    public static int getFourOfAKindRank(List<Card> sortedCards) {
        if (getCountOfTopRank(sortedCards) != 4) {
            return -1;
        }
        for (int i=0; i<sortedCards.size()-1; i++) {
            if (sortedCards.get(i).getRank() == sortedCards.get(i+1).getRank()) {
                return sortedCards.get(i).getRank().getValue();
            }
        }
        return -1;
    }

    public static int getSuitsForFullHouse(List<Card> sortedCards) {
        int suits = 0;
        if (sortedCards.get(1).getRank() == sortedCards.get(2).getRank()) {
            suits += 1 << sortedCards.get(0).getSuit().getValue();
            suits += 1 << sortedCards.get(1).getSuit().getValue();
        } else {
            suits += 1 << sortedCards.get(3).getSuit().getValue();
            suits += 1 << sortedCards.get(4).getSuit().getValue();
        }
        suits += 1 << sortedCards.get(2).getSuit().getValue();
        return suits;
    }

    public static int getRankNumber(List<Card> sortedCards) {
        for (int i=4; i>0; i--) {
            if (sortedCards.get(i).getRank() == sortedCards.get(i-1).getRank()) {
                return sortedCards.get(i).getRank().getValue();
            }
        }
        return -1;
    }

    public static int getSuitsForRank(List<Card> sortedCards) {
        for (int i=4; i>0; i--) {
            if (sortedCards.get(i).getRank() == sortedCards.get(i-1).getRank()) {
                int suits = 0;
                suits += 1 << sortedCards.get(i).getSuit().getValue();
                suits += 1 << sortedCards.get(i-1).getSuit().getValue();
                return suits;
            }
        }
        return 0;
    }
}
