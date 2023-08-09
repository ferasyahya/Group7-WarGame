package wargame;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class WarGameTest {

    private Card card1, card2;
    private GroupOfCard deck;
    private Player player1, player2;
    private WarGame warGame;

    @BeforeEach
    public void setUp() {
        card1 = new Card("Ace", "Hearts");
        card2 = new Card("King", "Hearts");
        deck = new GroupOfCard();
        player1 = new Player("Player1");
        player2 = new Player("Player2");
        warGame = new WarGame(player1, player2);
    }

    @Test
    public void testCardInitialization() {
        assertEquals("Ace", card1.rank);
        assertEquals("Hearts", card1.suit);
    }

    @Test
    public void testGroupOfCardOperations() {
        deck.addCard(card1);
        assertFalse(deck.isEmpty());

        Card dealtCard = deck.deal();
        assertEquals(card1, dealtCard);
        assertTrue(deck.isEmpty());
    }

    @Test
    public void testPlayerOperations() {
        player1.hand.addCard(card1);
        Card playedCard = player1.playCard();
        assertEquals(card1, playedCard);

        player1.addWinnings(List.of(card1));
        assertFalse(player1.isOutOfCards());
    }

    @Test
    public void testWarGameRoundPlayer1Wins() {
        player1.hand.addCard(card1); // Ace
        player2.hand.addCard(card2); // King
        warGame.playRound(1);
        assertEquals(0, player1.getRoundsWon());
        assertEquals(1, player2.getRoundsWon());
    }

    @Test
    public void testWarGameRoundPlayer2Wins() {
        player1.hand.addCard(card2); // King
        player2.hand.addCard(card1); // Ace
        warGame.playRound(1);
        assertEquals(1, player1.getRoundsWon());
        assertEquals(0, player2.getRoundsWon());
    }

    @Test
    public void testWarGameRoundTie() {
        Card tieCard1 = new Card("Queen", "Hearts");
        Card tieCard2 = new Card("Queen", "Clubs");
        player1.hand.addCard(tieCard1);
        player2.hand.addCard(tieCard2);
        warGame.playRound(1);
        assertEquals(0, player1.getRoundsWon());
        assertEquals(0, player2.getRoundsWon());
    }

 }
