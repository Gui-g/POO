package dados;

public class Moto extends Veiculo {
	
	private int cilindrada;
	private boolean partidaEletrica;
	
	public int getCilindrada() {
		return cilindrada;
	}
	public void setCilindrada(int cilindrada) {
		this.cilindrada = cilindrada;
	}
	public boolean isPartidaEletrica() {
		return partidaEletrica;
	}
	public void setPartidaEletrica(boolean partidaEletrica) {
		this.partidaEletrica = partidaEletrica;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("[Cilindrada = ");
		builder.append(cilindrada);
		builder.append(", Partida Eletrica = ");
		builder.append(partidaEletrica ? "Sim" : "Não");
		builder.append("]");
		return builder.toString();
	}
	
	

}
