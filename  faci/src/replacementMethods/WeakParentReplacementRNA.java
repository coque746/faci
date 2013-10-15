package replacementMethods;

import java.util.ArrayList;
import java.util.Collections;

import chromosomes.Chromosome;

import utils.ComparatorChromosome;

public class WeakParentReplacementRNA extends WeakParentReplacement {

	public WeakParentReplacementRNA()
	{
		super();
	}
	
	
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
		//solo agrego los que tienen mejor fitness (los de minimo fitness en este caso)
		actualPopulation.add(fitnessValue.get(0));
		actualPopulation.add(fitnessValue.get(1));

	}
	@Override
	public String getReplacementStrategy() {
		return "WeakParentReplacementRNA";
	}

}

