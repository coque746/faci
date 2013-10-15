package network;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import objectiveFunctions.Function;

public class OutputLayer extends Layer {

	private Function inputFunction;



	public OutputLayer(String inputFunction, int layerSize) {
		
		//funcion q usan las neuronas para calcular el valor de la entrada
		//generalmente es Summatory
		//System.out.print("capa de salida \n");
		//System.out.print(inputFunction);
		
		this.setInputFunction(this.createFunction(inputFunction));
		this.setLayerSize(layerSize);
		this.neuronList=new ArrayList<Neuron>(); 
		//System.out.print("tamaño capa oc "+String.valueOf(layerSize));
		for(int i= 0; i<layerSize;i++)
		{//System.out.println(i);
			Neuron aNeuron = new OutputNeuron(this);
			this.neuronList.add(aNeuron);
		//	System.out.print("Neurona salida"+String.valueOf(i)+ "--->");
			//aNeuron.print();
		}
		//creo el bias de la capa
		this.bias = new InputNeuron(this);
		this.bias.setInput(1);//entrada fija en 1
		this.neuronList.add(bias);
	
	}


	@Override
	public List<Neuron> getNeuronList() {
		return this.neuronList;
	}

	
	
	public void connect(Layer layer) {
			
			List<Neuron> otherLayerNL = layer.getNeuronList();
			//las conexiones van hasta tamaño menos uno
			//xq la ultima neurona es una neurona de entrada (bias)
			for(int i=0;i<this.neuronList.size()-1;i++)
			{
				this.neuronList.get(i).connectNeuron(otherLayerNL);
				
			}
		
		}


	public Function getInputFunction() {
		return this.inputFunction;
	}

	public void setInputFunction(Function inputFunction) {
		this.inputFunction = inputFunction;
	}


	public void print() throws IOException {
	/*	BufferedWriter bw = new BufferedWriter(new FileWriter("out/salidaO.txt"));*/
		
		System.out.println("\n ---- CAPA SALIDA ----\n");
		//el for recorre todas las neuronas menos la ultima q es el bias
		for(int index = 0; index < this.neuronList.size()-1;index++)
		{
			Neuron neuron = this.neuronList.get(index);
			System.out.println("Neurona "+String.valueOf(index)+ ": Entrada=>"+String.valueOf(neuron.getInput())
					+ " || Salida: "+ String.valueOf(neuron.getOutput())+"\n");
			List<Link> inputs = neuron.getInputs();
			for(int i=0;i<neuron.getInputs().size();i++)
			{ //System.out.println("entrddda");
				Link enlace = inputs.get(i); 
				System.out.println("                     Conexion => Valor: "+String.valueOf(enlace.getNeuron().getOutput())
						+" || Peso: "+ String.valueOf(enlace.getWeight())+"\n");

			}
	/*	bw.append("Bias Capa Salida: "+String.valueOf(this.bias.getInput()));
		bw.close();*/
		}
		
	}


	public double[] getOutPut() {
		//este metodo en esta capa en particular, devolvera la salida de la red
		double[] outputs = new double[this.neuronList.size()];
		//1-recorro la lista de neuronas de la capa
		for(int i=0;i<this.neuronList.size();i++)
		{
			
			Neuron aNeuron =  this.neuronList.get(i);
			outputs[i]=aNeuron.getOutput();
		}
		return outputs;
	
	
	
	}


	@Override
	public void updateWeights(double iterationError, double n) {
		
	
		for(int i=0;i< this.neuronList.size();i++)
		{
			Neuron aNeuron =this.neuronList.get(i);
			if(aNeuron!=null)
			{				
				aNeuron.updateWeights(iterationError, n);
			}
			
			
		}
	}

/*
	protected void calculateDeltasandUpdate(double n, double[] iterationErrors) {
		
		this.deltas = new ArrayList<ArrayList<Double>>();
		//por cada neurona "i"...
		for(int i = 0;i<this.neuronList.size();i++)
		{
			Neuron thisNeuron = this.neuronList.get(i);
			double deltai = iterationErrors[i];
			
			//por cada conexion j....
			ArrayList<Link> links =   thisNeuron.getInputs();
			for(int j = 0; j<links.size();j++)
			{
				double wij = this.matrixWeights.get(i).get(j);
				double Yi = thisNeuron.getCalculatedInput();
				double newWij = wij+(n*(deltai)*Yi);
				//ya actualizo el peso en la red...pero no todavia en la matrixWeights
				links.get(j).setWeight(newWij);
				//los delta de cada neurona i
				this.deltas.get(i).add(deltai);
			}
		}
		
		
	}*/
	

}
