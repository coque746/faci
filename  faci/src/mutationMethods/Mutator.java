package mutationMethods;

import chromosomes.Chromosome;

public abstract class Mutator {

	public abstract void makeMutation(Chromosome parent, Chromosome child);

}
