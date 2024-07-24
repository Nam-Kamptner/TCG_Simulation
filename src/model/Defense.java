package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Defense {
    private Player defender;

    public Defense(Player defender) {
        this.defender = defender;
    }

    public void declareDefense(CombatChain combatChain) {
        List<Card> hand = defender.getHand();
        if (hand.isEmpty()) {
            System.out.println(defender.getName() + " has no cards to defend with.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println(defender.getName() + ", do you want to defend? (yes/no)");
        String response = scanner.nextLine();

        if (response.equalsIgnoreCase("yes")) {
            List<Card> defenseCards = new ArrayList<>();
            System.out.println("Choose cards to defend with (enter numbers separated by spaces):");
            for (int i = 0; i < hand.size(); i++) {
                Card card = hand.get(i);
                System.out.println((i + 1) + ". " + card.getName() + " (Defense: " + card.getDefense() + ")");
            }
            String[] choices = scanner.nextLine().split(" ");
            for (String choice : choices) {
                int cardIndex = Integer.parseInt(choice) - 1;
                if (cardIndex >= 0 && cardIndex < hand.size()) {
                    defenseCards.add(hand.get(cardIndex));
                } else {
                    System.out.println("Invalid choice: " + choice);
                }
            }
            combatChain.declareDefense(defenseCards);
            defender.getHand().removeAll(defenseCards);
        } else {
            System.out.println(defender.getName() + " chooses not to defend.");
        }
    }
}
