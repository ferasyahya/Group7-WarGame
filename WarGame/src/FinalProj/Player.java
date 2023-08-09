package FinalProj;

import java.util.ArrayList;

public class Player {
    private String _name;
    private ArrayList<Card> _mainHand;

    public Player (String name){
        _mainHand = new ArrayList<>();
        _name = name;
    }

    public Player (){
        _mainHand = new ArrayList<>();

    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public ArrayList<Card> getHand(){
        return _mainHand;
    }

    public void setHand(ArrayList<Card> playerHand){
        _mainHand.addAll(playerHand);
    }

    public Card cardAtIndex(int index){
        return _mainHand.get(index);
    }

    public void displayDeck(ArrayList<Card> deck){
        if (deck.isEmpty()) System.out.println("Deck is empty");
        else{
            int count = 0;
            for (Card card : deck){
                System.out.println(++count + " :" + card);
            }
        }

    }

}
