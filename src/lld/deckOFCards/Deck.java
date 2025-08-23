package lld.deckOFCards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {

    List<Card> cards;

    public Deck() {

        cards = new ArrayList<>();
        initializeDeck();
        shuffleCards();
    }

    public void shuffleCards() {

        Collections.shuffle(cards);
    }


    public void initializeDeck() {

        for (Suit s : Suit.values()) {
            for (RANK rank : RANK.values()) {
                cards.add(new Card(rank, s));
            }
        }
    }

    public Card getCard() {
        return cards.remove(cards.size() - 1);
    }


}
