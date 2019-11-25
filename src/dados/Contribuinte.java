package dados;

import java.util.ArrayList;

public class Contribuinte extends Pessoa {

	private int contaBancaria;
	private ArrayList<Dependente> dependentes;
	private ArrayList<Bem> bens;
	private ArrayList<Documento> documentos;

	public Contribuinte() {
		dependentes = new ArrayList<Dependente>();
		bens = new ArrayList<Bem>();
		documentos = new ArrayList<Documento>();
	}

	public int getContaBancaria() {
		return contaBancaria;
	}
	public void setContaBancaria(int contaBancaria) {
		this.contaBancaria = contaBancaria;
	}
	public ArrayList<Dependente> getDependentes() {
		return dependentes;
	}
	public void setDependentes(ArrayList<Dependente> dependentes) {
		this.dependentes = dependentes;
	}
	public ArrayList<Bem> getBens() {
		return bens;
	}
	public void setBens(ArrayList<Bem> bens) {
		this.bens = bens;
	}
	public ArrayList<Documento> getDocumentos() {
		return documentos;
	}
	public void setDocumentos(ArrayList<Documento> documentos) {
		this.documentos = documentos;
	}
	
	
	
}
