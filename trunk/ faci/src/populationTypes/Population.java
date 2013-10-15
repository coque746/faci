package populationTypes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import replacementMethods.Replacement;
import selectionMethods.Selector;

import mutationMethods.Mutator;

import crossingMethods.Reproductor;

import chromosomes.ArithmeticChromosome;

import chromosomes.BinaryChromosome;
import chromosomes.Chromosome;

import utils.ComparatorChromosome;

import exceptions.ExceptionWrongParameterClass;

public abstract class Population {

 //atributos de configuracion
// private Selector selectionStrategy;
 //private Reproductor reproductionStrategy;
 
 private String chromosomeType;
 private int chromosomeSize;
 private int geneSize;
 
 // atributos de trabajo
 public int populationSize;
 public ArrayList<Chromosome> actualPopulation;
 protected ArrayList<Chromosome> parentsPool;
 protected Selector selector;
 private Reproductor reproductionStrategy;
 private Mutator mutator;
 private Population population;
 private double arithmeticLowerRange;
 private double arithmeticTopRange;
 protected double crossingRate;
 protected double mutationRate;
 public int generationsCounter;

	   
	public Population(int populationSize,String chromosomeType,int chromosomeSize,int geneSize, Selector selectionStrategy,
			Reproductor reproductionStrategy, Mutator mutationStrategy, double arithmeticLowerRange,double arithmeticTopRange,
			double crossingRate, double mutationRate) {
		this.populationSize=populationSize;
		this.chromosomeType = chromosomeType;
		this.arithmeticLowerRange=arithmeticLowerRange;
		this.arithmeticTopRange=arithmeticTopRange;
		this.crossingRate=crossingRate;
		this.mutationRate=mutationRate;
		this.chromosomeSize=chromosomeSize;
		this.generationsCounter=0;
		this.selector = selectionStrategy;
		this.reproductionStrategy=reproductionStrategy;
		this.parentsPool = new ArrayList<Chromosome>();
		this.mutator = mutationStrategy;
		this.geneSize = geneSize;
		
		
		
	}

	public ArrayList<Chromosome> makeSelection() {
	//	System.out.println("make sel");
		ArrayList<Chromosome> selectedParents = new ArrayList<Chromosome>();
		Chromosome selCrom = null;
		
		int topSelection = Math.round(this.actualPopulation.size()/2);
		while(selectedParents.size()<topSelection)
		{
			 
			selCrom =this.selector.getSelection(this.actualPopulation,selCrom);
			if(selectedParents.indexOf(selCrom)==-1)
			{
				//no estaba aca....
				selectedParents.add(selCrom);
			}

		}
		//agrego los padres seleccionados al pool de padres
		parentsPool.addAll(selectedParents);
		this.actualPopulation.addAll(selectedParents);
		
		return selectedParents;
	}

	
	/**
	 * este método además de hacer la cruza, realiza la mutacion de acuerdo a si se pudo o no realizar el cruce
	 * tambien evalua y dependiendo de la clase de poblacion hará o no el reemplazo 
	 * @throws ExceptionWrongParameterClass 
	 */
	public abstract ArrayList<Chromosome> makeCrossing(ArrayList<Chromosome> parents) throws ExceptionWrongParameterClass; 
	public abstract void setSubstitutor(Replacement rep);
	/**
	 * este metodo debe ser redefinido en todas las clases descendientes de esta clase, ya que es básicamente en lo que difieren 
	 * las diferentes poblaciones
	 */
	public abstract void makePopulationReplacement(ArrayList<Chromosome> parents, ArrayList<Chromosome> children);

	/**
	 * ordena la poblacion de acuerdo a su valor de fitness en forma ASCENDENTE
	 * 
	 */
	public void sortPopulation()
	{
		Collections.sort(this.actualPopulation,new ComparatorChromosome());
	}

	public ArrayList<Chromosome> getActualPopulation() {
		return actualPopulation;
	}

	public void setActualPopulation(ArrayList<Chromosome> actualPopulation) {
		this.actualPopulation = actualPopulation;
	}

	/**
	 * metodo que hace reemplazos en la poblacion. En el caso de una TemporalPopulation los parametros no son 
	 * de utilidad puesto que siempre se reemplaza la población completa por la población temporal que se va generando
	 * @param parents: los padres seleccionados para cruza
	 * @param children los hijos creados a partir de la cruza
	 * @throws ExceptionWrongParameterClass
	 */
	public void renewPopulation(ArrayList<Chromosome> parents, ArrayList<Chromosome> children) 
	{
		this.makePopulationReplacement(parents, children);
	}
	
	public void createAleatoryPopulation(int chromosomeSize, int precisionWeight) throws ExceptionWrongParameterClass {
		this.actualPopulation = new ArrayList<Chromosome>(); 
		Chromosome c =null;
		for(int i=0;i<this.populationSize;i++)
		{
			switch(this.chromosomeType)
			{
			case "BinaryRNAChromosome": 
	
			case "BinaryChromosome": 
				c = new BinaryChromosome(chromosomeSize,geneSize,reproductionStrategy);
				break;
			case "ArithmeticChromosome":
				c = new ArithmeticChromosome(chromosomeSize, geneSize,this.arithmeticLowerRange,this.arithmeticTopRange,crossingRate,reproductionStrategy,mutationRate);
				break;
	
			}
		 this.actualPopulation.add(c);
		}
		//una vez que creo la poblacion, para la misma le calculo el fitness

	}

	
	public void makeMutation(Chromosome parent, Chromosome child)
	{
		//System.out.println("haciendo mutacion");
		this.mutator.makeMutation(parent,child);
	}
	
	

	/**
	 * este metodo selecciona del pool de progenitores 2 cromosomas al azar
	 * @param parents es el par de cromosomas seleccionados para reproduccion, que se modifica en este metodo y 
	 * se utiliza en el metodo makeCrossing()
	 */
	public void selectParents(ArrayList<Chromosome> parents) {
		//selecciono 2 padres
		for(int i =0;i<2;i++)
		{
			Random r = new Random();
			int position = r.nextInt(populationSize);
			parents.add(this.parentsPool.get(position));
			//System.out.println("seleccionando");
		}
	}
	
	/**
 	 * metodo para determinar si dada la probabilidad de cruce p es factible realizar la cruza. Supóngase que p = 0.8. Se generan 2 números aleatorios de distribución uniforme se determinará si los 
	 *  emparejamientos se llevan a cabo. Admítase, por ejemplo, que los dos números extraídos sean menores que "p",
	 *  decidiéndose por tanto efectuar el cruce
 
	 * @param p probabilidad de cruce definida por el usuario
	 * @return valor booleano utilizado para determinar si se hace o no el cruce
	 */
	public boolean passCrossingProbability(double p)
	{
		//creo 2 numeros aleatorios con distribucion uniforme
		Random r = new Random();
		double value = r.nextDouble();
		
		return (value<p);
	}
	public boolean passMutationProbability(double p)
	{
		//creo 2 numeros aleatorios con distribucion uniforme
		Random r = new Random();
		double value = r.nextDouble();
		
		return (value>p);
	}
	
	/**
	 * 
	 * @return cantidad de generaciones que lleva la población desde su inicio
	 */
	public int getGenerations()
	{
		return generationsCounter;
	}

	/**
	 * 
	 * @return cantidad de invididuos actualmente en la poblacion
	 */
	public int getActualSize() {
		return this.actualPopulation.size();
	}
}
