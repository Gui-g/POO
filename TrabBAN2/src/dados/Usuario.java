package dados;

import org.bson.types.ObjectId;

public class Usuario extends Pessoa {

	private int idUser;
	private ObjectId idUserMongo;
	private Universidade uni;
	private Categoria cat;
	
	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int id) {
		this.idUser = id;
	}
	public Universidade getUni() {
		return uni;
	}
	public void setUni(Universidade uni) {
		this.uni = uni;
	}
	public Categoria getCat() {
		return cat;
	}
	public void setCat(Categoria cat) {
		this.cat = cat;
	}
	@Override
	public String toString() {
		return "[id: " + idUser + "\nNome: " + getNome() + ", CPF: " + getCpf() + "\nData Nascimento: " + getDtNasc() + ", Sexo: " + getSexo() + "\nEndereço: " + getEndereco()
				+ "\nUniversidade: " + uni.getNome() + "\nCategoria: " + cat.getNome() + "]";
	}
	
	public String toStringList() {
		return "[id: " + idUser + ", nome: " + getNome() + ", CPF: " + getCpf() + "]";
	}
	public ObjectId getIdUserMongo() {
		return idUserMongo;
	}
	public void setIdUserMongo(ObjectId idUserMongo) {
		this.idUserMongo = idUserMongo;
	}
	
	
	
	
	
}
