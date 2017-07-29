
public class clsCelda {
	
	private int valor;
	private boolean dado;
	
	
	public clsCelda(int valor){
		this.valor=valor;
		this.dado=false;
	}
				
	public clsCelda(int valor,boolean dado){
		this.valor=valor;
		this.dado=dado;
	}
	
	public int getValor() {
		return valor;
	}
	
	public void setValor(int valor) {
		this.valor = valor;
	}
	
	public void setValor(int valor,boolean bol){
		this.valor=valor;
		this.dado=bol;
	}
	
	public boolean esDado(){
		return this.dado;
	}
	
}
