package dados;

import org.bson.types.ObjectId;

public class Status {

	private int idSt;
	private ObjectId idStMongo;
	private String nome;
	
	public int getIdSt() {
		return idSt;
	}
	public void setIdSt(int idSt) {
		this.idSt = idSt;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Override
	public String toString() {
		return "[id: " + idSt + ", Nome: " + nome + "]";
	}
	public ObjectId getIdStMongo() {
		return idStMongo;
	}
	public void setIdStMongo(ObjectId idStMongo) {
		this.idStMongo = idStMongo;
	}

	
}
