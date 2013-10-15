package mutationMethods;

import java.util.Random;

import chromosomes.Chromosome;

public class ExchangeMutator extends Mutator {

	@Override
	public void makeMutation(Chromosome parent, Chromosome child) {
		
		Random r = new Random();
		int pos1= r.nextInt(child.getSize());
		int pos2 =r.nextInt(child.getSize());
		//este es un artilugio para que no seleccione 2 veces la misma posicion
		while (pos1==pos2)
		{
			pos2=r.nextInt(child.getSize());
		}

		//obtengo los valores a intercambiar
		//son tipo object para usar la clase en cualquier cromosoma
		Object valuePos1=child.getGene(pos1);
		Object valuePos2=child.getGene(pos2);
		child.setGeneValue(valuePos1,pos2);
		child.setGeneValue(valuePos2,pos1);
	}

}
