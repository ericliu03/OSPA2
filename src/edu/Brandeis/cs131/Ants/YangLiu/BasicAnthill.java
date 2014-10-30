package edu.Brandeis.cs131.Ants.YangLiu;

import java.util.ArrayList;
import java.util.Collection;

import edu.Brandeis.cs131.Ants.AbstractAnts.Animal;
import edu.Brandeis.cs131.Ants.AbstractAnts.Anthill;

public class BasicAnthill extends Anthill{
	protected Collection<Animal> animals = new ArrayList<Animal>();

	public BasicAnthill(String name, int ants) {
		super(name, ants);
	}

	public synchronized Collection<Animal> getAnimals() {
		return this.animals;
	}
	@Override
	public synchronized boolean tryToEatAt(Animal animal)  {
		//For each animal
		int numberOfAardvardks = 0;
		boolean noOthers = true;
		boolean hasAnteater = false;		
		boolean hasArmadillo = false;
		
		if (this.antsLeft() <= 0) return false;
		
		if (!this.animals.isEmpty()){
			for( Animal eachAnimal : this.animals){
				if (eachAnimal.getColour().equals(animal.getColour())) return false;
				noOthers = false;
				if (eachAnimal instanceof Aardvark) numberOfAardvardks++;
				if (eachAnimal instanceof Armadillo) hasArmadillo = true;
				if (eachAnimal instanceof Anteater) hasAnteater = true;
			}
		}

		//decide whether the animal is allowed to eat
		if ((animal instanceof Aardvark && (numberOfAardvardks > 1 || hasAnteater)) ||
			(animal instanceof Anteater && (hasAnteater || numberOfAardvardks > 0)) ||
			(animal instanceof Armadillo && (noOthers || hasArmadillo))) 
			return false;
		
		//if animal will eat at this hill
		this.animals.add(animal);
		this.eatAnt();
		return true;
	}

	@Override
	public synchronized void exitAnthill(Animal animal) {
		this.animals.remove(animal);
	}
}
