package algorithms;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

import exceptions.ExceptionWrongParameterClass;

import network.ANNTopology;
import network.networkTopology;

/**
 * Created by IntelliJ IDEA.
 * User: yeyo
 * Email: yeyo@druidalabs.com
 * Date: Nov 3, 2010
 * Time: 6:19:30 PM
 */
public abstract class Algorithm {
    public static final Hashtable<Integer, Algorithm> algorithms = new Hashtable<Integer, Algorithm>();

    /* ANTES (02/10/2012)
    static {
        algorithms.put(Genetic.ID, new Genetic());
        algorithms.put(BackPropagation.ID, new BackPropagation());
        algorithms.put(BackPropagationMax.ID, new BackPropagationMax());
    }

    public static Algorithm createFactory(int id) {
        return algorithms.get(id);
    }
	*/
    protected networkTopology network;
    protected ArrayList<ArrayList<Double>> in;
    protected ArrayList<ArrayList<Double>> target;

    public Algorithm() {
    	//TODO: IMPORTANTE CONCEPTUALMENTE ===> EESTE ARRAY LIST DEBE SER UN OBJETO GENERICO 
    	//TODO: CREAR UNA CLASE "NETWORKINPUT" QUE SE PUEDA USAR PARA DEJAR GENERICOS LOS METODOS DE ENTRENAMIENTO Y VALIDACION
        this.in = new ArrayList<ArrayList<Double>>();
        this.target = new ArrayList<ArrayList<Double>>();
    }

    public abstract void initialize() throws ExceptionWrongParameterClass;


    /**
     * realiza una iteración del algoritmo de entrenamiento
     * @throws IOException 
     * @throws ExceptionWrongParameterClass 
     */
    public abstract void runAlgorithm() throws IOException, ExceptionWrongParameterClass;
    
    /**
	 * solo evalua los casos de entrada a traves de la red entrenada
	 * y calcula los errores de la entrada versus la salida
	 * 
	 */
    public abstract void evaluateValidationCases() throws IOException;


    /**
     * @return retorna los datos que utiliza el algoritmo.
     */
  //  public abstract ANNTopology getRepresentation();

    /**
     * retorna un identificador único del algoritmo
     *
     * @return
     */

    public final void setIn(ArrayList<ArrayList<Double>> in) {
        this.in = in;
    }

    public final void setTarget(ArrayList<ArrayList<Double>> target) {
        this.target = target;
    }
    /**
     * 
     * devuelve el error cuadratico medio utilizado para entrenamiento y 
     * validacion
     * 
     */
	public abstract double getCuadraticMediumError();

	public abstract void initializeForRNA(networkTopology network, double learnVelocity) throws ExceptionWrongParameterClass ;

	//protected abstract void calculateChildFitness(Chromosome chromosome);

    
}
