package apresentacao;
import logica.Turma;

import java.io.IOException;
import java.util.Scanner;

import dados.Estudante;

public class App {

	public static Scanner in = new Scanner(System.in);
	public static Turma turma = new Turma();
	
	public static void main(String[] args){	
		
		int opt;
		boolean menu = true;
		
		while(menu) {
			System.out.println("1 - Cadastrar Turma\n2 - Ler de arquivo texto\n3 - Escrever arquivo texto\n4 - Ler de arquivo binário"
					+ "\n5 - Escrever arquivo binário\n0 - Sair");
			opt = in.nextInt();
			in.nextLine();
			switch(opt) {
				case 1:
					turma.getEstudantes().clear();
					int size;
					
					System.out.println("Número de alunos:");
					size = in.nextInt();
					in.nextLine();
					
					for(int i=0; i<size; i++)
						cadastraEstudante();
					turma.setMediaTurma();
					break;
				case 2:		
					try {
						turma.lerTurmaTexto();
						turma.setMediaTurma();
					} catch (IOException e1) {
						System.out.println("Falha em ler arquivo");
					}
					break;
				case 3:
					try {
						turma.escreverTexto();
					} catch (IOException e) {
						System.out.println("Falha em criar arquivo");
					}
					break;
				case 4:
					try {
						turma.lerTurmaBinario();
						turma.setMediaTurma();
					}
					catch (IOException e) {
						System.out.println("Falha em ler arquivo");
					}
					break;
				case 5:
					try {
						turma.escreverBin();
					} catch (IOException e) {
						System.out.println("Falha em criar arquivo");
					}
					break;
				case 0:
					menu = false;
					break;
				default:
					break;
			}
		}
		
		turma.printTurma();
		System.out.println();
		System.out.println("Media da turma: " + turma.getMediaTurma());
		
	}
	
	public static void cadastraEstudante() {
		Estudante aux = new Estudante();
		
		System.out.println("Nome");
		aux.setNome(in.nextLine());
		System.out.println("Notas");
		for(int i=0;i<4;i++) {
			System.out.printf("Nota %d: ", i+1);
			aux.setNotas(i, in.nextDouble());
			in.nextLine();
		}
		aux.setMedia();
		
		turma.setEstudante(aux);
	}
}
