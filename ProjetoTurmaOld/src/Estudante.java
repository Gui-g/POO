
public class Estudante {

	private double[] notas = new double[4];
	private String nome;
	private double media;
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void setNotas(int pos, double nota) {
		this.notas[pos] = nota;
	}
	
	public void setMedia() {
		this.media = 0;
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
