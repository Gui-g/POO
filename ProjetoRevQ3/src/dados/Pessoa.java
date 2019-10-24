package dados;

public class Pessoa {

	private String nome;
	private int cpf;
	private String email;
	private int tempoEmprestimo;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getCpf() {
		return cpf;
	}
	public void setCpf(int cpf) {
		this.cpf = cpf;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setTempoEmprestimo(int tempoEmprestimo) {
		this.tempoEmprestimo = tempoEmprestimo;
	}
	
	public int mostrarTempoEmprestimo() {
		return tempoEmprestimo;
	}
	@Override
	public String toString() {
		return "[Nome = " + nome + ", CPF = " + cpf + ", Email = " + email + "]";
	}
	
	
	
}
