package dados;

import org.bson.types.ObjectId;

public class Assistente extends Funcionario {

	private int idAssist;
	private ObjectId idAssistMongo;
	private Bibliotecario supervisor;

	public int getIdAssist() {
		return idAssist;
	}
	public void setIdAssist(int id) {
		this.idAssist = id;
	}
	public Bibliotecario getSupervisor() {
		return supervisor;
	}
	public void setSupervisor(Bibliotecario supervisor) {
		this.supervisor = supervisor;
	}
	
	public String toStringList() {
		return "[id: " + idAssist + ", Nome: " + getNome() + ", CPF: " + getCpf() + ", Turno: " + getTurno() + "]";
	}
	@Override
	public String toString() {
		return "[id: " + idAssist + "\nNome: " + getNome() + ", CPF: " + getCpf() + "\nData Nascimento: " + getDtNasc() + ", Sexo: " + getSexo() + "\nEndereço: " + getEndereco() +
				"\nSalário: " + getSalario() + ", CTPS:" + getCtps() + "\nTurno:" + getTurno() + "\nSupervisor: " + "\nID: " + supervisor.getIdBib() + ", Nome: " + supervisor.getNome() + "]";
	}
	public ObjectId getIdAssistMongo() {
		return idAssistMongo;
	}
	public void setIdAssistMongo(ObjectId idAssistMongo) {
		this.idAssistMongo = idAssistMongo;
	}
	
}
