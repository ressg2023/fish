package com.example.fishing.model;

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
    private DeckRank deckRank;
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
            if (cards.get(0).getRank() == Rank.TEN) {
                deckRank = DeckRank.ROYAL_FLUSH;
            } else {
                deckRank = DeckRank.STRAIGHT_FLUSH;
                if (cards.get(4).getRank() == Rank.ACE && cards.get(3).getRank() == Rank.FIVE) {
                    secondComparator = cards.get(3).getRank().getValue();
                } else {
                    secondComparator = cards.get(4).getRank().getValue();
                }
            }
            firstComparator = 1 << cards.get(0).getSuit().getValue();
            isSuitMoreImportant = true;
        } else if (getCountOfTopRank(cards) == 4) {
            deckRank = DeckRank.FOUR_OF_A_KIND;
            firstComparator = getFourOfAKindRank(cards);
            isSuitMoreImportant = false;
        } else if (getCountOfRank(cards) == 2) {
            deckRank = DeckRank.FULL_HOUSE;
            firstComparator = getSuitsForFullHouse(cards);
            secondComparator = cards.get(2).getRank().getValue();
            isSuitMoreImportant = true;
        } else if (getCountOfSuit(cards) == 1) {
            deckRank = DeckRank.FLUSH;
            firstComparator = 1 << cards.get(0).getSuit().getValue();
            secondComparator = cards.get(4).getRank().getValue();
            isSuitMoreImportant = true;
        } else if (isSequence(cards)) {
            deckRank = DeckRank.STRAIGHT;
            if (cards.get(4).getRank() == Rank.ACE && cards.get(3).getRank() == Rank.FIVE) {
                firstComparator= cards.get(3).getRank().getValue();
            } else {
                firstComparator= cards.get(4).getRank().getValue();
            }
            secondComparator = 1 << cards.get(4).getSuit().getValue();
            isSuitMoreImportant = false;
        } else if (getCountOfTopRank(cards) == 3) {
            deckRank = DeckRank.THREE_OF_A_KIND;
            firstComparator = getRankNumber(cards);
            isSuitMoreImportant = false;
        } else if (getCountOfRank(cards) == 3) {
            deckRank = DeckRank.TWO_PAIRS;
            firstComparator = getRankNumber(cards);
            secondComparator = getSuitsForRank(cards);
            isSuitMoreImportant = false;
        } else if (getCountOfRank(cards) == 4) {
            deckRank = DeckRank.ONE_PAIR;
            firstComparator = getRankNumber(cards);
            secondComparator = getSuitsForRank(cards);
            isSuitMoreImportant = false;
        } else {
            deckRank = DeckRank.NOTHING;
            // todo does the highest rank means all cards or only the top one card
            firstComparator = cards.get(4).getRank().getValue();
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
        if (this.deckRank == other.deckRank) {
            return this.firstComparator == other.firstComparator ? this.secondComparator - other.secondComparator : this.firstComparator - other.firstComparator;
        } else {
            return this.deckRank.getValue() - other.deckRank.getValue();
        }
    }
}
