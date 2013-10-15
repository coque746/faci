package objectiveFunctions;

import java.util.ArrayList;

import chromosomes.Chromosome;



public abstract class GeneticFitnessFunction implements Function {

	/**
	 * este valor determina si la funcion evalua como mejor fitnees un maximo o minimo para la comparacion
	 */
	protected String criteriaBestFitness="";
	public abstract void apply(Chromosome chrom);
	//public abstract double applyForRNA(ArrayList<ArrayList<Double>> target, double[][] output);
	
	/**
	 * devuelve el criterio de evaluacion establecido para definir cuando un valor de fitness es mejor que otro
	 * (esto es evaluado por el metodo "compareBestFitness"
	 * @return string cuyos posibles valores deberian ser max/maxeq/min/mineq
	 */
	public String getCriteriaBestFitness() {
		return criteriaBestFitness;
	}

	/**
	 * devuelve true o false si el primer parametro es un valor mejor de fitness que el segundo
	 * basado en el valor de "criteriaBestFitness" (max, maxeq,min o mineq) que se use para la funcion de fitness seleccionada
	 * @param evaluationValue valor que se intenta determinar como el mejor
	 * @param parameterValue valor de comparacion
	 * @return
	 */
	public boolean compareBestFitness(Double evaluationValue,Double parameterValue)
	{
		switch(this.criteriaBestFitness)
		{
			case "max":
				return(evaluationValue > parameterValue);
			case "maxeq":	
				return(evaluationValue >= parameterValue);
			case "min":	
				return(evaluationValue < parameterValue);
			case "mineq":	
				return(evaluationValue <= parameterValue);
		}
		return false;
	}
	public abstract double applyForRNA(ArrayList<ArrayList<Double>> target,
			ArrayList<double[]> evaluationResults);
	
}
