package objectiveFunctions;

import java.util.ArrayList;

import chromosomes.Chromosome;


public class AdjustedFitnessFunction extends GeneticFitnessFunction {
	
	public AdjustedFitnessFunction(String criteria)
	{
		this.criteriaBestFitness=criteria;
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
	public double applyForRNA(ArrayList<ArrayList<Double>> target,
			ArrayList<double[]> evaluationResults) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double apply() {
		// TODO Auto-generated method stub
		return 0;
	}

	

	
}
