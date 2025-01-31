import java.util.Random;

public class GameBE 
{
    private Fighter player;
    private Fighter enemy;
    private Random random;

    public GameBE(Fighter player) {
        this.random = new Random();
        this.player = player;
        this.enemy = chooseEnemy(player);
    }

    private Fighter determineFirst() {
        if (player.getSpeed() > enemy.getSpeed()) {
            return player;
        } else if (enemy.getSpeed() > player.getSpeed()) {
            return enemy;
        } else {
            return random.nextBoolean() ? player : enemy;
        }
    }

    public String executeTurn(int playerAttackIndex) {
        Fighter first = determineFirst();
        String battleLog = "";
        if (first == player) {
            battleLog = playerAttack(playerAttackIndex);
            if (!isBattleOver()) {
                battleLog += "\n" + enemyAttack();
            }
        } else {
            battleLog = enemyAttack();
            if (!isBattleOver()) {
                battleLog += "\n" + playerAttack(playerAttackIndex);
            }
        }
        return battleLog;
    }

    public String playerAttack(int attackIndex) {
        Attack attack = player.getAttacks(attackIndex);
        if (player.getEnergy() >= attack.getCost()) {
            int num = random.nextInt(101);
            if (num >= attack.getAccuracy()+enemy.getSpeed()/15) { 
                double damage = (attack.getStrength()*player.getPower()/enemy.getDefense());
                String formattedDamage = String.format("%.1f", damage);
                enemy.takeDamage(damage);
                player.useEnergy(attack.getCost());
                return player.getName()+" used " + attack.getName() + " dealing " + formattedDamage + " damage!";
            } else {
                return player.getName() +"'s attack missed!";
            }
        } else {
            return player.getName() + " does not have enough energy to use " + attack.getName();
        }
    }

    public String enemyAttack() {
        int attackIndex = random.nextInt(enemy.getAttacks().length);
        Attack attack = enemy.getAttacks()[attackIndex];
        if (enemy.getEnergy() >= attack.getCost()) {
            int num = random.nextInt(101);
            if (num >= attack.getAccuracy()+player.getSpeed()/15) {
                double damage = (attack.getStrength()*player.getPower()/enemy.getDefense());
                String formattedDamage = String.format("%.1f", damage);
                player.takeDamage(damage);
                enemy.useEnergy(attack.getCost());
                return enemy.getName()+" used " + attack.getName() + " dealing " + formattedDamage + " damage!";
            } else {
                return enemy.getName() +"'s attack missed!";
            }
        } else {
            return enemy.getName() + " does not have enough energy to use " + attack.getName();
        }
    }

    private Fighter chooseEnemy(Fighter player) {
        if (player == null) {
            // Handle the case where player is null, either return a default enemy or throw an exception
            throw new IllegalArgumentException("Player cannot be null");
        }
        while (true) {
            int num = random.nextInt(9);
            if (num == 0 && !(player.getName().equals("Goku")))
                return Fighter.gokuMaker();
            else if (num == 1 && !(player.getName().equals("Vegeta")))
                return Fighter.vegetaMaker();
            else if (num == 2 && !(player.getName().equals("Gohan")))
                return Fighter.gohanMaker();
            else if (num == 3 && !(player.getName().equals("Piccolo")))
                return Fighter.piccoloMaker();
            else if (num == 4 && !(player.getName().equals("Trunks")))
                return Fighter.trunksMaker();
            else if (num == 5 && !(player.getName().equals("Krillin")))
                return Fighter.krillinMaker();
            else if (num == 6 && !(player.getName().equals("Frieza")))
                return Fighter.friezaMaker();
            else if (num == 7 && !(player.getName().equals("Cell")))
                return Fighter.cellMaker();
            else if (num == 8 && !(player.getName().equals("Buu")))
                return Fighter.buuMaker();
            else
                continue;
        }
    }

    public boolean isBattleOver() {
        return player.getHealth() <= 0 || enemy.getHealth() <= 0;
    }

    public void resetBattle() {
        // Reset the player's health or any other state as needed
        player.setHealth(player.getMaxHealth());
        player.setEnergy(player.getMaxEnergy());
        
        // Choose a new enemy for the next round
        enemy = chooseEnemy(player);
    }

    public Fighter getPlayer() {
        return player;
    }
    
    public Fighter getEnemy() {
        return enemy;
    }
}