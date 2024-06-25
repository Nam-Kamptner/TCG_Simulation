package dao;
import model.Card;
import util.DatabaseUtil;

public class CardDAO {

    public void addCard(Card card) {
        String sql = "INSERT INTO Cards(name, type, attack, defense, cost, pitch) VALUES(?, ?, ?, ?, ?, ?)";
    }
}
