package com.example.fishing.model;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class Player {
    @Getter
    private String name;
    private List<Integer> wins;
    @Getter
    private Hand currentHand;

    public Player(String name) {
        this.name = name;
        wins = new ArrayList<>();
        currentHand = new Hand();
    }

    public void winRound(int round) {
        wins.add(round);
    }

    public void resetHand() {
        currentHand = new Hand();
    }

    public void assignCard(Card card) {
        currentHand.assignCard(card);
    }

    public int getTotalWins() {
        return wins.size();
    }

    public void calculateRank() {
        currentHand.calculateRank();
    }
}
