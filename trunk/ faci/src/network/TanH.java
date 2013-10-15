package network;

import java.util.ArrayList;

import objectiveFunctions.Function;

/**
 * clase que realiza el calculo de la Tangente hiperbolica y su derivada
 */

 
public class TanH implements Function {
    @Override
    public double apply(double s) {
        return (float) Math.tanh(s);
    }

    public double applyDf(double s) {
        return (1 - apply(s)) * (apply(s) + 1);
    }

	@Override
	public String print() {
		return "TanH";
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

