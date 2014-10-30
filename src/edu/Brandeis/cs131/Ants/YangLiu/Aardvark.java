package edu.Brandeis.cs131.Ants.YangLiu;

import edu.Brandeis.cs131.Ants.AbstractAnts.Colour;

public class Aardvark extends MyAnimal {
	private String name;
	private Colour color;
	private static int priority = 1;
	private static int speed = 8;
	private static int hunger = 3;
	
	public Aardvark(String name, Colour color) {
		super(name, color, priority, speed, hunger);
		this.name = name;
		this.color = color;
	}
	
	@Override
	public String toString() {
        return String.format("%s AARDVARK %s", color, name);
    }

}
