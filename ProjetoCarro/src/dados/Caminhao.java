package dados;

public class Caminhao extends Veiculo {

	private float capacidadeMaxima;
	private String tipoCarroceria;
	private int potenciaCV;
	private int numEixos;
	
	public float getCapacidadeMaxima() {
		return capacidadeMaxima;
	}
	public void setCapacidadeMaxima(float capacidadeMaxima) {
		this.capacidadeMaxima = capacidadeMaxima;
	}
	public String getTipoCarroceria() {
		return tipoCarroceria;
	}
	public void setTipoCarroceria(String tipoCarroceria) {
		this.tipoCarroceria = tipoCarroceria;
	}
	public int getPotenciaCV() {
		return potenciaCV;
	}
	public void setPotenciaCV(int potenciaCV) {
		this.potenciaCV = potenciaCV;
	}
	public int getNumEixos() {
		return numEixos;
	}
	public void setNumEixos(int numEixos) {
		this.numEixos = numEixos;
	}
	@Override
	public String toString() {
		return "Capacidade Máxima = " + capacidadeMaxima + ", Tipo de Carroceria = " + tipoCarroceria + ", Potencia em CV = "
				+ potenciaCV + ", Número de Eixos = " + numEixos + "]";
	}
	
	
}
