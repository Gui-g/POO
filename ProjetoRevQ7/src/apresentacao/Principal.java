package apresentacao;

import java.util.Scanner;
import dados.*;
import negocio.ReservaPassagem;

public class Principal {

	private static Scanner in = new Scanner(System.in);
	private static ReservaPassagem reserva = new ReservaPassagem();
	
	public static void main(String[] args) {
		boolean menu = true;
		int opt;
		
		while(menu) {
			System.out.println("Menu:\n1 - Cadastrar Passageiro\n2 - Cadastrar Cidade\n3 - Cadastrar Onibus\n"
					+ "4 - Cadastrar Viagem\n5 - Comprar Passagem\n6 - Passagens Disponiveis\n0 - Sair");
			opt = in.nextInt();
			in.nextLine();
			switch(opt) {
				case 1:
					try {
						cadastrarPassageiro();
					}
					catch(Exception e) {
						System.out.println("CPF já cadastrado");
					}
					System.out.println();
					break;
				case 2:
					try {
						cadastrarCidade();
					}
					catch(Exception e) {
						System.out.println("Código já cadastrado");
					}
					System.out.println();
					break;
				case 3:
					try {
						cadastrarOnibus();
					}
					catch(Exception e) {
						System.out.println("Código já cadastrado");
					}
					System.out.println();
					break;
				case 4:
					try {
						cadastrarViagem();
					}
					catch(Exception e) {
						System.out.println("Código já cadastrado");
					}
					System.out.println();
					break;
				case 5:
					comprarPassagem();
					System.out.println();
					break;
				case 6:
					mostrarPassagensDisponiveis();
					System.out.println();
					break;
				case 0:
					menu = false;
					break;
				default:
					break;
			}
		}
	}
	
	public static void cadastrarPassageiro() throws Exception{
		Passageiro auxPass = new Passageiro();
		
		System.out.println("CPF:");
		auxPass.setCpf(in.nextInt());
		in.nextLine();
		
		for(Passageiro pass : reserva.getListaPassageiros()) {
			if(pass.getCpf() == auxPass.getCpf())
				throw new Exception();
		}
		
		System.out.println("Nome:");
		auxPass.setNome(in.nextLine());
		System.out.println("RG:");
		auxPass.setRg(in.nextInt());
		in.nextLine();
		System.out.println("Endereço:");
		auxPass.setEndereco(in.nextLine());
		
		reserva.cadastrarPassageiro(auxPass);
	}
	
	public static void cadastrarOnibus() throws Exception{
		Onibus auxOnibus = new Onibus();
		
		System.out.println("Código:");
		auxOnibus.setCodigo(in.nextInt());
		in.nextLine();
		
		for(Onibus bus : reserva.getListaOnibus()) {
			if(bus.getCodigo() == auxOnibus.getCodigo())
				throw new Exception();
		}
		
		System.out.println("Número de Assentos:");
		auxOnibus.setNumeroAssentos(in.nextInt());
		in.nextLine();
		System.out.println("Ar Condicionado: 1 - Sim");
		int airCon = in.nextInt();
		in.nextLine();
		if(airCon == 1)
			auxOnibus.setArCondicionado(true);
		else
			auxOnibus.setArCondicionado(false);
		
		reserva.cadastrarOnibus(auxOnibus);
	}
	
	public static void cadastrarViagem() throws Exception{
		Viagem auxViagem = new Viagem();
		
		System.out.println("Código:");
		auxViagem.setCodigo(in.nextInt());
		in.nextLine();
		
		for(Viagem viagem : reserva.getListaViagens()) {
			if(viagem.getCodigo() == auxViagem.getCodigo())
				throw new Exception();
		}
		
		System.out.println("Cidades Cadastradas:");
		for(int i=0; i<reserva.getListaCidade().size(); i++) {
			System.out.println(reserva.getListaCidade().get(i).getCodigoCidade() + " - Nome: " + reserva.getListaCidade().get(i).getNome());
		}
		
		System.out.println("Cidade Origem: (Código)");
		int origem = in.nextInt();
		in.nextLine();
		for(Cidade cidade : reserva.getListaCidade()) {
			if(origem == cidade.getCodigoCidade())
				auxViagem.setOrigem(cidade);
		}
		
		System.out.println("Cidade Destino: (Código)");
		int destino = in.nextInt();
		in.nextLine();
		for(Cidade cidade : reserva.getListaCidade()) {
			if(destino == cidade.getCodigoCidade())
				auxViagem.setDestino(cidade);
		}
		
		System.out.println("Ônibus Cadastrados:");
		for(int i=0; i<reserva.getListaOnibus().size(); i++) {
			System.out.println(reserva.getListaOnibus().get(i).getCodigo() + " - Nº Assentos: " + reserva.getListaOnibus().get(i).getNumeroAssentos());
		}
		
		System.out.println("Onibus: (Código)");
		int onibus = in.nextInt();
		in.nextLine();
		for(Onibus bus : reserva.getListaOnibus()) {
			if(onibus == bus.getCodigo())
				auxViagem.setOnibus(bus);
		}
		
		System.out.println("Preço:");
		auxViagem.setPreco(in.nextFloat());
		in.nextLine();
		
		System.out.println("Volta? 1 - Sim");
		int volta = in.nextInt();
		in.nextLine();
		if(volta == 1) {
			auxViagem.setIdaVolta(true);
			
			Viagem voltaViagem = new Viagem();
			voltaViagem.setCodigo(auxViagem.getCodigo() + 1);
			voltaViagem.setDestino(auxViagem.getOrigem());
			voltaViagem.setOrigem(auxViagem.getDestino());
			voltaViagem.setOnibus(auxViagem.getOnibus());
			voltaViagem.setPreco(auxViagem.getPreco());
			
			auxViagem.setVolta(voltaViagem);
		}
		else
			auxViagem.setIdaVolta(false);
		
		reserva.cadastrarViagem(auxViagem);
	}
	
	public static void cadastrarCidade() throws Exception {
		Cidade auxCidade = new Cidade();
		
		System.out.println("Código:");
		auxCidade.setCodigoCidade(in.nextInt());
		in.nextLine();
		
		for(Cidade city : reserva.getListaCidade()) {
			if(city.getCodigoCidade() == auxCidade.getCodigoCidade())
				throw new Exception();
		}
		
		System.out.println("Nome:");
		auxCidade.setNome(in.nextLine());
		
		reserva.cadastrarCidade(auxCidade);
	}
	
	public static void comprarPassagem() {
		System.out.println("Lista de Passageiros:");
		for(int i=0; i<reserva.getListaPassageiros().size(); i++)
			System.out.println(reserva.getListaPassageiros().get(i).getCpf() + " - " + reserva.getListaPassageiros().get(i).getNome());
		
		System.out.println("CPF do Passageiro:");
		int cpf = in.nextInt();
		in.nextLine();
		
		for(Passageiro pass : reserva.getListaPassageiros()) {
			if(pass.getCpf() == cpf){
				mostrarPassagensDisponiveis();
				
				System.out.println("Escolher Viagem: (Código)");
				int codViagem = in.nextInt();
				in.nextLine();
				
				for(Viagem v : reserva.getListaViagens()) {
					if(v.getCodigo() == codViagem) {
						for(int num : reserva.AssentosDisponiveis(v))
							System.out.printf("%d ", num);
							System.out.println();
					}
				}
				
				System.out.println("Escolhe Assento:");
				int assento = in.nextInt();
				in.nextLine();
				
				for(Viagem v : reserva.getListaViagens()) {
					if(v.getCodigo() == codViagem) {
						reserva.reservarIda(pass, v, assento);
						if(v.isIdaVolta())
							reserva.reservarVolta(pass, v, assento);
					}
				}
			}
		}
	}
	
	public static void mostrarPassagensDisponiveis() {
		for(Viagem v : reserva.viagensDisponiveis()) {
			System.out.println(v.toString());
			System.out.println("Quantidade de Assentos: " + reserva.quantAssentosDisponiveis(v));
			System.out.println();
		}
	}
}



























