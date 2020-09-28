package dados;

import org.bson.types.ObjectId;

public class Funcionario extends Pessoa {

	private int idFunc;
	private ObjectId idFuncMongo;
	private double Salario;
	private String ctps;
	private char turno;
	
	public int getIdFunc() {
		return idFunc;
	}
	public void setIdFunc(int id) {
		this.idFunc = id;
	}
	public double getSalario() {
		return Salario;
	}
	public void setSalario(double salario) {
		Salario = salario;
	}
	public String getCtps() {
		return ctps;
	}
	public void setCtps(String ctps) {
		this.ctps = ctps;
	}
	public char getTurno() {
		return turno;
	}
	public void setTurno(char turno) {
		this.turno = turno;
	}
	
	public String toStringList() {
		return "[id: " + idFunc + ", Nome: " + getNome() + ", CPF: " + getCpf() + ", Turno: " + getTurno() + "]";
	}
	public ObjectId getIdFuncMongo() {
		return idFuncMongo;
	}
	public void setIdFuncMongo(ObjectId idFuncMongo) {
		this.idFuncMongo = idFuncMongo;
	}
	
}
