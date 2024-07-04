package dao;

import model.Card;
import util.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CardDAO {
    private Map<Integer, Card> cardCache = new HashMap<>();

    /**
     * Adds a new card to the database.
     *
     * @param card the card model Object to be added.
     */
    public void addCard(Card card) {
        if (card.getId() != 0) {
            throw new IllegalArgumentException("Cannot add card with an existing card_id. Use updateCard() instead.");
        }
        String sql = "INSERT INTO Cards(name, type, attack, defense, cost, pitch) VALUES(?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, card.getName());
            pstmt.setString(2, card.getType());
            pstmt.setInt(3, card.getAttack());
            pstmt.setInt(4, card.getDefense());
            pstmt.setInt(5, card.getCost());
            pstmt.setInt(6, card.getPitch());
            pstmt.executeUpdate();

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    card.setId(generatedKeys.getInt(1));
                }
            }

            cardCache.put(card.getId(), card);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Removes a card from the database based on the primary integer card_id.
     *
     * @param cardId The ID of the card to remove.
     */
    public void removeCard(int cardId) {
        String sql = "DELETE FROM Cards WHERE card_id = ?";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, cardId);
            pstmt.executeUpdate();

            cardCache.remove(cardId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Fetch all cards from the database.
     *
     * @return A List of all cards in the database.
     */
    public List<Card> getALLCards() {
        String sql = "SELECT * FROM Cards";
        List<Card> cards = new ArrayList<>();

        try (Connection conn = DatabaseUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet resultSet = stmt.executeQuery(sql)) {

            while (resultSet.next()) {
                int cardId = resultSet.getInt("card_id");
                Card card = cardCache.get(cardId);
                if (card == null) {
                    card = new Card(
                            resultSet.getString("name"),
                            resultSet.getString("type"),
                            resultSet.getInt("attack"),
                            resultSet.getInt("defense"),
                            resultSet.getInt("cost"),
                            resultSet.getInt("pitch")
                    );
                    addCard(card);
                    cardCache.put(card.getId(), card);
                }
                cards.add(card);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cards;
    }

    /**
     * Fetch a specific card from the database based on the primary integer card_id.
     *
     * @param id The ID of the Card to fetch.
     * @return The Card Object with the given card_id, or null if not found.
     */
    public Card getCardByID(int id) {
        Card card = cardCache.get(id);
        if (card != null) {
            return card;
        }
        String sql = "SELECT * FROM Cards WHERE card_id = ?";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            try (ResultSet resultSet = pstmt.executeQuery()) {
                if (resultSet.next()) {
                    card = new Card(
                            resultSet.getString("name"),
                            resultSet.getString("type"),
                            resultSet.getInt("attack"),
                            resultSet.getInt("defense"),
                            resultSet.getInt("cost"),
                            resultSet.getInt("pitch")
                    );

                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return card;
    }

    /**
     * Updates the given card object in the database.
     *
     * @param card The updated card object.
     */
    public void updateCard(Card card) {
        if (card.getId() == 0) {
            throw new IllegalArgumentException("Card does not exist in the database.");
        }
        String sql = "UPDATE Cards SET name = ?, type = ?, attack = ?, defense = ?, cost = ?, pitch = ? WHERE card_id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, card.getName());
            pstmt.setString(2, card.getType());
            pstmt.setInt(3, card.getAttack());
            pstmt.setInt(4, card.getDefense());
            pstmt.setInt(5, card.getCost());
            pstmt.setInt(6, card.getPitch());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
