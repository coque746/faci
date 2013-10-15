package validation;

import java.util.ArrayList;
import java.util.Collections;

import network.networkTopology;
import configuration.TestData;



public class KFoldValidator extends Validator {
	
	private ArrayList<ArrayList<TestData>> foldsList;
	private ArrayList<TestData> tests;
	
	
	/*
	 * constructor
	 */
	public KFoldValidator(networkTopology trainedNet,int folds,
			ArrayList<TestData> testingData,double bestValidationError) {
		super(trainedNet, testingData,bestValidationError);
	this.foldsList = new ArrayList<ArrayList<TestData>>();
    this.possibleBeta = new ArrayList<Integer>();
    this.possibleBeta.add(2);
    this.possibleBeta.add(5);
    this.possibleBeta.add(10);

		
	}

@Override
public void getTrainingData(int k, ArrayList<TestData> testsData, ArrayList<ArrayList<ArrayList<ArrayList<Double>>>>dataMatrix)
{
    	
		//creacion de folds
	this.tests=testsData;	
	this.createFolds(k, testsData);
	for(int i = 0;i<k;i++)
	{
		this.createDataMatrix( i, dataMatrix);
	}
	
		
    	
  }

	private void createDataMatrix(int K,ArrayList<ArrayList<ArrayList<ArrayList<Double>>>>dataMatrix)
	{
		//array de arrays para la matriz...es decir que dataMatrix[0]= lista de entradas de entrenamiento
		ArrayList<ArrayList<ArrayList<Double>>> column = new ArrayList<>();
				
    	ArrayList<TestData> trainingData = new ArrayList<TestData>();
    	ArrayList<TestData> validationData = new ArrayList<TestData>();
    	for(int i=0;i<this.foldsList.size();i++)
    	{
    		if(i!=K){
    			for(int j=0;j<foldsList.get(i).size();j++)
    			{
    				trainingData.add(foldsList.get(i).get(j));	
    			}
    		}
    		else
    		{
    			for(int j=0;j<foldsList.get(i).size();j++)
    			{
    				validationData.add(foldsList.get(i).get(j));	
    			}
    			
    		}
    	}
    	//lo usamos para mezclar los casos
    	ArrayList<Integer> l = new ArrayList<Integer>(trainingData.size());
        ArrayList<Integer> m = new ArrayList<Integer>(validationData.size());
        for (int j = 0; j < trainingData.size(); j++) {
            l.add(j);
        }
        for (int j = 0; j < validationData.size(); j++) {
            m.add(j);
        }
        Collections.shuffle(l);
        Collections.shuffle(m);
        
        
        //fin de la mezcla
        //cargo los datos de entrenamiento...todos los folds menos el "K"
        ArrayList<ArrayList<Double>> inputTrain = new ArrayList<>();
        ArrayList<ArrayList<Double>> targetTrain = new ArrayList<>();
        ArrayList<ArrayList<Double>> inputValidation = new ArrayList<>();
        ArrayList<ArrayList<Double>> targetValidation = new ArrayList<>();
        
        
        for (int i = 0; i < trainingData.size(); i++) {
        	//getInputData retorna un array de array de enteros (el array principal tiene toooodos los renglones del archivo que leyo)
        	ArrayList<ArrayList<Double>> inputData =trainingData.get(l.get(i)).getInputData();
        	//ArrayList<Double> input =inputData.get(l.get(i));
        	ArrayList<Double> input =inputData.get(0);
        	inputTrain.add(input);            
            targetTrain.add(trainingData.get(l.get(i)).getTargetData().get(0));
        }
        //armo la columna 
        column.add(inputTrain);
        column.add(targetTrain);
        //cargo los datos de validacion...solo el fold "K"
        for (int j = 0; j < validationData.size(); j++) {
            inputValidation.add(validationData.get(m.get(j)).getInputData().get(0));
            targetValidation.add(this.tests.get(m.get(j)).getTargetData().get(0));
        }
        column.add(inputValidation);
        column.add(targetValidation);
        //ahora en cada columna de la matriz, voy a tener los distintos folds 
        //con los cuales trabajar
        dataMatrix.add(column);
	
	}
	
	private void createFolds(int k, ArrayList<TestData> tests)
	{
    	int testSize = tests.size();
    	int foldSize = (int) testSize/k;
    	int pos =0;
    	ArrayList<TestData> foldJ=new ArrayList<TestData>();
    	for(int i=0;i<k;i++)
    	{
    		foldJ = new ArrayList<TestData>();
    		for(int l=pos;l<pos+foldSize;l++)
    		{
    			foldJ.add(tests.get(l));
    		}
    		this.foldsList.add(foldJ);
    		pos+=foldSize;
    	}
		
	}
	


}
