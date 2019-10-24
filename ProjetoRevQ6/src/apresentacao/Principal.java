package apresentacao;

import dados.*;
import negocio.SistemaVenda;
import java.util.Scanner;

public class Principal {
	
	private static SistemaVenda sistema = new SistemaVenda();
	private static Scanner in = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		boolean menu = true;
		int opt;
		while(menu) {
			System.out.println("Menu:\n1 - Cadastrar Cliente\n2 - Cadastrar produto\n3 - Listar Cliente\n4 - Listar Produtos"
					+ "\n5 - Realizar Venda\n0 - Sair");
			opt = in.nextInt();
			in.nextLine();
			
			switch(opt) {
				case 1:
					try {
						cadastrarCliente();
					}
					catch (Exception e) {
						System.out.println("CPF já cadastrado");
					}
					break;
				case 2:
					System.out.println("1 - Cadastrar Comida\n2 - Cadastrar Bebida");
					int opt2 = in.nextInt();
					in.nextLine();
					switch(opt2) {
						case 1:
							cadastrarComida();
							break;
						case 2:
							cadastrarBebida();
							break;
					}
					break;
				case 3:
					mostrarClientes();
					break;
				case 4:
					System.out.println("1 - Listar todos os Produtos\n2 - Listar Comidas\n3 - Listar Bebidas");
					int opt3 = in.nextInt();
					in.nextLine();
					switch(opt3) {
						case 1:
							mostrarProdutos();
							break;
						case 2:
							mostrarComidas();
							break;
						case 3:
							mostrarBebidas();
							break;
					}
					break;
				case 5:
					realizarCompra();
					break;
				case 0:
					menu = false;
					break;
				default:
					break;
			}
		}
		
		
		in.close();
	}
	
	public static void cadastrarCliente() throws Exception {
		Cliente auxCliente = new Cliente();
		
		System.out.println("CPF:");
		auxCliente.setCpf(in.nextInt());
		in.nextLine();
		
		for(Cliente cliente : sistema.listarClientes()) {
			if(cliente.getCpf() == auxCliente.getCpf())
				throw new Exception();
		}
		
		System.out.println("Nome:");
		auxCliente.setNome(in.nextLine());
		
		sistema.cadastrarCliente(auxCliente);
	}
	
	public static void setProduto(Produto produto) {
		System.out.println("Nome:");
		produto.setNome(in.nextLine());
		System.out.println("Preço:");
		produto.setPreco(in.nextFloat());
		in.nextLine();
	}
	
	public static void cadastrarBebida() {
		Bebida auxBebida = new Bebida();
		
		setProduto(auxBebida);
		System.out.println("Quantidade ML:");
		auxBebida.setQuantidadeML(in.nextInt());
		in.nextLine();
		System.out.println("Aloolica: (1 - Sim)");
		int opt = in.nextInt();
		in.nextLine();
		if(opt == 1)
			auxBebida.setAlcoolica(true);
		
		sistema.cadastrarProduto(auxBebida);
	}
	
	public static void cadastrarComida() {
		Comida auxComida = new Comida();
		
		setProduto(auxComida);
		System.out.println("Número Calorias:");
		auxComida.setNumeroCalorias(in.nextInt());
		in.nextLine();
		System.out.println("Peso:");
		auxComida.setPeso(in.nextInt());
		in.nextLine();
		
		sistema.cadastrarProduto(auxComida);
	}
	
	public static void realizarCompra(){
		System.out.println("Lista de Clientes:");
		mostrarClientes();
		System.out.println();
		System.out.println("Lista de Produtos:");
		mostrarComidas();
		System.out.println();
		mostrarBebidas();
		
		System.out.println("Escolher CPF do cliente:");
		int auxcpf = in.nextInt();
		in.nextLine();
		
		System.out.println("Escolher nome do Produto:");
		String auxnome = in.nextLine();
		
		for(Cliente cliente : sistema.listarClientes()) {
			if(cliente.getCpf() == auxcpf) {
				for(Produto produto : sistema.listarProdutos()) {
					if(produto.getNome().equals(auxnome))
						sistema.realizarCompra(cliente, produto);
				}
			}
		}
		
	}
	
	public static void mostrarProdutos() {
		for(int i=0; i<sistema.listarProdutos().size();i++)
			System.out.println(i+1 + " - " + sistema.listarProdutos().get(i).toString());
	}
	
	public static void mostrarComidas() {
		System.out.println("Comidas:");
		for(int i=0; i<sistema.listarProdutos().size();i++) {
			if(sistema.listarProdutos().get(i) instanceof Comida)
				System.out.println(i+1 + " - " + sistema.listarProdutos().get(i).toString());
		}
	}
	
	public static void mostrarBebidas() {
		System.out.println("Bebidas:");
		for(int i=0; i<sistema.listarProdutos().size();i++) {
			if(sistema.listarProdutos().get(i) instanceof Bebida)
				System.out.println(i+1 + " - " + sistema.listarProdutos().get(i).toString());
		}
	}
	
	public static void mostrarClientes() {
		for(int i=0; i<sistema.listarClientes().size(); i++)
			System.out.println(i+1 + " - " + sistema.listarClientes().get(i).toString());
	}

}
