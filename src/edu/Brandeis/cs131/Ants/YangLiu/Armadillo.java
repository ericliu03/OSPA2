package edu.Brandeis.cs131.Ants.YangLiu;

import edu.Brandeis.cs131.Ants.AbstractAnts.Colour;

public class Armadillo extends MyAnimal {
	private String name;
	private Colour color;
	private static int priority = 2;
	private static int speed = 6;
	private static int hunger = 2;

	public Armadillo(String name, Colour color) {
		super(name, color, priority, speed, hunger);
		this.name = name;
		this.color = color;
	}
	
	@Override
	public String toString() {
        return String.format("%s ARMADILLO %s", color, name);
    }
}
