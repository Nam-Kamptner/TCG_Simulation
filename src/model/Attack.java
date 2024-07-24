package model;

import java.util.List;
import java.util.Scanner;

public class Attack {
    private Player attacker;

    public Attack(Player attacker) {
        this.attacker = attacker;
    }

    public void declareAttack(CombatChain combatChain) {
        List<Card> hand = attacker.getHand();
        if (hand.isEmpty()) {
            System.out.println(attacker.getName() + " has no cards to attack with.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println(attacker.getName() + ", do you want to attack? (yes/no)");
        String response = scanner.nextLine();

        if (response.equalsIgnoreCase("yes")) {
            System.out.println("Choose a card to attack with:");
            for (int i = 0; i < hand.size(); i++) {
                Card card = hand.get(i);
                System.out.println((i + 1) + ". " + card.getName() + " (Attack: " + card.getAttack() + ", Cost: " + card.getCost() + ")");
            }
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            if (choice > 0 && choice <= hand.size()) {
                Card attackCard = hand.get(choice - 1);
                combatChain.declareAttack(attackCard);
                attacker.getHand().remove(attackCard);
            } else {
                System.out.println("Invalid choice. No attack declared.");
            }
        } else {
            System.out.println(attacker.getName() + " chooses not to attack.");
        }
    }
}
