public class clsMatriz {
	
	private Object [][] matriz;
	private int tamFilas;
	private int tamColumnas;
	
	public clsMatriz(int m, int n){
		this.tamFilas=m;
		this.tamColumnas=n;
		matriz= new Object[m][n];
		this.limpiar();
	}
	
	public clsMatriz(int m){
		this.tamFilas=m;
		this.tamColumnas=m;
		matriz= new Object[m][m];
		this.limpiar();
	}

	public int getTamFilas() {
		return tamFilas;
	}

	public void setTamFilas(int tamFilas) {
		this.tamFilas = tamFilas;
	}

	public int getTamColumnas() {
		return tamColumnas;
	}

	public void setTamColumnas(int tamColumnas) {
		this.tamColumnas = tamColumnas;
	}

	public void limpiar(){
		for(int i=0;i<this.getTamFilas();i++){
			for(int j=0;j<this.getTamColumnas();j++){
				matriz[i][j]=null;
			}
		}	
	}
	
	public Object getElemento(int fila, int columna){
		if((fila<this.getTamFilas())&&(columna<this.getTamColumnas())){
			return this.matriz[fila][columna];
		}else{
			return null;
		}
	}
	
	public void meter(Object elemento, int fila, int columna){
		if((fila<this.getTamFilas())&&(columna<this.getTamColumnas())){
			this.matriz[fila][columna]=elemento;
		}
	}

}