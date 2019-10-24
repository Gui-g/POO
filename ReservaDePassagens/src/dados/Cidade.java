package dados;

public class Cidade {

	private String nome;
	private String estado;
	private Aeroporto[] aeroporto = new Aeroporto[10];
	private int quantAero = 0;
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public String getEstado() {
		return estado;
	}
	
	public void addAeroporto(Aeroporto aero) {
		if(quantAero < 10) {
			aeroporto[quantAero] = aero;
			quantAero++;
		}
	}
	
}
