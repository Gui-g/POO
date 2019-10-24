import java.util.Scanner;

public class MenuPrincipal {

	private static Agenda agenda = new Agenda();
	private static Scanner in = new Scanner(System.in);
	
	public static void main(String[] args) {
		boolean menu = true;
		int opt;
		while(menu) {
			System.out.println();
			System.out.println("Opções:\n1 - Inserir Contato\n2 - Remover Contato\n3 - Consultar Contato\n4 - Sair");
			opt = in.nextInt();
			switch(opt) {
			case 1:
				inserir();
				break;
			case 2:
				remover();
				break;
			case 3:
				consultar();
				break;
			case 4:
				menu = sair();
				break;
			default:
				break;
			}
		}
	}
	
	public static void inserir() {
		Contato auxContato = new Contato();
		Contato teste;
		
		System.out.println("Telefone:");
		auxContato.setTelefone(in.nextInt());
		in.nextLine();
		
		teste = agenda.consultaContato(auxContato.getTelefone());
		if(teste == null) {
			System.out.println("Nome:");
			auxContato.setNome(in.nextLine());
			System.out.println("Email:");
			auxContato.setEmail(in.nextLine());
			System.out.println("Endereço:");
			auxContato.setEndereco(in.nextLine());
			
			agenda.setContato(auxContato);
		}
		else {
			System.out.println();
			System.out.println("Telefone já cadastrado");
		}
	}
	
	public static void remover() {
		int auxTelefone;
		Contato teste;
		
		System.out.println();
		System.out.println("Telefone para remover:");
		auxTelefone = in.nextInt();
		in.nextLine();
		teste = agenda.consultaContato(auxTelefone);
		
		if(teste == null) {
			System.out.println("Telefone não encontrado");
		}
		else {
			agenda.removeContato(auxTelefone);
			System.out.println("Cadastro removido");
		}
	}
	
	public static void consultar() {
		int auxTelefone;
		boolean menu = true;
		boolean existe;
		
		System.out.println();
		System.out.println("Lista de Telefones:");
		if(agenda.getQuantContatos() == 0)
			System.out.println("Nenhum telefone cadastrado");
		else {
			for(int i=0; i<agenda.getQuantContatos(); i++) 
				System.out.println(i + " - " + agenda.getContato(i).getTelefone());
			
			while(menu) {
				existe = false;
				System.out.println();
				System.out.println("Telefone que deseja consultar: (0 para terminar consulta)");
				auxTelefone = in.nextInt();
				in.nextLine();
				if(auxTelefone == 0)
					menu = false;
				else {
					System.out.println();
					for(int i=0; i<agenda.getQuantContatos(); i++) {
						if(agenda.getContato(i).getTelefone() == auxTelefone) {
							System.out.println(agenda.getContato(i).toString());
							existe = true;
						}
					}
					if(existe == false) {
						System.out.println("Número não cadastrado");
					}
				}
			}
		}
	}
	
	public static boolean sair() {
		return false;
	}
	
}
