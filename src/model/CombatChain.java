package model;

import java.util.ArrayList;
import java.util.List;

public class CombatChain {
    private Player attacker;
    private Player defender;
    private Card attackCard;
    private List<Card> defenseCards;

    public CombatChain(Player attacker, Player defender) {
        this.attacker = attacker;
        this.defender = defender;
        this.defenseCards = new ArrayList<>();
    }

    public void declareAttack(Card attackCard) {
        this.attackCard = attackCard;
        System.out.println(attacker.getName() + " attacks with " + attackCard.getName() + " (Attack: " + attackCard.getAttack() + ")");
    }

    public void declareDefense(List<Card> defenseCards) {
        this.defenseCards = defenseCards;
        if (defenseCards.isEmpty()) {
            System.out.println(defender.getName() + " does not defend.");
        } else {
            System.out.println(defender.getName() + " defends with:");
            for (Card card : defenseCards) {
                System.out.println(" - " + card.getName() + " (Defense: " + card.getDefense() + ")");
            }
        }
    }

    public Card getAttackCard() {
        return attackCard;
    }

    public void resolve() {
        if (attackCard == null) {
            System.out.println("No attack declared.");
            return;
        }

        int totalDefense = defenseCards.stream().mapToInt(Card::getDefense).sum();
        int damage = attackCard.getAttack() - totalDefense;

        if (damage > 0) {
            defender.setHealth(defender.getHealth() - damage);
            System.out.println(defender.getName() + " takes " + damage + " damage and is now at " + defender.getHealth() + " health.");
        } else {
            System.out.println(defender.getName() + " successfully defended the attack.");
        }

        // Reset the combat chain for the next attack
        attackCard = null;
        defenseCards.clear();
    }
}
