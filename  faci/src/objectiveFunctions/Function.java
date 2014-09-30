package objectiveFunctions;

import java.util.ArrayList;

public interface Function {

    double apply(double s);

    double applyDf(double s);
    
    double apply(ArrayList<Double> s);
    
	String print();

	double applyDf(ArrayList<Double> s);
	double apply();
}
