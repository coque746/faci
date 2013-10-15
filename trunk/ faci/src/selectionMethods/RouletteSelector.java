package selectionMethods;

import java.util.ArrayList;
import java.util.Random;

import chromosomes.Chromosome;

public class RouletteSelector extends Selector {

	
	
	public RouletteSelector()
	 {
		selectionPool = new ArrayList<Chromosome>();
	 }

	@Override
	public Chromosome getSelection(ArrayList<Chromosome> pop, Chromosome crom) {
		
		double popFitness = getPopulationFitness(pop);
		int N = pop.size();
		//creo un objeto random para "girar" la ruleta
		Random r = new Random();
		double tournValue = 0;
		
		Chromosome selected=null;
		
		while(selected==null)
		{
			//solo se selecciona una pareja de padres
			tournValue = r.nextDouble();
				//agrego a la seleccion un individuo seleccionado de acuerdo al
				//valor que salio en la ruleta
			selected = selectIndividual(tournValue, pop, popFitness);
			if(crom==null){
				this.selectionPool.add(selected);
				pop.remove(selected);
				
			}
			else
			{
				if(!selected.equals(crom)){
					this.selectionPool.add(selected);
					pop.remove(selected);
				}
				else
				{
					selected = null;
				}
			}
			
		}
		return selected;
	}


	/**
	 * este metodo permite calcular el fitness de toda la población, el cual se define
	 * como la suma del fitness de cada individuo
	 * @param pop es la poblacion actual sobre la que se quiere generar la nueva generacion
	 * @return el fitness de toda la poblacion
	 */
	private double getPopulationFitness(ArrayList<Chromosome> pop) {

		double fitness =0;
		for(int i=0;i<pop.size();i++)
		{
			fitness+=pop.get(i).getFitnessValue();
		}
		return fitness;
	}

	/**
	 * este metodo selecciona el individuo de la poblacion que cae en el valor
	 * aleatorio que dio como resultado el "giro" de la ruleta
	 * @param tournValue valor en el que se detuvo la ruleta
	 * @param pop 
	 * @param popFitness 
	 * @return el chromosoma que es seleccionado de acuerdo al valor de la ruleta
	 */
	private Chromosome selectIndividual(double tournValue, ArrayList<Chromosome> pop, double popFitness) {
		
		double accumulator = 0;
		double Pm = 0;
		int position =0;
		Chromosome returnChrom = null;
		while((accumulator<tournValue) & (position < pop.size()))
		{
			Pm = pop.get(position).getFitnessValue()/popFitness;
			accumulator+=Pm;
			position++;
		}
		int positionSel=position-1;
		//se retorna el de position-1 porque se incrementa el valor de position luego de que ya se obtuvo
		//el valor buscado de fitness
		if(positionSel>=0)
		{
			//puede que ninguno encaje en el torneo
			returnChrom =pop.get(positionSel);
			//elimino el seleccionado de la poblacion
			pop.remove(positionSel);
			
		}
		return(returnChrom);
	}

}
