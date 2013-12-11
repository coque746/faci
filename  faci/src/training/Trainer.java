package training;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import trainingMethod.Algorithm;


import exceptions.ExceptionWrongParameterClass;
import network.ANNTopology;
import network.networkTopology;

public abstract class Trainer {
 protected Algorithm algorithm;
 protected networkTopology trainingNetwork;
 //target de entrenamiento
 protected ArrayList<ArrayList<Double>> targetTrain;
 //entrada de entrenamiento
 protected ArrayList<ArrayList<Double>> inputTrain;
 protected networkTopology originalTrainingNetwork;
 protected double learnVelocity;
protected double errorTarget;
protected int age;
 
 public ANNTopology training(int repeat, double splitPercent) throws IOException, ExceptionWrongParameterClass
	{

	   //seteo los datos de entrenamiento en el
	   this.trainingNetwork = (networkTopology) this.originalTrainingNetwork.cloneNetwork();
	   algorithm.setIn(inputTrain);
       algorithm.setTarget(targetTrain);
	   algorithm.initializeForRNA(this.trainingNetwork, this.learnVelocity);	
	   
     //aca debo setear la "trainingNetwork" con una copia de la red original

     double calcValidationError = 1;
     double calcError = 1;
	   for (int cicle = 0; cicle < repeat; cicle++) {
			//para que no salga en el 1º loop
			for (int i=0; i < age && calcError > errorTarget ; i++) {
				algorithm.runAlgorithm();
				calcError = algorithm.getCuadraticMediumError();
			}
		}
	
		return this.trainingNetwork;
	
		
	}
	public abstract void saveBestRepresentation() throws FileNotFoundException, IOException, ClassNotFoundException;
}
