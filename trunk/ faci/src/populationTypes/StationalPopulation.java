package populationTypes;

import java.util.ArrayList;
import java.util.Random;

import replacementMethods.Replacement;
import selectionMethods.Selector;

import mutationMethods.Mutator;

import crossingMethods.Reproductor;

import chromosomes.Chromosome;

import exceptions.ExceptionWrongParameterClass;

public class StationalPopulation extends Population {

	private Replacement substitutor;
	public StationalPopulation(int populationSize, String chromosomeType,int chromosomeSize, int geneSize,
			Selector selectionStrategy,
			Reproductor reproductionStrategy, Mutator mutationStrategy, double arithmeticLowerRange, 
			double arithmeticTopRange, double crossingRate, double mutationRate, Replacement replacementStrategy) {
		
		super(populationSize, chromosomeType, chromosomeSize,geneSize,selectionStrategy,reproductionStrategy,
				mutationStrategy,
				arithmeticLowerRange,arithmeticTopRange, crossingRate,mutationRate);
		this.substitutor=replacementStrategy;
	}

	@Override
	public ArrayList<Chromosome> makeCrossing(ArrayList<Chromosome> parents ) throws ExceptionWrongParameterClass {
		ArrayList<Chromosome> children = new ArrayList<Chromosome>();
		//cruzo los padres seleccionados entre si
		if(passCrossingProbability(crossingRate))
			{
				children=parents.get(0).crossover(parents.get(1));
			}
			
			if(passMutationProbability(this.mutationRate) & children != null & children.size()>0)
			{
				//seleccionar un padre y un hijo al azar
				Random r = new Random();
				int posParent = r.nextInt(2);
				int posChild = r.nextInt(2);
				Chromosome parent1 =parents.get(posParent);
				
				Chromosome child = children.get(posChild);
				makeMutation(parent1,child);
			}
			if(children.size()==2)
			{return children;}
 	 return children;
	}


	public void setSubstitutor(Replacement substitutor) {
		this.substitutor = substitutor;
	}

	@Override
	public void makePopulationReplacement(ArrayList<Chromosome> parents, ArrayList<Chromosome> children) {

		
		/*
		 * hay que volver a agregar a los padres dentro de la poblacion (fueron retirados de la misma por el metodo de seleccion)
		 * y es necesario que esten presentes dentro de la poblacion para que puedan ser evaluados por las estrategias
		 * de reemplazo
		 */
		this.actualPopulation.add(parents.get(0));
		this.actualPopulation.add(parents.get(1));
		
		/*aca los "hijos" ya tienen fitness evaluado, de ahi el substitutor puede determinar como hacer los 
		 * reemplazos
		 */
		
		this.substitutor.makeReplacement(this.actualPopulation,parents,children,getGenerations());
		//para mantener siempre ordenada segun fitness a la poblacion luego de cada reemplazo...
		this.sortPopulation();
	}

	
	
	
}//end class
