package FinalProj;

public class Card implements Comparable<Card>{

    public enum Suit {SPADES, HEARTS, CLUBS, DIAMONDS};
    public enum Rank {JOKER, ACE, KING, QUEEN, JACK, TEN, NINE, EIGHT, SEVEN, SIX, FIVE, FOUR, THREE, TWO};
    public static final int MAX_DECK_SIZE = Suit.values().length * Rank.values().length;

    private Rank _rank;
    private Suit _suit;
    
    public Card (Rank rank, Suit suit){
        _rank = rank;
        _suit = suit;
    }

    public Rank get_rank() {
        return _rank;
    }

    public void set_rank(Rank _rank) {
        this._rank = _rank;
    }

    public Suit get_suit() {
        return _suit;
    }

    public void set_suit(Suit _suit) {
        this._suit = _suit;
    }

    @Override
    public String toString(){
        return _rank.toString() + " of " + _suit.toString();
    }

    /**
     * 
     * @param o
     * @return -1 if the rank of card is greater than the rank of the card its being compared to 
     */
    @Override
    public int compareTo(Card o) {
        return Integer.compare(Rank.valueOf(this.get_rank().toString()).ordinal(), Rank.valueOf(o.get_rank().toString()).ordinal());
    }

    
}
