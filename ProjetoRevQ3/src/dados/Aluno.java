package dados;

public class Aluno extends Pessoa {

	private String curso;
	private String fase;
	private int matricula;
	
	public String getCurso() {
		return curso;
	}
	public void setCurso(String curso) {
		this.curso = curso;
	}
	public String getFase() {
		return fase;
	}
	public void setFase(String fase) {
		this.fase = fase;
	}
	public int getMatricula() {
		return matricula;
	}
	public void setMatricula(int matricula) {
		this.matricula = matricula;
	}
	
	public int mostrarTempoEmprestimo() {
		return (int) (super.mostrarTempoEmprestimo()*1.4);
	}
	@Override
	public String toString() {
		return "[Nome = " + getNome() + ", CPF = " + getCpf() + ", Email = " + getEmail() + ", Curso = " + curso + ", Fase = " + fase + ", Matricula = " + matricula + "]";
	}
	
	
	
}
