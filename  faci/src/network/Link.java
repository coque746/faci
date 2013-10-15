package network;

import java.io.Serializable;

public class Link implements Serializable {

	private Neuron neuron;
	private double weight;
	
	public Link(Neuron n, double d)
	{
		this.setNeuron(n);
		this.setWeight(d);
	}
	
	public void setNeuron(Neuron neuron) {
		this.neuron=neuron;
	}

	public Neuron getNeuron() {
		return neuron;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight=weight;
		
	}

}
