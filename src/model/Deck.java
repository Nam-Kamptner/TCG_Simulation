package model;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class Deck {
    private static final int size = 40;
    private String deckName;
    private List<Integer> deckCards;
    private Map<Integer, Integer> cardCounts;

    public Deck(String deckName) {
        this.deckName = deckName;
        this.deckCards = new ArrayList<Integer>();
        this.cardCounts = new HashMap<>();
    }

    public void addCard(int cardId) {
        if (getCount(cardId) < 3) {
            deckCards.add(cardId);
            cardCounts.put(cardId, cardCounts.getOrDefault(cardId, 0) + 1);
        } else {
            throw new IllegalStateException("Cannot add more than 3 copies of the card with id " + cardId + ".");
        }
    }

    public void removeCard(int cardId) {
        if (cardCounts.containsKey(cardId)) {
            deckCards.remove(cardId);
            if (cardCounts.get(cardId) > 1) {
                cardCounts.put(cardId, cardCounts.get(cardId) - 1);
            } else {
                cardCounts.remove(cardId);
            }
        }
    }

    public List<Integer> getDeckCards() {
        return deckCards;
    }
    public Map<Integer, Integer> getCardCounts() {
        return cardCounts;
    }
    public int getSize() {
        return deckCards.size();
    }

    public int getCount(int cardId) {
        return cardCounts.getOrDefault(cardId, 0);
    }

    public String toString() {
        return "Deck{" +
                "Deck Name: " + deckName +
                ", deck cards: " + deckCards +
                ", card counts: " + cardCounts +
                "}";
    }
}
