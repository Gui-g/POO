package apresentacao;

import java.io.IOException;
import java.util.Scanner;

import dados.Contato;
import exceptions.AgendaVaziaException;
import exceptions.ContatoJaCadastradoException;
import exceptions.ContatoNaoEncontradoException;
import negocio.Agenda;

public class Main {	
	
	private static Agenda agenda = new Agenda();
	private static Scanner in = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		boolean menu = true;
		
		while(menu) {
			int opt;
			int opt2;
			System.out.println("Menu\n1 - Nova Agenda (txt)\n2 - Nova Agenda(bin)\n3 - Salvar Agenda (txt)\n4 - Salvar Agenda (bin)"
					+ "\n5 - Cadastrar Contato\n6 - Buscar Contato\n7 - Exibir Contatos\n8 - Remover Contato\n9 - Sair");
			opt = in.nextInt();
			in.nextLine();
			switch(opt) {
				case 1:
					agenda.setListaContatos(agenda.getManipulador().carregaDadosArquivoTexto());
					System.out.println();
					break;
				case 2:
					agenda.setListaContatos(agenda.getManipulador().carregaDadosArquivoBinario());
					System.out.println();
					break;
				case 3:
					try {
						agenda.getManipulador().gravaDadosArquivoTexto(agenda.getContatos());
					} catch (IOException e) {
						e.printStackTrace();
					} catch (AgendaVaziaException e) {
						System.out.println("Agenda vazia");
					}
					System.out.println();
					break;
				case 4:
					try {
						agenda.getManipulador().gravaDadosArquivoBinario(agenda.getContatos());
					} catch (IOException e) {
						e.printStackTrace();
					} catch (AgendaVaziaException e) {
						System.out.println("Agenda vazia");
					}
					System.out.println();
					break;
				case 5:
					Contato novo = new Contato();
					System.out.println("Nome:");
					novo.setNome(in.nextLine());
					System.out.println("Telefone:");
					novo.setTelefone(in.nextInt());
					in.nextLine();
					
					try {
						agenda.cadastrarContato(novo);
					} catch (ContatoJaCadastradoException e) {
						System.out.println("Contato já cadastrado");
					}
					System.out.println();
					break;
				case 6:
					System.out.println("1 - Buscar por nome\n2 - Buscar por telefone");
					opt2 = in.nextInt();
					in.nextLine();
					switch(opt2) {
						case 1:
							System.out.println("Nome:");
							try {
								System.out.println(agenda.buscarContatos(in.nextLine()).toString());
							} catch (ContatoNaoEncontradoException e) {
								System.out.println("Contato não encontrado");
							} catch (AgendaVaziaException e) {
								System.out.println("Agenda vazia");
							}
							break;
						case 2:
							System.out.println("Telefone:");
							try {
								System.out.println(agenda.buscarContato(in.nextInt()).toString());
							} catch (ContatoNaoEncontradoException e) {
								System.out.println("Contato não encontrado");
							} catch (AgendaVaziaException e) {
								System.out.println("Agenda vazia");
							}
							break;
						default:
							break;
					}
					System.out.println();
					break;
				case 7:
					try {
						for(Contato contato : agenda.getContatos())
							System.out.println(contato.toString());
					} catch (AgendaVaziaException e) {
						System.out.println("Agenda vazia");
					}
					System.out.println();
					break;
				case 8:
					System.out.println("1 - Remover por nome\n2 - Remover por telefone");
					opt2 = in.nextInt();
					in.nextLine();
					switch(opt2) {
						case 1:
							System.out.println("Nome:");
							String nome = in.nextLine();
							try {
								for(Contato contato : agenda.buscarContatos(nome))
									agenda.removerContato(contato);
							} catch (ContatoNaoEncontradoException e) {
								System.out.println("Contato não encontrado");
							} catch (AgendaVaziaException e) {
								System.out.println("Agenda vazia");
							}
							break;
						case 2:
							System.out.println("Telefone:");
							int telefone = in.nextInt();
							in.nextLine();
							try {
								agenda.removerContato(agenda.buscarContato(telefone));
							} catch (ContatoNaoEncontradoException e) {
								System.out.println("Contato não encontrado");
							} catch (AgendaVaziaException e) {
								System.out.println("Agenda vazia");
							}
							break;
						default:
							break;
					}
					System.out.println();
					break;
				case 9:
					menu = false;
					try {
						agenda.getManipulador().gravaDadosArquivoTexto(agenda.getContatos());
						agenda.getManipulador().gravaDadosArquivoBinario(agenda.getContatos());
					} catch (IOException e) {
						e.printStackTrace();
					} catch (AgendaVaziaException e) {
						System.out.println("Agenda vazia");
					}
					agenda.getManipulador().fechaArquivo();
					break;
				default:
					break;
			}
		}
		
		
	}
}
