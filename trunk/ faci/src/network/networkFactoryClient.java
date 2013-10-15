package network;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import configuration.Config;
import configuration.ConfigRNA;
import configuration.TestData;

public class networkFactoryClient {

	//este es un atributo que puede ser de cualquier clase concreta de red
	// por ejemplo: MLP, SOM...etc
	private ANNTopology network=null;

	private ANNTopology topology ;

	public networkFactoryClient(String topologySelected, ConfigRNA config) throws IOException
	{
		
		switch (topologySelected)
		{
		case "MLP":
			//System.out.print("es mlp \n");
			this.network = new MLPNetwork(config);
			break;
		case "RBF":
			this.network= new RBFNetwork(config);
			break;
		}	
		
		this.createNetwork();
		return;
	}


	private void createNetwork() throws IOException
	{
		
		this.network.createTopology();
	
	}


	public ANNTopology getNetwork() {
		return network;
	}
    
	/**
	 * ESTO LO TENGO Q HACER EN EL MAIN!!!!
	 * LUEGO INSTANCIAR ESTA CLASEEEEEE
	 
	Config config = Config.getInstance();
	//aca se va a crear la topologia de acuerdo a los parametros que tenga el config
	//se crearan las estructuras de datos necesarias para contener capas, neuronas, etc
	//esto lo hago en el 
	String topologySelected=config.getTopology();
	
	switch (topologySelected)
	{
	case "MLP":
		this.network= new MLPNetwork(config);
		break;
	case "RBF":
		this.network= new RBFNetwork(config);
		break;
	}*/

	
}
