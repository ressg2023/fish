package com.example.fishing.model;

import com.google.common.annotations.VisibleForTesting;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.stream.Collectors;

import static com.example.fishing.util.RankUtil.getCardNumber;

@Slf4j
public class Hand implements Comparable<Hand> {
    private final Card[] cards;
    private int count;
    @Getter
    private Rank rank;
    // bits indicate suits used to compare
    private int firstComparator;
    // number used to compare
    private int secondComparator;
    private boolean isSuitMoreImportant = true;

    public Hand() {
        cards = new Card[5];
    }

    public void assignCard(Card card) {
        if (count == 5) {
            log.warn("Player already has 5 cards.");
            return;
        }
        cards[count++] = card;
    }

    public void calculate() {
        Arrays.sort(cards);
        if (getCountOfSuit(cards) == 1 && isSequence(cards)) {
            if (cards[0].getNumber() == 10) {
                rank = Rank.ROYAL_FLUSH;
            } else {
                rank = Rank.STRAIGHT_FLUSH;
                if (cards[4].getNumber() == 14 && cards[3].getNumber() == 5) {
                    secondComparator = cards[3].getNumber();
                } else {
                    secondComparator = cards[4].getNumber();
                }
            }
            firstComparator = 1 << cards[0].getSuit().getValue();
            isSuitMoreImportant = true;
        } else if (getCountOfTopNumber(cards) == 4) {
            rank = Rank.FOUR_OF_A_KIND;
            firstComparator = getFourOfAKindNumber(cards);
            isSuitMoreImportant = false;
        } else if (getCountOfNumber(cards) == 2) {
            rank = Rank.FULL_HOUSE;
            firstComparator = getSuitsForFullHouse(cards);
            secondComparator = cards[2].getNumber();
            isSuitMoreImportant = true;
        } else if (getCountOfSuit(cards) == 1) {
            rank = Rank.FLUSH;
            firstComparator = 1 << cards[0].getSuit().getValue();
            secondComparator = cards[4].getNumber();
            isSuitMoreImportant = true;
        } else if (isSequence(cards)) {
            rank = Rank.STRAIGHT;
            if (cards[4].getNumber() == 14 && cards[3].getNumber() == 5) {
                firstComparator= cards[3].getNumber();
            } else {
                firstComparator= cards[4].getNumber();
            }
            secondComparator = 1 << cards[4].getSuit().getValue();
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
            firstComparator = cards[4].getNumber();
            secondComparator = 1 << cards[4].getSuit().getValue();
            isSuitMoreImportant = false;
        }
    }

    @VisibleForTesting
    boolean isSequence(Card[] cards) {
        return cards[0].getNumber() + 1 == cards[1].getNumber()
                && cards[1].getNumber() + 1 == cards[2].getNumber()
                && cards[2].getNumber() + 1 == cards[3].getNumber()
                && (cards[3].getNumber() + 1 == cards[4].getNumber() || (cards[3].getNumber() == 5 && cards[4].getNumber() == 14));
    }

    @VisibleForTesting
    int getCountOfTopNumber(Card[] cards) {
        Map<Integer, Long> map = Arrays.stream(cards).collect(Collectors.groupingBy(Card::getNumber, Collectors.mapping(Card::getSuit, Collectors.counting())));
        long result = 1;
        for (Map.Entry<Integer, Long> entry: map.entrySet()) {
            if (entry.getValue() > result) {
                result = entry.getValue();
            }
        }
        return (int) result;
    }

    @VisibleForTesting
    int getCountOfSuit(Card[] cards) {
        return (int) Arrays.stream(cards).map(Card::getSuit).distinct().count();
    }

    @VisibleForTesting
    int getCountOfNumber(Card[] cards) {
        return (int) Arrays.stream(cards).map(Card::getNumber).distinct().count();
    }

    @VisibleForTesting
    int getFourOfAKindNumber(Card[] cards) {
        for (int i=0; i<cards.length-1; i++) {
            if (cards[i].getNumber() == cards[i+1].getNumber()) {
                return cards[i].getNumber();
            }
        }
        return -1;
    }

    @VisibleForTesting
    int getSuitsForFullHouse(Card[] cards) {
        int suits = 0;
        if (cards[1].getNumber() == cards[2].getNumber()) {
            suits += 1 << cards[0].getSuit().getValue();
            suits += 1 << cards[1].getSuit().getValue();
        } else {
            suits += 1 << cards[3].getSuit().getValue();
            suits += 1 << cards[4].getSuit().getValue();
        }
        suits += 1 << cards[2].getSuit().getValue();
        return suits;
    }

    @VisibleForTesting
    int getNumberForRank(Card[] cards) {
        for (int i=4; i>0; i--) {
            if (cards[i].getNumber() == cards[i-1].getNumber()) {
                return cards[i].getNumber();
            }
        }
        return -1;
    }

    @VisibleForTesting
    int getSuitsForRank(Card[] cards) {
        for (int i=4; i>0; i--) {
            if (cards[i].getNumber() == cards[i-1].getNumber()) {
                int suits = 0;
                suits += 1 << cards[i].getSuit().getValue();
                suits += 1 << cards[i-1].getSuit().getValue();
                return suits;
            }
        }
        return 0;
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
