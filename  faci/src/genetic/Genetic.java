package genetic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import objectiveFunctions.GeneticFitnessFunction;
import populationTypes.GenerationalPopulation;
import populationTypes.Population;
import populationTypes.StationalPopulation;

import crossingMethods.Reproductor;

import chromosomes.Chromosome;



import replacementMethods.Replacement;
import selectionMethods.Selector;
import stoppingCriteria.GeneticStoppingCriterion;
import trainingMethod.Algorithm;
import utils.ComparatorChromosome;

import exceptions.ExceptionWrongParameterClass;

import mutationMethods.Mutator;
import network.ANNTopology;
import network.HiddenLayer;
import network.networkTopology;

public class Genetic extends Algorithm{

   //atributos de configuracion
   
   protected int populationSize;
   

   protected int chromosomeSize;
   protected int geneSize;
   protected String trainingFile; 
   protected String populationStrategy;
   protected Selector selectionStrategy;
   protected Reproductor reproductionStrategy;
   protected Mutator mutationStrategy;
   protected double crossingRate;
   protected double mutationRate;
   protected String chromosomeRepresentation;
   protected double arithmeticLowerRange;
   protected double arithmeticTopRange;
   
   protected Replacement replacementStrategy;
   //atributos de trabajo
   public Population population;
   GeneticFitnessFunction fitnessFunction;
   GeneticStoppingCriterion stopCriteria;
   //ciclos de ejecucion del algoritmo
   protected double cycles;
   double valueStoppingCriteria;
   //int generationCounter;

   public Genetic(int populationSize,int chromosomeSize, int geneSize,double mutationRate, double crossingRate, String chromosomeRepresentation, 
		   String populationStrategy,  Selector selectionStrategy,Reproductor reproductionStrategy, 
		  Mutator mutationStrategy, GeneticStoppingCriterion stoppingCriteria, double valueStopCriteria,
		  double lowerRange, double topRange, GeneticFitnessFunction fitnessFunction,Replacement replacementStrategy) throws ExceptionWrongParameterClass {
        super();
        this.populationSize=populationSize;
        this.populationStrategy = populationStrategy;
        this.selectionStrategy = selectionStrategy;
        this.reproductionStrategy=reproductionStrategy;
        this.mutationStrategy = mutationStrategy;
        this.crossingRate = crossingRate;
        this.mutationRate = mutationRate;
        this.chromosomeRepresentation =chromosomeRepresentation;
        this.stopCriteria = stoppingCriteria;
        this.geneSize=geneSize;
        if(!stoppingCriteria.validateStopCriteria(this.populationStrategy)) 
        {
        	throw new ExceptionWrongParameterClass("No es compatible el criterio de parada" +
        			" con el tipo de poblacion: "+this.populationStrategy);
        }
        this.arithmeticLowerRange=lowerRange;
        this.arithmeticTopRange=topRange;
        this.fitnessFunction=fitnessFunction;
        this.valueStoppingCriteria=valueStopCriteria;
        this.chromosomeSize = chromosomeSize;
        /* del apunte de catedra:
         * Pm=1/L donde L es la long del cromosoma
         * (Pm==>probabilidad de mutacion)  
      
        
        this.mutationRate = 1/chromosomeSize;   */
        this.replacementStrategy = replacementStrategy;
        
        initialize();
    }
  
   public GeneticFitnessFunction getFitnessFunction() {
		return fitnessFunction;
	}

	
	@Override
	public void initialize() throws ExceptionWrongParameterClass {
		/** inicializacion para propositos generales
		 * difiere del "initializeForRNA" en que el tama�o del cromosoma se obtiene
		 * desde la configuracion  
		 */
		this.cycles=0;
		//this.generationCounter=1;
		//determinar el tipo de poblacion
		switch(this.populationStrategy)
		{
		case "GenerationalPopulation":
			this.population= new GenerationalPopulation(this.populationSize,this.chromosomeRepresentation,this.chromosomeSize,this.geneSize,this.selectionStrategy,this.reproductionStrategy,this.mutationStrategy,
					this.arithmeticLowerRange,this.arithmeticTopRange, this.crossingRate, this.mutationRate);
			break;
		case "StationalPopulation":
			this.population= new StationalPopulation(this.populationSize,this.chromosomeRepresentation,this.chromosomeSize,this.geneSize,this.selectionStrategy,this.reproductionStrategy,this.mutationStrategy,
					this.arithmeticLowerRange,this.arithmeticTopRange, this.crossingRate, this.mutationRate,this.replacementStrategy);
			break;
		}   
		
		this.population.createAleatoryPopulation(chromosomeSize,0);
		this.initializePopulationFitness();
		//System.out.println("poblacion creada");

	}
	protected void initializePopulationFitness() throws ExceptionWrongParameterClass {
		//System.out.println("Primer fitness de la poblacion ");
		ArrayList<Chromosome> popu = population.getActualPopulation();
		for(int i=0;i<this.population.populationSize;i++)
		{
		  
		  //popu.get(i).setFitnessValue(this.fitnessDefaultValue);
		  this.calculateChildFitness(popu.get(i));
		  
		}
	}

	
/*
	protected void calculatePopulationFitness() {
		// TODO Auto-generated method stub
		ArrayList<Chromosome> actualPopulation = population.getActualPopulation();
		for(int i =0;i<actualPopulation.size();i++)
		{
			this.evaluateFitness(actualPopulation.get(i));
			
		}
		
	}*/



	/*	 	evaluateFitness antes
	 * 
	 
	private void evaluateFitness(Chromosome chromosome) {
		//datos necesarios
		int chromSize = chromosome.getSize();
		int layerAmount = this.network.getLayerAmount();
		int neuronHiddenAmount = this.network.getSizeHidden();
		int neuronOutAmount = this.network.getSizeOutput();
		int geneSize = chromosome.getGeneSize();
		//dividir el cromosoma en lista de genes de la capa
		//TODO: REVER, QUIZAS ES MAS UTIL LLEVAR UN CONTADOR DE POSICIONES BASADO EN LOS TAMA�OS DE GEN , ACTUALIZABLE
		//CON LAS ITERACIONES 
		//por cada capa oculta
		for(int i = 0;i< layerAmount-2;i++)
		{
			//por cada link en la capa
			for(int j=0;j<neuronHiddenAmount;j++)
			{
				double weight = chromosome.getGeneToWeight(j);
				//TODO:CONTINUAR ESTO
				List<HiddenLayer> hiddenLayers = this.network.getHiddenLayers();
			}
		}
		
	}

  */
/*	*//**
	 * este metodo debe estar publico disponible para ser llamado en los casos en los que no se
	 * este utilizando el algoritmo para entrenamiento de RNA. Cuando se utiliza para entrenamiento de RNA's, es llamado desde
	 * el m�todo initializeForRNA
	 * @param fitnessFunction Nombre de la funcion de fitness seleccionada para evaluar el algoritmo
 * @throws ExceptionWrongParameterClass 
	 * 
	 *//*

	public void createFitnessFunction(String fitnessFunction) {
		
		switch(fitnessFunction)
		{
		case "StandarizedFitnessFunction":
			this.fitnessFunction = new StandarizedFitnessFunction();
			break;
		case "NormalizedFitnessFunction":
			this.fitnessFunction=new NormalizedFitnessFunction();
			break;
		case "AdjustedFitnessFunction":
			this.fitnessFunction=new AdjustedFitnessFunction();
			break;
		case "CuadMedErrorFitnessFunction":
			this.fitnessFunction=new CuadMedErrorFitnessFunction();
			break;
		}
		
	}*/
	
	
	@Override
	public void runAlgorithm() throws ExceptionWrongParameterClass
	{
		ArrayList<Chromosome> parents = new ArrayList<Chromosome>();
		ArrayList<Chromosome> parentsPair = new ArrayList<Chromosome>();
		ArrayList<Chromosome> children = new ArrayList<Chromosome>();
		
		parents = this.population.makeSelection();
		while(!this.stopCriteria.reached(this))
		{
			//System.out.println("hola! van "+this.cycles+" ciclos");
			this.population.selectParents(parentsPair);
			//cruzo y ahora tengo los nuevos hijos
			children=this.population.makeCrossing(parentsPair);
			//si no paso la prob de cruza no se puede hacer lo que sigue
			if(children !=null & children.size()>0)
			{//calculo el fitness de los hijos
				
				this.calculateChildFitness(children.get(0));
				this.calculateChildFitness(children.get(1));
				this.population.renewPopulation(parents,children);
			}
			//este metodo solo llama al makeReplacement
			
			this.cycles++;
			//System.out.println("no se alcanzo el criterio de parada");
		}
		
	}

	/**
	 * este metodo corre para cambios poblacionales GENERACIONALES
	 * es necesario diferenciarlo del estacionario, ya que trabaja con un "pool" de padres preseleccionados
	 * a diferencia del estacionario, que para cada cruza, hace una seleccion de padres, cruza y reemplaza
	 * @throws ExceptionWrongParameterClass 
	 */
	

	/**
	 * este metodo calcula el fitness del cromosoma de acuerdo a la funcion que se indico 
	 * como parametro de la solucion
	 * @param chromosome sobre el que se calculara el fitness
	 * @throws ExceptionWrongParameterClass
	 */
	protected void calculateChildFitness(Chromosome chromosome) throws ExceptionWrongParameterClass {
				this.fitnessFunction.apply(chromosome);
	}


	@Override
	public void evaluateValidationCases() throws IOException {
		// TODO Auto-generated method stub
		
	}
	
	public Population getBestPopulation(GeneticStoppingCriterion criterion )
	{
		//TODO: hacer este metodo para que retorne la mejor solucion de acuerdo cierta condicion...
		return null;
	}

	
	@Override
	public double getCuadraticMediumError() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public void initializeForRNA(networkTopology network, double learnVelocity)
			throws ExceptionWrongParameterClass {
		//esta para no romper la estructura, por ahora lo dejo
		
		
	}


	public double getCycles() {
		return this.cycles;
	}
	
	/**
	 * 
	 * @param topRankedQuantity cantidad de cromosomas mejor posicionados que se desea evaluar
	 * @return lista de los "topRankedQuantity" mejores valores de fitness que se hallan en la poblacion
	 */

	public ArrayList<Double> getTopRankFitness(int topRankedQuantity) {

		ArrayList<Double> topRanked=new ArrayList<Double>();
		//puede ser en que cierto momento no este completa la poblacion
		ArrayList<Chromosome> actual = this.population.getActualPopulation();
		Collections.sort(actual,new ComparatorChromosome());
		int position = actual.size()-1;
		if(position > 0){
			for(int i=0;(i<topRankedQuantity & position>=0);i++)
			{
				//System.out.println("i "+i+" position "+position);
				Chromosome c = this.population.actualPopulation.get(position);
				topRanked.add(c.getFitnessValue());
				position--;
			}
		}
		return topRanked;
	}

	/**
	 * 
	 * @param topRankedQuantity cantidad de cromosomas mejor posicionados que se desea evaluar
	 * @return lista de los "topRankedQuantity" cromosomas con mejores valores de fitness que se hallan en la poblacion
	 */

	public ArrayList<Chromosome> getTopRankFitnessChromosome(int topRankedQuantity) {

		ArrayList<Chromosome> topRanked=new ArrayList<Chromosome>();
		//puede ser en que cierto momento no este completa la poblacion
		int position = this.population.getActualSize()-1;
		for(int i=0;i<topRankedQuantity;i++)
		{
			Chromosome c = this.population.actualPopulation.get(position);
			topRanked.add(c);
			position--;
		}
		return topRanked;
	}


}
