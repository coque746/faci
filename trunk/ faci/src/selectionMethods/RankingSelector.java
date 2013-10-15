package selectionMethods;

import java.util.ArrayList;
import java.util.Collections;

import chromosomes.Chromosome;

import utils.ComparatorChromosome;

public class RankingSelector extends Selector {

	
/*
 * 		En el operador de selecci�n basado en el ranking, los cromosomas se ordenan deacuerdo a sus valores para la
 *      funci�n de adaptaci�n. Luego, se seleccionan para lareproducci�n a los primeros "m"	(la cantidad que sea necesaria) 
 *      cromosomas. Como lasprobabilidades de ser elegido de cada individuo dependen solamente de su posici�n relativa 
 *      respecto a los dem�s y no del valor absoluto de aptitud, este operador norequiere que se apliquen las t�cnicas de escala 
 *      de la funci�n de adaptaci�n 
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
