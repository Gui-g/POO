package negocio;

import dados.Aeroporto;
import dados.Cidade;

public class ReservaPassagem {
	
//	private Cliente listaDeClientes[];
	private Cidade listaDeCidades[] = new Cidade[50];
	private int quantCidades = 0;
	private Aeroporto listaDeAeroportos[] = new Aeroporto[50];
	private int quantAero = 0;
	
	
	public void cadastrarCidade(Cidade cidade) {
		if (quantCidades < listaDeCidades.length) {
			listaDeCidades[quantCidades] = cidade;
			quantCidades++;
		}
	}
	
	public Cidade[] getCidades() {
		return listaDeCidades;
	}
	
	public int getQuantCidades() {
		return quantCidades;
	}

	public int getQuantAeroportos() {
		return quantAero;
	}
	public void cadastrarAeroporto(Aeroporto aeroporto) {
		if (quantAero < listaDeAeroportos.length) {
			listaDeAeroportos[quantAero] = aeroporto;
			quantAero++;
		}
	}
	
	public Aeroporto[] getAeroportos() {
		return listaDeAeroportos;
	}

	
	
	
}
