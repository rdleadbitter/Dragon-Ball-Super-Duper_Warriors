
public class Fighter 
{
	private String name;
	private double health;
	private double energy;
	private double power;
	private double defense;
	private double speed;
	private Attack[] attacks;
	public Fighter()
	{
		this.name = "none";
		this.health = 0;
		this.energy = 0;
		this.power = 0;
		this.defense = 0;
		this.speed = 0;
	}
	public Fighter(String n, double h, double k, double p, double d, double s, Attack[] a)
	{
		this.name = n;
		this.health = h;
		this.energy = k;
		this.power = p;
		this.defense = d;
		this.speed = s;
		this.attacks = a;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getHealth() {
		return health;
	}
	public void setHealth(double health) {
		this.health = health;
	}
	public double getEnergy() {
		return energy;
	}
	public void setEnergy(double ki) {
		this.energy = ki;
	}
	public double getPower() {
		return power;
	}
	public void setPower(double power) {
		this.power = power;
	}
	public double getDefense() {
		return defense;
	}
	public void setDefense(double defense) {
		this.defense = defense;
	}
	public double getSpeed() {
		return speed;
	}
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	public Attack getAttacks(int i)
	{
		return attacks[i];
	}
	public void printStats()
	{
		if (energy > 0 && health > 0)
			System.out.println(name +" - Health: "+health+"/1000.0, Energy: "+energy+"/1000.0");
		else if (energy <= 0 && health > 0)
			System.out.println(name +" - Health: "+health+"/1000.0, Energy: "+0.0+"/1000.0");
		else if (energy > 0 && health <= 0)
			System.out.println(name +" - Health: "+0.0+"/1000.0, Energy: "+energy+"/1000.0");
		else
			System.out.println(name +" - Health: "+0.0+"/1000.0, Energy: "+0.0+"/1000.0");
	}
	public void printAttacks()
	{
		System.out.println("a - "+attacks[0].getName()+" (Power: "+attacks[0].getStrength()+", Cost: "+attacks[0].getCost()+", Acc: "+(100-attacks[0].getAccuracy())+")"+
						 "\nb - "+attacks[1].getName()+" (Power: "+attacks[1].getStrength()+", Cost: "+attacks[1].getCost()+", Acc: "+(100-attacks[1].getAccuracy())+")"+
						 "\nx - "+attacks[2].getName()+" (Power: "+attacks[2].getStrength()+", Cost: "+attacks[2].getCost()+", Acc: "+(100-attacks[2].getAccuracy())+")"+
						 "\ny - "+attacks[3].getName()+" (Power: "+attacks[3].getStrength()+", Cost: "+attacks[3].getCost()+", Acc: "+(100-attacks[3].getAccuracy())+")");
	}
}
