import java.util.List;

public class Evaluate {
    public static boolean royalFlush(List<Card> hand) {
        int count = 0;
        for (int i = 1; i < hand.size(); i++) {
            if (hand.get(0).getRank().equals("T") && Card.getOrderedRank(hand.get(i).getRank()) == Card.getOrderedRank(hand.get(i - 1).getRank()) + 1 && hand.get(i).getSuit().matches(hand.get(i - 1).getSuit())) count++;
        }
        return (count == 4);
    }

    public static boolean straightFlush(List<Card> hand) {
        int count = 0;
        for (int i = 1; i < hand.size(); i++) {
            if (Card.getOrderedRank(hand.get(i).getRank()) == Card.getOrderedRank(hand.get(i - 1).getRank()) + 1 && hand.get(i).getSuit().matches(hand.get(i - 1).getSuit())) count++;
        }
        return (count == 4);
    }

    public static boolean fourOfAKind(List<Card> hand) {
        int count = 0;
        int maxCount = 0;
        for (int i = 1; i < hand.size(); i++) {
            if (hand.get(i).getRank().matches(hand.get(i - 1).getRank())) count++;
            else count = 0;
            if (count > maxCount) maxCount = count;
        }
        return (maxCount == 3);
    }

    public static boolean fullHouse(List<Card> hand) {
        int count = 0;
        int maxCount = 0;
        for (int i = 1; i < 3; i++) {
            if (count == 1 && !hand.get(i).getRank().matches(hand.get(i - 1).getRank())) break;
            else if (hand.get(i).getRank().matches(hand.get(i - 1).getRank())) count++;
            else count = 0;
            if (count > maxCount) maxCount = count;
        }
        if (maxCount == 1) {
            maxCount = 0;
            for (int i = 3; i < hand.size(); i++) {
                if (hand.get(i).getRank().matches(hand.get(i - 1).getRank())) count++;
                else count = 0;
                if (count > maxCount) maxCount = count;
            }
            return (maxCount == 1);
        }
        else if (maxCount == 2) {
            if (hand.get(4).getRank().matches(hand.get(3).getRank())) return true;
        }
        return false;
    }

    public static boolean flush(List<Card> hand) {
        int count = 0;
        int maxCount = 0;
        for (int i = 1; i < hand.size(); i++) {
            if (hand.get(i).getSuit().matches(hand.get(i - 1).getSuit())) count++;
            else count = 0;
            if (count > maxCount) maxCount = count;
        }
        return (maxCount == 3);
    }

    public static boolean straight(List<Card> hand) {
        int count = 0;
        for (int i = 1; i < hand.size(); i++) {
            if (Card.getOrderedRank(hand.get(i).getRank()) == Card.getOrderedRank(hand.get(i - 1).getRank()) + 1) count++;
        }
        return (count == 4);
    }

    public static boolean threeOfAKind(List<Card> hand) {
        int count = 0;
        int maxCount = 0;
        for (int i = 1; i < hand.size(); i++) {
            if (hand.get(i).getRank().matches(hand.get(i - 1).getRank())) count++;
            else count = 0;
            if (count > maxCount) maxCount = count;
        }
        return (maxCount == 2);
    }

    public static boolean twoPair(List<Card> hand) {
        
    }
}
