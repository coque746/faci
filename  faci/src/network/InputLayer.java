package network;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import objectiveFunctions.Function;

public abstract class InputLayer extends Layer implements Serializable {
	protected Function activationFunction ;

	public abstract void print() throws IOException ;	// TODO metodo print en clase Input Layer

	public abstract void setInputToLayer(ArrayList<Double> input) ;
		
	
}
