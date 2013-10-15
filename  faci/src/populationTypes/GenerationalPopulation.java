package populationTypes;

import java.util.ArrayList;
import java.util.Random;

import replacementMethods.Replacement;
import selectionMethods.Selector;

import mutationMethods.Mutator;

import crossingMethods.Reproductor;

import chromosomes.Chromosome;

import exceptions.ExceptionWrongParameterClass;

public class GenerationalPopulation extends Population {


	private ArrayList<Chromosome> newPop;
	
	public GenerationalPopulation(int populationSize, String chromosomeType,int chromosomeSize,int geneSize, Selector selectionStrategy, 
			Reproductor reproductionStrategy,
			Mutator mutationStrategy, double arithmeticLowerRange, double arithmeticTopRange, double crossingRate, double mutationRate) {
		super(populationSize, chromosomeType, chromosomeSize,geneSize, selectionStrategy,reproductionStrategy,mutationStrategy,
				arithmeticLowerRange,arithmeticTopRange, crossingRate,mutationRate);
		this.newPop=new ArrayList<Chromosome>();
	}


	@Override
	public ArrayList<Chromosome> makeCrossing(ArrayList<Chromosome> parents ) throws ExceptionWrongParameterClass {
		System.out.println("cruzando");
		ArrayList<Chromosome> children = new ArrayList<Chromosome>();
		/*esto noooo esto se hace por cada par de padresss
		 * while (newPop.size()<this.populationSize)
		{*/
			//cruzo los padres seleccionados entre si
			if(passCrossingProbability(crossingRate))
			{
				children=parents.get(0).crossover(parents.get(1));
			}
			//testear la probabilidad de mutacion
			if(passMutationProbability(this.mutationRate) & children != null & children.size()>0)
			{
				//seleccionar un padre y un hijo al azar
				Random r = new Random();
				int posParent = r.nextInt(2);
				int posChild = r.nextInt(2);
				makeMutation(parents.get(posParent),children.get(posChild));
			}
			//esta condicion es por si no pasaron las probabilidades
			if(children != null)
			{
				addChildrenToPopulation(children);
				System.out.println(this.newPop.size());
				if(this.newPop.size()==this.populationSize)
				{
					//si llegue al tamaño de la poblacion hago un reemplazo
					this.makePopulationReplacement();
				}
			}
			return children;
			// rever esto
			/*this.actualPopulation.add(children.get(0));
			this.actualPopulation.add(children.get(1));*/

		/*}*/
		//una vez que cree una nueva generacion, reemplazo la anterior
		//makePopulationReplacement();
		
		
	}

	/**
	 * este metodo inserta los hijos en la poblacion temporal "newPop"
	 * @param children los nuevos hijos
	 */
	private void addChildrenToPopulation(ArrayList<Chromosome> children) {
		

		if(children.size()>0){
			for(int i=0;i<children.size();i++)
			{
				this.newPop.add(children.get(i));
			}
		}
	}

	public void makePopulationReplacement()
	{
		this.actualPopulation = new ArrayList<Chromosome>();
		for(int i=0;i<newPop.size();i++)
		{
			actualPopulation.add(newPop.get(i));
		}
		this.sortPopulation();
		//incremento la cantidad de generaciones transcurridas
		this.generationsCounter++;
	
		System.out.println("hice reemplazo generacional directo por poblacion vacia \\n Cantidad de nueva poblacion: "+
		newPop.size());
	}
	@Override
	public void makePopulationReplacement(ArrayList<Chromosome> parents, ArrayList<Chromosome> children) {
		if(this.newPop.size()==this.populationSize)
		{
			this.actualPopulation = new ArrayList<Chromosome>();
			for(int i=0;i<newPop.size();i++)
			{
				actualPopulation.add(newPop.get(i));
			}
			this.sortPopulation();
			//incremento la cantidad de generaciones transcurridas
			this.generationsCounter++;

			System.out.println("hice reemplazo generacional");
		}
	}
	/* por ahora, es obsoleto, porque la seleccion se hace de a pares...no toma toda la poblacion
	 * asi que el reemplazo al ser llamado en esta clase, debe resolver si se puede o no aplicar
	 * 
	 
	@Override
	public void makePopulationReplacement(ArrayList<Chromosome> parents, ArrayList<Chromosome> children) {
		System.out.println("reemplazo generacional");
		//newPop va creciendo con cada nueva cruza
		for(int i=0;i<newPop.size();i++)
		{
			actualPopulation.add(newPop.get(i));
		}
		this.sortPopulation();
		
		//incremento la cantidad de generaciones transcurridas
		this.generationsCounter++;
	}
	*/

	@Override
	public void setSubstitutor(Replacement rep) {
		return;
		
	}

}
          