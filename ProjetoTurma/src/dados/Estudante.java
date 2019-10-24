package dados;

public class Estudante {

	private double[] notas;
	private String nome;
	private double media;
	
	public Estudante() {
		notas = new double[4];
		media = 0;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void setNotas(int pos, double nota) {
		this.notas[pos] = nota;
	}
	
	public void setMedia() {
		for(int i=0; i<4; i++)
			this.media += notas[i];
		this.media /= 4;
	}
	
	public double getMedia() {
		return this.media;
	}
	
	public String getNome() {
		return nome;
	}
	
}
