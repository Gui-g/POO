package negocio;

import dados.*;
import java.util.ArrayList;

public class ReservaPassagem {

	private ArrayList<Viagem> listaViagens;
	private ArrayList<Passageiro> listaPassageiros;
	private ArrayList<Cidade> listaCidade;
	private ArrayList<Onibus> listaOnibus;
	
	public ReservaPassagem() {
		listaViagens = new ArrayList<Viagem>();
		listaPassageiros = new ArrayList<Passageiro>();
		listaCidade = new ArrayList<Cidade>();
		listaOnibus = new ArrayList<Onibus>();
	}
	
	public ArrayList<Viagem> getListaViagens() {
		return listaViagens;
	}
	public void setListaViagens(ArrayList<Viagem> listaViagens) {
		this.listaViagens = listaViagens;
	}
	public ArrayList<Passageiro> getListaPassageiros() {
		return listaPassageiros;
	}
	public void setListaPassageiros(ArrayList<Passageiro> listaPassageiros) {
		this.listaPassageiros = listaPassageiros;
	}
	public ArrayList<Cidade> getListaCidade() {
		return listaCidade;
	}
	public void setListaCidade(ArrayList<Cidade> listaCidade) {
		this.listaCidade = listaCidade;
	}
	public ArrayList<Onibus> getListaOnibus() {
		return listaOnibus;
	}
	public void setListaOnibus(ArrayList<Onibus> listaOnibus) {
		this.listaOnibus = listaOnibus;
	}

	public void cadastrarCidade(Cidade cidade) {
		listaCidade.add(cidade);
	}
	
	public void cadastrarOnibus(Onibus onibus) {
		listaOnibus.add(onibus);
	}
	
	public void cadastrarPassageiro(Passageiro passageiro) {
		listaPassageiros.add(passageiro);
	}
	
	public void cadastrarViagem(Viagem viagem) {
		listaViagens.add(viagem);
	}
	
	public boolean reservarIda(Passageiro passageiro, Viagem viagem, int Assento) {
		for(Viagem v : listaViagens) {
			if(v.getCodigo() == viagem.getCodigo()) {
				if(v.getOnibus().getNumeroAssentos() < Assento) {
					System.out.println("Assento não disponível");
					return false;
				}
				else {
					for(int i=0;i<v.getOnibus().getNumeroAssentos(); i++) {
						if(Assento == v.getOnibus().getAssentos().get(i).getNumero()) {
							if(v.getOnibus().getAssentos().get(i).isOcupado()) {
								System.out.println("Assento não disponível");
								return false;
								
							}
							else {
								passageiro.setViagem(viagem);
								passageiro.getViagem().getOnibus().getAssentos().get(i).setOcupado(true);
								return true;
							}
						}
					}
				}
			}
		}
		return false;
	}
	
	public boolean reservarVolta(Passageiro passageiro, Viagem viagem, int Assento) {
		for(Viagem v : listaViagens) {
			if(v.getCodigo() == viagem.getCodigo()) {
				if(v.getVolta().getOnibus().getNumeroAssentos() < Assento) {
					System.out.println("Assento não disponível");
					return false;
				}
				else {
					for(int i=0; i<v.getVolta().getOnibus().getNumeroAssentos(); i++) {
						if(Assento == v.getVolta().getOnibus().getAssentos().get(i).getNumero()) {
							if(v.getVolta().getOnibus().getAssentos().get(i).isOcupado()) {
								System.out.println("Assento não disponível");
								return false;
							}
							else {
								passageiro.getViagem().setVolta(viagem.getVolta());
								passageiro.getViagem().getVolta().getOnibus().getAssentos().get(i).setOcupado(true);
								return true;
							}
						}
					}
				}
			}
		}
		return false;
	}
	
	public ArrayList<Viagem> viagensDisponiveis(){
		ArrayList<Viagem> disponiveis = new ArrayList<Viagem>();
		
		for(Viagem v : listaViagens) {
			boolean disponivel = false;
			for(int i=0; i<v.getOnibus().getAssentos().size(); i++) {
				if(v.getOnibus().getAssentos().get(i).isOcupado() == false)
					disponivel = true;
			}
		
			if(disponivel)
				disponiveis.add(v);
		}
		
		return disponiveis;
	}
	
	public int quantAssentosDisponiveis(Viagem viagem) {
		int quant = 0;
		for(int i=0;i<viagem.getOnibus().getAssentos().size(); i++) {
			if(viagem.getOnibus().getAssentos().get(i).isOcupado() == false)
				quant++;
		}
		
		return quant;
	}
	
	public ArrayList<Integer> AssentosDisponiveis(Viagem viagem){
		ArrayList<Integer> assentos = new ArrayList<Integer>();
		for(int i=0;i<viagem.getOnibus().getAssentos().size(); i++) {
			if(viagem.getOnibus().getAssentos().get(i).isOcupado() == false)
				assentos.add(viagem.getOnibus().getAssentos().get(i).getNumero());
		}
		
		return assentos;
	}
}

















