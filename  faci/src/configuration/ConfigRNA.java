package configuration;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import mutationMethods.ExchangeMutator;
import mutationMethods.FlippingMutator;
import mutationMethods.Mutator;
import mutationMethods.RevertMutator;

import crossingMethods.DPCrossover;
import crossingMethods.Reproductor;
import crossingMethods.SPCrossover;
import crossingMethods.UPCrossover;

import objectiveFunctions.AdjustedFitnessFunction;
import objectiveFunctions.CollisionFitnessFunction;
import objectiveFunctions.CuadMedErrorFitnessFunction;
import objectiveFunctions.GeneticFitnessFunction;
import objectiveFunctions.NormalizedFitnessFunction;
import objectiveFunctions.StandarizedFitnessFunction;

import replacementMethods.BothParentsReplacement;
import replacementMethods.RandomReplacement;
import replacementMethods.Replacement;
import replacementMethods.WeakParentReplacement;
import selectionMethods.RankingSelector;
import selectionMethods.RouletteSelector;
import selectionMethods.Selector;
import selectionMethods.TournamentSelector;

import stoppingCriteria.AmountOfGenerationsCriteria;
import stoppingCriteria.FitnessValueCriteria;
import stoppingCriteria.GeneticStoppingCriterion;
import stoppingCriteria.NumberCyclesCriteria;


public class ConfigRNA extends Config {
   
    private static ConfigRNA instance;
    //atributos de instancia
    private String topology;    
    private String trainMethod ;
    private String validator;
    private int in;
    private int out;
    private int hidden;
    private int age;
    private double error;
    private int repeat;
    private double n;
    private double precisionBP;
    private int layerAmount;
    private String inputHiddenFunction;
    private String activationHiddenFunction;
    private String activationOutputFuncion;
    private String kFolds;
    private int precisionPesos;
	private double split_percent;
    
    
    private ConfigRNA() {
    	super();
    }

    private static void loadConfig(String configFile) throws IOException {
    	
        Properties property = new Properties();
        FileReader f = new FileReader(configFile);
        property.load(f);
        f.close();

        instance = new ConfigRNA();
        //empiezo a buscar los parametros
        instance.topology=property.getProperty("topology");
        instance.trainMethod=property.getProperty("trainMethod");
        instance.validator = property.getProperty("validator");
        instance.in = Integer.parseInt(property.getProperty("in"));
        instance.out = Integer.parseInt(property.getProperty("out"));
        instance.hidden = Integer.parseInt(property.getProperty("hidden"));
        instance.age = Integer.parseInt(property.getProperty("age"));
        instance.error = Double.parseDouble(property.getProperty("error"));
        instance.repeat = Integer.parseInt(property.getProperty("repeat"));
        instance.n = Double.parseDouble(property.getProperty("n"));
        instance.precisionBP = Double.parseDouble(property.getProperty("precisionBP"));
        instance.layerAmount=Integer.parseInt(property.getProperty("layerAmount"));
        instance.inputHiddenFunction=property.getProperty("inputHiddenFunction");
        instance.activationHiddenFunction=property.getProperty("activationHiddenFunction");
        instance.activationOutputFuncion = property.getProperty("activationOutPutFunction");
        instance.kFolds=property.getProperty("k");
        instance.precisionPesos = Integer.valueOf(property.getProperty("precisionPesos"));
        instance.split_percent = Double.parseDouble(property.getProperty("split_percent"));
    }

    public static ConfigRNA getInstance(String configFile) throws IOException {
        if (instance == null) {
            loadConfig(configFile);
        }
        return instance;
    }   
    
    public String getTopology() {
		return topology;
	}

	public String getMethod() {
		return trainMethod;
	}
	
	public String getValidator() {
		return this.validator;
	}

	public static ConfigRNA getInstance() {
		return instance;
	}

	public String getTrainMethod() {
		return trainMethod;
	}

	public int getSizeIn() {
		return in;
	}

	public int getSizeOut() {
		return out;
	}

	public int getSizeHidden() {
		return hidden;
	}

	public int getAge() {
		return age;
	}

	public double getError() {
		return error;
	}

	public int getRepeat() {
		return repeat;
	}

	public double getN() {
		return n;
	}

	public double getPrecisionBP() {
		return precisionBP;
	}

	public int getLayerAmount() {
		return layerAmount;
	}

	public String getInputHiddenFunction() {
		return inputHiddenFunction;
	}

	public String getActivationHiddenFunction() {
		return activationHiddenFunction;
	}

	public String getActivationOutputFunction() {
		return activationOutputFuncion;
	}

	public String getkFolds() {
		return kFolds;
	}

	public int getPrecisionPesos() {
		return precisionPesos;
	}

	public double getSplitPercent() {
	        return split_percent;
	}
 
    

}//end class
