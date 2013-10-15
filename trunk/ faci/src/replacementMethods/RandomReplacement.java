package replacementMethods;

import java.util.ArrayList;
import java.util.Random;

import chromosomes.Chromosome;

public class RandomReplacement extends Replacement {

	/**
	 * esta clase hace el reemplazo seleccionando al azar a dos individuos y reemplazándolos 
	 * con los hijos de la nueva generación
	 */
	@Override
	public void makeReplacement(ArrayList<Chromosome> actualPopulation,
			ArrayList<Chromosome> parents, ArrayList<Chromosome> children,int elapsedGenerations) {
		 Random r = new Random();
		 int popSize = actualPopulation.size();
		 int position=0;
		 int replaced =0;
		 Chromosome selChrom;
		 //itera hasta que se insertan 2 individuos en la poblacion		 
		 while((replaced<2))
		 {
			 System.out.println("Popsize "+popSize);
			 position = r.nextInt(popSize);
			 selChrom = actualPopulation.get(position);
			 //si el cromosoma seleccionado no es un individuo recien insertado...hago el reemplazo 
			 if(selChrom.getAge()>0 || (elapsedGenerations==0 && selChrom.getAge()==0))
			 {
				 //elimino de la poblacion el cromosoma seleccionado al azar
				actualPopulation.remove(selChrom);
				//agrega el hijo cuya posicion es la misma cantidad que la de reemplazados
				//que va de 0 a 1
				actualPopulation.add(children.get(replaced));
				replaced++;
			 }
			 
		 }
	}

	@Override
	public String getReplacementStrategy() {
		return "RandomReplacement";
	}

}
