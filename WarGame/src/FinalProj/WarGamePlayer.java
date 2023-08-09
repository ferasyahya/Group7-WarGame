package FinalProj;

import java.util.ArrayList;

public class WarGamePlayer extends Player {

    private ArrayList<Card> _discardHand;

    public WarGamePlayer(String name) {
        super(name);
        _discardHand = new ArrayList<>();
    }

    public WarGamePlayer(){
        _discardHand = new ArrayList<>();

    }

    public ArrayList<Card> getDiscardHand() {
        return _discardHand;
    }

    public void set_discardHand(ArrayList<Card> _discardHand) {
        this._discardHand = _discardHand;
    }

    public int totalCards(){
        return this.getHand().size() + _discardHand.size();
    }
}
