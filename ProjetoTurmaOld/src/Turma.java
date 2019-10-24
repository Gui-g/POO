import java.util.ArrayList;
import java.util.Scanner;


public class Turma {
	Scanner in = new Scanner(System.in);
	private double media;
	private ArrayList<Estudante> estudantes = new ArrayList<Estudante>();
	
	public void setTurma() {
		int tam;
		System.out.println("Numero de alunos:");
		tam = in.nextInt();
		
		for(int i=0;i<tam;i++)
			setEstudante();
	}
	
	public void setEstudante() {
		Estudante aux = new Estudante();
		System.out.println();
		System.out.println("Nome");
		in.nextLine();
		aux.setNome(in.nextLine());
		System.out.println("Notas");
		for(int i=0;i<4;i++)
			aux.setNotas(i, in.nextDouble());
		aux.setMedia();
		
		this.estudantes.add(aux);
	}
	
	public void setMedia() {
		double total = 0;
		for(Estudante estudante : estudantes)
			total += estudante.getMedia();
		this.media = total / estudantes.size();
	}
	
	public double getMedia() {
		return this.media;
	}
	
	public void sortTurma() {
		Estudante temp;
		for(int i=1; i<estudantes.size(); i++) {
			for(int j=i; j>0; j--) {
				if(estudantes.get(j).getMedia() > estudantes.get(j-1).getMedia()) {
					temp = estudantes.get(j);
					estudantes.set(j, estudantes.get(j-1));
					estudantes.set(j-1, temp);
				}
			}
		}
	}
	
	public void printTurma() {
		sortTurma();
		for(Estudante estudante : estudantes) {
			System.out.println();
			System.out.println("Aluno: " + estudante.getNome());
			System.out.println("Media: " + estudante.getMedia());
		}
	}
}
