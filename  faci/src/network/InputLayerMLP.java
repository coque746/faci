package network;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class InputLayerMLP extends InputLayer implements Serializable {

	
	
	public InputLayerMLP(int layerSize) {
		
		//seteo el tamaño de la capa
		this.layerSize=layerSize;
		//en esta capa siempre la activacion es la identidad
		//System.out.print("capa entrada\n");
		this.activationFunction=new Identity();
		//System.out.print(layerSize+"\n tamaño capa");
		this.neuronList=new ArrayList<Neuron>(); 
		for(int i= 0; i<layerSize;i++)
		{
			//las neuronas toman el dato de la funcion de su capa, tienen una referencia a la capa a la que pertenecen
			Neuron aNeuron = new InputNeuron(this);
			//aNeuron.print();
			this.neuronList.add(aNeuron);	
		//	System.out.print("Neurona entrada"+String.valueOf(i)+ "--->");
			//aNeuron.print();
		}
		//creo el bias de la capa
		this.bias = new InputNeuron(this);
		this.bias.setInput(1);//entrada fija en 1
		this.neuronList.add(bias);
		
	}

	@Override
	public void print() throws IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter("out/salidaI.txt"));
		System.out.println("\n ---- CAPA ENTRADA ----\n");
		bw.append("\n -- En esta capa no se muestran pesos ni enlaces xq es de entrada, los --\n");
		bw.append("\n -- valores de entrada no son afectados por pesos, se distribuyen tal como ingresan --\n");
		for(int index = 0; index < this.neuronList.size()-1;index++)
		{
			Neuron neuron = this.neuronList.get(index);
			System.out.println("Neurona "+String.valueOf(index)+ ": Entrada=>"+String.valueOf(neuron.getInput())
					+ " || Salida: "+ String.valueOf(neuron.getOutput())+"\n");
			
			
		}
		bw.append("Bias: "+String.valueOf(this.bias.getInput()));
		bw.close();
	}

	@Override
	public void setInputToLayer(ArrayList<Double> input) {
		// TODO Auto-generated method stub
		if(this.layerSize==input.size())
		{
			for(int i = 0;i<layerSize;i++)
			{
				InputNeuron neuron= (InputNeuron) this.neuronList.get(i);
				neuron.setInput(input.get(i));				
			}
		}
	}

	

	@Override
	protected void updateWeights(double iterationError, double n) {
		// aca no se hace nada....las neuronas de esta capa no se conectan con otras y por ende no tienen 
		//pesos asignados
		
	}

	
	

}
