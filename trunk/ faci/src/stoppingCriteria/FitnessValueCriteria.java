package stoppingCriteria;

import genetic.Genetic;

import java.util.ArrayList;


public class FitnessValueCriteria extends GeneticStoppingCriterion {

	@Override
	public boolean reached(Genetic ag) {
		/* extraido de:
		 * introduccion a los algoritmos geneticos
		 * marcos gestal pose
		 * 
		 * detenerse cuando:
		 * "los mejores individuos de la poblacion representan soluciones suficientemente buenas
		 * para el problema que se desea resolver"
		 */
		/*decido tomar las mejores 10 soluciones*/
		ArrayList<Double> topTen = ag.getTopRankFitness(10);
		for(int i=0;i<topTen.size();i++)
		{
			//System.out.println("del top 10: "+i+" "+topTen.get(i));
			if(!ag.getFitnessFunction().compareBestFitness(topTen.get(i),this.stopValue))
			{
				return false;
			}
			
		}
		// siempre false, por si no trajo nada return true;
		
		return true;
		
	}

	@Override
	public boolean validateStopCriteria(String populationStrategy) {
		/* aplicable a todas*/
		return true;
	}
	
	

}
