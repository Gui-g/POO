package negocio;

import dados.*;
import java.util.ArrayList;

public class SistemaVenda {

	private ArrayList<Cliente> clientes;
	private ArrayList<Produto> produtos;
	
	public SistemaVenda() {
		clientes = new ArrayList<Cliente>();
		produtos = new ArrayList<Produto>();
	}
	
	public ArrayList<Cliente> listarClientes() {
		return clientes;
	}
	public void setClientes(ArrayList<Cliente> clientes) {
		this.clientes = clientes;
	}
	public ArrayList<Produto> listarProdutos() {
		return produtos;
	}
	public void setProdutos(ArrayList<Produto> produtos) {
		this.produtos = produtos;
	}
	
	public void cadastrarProduto(Produto produto) {
		produtos.add(produto);
	}
	public void cadastrarCliente(Cliente cliente) {
		if(clientes.size() < 20) 
			clientes.add(cliente);
		else{
			clientes.remove(0);
			clientes.add(cliente);
		}
	}
	
	public void realizarCompra(Cliente cliente, Produto produto) {
		cliente.getProdutosComprados().add(produto);
	}
	
}
