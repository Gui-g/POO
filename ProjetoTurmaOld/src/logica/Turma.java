package logica;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Scanner;

import dados.Estudante;


public class Turma {
	private double media;
	private ArrayList<Estudante> estudantes;
	
	public Turma(){
		estudantes = new ArrayList<Estudante>();
	}
	
	public ArrayList<Estudante> getEstudantes() {
		return estudantes;
	}

	public void setEstudantes(ArrayList<Estudante> estudantes) {
		this.estudantes = estudantes;
	}

	public void setEstudante(Estudante estudante) {		
		this.estudantes.add(estudante);
	}
	
	public void setMediaTurma() {
		double total = 0;
		for(Estudante estudante : estudantes)
			total += estudante.getMedia();
		this.media = total / estudantes.size();
	}
	
	public double getMediaTurma() {
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

	public void escreverTexto() throws IOException {
		sortTurma();
		BufferedWriter writer = new BufferedWriter(new FileWriter("Turma.txt"));
		
		for(Estudante estudante : estudantes) {
			writer.write("Aluno: " + estudante.getNome() + "\n");
			writer.write("Média: " + estudante.getMedia() + "\n");
		}
		writer.write("Média da Turma: " + this.media + "\n");
		writer.close();
		
	}
	
	public void escreverBin() throws IOException {
		sortTurma();
		OutputStream oS = new FileOutputStream("TurmaBin.xxx");
	    
		for(Estudante estudante : estudantes) {
			String nome = estudante.getNome();
			byte[] str = nome.getBytes();
			oS.write(str);
			double mediaAluno = estudante.getMedia();
			oS.write((byte) mediaAluno);
		}
	  
		oS.write((byte) this.media);
		
	    oS.close();
	}
	
	public void lerTurmaTexto() throws IOException {
		estudantes.clear();
		
		InputStream iS = new FileInputStream("Alunos.txt");
		InputStreamReader iSR = new InputStreamReader(iS);
		Scanner sC = new Scanner(iSR);
		String Turma = ".";
		
		while(Turma != null) {
			Estudante aux = new Estudante();
			
			aux.setNome(sC.nextLine());
			for(int i=0;i<4;i++) {
				String nota = sC.nextLine();
				Double notas = Double.parseDouble(nota);
				aux.setNotas(i, notas);
			}
			aux.setMedia();
			estudantes.add(aux);
			if(!sC.hasNextLine())
				break;
		}
		sC.close();
	}
	
	public void lerTurmaBinario() throws IOException {
		estudantes.clear();
		
		InputStream iS = new FileInputStream("Alunos.xxx");
		InputStreamReader iSR = new InputStreamReader(iS);
		Scanner sC = new Scanner(iSR);
		String Turma = ".";
		
		while(Turma != null) {
			Estudante aux = new Estudante();
			
			aux.setNome(sC.nextLine());
			for(int i=0;i<4;i++) {
				String nota = sC.nextLine();
				Double notas = Double.parseDouble(nota);
				aux.setNotas(i, notas);
			}
			aux.setMedia();
			estudantes.add(aux);
			if(!sC.hasNextLine())
				break;
		}
		sC.close();
		
		sC.close();
	}
	
}
