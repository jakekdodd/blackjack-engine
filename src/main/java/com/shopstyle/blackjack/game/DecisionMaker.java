package com.shopstyle.blackjack.game;

import com.shopstyle.blackjack.card.Card;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DecisionMaker {
    private static final Map<Hand.Type, Map<Integer, List<Operation>>> OPS = new HashMap<>();

    static {
        for (Hand.Type type : Hand.Type.values()) {
            OPS.put(type, DecisionLoader.opsForHandType(type));
        }
    }

    public static Operation decideOperation(Hand hand, Card dealerCard) {
        return OPS.get(hand.getType()).get(hand.value()).get(dealerCard.getValue() - 2);
    }
}
