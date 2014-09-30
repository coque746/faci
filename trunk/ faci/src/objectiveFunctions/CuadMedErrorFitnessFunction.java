package objectiveFunctions;

import java.util.ArrayList;

import chromosomes.Chromosome;


public class CuadMedErrorFitnessFunction extends GeneticFitnessFunction {
	
	public CuadMedErrorFitnessFunction(String criteria) {
		this.criteriaBestFitness = criteria;
	}
	@Override
	public double apply(double s) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double applyDf(double s) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double apply(ArrayList<Double> s) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String print() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double applyDf(ArrayList<Double> s) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void apply(Chromosome chrom) {
		// TODO Auto-generated method stub

	}

	@Override
	public double applyForRNA(ArrayList<ArrayList<Double>> target, ArrayList<double[]> output) {
		double value=0;
		double finalValue=0;
		//cantidad de elementos evaluados
		int numberOfCases = target.size(); 
		
		for(int j=0;j<numberOfCases;j++)
		{
			//por cada caso de "entrenamiento"
			ArrayList<Double> t = target.get(j);
			double[] out = output.get(j);
			
			//reinicializo "value"
			value =0;
			for(int k=0;k<t.size();k++)
			{
				double error_k=(t.get(k)-out[k]);
				value += Math.pow(error_k, 2); 
			}
			finalValue+=(value/2);
		}
		//sumatoria de todos los errores divido la cantidad de errores
		finalValue/=numberOfCases;
		if(finalValue<0)
		{
			finalValue*=(-1);
		}
		return finalValue;
		
	}
	@Override
	public double applyToPopulation(ArrayList<Chromosome> s) {
		// TODO Auto-generated method stub
		return 0;
	}

}
