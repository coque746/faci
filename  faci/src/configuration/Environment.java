package configuration;

import java.io.IOException;
import java.util.ArrayList;

import exceptions.ExceptionWrongParameterClass;

public abstract class Environment {

	 public abstract ArrayList<TestData> loadConfigurationDataTest(String trainingFile) throws IOException;
	 public abstract void loadConfigurationData();
	 protected abstract void initEnvironment(String configurationFile,String trainingFile) throws IOException;
	 protected abstract void initEnvironment(String configurationFile) throws IOException, ExceptionWrongParameterClass ;
}
