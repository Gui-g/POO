
public class OU {
	
	private boolean resultado = false;
	private boolean[] bool;
	private int[] num;
	
	public boolean isResultado() {
		return resultado;
	}
	
	public OU(boolean[] bool) {
		this.bool = bool;
	}
	
	public OU(int[] num) {
		this.num = num;
	}
	
	public boolean bool() {
		for(boolean teste : bool) {
			if(teste == true)
				resultado = true;
		}
		
		return resultado;
	}
	
	public int num() {
		for(int teste : num) {
			if(teste == 1)
				resultado = true;
		}
		
		if(resultado == false)
			return 0;
		else
			return 1;
	}
	
	
}
