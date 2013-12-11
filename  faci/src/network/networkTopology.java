package network;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import objectiveFunctions.Function;


import configuration.TestData;

public abstract class networkTopology extends ANNTopology implements Serializable{

	protected int sizeHidden;
	protected int sizeInput;
	protected int sizeOutput;

	protected int layerAmount;
	protected String inputHiddenFunction;
	protected String activHiddenFunction;
	protected String activOutPutFunction;
	
	
	//capas
	protected InputLayer inputLayer;
	protected List<HiddenLayer> hiddenLayers;
	
	protected OutputLayer outputLayer;
	
	//tasa de aprendizaje
	double n;
	////
	@Override
	public abstract void createTopology();

	

	@Override
	public abstract void validateNetwork();
	
	public abstract double[] getNetworkOutput();
	//public abstract void createLayers();
	public List<HiddenLayer> getHiddenLayers() {
		return hiddenLayers;
	}
	
	public OutputLayer getOutputLayer() {
		return outputLayer;
	}


	public int getSizeHidden() {
		return sizeHidden;
	}
	public void setSizeHidden(int sizeHidden) {
		this.sizeHidden = sizeHidden;
	}
	public int getSizeInput() {
		return sizeInput;
	}
	public void setSizeInput(int sizeInput) {
		this.sizeInput = sizeInput;
	}
	public int getSizeOutput() {
		return sizeOutput;
	}
	public void setSizeOutput(int sizeOutput) {
		this.sizeOutput = sizeOutput;
	}
	public abstract Function getActivationHiddenFunction();
	

	
	public abstract double[] evaluate(ArrayList<Double> in);
	

	
	public void setInput(ArrayList<Integer> in) {
		// TODO Auto-generated method stub
		
	}
	public abstract void updateWeights(double iterationError) ;



	public int getLayerAmount() {
		
		return this.layerAmount;
	}




//	public abstract void calculateDeltasandUpdate(double[] iterationErrors);
}
