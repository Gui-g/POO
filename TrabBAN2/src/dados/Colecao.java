package dados;

import org.bson.types.ObjectId;

public class Colecao {

	private int idCol;
	private ObjectId idColMongo;
	private String nome;
	
	public int getIdCol() {
		
		return idCol;
	}
	public void setIdCol(int idCol) {
		this.idCol = idCol;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Override
	public String toString() {
		return "[id: " + idCol + ", Nome: " + nome + "]";
	}
	public ObjectId getIdColMongo() {
		return idColMongo;
	}
	public void setIdColMongo(ObjectId idColMongo) {
		this.idColMongo = idColMongo;
	}

}
