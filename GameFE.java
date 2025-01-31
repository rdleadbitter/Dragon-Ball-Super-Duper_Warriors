import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class GameFE extends Application {
    private GameBE game;
    private int round;
    private int score;
    private Label playerStatsLabel;
    private Label enemyStatsLabel;
    private Label battleLogLabel;
    private Label scoreLabel;
    private Button attack1Button, attack2Button, attack3Button, attack4Button, continueButton;
    private ImageView playerImageView, enemyImageView;

    @Override
    public void start(Stage primaryStage) {
        // Initial setup
        score = 0;
        round = 0;

        // UI elements
        VBox mainLayout = new VBox(10);
        HBox battleLayout = new HBox(20);
        battleLayout.setStyle("-fx-alignment: center;");
        mainLayout.setStyle("-fx-alignment: center;");

        // Labels to display stats
        playerStatsLabel = new Label("Player: ");
        enemyStatsLabel = new Label("Enemy: ");
        battleLogLabel = new Label("Battle Log: ");
        scoreLabel = new Label("Score: " + score);

        attack1Button = new Button("Attack 1");
        attack2Button = new Button("Attack 2");
        attack3Button = new Button("Attack 3");
        attack4Button = new Button("Attack 4");

        // Button colors for attacks
        attack1Button.setStyle("-fx-background-color: orange; -fx-text-fill: white;");
        attack2Button.setStyle("-fx-background-color: orange; -fx-text-fill: white;");
        attack3Button.setStyle("-fx-background-color: orange; -fx-text-fill: white;");
        attack4Button.setStyle("-fx-background-color: orange; -fx-text-fill: white;");
        
        // Button actions for attacks
        attack1Button.setOnAction(e -> handleAttack(0, primaryStage, mainLayout));
        attack2Button.setOnAction(e -> handleAttack(1, primaryStage, mainLayout));
        attack3Button.setOnAction(e -> handleAttack(2, primaryStage, mainLayout));
        attack4Button.setOnAction(e -> handleAttack(3, primaryStage, mainLayout));

        // Continue button (Diabled by default)
        continueButton = new Button("continue");
        continueButton.setDisable(true); // Only enabled after a player victory

        // Buttons to start a new game or select a new character after a loss
        Button restartButton = new Button("Play Again");
        restartButton.setOnAction(e -> endGame(primaryStage, mainLayout));
        continueButton.setOnAction(e -> prepareNextRound());

        // Player and Enemy character images
        playerImageView = new ImageView();
        enemyImageView = new ImageView();
        playerImageView.setFitWidth(150);
        playerImageView.setFitHeight(150);
        enemyImageView.setFitWidth(150);
        enemyImageView.setFitHeight(150);

        VBox playerBox = new VBox(10, playerImageView, playerStatsLabel);
        VBox enemyBox = new VBox(10, enemyImageView, enemyStatsLabel);

        // Add elements to the layout
        battleLayout.getChildren().addAll(playerBox, enemyBox);
        mainLayout.getChildren().addAll(battleLayout, battleLogLabel, scoreLabel, 
                                        attack1Button, attack2Button, attack3Button, attack4Button, continueButton);
        showCharacterSelectionScreen(primaryStage, mainLayout);
        
        // Initially, show the character selection screen
        showCharacterSelectionScreen(primaryStage, mainLayout);
    }

    private void showCharacterSelectionScreen(Stage primaryStage, VBox layout) {
        VBox characterSelectionLayout = new VBox(10);
        characterSelectionLayout.setStyle("-fx-alignment: center; -fx-background-color: lightblue;");

        Button chooseGoku = new Button("Choose Goku");
        Button chooseVegeta = new Button("Choose Vegeta");
        Button chooseGohan = new Button("Choose Gohan");
        Button choosePiccolo = new Button("Choose Piccolo");
        Button chooseTrunks = new Button("Choose Trunks");
        Button chooseKrillin = new Button("Choose Krillin");
        Button chooseFrieza = new Button("Choose Frieza");
        Button chooseCell = new Button("Choose Cell");
        Button chooseBuu = new Button("Choose Buu");

        chooseGoku.setStyle("-fx-background-color: orange; -fx-text-fill: white;");
        chooseVegeta.setStyle("-fx-background-color: blue; -fx-text-fill: white;");
        chooseGohan.setStyle("-fx-background-color: orange; -fx-text-fill: white;");
        choosePiccolo.setStyle("-fx-background-color: green; -fx-text-fill: white;");
        chooseTrunks.setStyle("-fx-background-color: purple; -fx-text-fill: white;");
        chooseKrillin.setStyle("-fx-background-color: orange; -fx-text-fill: white;");
        chooseFrieza.setStyle("-fx-background-color: purple; -fx-text-fill: white;");
        chooseCell.setStyle("-fx-background-color: green; -fx-text-fill: white;");
        chooseBuu.setStyle("-fx-background-color: pink; -fx-text-fill: white;");

        chooseGoku.setOnAction(e -> startGame(Fighter.gokuMaker(), primaryStage, layout));
        chooseVegeta.setOnAction(e -> startGame(Fighter.vegetaMaker(), primaryStage, layout));
        chooseGohan.setOnAction(e -> startGame(Fighter.gohanMaker(), primaryStage, layout));
        choosePiccolo.setOnAction(e -> startGame(Fighter.piccoloMaker(), primaryStage, layout));
        chooseTrunks.setOnAction(e -> startGame(Fighter.trunksMaker(), primaryStage, layout));
        chooseKrillin.setOnAction(e -> startGame(Fighter.krillinMaker(), primaryStage, layout));
        chooseFrieza.setOnAction(e -> startGame(Fighter.friezaMaker(), primaryStage, layout));
        chooseCell.setOnAction(e -> startGame(Fighter.cellMaker(), primaryStage, layout));
        chooseBuu.setOnAction(e -> startGame(Fighter.buuMaker(), primaryStage, layout));

        characterSelectionLayout.getChildren().addAll(chooseGoku, chooseVegeta, chooseGohan, choosePiccolo, chooseTrunks,
                                                        chooseKrillin, chooseFrieza, chooseCell, chooseBuu);

        Scene characterSelectionScene = new Scene(characterSelectionLayout, 600, 600);
        primaryStage.setScene(characterSelectionScene);
        primaryStage.setTitle("Character Selection");
        primaryStage.show();
    }

    // Start the game with selected player
    private void startGame(Fighter selectedPlayer, Stage primaryStage, VBox layout) {
        // Initialize the game backend
        game = new GameBE(selectedPlayer);

        // Update the UI with character stats
        updateStats();

        playerImageView.setImage(new Image(game.getPlayer().getImagePath()));
        enemyImageView.setImage(new Image(game.getEnemy().getImagePath()));
        enemyImageView.setScaleX(-1);

        // Transition to the battle screen after selection
        layout.setStyle("-fx-alignment: center; -fx-background-color: lightblue;");
        Scene battleScene = new Scene(layout, 600, 600);
        primaryStage.setScene(battleScene);
        primaryStage.setTitle("Battle Game");
        primaryStage.show();
    }

    private void handleAttack(int attackIndex, Stage primaryStage, VBox layout) {
        // Disable attack buttons during the turn
        disableAttackButtons(true);
    
        // Execute the turn (this will handle both player and enemy actions)
        String battleResult = game.executeTurn(attackIndex);
    
        // Update the battle log and stats with the results from executeTurn
        battleLogLabel.setText(battleResult);
        updateStats();
    
        // Check if the battle is over
        if (game.isBattleOver()) {
            if (game.getPlayer().getHealth() > 0) {
                // Player wins: Increment the round and allow them to continue to the next round
                round++;
                score += round * 10; // Increment score by 10 times the round number
                //updateStats();
                // Reset the battle, choose a new enemy, and continue fighting
                game.resetBattle(); // Reset enemy and health for the player
                battleLogLabel.setText("You won the round! Preparing for the next round...");
                continueButton.setDisable(false); // Enable Continue button
            } else {
                // Player loses, end the game
                endGame(primaryStage, layout);
            }
        } else {
            // Re-enable attack buttons for the player's next turn
            disableAttackButtons(false);
        }
    }

    private void endGame(Stage primaryStage, VBox layout) {
        // Display final message and score
        battleLogLabel.setText("Your final score: " + score);
        
        // Add an option for the player to restart or quit after the game ends
        Button playAgainButton = new Button("Play Again");
        playAgainButton.setOnAction(e -> restartGame(primaryStage, layout));
        VBox gameOverLayout = new VBox(10);
        gameOverLayout.setStyle("-fx-alignment: center;");
        gameOverLayout.getChildren().addAll(new Label("Game Over!"), battleLogLabel, playAgainButton);
        Scene gameOverScene = new Scene(gameOverLayout, 600, 600);
        primaryStage.setScene(gameOverScene);
        primaryStage.setTitle("Game Over");
        primaryStage.show();
    }

    private void restartGame(Stage primaryStage, VBox layout) {
        // Reset game and score
        score = 0;
        round = 0;
        
        // Show character selection screen again
        showCharacterSelectionScreen(primaryStage, layout);
    }

    private void prepareNextRound() {
        // Reset battle for the next round
        game.resetBattle();
        updateStats();

        // Reset battle log and disable the Continue button
        battleLogLabel.setText("New battle begins! Choose your attack.");
        continueButton.setDisable(true);
    }

    private void disableAttackButtons(boolean disable) {
        attack1Button.setDisable(disable);
        attack2Button.setDisable(disable);
        attack3Button.setDisable(disable);
        attack4Button.setDisable(disable);
    }

    private void updateStats() {
        // Update the player and enemy stats
        String formattedPlayerHealth = String.format("%.1f", game.getPlayer().getHealth());
        String formattedPlayerEnergy = String.format("%.1f", game.getPlayer().getEnergy());
        String formattedEnemyHealth = String.format("%.1f", game.getEnemy().getHealth());
        String formattedEnemyEnergy = String.format("%.1f", game.getEnemy().getEnergy());
        if (game.getEnemy().getHealth() < 0)
            formattedEnemyHealth = "0.0";
        playerStatsLabel.setText("Player: " + game.getPlayer().getName() + 
                                 " | Health: " + formattedPlayerHealth +
                                 " | Energy: " + formattedPlayerEnergy);

        enemyStatsLabel.setText("Enemy: " + game.getEnemy().getName() + 
                                " | Health: " + formattedEnemyHealth +
                                " | Energy: " + formattedEnemyEnergy);

        // Update the score and round
        scoreLabel.setText("Score: " + score + " | Round: " + (round+1));

        // Update the attack button labels based on the player's energy
        updateAttackButtonLabels();
    }

    private void updateAttackButtonLabels() {
        // Get the player's current energy
        double playerEnergy = game.getPlayer().getEnergy();
        Attack attack1 = game.getPlayer().getAttacks(0);
        Attack attack2 = game.getPlayer().getAttacks(1);
        Attack attack3 = game.getPlayer().getAttacks(2);
        Attack attack4 = game.getPlayer().getAttacks(3);
    
        // Update each attack button's label based on the cost of the attack
        attack1Button.setText(attack1.getName()+" (Cost: " + attack1.getCost() + ")");
        attack2Button.setText(attack2.getName()+" (Cost: " + attack2.getCost() + ")");
        attack3Button.setText(attack3.getName()+" (Cost: " + attack3.getCost() + ")");
        attack4Button.setText(attack4.getName()+" (Cost: " + attack4.getCost() + ")");
    
        // Disable buttons if the player doesn't have enough energy to use the attack
        attack1Button.setDisable(playerEnergy < game.getPlayer().getAttacks(0).getCost());
        attack2Button.setDisable(playerEnergy < game.getPlayer().getAttacks(1).getCost());
        attack3Button.setDisable(playerEnergy < game.getPlayer().getAttacks(2).getCost());
        attack4Button.setDisable(playerEnergy < game.getPlayer().getAttacks(3).getCost());
    }

    public static void main(String[] args) {
        launch();
    }
}