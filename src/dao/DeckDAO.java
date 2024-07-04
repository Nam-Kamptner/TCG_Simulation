package dao;

import model.Deck;
import util.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DeckDAO {

    public void addDeck(Deck deck) {
        String sql = "INSERT INTO decks (name, card_id, count) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            for (Map.Entry<Integer, Integer> entry : deck.getCardCounts().entrySet()) {
                pstmt.setString(1, deck.getDeckName());
                pstmt.setInt(2, entry.getKey());
                pstmt.setInt(3, entry.getValue());
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateDeck(Deck deck) {
        String updateSql = "UPDATE decks SET count = ? WHERE deckname = ? and card_id = ?";
        String insertSql = "INSERT INTO decks (deckname, card_id, count) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement updateStmt = updateStmt = conn.prepareStatement(updateSql);
             PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {

            Map<Integer, Integer> currentCounts = getCurrentCounts(deck.getDeckName());

            for (Map.Entry<Integer, Integer> entry : deck.getCardCounts().entrySet()) {
                int cardId = entry.getKey();
                int count = entry.getValue();

                if (currentCounts.containsKey(cardId)) {
                    if (currentCounts.get(cardId) != count) {
                        updateStmt.setInt(1, count);
                        updateStmt.setString(2, deck.getDeckName());
                        updateStmt.setInt(3, cardId);
                        updateStmt.executeUpdate();
                    }
                } else {
                    insertStmt.setString(1, deck.getDeckName());
                    insertStmt.setInt(2, cardId);
                    insertStmt.setInt(3, count);
                    insertStmt.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteDeck(String deckName) {
        String sql = "DELETE FROM decks WHERE deckname = ?";

        try (Connection conn = DatabaseUtil.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, deckName);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Deck getDeck(String deckName) {
        String sql = "SELECT card_id, count FROM decks WHERE deckname = ?";
        Deck deck = new Deck(deckName);

        try (Connection conn = DatabaseUtil.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, deckName);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int cardId = rs.getInt("card_id");
                int count = rs.getInt("count");
                for (int i = 0; i < count; i++) {
                    deck.addCard(cardId);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return deck;
    }

    public Map<Integer, Integer> getCurrentCounts(String deckName) {
        String sql = "SELECT card_id, count FROM decks WHERE deckname = ?";
        Map<Integer, Integer> currentCounts = new HashMap<>();

        try (Connection conn = DatabaseUtil.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, deckName);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int cardId = rs.getInt("card_id");
                int count = rs.getInt("count");
                currentCounts.put(cardId, count);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return currentCounts;
    }

}

