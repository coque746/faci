package utils;

import java.util.ArrayList;

public class Various {

	public Various() {
		super();
	}

	public int binADec(ArrayList<Integer> binary) {
		
		int suma = 0;
		int init =0;
		int top=binary.size()-1;
		int index=0;
		for(int i=0;i<top+1;i++)
		{
			index = top-i;
			int val =(int) ((Math.pow(2, index))*binary.get(i)); 
			suma+=val;
		}

		return suma;
	}

	
	
}
