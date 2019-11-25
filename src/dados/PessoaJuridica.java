package dados;

import java.util.ArrayList;

public class PessoaJuridica {

	private int id;
	private String cnpj;
	private String nomePJ;
	private String endereco;
	private int numFuncionarios;
	private ArrayList<Documento> documentos;
	
	public PessoaJuridica() {
		documentos = new ArrayList<Documento>();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getNomePJ() {
		return nomePJ;
	}
	public void setNomePJ(String nomePJ) {
		this.nomePJ = nomePJ;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public int getNumFuncionarios() {
		return numFuncionarios;
	}
	public void setNumFuncionarios(int numFuncionarios) {
		this.numFuncionarios = numFuncionarios;
	}
	public ArrayList<Documento> getDocumentos() {
		return documentos;
	}
	public void setDocumentos(ArrayList<Documento> documentos) {
		this.documentos = documentos;
	}
	
	
}
