package selectionMethods;

import java.util.ArrayList;
import java.util.Random;

import chromosomes.Chromosome;

public class TournamentSelector extends Selector {

	public TournamentSelector(){
		this.selectionPool=new ArrayList<Chromosome>();
	}
	
	private Random randGen=new Random();

	
	@Override
	public Chromosome getSelection(ArrayList<Chromosome> pop, Chromosome crom) {

		//umbral K (valor usual:0.75)
		double threshold_K=0.75;
		int top = pop.size();
		
		ArrayList<Chromosome> selectedPair=null;
		double r =0;
		
		 /* cambiado el 14/07
		  * for(int i = 0;i<top;i++)
		  * 
		  */
		 Chromosome  selected =null;
		 
		 while (selected==null)
		 {
			 selectedPair=getCompetitorPair(pop);
			 r = randGen.nextDouble();
			
			 if(r<threshold_K)
			 {
				 selected = getBestParent(selectedPair);
   			 }
			 else
			 {
				 selected = getWorstParent(selectedPair);
				 
			 }
			 
			 if(!selected.equals(crom))
			 {
				 this.selectionPool.add(selected);
				 //elimino el padre seleccionado
				 pop.remove(selected);
			 }
			 else
			 {
				 selected=null;
			 }
		 }
		 
		 return selected;
	}


	/**
	 * este metodo selecciona el cromosoma con peor valor de fitness
	 * @param selectedPair par preseleccionado para competir
	 * @return el cromosoma con peor fitness
	 */
	protected Chromosome getWorstParent(ArrayList<Chromosome> selectedPair) {
		double fitnessOne = selectedPair.get(0).getFitnessValue();
		double fitnessTwo = selectedPair.get(1).getFitnessValue();
		if(fitnessOne<fitnessTwo)
		{
			return selectedPair.get(0);
		}
		return selectedPair.get(1);
	}


	/**
	 * este metodo selecciona el cromosoma con mejor valor de fitness
	 * @param selectedPair par preseleccionado para competir
	 * @return el cromosoma con mejor fitness
	 */
	protected Chromosome getBestParent(ArrayList<Chromosome> selectedPair) {
			
		double fitnessOne = selectedPair.get(0).getFitnessValue();
		double fitnessTwo = selectedPair.get(1).getFitnessValue();
		if(fitnessOne>=fitnessTwo)
		{
			return selectedPair.get(0);
		}
		return selectedPair.get(1);
	}

	/**
	 * permite obtener un par de candidatos para el torneo
	 * @param pop poblacion de trabajo
	 * @return par de cromosomas competidores
	 */
	private ArrayList<Chromosome> getCompetitorPair(ArrayList<Chromosome> pop) {
		ArrayList<Chromosome> pair=new ArrayList<Chromosome>();
		int r = 0;
		int N = pop.size();
		int selectedPos =randGen.nextInt(N);
		//NOTA: obtener dos candidatos para el torneo es una estrategia usada normalmente 
		for(int i= 0; (i<2 || pair.size()<2);i++)
		{
			r = randGen.nextInt(N);
			if(r!=selectedPos)
			{
				pair.add(pop.get(r));
				//esto es para que no saque 2 veces el mismo cromosoma
				selectedPos=r;
			}
		}
		return pair;
	}

}
