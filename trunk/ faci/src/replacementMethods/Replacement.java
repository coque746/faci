package replacementMethods;

import java.util.ArrayList;

import chromosomes.Chromosome;

/**
 * clase para realizar diferentes tipos de reemplazo generacional cuando se realizan los reemplazos en poblaciones
 * de trabajo 
 * @author COQUE
 *
 */
/**
 * IMPORTANTE: Mas alla del tipo de reemplazo que se utilice, hay que tener en cuenta lo siguiente:
 * no se reemplazarán individuos de la población que tengan "age"=0, ya que son individuos que han ingresado recientemente a la poblacion
 */
public abstract class Replacement {

	public abstract void makeReplacement(ArrayList<Chromosome> actualPopulation,
			ArrayList<Chromosome> parents, ArrayList<Chromosome> children, int elapsedGenerations);
	public abstract String getReplacementStrategy();
	
}
