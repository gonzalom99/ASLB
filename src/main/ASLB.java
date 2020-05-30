package main;

import java.util.List;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

import org.apache.commons.configuration.Configuration;

import matrizindividual.MatrizAdyacencia;
import matrizindividual.MatrizIndividual;
import net.sf.jclec.IConfigure;
import net.sf.jclec.IEvaluator;
import net.sf.jclec.IFitness;
import net.sf.jclec.IIndividual;
import net.sf.jclec.base.AbstractEvaluator;
import net.sf.jclec.fitness.SimpleValueFitness;
import net.sf.jclec.fitness.ValueFitnessComparator;
import net.sf.jclec.selector.TournamentSelector;
import net.sf.jclec.intarray.IntArrayIndividual;

 
/**
 * ASLB optimization problem
 * 
 * @author Paula Robles Mateos
 * @author Maria Merino Pereda
 * @author Gonzalo Mart�nez Salmer�n
 * 
 */
public class ASLB extends AbstractEvaluator implements IConfigure{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2635335580011827514L;

	//n numero de estaciones
	static int n;

	//m numero de operaciones
	static int m;

	//duracion operaciones
	public static int duracion[] = new int [m];

	//matriz asociada a cada individuo A m*n
	int A[][] = new int [m][n];	


	//matriz de adyacencia
	static MatrizAdyacencia m_adyacencia = new MatrizAdyacencia(m);

	//Comparador de fitness
	private Comparator<IFitness> COMPARATOR;
	
	//Buffered reader
	private BufferedReader br;
	
	
	/*
	public static int [][] solucionAleatoria(){
		ArrayList<Integer> operaciones = new ArrayList<Integer>();
		operaciones.add(1);
		operaciones.add(2);
		operaciones.add(3);
		operaciones.add(4);
		operaciones.add(5);
		operaciones.add(6);
		operaciones.add(7);
		operaciones.add(8);
		operaciones.add(9);
		operaciones.add(10);

		int matriz[][] = new int[n][m];

		//se inicializa matriz en 0
		for(int i=0; i< n; i++){
			for(int j=0; j< m; j++){
				matriz[i][j] = 0;
			}        
		}


		for(int k=0; k<m; k++) {
			int posicion= (int) (Math.random() * 3);
			matriz[posicion][k]= duracion[k];

		}

		for (int x=0; x < matriz.length; x++) {
			System.out.print("|");
			for (int y=0; y < matriz[x].length; y++) {
				System.out.print (matriz[x][y]);
				if (y!=matriz[x].length-1) System.out.print("\t");
			}
			System.out.println("|");
		}

		return matriz;

	}
	
	public static ArrayList<int [][]> solucionesAleatorias(int numSoluciones){
		Random rand = new Random();
		ArrayList<int [][]> resultado = new ArrayList<int [][]>();
		ArrayList<Integer> usados = new ArrayList<Integer>();
		ArrayList<Integer> operaciones = new ArrayList<Integer>();

		operaciones.add(1);
		operaciones.add(2);
		operaciones.add(3);
		operaciones.add(4);
		operaciones.add(5);
		operaciones.add(6);
		operaciones.add(7);
		operaciones.add(8);
		operaciones.add(9);
		operaciones.add(10);




		for(int iter=0; iter < numSoluciones; iter++) {
			int matriz[][] = new int[n][m];
			//se inicializa matriz en 0
			for(int i=0; i< n; i++){
				for(int j=0; j< m; j++){
					matriz[i][j] = 0;
				}        
			}

			for(int k=0; k<m; k++) {
				int posicion= (int) (Math.random() * 3);
				matriz[posicion][k]= duracion[k];
			}
			resultado.add(matriz);
		}

		return resultado;

	}*/

	//imprimir una matriz pasada como parametro
	public static void impr(int [][] matriz) {
		for (int x=0; x < matriz.length; x++) {
			System.out.print("|");
			for (int y=0; y < matriz[x].length; y++) {
				System.out.print (matriz[x][y]);
				if (y!=matriz[x].length-1) System.out.print("\t");
			}
			System.out.println("|");
		}
	}
	
	public void evaluate(IIndividual ind) {
		
		int [] genotype2 = ((IntArrayIndividual)ind).getGenotype();
	
		int [][] genotype = new int[m][n];
		//System.out.println(m +" " +n);
		//System.out.println("La longitud del genotype es: "+genotype2.length);
		for(int k=0; k<3; k++) {
		//System.out.println("El ind que llega es: "+genotype2[k]);
		}
		
		//System.out.println("LONGITUD INDIVIDUAL 1 "+genotype2.length);
		//System.out.println("LONGITUD INDIVIDUAL "+((IntArrayIndividual)ind).getGenotype().length);
		for(int k=0; k<m; k++) {
			for(int j=0; j<n; j++) {
				genotype[k][j]=0;
			}
		}
		for(int i=0; i<genotype2.length;i++) {
			//System.out.println((int) genotype2[i]);
		genotype[genotype2[i]][i]=duracion[i];	
		}
		
		

	    //System.out.println("La matriz solucion es: ");
		//impr(genotype);
		
		
		//m estaciones n operaciones
		//tenemos que recorrer la matriz y sumar las operaciones de las filas
		//luego nos quedamos con la mas alta
		int orden=0;
		int fitness =0;
		int valorEstacion =0;
		for(int i=0; i< m; i++) {
			valorEstacion=0;
			for(int j=0; j<n; j++ ) {
				valorEstacion  += genotype[i][j];
				
			}
			if (valorEstacion > fitness) {
				fitness = valorEstacion;
				System.out.println("el fitness es: " +fitness);
			}
		}

		//Ahora comprobamos que se cumple el orden

		//primero recorremos la matriz adyacencia para ver que operaciones necesitan a otras
		for(int filas=0; filas<n; filas++) {
			for(int columnas=0; columnas <n; columnas++) {
				//Si es 1 es que el elemento columna necesita al fila 
				if(m_adyacencia.getElem(filas, columnas) == 1) {
					//comprobamos que pertenezca a la misma estacion o a una mayor
					if(getEstacion(genotype,filas) > getEstacion(genotype, columnas)) {
						orden ++;
					}
				}
			}

		}
		System.out.println("el orden es: " +orden);
		//Si es 0 es que cumple el orden y se establece el valor de la estacion mas alta
		if(orden==0) {
			ind.setFitness(new SimpleValueFitness(fitness));
		}
		//Si es negativo, no cumple el orden y ponemos un valor mucho mayor para que lo descarte el algoritmo
		else {
			fitness= fitness * (orden+1);
			ind.setFitness(new SimpleValueFitness(fitness));
		}
		System.out.println("El valor de fitness para ");
		for(int i=0; i<genotype2.length;i++) {
			System.out.println((int) genotype2[i]);
			
		}
		System.out.println("El valor final de fitnes es: "+ind.getFitness());
		

	}


	//Esta funcion te devuelve la estacion a la que pertenece una operacion en la solucion
	public static int getEstacion(int [][]solucion, int operacion) {
		int estacion =-1;
		for(int i=0; i<m; i++) {
			if(solucion[i][operacion]!= 0) {
				estacion = i;
			}
		}
		return estacion;
	}

	
	protected boolean maximize = false;
	public boolean isMaximize()
	{
		return maximize;
	}
	
	/** Set the maximize flag.
	* @param maximize Actual maximize flag. */
	public void setMaximize(boolean maximize)
	{
		this.maximize = maximize;
	}
	public Comparator<IFitness> getComparator()
	{
		// Set fitness comparator (if necessary)
		if (COMPARATOR == null)
			COMPARATOR = new ValueFitnessComparator(!maximize);
	
		// Return comparator
		return COMPARATOR;
	}
	

		public void configure(Configuration settings)
		{		
			
			String fileName = settings.getString("[@file-name]");
			//System.out.println(fileName);
			String line = null;
			
			n=settings.getInt("[@number-operations]");
			m=settings.getInt("[@number-estaciones]");
			//System.out.println(m + " "+ n);
			duracion = new int[n];
			m_adyacencia = new MatrizAdyacencia(n);
			File file = new File (fileName);
			//int duraciones[]= new int [num_operaciones];
	       // double y[]= new double [num_operaciones];
	        int k=0;
			int z=1;
		    try {
				FileReader fr = new FileReader(file);
				br = new BufferedReader(fr);
				
		        while(!(line=br.readLine()).contains("NODE_COORD_SECTION"));
		        while(!(line=br.readLine()).contains("EOF"))
		        {
		        	line=String.copyValueOf(line.toCharArray(), line.indexOf(" "), line.length()-line.indexOf(" "));
		        
		        	line=line.trim();
		        	//System.out.println("linea "+line);
		        	duracion[k]=Integer.valueOf(line.split(" ")[0]).intValue();
		        	int length =line.split(" ").length;		
		        	//System.out.println("longitud: "+length);
		        	while(z<length) {
		        		//comprobar luego filas y columnas
		        		//ahora es (numOp,precedente)�
		        	//	System.out.println("NUMERO "+ Integer.valueOf(line.split(" ")[z]));
		        	//	System.out.println("Agregamos: fila "+k+" columna :" +Integer.valueOf(line.split(" ")[z]));
		        	//	m_adyacencia.agregar(k, Integer.valueOf(line.split(" ")[z]));
		        		System.out.println("Agregamos: fila "+Integer.valueOf(line.split(" ")[z])+" columna :" + (k+1));
			        	m_adyacencia.agregar(Integer.valueOf(line.split(" ")[z])-1, k);
		        		z++;
		        	}
		        	z=1;
		        	
		        k++;	
		        }
		       
		        
		        
			} catch (IOException e) {
	            System.out.println(e);
			}
		    
		    for(int i=0;i<duracion.length;i++) {
		    	System.out.println("duracion: "+duracion[i]);
		    }
		    m_adyacencia.imprimir();
		    
		}
	
		
		
	/*
	public static void main(String[] args) {

		//generar matriz de adyacencia
		m_adyacencia.agregar(0, 1);
		m_adyacencia.agregar(0, 3);
		m_adyacencia.agregar(0, 2);
		m_adyacencia.agregar(1, 4);
		m_adyacencia.agregar(3, 4);
		m_adyacencia.agregar(3, 5);
		m_adyacencia.agregar(2, 6);
		m_adyacencia.agregar(2, 8);
		m_adyacencia.agregar(4, 7);
		m_adyacencia.agregar(5, 6);
		m_adyacencia.agregar(5, 7);
		m_adyacencia.agregar(7, 9);
		m_adyacencia.agregar(6, 9);
		m_adyacencia.agregar(8, 9);
		//m_adyacencia.imprimir();

		//			int [][]sol= solucionAleatoria();
		//
		//			System.out.println("El valor mas alto de estaciones es:"+evaluate(sol, m_adyacencia));

		//lista donde se guardan las soluciones
		ArrayList<int [][]> soluciones = new ArrayList<int [][]>();

		//crear 4 soluciones aleatorias
		soluciones=solucionesAleatorias(4);

		//imprimir soluciones evaluadas sin fitness
				for(int i=0;i<soluciones.size();i++) {
					int [][] solucion_actual=soluciones.get(i);
					System.out.println("----------------SOLUCION " +(i+1)+"------------");
					impr(solucion_actual);
					System.out.println();
					//System.out.println("El valor mas alto de estaciones es:"+evaluate(solucion_actual, m_adyacencia));
					System.out.println();
				}


		List<IIndividual> lista = new ArrayList<IIndividual>();
		for(int i=0; i<soluciones.size(); i++) {
			IIndividual aux = new MatrizIndividual(soluciones.get(i));
			lista.add(aux);
		}
		System.out.println("Tama�o lista de individuos: " + lista.size());
		
		//imprimir soluciones evaluadas con fitness
		for(int i=0;i<lista.size();i++) {
			evaluate(lista.get(i));
			System.out.println("INDIVIDUO "+ (i+1) + " :"+ lista.get(i));
		}

		
		//seleccion por torneo
		TournamentSelector Torneo = new TournamentSelector();
		Torneo.setTournamentSize(2);

		List<IIndividual> escogidos = new ArrayList<IIndividual>();
		
		escogidos = Torneo.select(lista,1,false);
		//System.out.println("Tama�o escogidos: " + escogidos.size());
		//	System.out.print(Torneo.select(lista));
	}
	*/

}