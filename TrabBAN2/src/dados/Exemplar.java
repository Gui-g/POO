package dados;

import org.bson.types.ObjectId;

public class Exemplar extends Livro {

	private int idEx;
	private ObjectId idExMongo;
	private Status stat;
	private Colecao col;
	
	public int getIdEx() {
		return idEx;
	}
	public void setIdEx(int idEx) {
		this.idEx = idEx;
	}
	public Status getStat() {
		return stat;
	}
	public void setStat(Status stat) {
		this.stat = stat;
	}
	public Colecao getCol() {
		return col;
	}
	public void setCol(Colecao col) {
		this.col = col;
	}
	@Override
	public String toString() {
		return "[id: " + idEx + ", Status: " + stat + ", Coleção: " + col + "\n Titulo: " + getTitulo()
				+ ", Autor: " + getAutor() + ", ISBN: " + getIsbn() + "]";
	}
	
	public String toStringList() {
		return "[id: " + idEx + ", Status: " + stat + ", Coleção: " + col + "]";
	}
	public ObjectId getIdExMongo() {
		return idExMongo;
	}
	public void setIdExMongo(ObjectId idExMongo) {
		this.idExMongo = idExMongo;
	}
	
	
}
