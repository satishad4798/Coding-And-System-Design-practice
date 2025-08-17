package systems.deckOFCards;

public class Card {

    RANK rank;
    Suit suit;

    public Card(RANK rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public RANK getRank() {
        return rank;
    }

    public Suit getSuit() {
        return suit;
    }


    @Override
    public String toString() {
        return suit + ":" + rank + ":" + rank.getRank();
    }
}
