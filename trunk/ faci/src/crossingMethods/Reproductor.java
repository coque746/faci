package crossingMethods;

import java.util.ArrayList;

import chromosomes.Chromosome;

import exceptions.ExceptionWrongParameterClass;

public abstract class Reproductor {

	public abstract ArrayList<Chromosome> getCrossing(ArrayList<Chromosome> parents) throws ExceptionWrongParameterClass ;

}
