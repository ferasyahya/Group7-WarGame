package FinalProj;


public class Runner {
    

    public static void main (String args[]){



        //GroupOfCards deck = new GroupOfCards(20);
        WarGamePlayer player1 = new WarGamePlayer();
        WarGamePlayer player2 = new WarGamePlayer("PC");
        WarGame newGame = new WarGame("Test", player1, player2);
        newGame.Play();

    }
}
