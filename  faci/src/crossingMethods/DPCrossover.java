package crossingMethods;

import java.util.ArrayList;
import java.util.Random;

import chromosomes.BinaryChromosome;
import chromosomes.Chromosome;

import exceptions.ExceptionWrongParameterClass;

public class DPCrossover extends Reproductor {

	@Override
	public ArrayList<Chromosome> getCrossing(ArrayList<Chromosome> parents)
			throws ExceptionWrongParameterClass {

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
		int randPosition = r.nextInt(topPosition);
		int randPosition2 = 0;
		
		//esto es una artilugio para no hacer tanta logica de ordenamiento de posiciones de corte
		ArrayList<Integer> positions = new ArrayList<Integer>();
		//busco aleatoriamente hasta q la segunda posicion sea mayor que la 1º
		if(randPosition<topPosition-1)
		{
			positions.add(randPosition);
			while(randPosition2<randPosition )
			{
				randPosition2 = r.nextInt(topPosition);
			}
			positions.add(randPosition2);
		}
		else
		{//es igual a la ultima posicion
			randPosition2 = chromSize-1;
			while(randPosition2>=randPosition )
			{
				//la segunda posicion debe ser menor que la primera
				randPosition2 = r.nextInt(topPosition);
			}
			positions.add(randPosition2);
			positions.add(randPosition);
			
		}
		Chromosome parent1 = parents.get(0);
		Chromosome parent2 = parents.get(1);
		//for(int i=0;i<chromSize;i++)
		for(int i=0;i<topPosition;i++)
		{
			if(i<positions.get(0))
			{
				child1.addGene(parent1.getGene(i));
				child2.addGene(parent2.getGene(i));
			}
			else
			{
				//esta en el medio 
				if(i>=positions.get(0) && i<positions.get(1))
				{
					child1.addGene(parent2.getGene(i));
					child2.addGene(parent1.getGene(i));
				}
				else
				{
					child1.addGene(parent1.getGene(i));
					child2.addGene(parent2.getGene(i));
				}
			}
		}
		
		children.add(child1);
		children.add(child2);

		return children;
	} 

}
