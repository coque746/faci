package algorithms;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import objectiveFunctions.Function;


import network.HiddenLayer;
import network.Layer;
import network.Link;
import network.Neuron;
import network.OutputLayer;
import network.Sigmoid;
import network.networkTopology;

public class BackPropagation extends Algorithm {
    public static final int ID = 2;
    public double[] errors;
    private double[] temporalOutput;
    double n;
   
    private double[] iterationErrors;
    //seteo este error en un valor "alto" 
    //para la 1º evaluacion del ciclo de entrenamiento
    //en trainer.training -->" 	for (i = 0; i < age && algorithm.getError() > errorTarget; i++) "
    //si no , no entra!!
    
    double iterationError =1;
    public BackPropagation(int outputSize, String trainingFile) {
        super();
        iterationErrors = new double[outputSize];
    }


    @Override
    public void initializeForRNA(networkTopology network, double learnVelocity) {
		n = learnVelocity;
		this.network=network;
		}

    @Override
    public void runAlgorithm() throws IOException {

    	//por cada input....
    	for(int i = 0; i<this.in.size();i++)
    	{
    		
    		this.temporalOutput=evaluate(in.get(i));
    		
    		calculateIterationErrors(i);
    		calculateDeltasandUpdate(this.iterationErrors);
    		
    	}
    	//this.network.print();
    	//System.exit(0);
    	
   
    
    }
    
    //VERSION REPENSADA
    private void calculateDeltasandUpdate(double[] iterationErrors) {
    	//TODO: VER...SI TENIENDO LOS SUMPREVLAYER...ES NECESARIO TENER LA MATRIZ DE PESOS ACTUALES???
    	//PUEDO ESTAR SACANDOLO DEL ENLACE DE LA NEURONA Y YA ESTAR ACTUALIZANDOLO..YA Q LA PROX ITERACION  NO 
    	//VA A USAR ESE VIEJO Wij
    	
    	/* ********* empiezo a trabajar con las capas de la red******************/
    	
    	//1-tomo la capa de salida
    	OutputLayer oLayer = this.network.getOutputLayer();
    	//2-obtener la matriz de pesos actual
    	//ArrayList<ArrayList<Double>> actualMatrixWeight = oLayer.getMatrixWeights();
    	//3-solo en este caso , los deltas son iguales al iterationErrors
    	double summPrevLayer = 0;
    	List<Neuron> nList = oLayer.getNeuronList();
    	//nList.size()-1 porque no tengo q operar sobre la neurona "bias"
    	int top = nList.size()-1;
    	for(int i = 0; i< top ;i++)
    	{
    		//tomo la neurona i
    		Neuron aNeuron = nList.get(i);
    		ArrayList<Link> connList = aNeuron.getInputs();
    		//recorro las conexiones de la neurona i
    	
    		for(int j=0; j<connList.size();j++)
    		{
    			//aca tomo la salida de la neurona de la capa anterior
    			double Yi=connList.get(j).getNeuron().getCalculatedOutputValue();
    			//peso de la conexion j de la neurona i
    			double Wij = connList.get(j).getWeight();
    			//actualizo la sumatoria...aprovechando la iteracion
    			summPrevLayer=summPrevLayer+(Wij*iterationErrors[i]);
    			//calculo el nuevo peso

    			double newWij= Wij+(n*iterationErrors[i]*Yi);
    			//calculo el valor promedio
    			//newWij /= connList.size();
    			connList.get(j).setWeight(newWij);
    			
    		}

    	}
    	//tomo las capas ocultas
    	List<HiddenLayer> hList = this.network.getHiddenLayers();
    	//las posiciones siempre es n-1
    	int topHiddens = hList.size()-1;
    	//02/02/2013
    	for(int i = topHiddens; i>=0;i--)//por cada capa oculta "i", hago esto
    	{
    		//capa de trabajo
    		Layer l = hList.get(i);
    		//busco la funcion de activacion en la capa
    		Function f = l.getActivationFunction();
    		//tomo cantidad de neuronas de la capa i
    		int topeNeuro = l.getLayerSize(); 
    		
    		Layer nextLayer = getNextLayer(i,topHiddens);    		   		
    		//por cada neurona "j" de la capa "i"
    		for(int j=0;j<topeNeuro;j++)
    		{
    			//neurona de trabajo
    			Neuron neuro = l.getNeuronList().get(j);
    			double sum = 0;
    			//por cada error
    			sum = calculateErrorPropagation(nextLayer,j);//paso la capa actual y el maximo de capas
    			//update de los pesos de la neurona
    			//coeficiente promedio
    			
    			
    			updateHiddenWeights(neuro,sum,f);
    			
    		}
    		
    	}
    	
    	
    	
    	/* version antes del 02/02/2013
    	for(int i = topeHiddens; i>=0;i--)//por cada capa oculta "i", hago esto
    	{
    		double summPrevLayer2 = 0;	
    		//tomo la capa oculta "i"
    		HiddenLayer actualL = hList.get(i);
    		//tomo la funcion de activacion q esta en la capa
    		Function actFunc = actualL.getActivationFunction();
    		//tomo la lista de neuronas de la capa
    		List<Neuron> nListH = actualL.getNeuronList();
    		//nList.size()-1 porque no tengo q operar sobre la neurona "bias"
        	int topH = nListH.size()-1;
    		//recorro todas las neuronas de la capa....
    		for(int j = 0; j<topH;j++)
    		{
    			//tomo la neurona j
        		Neuron aNeuronH = nListH.get(j);
        		ArrayList<Link> connListH = aNeuronH.getInputs();
        		double deltaH =0;
        		//recorro las conexiones de la neurona j
        		for(int k=0; k<connListH.size();k++)
        		{
        			double netj = aNeuronH.getCalculatedInput();
        			double Yi = aNeuronH.getCalculatedOutputValue();
        			deltaH = actFunc.applyDf(netj) * summPrevLayer;
        			//peso de la conexion k de la neurona j
        			double Wij = connListH.get(k).getWeight();
        			double newWij = Wij+(n*deltaH*Yi);
        			//voy calculando la nueva sumatoria
        			summPrevLayer2 = summPrevLayer2+(Wij*deltaH);		
        		}
        		
    		}
    		//actualizo este valor para que la sig capa en bajada la tenga actualizada
    		summPrevLayer=summPrevLayer2;
    	}*/
	}

    /*
    @SuppressWarnings("unused")
	private void calculateDeltasandUpdateVERSION_INICIAL(double[] iterationErrors) {
    	//TODO: VER...SI TENIENDO LOS SUMPREVLAYER...ES NECESARIO TENER LA MATRIZ DE PESOS ACTUALES???
    	//PUEDO ESTAR SACANDOLO DEL ENLACE DE LA NEURONA Y YA ESTAR ACTUALIZANDOLO..YA Q LA PROX ITERACION  NO 
    	//VA A USAR ESE VIEJO Wij
    	ArrayList<ArrayList<ArrayList<Double>>> listNewMatrixWeights = new ArrayList<ArrayList<ArrayList<Double>>>();
    	
    	//creo una matriz de nuevos pesos para esta capa
    	ArrayList<ArrayList<Double>> newMatrixWeight = new ArrayList<ArrayList<Double>>();
    	
    	/* ********* empiezo a trabajar con las capas de la red******************
    	
    	//1-tomo la capa de salida
    	OutputLayer oLayer = this.network.getOutputLayer();
    	//2-obtener la matriz de pesos actual
    	ArrayList<ArrayList<Double>> actualMatrixWeight = oLayer.getMatrixWeights();
    	//3-solo en este caso , los deltas son iguales al iterationErrors
    	double summPrevLayer = 0;
    	List<Neuron> nList = oLayer.getNeuronList();
    	for(int i = 0; i< nList.size();i++)
    	{
    		//tomo la neurona i
    		Neuron aNeuron = nList.get(i);
    		ArrayList<Link> connList = aNeuron.getInputs();
    		//recorro las conexiones de la neurona i
    		for(int j=0; j<connList.size();j++)
    		{
    			
    			double Yi=aNeuron.getCalculatedOutputValue();//la salida en esta neurona es = a la entrada (igual aca va la salida)
    			double Wij = actualMatrixWeight.get(i).get(j);
    			//actualizo la sumatoria...aprovechando la iteracion
    			summPrevLayer=summPrevLayer+(Wij*iterationErrors[i]);
    			//calculo el nuevo peso
    			double newWij= Wij+(n*iterationErrors[i]*Yi);
    			newMatrixWeight.get(i).add(newWij);
    			
    		}
    		
    	}
    	//en posicion 0 queda la matriz de nuevos pesos de la capa de salida
    	listNewMatrixWeights.add(newMatrixWeight);
    	
    	List<HiddenLayer> hList = this.network.getHiddenLayers();
    	
    	
    	
    	int tope = hList.size()-1;
    	for(int i = tope; i>=0;i--)//por cada capa oculta "i", hago esto
    	{
    		double summPrevLayer2 = 0;	
    		ArrayList<ArrayList<Double>> newMatrixWeightH = new ArrayList<ArrayList<Double>>();
    		//tomo la capa oculta "i"
    		HiddenLayer actualL = hList.get(i);
    		//tomo la funcion de activacion q esta en la capa
    		Function actFunc = actualL.getActivationFunction();
    		//tomo la lista de neuronas de la capa
    		List<Neuron> nListH = actualL.getNeuronList();
    		//tomo la matriz de pesos de la capa en cuestion
    		actualMatrixWeight = actualL.getMatrixWeights();
    		//recorro todas las neuronas de la capa....
    		for(int j = 0; j<nListH.size();j++)
    		{
    			//tomo la neurona j
        		Neuron aNeuronH = nListH.get(j);
        		ArrayList<Link> connListH = aNeuronH.getInputs();
        		double deltaH =0;
        		//recorro las conexiones de la neurona j
        		for(int k=0; k<connListH.size();k++)
        		{
        			double netj = aNeuronH.getCalculatedInput();
        			double Yi = aNeuronH.getCalculatedOutputValue();
        			deltaH = actFunc.applyDf(netj) * summPrevLayer;
        			double Wij = actualMatrixWeight.get(j).get(k);
        			double newWij = Wij+(n*deltaH*Yi);
        			//voy calculando la nueva sumatoria
        			summPrevLayer2 = summPrevLayer2+(Wij*deltaH);		
        			newMatrixWeightH.get(j).add(newWij);
        			
        		}
        		
    		}
    		//actualizo este valor para que la sig capa en bajada la tenga actualizada
    		summPrevLayer=summPrevLayer2;
    		//agrego la nueva matriz de pesos calculada
    		listNewMatrixWeights.add(newMatrixWeight);

    	}
    	
		
	}
*/

	/* hasta el 06/11
	private void calculateDeltasandUpdate(double[] iterationErrors) {
		this.network.calculateDeltasandUpdate(iterationErrors);
		
	}
	*/

    private Layer getNextLayer(int i, int topHiddens) {
   
		Layer layer;
		double sum=0;
		if(i==topHiddens)
		{
			//en este caso estamos trabajando la capa anterior a la capa de salida
			//es una situacion diferente por el hecho de que hay que tomar la capa "anterior"
			//desde otra parte de la estructura de la red: la 'OutputLayer'
			layer = this.network.getOutputLayer();
		}
		else
		{
			//porque quiero la capa siguiente
			i+=1;
			layer = this.network.getHiddenLayers().get(i);
		}
		return layer;
   }


	/**
     * 
     * @param neuro neurona a la que se le cambian los pesos (i) 
     * @param sum suma consistente en sumatoria(Ej*Wji) donde j es la neurona de capa siguiente de la cual se obtiene el peso de la conexion con la neurona i (j recorre todas las neuronas de la capa siguiente)
     * @param f funcion de activacion de las neuronas de la capa 
     */
    private void updateHiddenWeights(Neuron neuro, double sum, Function f) {
		
		ArrayList<Link> connections =neuro.getInputs();
	
		//obtengo la entrada neta
		double NETij = neuro.getCalculatedInput();
		//derivada de la funcion de activacion evaluada en la entrada neta
		double Dfnet = f.applyDf(NETij);
		
		//por cada conexion 
		for(int i=0;i<connections.size();i++)
		{
			Link conn =connections.get(i);
			double Wij=conn.getWeight();
			double Xi = conn.getNeuron().getCalculatedOutputValue();
			//nuevo peso
			double newWij = Wij - (this.n * sum * Dfnet * Xi);
			
			
			conn.setWeight(newWij);					
		}
	}


	/**
     * 
     * @param layer capa OCULTA de trabajo actual
     * @param j posicion de la neurona oculta de trabajo actual
     * @param topHiddens Cantidad de capas ocultas
     * @return suma de Ek*Wjk de capa anterior a la capa de trabajo actual
     */
	private double calculateErrorPropagation(Layer layer,int j) {
		
		//capa de trabajo
		
		double sum=0;
			
		List<Neuron> neuronList =layer.getNeuronList();
		int cantNeu = neuronList.size()-1;
		//por cada error
		for(int k=0;k<this.iterationErrors.length;k++)
		{
			//tengo q tomar de cada neurona de la capa siguiente el Wij donde i corresponde a la posicion de la neuro 
			//a la que le estoy cambiando los pesos
			//por cada neurona en la capa 'anterior'
			for(int l=0;l<cantNeu;l++)
			{
				//tomo el peso j (asociado a la neurona j en la capa anterior)
				double weight =neuronList.get(l).getWeights().get(j); 	
				sum= sum + this.iterationErrors[k]*weight;
			}
		}
		return sum;
		
	}


	/* este metodo devuelve el error total*/
	private void calculateIterationErrors(int indexIt) throws IOException {
		//System.out.print("calculate");
		double error = 0;
		for(int i = 0; i< this.target.get(indexIt).size();i++)
		{
			error = (target.get(indexIt).get(i)-temporalOutput[i]);
			//this.network.print();
			//System.out.println("target "+ target.get(indexIt).get(i)+ "salida temporal " +temporalOutput[i]);
			//System.out.println("********************************************************");
			this.iterationErrors[i]=error;
			//System.out.println("error de itercion "+i + " :"+this.iterationErrors[i] );
		}
	
		
	}



	private double[] evaluate(ArrayList<Double> in) throws IOException {
		double[] output = this.network.evaluate(in);
		return output;
		
	}


	@Override
	public double getCuadraticMediumError(){
		double errorIteration =0;
		for(int i=0;i<this.iterationErrors.length;i++)
		{
			//errorIteration = errorIteration+0.5*(Math.pow(iterationErrors[i], 2));
			errorIteration = errorIteration+(Math.pow(iterationErrors[i], 2));
		}
		
		//errorIteration/=this.iterationErrors.length;
		errorIteration/=2;
		return errorIteration;
	}
//	@Override
//	public double getError() {
//		double errorIteration =0;
//		for(int i=0;i<this.iterationErrors.length;i++)
//		{
//			errorIteration = errorIteration+0.5*(Math.pow(iterationErrors[i], 2));
//		}
//		
//		return errorIteration;
//	}


	
	
	@Override
	public void evaluateValidationCases() throws IOException {

		for(int i = 0; i<this.in.size();i++)
    	{
			this.temporalOutput=evaluate(in.get(i));
			calculateIterationErrors(i);
    	}
		
	}


	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		
	}



    
    
    
    
    
    
    
    
    /* ITERATE ANTERIOR
    public void iterate() {
        double out[][] = new double[in.size()][network.getSizeOutput()];
        double hidden[][] = new double[in.size()][network.getSizeHidden()];

        for (int caseIndex = 0; caseIndex < in.size(); caseIndex++) {
            out[caseIndex] = network.evaluate(in.get(caseIndex));
            hidden[caseIndex] = network.getOutHidden();
        }

        double[][] deltaW2 = getDeltaW2WithAvg(out, hidden);
     //   double[] bias = getBiasWithAvg(out);
        double[][] deltaW1 = getDeltaW1WithAvg(out);

        //update w2
        for (int hiddenIndex = 0; hiddenIndex < network.getSizeHidden(); hiddenIndex++) {
            for (int outIndex = 0; outIndex < network.getSizeOutput(); outIndex++) {
                double value = ((NetworkRepresentation) network).getW2(hiddenIndex, outIndex);
                ((NetworkRepresentation) network).setW2(hiddenIndex, outIndex, value + deltaW2[hiddenIndex][outIndex]);
            }
        }

     /*   for (int hiddenIndex = 0; hiddenIndex < representation.getSizeHidden(); hiddenIndex++) {
            double value = ((NetworkRepresentation) representation).getBias(hiddenIndex);
            ((NetworkRepresentation) representation).setBias(hiddenIndex, value + bias[hiddenIndex]);
        }   *//*
        //update w1
        for (int inIndex = 0; inIndex < network.getSizeIn(); inIndex++) {
            for (int hiddenIndex = 0; hiddenIndex < network.getSizeHidden(); hiddenIndex++) {
                double value = ((NetworkRepresentation) network).getW1(inIndex, hiddenIndex);
                ((NetworkRepresentation) network).setW1(inIndex, hiddenIndex, value + deltaW1[inIndex][hiddenIndex]);
            }
        }
    }*/
/*
    private double[] getBiasWithAvg(double[][] out) {
        double[] deltaBias = new double[network.getSizeHidden()];
        double[] dfSigmoide = new double[network.getSizeHidden()];
        Function function = network.getActivationFunction();

        for (int caseIndex = 0; caseIndex < in.size(); caseIndex++) {
            ArrayList<Integer> inputI = in.get(caseIndex);

            for (int hiddenIndex = 0; hiddenIndex < network.getSizeHidden(); hiddenIndex++) {
                dfSigmoide[hiddenIndex] = function.applyDf(((NetworkRepresentation) network).getInputHidden(hiddenIndex, inputI));
            }

            for (int outIndex = 0; outIndex < network.getSizeOutput(); outIndex++) {
                for (int hiddenIndex = 0; hiddenIndex < network.getSizeHidden(); hiddenIndex++) {
                    deltaBias[hiddenIndex] += n *
                            (target.get(caseIndex).get(outIndex) - out[caseIndex][outIndex]) *
                            ((NetworkRepresentation) network).getW2(hiddenIndex, outIndex) *
                            dfSigmoide[hiddenIndex];
                }
            }
        }

        double n = in.size() * network.getSizeOutput();

        for (int hiddenIndex = 0; hiddenIndex < network.getSizeHidden(); hiddenIndex++) {
            deltaBias[hiddenIndex] /= n;
        }

        return deltaBias;
    }


    private double[][] getDeltaW2WithAvg(double[][] out, double[][] hidden) {
        double[][] deltaW2 = new double[network.getSizeHidden()][network.getSizeOutput()];

        for (int caseIndex = 0; caseIndex < in.size(); caseIndex++) {
            for (int outIndex = 0; outIndex < network.getSizeOutput(); outIndex++) {
                for (int hiddenIndex = 0; hiddenIndex < network.getSizeHidden(); hiddenIndex++) {
                    deltaW2[hiddenIndex][outIndex] += n *
                            (target.get(caseIndex).get(outIndex) - out[caseIndex][outIndex]) *
                            hidden[caseIndex][hiddenIndex];
                }
            }
        }

        double n = in.size();

        for (int outIndex = 0; outIndex < network.getSizeOutput(); outIndex++) {
            for (int hiddenIndex = 0; hiddenIndex < network.getSizeHidden(); hiddenIndex++) {
                deltaW2[hiddenIndex][outIndex] /= n;
            }
        }

        return deltaW2;
    }

    private double[][] getDeltaW1WithAvg(double[][] out) {
        double[][] deltaW1 = new double[network.getSizeIn()][network.getSizeHidden()];
        double[] dfSigmoide = new double[network.getSizeHidden()];
        Function function = network.getActivationFunction();

        for (int caseIndex = 0; caseIndex < in.size(); caseIndex++) {
            ArrayList<Integer> inputI = in.get(caseIndex);

            for (int hiddenIndex = 0; hiddenIndex < network.getSizeHidden(); hiddenIndex++) {
                dfSigmoide[hiddenIndex] = function.applyDf(((NetworkRepresentation) network).getInputHidden(hiddenIndex, inputI));
            }          

            for (int outIndex = 0; outIndex < network.getSizeOutput(); outIndex++) {
                for (int hiddenIndex = 0; hiddenIndex < network.getSizeHidden(); hiddenIndex++) {
                    for (int inIndex = 0; inIndex < network.getSizeIn(); inIndex++) {
                        deltaW1[inIndex][hiddenIndex] += n *
                                (target.get(caseIndex).get(outIndex) - out[caseIndex][outIndex]) *
                                ((NetworkRepresentation) network).getW2(hiddenIndex, outIndex) *
                                dfSigmoide[hiddenIndex] * inputI.get(inIndex);
                    }
                }
            }
        }

        double n = in.size() * network.getSizeOutput();

        for (int inIndex = 0; inIndex < network.getSizeIn(); inIndex++) {
            for (int hiddenIndex = 0; hiddenIndex < network.getSizeHidden(); hiddenIndex++) {
                deltaW1[inIndex][hiddenIndex] /= n;
            }
        }

        return deltaW1;
    }

    @Override
    public Representation getRepresentation() {
        return network;
    }

    @Override
    public String toString() {
        return "BackPropagation";
    }


	@Override
	public double getError() {
		// TODO Auto-generated method stub
		return 0;
	}
*/



}
