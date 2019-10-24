package dados;

public class Onibus extends Veiculo {
	
	private int numAssentos;
	private int potenciaCV;
	
	public int getNumAssentos() {
		return numAssentos;
	}
	public void setNumAssentos(int numAssentos) {
		this.numAssentos = numAssentos;
	}
	public int getPotenciaCV() {
		return potenciaCV;
	}
	public void setPotenciaCV(int potenciaCV) {
		this.potenciaCV = potenciaCV;
	}
	@Override
	public String toString() {
		return String.format("[Número de Assentos = %s, Potencia em CV = %s]", numAssentos, potenciaCV);
	}
	
	
	
}
