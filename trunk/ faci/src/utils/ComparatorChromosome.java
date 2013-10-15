package utils;

import java.util.Comparator;

import chromosomes.Chromosome;


public class ComparatorChromosome implements Comparator<Chromosome> {

	
	@Override
	public int compare(Chromosome crom1, Chromosome crom2) {

		if(crom1.getFitnessValue()<crom2.getFitnessValue())
		{
			return -1;
		}
	
		if(crom1.getFitnessValue()>crom2.getFitnessValue())
		{
			return 1;
		}
		return 0;
	}

}
