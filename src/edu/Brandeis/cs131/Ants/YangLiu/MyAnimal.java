package edu.Brandeis.cs131.Ants.YangLiu;

import edu.Brandeis.cs131.Ants.AbstractAnts.Animal;
import edu.Brandeis.cs131.Ants.AbstractAnts.Anthill;
import edu.Brandeis.cs131.Ants.AbstractAnts.Colour;

public abstract class MyAnimal extends Animal implements Comparable<Animal>{

	protected Anthill onWhichAnthill;
	
    public MyAnimal(String name, Colour color, int priority, int speed, int hunger) {
        super(name, color, priority, speed, hunger);
    }
    
	public synchronized void waitForHill(){
		try {
			this.wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void giveWhichHill(Anthill anthill) {
		this.onWhichAnthill = anthill;
	}
	
	public Anthill getWhichHill() {
		return this.onWhichAnthill;
	}
	
	public synchronized void notifyOthers(){
		this.notifyAll();
	}
	
	public synchronized int compareTo(Animal another) {
 
		return another.getPriority() - this.getPriority();
	}
}
