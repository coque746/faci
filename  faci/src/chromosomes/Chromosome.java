package chromosomes;

import java.util.ArrayList;

import org.omg.CORBA.RepositoryIdHelper;

import crossingMethods.Reproductor;


import exceptions.ExceptionWrongParameterClass;

public abstract class Chromosome {
	
	protected ArrayList geneStrip;
	private double fitnessValue;
	protected Reproductor reproductor;
	protected int age;
	protected int chromosomeSize;
	protected int geneSize;
	public Chromosome(int size, int geneSize,Reproductor rep)
	{
		//nacen con edad 0
		age=0;
		this.chromosomeSize=size;
		this.reproductor=rep;
		this.geneSize=geneSize;
	}
	 
	public void setGeneSize(int geneSize) {
		this.geneSize = geneSize;
	}

	public ArrayList<?> getGeneStrip() {
		return geneStrip;
	}

	

	public void setGeneStrip(ArrayList<?> geneStrip) {
		this.geneStrip = geneStrip;
	}

	public Reproductor getReproductor() {
		return reproductor;
	}

	public void setReproductor(Reproductor reproductor) {
		this.reproductor = reproductor;
	}

	public abstract int getSize();

	public double getFitnessValue() {
		
		return fitnessValue;
	}
	public void setFitnessValue(double fitnessValue) {
		this.fitnessValue = fitnessValue;
	}
	
	public ArrayList<Chromosome> crossover(Chromosome otherChromosome) throws ExceptionWrongParameterClass
	{
			//creo la pareja de reproduccion
			ArrayList<Chromosome> parents = new ArrayList<Chromosome>();
			parents.add(otherChromosome);
			parents.add(this);

			ArrayList<Chromosome> children = this.reproductor.getCrossing(parents);
			return children;
	}
	

	/**
	 * 
	 * @return "edad" o generaciones que lleva en la poblacion del cromosoma
	 */
	public int getAge() {
		
		return this.age;
	}
	public abstract void addGene(Object gene);
	
	public Object getGene(int pos)
	{  
		return this.geneStrip.get(pos);
	}

	public abstract void flipGene(int i) ;

	public abstract void setGeneValue(Object valuePos1, int pos2);

	public int getGeneSize() {
		return this.geneSize;
		
	}
	
	public int getChromosomeSize() {
		return chromosomeSize;
	}

	public void setChromosomeSize(int chromosomeSize) {
		this.chromosomeSize = chromosomeSize;
	}



	public void print() {
		for(int i=0;i<this.chromosomeSize;i++)
		{
			System.out.print(this.geneStrip.get(i)+"-");
		}
	}
		
}
