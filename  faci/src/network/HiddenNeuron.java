package network;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import objectiveFunctions.Function;

public class HiddenNeuron extends Neuron {

	
	/**
	 * en esta clase, las funciones de entrada y activacion 
	 * estan en la Capa que la contiene, por lo tanto los atributos
	 * especificos de una HiddenNeuron son 
	 * 
	 * Una lista de enlaces (Link) a las neuronas de la capa anterior
	 * esta lista es seteada en la creacion de la capa contenedora
	 * @param container (la capa donde se encuentra contenida la neurona
	 * 
	 */
	
	
	private HiddenLayer containerLayer;
		
	public HiddenNeuron(HiddenLayer container) {
		this.containerLayer= container;
		this.linkList=new ArrayList<Link>();
		
	
	}

	public double getOutput() 
	{
			//calculo las entradas cada vez que quiero obtener la salida
			this.calculatedIntputValue = this.calculateInput();
			//obtengo la funcion de activacion desde la capa contenedora
			Function activationF = this.containerLayer.getActivationFunction();
			
			this.calculatedOutputValue=activationF.apply(this.calculatedIntputValue);
			return this.calculatedOutputValue;
	}



	private double calculateInput() {
		ArrayList<Double> connectionsOutput = new ArrayList();
		Function inputF = this.containerLayer.getInputFunction();
		
		for (int i = 0; i < this.linkList.size(); i++) {
			/**calculo el valor de la conexion con la neurona "i"
			 * como el valor de salida de dicha neurona por el peso de la conexion con ella
			 */
			
			double inputElement = linkList.get(i).getNeuron().getOutput();
			double weightElement = linkList.get(i).getWeight();
			double anInput = inputElement*weightElement;
			//agrego el valor "entrante" a la lista que voy a pasar a la funcion de entrada
			connectionsOutput.add(anInput);
		}
		this.calculatedIntputValue= inputF.apply(connectionsOutput);
		//System.out.println("Valor calculado de entrada neurona Hidden: "+String.valueOf(this.calculatedIntputValue));
		return this.calculatedIntputValue;
	}



	@Override
	public double getInput() {
		this.calculateInput();
		return this.calculatedIntputValue;
		
	}


	@Override
	public double getCalculatedInput() {
		return this.calculatedIntputValue;
	}

	@Override
	protected double calculateNewWeight(double iterationError, Link l, double n) {
		//tomo la neurona conectada por el link "l"
		Neuron neuro = l.getNeuron();
		//obtengo el peso de la conexion
		double weight_prev = l.getWeight();
		//obtengo la salida de la neurona con la que estoy conectada (xj)
		double output = neuro.getCalculatedOutputValue();
		double input = neuro.getCalculatedInput();
		Function func = this.containerLayer.getActivationFunction();
		double dfActivationFunction = func.applyDf(iterationError);
		double newWeight = weight_prev - (n*(dfActivationFunction)*input);
		return newWeight;
	}



	
	
}
