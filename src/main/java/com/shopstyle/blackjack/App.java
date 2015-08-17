package com.shopstyle.blackjack;

import java.util.List;
import java.util.Locale;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;
import org.kohsuke.args4j.spi.StringArrayOptionHandler;

import com.shopstyle.blackjack.card.Card;
import com.shopstyle.blackjack.card.Rank;
import com.shopstyle.blackjack.card.Suit;
import com.shopstyle.blackjack.game.DecisionMaker;
import com.shopstyle.blackjack.game.Hand;
import com.shopstyle.blackjack.game.Operation;

/**
 * Hello world!
 *
 */
public class App {

    @Option(name = "-hand", usage = "List of cards in the player's hand", required = true,
            handler = StringArrayOptionHandler.class)
    private static List<String> HAND;

    @Option(name = "-dealer", usage = "Card that the dealer is showing", required = true)
    private static String DEALER;

    public static void main( String[] args ) {
        App app = new App();
        CmdLineParser parser = new CmdLineParser(app);
        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            System.err.println("Error parsing command line arguments: " + e);
            System.out.println("\nUsage:");
            System.out.println("java " + App.class.getSimpleName());
            parser.printUsage(System.out);
            System.exit(1);
        }

        Hand hand = new Hand();
        for (String card : HAND) {
            hand.addCard(new Card(Suit.CLUBS, Rank.valueOf(card.toUpperCase(Locale.US))));
        }

        Operation op = DecisionMaker.decideOperation(hand,
                new Card(Suit.CLUBS, Rank.valueOf(DEALER.toUpperCase(Locale.US))));

        System.out.println("You should " + op.name());
    }
}
