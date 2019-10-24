package apresentacao;

import dados.*;
import negocio.SistemaBiblioteca;

import java.util.ArrayList;
import java.util.Scanner;

public class App {
	
	private static Scanner in = new Scanner(System.in);
	private static SistemaBiblioteca sistema = new SistemaBiblioteca();
	
	public static void main(String[] args) {
	
		boolean menu = true;
		int opt;
		
		while(menu) {
			System.out.println("Menu:\n1 - Cadastrar\n2 - Mostrar\n3 - Lista de Professores (Salário)\n4 - Tempo de Emprestimo\n0 - Sair");
			opt = in.nextInt();
			in.nextLine();
			switch(opt) {
				case 1:
					System.out.println("1 - Cadastrar Pessoa\n2 - Cadastrar Aluno\n3 - Cadastrar Professor");
					int opt2 = in.nextInt();
					in.nextLine();
					switch(opt2) {
						case 1:
							try {
								cadastraPessoa();
							}
							catch(Exception e) {
								System.out.println("CPF já cadastrado");
							}
							break;
						case 2:
							try {
								cadastraAluno();
							}
							catch(Exception e) {
								System.out.println("CPF já cadastrado");
							}
							break;
						case 3:
							try {
								cadastraProfessor();
							}
							catch(Exception e) {
								System.out.println("CPF já cadastrado");
							}
							break;
					}
					System.out.println();
					break;
				case 2:
					System.out.println("1 - Mostrar Alunos\n2 - Mostrar Professores");
					int opt3 = in.nextInt();
					in.nextLine();
					switch(opt3) {
						case 1:
							mostraAluno();
							break;
						case 2:
							mostraProfessor();
							break;
					}
					System.out.println();
					break;
				case 3:
					mostraProfessoresSalario();
					System.out.println();
					break;
				case 4:
					mostraTempoEmprestimo();
					System.out.println();
					break;
				case 0:
					menu = false;
					break;
				default:
					break;
			}
		}
		
	}
	
	public static void setPessoa(Pessoa pessoa) throws Exception {
		 System.out.println("Nome:");
		 pessoa.setNome(in.nextLine());
		 System.out.println("CPF:");
		 pessoa.setCpf(in.nextInt());
		 in.nextLine();
		 
		 for(int i=0; i<sistema.getPessoas().size(); i++){
				if(sistema.getPessoas().get(i).getCpf() == pessoa.getCpf()) {
					System.out.println();
					throw new Exception("CPF já cadastrado");
				}
			}
		 
		 System.out.println("Email:");
		 pessoa.setEmail(in.nextLine());
		 System.out.println("Tempo de Emprestimo:");
		 pessoa.setTempoEmprestimo(in.nextInt());
		 in.nextLine();
	}
	
	public static void cadastraPessoa() throws Exception{
		Pessoa aux = new Pessoa();
		
		setPessoa(aux);
		
		sistema.cadastraPessoa(aux);
	}
	
	public static void cadastraAluno() throws Exception{
		Aluno aux = new Aluno();
		
		setPessoa(aux);
		System.out.println("Curso:");
		aux.setCurso(in.nextLine());
		System.out.println("Fase:");
		aux.setFase(in.nextLine());
		System.out.println("Matricula:");
		aux.setMatricula(in.nextInt());
		in.nextLine();
		
		sistema.cadastraAluno(aux);
	}
	
	public static void cadastraProfessor() throws Exception{
		Professor aux = new Professor();
	
		setPessoa(aux);
		System.out.println("Formação:");
		aux.setFormacao(in.nextLine());
		System.out.println("Salário:");
		aux.setSalario(in.nextFloat());
		in.nextLine();
		System.out.println("Departamento:");
		aux.setDepartamento(in.nextLine());
		
		sistema.cadastraProfessor(aux);
	}
	
	public static void mostraProfessor() {
		for(Pessoa pessoa : sistema.getPessoas()) {
			if(pessoa instanceof Professor)
				System.out.println("- " + pessoa.toString());
		}
	}
	
	public static void mostraAluno() {
		for(Pessoa pessoa : sistema.getPessoas()) {
			if(pessoa instanceof Aluno)
				System.out.println("- " + pessoa.toString());
		}
	}
	
	public static void mostraProfessoresSalario() {
		System.out.println("Salário:");
		float aux = in.nextFloat();
		in.nextLine();
		
		ArrayList<Pessoa> professores = sistema.listaProfessoresSalario(aux);
		for(Pessoa pessoa : professores)
			System.out.println(pessoa.toString());
	}
	
	public static void mostraTempoEmprestimo() {
		System.out.println("Alunos:");
		for(Pessoa pessoa : sistema.getPessoas()) {
			if(pessoa instanceof Aluno) {
				System.out.println(pessoa.toString());
			}
		}
		
		System.out.println("Professores:");
		for(Pessoa pessoa : sistema.getPessoas()) {
			if(pessoa instanceof Professor) {
				System.out.println(pessoa.toString());
			}
		}
		
		System.out.println("Pessoas:");
		for(Pessoa pessoa : sistema.getPessoas()) {
			if(pessoa instanceof Professor) 
				break;
			else if(pessoa instanceof Aluno)
				break;
			else
				System.out.println(pessoa.toString());
		}
				
		Pessoa auxPessoa = new Pessoa();
		System.out.println();
		System.out.println("CPF que deseja ver o tempo:");
		auxPessoa.setCpf(in.nextInt());
		in.nextLine();
		
		for(Pessoa pessoa : sistema.getPessoas()) {
			if(auxPessoa.getCpf() == pessoa.getCpf())
				System.out.println("Tempo de Emprestimo = " + pessoa.mostrarTempoEmprestimo());
		}
		System.out.println();
	}

}




















