package network;

import java.util.ArrayList;

import objectiveFunctions.Function;

import configuration.Config;
import configuration.TestData;

public class RBFNetwork  extends networkTopology {

	public RBFNetwork(Config config) {
		
		/*this.selectedActivFunction = config.getFunction();
		this.sizeHidden = config.getSizeHidden();*/
		// TODO Auto-generated constructor rbfnetwork
	}

	@Override
	public void createTopology() {
		// TODO implementar create topology en clase rbfnetwork
		
	}

	

	@Override
	public void validateNetwork() {
		// TODO Auto-generated method rbfnetwork
		
	}

	@Override
	public void print() {
		// TODO metdo print en clase rbf network
		
	}

	@Override
	public double[] getNetworkOutput() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Function getActivationHiddenFunction() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double[] evaluate(ArrayList<Double> in) {
		return null;
		// TODO Auto-generated method stub
		
	}


	@Override
	public void updateWeights(double iterationError) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object clone() {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public ANNTopology cloneNetwork() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Double[] useTrainedNetwork(ArrayList<TestData> data) {
		// TODO Auto-generated method stub
		return null;
	}


	

	
	
}
