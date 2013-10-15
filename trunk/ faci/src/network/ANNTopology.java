package network;

import java.io.IOException;
import java.util.ArrayList;

import configuration.TestData;

public abstract class ANNTopology {

	protected String selectedActivFunction;

	//paso el objeto de config de forma q tome el tipo de neurona
	// el algoritmo de entrenamiento, cantidad de capas....etc
	
	public abstract void createTopology();
	public abstract void validateNetwork();
	public abstract void print() throws IOException;
	public abstract double[] evaluate(ArrayList<Double> arrayList);
	public abstract ANNTopology cloneNetwork();
	public CharSequence toLog() {
		// TODO metodo toLog en anntopology
		return null;
	}
	/**
	 * metodo que toma elementos para clasificarlos, se usa para PROBAR
	 * la tasa de clasificacion y fallos de la red
	 * @param data: los elementos que se clasificaran en la red
	 * @return array de double, que en la posicion 0 indica la proporcion de aciertos y en
	 *  la posicion 1 indica la cantidad de fallos detectados
	 */
	public abstract Double[] useTrainedNetwork(ArrayList<TestData> data);
	
	
}
