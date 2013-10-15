package objectiveFunctions;

import java.util.ArrayList;

import chromosomes.Chromosome;

//n: tamaño del tablero

public class CollisionFitnessFunction extends GeneticFitnessFunction {
	
	private int tamanio=0;
	private int MAX =0;
	private int criteriaBestFitness =0;
	public CollisionFitnessFunction(int criteria)
	{
		this.criteriaBestFitness=criteria;
	}
	
	public int getTamanioTablero()
	{
		return this.tamanio;
	}
	
	public void setTamanioTablero(int n)
	{
		this.tamanio=n;
		this.setMax();
	}
	
	@Override
	public double apply(double s) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double applyDf(double s) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double apply(ArrayList<Double> s) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String print() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double applyDf(ArrayList<Double> s) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public boolean compareBestFitness(Double evaluationValue,
			Double parameterValue) {
		return(evaluationValue >= parameterValue);
		
	}

	@Override
	public void apply(Chromosome chrom) {

		int collisions=0;
		//esto es especifico para una solucion, por eso la especificidad
		ArrayList<Integer> geneStrip=(ArrayList<Integer>) chrom.getGeneStrip();
		
		collisions=hayColisionHor((ArrayList<Integer>) chrom.getGeneStrip());
		for(int i=0;i<chrom.getSize();i++)
		{
			for(int j=i+1;j<chrom.getSize();j++)
			{
				Integer Ya=(Integer) geneStrip.get(i);
				Integer Yb=(Integer) geneStrip.get(j);
				if(hayColisionDiag(i,Ya,j,Yb)) collisions++;
				
				
			}
		}
		int fit = MAX-collisions;
		System.out.println("Fitness calculado: "+fit);
		chrom.setFitnessValue(fit);
	}



	private int hayColisionHor(ArrayList<Integer> geneStrip) {
		int counter=0;
		ArrayList<Integer> genSCopy = (ArrayList<Integer>) geneStrip.clone();
		for(int i=0;i<geneStrip.size();i++)
		{
			int geneI = geneStrip.get(i);
			genSCopy.remove((Object)geneI);
			if(genSCopy.contains(geneI))
			{
				counter++;
			}
		}
		
		return counter;
		
	}

	/**
	 * Metodo que determina si existen colisiones.
	 * @param Xa
	 * @param Ya
	 * @param Xb
	 * @param Yb
	 * @return
	 */
	private boolean hayColisionDiag(int Xa, int Ya, int Xb, int Yb) {
		int distH=Math.abs(Xb-Xa);
		int distV=Math.abs(Yb-Ya);
		return ((distH==distV));
	}
	

	@Override
	public double applyForRNA(ArrayList<ArrayList<Double>> target,
			ArrayList<double[]> evaluationResults) {
		// TODO Auto-generated method stub
		return 0;
	}

	private void setMax()
	{
		int n = this.tamanio;
		MAX=(((n-1)*n)/2);
	}

	
}
