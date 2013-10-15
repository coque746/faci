package chromosomes;

import java.util.ArrayList;

import crossingMethods.Reproductor;


public class ArithmeticChromosome extends Chromosome {

	

	protected double arithmeticLowerRange=0;
	protected double arithmeticTopRange=0;
	public ArithmeticChromosome(int chromosomeSize, int genSize,double arithmeticLowerRange, double arithmeticTopRange, 
			double crossingRate, Reproductor reproductor, double mutationRate) {
		super(chromosomeSize, genSize,reproductor);
		this.crossingProbability = crossingRate;
		this.mutationProbability=mutationRate;
		this.arithmeticLowerRange=arithmeticLowerRange;
		this.arithmeticTopRange=arithmeticTopRange;
		this.geneStrip= new ArrayList<Double>();
		
		
		
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return this.chromosomeSize;
	}

	@Override
	public void addGene(Object gene) {
		this.geneStrip.add(gene);
		
	}

	@Override
	public void flipGene(int i) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setGeneValue(Object valuePos1, int pos2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double getGeneToWeight(int j) {
		// TODO Auto-generated method stub
		return 0;
	}

	
   protected void initGeneStrip()
   {
	   //para especializar
	   for(int i=0;(i<chromosomeSize && geneStrip.size()<chromosomeSize);i++)
		{
			//todo esto para poder generar numeros aleatorios entre 2 valores 
			double Max =  this.arithmeticTopRange;
			double Min = this.arithmeticLowerRange;
			double value = ((Math.random()*(Max-Min))+Min);
			this.geneStrip.add(value);
		}
   }

   public double getArithmeticLowerRange() {
		return arithmeticLowerRange;
	}

	public double getArithmeticTopRange() {
		return arithmeticTopRange;
	}



}
