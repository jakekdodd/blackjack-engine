package com.shopstyle.blackjack.card;

public class Card {
    private final Suit suit;
    private final Rank rank;

    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public final int getValue() {
        return rank.getValue();
    }

    public final int getMinValue() {
        if (rank.equals(Rank.ACE)) {
            return 1;
        } else {
            return rank.getValue();
        }
    }

    public final Rank getRank() {
        return rank;
    }

    public final Suit getSuit() {
        return suit;
    }
}
