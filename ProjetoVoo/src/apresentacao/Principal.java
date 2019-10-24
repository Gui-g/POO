package apresentacao;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import dados.Aeroporto;
import dados.Cidade;
import dados.Cliente;
import dados.Reserva;
import dados.Trecho;
import negocio.ReservaPassagem;

public class Principal {
	
	private static ReservaPassagem reserva;
	private static Scanner in = new Scanner(System.in);
	
	public static void main(String[] args) {
		reserva = new ReservaPassagem();
		String string;
		int opt;
		
		boolean menu = true;
		do {
			System.out.println();
			System.out.println("1 - Cadastrar Cliente\n2 - Cadastrar Cidade\n3 - Cadastrar Aeroporto\n4 - Fazer Reserva\n5 - Mostrar Reservas\n");
			string = in.nextLine();
			opt = Integer.parseInt(string);
			switch(opt) {
			case 1:
				cadastrarCliente();
				break;
			case 2:
				cadastrarCidade();
				break;
			case 3:
				cadastrarAeroporto();
				break;
			case 4:
				try {
					fazerReserva();
				} catch (ParseException e) {
					System.out.println("Erro em fazer reserva");
				}
				break;
			case 5:
				mostrarReservas();
				break;
			default:
				menu = false;
			}
		} while(menu);	
		in.close();
	}
	
	public static void fazerReserva() throws ParseException {
		Reserva novaReserva;
		Cliente cliente = new Cliente();
		boolean volta = false;
		int cpf;
		int opt;
		String data;
		
		System.out.println("Ida e volta?\n 1 - Sim");
		opt = in.nextInt();
		in.nextLine();
		
		if(opt == 1)
			volta = true;
		
		novaReserva = new Reserva(volta);
		
		System.out.println("CPF do cliente:");
		cpf = in.nextInt();
		in.nextLine();
		for(Cliente clienteAux : reserva.listaClientes()) {
			if(clienteAux.getCpf() == cpf) {
				cliente = clienteAux;
				opt = 2;
			}
		}
		
		if(opt == 2) {
			System.out.println("Número da Reserva:");
			novaReserva.setNumreserva(in.nextInt());
			in.nextLine();
			System.out.println("Data do Voo: (dd/MM/yyyy)");
			data = in.nextLine();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date dataFormatado = sdf.parse(data);
			novaReserva.setDatavoo(dataFormatado);
			System.out.println("Hora do Voo: (H:mm:ss)");
			data = in.nextLine();
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("H:mm:ss");
			LocalTime time = LocalTime.parse(data, dtf);
			novaReserva.setHoravoo(time);
			System.out.println("Preço da Reserva:");
			novaReserva.setPreco(in.nextFloat());
			in.nextLine();
			System.out.println("Classe do Voo:");
			novaReserva.setClassevoo(in.nextLine());
			
			System.out.println("Cadastrar Trechos:");
			ArrayList<Trecho> trechos = cadastrarTrechos();
			novaReserva.setTrechos(trechos);
			
			reserva.reservarIda(cliente, novaReserva);
			
			if(volta) {
				Reserva voltaReserva = new Reserva(false);
				
				System.out.println("Número da Reserva da volta:");
				voltaReserva.setNumreserva(in.nextInt());
				in.nextLine();
				System.out.println("Data do Voo de volta: (dd/MM/yyyy)");
				data = in.nextLine();
				dataFormatado = sdf.parse(data);
				voltaReserva.setDatavoo(dataFormatado);
				System.out.println("Hora do Voo de volta: (H:mm:ss)");
				data = in.nextLine();
				time = LocalTime.parse(data, dtf);
				voltaReserva.setHoravoo(time);
				System.out.println("Preço da Reserva de volta:");
				voltaReserva.setPreco(in.nextFloat());
				in.nextLine();
				System.out.println("Classe do Voo de volta:");
				voltaReserva.setClassevoo(in.nextLine());
				
				System.out.println("Cadastrar Trechos Volta:");
				trechos = cadastrarTrechos();
				voltaReserva.setTrechos(trechos);
				
				reserva.reservarVolta(cliente, novaReserva, voltaReserva);
			}
		}
	}
	
	public static void cadastrarCliente() {
		Cliente aux = new Cliente();
		
		System.out.println("CPF:");
		aux.setCpf(in.nextInt());
		in.nextLine();
		System.out.println("Nome:");
		aux.setNome(in.nextLine());
		System.out.println("Endereço:");
		aux.setEndereco(in.nextLine());
		System.out.println("Telefone:");
		aux.setTelefone(in.nextInt());
		in.nextLine();
		
		reserva.cadastrarCliente(aux);
	}
	
	public static void cadastrarCidade() {
		Cidade aux = new Cidade();
		
		System.out.println("Estado:");
		aux.setEstado(in.nextLine());
		System.out.println("Nome:");
		aux.setNome(in.nextLine());
		
		reserva.cadastrarCidade(aux);
	}
	
	public static void cadastrarAeroporto() {
		Aeroporto aux = new Aeroporto();
		
		System.out.println("Código:");
		aux.setCodigo(in.nextLine());
		System.out.println("Nome:");
		aux.setNome(in.nextLine());
		
		for(int i=0; i<reserva.listaCidades().size(); i++) 
			System.out.println(String.format("%d - %s", i+1, reserva.listaCidades().get(i).getNome()));
		System.out.println("Cod cidade");
		int num = in.nextInt();
		in.nextLine();
		aux.setCidade(reserva.listaCidades().get(num-1));
		
		reserva.cadastrarAeroporto(aux);
	}
	
	private static ArrayList<Trecho> cadastrarTrechos() throws ParseException{
		ArrayList<Trecho> trechos = new ArrayList<Trecho>();
		int idBusca;
		
		while(true) {
			System.out.println("Num Trecho:");
			idBusca = in.nextInt();
			in.nextLine();
			if(idBusca == -1)
				break;
			else if (pesquisaIDTrechos(trechos, idBusca)) {
				System.out.println("Num Reserva já registrado");
				continue;
			}
			else
				trechos.add(criarTrecho(idBusca));
		}
		
		return trechos;
	}
	
	private static boolean pesquisaIDTrechos(ArrayList<Trecho> trechos, int ID) {
		
		for(Trecho trecho : trechos) {
			if(ID == trecho.getNumtrecho())
				return true;
		}
		
		return false;
	}
	
	private static Trecho criarTrecho(int numTrecho) throws ParseException {
		
		Trecho trecho = new Trecho();
		String data;
		
		trecho.setNumtrecho(numTrecho);
		in.nextLine();
		System.out.println("Duração do Trecho:");
		trecho.setDuracao(in.nextInt());
		in.nextLine();
		System.out.println("Data Partida:");
		data = in.nextLine();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date dataFormatado = sdf.parse(data);
		trecho.setDatapartida(dataFormatado);
		System.out.println("Data Chegada:");
		data = in.nextLine();
		dataFormatado = sdf.parse(data);
		trecho.setDatachegada(dataFormatado);
		System.out.println("Hora Partida:");
		data = in.nextLine();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("H:mm:ss");
		LocalTime time = LocalTime.parse(data, dtf);
		trecho.setHorapartida(time);
		System.out.println("Hora Chegada:");
		data = in.nextLine();
		time = LocalTime.parse(data,dtf);
		trecho.setHorachegada(time);
		System.out.println("Classe do Voo:");
		trecho.setClassevoo(in.nextLine());
		System.out.println("Número Poltrona:");
		trecho.setNumpoltrona(in.nextInt());
		in.nextLine();
		
		return trecho;
	}
	
	public static void mostrarReservas() {
		int opt;
		
		System.out.println("1 - Mostrar Reservas de um Cliente\n2 - Mostrar Todas as Reservas");
		opt = in.nextInt();
		in.nextLine();
		switch(opt) {
		case 1:
			System.out.println("CPF:");
			int cpf = in.nextInt();
			in.nextLine();
			for(Reserva reservaAux : reserva.mostrarReservas(cpf)) {
				System.out.println(reservaAux.toString());
			}
			break;
		case 2:
			for(Reserva reservaAux : reserva.mostrarReservas()) {
				System.out.println(reservaAux.toString());
			}		
			break;
		default:
			break;
		}
	}
	
}




























