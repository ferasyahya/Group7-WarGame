/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package wargame;

/**
 *
 * @author Keshav Sriram Kumar
 */

import java.util.Scanner;

//Game main class...
public class Game {
    public static void main(String[] args) {
        
        //Scanner Object.
        Scanner scanner = new Scanner(System.in);
        
        //Introduction to the Game.
        System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
        System.out.println("WELCOME TO THE WAR GAME!");

        //Getting Player 1 Name.
        System.out.print("Enter Player 1's name: ");
        String player1Name = scanner.nextLine();
        Player player1 = new Player(player1Name);

        //Getting Player 2 Name.
        System.out.print("Enter Player 2's name: ");
        String player2Name = scanner.nextLine();
        Player player2 = new Player(player2Name);

        //Creating a list of Ranks and Suits.
        GroupOfCard deck = new GroupOfCard();
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};

        //Adding the Rank and Suits in the Deck.
        for (String suit : suits) {
            for (String rank : ranks) {
                Card card = new Card(rank, suit);
                deck.addCard(card);
            }
        }

        deck.shuffle();

        //Distributing the cards to the Players.
        for (int i = 0; i < deck.cards.size(); i++) {
            if (i % 2 == 0) {
                player1.hand.addCard(deck.deal());
            } else {
                player2.hand.addCard(deck.deal());
            }
        }

        //Starting the Game.
        System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
        System.out.println("STARTING THE WAR CARD GAME!\n");

        int roundNum = 1;
        boolean continuePlaying = true;

        while (continuePlaying) {
            WarGame warGame = new WarGame(player1, player2);
            warGame.playRound(roundNum);

            roundNum++;

            //Asking the user wish to Continue or Not.
            System.out.print("Do you want to play again? (yes/no): ");
            String response = scanner.nextLine();
            continuePlaying = response.equalsIgnoreCase("yes");
        }

        //Displaying Thank you Message.
        System.out.println("\nTHANKS FOR PLAYING THE WAR CARD GAME!");
    }
}

