package systems.deckOFCards;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        Deck deck = new Deck();

        deck.shuffleCards();

        sortDesk(deck.cards);

        System.out.println();

        for (int i = 0; i < 10; i++) {
            System.out.println(deck.getCard().toString());
        }

        System.out.println("bigger one : " + compare(deck.getCard(), deck.getCard()));
    }

    public static Card compare(Card card1, Card card2) {
        System.out.println("card given for compare are");
        System.out.println(card1.toString());
        System.out.println(card2.toString());
        if (card1.rank.getRank() > card2.rank.getRank()) {
            return card1;
        } else {
            return card2;
        }

    }


    public static void sortDesk(List<Card> cards) {

        cards.sort((card1, card2) -> {
            System.out.println();
            int diff = card1.getSuit().compareTo(card2.getSuit());
            if (diff != 0) {
                return diff;
            }
            return card1.getRank().compareTo(card2.getRank());
        });

    }


}
