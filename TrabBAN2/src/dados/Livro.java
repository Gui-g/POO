package dados;

import java.util.ArrayList;

import org.bson.types.ObjectId;

public class Livro {

	private int idLiv;
	private ObjectId idLivMongo;
	private String titulo;
	private String autor;
	private String isbn;
	private ArrayList<Editora> editoras;
	
	public Livro() {
		editoras = new ArrayList<Editora>();
	}
	
	public int getIdLiv() {
		return idLiv;
	}
	public void setIdLiv(int idLiv) {
		this.idLiv = idLiv;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public ArrayList<Editora> getEditoras() {
		return editoras;
	}
	public void setEditoras(ArrayList<Editora> editoras) {
		this.editoras = editoras;
	}

	@Override
	public String toString() {
		return "[id: " + idLiv + ", Titulo: " + titulo + ", Autor: " + autor + ", ISBN: " + isbn + "\nEditoras: "
				+ editoras + "]";
	}
	
	public String toStringList() {
		return "[id: " + idLiv + ", Título: " + titulo + ", Autor: " + autor + ", ISBN:" + isbn + "]";
	}

	public ObjectId getIdLivMongo() {
		return idLivMongo;
	}

	public void setIdLivMongo(ObjectId idLivMongo) {
		this.idLivMongo = idLivMongo;
	}
	
	
}
