package test;

import model.Card;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CardTest {
    private Card card;

    @BeforeEach
    public void setUp() {
        card = new Card("Sample Card", "Attack", 5, 3, 2, 1);
    }

    @Test
    public void testCardCreation() {
        assertNotNull(card);
        assertEquals("Sample Card", card.getName());
        assertEquals("Attack", card.getType());
        assertEquals(5, card.getAttack());
        assertEquals(3, card.getDefense());
        assertEquals(2, card.getCost());
    }

    @Test
    public void testCardInvalidValues() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Card("Invalid Card", "Defense", -1, 3, 2,1);
        });
        assertEquals("Attack, defense, and cost must be non-negative.", exception.getMessage());
    }

    @Test
    public void testSetCardId() {
        card.setId(1);
        assertEquals(1, card.getId());
    }

    @Test
    public void testCardEqualsAndHashCode() {
        card.setId(1);
        Card sameCard = new Card("Sample Card", "Attack", 5, 3, 2,1);
        sameCard.setId(1);
        Card differentCard = new Card("Another Card", "Defense", 4, 4, 1,1);
        differentCard.setId(2);

        assertEquals(card, sameCard);
        assertNotEquals(card, differentCard);
        assertEquals(card.hashCode(), sameCard.hashCode());
        assertNotEquals(card.hashCode(), differentCard.hashCode());
    }
}
