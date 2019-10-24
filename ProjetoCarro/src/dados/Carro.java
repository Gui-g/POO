package dados;

public class Carro extends Veiculo {

	private int numAssentos;
	private int potenciaCV;
	private int numPortas;
	
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
	public int getNumPortas() {
		return numPortas;
	}
	public void setNumPortas(int numPortas) {
		this.numPortas = numPortas;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("[Número de Assentos=");
		builder.append(numAssentos);
		builder.append(", Potencia em CV=");
		builder.append(potenciaCV);
		builder.append(", Número de Portas=");
		builder.append(numPortas);
		builder.append("]");
		return builder.toString();
	}
	
	
	
}
