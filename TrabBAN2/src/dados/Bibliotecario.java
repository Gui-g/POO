package dados;

import org.bson.types.ObjectId;

public class Bibliotecario extends Funcionario {

	private int idBib;
	private ObjectId idBibMongo;

	public int getIdBib() {
		return idBib;
	}
	public void setIdBib(int idBib) {
		this.idBib = idBib;
	}
	
	public String toStringList() {
		return "[id: " + idBib + ", Nome: " + getNome() + ", CPF: " + getCpf() + ", Turno: " + getTurno() + "]";
	}
	@Override
	public String toString() {
		return "[id: " + idBib + "\nNome: " + getNome() + ", CPF: " + getCpf() + "\nData Nascimento: " + getDtNasc() + ", Sexo: " + getSexo() + "\nEndereço: " + getEndereco() +
				"\nSalário: " + getSalario() + ", CTPS:" + getCtps() + "\nTurno:" + getTurno() + "]";
	}
	public ObjectId getIdBibMongo() {
		return idBibMongo;
	}
	public void setIdBibMongo(ObjectId idBibMongo) {
		this.idBibMongo = idBibMongo;
	}
}
