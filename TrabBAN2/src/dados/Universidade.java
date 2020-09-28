package dados;

import org.bson.types.ObjectId;

public class Universidade {

	private int idUni;
	private ObjectId idUniMongo;
	private String nome;
	private String sigla;
	
	public int getIdUni() {
		return idUni;
	}
	public void setIdUni(int id) {
		this.idUni = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSigla() {
		return sigla;
	}
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	
	@Override
	public String toString() {
		return "[nome: " + nome + ", sigla: " + sigla + "]";
	}
	public ObjectId getIdUniMongo() {
		return idUniMongo;
	}
	public void setIdUniMongo(ObjectId idUnimongo) {
		this.idUniMongo = idUnimongo;
	}
	
	
}
