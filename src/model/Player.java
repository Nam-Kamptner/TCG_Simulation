package model;

import dao.CardDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Player {
    private String name;
    private int health;
    private Deck deck;
    private List<Card> hand;
    private int actionPoints;

    public Player(String name, int health, Deck deck) {
        this.name = name;
        this.health = health;
        this.deck = deck;
        this.hand = new ArrayList<>();
        this.actionPoints = 1; // Starting action points
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public List<Card> getHand() {
        return hand;
    }

    public void drawCard(CardDAO cardDAO) {
        int cardId = deck.drawCardId();
        if (cardId != -1) {
            Card card = cardDAO.getCardByID(cardId);
            if (card != null) {
                hand.add(card);
                System.out.println(name + " drew " + card.getName());
            }
        }
    }

    public void drawStartingHand(CardDAO cardDAO) {
        for (int i = 0; i < 4; i++) {
            drawCard(cardDAO);
        }
    }

    public void playCard(Card card) {
        hand.remove(card);
    }

    public void addCardToHand(Card card) {
        hand.add(card);
    }

    public void setActionPoints(int actionPoints) {
        this.actionPoints = actionPoints;
    }

    public int getActionPoints() {
        return actionPoints;
    }

    public Card chooseAttackCard() {
        System.out.println(name + ", choose a card to attack with:");
        return chooseCardFromHand();
    }

    public List<Card> chooseDefenseCards() {
        System.out.println(name + ", choose cards to defend with:");
        List<Card> defenseCards = new ArrayList<>();
        while (true) {
            Card card = chooseCardFromHand();
            if (card == null) {
                break;
            }
            defenseCards.add(card);
            playCard(card); // Remove card from hand after choosing it
        }
        return defenseCards;
    }

    private Card chooseCardFromHand() {
        if (hand.isEmpty()) {
            System.out.println("No cards in hand.");
            return null;
        }

        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < hand.size(); i++) {
            System.out.println((i + 1) + ": " + hand.get(i));
        }

        int choice = scanner.nextInt() - 1;
        if (choice < 0 || choice >= hand.size()) {
            System.out.println("Invalid choice.");
            return null;
        }

        return hand.get(choice);
    }
}
