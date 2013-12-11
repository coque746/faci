package validation;

import java.io.IOException;
import java.util.ArrayList;

import trainingMethod.Algorithm;



import network.networkTopology;

import configuration.TestData;

public abstract class Validator {
	
	 private double validationError;
	 private networkTopology trainedNetwork;
	 private ArrayList<TestData> data;
	 private boolean stopTrain;
	 protected ArrayList<Integer> possibleBeta;
	 protected double bestValidationError;
	 private ArrayList<ArrayList<Double>> inputData;
	 private ArrayList<ArrayList<Double>> targetData;
	 
	 public Validator(networkTopology trainedNet, ArrayList<TestData> validationData,double bestValError) {
	        
		 validationError=0;
		 trainedNetwork=trainedNet;
		 data = validationData;		 
		 stopTrain=false;
		 this.bestValidationError=bestValError;
	    }
	 
	 /**
	  * 
	  * @return devuelve (dentro del parametro dataMatrix) los datos que se usaran para entrenar, cada implementacion lo hara de acuerdo a sus caracteristicas
	  */
	 public abstract void getTrainingData(int folds, ArrayList<TestData> tests, ArrayList<ArrayList<ArrayList<ArrayList<Double>>>>dataMatrix);
	 
	 
	 
	 public boolean stopTrain()
	 {
		 return this.stopTrain;
	 }
	
	 
	 public double validate ( Algorithm algorithm) throws IOException
	 {
		algorithm.setIn(inputData);
		algorithm.setTarget(targetData);
		algorithm.evaluateValidationCases();
		validationError= algorithm.getCuadraticMediumError();
		return validationError;
	}
	
	public ArrayList<Integer> getPossibleBeta()
	 { return this.possibleBeta;}

	public void setInput(ArrayList<ArrayList<Double>> inputValidation) 
	{	this.inputData=inputValidation;	}

	public void setTarget(ArrayList<ArrayList<Double>> targetValidation) {
		this.targetData=targetValidation;
		
	}

	
}
