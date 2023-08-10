package FinalProj;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class WarGame extends Game {

    Scanner input = new Scanner(System.in);
    private WarGamePlayer warPlayer1;
    private WarGamePlayer warPlayer2;

    public WarGame(String name, WarGamePlayer player1, WarGamePlayer player2) {
        super(name, player1, player2);
        warPlayer1 = (WarGamePlayer) _Player1;
        warPlayer2 = (WarGamePlayer) _Player2;
    }

    @Override
    public void Play() {
        System.out.println("Welcome to a classic WAR card game... Enter your username: ");
        String name = input.next();
        _Player1.set_name(name);

        System.out.println("Enter the size of the deck or enter 0 to use a full deck of cards");

        // Validate deck size
        while (true) {
            try {
                _deckSize = input.nextInt();
                if (_deckSize < 10 && _deckSize != 0) {
                    System.out.println("Minimum deck size is 10!");
                    input.nextLine();
                } else if (_deckSize % 2 != 0) {
                    System.out.println("Please select an even number to split the deck evenly between the two players");
                    input.nextLine();
                } else if (_deckSize == 0) {
                    _deckSize = Card.MAX_DECK_SIZE;
                    break;
                } else {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid size!");
                input.nextLine();
            }
        }
        initializeDeck();
        System.out.println("The deck of cards that will be used for this game is as follows");
        int count = 0;
        for (Card card : _deck.getDeckOfCards()) {
            System.out.println(++count + ": " + card);
        }
        int userinput;
        while (true) {
            System.out.println("Press 1 to shuffle cards and get your hand..");
            try {
                userinput = input.nextInt();
                if (userinput == 0) {
                    System.out.println("You quit");
                    System.exit(0);
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input!");
                input.nextLine();
            }
        }

        if (userinput != 0) {
            deal();
            presentHand();
        }

    }

    @Override
    public void declareWinner() {
        if (warPlayer1.totalCards() == 0) {
            System.out.println(_Player1.get_name() + " Lost the Game!");
        } else if (warPlayer2.totalCards() == 0) {
            System.out.println(_Player1.get_name() + " Won the game!");
        } else {
            System.out.println(_Player1.get_name() + " quit..");
        }
        System.out.println("To play again press 1. To quit press 0");

        if (input.nextInt() == 1) {
            resetDecks();
            Play();
        } else {
            System.out.println("You quit");
            System.exit(0);
        }

    }

    public void presentHand() {

        int result;
        int userinput;

        do {
            System.out.println("Enter 1 to deal, 0 to quit");
            try {

                userinput = input.nextInt();
                if (userinput == 0) {
                    break;

                } else {

                    System.out.println("-----------------------ROUND RESULTS-----------------------");

                    System.out.println("\nThe card you played is " + warPlayer1.cardAtIndex(0));
                    System.out.println("\nThe card your oponent played is " + warPlayer2.cardAtIndex(0));

                    result = warPlayer1.cardAtIndex(0).compareTo(warPlayer2.cardAtIndex(0));

                    if (result == -1) {
                        System.out.println("\nYou won the round and absorbed your opponent's card");
                        warPlayer1.getDiscardHand().add(warPlayer1.cardAtIndex(0));
                        warPlayer1.getDiscardHand().add(warPlayer2.cardAtIndex(0));
                        warPlayer2.getHand().remove(0);
                        warPlayer1.getHand().remove(0);

                    } else if (result == 1) {
                        System.out.println("\nYou lost the round and your card was absorbed by your opponent");
                        warPlayer2.getDiscardHand().add(warPlayer1.cardAtIndex(0));
                        warPlayer2.getDiscardHand().add(warPlayer2.cardAtIndex(0));
                        warPlayer2.getHand().remove(0);
                        warPlayer1.getHand().remove(0);

                    } else {
                        declareWar();
                    }

                    if (warPlayer1.getHand().isEmpty() || warPlayer2.getHand().isEmpty()) {
                        if (warPlayer1.getHand().isEmpty()) {
                            transferFromDiscard(warPlayer1);
                        }

                        if (warPlayer2.getHand().isEmpty()) {
                            transferFromDiscard(warPlayer2);

                        }
                    }

                    displayInformation();

                    if (warPlayer1.totalCards() == 0 || warPlayer2.totalCards() == 0) {
                        break;
                    }
                }

            } catch (InputMismatchException e) {
                System.out.println("Invalid input");
                input.nextLine();
            }

        } while (true);

        declareWinner();
    }

    /*
     * Helper methods used only within this class
     */
    /**
     * Clears all decks of both players
     */
    private void resetDecks() {
        warPlayer1.getHand().clear();
        warPlayer1.getDiscardHand().clear();
        warPlayer2.getHand().clear();
        warPlayer2.getDiscardHand().clear();
    }

    /**
     * Transfers cards from the discard pile of the selected player to the main
     * deck
     *
     * @param player The player to transfer the cards for
     */
    private void transferFromDiscard(WarGamePlayer player) {
        //System.out.println("Main deck is out of cards, transferring cards from discard pile..");
        if (player.getDiscardHand().isEmpty()) {
            declareWinner();
        } else {
            shuffle(player.getDiscardHand());
            player.setHand(player.getDiscardHand());
            player.getDiscardHand().clear();
        }

    }

    /**
     * Prints a summary of the round results
     */
    private void displayInformation() {
        System.out.println("-----------------------------------------------");
        System.out.println("\nYou have a total of " + warPlayer1.totalCards() + " on hand");

        //System.out.println("\nYour Main deck: ");
        //warPlayer1.displayDeck(warPlayer1.getHand());
        System.out.println("\nYour discard pile: ");
        warPlayer1.displayDeck(warPlayer1.getDiscardHand());

        System.out.println("-----------------------------------------------");
        System.out.println("\nYour opponent has a total of " + warPlayer2.totalCards() + " on hand");
        //System.out.println("\nOpponent's Main deck: ");
        //warPlayer1.displayDeck(warPlayer2.getHand());

        System.out.println("\nOpponent's discard pile: ");
        warPlayer2.displayDeck(warPlayer2.getDiscardHand());
    }

    /**
     * Defines the logic that will occur when two cards are equal and the round
     * result is a tie
     */
    private void declareWar() {
        int result = 0;

        // Create a dedicated pile that will store the cards being placed during the
        // war.
        ArrayList<Card> warPile = new ArrayList<>();

        // Add the two previous cards into the war pile. These are the two cards that
        // caused the war.
        warPile.add(warPlayer1.cardAtIndex(0));
        warPile.add(warPlayer2.cardAtIndex(0));

        // Remove those cards from the deck
        warPlayer1.getHand().remove(0);
        warPlayer2.getHand().remove(0);

        // Start a while loop that will keep looping until the war ends.
        do {
            System.out.println("\nA war has occured!!!");

            // A for loop that runs three times. This simulates displaying the three cards
            // face down.
            for (int i = 0; i < 3; i++) {

                if (warPlayer1.getHand().isEmpty() || warPlayer2.getHand().isEmpty()) {

                    if (warPlayer1.getHand().isEmpty()) {
                        transferFromDiscard(warPlayer1);
                        System.out.println("Your Card " + (i + 1) + ": " + warPlayer1.cardAtIndex(0));
                        warPile.add(warPlayer1.cardAtIndex(0));
                        warPlayer1.getHand().remove(0);

                        if (warPlayer2.getHand().isEmpty()) {
                            transferFromDiscard(warPlayer2);
                            System.out.println("Opponent's Card " + (i + 1) + ": " + warPlayer2.cardAtIndex(0));
                            warPile.add(warPlayer2.cardAtIndex(0));
                            warPlayer2.getHand().remove(0);

                        } else {
                            System.out.println("Opponent's Card " + (i + 1) + ": " + warPlayer2.cardAtIndex(0));
                            warPile.add(warPlayer2.cardAtIndex(0));
                            warPlayer2.getHand().remove(0);
                        }

                    } else if (warPlayer2.getHand().isEmpty()) {
                        transferFromDiscard(warPlayer2);
                        System.out.println("Opponent's Card " + (i + 1) + ": " + warPlayer2.cardAtIndex(0));
                        warPile.add(warPlayer2.cardAtIndex(0));
                        warPlayer2.getHand().remove(0);

                        if (warPlayer1.getHand().isEmpty()) {
                            transferFromDiscard(warPlayer1);
                            System.out.println("Your Card " + (i + 1) + ": " + warPlayer1.cardAtIndex(0));
                            warPile.add(warPlayer1.cardAtIndex(0));
                            warPlayer1.getHand().remove(0);

                        } else {
                            System.out.println("Your Card " + (i + 1) + ": " + warPlayer1.cardAtIndex(0));
                            warPile.add(warPlayer1.cardAtIndex(0));
                            warPlayer1.getHand().remove(0);
                        }
                    }
                } else {
                    System.out.println("Your Card " + (i + 1) + " :" + warPlayer1.cardAtIndex(0));
                    System.out.println("Opponent's Card " + (i + 1) + " :" + warPlayer2.cardAtIndex(0));

                    warPile.add(warPlayer1.cardAtIndex(0));
                    warPile.add(warPlayer2.cardAtIndex(0));
                    warPlayer1.getHand().remove(0);
                    warPlayer2.getHand().remove(0);
                }

            }

            // Display the cards in the war pile once the two players have put down their
            // cards.
//            for (Card card : warPile) {
//                System.out.println(card);
//            }

            // Check that both players have enough cards in their main deck before
            // displaying the last card.
            if (warPlayer1.getHand().isEmpty()) {
                transferFromDiscard(warPlayer1);
            }
            if (warPlayer2.getHand().isEmpty()) {
                transferFromDiscard(warPlayer2);
            }
            // Compare the fourth cards being placed down which will determine who wins the
            // war
            System.out.println("The being compared are: ");
            System.out.println(warPlayer1.cardAtIndex(0));
            System.out.println(warPlayer2.cardAtIndex(0));

            result = warPlayer1.cardAtIndex(0)
                    .compareTo(warPlayer2.cardAtIndex(0));

            // Add those cards to the war pile.
            warPile.add(warPlayer1.cardAtIndex(0));
            warPile.add(warPlayer2.cardAtIndex(0));
            warPlayer1.getHand().remove(0);
            warPlayer2.getHand().remove(0);

            // Determine who takes the cards from the war pile to their discarded deck.
            if (result == -1) {
                System.out.println(_Player1.get_name() + " won the WAR!!!");
                warPlayer1.getDiscardHand().addAll(warPile);
                break;

            } else if (result == 1) {
                System.out.println("You lost the WAR..");
                warPlayer2.getDiscardHand().addAll(warPile);
                break;
            }
        } while (result == 0);

        // Clear the war pile only once a winner has been determined.
        warPile.clear();
    }

}
