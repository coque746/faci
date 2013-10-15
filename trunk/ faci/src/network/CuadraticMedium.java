package network;

import java.util.ArrayList;

import objectiveFunctions.Function;

/**
 * clase cuadraticmedium , implementa la funcion medios cuadradados
 * 
 */
public class CuadraticMedium implements Function {

	
    @Override
    public double apply(double s) {
        
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public double applyDf(double s) {
        return 0; 
    }

    public double apply(double t, double y )
    {
       return((t-y)*(t-y)*0.5);
    }

	@Override
	public String print() {
		return "Cuadratic Medium";
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
