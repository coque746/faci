package network;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Neuron implements Serializable {

	/**
	 * 01/09/2012
	 * 	ENTRADAS: estan modeladas via el atributo==>inputs
	 * 	"linkList": atributo definido como una lista de objetos "Links"
	 * 	SALIDAS: las salidas de la neurona son calculadas a través de los valores de las entradas, la funcion de
	 *	entrada y la función de activación. La salida no ha sido modelada
	 *	como un atributo de la neurona, debido a la red esta modelada de forma que la salida sea solicitada por la capa siguiente.
	 *	En el caso de neuronas de borde(capa de salida de la red) quien solicite la salida de la red, será el manager
	 *	de la misma		
	 */
	//las neuronas tiene que tener determinado el valor de la entrada y almacenado como estado de la misma
	//de forma que se puedan utilizar estos valores para la funcion de activacion e indirectamente
	//para entrenamiento, validacion y uso

	protected ArrayList<Link> linkList;
	
	protected double actualValue;
	//creo estos atributos porque pueden ser necesarios
	protected double calculatedOutputValue ;
	protected double calculatedIntputValue ;
	
	

	public double getCalculatedOutputValue() {
		return calculatedOutputValue;
	}
	
	public ArrayList<Link> getInputs() {
		return linkList;
	}

	
	
	//no todas las neuronas lo van a reescribir
	//las neuro de entrada solo lo van a reescribir
	public abstract double getOutput();
	public abstract double getInput();
	public void connectNeuron(List<Neuron> otherLayerNeuronList) {

		for(int i=0;i<otherLayerNeuronList.size();i++)
		{
			Random r = new Random();
			Neuron neuron =  otherLayerNeuronList.get(i);
			double weight = r.nextDouble();
			Link aLink = new Link(neuron,r.nextDouble());
			this.addInput(aLink);			
		}
		
	}


	public void print()
	{
		System.out.print("Hola soy neurona\n");
	}
	
	public void addInput(Link aLink)
	{
		this.linkList.add(aLink);
	}

	public void updateWeights(double iterationError, double n) {
		int i =0;
		if(this.linkList!=null)
		{
			while (i<linkList.size() && (this.linkList.get(i)!=null) )
			{
				Link l = this.linkList.get(i); 
				double weight = this.calculateNewWeight(iterationError,l, n);
				l.setWeight(weight);
				i++;
			}
		}
	}

	protected abstract double calculateNewWeight(double iterationError, Link l,double n);

	public abstract double getCalculatedInput();
/*
	public void connectNeuronToInOutput(List<Neuron> otherLayerNL) {

		for(int i=0;i<otherLayerNL.size();i++)
		{
			Random r = new Random();
			Link aLink = new Link(otherLayerNL.get(i),r.nextDouble());
			this.addInput(aLink);			
		}
		
		
	}
*/
	public ArrayList<Double> getWeights() {
		// TODO Auto-generated method stub
		ArrayList<Double> weightList=new ArrayList<Double>();
		for(int i = 0;i<this.linkList.size();i++)
		{
			weightList.add(this.linkList.get(i).getWeight());
		}
		return weightList;
	}


	
}
