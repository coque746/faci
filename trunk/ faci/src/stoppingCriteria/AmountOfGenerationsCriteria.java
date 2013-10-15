package stoppingCriteria;

import genetic.Genetic;

public class AmountOfGenerationsCriteria extends GeneticStoppingCriterion {

	@Override
	public boolean reached(Genetic ag) {
		int transcurredGenerations = ag.population.generationsCounter;
		System.out.println("GENERACIONES "+ transcurredGenerations);
		// el valor de generaciones para detenerse es el mismo que el que lleva la poblacion?
		return (this.stopValue==transcurredGenerations);
		
	}

	@Override
	public boolean validateStopCriteria(String populationStrategy) {
		// no aplicable a poblaciones estacionales
		return (populationStrategy=="StationalPopulation");
	}


}
