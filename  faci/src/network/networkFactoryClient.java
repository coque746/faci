package network;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import configuration.TestData;

public class networkFactoryClient {

	//este es un atributo que puede ser de cualquier clase concreta de red
	// por ejemplo: MLP, SOM...etc
	private ANNTopology network=null;

	private ANNTopology topology ;

	public networkFactoryClient(String topologySelected) throws IOException
	{
		
		switch (topologySelected)
		{
		case "MLP":
			//System.out.print("es mlp \n");
			this.network = new MLPNetwork();
			
			break;
		case "RBF":
			this.network= new RBFNetwork();
			break;
		}	
		
		
	}


	private void createNetwork() throws IOException
	{
		
		this.network.createTopology();
	
	}


	public ANNTopology getNetwork() {
		return network;
	}
    

}
