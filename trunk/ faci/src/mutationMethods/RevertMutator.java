package mutationMethods;

import java.util.Random;

import chromosomes.Chromosome;

public class RevertMutator extends Mutator {

	@Override
	public void makeMutation(Chromosome parent, Chromosome child) {
		//tomo una psosicion al azar
		
		Random r = new Random();
		int pos = r.nextInt(child.getSize());
		child.flipGene(pos);

	}

}
