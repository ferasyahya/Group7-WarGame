package FinalProj;

import java.util.ArrayList;
import java.util.Collections;
import FinalProj.Card.Rank;
import FinalProj.Card.Suit;

public class GroupOfCards {
    private ArrayList<Card> _deckOfCards;
    private int _deckSize;
    //This is a global variable that determines the possible max size of the deck. Updates based on the Card class
    private static final int STANDARD_DECK_SIZE = FinalProj.Card.MAX_DECK_SIZE; 

    public GroupOfCards() {
        _deckOfCards = new ArrayList<>();
        _deckSize = STANDARD_DECK_SIZE;
        generateHand();
    }

    public GroupOfCards(int deckSize) {
        _deckOfCards = new ArrayList<>();
        _deckSize = deckSize;
        generateHand();
    }

    public ArrayList<Card> getDeckOfCards() {
        return _deckOfCards;
    }

    private void generateHand() {
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                _deckOfCards.add(new Card(rank, suit));
            }
        }

        if (_deckSize != STANDARD_DECK_SIZE){
            Collections.shuffle(_deckOfCards);
            ArrayList<Card> randomDeck = new ArrayList<>(_deckOfCards.subList(0, _deckSize));
            _deckOfCards.clear();
            _deckOfCards.addAll(randomDeck);
        }
        

    }
}
