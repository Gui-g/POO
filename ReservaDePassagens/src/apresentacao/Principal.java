package apresentacao;

import java.util.Scanner;

import dados.Aeroporto;
import dados.Cidade;
import negocio.ReservaPassagem;

public class Principal {
	private static Scanner sc = new Scanner(System.in);
	private static ReservaPassagem reservaPassagem = new ReservaPassagem();

	public static void main(String[] args) {
		int opcao = -1;
		
		while (opcao != 0) {
		
			System.out.println("Sistema de Reserva de Passagens");
			System.out.println("1 - Cadastro de Cidade");
			System.out.println("2 - Cadastro de Aeroporto");
			System.out.println("3 - Listar Aeroportos");
			System.out.println("0 - Para sair");
			System.out.print("Digite a opção desejada:");
			opcao = sc.nextInt();
			
			switch (opcao) {
			case 1:
					cadastrarCidade();
				break;
			case 2:
				cadastrarAeroporto();
			break;
			case 3:
				listarAeroportos();
			break;
	
			default:
				break;
			}
		}

	}

	private static void listarAeroportos() {
		System.out.println("Lista de Aeroportos:");
		for (int i = 0; i < reservaPassagem.getQuantAeroportos(); i++) {
			System.out.println("Nome:"+reservaPassagem.getAeroportos()[i].getNome());
		}
		System.out.println("");
	}

	private static void cadastrarCidade() {
		Cidade cidade = new Cidade();
		
		System.out.println("Cadastro de Cidade");
		System.out.print("Digite o nome da cidade:");
		cidade.setNome(sc.next());
		//sc.next();
		System.out.print("\nDigite o estado da cidade:");
		cidade.setEstado(sc.next());
		//sc.next();
		reservaPassagem.cadastrarCidade(cidade);
	}
	
	
	private static void cadastrarAeroporto() {
		Aeroporto aeroporto = new Aeroporto();
		
		System.out.println("Cadastro de Aeroporto");
		System.out.print("Digite o código do aeroporto:");
		aeroporto.setCodigo(sc.next());
		//sc.next();
		System.out.print("Digite o nome do aeroporto:");
		aeroporto.setNome(sc.next());
		//sc.next();
		Cidade cidades[] = reservaPassagem.getCidades();
		System.out.println("Lista de Cidades:");
		for (int i = 0; i < reservaPassagem.getQuantCidades(); i++) {
			System.out.println((i+1)+" - "+cidades[i].getNome());
			
		}
		System.out.print("\nDigite código da cidade:");
		int cod = sc.nextInt();
		aeroporto.setCidade(cidades[cod-1]);
		
		reservaPassagem.cadastrarAeroporto(aeroporto);
	}
}
