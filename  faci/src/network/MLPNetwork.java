package network;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import objectiveFunctions.Function;

import configuration.Config;
import configuration.ConfigRNA;
import configuration.TestData;

public class MLPNetwork extends networkTopology implements Serializable  {


	public MLPNetwork(ConfigRNA config) {
		super();
		this.config=config;
		//tamaño de cada tipo de capa
		this.sizeInput = config.getSizeIn();
		this.sizeOutput = config.getSizeOut();
		this.sizeHidden = config.getSizeHidden();
		//cantidad de capas
		this.layerAmount=config.getLayerAmount();
		
		//funcion de entrada a neuronas de capa oculta
		this.inputHiddenFunction = config.getInputHiddenFunction();
		//System.out.print("funcion entrada capa oculta:"+this.inputHiddenFunction+"\n");
		//funcion de activacion neuronas de capa oculta
		this.activHiddenFunction = config.getActivationHiddenFunction();
		this.activOutPutFunction = config.getActivationOutputFunction();
		this.n=config.getN();
		//System.out.print("funcion activacion capa oculta:"+this.activHiddenFunction+"\n");
	}

	public List<HiddenLayer> getHiddenLayers()
	{
		return this.hiddenLayers;
	}
	
	@Override
	public void createTopology() {
		
		//cantidad de capas ocultas
		int hiddenAmount = this.layerAmount - 2;
	
		
		//creo las capas de entrada y salida
		//la capa de entrada no tiene funcion de entrada ni de activacion (se setea sola- dado el tipo de capa- a Identity) 
		//ni tampoco funcion de entrada
		this.inputLayer = new InputLayerMLP(this.sizeInput);
		this.outputLayer = new OutputLayer (this.activOutPutFunction,this.sizeOutput);
		this.hiddenLayers=new ArrayList<HiddenLayer>(); 
		//creo las capas ocultas
		for(int i=0;i<hiddenAmount;i++)
		{
			HiddenLayer hLayer = new HiddenLayer(this.inputHiddenFunction,this.activHiddenFunction, this.sizeHidden);
			
			this.hiddenLayers.add(hLayer);
			if(i==0)
			{
				this.hiddenLayers.get(i).connect(this.inputLayer);
			}
			else
			{
				this.hiddenLayers.get(i).connect(this.hiddenLayers.get(i-1));
			}
		}
		//la ultima capa oculta..q es con la que se tiene q conectar la capa de salida
		int lastLayerPos = hiddenAmount-1;
		this.outputLayer.connect(this.hiddenLayers.get(lastLayerPos));
		
		
	}

	
	

	@Override
	public void validateNetwork() {
		// TODO Auto-generated method validateNetwork		mlpnetwork
		
	}

	@Override
	public void print() throws IOException {
		this.inputLayer.print();
		for(int i=0;i<this.hiddenLayers.size();i++)
		{
			this.hiddenLayers.get(i).print();
		}
		this.outputLayer.print();
	}

	@Override
	public double[] getNetworkOutput() {
		return this.outputLayer.getOutPut();
	}

	@Override
	public Function getActivationHiddenFunction() {
		//obtengo la funcion de activacion de la 1º capa oculta, ya que se repite en todas las ocultas
		Function f = this.hiddenLayers.get(0).getActivationFunction();
		return f;
	}

	@Override
	public double[] evaluate(ArrayList<Double> input) {
		this.inputLayer.setInputToLayer(input);
		double[] networkOutput = this.outputLayer.getOutPut();
		return networkOutput;
	}

	@Override
	public void updateWeights(double iterationError) {
		
		this.outputLayer.updateWeights(iterationError,  this.n);
		//va de la ultima a la primera
		for(int i=this.hiddenLayers.size()-1; i>=0; i--)
		{
			this.hiddenLayers.get(i).updateWeights(iterationError, this.n);
		}
	}
	
	public double getN()
	{
		return this.n;
	}



	private ArrayList<ArrayList<Double>> extractLayerWeights(Layer layer)
	{
		List<Neuron> neuronList = layer.getNeuronList();
		ArrayList<ArrayList<Double>> weights = new ArrayList<ArrayList<Double>>();
		int tope = neuronList.size()-1;
		/*uso la var tope xq la ultima neurona es de tipo InputNeuron (el bias) que
		 * no tiene enlaces
		 */
		
		for(int i =0;i<tope;i++)
		{	
				Neuron n = neuronList.get(i);
				weights.add(n.getWeights());
		}
		return weights;
	}
	

	@Override
	public ANNTopology cloneNetwork() {
		//nueva red con la misma configuracion
		
		
		MLPNetwork newNet = new MLPNetwork(config);
		newNet.createTopology();
		ArrayList<ArrayList<Double>> inputWeights = new ArrayList<ArrayList<Double>>();
/*	LAS CAPAS DE ENTRADA NO TIENEN ENLACESSSSS!!!!!!!!!!		
 * inputWeights=this.extractLayerWeights(this.inputLayer);
		//capa de entrada
		newNet.inputLayer.setAllWeights(inputWeights);
*/		//por cada capa oculta
		for(int j = 0; j< this.hiddenLayers.size();j++)
		{
			List<HiddenLayer> listL =newNet.getHiddenLayers(); 
			HiddenLayer l =listL.get(j); 
			inputWeights = this.extractLayerWeights(this.hiddenLayers.get(j));
			
			l.setAllWeights(inputWeights);
		}
		
		inputWeights=this.extractLayerWeights(this.outputLayer);
		newNet.getOutputLayer().setAllWeights(inputWeights);
		return newNet;
	}


	
	@Override
	public Double[] useTrainedNetwork(ArrayList<TestData> data) {
	
		Double statistics[] = new Double[2];
		double success = 0;
		double failure =0;
		double error =0;	
		for(int j=0;j<data.size();j++)
		{
			
			TestData element = data.get(j);
			ArrayList<Double> in = element.getInputData().get(0);
			ArrayList<Double> target = element.getTargetData().get(0);
			double[] output = this.evaluate(in);
			error = calculateNetworkGeneralError(output,target);
			if(error<=0.003)
			{success++;}
			else
			{failure++;}
		}
		System.out.println("success "+success);
		System.out.println("fail "+failure);
		success/=data.size();
		failure/=data.size();
		statistics[0]=success;
		statistics[1]=failure;
		return statistics;
		
	}


	private double calculateNetworkGeneralError(double[] output, ArrayList<Double> target) {
		
		double sum =0;
		for(int i=0;i<target.size();i++)
		{
			sum += Math.pow((target.get(i)-output[i]),2);
		}
		sum/=2;
		return sum;
	}



	/*@Override
	public void calculateDeltasandUpdate(double[] iterationErrors) {
		this.outputLayer.calculateDeltasandUpdate(this.n,iterationErrors);
		ArrayList<ArrayList<Double>> deltasOut = this.outputLayer.getDeltas();
		for(int i=0;i<this.hiddenLayers.size();i++)
		{
			this.hiddenLayers.get(i).calculateDeltasandUpdate(this.n,deltasOut);
		}
		
	}*/

}
