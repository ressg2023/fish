package com.example.fishing.model;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class Player {
    @Getter
    private String name;
    private final List<Integer> wins;
    @Getter
    @Setter
    private Hand currentHand;

    public Player(String name) {
        this.name = name;
        wins = new ArrayList<>();
        currentHand = new Hand();
    }

    public void winRound(int round) {
        wins.add(round);
    }

    public void assignCard(Card card) {
        if (currentHand == null) {
            log.warn("Current Hand is empty");
        } else {
            currentHand.assignCard(card);
        }
    }

    public int getTotalWins() {
        return wins.size();
    }

    public void calculate() {
        if (currentHand == null) {
            log.warn("Current Hand is empty");
        } else {
            currentHand.calculate();
        }
    }
}
