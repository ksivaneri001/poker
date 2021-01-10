import java.util.ArrayList;
import java.util.List;

public class Player {
    private final List<Card> hand;
    private int chips;

    public Player() {
        this.hand = new ArrayList<>();
        this.chips = 0;
    }

    public List<Card> getHand() {
        return hand;
    }

    public int getChips() {
        return chips;
    }

    public void deal(Card card) {
        hand.add(card);
    }

    public void removeCard(int index) {
        hand.remove(index);
    }

    public void setCard(int index, Card card) {
        hand.set(index, card);
    }

    public void sortHand() {
        hand.sort((a, b) -> {
            if (Card.getOrderedRank(a.getRank()) == Card.getOrderedRank(b.getRank())) {
                return Card.getOrderedSuit(a.getSuit()) - Card.getOrderedSuit(b.getSuit());
            }

            return Card.getOrderedRank(a.getRank()) - Card.getOrderedRank(b.getRank());
        });
    }

    public int evaluateHand() {
        if (Evaluate.royalFlush(hand)) return 100;
        else return -1;
    }
}
