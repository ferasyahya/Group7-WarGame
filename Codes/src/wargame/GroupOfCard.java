
package wargame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Keshav Sriram Kumar
 */

//Group Of Cards Class.
public class GroupOfCard {
    List<Card> cards = new ArrayList<>();

    void addCard(Card card) {
        cards.add(card);
    }

    void shuffle() {
        Collections.shuffle(cards);
    }

    Card deal() {
        if (!isEmpty()) {
            return cards.remove(0);
        }
        return null;
    }

    boolean isEmpty() {
        return cards.isEmpty();
    }
}
