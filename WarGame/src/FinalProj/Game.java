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

//    public void deal() {
//        ArrayList<Card> player1Deck = new ArrayList<>();
//        ArrayList<Card> player2Deck = new ArrayList<>();
//        // ArrayList<Card> shuffledDeck = _deck.shuffle();
//        
//        Card card1 = new Card(Card.Rank.FOUR, Card.Suit.DIAMONDS);
//        Card card2 = new Card(Card.Rank.FIVE, Card.Suit.SPADES);
//        Card card3 = new Card(Card.Rank.JOKER, Card.Suit.HEARTS);
//        Card card4 = new Card(Card.Rank.THREE, Card.Suit.DIAMONDS);
//        Card card5 = new Card(Card.Rank.QUEEN, Card.Suit.SPADES);
//
//        player1Deck.add(card1);
//        player1Deck.add(card2);
//        player1Deck.add(card3);
//        player1Deck.add(card4);
//        player1Deck.add(card5);
//        
//        player1Deck.add(card1);
//        player1Deck.add(card2);
//        player1Deck.add(card3);
//        player1Deck.add(card4);
//        player1Deck.add(card5);
//        
//        Card card6 = new Card(Card.Rank.FOUR, Card.Suit.SPADES);
//        Card card7 = new Card(Card.Rank.THREE, Card.Suit.SPADES);
//        Card card8 = new Card(Card.Rank.TEN, Card.Suit.HEARTS);
//        Card card9 = new Card(Card.Rank.JACK, Card.Suit.DIAMONDS);
//        Card card10 = new Card(Card.Rank.QUEEN, Card.Suit.SPADES);
//
//        player2Deck.add(card6);
//        player2Deck.add(card7);
//        player2Deck.add(card8);
//        player2Deck.add(card9);
//        player2Deck.add(card10);
//        
//        player2Deck.add(card6);
//        player2Deck.add(card7);
//        player2Deck.add(card8);
//        player2Deck.add(card9);
//        player2Deck.add(card10);
//        
//        _Player1.setHand(player1Deck);
//        _Player2.setHand(player2Deck);
//        System.out.println("You deck of cards is as follows: ");
//        _Player1.displayDeck(_Player1.getHand());
//    }
    
    public void shuffle(ArrayList<Card> deckToShuffle) {
        Collections.shuffle(deckToShuffle);

    }

    public void initializeDeck() {
        _deck = new GroupOfCards(_deckSize);
    }

}
