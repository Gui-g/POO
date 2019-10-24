package dados;

public class Professor extends Pessoa {

	private String formacao;
	private float salario;
	private String departamento;
	
	public String getFormacao() {
		return formacao;
	}
	public void setFormacao(String formacao) {
		this.formacao = formacao;
	}
	public float getSalario() {
		return salario;
	}
	public void setSalario(float salario) {
		this.salario = salario;
	}
	public String getDepartamento() {
		return departamento;
	}
	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}
	
	public int mostrarTempoEspera() {
		return (int) (super.mostrarTempoEmprestimo()*1.7);
	}
	
	@Override
	public String toString() {
		return "[Nome = " + getNome() + ", CPF = " + getCpf() + ", Email = " + getEmail() + ", Formação = " + formacao + ", Salário = " + salario + ", Departamento = " + departamento + "]";
	}
	
	
	
}
