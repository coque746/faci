package network;

import java.util.ArrayList;

import objectiveFunctions.Function;

/**
 * clase Centroid, implementa la funcion centroide y su derivada
 * UTILIZADA como funcion de entrada en la neurona de REDES RBF
 * 
 */

public class Centroid implements Function {

	

	@Override
	public double applyDf(double s) {
		// TODO metodo applydf en clase centroid
		return 0;
	}

	@Override
	public String print() {
		return "Centroid";
	}

	@Override
	public double apply(double s) {
		// TODO Auto-generated method stub
		return 0;
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
