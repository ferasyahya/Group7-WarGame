package FinalProj;

import java.util.ArrayList;
import java.util.Collections;

public abstract class Game {
    public String _name;
    public GroupOfCards _deck;
    public Player _Player1;
    public Player _Player2;
    public int _deckSize;

    // public Game(String name, GroupOfCards deck, Player player1, Player player2){
    public Game(String name, Player player1, Player player2) {
        _name = name;
        // _deckSize = deckSize;
        _Player1 = player1;
        _Player2 = player2;
    }

    public abstract void Play();

    public abstract void declareWinner();

    public void deal() {
        ArrayList<Card> player1Deck = new ArrayList<>();
        ArrayList<Card> player2Deck = new ArrayList<>();
        // ArrayList<Card> shuffledDeck = _deck.shuffle();
        shuffle(_deck.getDeckOfCards());

        int totalCards = _deck.getDeckOfCards().size();
        int cardsPerPlayer = totalCards / 2;

        for (int i = 0; i < cardsPerPlayer; i++) {
            player1Deck.add(_deck.getDeckOfCards().remove(0));
            player2Deck.add(_deck.getDeckOfCards().remove(0));
        }
        _Player1.setHand(player1Deck);
        _Player2.setHand(player2Deck);
        System.out.println("You deck of cards is as follows: ");
        _Player1.displayDeck(_Player1.getHand());
    }

    public void shuffle(ArrayList<Card> deckToShuffle) {
        Collections.shuffle(deckToShuffle);

    }

    public void initializeDeck() {
            _deck = new GroupOfCards(_deckSize);
    }

}
