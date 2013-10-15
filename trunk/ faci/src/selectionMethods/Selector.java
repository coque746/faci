package selectionMethods;

import java.util.ArrayList;

import chromosomes.Chromosome;

public abstract class Selector {
	protected ArrayList<Chromosome> selectionPool;
	
	public abstract Chromosome getSelection(ArrayList<Chromosome> pop, Chromosome crom) ;
}
