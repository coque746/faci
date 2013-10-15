package replacementMethods;

import java.util.ArrayList;

import chromosomes.Chromosome;

public class BothParentsReplacement extends Replacement {

	@Override
	public void makeReplacement(ArrayList<Chromosome> actualPopulation,
			ArrayList<Chromosome> parents, ArrayList<Chromosome> children,
			int elapsedGenerations) {
		//elimino a los padres
		actualPopulation.remove(parents.get(0));
		actualPopulation.remove(parents.get(1));
		//agrego los hijos
		actualPopulation.add(children.get(0));
		actualPopulation.add(children.get(1));
		
		
	}

	@Override
	public String getReplacementStrategy() {
		return "BothParentsReplacement";
	}

}
