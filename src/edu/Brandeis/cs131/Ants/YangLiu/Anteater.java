package edu.Brandeis.cs131.Ants.YangLiu;

import edu.Brandeis.cs131.Ants.AbstractAnts.Colour;

public class Anteater extends MyAnimal {
	private String name;
	private Colour color;
	private static int priority = 2;
	private static int speed = 4;
	private static int hunger = 3;

	public Anteater(String name, Colour color) {
		super(name, color, priority, speed, hunger);
		this.name = name;
		this.color = color;
	}
	
	@Override
	public String toString() {
        return String.format("%s ANTEATER %s", color, name);
    }
}
