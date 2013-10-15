package replacementMethods;

import java.util.ArrayList;
import java.util.Collections;

import chromosomes.Chromosome;

import utils.ComparatorChromosome;

public class WeakParentReplacement extends Replacement {

	@Override
	public void makeReplacement(ArrayList<Chromosome> actualPopulation,
			ArrayList<Chromosome> parents, ArrayList<Chromosome> children,
			int elapsedGenerations) {
		
		ArrayList<Chromosome> fitnessValue = new ArrayList<Chromosome>();
		/*saco los padres, porque al ordenar puede ser que los hijos tengan peor fit y
		 * no los estaria eliminando, ya que aun no estan en la poblacion 
		 */
		actualPopulation.removeAll(parents);
		
		for(int i=0;i<2;i++)
		{
			fitnessValue.add(parents.get(i));
			fitnessValue.add(children.get(i));
		}
		
		Collections.sort(fitnessValue,new ComparatorChromosome());
		//solo agrego los que tienen mejor fitness
		actualPopulation.add(fitnessValue.get(2));
		actualPopulation.add(fitnessValue.get(3));
		Collections.sort(actualPopulation,new ComparatorChromosome());
		
	}

	@Override
	public String getReplacementStrategy() {
		return "WeakParentReplacement";
	}

}
