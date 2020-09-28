package dados;

import org.bson.types.ObjectId;
import org.postgresql.util.PGInterval;

public class Categoria {

	private int idCat;
	private ObjectId idCatMongo;
	private String nome;
	private PGInterval tempo;
	
	public int getIdCat() {
		return idCat;
	}
	public void setIdCat(int id) {
		this.idCat = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public PGInterval getTempo() {
		return tempo;
	}
	public void setTempo(PGInterval tempo) {
		this.tempo = tempo;
	}
	
	@Override
	public String toString() {
		return "[id: " + idCat + ", nome: " + nome + ", tempo: " + tempo + "]";
	}
	public ObjectId getIdCatMongo() {
		return idCatMongo;
	}
	public void setIdCatMongo(ObjectId idCatMongo) {
		this.idCatMongo = idCatMongo;
	}
	
	
}
