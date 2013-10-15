package mutationMethods;

import chromosomes.Chromosome;

public class FlippingMutator extends BinaryMutator {

	@Override
	public void makeMutation(Chromosome parent, Chromosome child) {
		for(int i=0;i<parent.getSize();i++)
		{
			//en este caso el casting lo hago a double xq esta clase podria usarse
			//para mutacion de cromosomas aritmeticos
			Object gen =parent.getGene(i);
			//int genInt = Integer.parseInt((String) gen);
			int genInt = (Integer) gen;
			if(genInt==1)
			{
				child.flipGene(i);
			}
		}
	}
		
	
}
