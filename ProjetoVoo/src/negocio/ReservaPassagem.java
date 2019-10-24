package negocio;
import java.util.ArrayList;

import dados.Aeroporto;
import dados.Cidade;
import dados.Cliente;
import dados.Reserva;
import dados.Trecho;

public class ReservaPassagem {

	private ArrayList<Aeroporto> aeroportos;
	private ArrayList<Cidade> cidades;
	private ArrayList<Cliente> clientes;
	
	public ReservaPassagem() {
		this.aeroportos = new ArrayList<Aeroporto>();
		this.cidades = new ArrayList<Cidade>();
		this.clientes = new ArrayList<Cliente>();
	}

	public void cadastrarCidade(Cidade cidade) {
		cidades.add(cidade);
	}
	
	public void cadastrarCliente(Cliente cliente) {
		clientes.add(cliente);
	}
	
	public void cadastrarAeroporto(Aeroporto aeroporto) {
		aeroportos.add(aeroporto);
	}
	
	public void reservarIda(Cliente cliente, Reserva reserva) {
		cliente.reservarIda(reserva);
	}
	
	public void reservarVolta(Cliente cliente, Reserva ida, Reserva volta) {
		cliente.reservarVolta(ida, volta);
	}
	
	public void reservarTrecho(Cliente cliente, Reserva reserva, Trecho trecho) {
		cliente.reservarTrecho(reserva, trecho);
	}
	
	public void reservarPoltrona(Cliente cliente, Reserva reserva, Trecho trecho, int poltrona) {
		cliente.reservarPoltrona(reserva, trecho, poltrona);
	}
	
	public ArrayList<Cidade> listaCidades(){
		return this.cidades;
	}
	
	public ArrayList<Cliente> listaClientes(){
		return this.clientes;
	}
	
	public ArrayList<Reserva> mostrarReservas(int CPF) {
		for(Cliente cliente : clientes) {
			if(CPF == cliente.getCpf())
				return cliente.getReservas();
		}
		
		return new ArrayList<Reserva>();
	}
	
	public ArrayList<Reserva> mostrarReservas() {
		ArrayList<Reserva> reservas = new ArrayList<Reserva>();
		
		for(Cliente cliente : clientes)
			reservas.addAll(cliente.getReservas());
		
		return reservas;
	}
	
}
