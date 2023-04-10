package com.example.fishing.model;

import com.google.common.annotations.VisibleForTesting;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

import static com.example.fishing.util.HandUtil.*;
import static com.example.fishing.util.RankUtil.getCardNumber;

@Slf4j
@Getter
@Setter
public class Hand implements Comparable<Hand> {
    private final List<Card> cards;
    private Rank rank;
    // bits indicate suits used to compare
    private int firstComparator;
    // number used to compare
    private int secondComparator;
    private boolean isSuitMoreImportant = true;

    public Hand() {
        cards = new ArrayList<>();
    }

    public void assignCard(Card card) {
        if (cards.size() == 5) {
            log.warn("Player already has 5 cards.");
            return;
        }
        cards.add(card);
    }

    public void calculate() {
        Collections.sort(cards);
        if (getCountOfSuit(cards) == 1 && isSequence(cards)) {
            if (cards.get(0).getNumber() == 10) {
                rank = Rank.ROYAL_FLUSH;
            } else {
                rank = Rank.STRAIGHT_FLUSH;
                if (cards.get(4).getNumber() == 14 && cards.get(3).getNumber() == 5) {
                    secondComparator = cards.get(3).getNumber();
                } else {
                    secondComparator = cards.get(4).getNumber();
                }
            }
            firstComparator = 1 << cards.get(0).getSuit().getValue();
            isSuitMoreImportant = true;
        } else if (getCountOfTopNumber(cards) == 4) {
            rank = Rank.FOUR_OF_A_KIND;
            firstComparator = getFourOfAKindNumber(cards);
            isSuitMoreImportant = false;
        } else if (getCountOfNumber(cards) == 2) {
            rank = Rank.FULL_HOUSE;
            firstComparator = getSuitsForFullHouse(cards);
            secondComparator = cards.get(2).getNumber();
            isSuitMoreImportant = true;
        } else if (getCountOfSuit(cards) == 1) {
            rank = Rank.FLUSH;
            firstComparator = 1 << cards.get(0).getSuit().getValue();
            secondComparator = cards.get(4).getNumber();
            isSuitMoreImportant = true;
        } else if (isSequence(cards)) {
            rank = Rank.STRAIGHT;
            if (cards.get(4).getNumber() == 14 && cards.get(3).getNumber() == 5) {
                firstComparator= cards.get(3).getNumber();
            } else {
                firstComparator= cards.get(4).getNumber();
            }
            secondComparator = 1 << cards.get(4).getSuit().getValue();
            isSuitMoreImportant = false;
        } else if (getCountOfTopNumber(cards) == 3) {
            rank = Rank.THREE_OF_A_KIND;
            firstComparator = getNumberForRank(cards);
            isSuitMoreImportant = false;
        } else if (getCountOfNumber(cards) == 3) {
            rank = Rank.TWO_PAIRS;
            firstComparator = getNumberForRank(cards);
            secondComparator = getSuitsForRank(cards);
            isSuitMoreImportant = false;
        } else if (getCountOfNumber(cards) == 4) {
            rank = Rank.ONE_PAIR;
            firstComparator = getNumberForRank(cards);
            secondComparator = getSuitsForRank(cards);
            isSuitMoreImportant = false;
        } else {
            rank = Rank.NOTHING;
            // todo does the highest rank means all cards or only the top one card
            firstComparator = cards.get(4).getNumber();
            secondComparator = 1 << cards.get(4).getSuit().getValue();
            isSuitMoreImportant = false;
        }
    }

    public String getDescription() {
        StringBuilder sb = new StringBuilder();
        if (firstComparator > 0) {
            if (isSuitMoreImportant) {
                sb.append("suit ").append(convertToSuit(firstComparator));
            } else {
                sb.append("rank ").append(getCardNumber(firstComparator));
            }
        }
        if (secondComparator > 0) {
            if (sb.length() > 0) {
                sb.append(" and ");
            }
            if (isSuitMoreImportant) {
                sb.append("rank ").append(getCardNumber(secondComparator));
            } else {
                sb.append("suit ").append(convertToSuit(secondComparator));
            }
        }
        return sb.toString();
    }

    private String convertToSuit(int input) {
        int suit = 0;
        while (input > 0) {
            input = input >> 1;
            suit++;
        }
        return Objects.requireNonNull(Suit.fromValue(suit-1)).getShape();
    }

    @Override
    public int compareTo(Hand other) {
        if (this.rank == other.rank) {
            return this.firstComparator == other.firstComparator ? this.secondComparator - other.secondComparator : this.firstComparator - other.firstComparator;
        } else {
            return this.rank.getValue() - other.rank.getValue();
        }
    }
}
