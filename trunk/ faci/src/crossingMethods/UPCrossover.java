package crossingMethods;

import java.util.ArrayList;
import java.util.Random;


import chromosomes.BinaryChromosome;
import chromosomes.Chromosome;

import exceptions.ExceptionWrongParameterClass;

public class UPCrossover extends Reproductor {

	ArrayList<Integer> mask ;
	
	public UPCrossover()
	{
		mask = new ArrayList<Integer>();
	}
	
	@Override
	public ArrayList<Chromosome> getCrossing(ArrayList<Chromosome> parents)
			throws ExceptionWrongParameterClass {
		
		int chromSize = parents.get(0).getSize();
		//creo una mascara aleatoria
		ArrayList<Chromosome> children = new ArrayList<Chromosome>();
		int topPosition = chromSize*parents.get(0).getGeneSize();
		if(this.mask.size()==0){
			//si la mascara no fue creada, la creo y debe ser la misma para todos los cruces
			this.createMask(topPosition);
		}
		int genSize = parents.get(0).getGeneSize();
		Chromosome child1=new BinaryChromosome(chromSize,genSize,this);
		child1.setGeneSize(parents.get(0).getGeneSize());
		Chromosome child2=new BinaryChromosome(chromSize,genSize,this);
		child2.setGeneSize(parents.get(0).getGeneSize());
		Chromosome parent1=parents.get(0);
		Chromosome parent2=parents.get(1);
		
		for(int j=0; j<topPosition;j++)
		{
			
			if(mask.get(j)==1)
			{
				
				child1.addGene(parent1.getGene(j));
				child2.addGene(parent2.getGene(j));
			}
			else
			{
				child1.addGene(parent2.getGene(j));
				child2.addGene(parent1.getGene(j));
}
		}
		children.add(child1);
		children.add(child2);
		
		return children;
	}
	
	
	private void createMask(int chromSize)
	{
		Random r  = new Random();
		for(int i=0;i<chromSize;i++)
		{
			mask.add(r.nextInt(2));		
		}
	}

}
