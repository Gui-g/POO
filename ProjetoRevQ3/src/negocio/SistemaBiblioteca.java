package negocio;

import dados.*;
import java.util.ArrayList;

public class SistemaBiblioteca {

	private ArrayList<Pessoa> pessoas;
	
	public SistemaBiblioteca() {
		pessoas = new ArrayList<Pessoa>();
	}

	public ArrayList<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(ArrayList<Pessoa> pessoas) {
		if(pessoas.size() <= 100)
			this.pessoas = pessoas;
		else
			this.pessoas = null;
	}
	
	public ArrayList<Pessoa> listaProfessores() {
		ArrayList<Pessoa> professores = new ArrayList<Pessoa>();
		
		if(pessoas.size() <= 100) {
			for(int i=0;i<pessoas.size();i++) {
				if(pessoas.get(i) instanceof Professor)
					professores.add(pessoas.get(i));
			}
		}
		
		return professores;
	}
	
	public ArrayList<Pessoa> listaAluno() {
		ArrayList<Pessoa> alunos = new ArrayList<Pessoa>();
		
		if(pessoas.size() <= 100) {
			for(int i=0;i<pessoas.size();i++) {
				if(pessoas.get(i) instanceof Aluno)
					alunos.add(pessoas.get(i));
			}
		}
		
		return alunos;
	}
	
	public ArrayList<Pessoa> listaProfessoresSalario(float salario){
		ArrayList<Pessoa> professores = new ArrayList<Pessoa>();
		
		if(pessoas.size() <= 100) {
			for(int i=0; i<pessoas.size();i++) {
				if(pessoas.get(i) instanceof Professor) {
					if(((Professor) pessoas.get(i)).getSalario() >= salario)
						professores.add(pessoas.get(i));
				}
			}
		}
		
		return professores;
	}
	
	public void cadastraProfessor(Professor professor) {
		if(pessoas.size() <= 100)
			pessoas.add(professor);
		else
			System.out.println("Lista cheia");
	}
	
	public void cadastraAluno(Aluno aluno) {
		if(pessoas.size() <= 100)
			pessoas.add(aluno);
		else
			System.out.println("Lista cheia");
	}
	
	public void cadastraPessoa(Pessoa pessoa) {
		if(pessoas.size() <= 100)
			pessoas.add(pessoa);
		else
			System.out.println("Lista cheia");
	}

	public int mostrarTempoEmprestimo(Pessoa pessoa) {
		if(pessoa instanceof Aluno)
			return ((Aluno) pessoa).mostrarTempoEmprestimo();
		else if(pessoa instanceof Professor) 
			return ((Professor) pessoa).mostrarTempoEspera();
		else
			return pessoa.mostrarTempoEmprestimo();
	}
	
}





















