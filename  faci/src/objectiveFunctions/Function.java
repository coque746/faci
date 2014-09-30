package objectiveFunctions;

import java.util.ArrayList;

import chromosomes.Chromosome;

public interface Function {

    double apply(double s);

    double applyDf(double s);
    
    double apply(ArrayList<Double> s);
    
	String print();

	double applyDf(ArrayList<Double> s);
	
	double applyToPopulation(ArrayList<Chromosome> s);
}
