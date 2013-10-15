package configuration;
//TODO: VER URGENTE COMO ES QUE SE CREA EL TEST, AHORA DEBERIA SER UN CONJUNTO DE ENTRADA Y UNO DE SALIDA!!! 
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class TestData implements Cloneable {
    private ArrayList<ArrayList<Double>> x;
    private ArrayList<ArrayList<Double>> t;

    public TestData() {

        x = new ArrayList<ArrayList<Double>>();
        t = new ArrayList<ArrayList<Double>>();
    }

    public TestData(ArrayList<TestData> list, int index) {
        this();

      /*  for (TestData tmp : list) {
            this.x.add(tmp.x.get(1));
            this.t.add(tmp.t.get(1));
        }*/
         
        for (int i = 0; i < 1; i++) {
            this.x.addAll(list.get(index).x);
            this.t.addAll(list.get(index).t);
        }
    }

    public TestData(int index, ArrayList<TestData> tmp) {
        this();
        for (int i = 0; i < tmp.size(); i++) {
            if(i == index) continue;
            this.x.addAll(tmp.get(i).x);
            this.t.addAll(tmp.get(i).t);
        }
    }
    
    public ArrayList<ArrayList<Double>> getX() {
		return x;
	}

	public ArrayList<ArrayList<Double>> getT() {
		return t;
	}

	//TODO:VER SI NO CONVIENE REEMPLAZAR "load" POR ESTE
    public static ArrayList<TestData> loadSimple(String testFile, ArrayList<TestData> data) throws IOException {

        TestData dataLine = new TestData();

        FileReader f = new FileReader(testFile);
        BufferedReader reader = new BufferedReader(f);
        String line;
        boolean flag;
        while ((line = reader.readLine()) != null) {
            if (line.trim().equals("")) continue;
            dataLine = new TestData();
            dataLine.x.add(new ArrayList<Double>());
            flag=false;
            for (String token : line.split(";")) {
             	//System.out.print(token);
                if(!token.equals("#"))
                {
                	if(!flag)
                	{
                		//System.out.println(token);
                		dataLine.x.get(dataLine.x.size() - 1).add(Double.parseDouble(token.trim()));
                	}
                	else
                	{
                		//esto lo hace luego de que itera nuevamente despues del else de aca abajo
                		dataLine.t.get(dataLine.t.size() - 1).add(Double.parseDouble(token.trim()));
                		
                	}
                }
                else
                {
                	//empiezo a cargar el target
                	dataLine.t.add(new ArrayList<Double>());
                	flag= true;
                }
                	
     
            }
            data.add(dataLine);
         
        }

        reader.close();
        f.close();

        return data;
    }
    public static ArrayList<TestData> load(String testFile, ArrayList<TestData> data) throws IOException {
        TestData dataLine = new TestData();

        FileReader f = new FileReader(testFile);
        BufferedReader reader = new BufferedReader(f);
        String line;
        boolean flag;
        while ((line = reader.readLine()) != null) {
            if (line.trim().equals("")) continue;
            dataLine = new TestData();
            dataLine.x.add(new ArrayList<Double>());
            flag=false;
            for (String token : line.split(";")) {
             	//System.out.print(token);
                if(!token.equals("#"))
                {
                	if(!flag)
                	{
                		//System.out.println(token);
                		dataLine.x.get(dataLine.x.size() - 1).add(Double.parseDouble(token.trim()));
                	}
                	else
                	{
                		//esto lo hace luego de que itera nuevamente despues del else de aca abajo
                		dataLine.t.get(dataLine.t.size() - 1).add(Double.parseDouble(token.trim()));
                		
                	}
                }
                else
                {
                	//empiezo a cargar el target
                	dataLine.t.add(new ArrayList<Double>());
                	flag= true;
                }
                	
     
            }
            data.add(dataLine);
         
        }

        reader.close();
        f.close();

        return data;
    }


    public ArrayList<ArrayList<Double>> getInputData() {
        return x;
    }

    public ArrayList<ArrayList<Double>> getTargetData() {
        return t;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        TestData data = (TestData) super.clone();

        data.x = (ArrayList<ArrayList<Double>>) this.x.clone();
        data.t = (ArrayList<ArrayList<Double>>) this.t.clone();

        return data;
    }

    public void setX(ArrayList<ArrayList<Double>> x) {
		this.x = x;
	}

	public void setT(ArrayList<ArrayList<Double>> t) {
		this.t = t;
	}

	public void setTargetTo(ArrayList<Double> value) {
        for (int i = 0; i < t.size(); i++) {
            t.set(i, value);
        }
    }
}
