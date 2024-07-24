
import dao.CardDAO;
import model.*;
import util.DatabaseUtil;

public class Game {
    private Player player1;
    private Player player2;
    private CardDAO cardDAO;
    private boolean gameOver;

    public Game() {
        DatabaseUtil.initialize();
        cardDAO = new CardDAO();
        initializePlayers();
        gameOver = false;
    }

    private void initializePlayers() {
        Deck deck1 = new Deck("Deck 1");
        Deck deck2 = new Deck("Deck 2");

        // Add cards to the decks (assume card IDs exist in the database)
        for (int i = 0; i < 20; i++) {
            deck1.addCard(i);
            deck1.addCard(i);
     }
        for (int i = 20; i < 41; i++) {
            deck2.addCard(i);
            deck2.addCard(i);
        }

        player1 = new Player("Player 1", 20, deck1);
        player2 = new Player("Player 2", 20, deck2);

        // Draw starting hands
        player1.drawStartingHand(cardDAO);
        player2.drawStartingHand(cardDAO);
    }

    public void startGame() {
        Player currentPlayer = player1;
        Player opponent = player2;

        while (!gameOver) {
            playTurn(currentPlayer, opponent);

            // Check win conditions
            if (opponent.getHealth() <= 0) {
                System.out.println(currentPlayer.getName() + " wins!");
                gameOver = true;
                break;
            }

            // Swap players
            Player temp = currentPlayer;
            currentPlayer = opponent;
            opponent = temp;
        }
    }

    private void playTurn(Player currentPlayer, Player opponent) {
        System.out.println(currentPlayer.getName() + "'s turn:");

        drawPhase(currentPlayer);
        mainPhase(currentPlayer, opponent);
        combatPhase(currentPlayer, opponent);
        endPhase(currentPlayer);

        System.out.println(currentPlayer.getName() + " ends their turn.");
    }

    private void drawPhase(Player player) {
        System.out.println(player.getName() + " is drawing a card.");
        player.drawCard(cardDAO);
    }

    private void mainPhase(Player player, Player opponent) {
        System.out.println(player.getName() + " is in the main phase.");
    }

    private void combatPhase(Player attacker, Player defender) {
        System.out.println(attacker.getName() + " is in the combat phase.");

        CombatChain combatChain = new CombatChain(attacker, defender);
        Attack attack = new Attack(attacker);
        Defense defense = new Defense(defender);

        attack.declareAttack(combatChain);

        if (combatChain.getAttackCard() != null) {
            defense.declareDefense(combatChain);
            combatChain.resolve();
        }
    }

    private void endPhase(Player player) {
        // Reset action points and prepare for the next turn
        player.setActionPoints(1);
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.startGame();
    }
}
