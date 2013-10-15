package network;

import java.io.Serializable;
import java.util.ArrayList;

import objectiveFunctions.Function;

/**
 * clase Sigmoide, implementa la funcion sigmoide y su derivada
 * 
 */
public class Sigmoid implements Function, Serializable {
    @Override
    public double apply(double s) {
    	//System.out.println(s);
        return  (1.0 / (1 + Math.exp(-s)));
    }

    public double applyDf(double s) {
        return ((1 - apply(s)) * apply(s));
    }

	@Override
	public String print() {
		return "Sigmoid";
	}

	@Override
	public double apply(ArrayList<Double> s) {
		// metodo que esta solo para ser usado en caso de necesidad
		//en esta funcion no se va a usar xq en gral se usa como func de activ
		return 0;
	}

	@Override
	public double applyDf(ArrayList<Double> s) {
		// metodo que esta solo para ser usado en caso de necesidad
		//en esta funcion no se va a usar xq en gral se usa como func de activ
		return 0;
	}
	
	

}
