package negocio;

import java.util.ArrayList;

import dados.Contato;
import exceptions.*;
import persistencia.ManipuladorArquivoAgenda;

public class Agenda {

	private ArrayList<Contato> listaContatos;
	private ManipuladorArquivoAgenda manipulador;
	
	public Agenda(){
		listaContatos = new ArrayList<Contato>();
		manipulador = new ManipuladorArquivoAgenda("agenda");
	}
	
	public ManipuladorArquivoAgenda getManipulador() {
		return manipulador;
	}
	public void setManipulador(ManipuladorArquivoAgenda manipulador) {
		this.manipulador = manipulador;
	}
		public void setListaContatos(ArrayList<Contato> listaContatos) {
		this.listaContatos = listaContatos;
	}



	public void cadastrarContato(Contato novo) throws ContatoJaCadastradoException {
		
		for(Contato contato : listaContatos) {
			if(novo.getTelefone() == contato.getTelefone()) {
				throw new ContatoJaCadastradoException("Contato já cadastrado");
			}
		}
		
		listaContatos.add(novo);
	}
	
	public Contato buscarContato(int telefone) throws ContatoNaoEncontradoException, AgendaVaziaException {
		
		boolean existe = false;
		Contato auxContato = new Contato();
		
		if(listaContatos.isEmpty())
			throw new AgendaVaziaException("Agenda vazia");
		else {
			for(Contato contato : listaContatos) {
				if(telefone == contato.getTelefone()) {
					auxContato = contato;
					existe = true;
				}
			}
		}
		
		if(existe)
			return auxContato;
		else
			throw new ContatoNaoEncontradoException("Contato não encontrado");
	}
	
	public ArrayList<Contato> buscarContatos(String nome) throws ContatoNaoEncontradoException, AgendaVaziaException {
		
		boolean existe = false;
		ArrayList<Contato> nomes = new ArrayList<Contato>();
		
		if(listaContatos.isEmpty())
			throw new AgendaVaziaException("Agenda vazia");
		else {
			for(Contato contato : listaContatos) {
				if(contato.getNome().equals(nome)) {
					nomes.add(contato);
					existe = true;
				}
			}
		}
		
		if(existe)
			return nomes;
		else
			throw new ContatoNaoEncontradoException("Contato não encontrado");
	}
	
	public void removerContato(Contato contato) throws ContatoNaoEncontradoException, AgendaVaziaException {
		
		boolean existe = false;
		
		if(listaContatos.isEmpty())
			throw new AgendaVaziaException("Agenda vazia");
		else {
			for(Contato busca : listaContatos) {
				if(busca.equals(contato)) {
					existe = true;
				}
			}
		}
		
		if(existe)
			listaContatos.remove(contato);
		else
			throw new ContatoNaoEncontradoException("Contato não encontrado");
	}
	
	public ArrayList<Contato> getContatos() throws AgendaVaziaException {
		
		if(listaContatos.isEmpty())
			throw new AgendaVaziaException("Agenda vazia");
		else
			return listaContatos;
	}
	
}
