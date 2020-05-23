package main;

public class MatrizAdyacencia {
	private int n;
	private int[][] matriz;

	public MatrizAdyacencia(int n) {
		this.n = n;
		matriz = new int[this.n][this.n];
		//se inicializa matriz en 0
		for(int i=0; i< n; i++){
			for(int j=0; j< n; j++){
				matriz[i][j] = 0;
			}            
		}
	}

	public void agregar(int i, int j){
		matriz[i][j] += 1;
	}

	public void remover(int i, int j){
		if(matriz[i][j]>0)
			matriz[i][j] -= 1;
	}
	
	public int getElem(int i, int j) {
		return matriz[i][j];
	}

	public void imprimir(){
		for(int i=0; i< n; i++){
			for(int j=0; j< n; j++){
				System.out.print( matriz[i][j] + "  " );        
			}
			System.out.println();
		}  
	}
}
