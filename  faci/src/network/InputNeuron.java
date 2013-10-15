package network;

import java.util.ArrayList;

import objectiveFunctions.Function;

public class InputNeuron extends Neuron {

	private double input;
	private Layer containerLayer;
	
	public InputNeuron(Layer container) {
		
	 this.containerLayer=container;
	 
	}

	
	public double getInput() {
		return input;
	}

	public void setInput(double input) {
		this.input = input;
		this.calculatedIntputValue=input;
		this.calculatedOutputValue=input;
	}

	private Function getActivationFunction() {
		return this.containerLayer.getActivationFunction();
	}

	public double getCalculatedOutputValue()
	{
		return this.calculatedIntputValue;
	}

	@Override
	public double getOutput() {
		//en este caso, la funcion de entrada no existe, la 
		//de activacion en si, tampoco ( a los efectos de hacer calculos)
		return this.input;
	}


	


	@Override
	public double getCalculatedInput() {
		
		return this.getInput();
	}


	@Override
	protected double calculateNewWeight(double iterationError, Link l, double n) {
		// este metodo no hace nada (solo en esta clase de neuro)
		return 0;
	}



	


	
	
	
}
