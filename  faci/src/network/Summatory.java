package network;

import java.io.Serializable;
import java.util.ArrayList;

import chromosomes.Chromosome;
import objectiveFunctions.Function;

/**
 * clase summatory implemnenta la funcion sumatoria, funcion utilizada 
 * principalmente como funcion de entrada a la neurona 
 *
 */
public class Summatory implements Function,Serializable {

	@Override
	public double apply(double s) {
		// este metodo esta por verse
		return 0;
	}

	@Override
	public double applyDf(double s) {
		//este metodo no se va a usar, ya qeu la sumatoria implica varios argumentos
		return 0;
	}

	@Override
	public String print() {
		return "Summatory";
	}

	@Override
	public double apply(ArrayList<Double> s) {
		double result =0; 
		for(int i =0;i<s.size();i++)
		{
			result+= s.get(i);
		}
		
		return result;
	}
	
	@Override
	public double applyDf(ArrayList<Double> s) {
		
//		retorno la cantidad de elementos de la entrada...puesto q la derivada de cada elemento de la sumatoria
//		me daria como resultado, la suma de tantos unos como valores de entrada tenga ;)
		return s.size();
	}

	@Override
	public double applyToPopulation(ArrayList<Chromosome> s) {
		// TODO Auto-generated method stub
		return 0;
	}
}
