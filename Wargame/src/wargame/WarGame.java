
package wargame;

import java.util.List;

/**
 *
 * @author Keshav Sriram Kumar.
 */
public class WarGame {
    Player player1;
    Player player2;

    WarGame(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    void playRound(int roundNum) {
        // ... (same as before)
        System.out.println("Round " + roundNum + '\n');
        Card card1 = player1.playCard();
        Card card2 = player2.playCard();
        System.out.println(player1.name + " plays: " + card1);
        System.out.println(player2.name + " plays: " + card2 + "\n");

        if (card1.rank.compareTo(card2.rank) > 0) {
            player1.addWinnings(List.of(card1, card2));
            player1.incrementRoundsWon();
            System.out.println(player1.name + " WINS THE ROUND!");
            System.out.println(player1.name + " won " + player1.getRoundsWon() + " rounds.");
            System.out.println(player2.name + " won " + player2.getRoundsWon() + " rounds.");

        } else if (card2.rank.compareTo(card1.rank) > 0) {
            player2.addWinnings(List.of(card1, card2));
            player2.incrementRoundsWon();
            System.out.println(player2.name + " WINS THE ROUND!");
            System.out.println(player1.name + " won " + player1.getRoundsWon() + " rounds.");
            System.out.println(player2.name + " won " + player2.getRoundsWon() + " rounds.");
        } else {
            System.out.println("IT'S A TIE!");
        }

    }
}
