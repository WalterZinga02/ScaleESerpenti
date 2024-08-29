package box;

import game.Game;
import player.Player;

import java.util.*;

public class DrawACardBox extends AbstractBox{
    private Deck deck;

    public DrawACardBox(int boxNumber) {
        super(boxNumber);
        this.deck = new Deck();
    }

    @Override
    public void act(Game game, Player player) {
        System.out.println("the player " + player.getName() + " is on a draw a card box "+boxNumber);
        String cardType = deck.drawCard();
        actCard(cardType, player, game);
    }

    private void actCard(String cardType, Player player, Game game) {
        switch (cardType) {
            case "Bench":
                player.setTurnsToSkip(1);
                System.out.println(player.getName() + " has to skip a turn");
                break;
            case "Inn":
                player.setTurnsToSkip(3);
                System.out.println(player.getName() + " has to skip three turns");
                break;
            case "Dice":
                System.out.println(player.getName() + " has to throw dice again.");
                game.turn(player);
                break;
            case "Spring":
                System.out.println(player.getName() + " has to move again");
                game.moveAgain(player);
                break;
            default:
                System.out.println("Unknown card type.");
                break;
        }
    }

    @Override
    public String getBoxType() {
        return "DrawACard";
    }

    private static class Deck {
        private LinkedList<String> cards;

        public Deck() {
            this.cards = new LinkedList<>();
            initializeDeck();
        }

        private void initializeDeck() {
            cards.add("Bench");
            cards.add("Inn");
            cards.add("Dice");
            cards.add("Spring");

            shuffleDeck();
        }

        //shuffles the deck changing the order of the cards
        public void shuffleDeck() {
            Collections.shuffle(cards);
        }

        public String drawCard() {
            String drawnCard = cards.poll(); //draws and removes the card on top
            cards.add(drawnCard); //adds the card in the back of the deck
            return drawnCard;
        }
    }
}
