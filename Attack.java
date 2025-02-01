/*
 * Author: Ryan Leadbitter
 */

public class Attack 
{
	private String name;
    double strength;
	private double cost;  // correlates with fighter energy
	private int accuracy;  // used for miss check calculations
	public Attack()
	{
		this.name = "none";
		this.strength = 0;
		this.cost = 0;
		this.accuracy = 0;
	}
	public Attack(String n, double s, double c, int a)
	{
		this.name = n;
		this.strength = s;
		this.cost = c;
		this.accuracy = a;
	}
	public double getStrength() {
		return strength;
	}
	public void setStrength(double strength) {
		this.strength = strength;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public int getAccuracy() {
		return accuracy;
	}
	public void setAccuracy(int accuracy) {
		this.accuracy = accuracy;
	}
}
