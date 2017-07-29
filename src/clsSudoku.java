import java.math.*;
public class clsSudoku {
	private clsMatriz tablero;
	private boolean tieneSolucion;
	
	public clsSudoku(){
		tablero=new clsMatriz(9);
		tieneSolucion=false;
		
		for(int i=0; i<9; i++){
    		for(int j=0;j<9;j++){
    			clsCelda celda=new clsCelda(0,false);
    			tablero.meter(celda, i, j);
    		}
    	}
		
		
	}
	
	public clsCelda getC(int fila, int columna){
		clsCelda aux=(clsCelda) this.tablero.getElemento(fila, columna);
		return aux;
	}
	
	public void tieneSol(){
		this.tieneSolucion=true;
	}
	
	public boolean getTieneSol(){
		return this.tieneSolucion;
	}
	
	public boolean esFactible(int fila, int columna){
		int num=this.getC(fila,columna).getValor();
		
		int Cfila = (fila / 3) * 3;
		int Ccolumna = (columna / 3) * 3;
		
		for(int f = 0; f<3; f++){
			for(int c = 0; c<3; c++){
				if((this.getC(Cfila + f, Ccolumna + c).getValor()==num)&&!(Cfila+f==fila && Ccolumna+c==columna)){
					return false;
				}
			}
		}
		
		for(int i=0;i<=8;i++){
			if(columna!=i){
				clsCelda aux=this.getC(fila,i);
				if(num==aux.getValor())
					return false;
			}
			if(fila!=i){
				clsCelda aux=this.getC(i,columna);
				if(num==aux.getValor())
					return false;
			}
		}
		return true;
	}
	
	
	public void limpiarTablero(int fila, int columna){
		clsCelda aux=this.getC(fila, columna);
		if (!aux.esDado()){
			aux.setValor(0, false);
		}
		if(fila==8 && columna==8)
			return;
		if(fila<8 && columna==8){
			this.limpiarTablero(fila+1, 0);
		}
		if(fila<=8 && columna<8){
			this.limpiarTablero(fila, columna+1);
		}
	}
	
	public void checkSudoku(int fila, int columna){
		
		if(!this.getC(fila, columna).esDado()){
			for(int k=1;k<=9;k++){
				this.limpiarTablero(fila, columna);
				this.getC(fila, columna).setValor(k);
				//JOptionPane.showMessageDialog(null, "Llegue hasta aqui"+Integer.toString(fila)+Integer.toString(columna));
				if(this.esFactible(fila, columna)){
					if((fila==8)&&(columna==8)){
						this.tieneSol();//llega al final del sudoku y cambia el valor del atributo tieneSolucion a true
						return;
					}else{
						if(fila<8 && columna==8){
							this.checkSudoku(fila+1, 0);
							if(this.getTieneSol()){
								return;
							}
						}else{
							if(fila<=8 && columna<8){
								this.checkSudoku(fila, columna+1);
								if(this.getTieneSol()){
									return;
								}
							}
						}
				    }
				}
			}
			return;
		}else{
			if((fila==8)&&(columna==8)){
				this.tieneSol();
				return;
			}else{
				if(fila<8 && columna==8){
					this.checkSudoku(fila+1, 0);
					if(this.getTieneSol()){
						return;
					}
				}else{
					if(fila<=8 && columna<8){
						this.checkSudoku(fila, columna+1);
						if(this.getTieneSol()){
							return;
						}
					}
				}
			}
		}
	}
	
	public boolean checkTablero(int fila, int columna){
		boolean band=false;		
		if(this.esFactible(fila, columna)){
			if(fila==8 && columna==8){
				return true;
			}
			if(fila<8 && columna==8){
				band= this.checkTablero(fila+1, 0);
			}
			if(fila<=8 && columna<8){
				band= this.checkTablero(fila, columna+1);
			}
		}else{
			band= false;			
		}
		return band;
	}
	
	public boolean hayVacios(int fila, int columna){
		boolean band=true;		
		if(this.getC(fila, columna).getValor()!=0){
			if(fila==8 && columna==8){
				return false;
			}
			if(fila<8 && columna==8){
				band= this.checkTablero(fila+1, 0);
			}
			if(fila<=8 && columna<8){
				band= this.checkTablero(fila, columna+1);
			}
		}else{
			band= true;			
		}
		return band;
	}
	
	public boolean hayVacios(){
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if(this.getC(i, j).getValor()==0){
					return true;
				}
			}
		}
		return false;
	}
	
	public int aleatorioEntre(int M, int N){
		 int numero = (int)Math.floor(Math.random()*(N-M+1)+M);
		 return numero;
	}
	
	public void generarTablero(int dificultad){
		do{
			this.limpiarTablero(0, 0);
			int semilla= this.aleatorioEntre(1, 17);  // Valor entre M y N, ambos incluidos.
			for(int i=0;i<semilla;i++){
				int x= this.aleatorioEntre(0, 8);
				int y= this.aleatorioEntre(0, 8);
				while(this.getC(x, y).getValor()==0){
					int num= this.aleatorioEntre(1, 9);;
					this.getC(x, y).setValor(num,true);
					if(!this.esFactible(x, y)){
						this.getC(x, y).setValor(0);
					}
				}	
			}
			this.checkSudoku(0, 0);	
	}while(!this.getTieneSol());
			this.setDificultad(dificultad);
	}
	
	public void setDificultad(int valor){
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				int num=this.getC(i, j).getValor();
				this.getC(i, j).setValor(num, true);
			}
		}
		switch(valor){
			case 0:{
				for(int i=1;i<=10;i++){
					int x=this.aleatorioEntre(0, 8);
					int y=this.aleatorioEntre(0, 8);
					while(this.getC(x, y).getValor()==0){
						x=this.aleatorioEntre(0, 8);
						y=this.aleatorioEntre(0, 8);
					}
					this.getC(x, y).setValor(0, false);
				}
			}break;
			case 1:{
				for(int i=1;i<=40;i++){
					int x=this.aleatorioEntre(0, 8);
					int y=this.aleatorioEntre(0, 8);
					while(this.getC(x, y).getValor()==0){
						x=this.aleatorioEntre(0, 8);
						y=this.aleatorioEntre(0, 8);
					}
					this.getC(x, y).setValor(0, false);
				}
			}break;
			case 2:{
				for(int i=1;i<=55;i++){
					int x=this.aleatorioEntre(0, 8);
					int y=this.aleatorioEntre(0, 8);
					while(this.getC(x, y).getValor()==0){
						x=this.aleatorioEntre(0, 8);
						y=this.aleatorioEntre(0, 8);
					}
					this.getC(x, y).setValor(0, false);
				}
			}break;
			default:{
				for(int i=1;i<=65;i++){
					int x=this.aleatorioEntre(0, 8);
					int y=this.aleatorioEntre(0, 8);
					while(this.getC(x, y).getValor()==0){
						x=this.aleatorioEntre(0, 8);
						y=this.aleatorioEntre(0, 8);
					}
					this.getC(x, y).setValor(0, false);
				}
			}break;
			
		}	
	}
	
	
}
