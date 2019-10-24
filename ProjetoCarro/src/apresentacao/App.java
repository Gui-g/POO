package apresentacao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import dados.*;
import logica.Revenda;

public class App {

	private static Scanner in = new Scanner(System.in);
	private static Revenda revenda = new Revenda();
	
	public static void main(String[] args) {
		
		int opt;
		boolean menu = true;
		
		while(menu) {
			System.out.println("Menu Principal:\n1 - Cadastrar Veiculo\n2 - Listar Veiculos\n3 - Mostrar Veiculo\n"
					+ "4 - Vender Veiculo\n0 - Sair");
			opt = in.nextInt();
			in.nextLine();
			System.out.println();
			switch(opt) {
				case 1:
					cadastrarVeiculo();
					System.out.println();
					break;
				case 2:
					listarVeiculo();
					System.out.println();
					break;
				case 3:
					System.out.println("Placa do veículo que deseja checar:");
					mostrarVeiculo(in.nextLine());
					System.out.println();
					break;
				case 4:
					try {
						System.out.println("Digite a placa do carro que deseja vender:");
						venderVeiculo(in.nextLine());
						System.out.println();
					} catch (ParseException e) {
						System.out.println("Falha na venda do veiculo");
					}
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
	
	public static void cadastrarVeiculo() {
		int opt;
		
		System.out.println("Opções de cadastro:\n1 - Carro\n2 - Caminhonete\n3 - Caminhão\n4 - Moto\n5 - Onibus");
		opt = in.nextInt();
		in.nextLine();
		switch(opt) {
			case 1:
				Carro carro = new Carro();
				try {
					setVeiculo(carro);
					System.out.println("Numero Assentos:");
					carro.setNumAssentos(in.nextInt());
					in.nextLine();
					System.out.println("Potencial CV:");
					carro.setPotenciaCV(in.nextInt());
					in.nextLine();
					System.out.println("Numero Portas:");
					carro.setNumPortas(in.nextInt());
					in.nextLine();
					
					revenda.cadastrarVeiculo(carro);
					System.out.println();
					System.out.println("Carro cadastrado!");
					System.out.println();
				} catch (Exception e) {
					System.out.println("Falha em cadastrar carro");
				}
				break;
			case 2:
				Caminhonete caminhonete = new Caminhonete();
				try {
					setVeiculo(caminhonete);
					System.out.println("Capacidade Maxima:");
					caminhonete.setCapacidadeMaxima(in.nextFloat());
					in.nextLine();
					System.out.println("Tipo de Carroceria:");
					caminhonete.setTipoCarroceria(in.nextLine());
					System.out.println("Potencia CV:");
					caminhonete.setPotenciaCV(in.nextInt());
					in.nextLine();
					System.out.println("Cabine Dupla: 1 - Sim");
					int cabine = in.nextInt();
					in.nextLine();
					if(cabine == 1)
						caminhonete.setCabineDupla(true);
					else
						caminhonete.setCabineDupla(false);
					
					revenda.cadastrarVeiculo(caminhonete);
					System.out.println();
					System.out.println("Caminhonete cadastrada!");
					System.out.println();					
				} catch (Exception e) {
					System.out.println("Falha em cadastrar caminhonete");
				}
				break;
			case 3:
				Caminhao caminhao = new Caminhao();
				try {
					setVeiculo(caminhao);
					System.out.println("Capacidade Maxima:");
					caminhao.setCapacidadeMaxima(in.nextFloat());
					in.nextLine();
					System.out.println("Tipo Carroceria:");
					caminhao.setTipoCarroceria(in.nextLine());
					System.out.println("Potencial CV:");
					caminhao.setPotenciaCV(in.nextInt());
					in.nextLine();
					System.out.println("Numero Eixos:");
					caminhao.setNumEixos(in.nextInt());
					in.nextLine();
					
					revenda.cadastrarVeiculo(caminhao);
					System.out.println();
					System.out.println("Caminhão cadastrado!");
					System.out.println();					
				} catch (Exception e) {
					System.out.println("Falha em cadastrar caminhão");
				}
				break;
			case 4:
				Moto moto = new Moto();
				try {
					setVeiculo(moto);
					System.out.println("Cilindradas:");
					moto.setCilindrada(in.nextInt());
					in.nextLine();
					System.out.println("Partida Eletrica: 1 - Sim");
					int partida = in.nextInt();
					in.nextLine();
					if(partida == 1)
						moto.setPartidaEletrica(true);
					else
						moto.setPartidaEletrica(false);
					
					revenda.cadastrarVeiculo(moto);
					System.out.println();
					System.out.println("Moto cadastrada!");
					System.out.println();				
				} catch (Exception e) {
					System.out.println("Falha em cadastrar moto");
				}
				break;
			case 5:
				Onibus onibus = new Onibus();
				try {
					setVeiculo(onibus);
					System.out.println("Numero Assentos:");
					onibus.setNumAssentos(in.nextInt());
					in.nextLine();
					System.out.println("Potencia CV:");
					onibus.setPotenciaCV(in.nextInt());
					in.nextLine();
					
					revenda.cadastrarVeiculo(onibus);
					System.out.println();
					System.out.println("Onibus cadastrado!");
					System.out.println();
				} catch (Exception e) {
					System.out.println("Falha em cadastrar onibus");
				}
				break;
			default:
				System.out.println("Opção invalida");
				break;
		}
	}
	
	public static void setVeiculo(Veiculo veiculo) throws Exception {
		String data;
		
		System.out.println("Ano de Fabricação:");
		veiculo.setAnoFabricacao(in.nextInt());
		in.nextLine();
		System.out.println("Modelo:");
		veiculo.setModelo(in.nextLine());
		System.out.println("Numero Placa:");
		veiculo.setNumPlaca(in.nextLine());
		
		for(int i=0; i<revenda.getVeiculos().size(); i++){
			if(revenda.getVeiculos().get(i).getNumPlaca().equals(veiculo.getNumPlaca())) {
				System.out.println();
				throw new Exception("Placa já cadastrada");
			}
		}
		
		System.out.println("Cor:");
		veiculo.setCor(in.nextLine());
		System.out.println("Numero Chassi:");
		veiculo.setNumChassi(in.nextLine());
		System.out.println("Quilometragem:");
		veiculo.setQuilometragem(in.nextFloat());
		in.nextLine();
		System.out.println("Marca:");
		veiculo.setMarca(in.nextLine());
		System.out.println("Numero Marchas:");
		veiculo.setNumMarchas(in.nextInt());
		in.nextLine();
		System.out.println("Valor do Veiculo:");
		veiculo.setValorVeiculo(in.nextFloat());
		in.nextLine();
		System.out.println("Data de Entrada: (dd/MM/yyyy)");
		data = in.nextLine();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date dataFormatado = sdf.parse(data);
		veiculo.setDataEntrada(dataFormatado);
	}
	
	public static void listarVeiculo() {
		System.out.println("Menu:\n1 - Listar veículos não vendidos\n2 - Listar todos veículos");
		int opt = in.nextInt();
		in.nextLine();
		switch(opt) {
			case 1:
				System.out.println("Carros:");
				System.out.println();
				for(int i=0; i<revenda.getVeiculosNaoVendidos().size(); i++) {
					if(revenda.getVeiculosNaoVendidos().get(i) instanceof Carro) {
						System.out.println(revenda.getVeiculosNaoVendidos().get(i).toString3());
					}
				}
				System.out.println();
				System.out.println("Caminhonete:");
				for(int i=0; i<revenda.getVeiculosNaoVendidos().size(); i++) {
					if(revenda.getVeiculosNaoVendidos().get(i) instanceof Caminhonete) {
						System.out.println(revenda.getVeiculosNaoVendidos().get(i).toString3());
					}
				}
				System.out.println();
				System.out.println("Caminhão:");
				for(int i=0; i<revenda.getVeiculosNaoVendidos().size(); i++) {
					if(revenda.getVeiculosNaoVendidos().get(i) instanceof Caminhao) {
						System.out.println(revenda.getVeiculosNaoVendidos().get(i).toString3());
					}
				}
				System.out.println();
				System.out.println("Moto:");
				for(int i=0; i<revenda.getVeiculosNaoVendidos().size(); i++) {
					if(revenda.getVeiculosNaoVendidos().get(i) instanceof Moto) {
						System.out.println(revenda.getVeiculosNaoVendidos().get(i).toString3());
					}
				}
				System.out.println();
				System.out.println("Onibus:");
				for(int i=0; i<revenda.getVeiculosNaoVendidos().size(); i++) {
					if(revenda.getVeiculosNaoVendidos().get(i) instanceof Onibus) {
						System.out.println(revenda.getVeiculosNaoVendidos().get(i).toString3());
					}
				}
				break;
			case 2:
				System.out.println("Carros:");
				for(int i=0; i<revenda.getVeiculos().size(); i++) {
					if(revenda.getVeiculos().get(i) instanceof Carro) {
						System.out.println(revenda.getVeiculos().get(i).toString4());
					}
				}
				System.out.println();
				System.out.println("Caminhonete:");
				for(int i=0; i<revenda.getVeiculos().size(); i++) {
					if(revenda.getVeiculos().get(i) instanceof Caminhonete) {
						System.out.println(revenda.getVeiculos().get(i).toString4());
					}
				}
				System.out.println();
				System.out.println("Caminhao:");
				for(int i=0; i<revenda.getVeiculos().size(); i++) {
					if(revenda.getVeiculos().get(i) instanceof Caminhao) {
						System.out.println(revenda.getVeiculos().get(i).toString4());
					}
				}
				System.out.println();
				System.out.println("Moto:");
				for(int i=0; i<revenda.getVeiculos().size(); i++) {
					if(revenda.getVeiculos().get(i) instanceof Moto) {
						System.out.println(revenda.getVeiculos().get(i).toString4());
					}
				}
				System.out.println();
				System.out.println("Onibus:");
				for(int i=0; i<revenda.getVeiculos().size(); i++) {
					if(revenda.getVeiculos().get(i) instanceof Onibus) {
						System.out.println(revenda.getVeiculos().get(i).toString4());
					}
				}
				break;
			default:
				System.out.println("Opção inválida");
				break;				
		}
	}
	
	public static void mostrarVeiculo(String placa) {
		for(int i=0; i<revenda.getVeiculosNaoVendidos().size(); i++) {
			if(revenda.getVeiculos().get(i).getNumPlaca().equals(placa)) {
				if(revenda.getVeiculos().get(i) instanceof Carro) {
					System.out.println("Carro:");
					System.out.println("Informações gerais:");
					System.out.println();
					System.out.println(revenda.getVeiculos().get(i).toString2());
					System.out.println();
					System.out.println("Informações especificas:");
					System.out.println();
					System.out.println(((Carro) revenda.getVeiculos().get(i)).toString());
					System.out.println();
				}
				
				if(revenda.getVeiculos().get(i) instanceof Caminhonete) {
					System.out.println("Caminhonete:");
					System.out.println("Informações gerais:");
					System.out.println();
					System.out.println(revenda.getVeiculos().get(i).toString2());
					System.out.println();
					System.out.println("Informações especificas:");
					System.out.println();
					System.out.println(((Caminhonete) revenda.getVeiculos().get(i)).toString());
					System.out.println();
				}
				
				if(revenda.getVeiculos().get(i) instanceof Caminhao) {
					System.out.println("Caminhonete:");
					System.out.println("Informações gerais:");
					System.out.println();
					System.out.println(revenda.getVeiculos().get(i).toString2());
					System.out.println();
					System.out.println("Informações especificas:");
					System.out.println();
					System.out.println(((Caminhao) revenda.getVeiculos().get(i)).toString());
					System.out.println();
				}
				
				if(revenda.getVeiculos().get(i) instanceof Moto) {
					System.out.println("Moto:");
					System.out.println("Informações gerais:");
					System.out.println();
					System.out.println(revenda.getVeiculos().get(i).toString2());
					System.out.println();
					System.out.println("Informações especificas:");
					System.out.println();
					System.out.println(((Moto) revenda.getVeiculos().get(i)).toString());
					System.out.println();
				}
				
				if(revenda.getVeiculos().get(i) instanceof Onibus) {
					System.out.println("Onibus:");
					System.out.println("Informações gerais:");
					System.out.println();
					System.out.println(revenda.getVeiculos().get(i).toString2());
					System.out.println();
					System.out.println("Informações especificas:");
					System.out.println();
					System.out.println(((Onibus) revenda.getVeiculos().get(i)).toString());
					System.out.println();
				}
			}
		}
	}
	
	public static void venderVeiculo(String placa) throws ParseException {
		String data;
		
		for(int i=0; i<revenda.getVeiculosNaoVendidos().size(); i++) {
			if(revenda.getVeiculosNaoVendidos().get(i).getNumPlaca().equals(placa)){
				System.out.println("Data da Venda: (dd/MM/yyyy)");
				data = in.nextLine();
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				Date dataFormatado = sdf.parse(data);
				revenda.getVeiculosNaoVendidos().get(i).setDataVenda(dataFormatado);
				
				revenda.getVeiculosNaoVendidos().get(i).setVendido(true);
			}
		}
	}
		
	
}



















