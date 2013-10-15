package network;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import objectiveFunctions.Function;

public class HiddenLayer extends Layer {
	private Function inputFunction;
	private Function activationFunction;
	

	public HiddenLayer(String inputFunction, String activationFunction, int layerSize)
	{
		//funcion q usan las neuronas para calcular el valor de la entrada
		//generalmente es Summatory
		//System.out.print("capa oculta \n"+inputFunction);
		this.inputFunction=this.createFunction(inputFunction);
		this.activationFunction= this.createFunction(activationFunction);
		this.setLayerSize(layerSize);
		this.neuronList=new ArrayList<Neuron>(); 
		for(int i= 0; i<layerSize;i++)
		{
			Neuron aNeuron = new HiddenNeuron(this);
			this.neuronList.add(aNeuron);
		}
		//creo el bias de la capa
		this.bias = new InputNeuron(this);
		this.bias.setInput(1);//entrada fija en 1
		this.neuronList.add(bias);
		
		
	}
	

	public Function getInputFunction() {
		return inputFunction;
	}


	public Function getActivationFunction() {
		return activationFunction;
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

	
	/**metodo que retorna la salida de cada neurona de la capa
	 * devuelve una lista de salidas neuronales
	 */
	public ArrayList<Double> getOutput()
	{
		ArrayList<Double> output = new ArrayList<Double>();
		for(int i = 0;i<this.getLayerSize();i++)
		{
			output.add(this.neuronList.get(i).getOutput());
		}
		return output;
		
	}
	
	
	public List<Neuron> getNeuronList() {
		
		return this.neuronList;
	}


	public void print() throws IOException {
		
		/*BufferedWriter bw = new BufferedWriter(new FileWriter("out/salidaH.txt"));*/
		System.out.println("\n ---- CAPA OCULTA ----\n");
		//el for recorre todas las neuronas menos la ultima q es el bias
		for(int index = 0; index < this.neuronList.size()-1;index++)
		{
			Neuron neuron = this.neuronList.get(index);
			System.out.println("Neurona "+String.valueOf(index)+ ": Entrada=>"+String.valueOf(neuron.getInput())
					+ " || Salida: "+ String.valueOf(neuron.getOutput())+"\n");
			List<Link> inputs = neuron.getInputs();
			for(int i=0;i<inputs.size();i++)
			{
				Link enlace = inputs.get(i); 
				System.out.println("                     Conexion => Valor: "+String.valueOf(enlace.getNeuron().getOutput())
						+" || Peso: "+ String.valueOf(enlace.getWeight())+"\n");
			}
			
		}
		/*bw.append("Bias Capa Oculta: "+String.valueOf(this.bias.getInput()));
		bw.close();*/
	}


	@Override
	protected void updateWeights(double iterationError, double n) {
		
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
	public void connectToInput(InputLayer layer) {
		
		List<Neuron> otherLayerNL = layer.getNeuronList();
		//las conexiones van hasta tamaño menos uno
		//xq la ultima neurona es una neurona de entrada (bias)
		for(int i=0;i<this.neuronList.size()-1;i++)
		{
			this.neuronList.get(i).connectNeuronToInOutput(otherLayerNL);
			
			
		}
		
	}
*/

	public ArrayList<ArrayList<Double>> getWeights() {
		
		ArrayList<ArrayList<Double>> weights = new ArrayList<ArrayList<Double>>();
		for(int i=0;i<this.neuronList.size();i++)
		{
			// por cada neurona i
			Neuron aNeuron = neuronList.get(i);
			for(int j =0;j<aNeuron.linkList.size();j++)
			{
				//obtengo el peso ij asoc a la neurona i con la neurona j
				double weightLink = aNeuron.linkList.get(j).getWeight();
				weights.get(i).add(weightLink);
			}
				
		}
		return weights;
	}

/*
	@Override
	protected void calculateDeltasandUpdate(double n, ArrayList<ArrayList<Double>> deltasPrev) {
		// TODO Auto-generated method stub
		
	}
*/



	


}
