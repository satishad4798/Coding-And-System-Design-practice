package systems.deckOFCards;

public enum Suit {

    HEART(1),
    DIAMOND(2),
    CLUB(3),
    SPADE(4);

    private final int internalRank;

    Suit(int internalRank) {
        this.internalRank = internalRank;
    }
}
