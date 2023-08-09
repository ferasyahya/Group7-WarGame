
package wargame;



//Card Class.
public class Card {
    String rank;
    String suit;

    //Assigning the Values.
    Card(String rank, String suit) {
        this.rank = rank;
        this.suit = suit;
    }

    @Override
    public String toString() {
        return rank + " of " + suit;
    }
}
