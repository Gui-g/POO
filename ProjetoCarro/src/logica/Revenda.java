package logica;

import java.util.ArrayList;
import dados.Veiculo;

public class Revenda {

	private ArrayList<Veiculo> veiculos;
	
	public Revenda() {
		this.veiculos = new ArrayList<Veiculo>();
	}

	public ArrayList<Veiculo> getVeiculos() {
		return veiculos;
	}

	public void setVeiculos(ArrayList<Veiculo> veiculos) {
		this.veiculos = veiculos;
	}
	
	public void cadastrarVeiculo(Veiculo veiculo) {
		veiculos.add(veiculo);
	}
	
	public ArrayList<Veiculo> getVeiculosNaoVendidos(){
		ArrayList<Veiculo> aux = new ArrayList<Veiculo>();
		
		for(int i=0; i<veiculos.size(); i++) {
			if(veiculos.get(i).isVendido() == false)
				aux.add(veiculos.get(i));
		}
		
		return aux;
	}
	
	public void venderVeiculo(Veiculo veiculo) {
		for(int i=0; i<veiculos.size();i++) {
			if(veiculos.get(i) == veiculo) {
				veiculos.get(i).setVendido(true);
			}
		}
	}
	
}
