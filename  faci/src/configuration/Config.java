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


public class Config {
   
    private static Config instance;

    
	public static Config getInstance(String configFile) throws IOException {
        if (instance == null) {
            loadConfig(configFile);
        }
        return instance;
    }

    
    @SuppressWarnings("unused")
	private static void loadConfig(String configFile) throws IOException {
    	
        Properties property = new Properties();
        FileReader f = new FileReader(configFile);
        property.load(f);
        f.close();

        instance = new Config();
    }
}