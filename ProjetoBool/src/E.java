
public class E {

	private boolean resultado = true;
	private boolean[] bool;
	private int[] num;
	
	public boolean isResultado() {
		return resultado;
	}
	
	public E(boolean[] bool) {
		this.bool = bool;
	}
	
	public E(int[] num) {
		this.num = num;
	}
	
	public boolean bool() {
		for(boolean teste : bool) {
			if(teste == false)
				resultado = false;
		}
		
		return resultado;
	}
	
	public int num() {
		for(int teste : num) {
			if(teste == 0)
				resultado = false;
		}
		
		if(resultado == false)
			return 0;
		else
			return 1;
	}
	
}
