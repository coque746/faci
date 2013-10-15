package selectionMethods;

import java.util.ArrayList;
import java.util.Collections;

import chromosomes.Chromosome;

import utils.ComparatorChromosome;

public class RankingSelector extends Selector {

	
/*
 * 		En el operador de selección basado en el ranking, los cromosomas se ordenan deacuerdo a sus valores para la
 *      función de adaptación. Luego, se seleccionan para lareproducción a los primeros "m"	(la cantidad que sea necesaria) 
 *      cromosomas. Como lasprobabilidades de ser elegido de cada individuo dependen solamente de su posición relativa 
 *      respecto a los demás y no del valor absoluto de aptitud, este operador norequiere que se apliquen las técnicas de escala 
 *      de la función de adaptación 
 */
	


	
	/**
	 * este metodo retorna el cromosoma con mejor fitness (de maximo,AG NORMAL)
	 */
	@Override
	public Chromosome getSelection(ArrayList<Chromosome> pop,Chromosome crom) {

		Collections.sort(pop,new ComparatorChromosome());
		int topPosition = pop.size()-1;
		Chromosome selCrom=pop.get(topPosition);
	
		pop.remove(selCrom);
		return selCrom;
	}


	
}
