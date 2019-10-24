package dados;

public class Caminhonete extends Veiculo {

	private float capacidadeMaxima;
	private String tipoCarroceria;
	private int potenciaCV;
	private boolean cabineDupla;
	
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
	public boolean isCabineDupla() {
		return cabineDupla;
	}
	public void setCabineDupla(boolean cabineDupla) {
		this.cabineDupla = cabineDupla;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("[Capacidade Máxima = ");
		builder.append(capacidadeMaxima);
		builder.append(", Iipo de Carroceria = ");
		builder.append(tipoCarroceria);
		builder.append(", Potencia em CV =");
		builder.append(potenciaCV);
		builder.append(", Cabine Dupla =");
		builder.append(cabineDupla ? "Sim" : "Não");
		builder.append("]");
		return builder.toString();
	}
	
	
	
}
