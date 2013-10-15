package network;

import java.io.Serializable;
import java.util.ArrayList;

import objectiveFunctions.Function;

/** 
 * clase identity implementa la funcion identidad, usada como funcion de activacion de neuronas de capa de entrada y de salida
 * 
 */

public class Identity implements Function, Serializable {



	@Override
	public double applyDf(double s) {
		// TODO metodo applydf en clase identity
		return 0;
	}

	@Override
	public String print() {
		return "Identity";	
	}

	@Override
	public double apply(double s) {
			return s;
	}

	@Override
	public double apply(ArrayList<Double> s) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double applyDf(ArrayList<Double> s) {
		// TODO Auto-generated method stub
		return 0;
	}

}
