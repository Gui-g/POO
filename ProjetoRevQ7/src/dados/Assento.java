package dados;

public class Assento {
	
	private int numero;
	private boolean ocupado;
	
	public Assento() {
		ocupado = false;
	}
	
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public boolean isOcupado() {
		return ocupado;
	}
	public void setOcupado(boolean ocupado) {
		this.ocupado = ocupado;
	}
	
}
