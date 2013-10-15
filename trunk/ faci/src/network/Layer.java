package network;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import objectiveFunctions.Function;


public abstract class Layer implements Serializable {
	
	
	protected List<Neuron> neuronList;
	protected InputNeuron bias;
	protected Function activationFunction;
	protected int layerSize;
	
	protected void setAllWeights(ArrayList<ArrayList<Double>> weights)
	{
		for(int i=0;i<weights.size();i++)
		{//por cada neurona
			ArrayList<Link> neuronILinkList = this.neuronList.get(i).linkList;
			for(int j=0;j<neuronILinkList.size();j++)
			{
				//tomo el valor del peso de la conexion entre la neurona i en el peso j
				double weightValue = weights.get(i).get(j);
				//tomo el enlace j de la neurona i y le seteo el valor
				neuronILinkList.get(j).setWeight(weightValue);
			}
			
		}
		
	}


	protected ArrayList<ArrayList<Double>> deltas;
	public List<Neuron> getNeuronList()
	{
		return this.neuronList;
	}
	
	public Function getActivationFunction() {
		
		return this.activationFunction;
	}
	/**
	 *  
	 * @return cantidad de neuronas en la capa
	 */
	
	public int getLayerSize() {
		return layerSize;
	}

	public void setLayerSize(int layerSize) {
		this.layerSize = layerSize;
	}

	public String getActivationFunctionName() {
		return this.activationFunction.print();
		
	}



	public ArrayList<ArrayList<Double>> getDeltas() {
		return deltas;
	}

	protected Function createFunction(String function) {
		// este metodo permite crear una funcion 
		 // de acuerdo al parametro que llega...este parametro es tomado 
		 // al crear la red, desde el archivo de configuracion
		 
		Function aFunction=null;
		//System.out.print("la funcion es: "+function);
		function = function.trim();
		switch (function)
		{
		case "Sigmoide":
			aFunction = new Sigmoid();
			break;
		case "Centroid":
			aFunction = new Centroid();
			break;
		case "Summatory":
			aFunction = new Summatory();
			break;
		case "TanH":
			aFunction = new TanH();
			break;
		case "CuadraticMedium":
			aFunction = new CuadraticMedium();
			break;
		case "Identity":
			aFunction = new Identity();
			break;
		}
		return aFunction;
	}
	
	 protected abstract void updateWeights(double iterationError, double n);


}
