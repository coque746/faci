package network;

import java.util.ArrayList;
import java.util.List;

import objectiveFunctions.Function;

public class OutputNeuron extends Neuron {

	private double calculatedIntputValue;
	//enlaces a la capa anterior

	private OutputLayer containerLayer;
	
	
	
	public OutputNeuron(OutputLayer container) {
		this.containerLayer=container;
		this.linkList=new ArrayList<Link>();
	}

	@Override
	public double getOutput() {
		//calculo las entradas cada vez que quiero obtener la salida
		this.calculatedIntputValue = this.calculateInput();
		//System.out.println("emtrada :"+this.calculatedIntputValue);
		//no tiene funcion de salida ni activacion...la entrada a esta neurona es la salida de la misma
		return this.calculatedIntputValue;
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
		//la funcion de entrada trabaja sobre la lista de conexiones entrantes
		return inputF.apply(connectionsOutput);
	}

	@Override
	public double getInput() {
		this.calculatedIntputValue=this.calculateInput();
		//System.out.println("Valor calculado de entrada neurona oculta: "+String.valueOf(this.calculatedIntputValue));
		return this.calculatedIntputValue;
	}
	
	


	@Override
	public double getCalculatedOutputValue() {
		//la salida es igual a la entrada (depende solo de la funcion de entrada)
		return this.calculatedIntputValue;
	}

	@Override
	protected double calculateNewWeight(double iterationError, Link l, double n)  {
		Neuron neuro = l.getNeuron();
		double weight_prev = l.getWeight();
		double output = neuro.getCalculatedOutputValue();
		double input = this.getCalculatedInput();
		Function func = this.containerLayer.getActivationFunction();
		//esto lo cambio porque el error se va al carajo
		//double newWeight = weight_prev + (n*(iterationError)*input);
		double newWeight = weight_prev - (n*(iterationError)*input);
		return newWeight;
		
		
	}

	@Override
	public double getCalculatedInput() {
		return this.calculatedIntputValue;
	}

	
}
