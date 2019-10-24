package logica;

import dados.*;
import java.util.ArrayList;

public class SistemaAnimais {

	private ArrayList<Animal> animais;
	
	public SistemaAnimais() {
		animais = new ArrayList<Animal>();
	}
	
	public ArrayList<Animal> getAnimais() {
		return animais;
	}

	public void setAnimais(ArrayList<Animal> animais) {
		this.animais = animais;
	}

	public void cadastraMamifero(String nome, String continente, String comida, int estimativa, int dentes) {
		Mamifero auxAnimal = new Mamifero();
		
		auxAnimal.setNome(nome);
		auxAnimal.setContinenteEncontrado(continente);
		auxAnimal.setComidaFavorita(comida);
		auxAnimal.setEstimativaVida(estimativa);
		auxAnimal.setNumDentes(dentes);
		
		animais.add(auxAnimal);
	}
	
	public void cadastraPeixe(String nome, String continente, String comida, int estimativa, String escama) {
		Peixe auxAnimal = new Peixe();
		
		auxAnimal.setNome(nome);
		auxAnimal.setContinenteEncontrado(continente);
		auxAnimal.setComidaFavorita(comida);
		auxAnimal.setEstimativaVida(estimativa);
		auxAnimal.setTipoEscama(escama);
		
		animais.add(auxAnimal);
	}
	
	public void cadastraReptil(String nome, String continente, String comida, int estimativa, int pernas) {
		Reptil auxAnimal = new Reptil();
		
		auxAnimal.setNome(nome);
		auxAnimal.setContinenteEncontrado(continente);
		auxAnimal.setComidaFavorita(comida);
		auxAnimal.setEstimativaVida(estimativa);
		auxAnimal.setNumPerna(pernas);
		
		animais.add(auxAnimal);
	}
	
	public void removeAnimal(String nome) {
		for(Animal anim : animais) {
			if(anim.getNome() == nome)
				animais.remove(anim);
		}
	}
	
	public void inserirAnimal(Animal animal) {
		animais.add(animal);
	}
	
	public ArrayList<Animal> buscaMamifero(){
		ArrayList<Animal> auxAnimal = new ArrayList<Animal>();
		
		for(Animal animal : animais){
			if(animal instanceof Mamifero) {
				auxAnimal.add(animal);
			}
		}
		
		return auxAnimal;
	}
	
	public ArrayList<Animal> buscaPeixe(){
		ArrayList<Animal> auxAnimal = new ArrayList<Animal>();
		
		for(Animal animal : animais){
			if(animal instanceof Peixe) {
				auxAnimal.add(animal);
			}
		}
		
		return auxAnimal;
	}
	
	public ArrayList<Animal> buscaReptil(){
		ArrayList<Animal> auxAnimal = new ArrayList<Animal>();
		
		for(Animal animal : animais){
			if(animal instanceof Reptil) {
				auxAnimal.add(animal);
			}
		}
		
		return auxAnimal;
	}
	
	
	
}
