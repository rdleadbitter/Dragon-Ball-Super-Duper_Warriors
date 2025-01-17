/*
 * Ryan Leadbitter
 */
import java.util.*;
//import java.awt.*;
//import java.io.File;
//import javax.swing.*;
public class Game 
{
	private static Fighter opponent = new Fighter();
	private static Random rand = new Random();
	private static Scanner scan = new Scanner(System.in);
	public static void main(String[] args) 
	{
		System.out.println("DRAGON BALL Z: TENKAISHITTY");
		System.out.println();
		boolean joever = false;
		boolean quit = false;
		System.out.println("Enter 1 for 1 player\n"
                         + "Enter 2 for 2 players");
		int playerNum = 1;
		while (!quit)
		{
			String temp = scan.nextLine();
			if (temp.equals("1") || temp.equals("2"))
			{
				quit = true;
				playerNum = Integer.parseInt(temp);
			}
			else
				System.out.println("Invalid input.");
		}
		if (playerNum == 1)
		{
			Fighter player1 = new Fighter();
			String p1 = "";
			newTurn();
			int score = 0;
			int round = 0;
			while (!joever)
			{
				round++;
				System.out.println("Round: "+round);
				System.out.println("Score: "+score);
				System.out.println();
				quit = false;
				System.out.println("Choose your fighter:\n"
				         + "Goku\n"
						 + "Vegeta\n"
						 + "Gohan\n"
				         + "Piccolo\n"
						 + "Trunks\n"
				         + "Krillin\n"
						 + "Frieza\n"
						 + "Cell\n"
						 + "Buu");
				System.out.println("Player 1, select fighter");
				quit = false;
				while (!quit)
				{
					p1 = scan.nextLine();
					if (charIsValid(p1))
						quit = true;
				}
				player1 = characterSelect(p1);
				characterSelectOpp(player1.getName());
				System.out.println();
				System.out.println("You selected: "+player1.getName());
				System.out.println("Your opponent: " +opponent.getName());
				System.out.println();
				System.out.println("FIGHT!");
				System.out.println();
				while(player1.getHealth() > 0 && opponent.getHealth() > 0)
				{
					quit = false;
					String input = "";
					player1.printStats();
					opponent.printStats();
					System.out.println();
					if (player1.getEnergy() <= 0 && opponent.getEnergy() > 0)
					{
						System.out.println(player1.getName()+" is out of energy!");
						move(null, opponent, player1);
						if (healthCheck(playerNum, player1, opponent))
							break;
					}
					else if (opponent.getEnergy() <= 0 && player1.getEnergy() <= 0)
					{
						System.out.println("Both fighters are out of energy!");
						break;
					}
					else if (player1.getEnergy() > 0)
					{
						System.out.println("Select your move");
						player1.printAttacks();
						while (!quit)
						{
							input = scan.nextLine();
							if (isValid(input))
								quit = true;
						}
						if (opponent.getEnergy() <= 0)
						{
							System.out.println(opponent.getName()+" is out of energy!");
							move(input, player1, opponent);
							if (healthCheck(playerNum, player1, opponent))
								break;
						}
						else if (opponent.getEnergy() > 0)
						{
							if (player1.getSpeed() >= opponent.getSpeed())
							{
								move(input, player1, opponent);
								if (healthCheck(playerNum, player1, opponent))
									break;
								move(null, opponent, player1);
								if (healthCheck(playerNum, player1, opponent))
									break;
							}
							else if (player1.getSpeed() < opponent.getSpeed())
							{
								move(null, opponent, player1);
								if (healthCheck(playerNum, player1, opponent))
									break;
								move(input, player1, opponent);
								if (healthCheck(playerNum, player1, opponent))
									break;
							}
						}
					}
					newTurn();
				}
				newTurn();
				player1.printStats();
				opponent.printStats();
				boolean lost = false;
				if (opponent.getHealth() <= 0 && player1.getHealth() > 0)
				{
					System.out.println("YOU WIN!");
					score += round*10;
				}
				else if (opponent.getHealth() > 0 && player1.getHealth() <= 0)
				{
					System.out.println("YOU LOSE!");
					lost = true;
				}
				else
				{
					System.out.println("IT'S A DRAW!");
					lost = true;
				}
				System.out.println();
				System.out.println("Press any button to continue, enter 0 to quit.");
				String answer = scan.nextLine();
				if (answer.equalsIgnoreCase("0"))
				{
					System.out.println("Goodbye.");
					joever = true;
				}
				else
				{
					if (lost)
					{
						round = 0;
						score = 0;
					}
				}
			}
			System.out.println("Score: "+score);
		}
		else if (playerNum == 2)
		{
			Fighter player1 = new Fighter();
			Fighter player2 = new Fighter();
			String p1 = "";
			String p2 = "";
			newTurn();
			int score1 = 0;
			int score2 = 0;
			int round = 0;
			int multi1 = 0;
			int multi2 = 0;
			while (!joever)
			{
				round++;
				System.out.println("Round: "+round);
				System.out.println("P1 Score: "+score1);
				System.out.println("P2 Score: "+score2);
				System.out.println();
				System.out.println("Choose your fighter:\n"
				         + "Goku\n"
						 + "Vegeta\n"
						 + "Gohan\n"
				         + "Piccolo\n"
						 + "Trunks\n"
				         + "Krillin\n"
						 + "Frieza\n"
						 + "Cell\n"
						 + "Buu");
				System.out.println();
				System.out.println("Player 1, select fighter");
				quit = false;
				while (!quit)
				{
					p1 = scan.nextLine();
					if (charIsValid(p1))
						quit = true;
				}
				player1 = characterSelect(p1);
				System.out.println("Player 2, select fighter");
				quit = false;
				while (!quit)
				{
					p2 = scan.nextLine();
					if (charIsValid(p2))
						quit = true;
				}
				player2 = characterSelect(p2);
				System.out.println("P1 selected: " +player1.getName());
				System.out.println("P2 selected: " +player2.getName());
				System.out.println();
				System.out.println("FIGHT!");
				System.out.println();
				while(player1.getHealth() > 0 && player2.getHealth() > 0)
				{
					quit = false;
					String input1 = "";
					String input2 = "";
					player1.printStats();
					player2.printStats();
					System.out.println();
					if (player1.getEnergy() <= 0 && player2.getEnergy() <= 0)
					{
						System.out.println("Both fighters are out of energy!");
						break;
					}
					else if (player1.getEnergy() <= 0 && player2.getEnergy() > 0)
					{
						System.out.println(player1.getName()+" is out of energy!");
						System.out.println("P2, Select your move");
						player2.printAttacks();
						while (!quit)
						{
							input2 = scan.nextLine();
							if (isValid(input2))
								quit = true;
						}
						move(input2, player2, player1);
						if (healthCheck(playerNum, player1, player2))
							break;
					}
					else if (player2.getEnergy() <= 0 && player1.getEnergy() > 0)
					{
						System.out.println(player2.getName()+" is out of energy!");
						System.out.println("P1, Select your move");
						player1.printAttacks();
						while (!quit)
						{
							input1 = scan.nextLine();
							if (isValid(input1))
								quit = true;
						}
						move(input1, player1, player2);
						if (healthCheck(playerNum, player1, player2))
							break;
					}
					else if (player1.getEnergy() > 0 && player2.getEnergy() > 0)
					{
						System.out.println("P1, Select your move");
						player1.printAttacks();
						quit = false;
						while (!quit)
						{
							input1 = scan.nextLine();
							if (isValid(input1))
								quit = true;
						}
						System.out.println("P2, Select your move");
						player2.printAttacks();
						quit = false;
						while (!quit)
						{
							input2 = scan.nextLine();
							if (isValid(input2))
								quit = true;
						}
						if (player1.getSpeed() > player2.getSpeed())
						{
							move(input1, player1, player2);
							if (healthCheck(playerNum, player1, player2))
								break;
							move(input2, player2, player1);
							if (healthCheck(playerNum, player2, player1))
								break;
						}	
						else if (player2.getSpeed() > player1.getSpeed())
						{
							move(input2, player2, player1);
							if (healthCheck(playerNum, player1, player2))
								break;
							move(input1, player1, player2);
							if (healthCheck(playerNum, player1, player2))
								break;
						}
						else if (player1.getSpeed() == player2.getSpeed())
						{
							int random = rand.nextInt(2);
							if (random == 0)
							{
								move(input1, player1, player2);
								if (healthCheck(playerNum, player1, player2))
									break;
								move(input2, player2, player1);
								if (healthCheck(playerNum, player1, player2))
									break;
							}
							else if (random == 1)
							{
								move(input2, player2, player1);
								if (healthCheck(playerNum, player1, player2))
									break;
								move(input1, player1, player2);
								if (healthCheck(playerNum, player1, player2))
									break;
							}
						}
					}
					newTurn();
				}
				newTurn();
				player1.printStats();
				player2.printStats();
				if (player2.getHealth() <= 0 && player1.getHealth() > 0)
				{
					System.out.println("P1 WINS!");
					multi1++;
					score1 += multi1*10;
				}
				else if (player2.getHealth() > 0 && player1.getHealth() <= 0)
				{
					System.out.println("P2 WINS!");
					multi2++;
					score2 += multi2*10;
				}
				else
				{
					System.out.println("IT'S A DRAW!");
				}
				System.out.println();
				System.out.println("Press any button to continue, enter 0 to quit.");
				String answer = scan.nextLine();
				if (answer.equalsIgnoreCase("0"))
				{
					System.out.println("Goodbye.");
					joever = true;
				}
			}
			System.out.println("P1 Score: "+score1);
			System.out.println("P2 Score: "+score2);
		}
	}
	public static Fighter characterSelect(String choice)
	{
		if (choice.equalsIgnoreCase("goku"))
			return gokuMaker();
		else if (choice.equalsIgnoreCase("vegeta"))
			return vegetaMaker();
		else if (choice.equalsIgnoreCase("gohan"))
			return gohanMaker();
		else if (choice.equalsIgnoreCase("piccolo"))
			return piccoloMaker();
		else if (choice.equalsIgnoreCase("trunks"))
			return trunksMaker();
		else if (choice.equalsIgnoreCase("krillin"))
			return krillinMaker();
		else if (choice.equalsIgnoreCase("frieza"))
			return friezaMaker();
		else if (choice.equalsIgnoreCase("cell"))
			return cellMaker();
		else if (choice.equalsIgnoreCase("buu"))
			return buuMaker();
		else
			return null;
	}
	public static void characterSelectOpp(String pName)
	{
		boolean names = false;
		while (names == false)
		{
			int num = rand.nextInt(9);
			if (num == 0)
				opponent = gokuMaker();
			else if (num == 1)
				opponent = vegetaMaker();
			else if (num == 2)
				opponent = gohanMaker();
			else if (num == 3)
				opponent = piccoloMaker();
			else if (num == 4)
				opponent = trunksMaker();
			else if (num == 5)
				opponent = krillinMaker();
			else if (num == 6)
				opponent = friezaMaker();
			else if (num == 7)
				opponent = cellMaker();
			else if (num == 8)
				opponent = buuMaker();
			if (pName.equals(opponent.getName()))
				continue;
			else
				names = true;
		}
	}
	public static boolean missCheck(Attack att, Fighter target)
	{
		int num = rand.nextInt(101);
		if (num >= att.getAccuracy()+target.getSpeed()/25)
			return true;
		else
			return false;
	}
	public static boolean healthCheck(int pNum, Fighter p1, Fighter p2)
	{
		if (pNum == 1)
		{
			if (p1.getHealth() <= 0 || opponent.getHealth() <= 0)
				return true;
			else
				return false;
		}
		else
		{
			if (p1.getHealth() <= 0 || p2.getHealth() <= 0)
				return true;
			else
				return false;
		}
	}
	public static boolean charIsValid(String input)
	{
		if (input.equalsIgnoreCase("goku"))
			return true;
		else if (input.equalsIgnoreCase("vegeta"))
			return true;
		else if (input.equalsIgnoreCase("piccolo"))
			return true;
		else if (input.equalsIgnoreCase("gohan"))
			return true;
		else if (input.equalsIgnoreCase("frieza"))
			return true;
		else if (input.equalsIgnoreCase("krillin"))
			return true;
		else if (input.equalsIgnoreCase("cell"))
			return true;
		else if (input.equalsIgnoreCase("buu"))
			return true;
		else if (input.equalsIgnoreCase("trunks"))
			return true;
		else
		{
			System.out.println("Invalid input.");
			return false;
		}
	}
	public static boolean isValid(String input)
	{
		if (input.equalsIgnoreCase("a") || input.equalsIgnoreCase("b") ||
		    input.equalsIgnoreCase("x") || input.equalsIgnoreCase("y"))
			return true;
		else
		{
			System.out.println("Invalid input. Select again.");
			return false;
		}
	}
	public static void move(String input, Fighter user, Fighter target)
	{
		Attack attack = new Attack();
		if (user == opponent)
		{
			int i = rand.nextInt(4);
			if (i == 0)
				attack = user.getAttacks(0);
			else if (i == 1)
				attack = user.getAttacks(1);
			else if (i == 2)
				attack = user.getAttacks(2);
			else if (i == 3)
				attack = user.getAttacks(3);
		}
		else
		{
			if (input.equalsIgnoreCase("a"))
				attack = user.getAttacks(0);
			else if (input.equalsIgnoreCase("b"))
				attack = user.getAttacks(1);
			else if (input.equalsIgnoreCase("x"))
				attack = user.getAttacks(2);
			else if (input.equalsIgnoreCase("y"))
				attack = user.getAttacks(3);
			else
			{
				System.out.println("Invalid input. Select again.");
			}
		}
		
		if (attack.getCost() <= user.getEnergy())
		{
			user.setEnergy(user.getEnergy()-attack.getCost());
			if (missCheck(attack, target))
			{
				double damage = attack.getStrength()*user.getPower()/target.getDefense();
				damage=Math.round(damage);
				System.out.println(user.getName() + " used " +attack.getName());
				System.out.println("\t"+damage+" damage!");
				target.setHealth(target.getHealth()-damage);
			}
			else
			{
				System.out.println(user.getName()+" tried to use "+attack.getName()+"..."+
								   "\n\t"+user.getName()+"'s attack missed!");
			}
		}
		else
		{
			System.out.println(user.getName()+" tried to use "+attack.getName()+"..."+
						       "\n\t"+user.getName()+" doesn't have enough energy!");
		}
	}
	public static Fighter gokuMaker()
	{
		Attack[] attacks = new Attack[4];
		attacks[0] = new Attack("Punch", 100, 10, 10);
		attacks[1] = new Attack("Ki blast", 150, 50, 15);
		attacks[2] = new Attack("Kamehameha", 250, 100, 25);
		attacks[3] = new Attack("Spirit bomb", 500, 250, 50);
		Fighter goku = new Fighter("Goku", 1000, 1000, 200, 200, 200, attacks);
		return goku;
	}
	public static Fighter vegetaMaker()
	{
		Attack[] attacks = new Attack[4];
		attacks[0] = new Attack("Punch", 100, 10, 10);
		attacks[1] = new Attack("Ki blast", 150, 50, 15);
		attacks[2] = new Attack("Galick gun", 250, 100, 25);
		attacks[3] = new Attack("Final flash", 500, 250, 50);
		Fighter vegeta = new Fighter("Vegeta", 1000, 1000, 175, 250, 175, attacks);
		return vegeta;
	}
	public static Fighter gohanMaker()
	{
		Attack[] attacks = new Attack[4];
		attacks[0] = new Attack("Punch", 100, 10, 10);
		attacks[1] = new Attack("Ki blast", 150, 50, 15);
		attacks[2] = new Attack("Masenko", 250, 100, 25);
		attacks[3] = new Attack("Super kamehameha", 500, 250, 50);
		Fighter gohan = new Fighter("Gohan", 1000, 1000, 225, 150, 225, attacks);
		return gohan;
	}
	public static Fighter piccoloMaker()
	{
		Attack[] attacks = new Attack[4];
		attacks[0] = new Attack("Punch", 100, 10, 10);
		attacks[1] = new Attack("Ki blast", 150, 50, 15);
		attacks[2] = new Attack("Light grenade", 250, 100, 25);
		attacks[3] = new Attack("Makankosappo", 500, 250, 50);
		Fighter piccolo = new Fighter("Piccolo", 1000, 1000, 125, 275, 200, attacks);
		return piccolo;
	}
	public static Fighter trunksMaker()
	{
		Attack[] attacks = new Attack[4];
		attacks[0] = new Attack("Sword slash", 100, 10, 10);
		attacks[1] = new Attack("Ki blast", 150, 50, 15);
		attacks[2] = new Attack("Burning Attack", 250, 100, 25);
		attacks[3] = new Attack("Heat Dome Attack", 500, 250, 50);
		Fighter trunks = new Fighter("Trunks", 1000, 1000, 175, 225, 200, attacks);
		return trunks;
	}
	public static Fighter krillinMaker()
	{
		Attack[] attacks = new Attack[4];
		attacks[0] = new Attack("Punch", 100, 10, 10);
		attacks[1] = new Attack("Solar Flare", 150, 50, 15);
		attacks[2] = new Attack("Kamehameha", 250, 100, 25);
		attacks[3] = new Attack("Destructo Disk", 500, 250, 50);
		Fighter frieza = new Fighter("Krillin", 1000, 1000, 50, 50, 1000, attacks);
		return frieza;
	}
	public static Fighter friezaMaker()
	{
		Attack[] attacks = new Attack[4];
		attacks[0] = new Attack("Punch", 100, 10, 10);
		attacks[1] = new Attack("Ki blast", 150, 50, 15);
		attacks[2] = new Attack("Death Beam", 250, 100, 25);
		attacks[3] = new Attack("Death Ball", 500, 250, 50);
		Fighter frieza = new Fighter("Frieza", 1000, 1000, 275, 125, 200, attacks);
		return frieza;
	}
	public static Fighter cellMaker()
	{
		Attack[] attacks = new Attack[4];
		attacks[0] = new Attack("Punch", 100, 10, 10);
		attacks[1] = new Attack("Ki blast", 150, 50, 15);
		attacks[2] = new Attack("Kamehameha", 250, 100, 25);
		attacks[3] = new Attack("Solar Kamehameha", 500, 250, 50);
		Fighter buu = new Fighter("Cell", 1000, 1000, 250, 150, 200, attacks);
		return buu;
	}
	public static Fighter buuMaker()
	{
		Attack[] attacks = new Attack[4];
		attacks[0] = new Attack("Punch", 100, 10, 10);
		attacks[1] = new Attack("Ki blast", 150, 50, 15);
		attacks[2] = new Attack("Explosion", 250, 100, 25);
		attacks[3] = new Attack("Turn into candy", 500, 250, 50);
		Fighter buu = new Fighter("Buu", 1000, 1000, 200, 300, 100, attacks);
		return buu;
	}
	public static void newTurn()
	{
		System.out.println("---------------------------------------------------------------------");
	}
}