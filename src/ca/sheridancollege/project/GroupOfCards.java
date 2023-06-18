
package ca.sheridancollege.project;

import java.util.ArrayList;
import java.util.Collections;

/**
 * A concrete class that represents any grouping of cards for a Game.
 */
public class GroupOfCards {

    //property: The group of cards, stored in an ArrayList
    private ArrayList<Card> cards;
    
    //property: the size of the grouping
    private int size;

    //Constructor
    public GroupOfCards(int size) {
        this.size = size;
    }

    /**
     * 
     * Getter method for the cards in the grouping, the result will be an array list
     * 
     * @return the group of cards.
     */
    public ArrayList<Card> getCards() {
        return cards;
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    /**
     * 
     * 
     * @return int acts as a getter for the size of the grouping
     */
    public int getSize() {
        return size;
    }

    /**
     * Setter method for the size of the grouping
     * 
     * @param size sets the size of the grouping
     */
    public void setSize(int size) {
        this.size = size;
    }

}