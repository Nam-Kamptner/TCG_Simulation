package model;

import java.util.ArrayList;
import java.util.List;

public class Deck {
    private static final int size = 40;
    private List<Card> deck;

    public Deck() {
        this.deck = new ArrayList<Card>();
    }

    public void addCard(Card card) {
        if (!(getCardCount(card) == 3)) {
            deck.add(card);
        } else {
            throw new IllegalStateException("Deck already contains max. copies (3) of " + card.getName());
        }
    }
}
