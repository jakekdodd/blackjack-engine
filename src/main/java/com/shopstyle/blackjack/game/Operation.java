package com.shopstyle.blackjack.game;

import java.util.HashMap;
import java.util.Map;

public enum Operation {
    HIT("h"), STAND("s"), SPLIT("p"), DOUBLE_OR_HIT("dh"), DOUBLE_OR_STAND("ds");

    private static final Map<String, Operation> MAP = new HashMap<>();
    private final String val;

    static {
        for (Operation op: Operation.values()) {
            MAP.put(op.toString(), op);
        }
    }

    private Operation(String val) {
        this.val = val;
    }

    public static Operation fromString(String string) {
        return MAP.get(string);
    }

    @Override
    public String toString() {
        return val;
    }
}
