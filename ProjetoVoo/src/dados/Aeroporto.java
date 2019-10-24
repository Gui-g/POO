package dados;
public class Aeroporto {
	
	private String nome;
	private String codigo;
	private Cidade cidade;
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public String getCodigo() {
		return this.codigo;
	}
	
	public Cidade getCidade() {
		return this.cidade;
	}
	
}
