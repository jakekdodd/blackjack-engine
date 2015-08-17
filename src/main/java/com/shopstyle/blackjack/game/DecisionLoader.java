package com.shopstyle.blackjack.game;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class DecisionLoader {

    public static Map<Integer, List<Operation>> opsForHandType(Hand.Type type) {
        Reader reader = readerForHandType(type);
        try {
            CSVParser parser = new CSVParser(reader, CSVFormat.DEFAULT);
            Map<Integer, List<Operation>> ops = new HashMap<>();
            for (CSVRecord record : parser) {
                List<String> list = recordToList(record);
                Integer hand = Integer.valueOf(list.remove(0));
                ops.put(hand, recordToOps(list));
            }
            return ops;
        } catch (IOException e) {
            throw new RuntimeException("Unable to parse " + filenameForHandType(type), e);
        }
    }

    private static List<Operation> recordToOps(List<String> list) {
        List<Operation> ops = new ArrayList<>();
        for (String str : list) {
            ops.add(Operation.fromString(str.toLowerCase(Locale.US)));
        }
        return ops;
    }

    private static List<String> recordToList(CSVRecord record) {
        List<String> list = new ArrayList<>();
        for (String str : record) {
            list.add(str);
        }
        return list;
    }

    private static Reader readerForHandType(Hand.Type type) {
        return new InputStreamReader(DecisionLoader.class.getClassLoader()
                .getResourceAsStream(filenameForHandType(type)));
    }

    private static String filenameForHandType(Hand.Type type) {
        return type.name().toLowerCase() + ".csv";
    }
}
