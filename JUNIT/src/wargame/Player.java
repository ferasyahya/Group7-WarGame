
package wargame;

import java.util.List;

public class Player {
    String name;
    GroupOfCard hand = new GroupOfCard();
    int roundsWon = 0;

    Player(String name) {
        this.name = name;
    }

    Card playCard() {
        return hand.deal();
    }

    void addWinnings(List<Card> cards) {
        hand.cards.addAll(cards);
    }

    boolean isOutOfCards() {
        return hand.isEmpty();
    }
    
    void incrementRoundsWon(){
        roundsWon++;
    }
    
    int getRoundsWon(){
        return roundsWon;
    }
}
