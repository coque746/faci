package stoppingCriteria;

import genetic.Genetic;

public abstract class GeneticStoppingCriterion {

	double stopValue=0;
	public abstract boolean reached(Genetic ag);

	public void setValueStop(double value)
	{
		this.stopValue=value;
	}

	public abstract boolean validateStopCriteria(String populationStrategy);
}
