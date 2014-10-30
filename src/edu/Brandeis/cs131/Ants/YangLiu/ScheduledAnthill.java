package edu.Brandeis.cs131.Ants.YangLiu;

import java.util.ArrayList;
import java.util.Collection;
import java.util.PriorityQueue;

import edu.Brandeis.cs131.Ants.AbstractAnts.Animal;
import edu.Brandeis.cs131.Ants.AbstractAnts.Anthill;
import edu.Brandeis.cs131.Ants.AbstractAnts.Log.AntLog;

public class ScheduledAnthill extends Anthill{
	
	private Collection<Anthill> basicAnthills = new ArrayList<Anthill>();
	private PriorityQueue<Animal> waitingQueue = new PriorityQueue<Animal>();

	private static int computeAnt(Collection<Anthill> basicAnthills) {
		int antsOfAll = 0;
		for (Anthill eachHill: basicAnthills) 
			antsOfAll += eachHill.antsLeft();
		return antsOfAll;
	}
	
	public ScheduledAnthill(String label, Collection<Anthill> basicAnthills, AntLog log) {
		super(label, computeAnt(basicAnthills), log);
		this.basicAnthills = basicAnthills;
	}
	
	@Override
	public boolean tryToEatAt(Animal animal)  {
		while (true){
			//get the lock, then test the animal should be put into queue or try to eat at hills
			synchronized(this) {
			if (waitingQueue.isEmpty() || (animal.getPriority() >= waitingQueue.element().getPriority())) 
				for (Anthill eachHill: basicAnthills) 
					if (eachHill.attemptToEatAt(animal)) {
						((MyAnimal)animal).giveWhichHill(eachHill);
						this.eatAnt();
						return true;
					}
			}
			//if unfortunately have to into the queue
			synchronized(waitingQueue) {waitingQueue.offer(animal);}
			((MyAnimal) animal).waitForHill();
		}
	}
	
	@Override
	public synchronized void exitAnthill(Animal animal) {
		//let the animal's current hill “leave” the animal and notify other animals in the queue
		((MyAnimal)animal).getWhichHill().leaveAnthill(animal);
		
		synchronized(waitingQueue){
		if (!this.waitingQueue.isEmpty()) {
			Animal tempAnimal = this.waitingQueue.poll();
			((MyAnimal) tempAnimal).notifyOthers();
		} 
		}
	}
	
}
