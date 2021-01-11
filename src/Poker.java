import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Poker {
    private final String[] SUITS = { "C", "D", "H", "S" };
    private final String[] RANKS = { "A", "2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K" };

    private final Player player;
    private List<Card> deck;
    private final Scanner in;

    public Poker() {
        this.player = new Player();
        this.in = new Scanner(System.in);
    }

    public void game() {
        shuffle();

        while (deck.size() >= 8) {
            for (int i = 0; i < 5; i++) {
                player.deal(deck.get(0));
                deck.remove(0);
            }
            player.sortHand();

            System.out.print("\nYour hand: ");
            System.out.println(player.getHand());

            takeTurn();

            player.clearHand();

            System.out.println("\nNext Turn\n");
        }
    }

    public void takeTurn() {
        int cardsToTrade = -1;
        do {
            System.out.println("\nHow many cards would you like to trade? (Any number from 0 - 3)");
            try {
                cardsToTrade = in.nextInt();
            }
            catch (Exception e) {
                cardsToTrade = -1;
                in.next();
            }
        } while (cardsToTrade < 0 || cardsToTrade > 3);
        in.nextLine();

        if (cardsToTrade > 0) System.out.println("\nWhich cards will you trade? (Choose " + cardsToTrade + ", choose the numbered position of the card, not the card itself)");

        int[] indexes = new int[cardsToTrade];
        for (int i = 0; i < cardsToTrade; i++) {
            indexes[i] = 0;
        }
        for (int i = 0; i < cardsToTrade; i++) {
            int indexPlusOne = -1;
            do {
                System.out.println("\nPick card " + (i + 1) + " / " + cardsToTrade + ".");
                try {
                    indexPlusOne = in.nextInt();
                }
                catch (Exception e) {
                    indexPlusOne = -1;
                    in.next();
                }
                finally {
                    for (int j = 0; j < cardsToTrade; j++) {
                        if (indexPlusOne == indexes[j]) {
                            indexPlusOne = 0;
                        }
                    }
                    if (indexPlusOne > 0 && indexPlusOne <= player.getHand().size()) {
                        indexes[i] = indexPlusOne;
                        System.out.println("Removed " + player.getHand().get(indexPlusOne - 1).toString());
                    }
                }
            } while (indexPlusOne <= 0 || indexPlusOne > player.getHand().size());
            in.nextLine();
        }

        for (int i : indexes) {
            System.out.println(i);
        }

        for (int i = 0; i < cardsToTrade; i++) {
            player.setCard(indexes[i] - 1, new Card("X", "X"));
            player.deal(deck.get(0));
            deck.remove(0);
        }
        for (int i = 0; i < player.getHand().size(); i++) {
            if (player.getHand().get(i).getRank().matches("X")) {
                player.removeCard(i);
                i = -1;
            }
        }

        player.sortHand();

        if (cardsToTrade > 0) {
            System.out.print("\nYour new hand: ");
            System.out.println(player.getHand());
        }

        int payOutMultiplier = player.evaluateHand();
        System.out.println(payOutMultiplier);
    }

    public void shuffle() {
        deck = new ArrayList<>(52);

        for (String suit : SUITS) {
            for (String rank : RANKS) {
                deck.add(new Card(rank, suit));
            }
        }

        Collections.shuffle(deck);
    }

    public static void main(String[] args) {
        new Poker().game();
    }
}
