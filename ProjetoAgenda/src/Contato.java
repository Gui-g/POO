
public class Contato {

	private String nome;
	private String email;
	private String endereco;
	private int Telefone;

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public int getTelefone() {
		return Telefone;
	}
	public void setTelefone(int telefone) {
		Telefone = telefone;
	}
	
	@Override
	public String toString() {
		return "Contato [nome=" + nome + ", email=" + email + ", endereco=" + endereco + "]";
	}
}
