package dados;

import java.util.Date;

import org.bson.types.ObjectId;

public class Pessoa {

	private int idPes;
	private ObjectId idPesMongo;
	private String cpf;
	private String nome;
	private Date dtNasc;
	private char sexo;
	private String endereco;
	
	public int getIdPes() {
		return idPes;
	}
	public void setIdPes(int id) {
		this.idPes = id;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Date getDtNasc() {
		return dtNasc;
	}
	public void setDtNasc(Date dtNasc) {
		this.dtNasc = dtNasc;
	}
	public char getSexo() {
		return sexo;
	}
	public void setSexo(char sexo) {
		this.sexo = sexo;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	public String toStringList() {
		return "[id: " + idPes + ", nome: " + getNome() + ", CPF: " + getCpf() + "]";
	}
	
	@Override
	public String toString() {
		return "[id: " + idPes + "\nNome: " + getNome() + ", CPF: " + getCpf() + "\nData Nascimento: " + getDtNasc() + ", Sexo: " + getSexo() + "\nEndereço: " + getEndereco()
				+ "]";
	}
	public ObjectId getIdPesMongo() {
		return idPesMongo;
	}
	public void setIdPesMongo(ObjectId idPesMongo) {
		this.idPesMongo = idPesMongo;
	}
}
