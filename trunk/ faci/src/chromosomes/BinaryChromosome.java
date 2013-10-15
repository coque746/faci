package chromosomes;

import java.util.ArrayList;
import java.util.Random;

import crossingMethods.Reproductor;


import exceptions.ExceptionWrongParameterClass;

public class BinaryChromosome extends Chromosome {

	public BinaryChromosome(int chromosomeSize, int geneSize,Reproductor reproductorObject) throws ExceptionWrongParameterClass {
	
		super(chromosomeSize,geneSize, reproductorObject);
		/* 
		 * chequeo la clase del reproductor
		 * si no correspoonde con el cromosoma binario lanzo una excepcion
		 */
		Class<? extends Reproductor> reproductorClass = reproductorObject.getClass();
		String reproductorClassName = reproductorClass.getName();
		//inicializo el array de genes con el tipo de datos del gen, en este caso son integer entre 0 y 1
		this.geneStrip=new ArrayList<Integer>();
		//uso esta expresion regular porque el className me devuelve "training.class..."
		String[] className = reproductorClassName.split("\\.");
		switch(className[1])
		{
		case "SPCrossover":
		case "DPCrossover":
		case "UPCrossover":
		case "Copy":
			this.reproductor=reproductorObject;
			break;
		default:
			 throw new ExceptionWrongParameterClass("Clase de Reproductor no válida para el Cromosoma Binario : "+reproductorClassName);
		}
		
		


	}
	/**
	 * crea un cromosoma de genes aleatorios (tantos genes como 'chromosomeSize' indique)
	 */
	protected void initializeChromosome()
	{
		Random r = new Random();
		
		for(int i=0;i<chromosomeSize;i++)
		{	//crea un cromosoma binario con 0 y 1 al azar
			this.geneStrip.add(r.nextInt(2));			
		}

	}	
	
	

	@Override
	public int getSize() {

		return this.chromosomeSize;
		//return this.geneStrip.size();
		
	}

	@Override
	public void addGene(Object gene) {
		this.geneStrip.add(gene);
		
	}

	@Override
	public void flipGene(int i) {
		//if(Integer.parseInt((String) geneStrip.get(i))==1)
		if((Integer) geneStrip.get(i)==1)
		{
			geneStrip.set(i, 0);
		}
		else
		{
			geneStrip.set(i, 1);
		}
		
	}

	@Override
	public void setGeneValue(Object newValue, int position) {
		geneStrip.set(position, newValue);		
	}
	
	
	/* completa un string que es resultado de traducir un entero a binario
	 * con la cantidad de ceros a la izquierda como sean necesarios para completar
	 * la longitud de 4 caracteres o nros binarios, que son los necesarios para representar
	 * valores enteros entre 0 y 9
	 */
	protected String completeBinaryString(String binary)
	{
		for(int i=0;binary.length()<4;i++)
		{
			binary="0"+binary;
		}
		return binary;
	}
	@Override
	public double getGeneToWeight(int j) {
		// TODO Auto-generated method stub
		return 0;
	}
	

}
