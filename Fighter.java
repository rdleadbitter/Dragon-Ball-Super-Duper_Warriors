/*
 * Author: Ryan Leadbitter
 */

public class Fighter 
{
	private String name;
	private double health;
    private double maxHealth;
	private double energy;  // Correlates with attack cost
    private double maxEnergy;
	private double power;  // character strength: used for damage calculations
	private double defense;  // character constitution: used for damage calculations
	private double speed;  // determines move order and elusiveness
	private Attack[] attacks;
    private String imagePath;  // path for sprite png
    private String color;  // color for character's button
	public Fighter()
	{
		this.name = "none";
		this.health = 0;
        this.maxHealth = 0;
		this.energy = 0;
        this.maxEnergy = 0;
		this.power = 0;
		this.defense = 0;
		this.speed = 0;
	}
	public Fighter(String n, double h, double k, double p, double d, double s, Attack[] a, String i, String c)
	{
		this.name = n;
		this.health = h;
        this.maxHealth = h;
		this.energy = k;
        this.maxEnergy = k;
		this.power = p;
		this.defense = d;
		this.speed = s;
		this.attacks = a;
        this.imagePath = i;
        this.color = c;
	}
	public String getName() {
		return name;
	}
	public double getHealth() {
		return health;
	}
    public void setHealth(double health) {
        this.health = health;
    }
    public double getMaxHealth() {
        return maxHealth;
    }
	public double getEnergy() {
		return energy;
	}
    public void setEnergy(double energy) {
        this.energy = energy;
    }
    public double getMaxEnergy() {
        return maxEnergy;
    }
	public double getPower() {
		return power;
	}
	public double getDefense() {
		return defense;
	}
	public double getSpeed() {
		return speed;
	}
    public Attack[] getAttacks() {
        return attacks;
    }
	public Attack getAttacks(int i)
	{
		return attacks[i];
	}
    public String getImagePath() {
        return imagePath;
    }
    public void takeDamage(double damage) {
        health -= damage;
    }
    public void useEnergy(double cost) {
        energy -= cost;
    }
    public String getColor() {
        return color;
    }

    // Pre made characters
    public static Fighter gokuMaker() {
        Attack[] attacks = new Attack[4];
        String image = "file:images/goku.png";
        attacks[0] = new Attack("Punch", 100, 10, 10);
        attacks[1] = new Attack("Ki blast", 150, 50, 15);
        attacks[2] = new Attack("Kamehameha", 250, 100, 25);
        attacks[3] = new Attack("Spirit bomb", 500, 250, 50);
        Fighter goku = new Fighter("Goku", 1000, 1000, 200, 200, 200, attacks, image, "darkorange");
        return goku;
    }
    public static Fighter vegetaMaker() {
        Attack[] attacks = new Attack[4];
        String image = "file:images/vegeta.png";
        attacks[0] = new Attack("Punch", 100, 10, 10);
        attacks[1] = new Attack("Ki blast", 150, 50, 15);
        attacks[2] = new Attack("Galick gun", 250, 100, 25);
        attacks[3] = new Attack("Final flash", 500, 250, 50);
        Fighter vegeta = new Fighter("Vegeta", 1000, 1000, 175, 250, 175, attacks, image, "blue");
        return vegeta;
    }
    public static Fighter gohanMaker() {
        Attack[] attacks = new Attack[4];
        String image = "file:images/gohan.png";
        attacks[0] = new Attack("Punch", 100, 10, 10);
        attacks[1] = new Attack("Ki blast", 150, 50, 15);
        attacks[2] = new Attack("Masenko", 250, 100, 25);
        attacks[3] = new Attack("Super kamehameha", 500, 250, 50);
        Fighter gohan = new Fighter("Gohan", 1000, 1000, 225, 150, 225, attacks, image, "darkorange");
        return gohan;
    }
    public static Fighter piccoloMaker() {
        Attack[] attacks = new Attack[4];
        String image = "file:images/piccolo.png";
        attacks[0] = new Attack("Punch", 100, 10, 10);
        attacks[1] = new Attack("Ki blast", 150, 50, 15);
        attacks[2] = new Attack("Light grenade", 250, 100, 25);
        attacks[3] = new Attack("Makankosappo", 500, 250, 50);
        Fighter piccolo = new Fighter("Piccolo", 1000, 1000, 125, 275, 200, attacks, image, "green");
        return piccolo;
    }
    public static Fighter trunksMaker() {
        Attack[] attacks = new Attack[4];
        String image = "file:images/trunks.png";
        attacks[0] = new Attack("Sword slash", 100, 10, 10);
        attacks[1] = new Attack("Ki blast", 150, 50, 15);
        attacks[2] = new Attack("Burning Attack", 250, 100, 25);
        attacks[3] = new Attack("Heat Dome Attack", 500, 250, 50);
        Fighter trunks = new Fighter("Trunks", 1000, 1000, 175, 225, 200, attacks, image, "mediumpurple");
        return trunks;
    }
    public static Fighter krillinMaker() {
        Attack[] attacks = new Attack[4];
        String image = "file:images/krillin.png";
        attacks[0] = new Attack("Punch", 100, 10, 10);
        attacks[1] = new Attack("Solar Flare", 150, 50, 15);
        attacks[2] = new Attack("Kamehameha", 250, 100, 25);
        attacks[3] = new Attack("Destructo Disk", 500, 250, 50);
        Fighter krillin = new Fighter("Krillin", 1000, 1000, 50, 50, 250, attacks, image, "orange");
        return krillin;
    }
    public static Fighter friezaMaker() {
        Attack[] attacks = new Attack[4];
        String image = "file:images/frieza.png";
        attacks[0] = new Attack("Punch", 100, 10, 10);
        attacks[1] = new Attack("Ki blast", 150, 50, 15);
        attacks[2] = new Attack("Death Beam", 250, 100, 25);
        attacks[3] = new Attack("Death Ball", 500, 250, 50);
        Fighter frieza = new Fighter("Frieza", 1000, 1000, 275, 125, 200, attacks, image, "blueviolet");
        return frieza;
    }
    public static Fighter cellMaker() {
        Attack[] attacks = new Attack[4];
        String image = "file:images/cell.png";
        attacks[0] = new Attack("Punch", 100, 10, 10);
        attacks[1] = new Attack("Ki blast", 150, 50, 15);
        attacks[2] = new Attack("Kamehameha", 250, 100, 25);
        attacks[3] = new Attack("Solar Kamehameha", 500, 250, 50);
        Fighter cell = new Fighter("Cell", 1000, 1000, 250, 150, 200, attacks, image, "yellowgreen");
        return cell;
    }
    public static Fighter buuMaker() {
        Attack[] attacks = new Attack[4];
        String image = "file:images/buu.png";
        attacks[0] = new Attack("Punch", 100, 10, 10);
        attacks[1] = new Attack("Ki blast", 150, 50, 15);
        attacks[2] = new Attack("Explosion", 250, 100, 25);
        attacks[3] = new Attack("Turn into candy", 500, 250, 50);
        Fighter buu = new Fighter("Buu", 1000, 1000, 200, 300, 100, attacks, image, "pink");
        return buu;
    }
}
