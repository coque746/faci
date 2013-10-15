package stoppingCriteria;

import genetic.Genetic;

public class NumberCyclesCriteria extends GeneticStoppingCriterion {

	
	@Override
	public boolean reached(Genetic ag) {
		// TODO Auto-generated method stub
		return (this.stopValue==ag.getCycles());
	}

	@Override
	public boolean validateStopCriteria(String populationStrategy) {
		// aplicable a todas
		return true;
	}
	
	


	
}
