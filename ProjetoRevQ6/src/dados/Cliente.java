package dados;

import java.util.ArrayList;

public class Cliente {

	private int cpf;
	private String nome;
	private ArrayList<Produto> produtosComprados;
	
	public Cliente() {
		produtosComprados = new ArrayList<Produto>();
	}
	
	public ArrayList<Produto> getProdutosComprados() {
		return produtosComprados;
	}
	public void setProdutosComprados(ArrayList<Produto> produtosComprados) {
		this.produtosComprados = produtosComprados;
	}
	public int getCpf() {
		return cpf;
	}
	public void setCpf(int cpf) {
		this.cpf = cpf;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return "[CPF = " + cpf + ", Nome = " + nome + "]";
	}
	
	
	
}
