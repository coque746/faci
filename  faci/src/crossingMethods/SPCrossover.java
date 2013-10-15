package crossingMethods;

import java.util.ArrayList;
import java.util.Random;

import chromosomes.BinaryChromosome;
import chromosomes.Chromosome;

import exceptions.ExceptionWrongParameterClass;

public class SPCrossover extends Reproductor {

	@Override
	public ArrayList<Chromosome> getCrossing(ArrayList<Chromosome> parents) throws ExceptionWrongParameterClass {
		
		ArrayList<Chromosome>children= new ArrayList<Chromosome>();
		Random r= new Random();
		int chromSize = parents.get(0).getSize();
		int genSize = parents.get(0).getGeneSize();
		Chromosome child1=new BinaryChromosome(chromSize,genSize,this);
		child1.setGeneSize(parents.get(0).getGeneSize());
		Chromosome child2=new BinaryChromosome(chromSize,genSize,this);
		child2.setGeneSize(parents.get(0).getGeneSize());
		int topPosition = chromSize*parents.get(0).getGeneSize();
		//por ahora tomo una posicion al azar
		int randPosition = r.nextInt(chromSize);
		Chromosome parent1 = parents.get(0);
		Chromosome parent2 = parents.get(1);
		
		for(int i=0;i<topPosition-1;i++)
		{
			if(i<randPosition)
			{
				child1.addGene(parent1.getGene(i));
			}
			else
			{
				child2.addGene(parent1.getGene(i));
			}
		}
		for(int j=0;j<topPosition-1;j++)
		{
			if(j<randPosition)
			{
				child2.addGene(parent2.getGene(j));
			}
			else
			{
				child1.addGene(parent2.getGene(j));
			}
			
		}
		children.add(child1);
		children.add(child2);

		return children;
	}

}
