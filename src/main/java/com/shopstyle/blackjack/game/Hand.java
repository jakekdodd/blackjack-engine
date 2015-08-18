package com.shopstyle.blackjack.game;

import com.shopstyle.blackjack.card.Card;
import com.shopstyle.blackjack.card.Rank;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class Hand {

    private final ArrayDeque<Card> cards = new ArrayDeque<>();

    public enum Type {
        SOFT, HARD, PAIR
    }

    public Hand() {
        this(Collections.<Card>emptyList());
    }

    public Hand(Card card) {
        this(Arrays.asList(card));
    }

    public Hand(Collection<Card> cards) {
        this.cards.addAll(cards);
    }

    public final void addCard(Card card) {
        cards.push(card);
    }

    public final Hand split() {
        if (!isPair()) {
            throw new RuntimeException("Can only split a hand with two cards. No more, no less.");
        }
        return new Hand(cards.pop());
    }

    public final int value() {
        if (numAces() == 0) {
            return naiveValue();
        }
        Integer[] possibleVals = possibleValues();
        if (possibleVals[0] <= 21) {
            return possibleVals[0];
        } else {
            return possibleVals[1];
        }
    }

    public final boolean isHard() {
        if (numAces() == 0) {
            return true;
        }
        return possibleValues()[0] <= 21;
    }

    private int naiveValue() {
        int val = 0;
        for (Card card : cards) {
            val += card.getValue();
        }
        return val;
    }

    private Integer[] possibleValues() {
        return new Integer[] { baseValue() + Rank.ACE.getValue() + numAces() - 1,
                baseValue() + numAces() };
    }

    private int baseValue() {
        return naiveValue() - numAces() * Rank.ACE.getValue();
    }

    private int numAces() {
        int numAces = 0;
        for (Card card : cards) {
            if (card.getRank().equals(Rank.ACE)) {
                numAces ++;
            }
        }
        return numAces;
    }

    public final boolean isSoft() {
        return !isHard();
    }

    public final boolean isPair() {
        return (cards.size() == 2)
                && (cards.peekFirst().getValue() ==  cards.peekLast().getValue());
    }

    public final boolean isBusted() {
        return (value() > 21);
    }

    public final Type getType() {
        if (isPair()) {
            return Type.PAIR;
        } else if (isHard()) {
            return Type.HARD;
        } else {
            return Type.SOFT;
        }
    }
}
