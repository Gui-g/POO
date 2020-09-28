package dados;

import org.bson.types.ObjectId;

public class Editora {

	private int idEdi;
	private ObjectId idEdiMongo;
	private String nome;
	private String cnpj;
	
	public int getIdEdi() {
		return idEdi;
	}
	public void setIdEdi(int idEdi) {
		this.idEdi = idEdi;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	@Override
	public String toString() {
		return "[id: " + idEdi + ", Nome: " + nome + ", CNPJ: " + cnpj + "]";
	}
	public ObjectId getIdEdiMongo() {
		return idEdiMongo;
	}
	public void setIdEdiMongo(ObjectId idEdiMongo) {
		this.idEdiMongo = idEdiMongo;
	}
	
	
}
