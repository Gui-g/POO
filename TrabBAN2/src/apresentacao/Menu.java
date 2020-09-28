package apresentacao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.postgresql.util.PGInterval;

import dados.Assistente;
import dados.Bibliotecario;
import dados.Categoria;
import dados.Colecao;
import dados.Editora;
import dados.Emprestimo;
import dados.Exemplar;
import dados.Funcionario;
import dados.Livro;
import dados.Pessoa;
import dados.Status;
import dados.Universidade;
import dados.Usuario;
import negocio.Sistema;

public class Menu {

	private Scanner in = new Scanner(System.in);
	private Sistema sistema;
	
	public Menu() {
		sistema = new Sistema();
	}
	
	public void createMenu() throws ParseException {
		
		boolean menu = true;
		int option;
		
		while(menu) {
			System.out.println();
			System.out.println("1 - Gerenciar Dados\n2 - Gerenciar Emprestimos\n3 - Informações no Sistema\n0 - Sair");
			
			option = in.nextInt();
			
			switch(option) {
			case 1:
				int option2;
				System.out.println("1 - Gerenciar Pessoas\n2 - Gerenciar Universidades\n3 - Gerenciar Editoras\n4 - Gerenciar Livros");
				option2 = in.nextInt();
				in.nextLine();
				
				switch(option2) {
				case 1:
					int optionpessoa;
					System.out.println("1 - Cadastrar Pessoa\n2 - Atualizar Registro\n3 - Deletar/Desvincular Registro\n4 - Vincular como Usuario ou Funcionario\n5 - Gerenciar Categorias de Usuarios");
					optionpessoa = in.nextInt();
					in.nextLine();
					
					switch(optionpessoa) {
						case 1:
							this.cadastrarPessoa();
							break;
						case 2:
							this.atualizarRegistro();
							break;
						case 3:
							this.deletarRegistro();
							break;
						case 4: 
							this.vinculaRegistro();
							break;
						case 5:
							int optioncat;
							System.out.println("1 - Cadastrar Nova Categoria\n2 - Modificar Categoria\n3 - Remover Categoria");
							optioncat = in.nextInt();
							in.nextLine();
							
							switch(optioncat) {
							case 1:
								this.cadastraCategoria();
								break;
							case 2:
								this.atualizaCategoria();
								break;
							case 3:
								this.deletaCategoria();
								break;
							}
							break;
						default:
							break;
					}
					break;
				case 2:
					int optionuni;
					System.out.println("1 - Cadastrar Universidade\n2 - Modificar Universidade\n3 - Deletar Universidade");
					optionuni = in.nextInt();
					in.nextLine();
					
					switch(optionuni) {
					case 1:
						this.cadastrarUni();
						break;
					case 2:
						this.atualizaUni();
						break;
					case 3:
						this.deletaUni();
						break;
					}
					break;
				case 3:
					int optionedit;
					System.out.println("1 - Cadastrar Editora\n2 - Modificar Editora\n3 - Deletar Editora");
					optionedit = in.nextInt();
					in.nextLine();
					
					switch(optionedit) {
					case 1:
						this.cadastraEditora();
						break;
					case 2:
						this.atualizaEditora();
						break;
					case 3:
						this.deletaEditora();
						break;
					}
					break;
				case 4:
					int optionbook1;
					System.out.println("1 - Gerenciar Livros\n2 - Gerenciar Coleções");
					optionbook1 = in.nextInt();
					in.nextLine();
					
					switch(optionbook1) {
					case 1:
						int optionbook2;
						System.out.println("1 - Cadastrar Livro/Exemplar\n2 - Modificar Livro/Exemplar\n3 - Deletar Livro/Exemplar\n4 - Gerenciar Status Livros");
						optionbook2 = in.nextInt();
						in.nextLine();
						
						switch(optionbook2) {
						case 1:
							this.cadastraLivro();
							break;
						case 2:
							this.atualizaLivro();
							break;
						case 3:
							this.deletaLivro();
							break;
						case 4:
							int optionstatus;
							System.out.println("1 - Criar Novo Status\n2 - Modificar Status\n3 - Deletar Status");
							optionstatus = in.nextInt();
							in.nextLine();
							switch(optionstatus) {
							case 1:
								this.cadastraStatus();
								break;
							case 2:
								this.atualizaStatus();
								break;
							case 3:
								this.deletaStatus();
								break;
							default:
								break;
							}
							break;
						default:
							break;
						}
						break;
					case 2:
						int optioncollect;
						System.out.println("1 - Criar Coleção\n2 - Modificar Coleção\n3 - Deletar Coleção");
						optioncollect = in.nextInt();
						in.nextLine();
						switch(optioncollect) {
						case 1:
							this.cadastraColecao();
							break;
						case 2:
							this.atualizaColecao();
							break;
						case 3:
							this.deletaColecao();
							break;
						default:
							break;
						}
						
						break;
					}
					break;
				}
			break;
			case 2:
				int optionemp;
				System.out.println("1 - Realizar Novo Empréstimo\n2 - Renovação/Devolução\n3 - Remover Empréstimo");
				optionemp = in.nextInt();
				in.nextLine();
				
				switch(optionemp) {
				case 1:
					this.realizarEmprestimo();
					break;
				case 2:
					int optionmod;
					System.out.println("1 - Realizar Entrega\n2 - Renovar Empréstimo");
					optionmod = in.nextInt();
					in.nextLine();
					switch(optionmod) {
					case 1:
						this.realizarEntrega();
						break;
					case 2:
						this.renovarEmprestimo();
						break;
					default:
						break;
					}
					break;
				case 3:
					this.removerEmprestimo();
					break;
				default:
					break;
				}
				
				break;
			case 3:
				int optiondata;
				System.out.println("1 - Listar Dados\n2 - Checar Empréstimo");
				optiondata = in.nextInt();
				in.nextLine();
				
				switch(optiondata) {
				case 1:
					int optionlist;
					System.out.println("1 - Listar Usuários\n2 - Listar Funcionários\n3 - Listar Livros\n4 - Listar Universidades\n5 - Listar Editoras\n6 - Listar Coleções");
					optionlist = in.nextInt();
					in.nextLine();
					switch(optionlist) {
					case 1:
						int optionlistuser;
						System.out.println("1 - Listar Todos Usuários\n2 - Listar Usuários Categoria");
						optionlistuser = in.nextInt();
						in.nextLine();
						switch(optionlistuser) {
						case 1:
							this.listarUsuarios();
							System.out.println();
							break;
						case 2:
							for(Categoria cat : sistema.getCategorias()) {
								System.out.println(cat.toString());
							}
							
							System.out.println("ID da Categoria:");
							this.listarUsuariosCat(in.nextInt());
							System.out.println();
							in.nextLine();
							break;
						default:
							break;
						}
						break;
					case 2:
						int optionlistfunc;
						System.out.println("1 - Listar Todos Funcionários\n2 - Listar Todos Bibliotecários\n3 - Listar Todos Assistentes");
						optionlistfunc = in.nextInt();
						in.nextLine();
						switch(optionlistfunc) {
						case 1:
							this.listarFunc();
							System.out.println();
							break;
						case 2:
							this.listarBibliotecario();
							System.out.println();
							break;
						case 3:
							this.listarAssistente();
							System.out.println();
							break;
						default:
							break;
						}
						break;
					case 3:
						int optionbook;
						System.out.println("1 - Listar Todos Livros\n2 - Listar Exemplares");
						optionbook = in.nextInt();
						in.nextLine();
						
						switch(optionbook) {
						case 1:
							this.listarLivros();
							System.out.println();
							break;
						case 2:
							for(Livro liv : sistema.getLivros()) {
								this.listarExemplares(liv.getIdLiv());
								System.out.println();
							}
							break;
						default:
							break;
						}
						break;
					case 4:
						this.listarUnis();
						System.out.println();
						break;
					case 5:
						this.listarEditoras();
						System.out.println();
						break;
					case 6:
						this.listarColecoes();
						System.out.println();
						break;
					default:
						break;
					}
					break;
				case 2:
					int optionlistemp;
					System.out.println("1 - Todos os Empréstimos\n2 - Empréstimos de Usuário\n3 - Empréstimos de Livro");
					optionlistemp = in.nextInt();
					in.nextLine();
					
					switch(optionlistemp) {
					case 1:
						this.listarEmp();
						break;
					case 2:
						this.listarUsuarios();
						System.out.println("ID do Usuário:");
						
						this.listarEmpUser(in.nextInt());
						in.nextLine();
						System.out.println();
						break;
					case 3:
						this.listarLivros();
						System.out.println("ID do Livro:");
						
						this.listarEmpLivTotal(in.nextInt());
						in.nextLine();
						System.out.println();
						break;
					default:
						break;
					}
					break;
				default:
					break;
				}
				break;
			case 0:
				menu = false;
				break;
			default:
				break;
			}
		}
	}

	
	public void cadastrarPessoa() throws ParseException {
		Pessoa pessoa = new Usuario();
		
		System.out.println("Nome:");
		pessoa.setNome(in.nextLine());
		System.out.println("CPF:");
		pessoa.setCpf(in.nextLine());
		System.out.println("Data Nascimento: (dd/MM/yyyy)");
		String data = in.nextLine();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date dataFormatado = sdf.parse(data);
		pessoa.setDtNasc(dataFormatado);
		System.out.println("Sexo:");
		pessoa.setSexo(in.next().charAt(0));
		in.nextLine();
		System.out.println("Endereço:");
		pessoa.setEndereco(in.nextLine());
		
		int option;
		System.out.println("1 - Cadastrar Pessoa como Usuário\n2 - Cadastrar Pessoa como Funcionário\nOutro - Finalizar");
		option = in.nextInt();
		in.nextLine();
		switch(option) {
		case 1:
			Usuario user = new Usuario();
			this.listarUnis();
			System.out.println("Universidade:");
			try {
				user.setUni(sistema.selecionarUniversidade(sistema.getUnis().get(in.nextInt()-1).getIdUni()));
			} catch (Exception e3) {
				System.out.println("ID não existente.");
				return;
			}
			in.nextLine();
			
			this.listarCategorias();
			System.out.println("ID da Categoria:");
			try {
				user.setCat(sistema.selecionarCategoria(in.nextInt()));
			} catch (Exception e2) {
				System.out.println("ID não existente.");
				return;
			}
			in.nextLine();
	
			try {
				sistema.inserePessoa(pessoa);
			} catch (Exception e) {
				System.out.println("CPF já cadastrado");
				return;
			}
			
			try {
				user.setIdPes(sistema.selecionarPessoaCpf(pessoa.getCpf()).getIdPes());
			} catch (Exception e1) {
				System.out.println("CPF não cadastrado");
				return;
			}
			sistema.insereUsuario(user);
			break;
		case 2:
			int option2;
			Funcionario func = new Funcionario();
			
			System.out.println("CTPS:");
			func.setCtps(in.nextLine());
			System.out.println("Salário:");
			func.setSalario(in.nextDouble());
			in.nextLine();
			System.out.println("Turno:");
			func.setTurno(in.next().charAt(0));
			
			System.out.println();
			System.out.println("1 - Cadastrar como Bibliotecario\n2 - Cadastrar como Assistente\nOutro - Finalizar");
			option2 = in.nextInt();
			in.nextLine();
			switch(option2) {
			case 1:
				Bibliotecario bib = new Bibliotecario();
				
				try {
					sistema.inserePessoa(pessoa);
				} catch (Exception e) {
					System.out.println("CPF já cadastrado");
					return;
				}
				
				try {
					func.setIdPes(sistema.selecionarPessoaCpf(pessoa.getCpf()).getIdPes());
				} catch (Exception e1) {
					System.out.println("CPF não cadastrado");
					return;
				}
				sistema.insereFuncionario(func);
				
				bib.setIdFunc(sistema.selecionarFuncionarioId(func.getIdPes()).getIdFunc());
				sistema.insereBibliotecario(bib);
				break;
			case 2:
				Assistente assist = new Assistente();
				
				this.listarBibliotecario();
				System.out.println("ID do Supervisor:");
				try {
					assist.setSupervisor(sistema.selecionarBibliotecario(in.nextInt()));
				} catch (Exception e) {
					System.out.println("ID não existente.");
					return;
				}
				in.nextLine();
				
				try {
					sistema.inserePessoa(pessoa);
				} catch (Exception e) {
					System.out.println("CPF já cadastrado");
					return;
				}
				
				try {
					func.setIdPes(sistema.selecionarPessoaCpf(pessoa.getCpf()).getIdPes());
				} catch (Exception e1) {
					System.out.println("CPF não cadastrado");
					return;
				}
				sistema.insereFuncionario(func);
				
				assist.setIdFunc(sistema.selecionarFuncionarioId(func.getIdPes()).getIdFunc());
				sistema.insereAssistente(assist);
				break;
			default:
				try {
					sistema.inserePessoa(pessoa);
				} catch (Exception e) {
					System.out.println("CPF já cadastrado");
					return;
				}
				
				try {
					func.setIdPes(sistema.selecionarPessoaCpf(pessoa.getCpf()).getIdPes());
				} catch (Exception e1) {
					System.out.println("CPF não cadastrado");
					return;
				}
				sistema.insereFuncionario(func);
				break;
			}
			break;
		default:
			try {
				sistema.inserePessoa(pessoa);
			} catch (Exception e) {
				System.out.println("CPF já cadastrado");
				return;
			}
			break;
		}
	}
	
	public void atualizarRegistro() {
		int option;
		int select;
		
		System.out.println("1 - Modificar Usuario\n2 - Modificar Funcionario");
		
		option = in.nextInt();
		in.nextLine();
		
		switch(option) {
		case 1:
			System.out.println("Lista de usuários cadastrados: ");
			System.out.println();
			this.listarUsuarios();
			System.out.println();
			System.out.println("Selecionar ID do usuario: ");
			int iduser = in.nextInt();
			in.nextLine();
			Usuario usermod;
			try {
				usermod = sistema.selecionarUsuario(iduser);
			} catch (Exception e) {
				System.out.println("ID não existente.");
				return;
			}
			System.out.println(usermod.toString());
			System.out.println();
			
			System.out.println("Modificar nome? (1 - Sim)");
			select = in.nextInt();
			in.nextLine();
			if(select == 1) {
				System.out.println("Novo nome:");
				usermod.setNome(in.nextLine());
			}
			
			System.out.println("Modificar Sexo? (1 - Sim)");
			select = in.nextInt();
			in.nextLine();
			if(select == 1) {
				System.out.println("Novo sexo:");
				usermod.setSexo(in.next().charAt(0));
			}
			
			System.out.println("Modificar Endereço? (1 - Sim)");
			select = in.nextInt();
			in.nextLine();
			if(select == 1) {
				System.out.println("Novo endereço:");
				usermod.setEndereco(in.nextLine());
			}
			
			System.out.println("Modificar Universidade? (1 - Sim)");
			select = in.nextInt();
			in.nextLine();
			if(select == 1) {
				this.listarUnis();
				
				System.out.println("ID da nova universidade:");
				try {
					usermod.setUni(sistema.selecionarUniversidade(in.nextInt()));
				} catch (Exception e) {
					System.out.println("ID não existente.");
					return;
				}
				in.nextLine();
			}
			
			System.out.println("Modificar Categoria? (1 - Sim)");
			select = in.nextInt();
			in.nextLine();
			if(select == 1) {
				this.listarCategorias();
				
				
				System.out.println("ID da nova categoria:");
				try {
					usermod.setCat(sistema.selecionarCategoria(in.nextInt()));
				} catch (Exception e) {
					System.out.println("ID não existente.");
					return;
				}
				in.nextLine();
			}
			
			sistema.atualizaPessoa(usermod);
			sistema.atualizaUsuario(usermod);
			break;
		case 2:
			int option2;
			System.out.println("1 - Modificar Bibliotecario\n2 - Modificar Assistente");
			option2 = in.nextInt();
			in.nextLine();
			
			switch(option2) {
			case 1:
				System.out.println();
				this.listarBibliotecario();
				System.out.println();
				System.out.println("Selecionar ID do Bibliotecario:");
				int idbib = in.nextInt();
				in.nextLine();
				Bibliotecario bibmod;
				try {
					bibmod = sistema.selecionarBibliotecario(idbib);
				} catch (Exception e) {
					System.out.println("ID não existente.");
					return;
				}
				System.out.println(bibmod.toString());
				
				System.out.println();
				
				System.out.println("Modificar nome? (1 - Sim)");
				select = in.nextInt();
				in.nextLine();
				if(select == 1) {
					System.out.println("Novo nome:");
					bibmod.setNome(in.nextLine());
				}
				
				System.out.println("Modificar Sexo? (1 - Sim)");
				select = in.nextInt();
				in.nextLine();
				if(select == 1) {
					System.out.println("Novo sexo:");
					bibmod.setSexo(in.next().charAt(0));
				}
				
				System.out.println("Modificar Endereço? (1 - Sim)");
				select = in.nextInt();
				in.nextLine();
				if(select == 1) {
					System.out.println("Novo endereço:");
					bibmod.setEndereco(in.nextLine());
				}
				
				System.out.println("Modificar Turno? (1 - Sim)");
				select = in.nextInt();
				in.nextLine();
				if(select == 1) {
					System.out.println("Novo turno:");
					bibmod.setTurno(in.next().charAt(0));
				}
				
				System.out.println("Modificar CTPS? (1 - Sim)");
				select = in.nextInt();
				in.nextLine();
				if(select == 1) {
					System.out.println("Novo CTPS:");
					bibmod.setCtps(in.nextLine());
				}
				
				System.out.println("Modificar Salário? (1 - Sim)");
				select = in.nextInt();
				in.nextLine();
				if(select == 1) {
					System.out.println("Novo salário:");
					bibmod.setSalario(in.nextDouble());
					in.nextLine();
				}
				
				sistema.atualizaPessoa(bibmod);
				sistema.atualizaFuncionario(bibmod);
				break;
			case 2:
				System.out.println();
				this.listarAssistente();
				System.out.println();
				System.out.println("Selecionar ID do Assistente:");
				int idassist = in.nextInt();
				in.nextLine();
				Assistente assistmod;
				try {
					assistmod = sistema.selecionarAssistente(idassist);
				} catch (Exception e1) {
					System.out.println("ID não existente");
					return;
				}
				System.out.println(assistmod.toString());
				
				System.out.println();
				
				System.out.println("Modificar nome? (1 - Sim)");
				select = in.nextInt();
				in.nextLine();
				if(select == 1) {
					System.out.println("Novo nome:");
					assistmod.setNome(in.nextLine());
				}
				
				System.out.println("Modificar Sexo? (1 - Sim)");
				select = in.nextInt();
				in.nextLine();
				if(select == 1) {
					System.out.println("Novo sexo:");
					assistmod.setSexo(in.next().charAt(0));
				}
				
				System.out.println("Modificar Endereço? (1 - Sim)");
				select = in.nextInt();
				in.nextLine();
				if(select == 1) {
					System.out.println("Novo endereço:");
					assistmod.setEndereco(in.nextLine());
				}
				
				System.out.println("Modificar Turno? (1 - Sim)");
				select = in.nextInt();
				in.nextLine();
				if(select == 1) {
					System.out.println("Novo turno:");
					assistmod.setTurno(in.next().charAt(0));
				}
				
				System.out.println("Modificar CTPS? (1 - Sim)");
				select = in.nextInt();
				in.nextLine();
				if(select == 1) {
					System.out.println("Novo CTPS:");
					assistmod.setCtps(in.nextLine());
				}
				
				System.out.println("Modificar Salário? (1 - Sim)");
				select = in.nextInt();
				in.nextLine();
				if(select == 1) {
					System.out.println("Novo salário:");
					assistmod.setSalario(in.nextDouble());
					in.nextLine();
				}
				
				System.out.println("Modificar Salário? (1 - Sim)");
				select = in.nextInt();
				in.nextLine();
				if(select == 1) {
					System.out.println("Novo salário:");
					assistmod.setSalario(in.nextDouble());
					in.nextLine();
				}
				
				System.out.println("Modificar Supervisor? (1 - Sim)");
				select = in.nextInt();
				in.nextLine();
				if(select == 1) {
					this.listarBibliotecario();
					
					System.out.println("ID do novo supervisor:");
					try {
						assistmod.setSupervisor(sistema.selecionarBibliotecario(in.nextInt()));
					} catch (Exception e) {
						System.out.println("ID não existente.");
						return;
					}
					in.nextLine();
				}
				
				sistema.atualizaPessoa(assistmod);
				sistema.atualizaFuncionario(assistmod);
				sistema.atualizaAssistente(assistmod);
				break;
			default:
				break;
			}
		default:
			break;
		}
	}
	
	public void deletarRegistro() {
		int option;
		int confirm;
		
		System.out.println("1 - Desvincular Registro\n2 - Deletar Registro");
		option = in.nextInt();
		in.nextLine();
		
		switch(option) {
		case 1:
			int option2;
			System.out.println();
			System.out.println("1 - Desvincular Usuário\n2 - Desvincular Bibliotecário\n3 - Desvincular Assistente");
			option2 = in.nextInt();
			in.nextLine();
			
			switch(option2) {
			case 1:
				System.out.println();
				this.listarUsuarios();
				System.out.println();
				
				System.out.println("ID do Usuário: ");
				int iduser = in.nextInt();
				try {
					System.out.println(sistema.selecionarUsuario(iduser).toString());
				} catch (Exception e) {
					System.out.println("ID não existente.");
					return;
				}
				System.out.println("Confirmar? (1 - Sim)");
				confirm = in.nextInt();
				in.nextLine();
				if(confirm == 1)
					sistema.deletarUsuario(iduser);
				break;
			case 2:
				System.out.println();
				this.listarBibliotecario();
				System.out.println();
				
				System.out.println("ID do Bibliotecário: ");
				Bibliotecario bib;
				try {
					bib = sistema.selecionarBibliotecario(in.nextInt());
				} catch (Exception e1) {
					System.out.println("ID não existente.");
					return;
				}
				in.nextLine();
				System.out.println(bib.toString());
				System.out.println("Confirmar? (1 - Sim)");
				confirm = in.nextInt();
				in.nextLine();
				if(confirm == 1) {
					sistema.deletarBibliotecario(bib.getIdBib());
					sistema.deletarFuncionario(bib.getIdFunc());
				}
				break;
			case 3:
				System.out.println();
				this.listarAssistente();
				System.out.println();
				
				System.out.println("ID do Assistente: ");
				Assistente assist;
				try {
					assist = sistema.selecionarAssistente(in.nextInt());
				} catch (Exception e1) {
					System.out.println("ID não existente");
					return;
				}
				in.nextLine();
				System.out.println(assist.toString());
				System.out.println("Confirmar? (1 - Sim)");
				confirm = in.nextInt();
				in.nextLine();
				if(confirm == 1) {
					sistema.deletarAssistente(assist.getIdAssist());
					sistema.deletarFuncionario(assist.getIdFunc());
				}
				break;
			}
			break;
		case 2:
			int option3;
			System.out.println("1 - Deletar Usuário\n2 - Deletar Bibliotecário\n3 - Deletar Assistente\n4 - Deletar Pessoa não Vinculada");
			option3 = in.nextInt();
			in.nextLine();
			
			switch(option3) {
			case 1:
				System.out.println();
				this.listarUsuarios();
				System.out.println();
				
				System.out.println("ID do Usuário: ");
				Usuario user;
				try {
					user = sistema.selecionarUsuario(in.nextInt());
				} catch (Exception e) {
					System.out.println("ID não existente.");
					return;
				}
				in.nextLine();
				System.out.println(user.toString());
				System.out.println("Confirmar? (1 - Sim)");
				confirm = in.nextInt();
				in.nextLine();
				if(confirm == 1) {
					sistema.deletarUsuario(user.getIdUser());
					sistema.deletarPessoa(user.getIdPes());
				}
				break;
			case 2:
				System.out.println();
				this.listarBibliotecario();
				System.out.println();
				
				System.out.println("ID do Bibliotecário: ");
				Bibliotecario bib;
				try {
					bib = sistema.selecionarBibliotecario(in.nextInt());
				} catch (Exception e) {
					System.out.println("ID não existente.");
					return;
				}
				in.nextLine();
				System.out.println(bib.toString());
				System.out.println("Confirmar? (1 - Sim)");
				confirm = in.nextInt();
				in.nextLine();
				if(confirm == 1) {
					sistema.deletarBibliotecario(bib.getIdBib());
					sistema.deletarFuncionario(bib.getIdFunc());
					sistema.deletarPessoa(bib.getIdPes());
				}
				break;
			case 3:
				System.out.println();
				this.listarAssistente();
				System.out.println();
				
				System.out.println("ID do Assistente: ");
				Assistente assist;
				try {
					assist = sistema.selecionarAssistente(in.nextInt());
				} catch (Exception e) {
					System.out.println("ID não existente");
					return;
				}
				in.nextLine();
				System.out.println(assist.toString());
				System.out.println("Confirmar? (1 - Sim)");
				confirm = in.nextInt();
				in.nextLine();
				if(confirm == 1) {
					sistema.deletarAssistente(assist.getIdAssist());
					sistema.deletarFuncionario(assist.getIdFunc());
					sistema.deletarPessoa(assist.getIdPes());
				}
				break;
			case 4:
				System.out.println();
				this.listarPessoasOnly();
				System.out.println();
				
				System.out.println("ID da Pessoa: ");
				int idpes = in.nextInt();
				in.nextLine();
				try {
					System.out.println(sistema.selecionarPessoa(idpes).toString());
				} catch (Exception e) {
					System.out.println("ID não existente.");
					return;
				}
				System.out.println("Confirmar? (1 - Sim)");
				confirm = in.nextInt();
				in.nextLine();
				if(confirm == 1) {
					sistema.deletarPessoa(idpes);
				}
				
				
			}
		}
	}
	
	public void vinculaRegistro() {
		int option;
		
		System.out.println("1 - Vincular Usuário\n2 - Vincular Funcionário\n3 - Vincular Bibliotecário\n4 - Vincular Assistente");
		System.out.println();
		option = in.nextInt();
		in.nextLine();
		
		switch(option) {
		case 1:
			System.out.println();
			this.listarPessoasNotUser();
			System.out.println();
			
			System.out.println("ID da Pessoa: ");
			Pessoa pessoaUser;
			try {
				pessoaUser = sistema.selecionarPessoa(in.nextInt());
			} catch (Exception e1) {
				System.out.println("ID não existente.");
				return;
			}
			in.nextLine();
			Usuario user = new Usuario();
			user.setNome(pessoaUser.getNome());
			user.setCpf(pessoaUser.getCpf());
			user.setDtNasc(pessoaUser.getDtNasc());
			user.setEndereco(pessoaUser.getEndereco());
			user.setIdPes(pessoaUser.getIdPes());
			user.setSexo(pessoaUser.getSexo());
			
			this.listarUnis();
			System.out.println("ID da Universidade:");
			try {
				user.setUni(sistema.selecionarUniversidade(in.nextInt()));
			} catch (Exception e3) {
				System.out.println("ID não existente.");
				return;
			}
			in.nextLine();
			
			this.listarCategorias();
			System.out.println("ID da Categoria:");
			try {
				user.setCat(sistema.selecionarCategoria(in.nextInt()));
			} catch (Exception e2) {
				System.out.println("ID não existente.");
				return;
			}
			in.nextLine();
	
			sistema.insereUsuario(user);
			break;
		case 2:
			System.out.println();
			this.listarPessoasNotFunc();
			System.out.println();
			
			System.out.println("ID da Pessoa: ");
			Pessoa pessoaFunc;
			try {
				pessoaFunc = sistema.selecionarPessoa(in.nextInt());
			} catch (Exception e1) {
				System.out.println("ID não existente.");
				return;
			}
			in.nextLine();
			Funcionario func = new Funcionario();
			func.setNome(pessoaFunc.getNome());
			func.setCpf(pessoaFunc.getCpf());
			func.setDtNasc(pessoaFunc.getDtNasc());
			func.setEndereco(pessoaFunc.getEndereco());
			func.setIdPes(pessoaFunc.getIdPes());
			func.setSexo(pessoaFunc.getSexo());
			
			System.out.println("CTPS:");
			func.setCtps(in.nextLine());
			System.out.println("Salário:");
			func.setSalario(in.nextDouble());
			in.nextLine();
			System.out.println("Turno:");
			func.setTurno(in.next().charAt(0));
			
			sistema.insereFuncionario(func);
			break;
		case 3:
			System.out.println();
			this.listarFuncSemCargo();
			System.out.println();
			
			System.out.println("ID do Funcionario: ");
			Funcionario funcBib;
			try {
				funcBib = sistema.selecionarFuncionario(in.nextInt());
			} catch (Exception e1) {
				System.out.println("ID não existente.");
				return;
			}
			in.nextLine();
			Bibliotecario bib = new Bibliotecario();

			bib.setNome(funcBib.getNome());
			bib.setCpf(funcBib.getCpf());
			bib.setDtNasc(funcBib.getDtNasc());
			bib.setEndereco(funcBib.getEndereco());
			bib.setIdPes(funcBib.getIdPes());
			bib.setSexo(funcBib.getSexo());
			
			bib.setCtps(funcBib.getCtps());
			bib.setSalario(funcBib.getSalario());
			bib.setTurno(funcBib.getTurno());
			bib.setIdFunc(funcBib.getIdFunc());
			
			sistema.insereBibliotecario(bib);
			break;
		case 4:
			System.out.println();
			this.listarFuncSemCargo();
			System.out.println();
			
			System.out.println("ID do Funcionario: ");
			Funcionario funcAssist;
			try {
				funcAssist = sistema.selecionarFuncionario(in.nextInt());
			} catch (Exception e1) {
				System.out.println("ID não existente.");
				return;
			}
			in.nextLine();
			Assistente assist = new Assistente();

			assist.setNome(funcAssist.getNome());
			assist.setCpf(funcAssist.getCpf());
			assist.setDtNasc(funcAssist.getDtNasc());
			assist.setEndereco(funcAssist.getEndereco());
			assist.setIdPes(funcAssist.getIdPes());
			assist.setSexo(funcAssist.getSexo());
			
			assist.setCtps(funcAssist.getCtps());
			assist.setSalario(funcAssist.getSalario());
			assist.setTurno(funcAssist.getTurno());
			assist.setIdFunc(funcAssist.getIdFunc());
			
			this.listarBibliotecario();
			System.out.println("ID do Supervisor:");
			try {
				assist.setSupervisor(sistema.selecionarBibliotecario(in.nextInt()));
			} catch (Exception e) {
				System.out.println("ID não existente.");
				return;
			}
			in.nextLine();
			sistema.insereAssistente(assist);
			break;
		default:
			break;			
		}
		
	}
	
	public void cadastrarUni() {
		Universidade uni = new Universidade();
		
		System.out.println("Nome da Universidade: ");
		uni.setNome(in.nextLine());
		System.out.println("Sigla da Universidade: ");
		uni.setSigla(in.nextLine());
		
		sistema.insereUniversidade(uni);
	}
	
	public void atualizaUni() {
		int select;
		
		System.out.println();
		this.listarUnis();
		System.out.println();
		
		System.out.println("ID da Universidade: ");
		Universidade unimod;
		try {
			unimod = sistema.selecionarUniversidade(in.nextInt());
		} catch (Exception e) {
			System.out.println("ID não existente.");
			return;
		}
		in.nextLine();

		System.out.println("Modificar nome? (1 - Sim)");
		select = in.nextInt();
		in.nextLine();
		if(select == 1) {
			System.out.println("Novo nome:");
			unimod.setNome(in.nextLine());
		}
		
		System.out.println("Modificar Sigla? (1 - Sim)");
		select = in.nextInt();
		in.nextLine();
		if(select == 1) {
			System.out.println("Nova sigla:");
			unimod.setSigla(in.nextLine());
		}
		
		sistema.atualizaUniversidade(unimod);
	}
	
	private void deletaUni() {
		int confirm;
		
		System.out.println();
		this.listarUnis();
		System.out.println();
		
		System.out.println("ID da Universidade: ");
		Universidade uni;
		try {
			uni = sistema.selecionarUniversidade(in.nextInt());
		} catch (Exception e1) {
			System.out.println("ID não existente");
			return;
		}
		in.nextLine();
		
		System.out.println(uni.toString());
		System.out.println("Confirmar? (1 - Sim)");
		confirm = in.nextInt();
		in.nextLine();
		if(confirm == 1)
			try {
				sistema.deletarUniversidade(uni.getIdUni());
			} catch (Exception e) {
				System.out.println("Universidade vinculada a algum usuário");
				return;
			}
	}
	
	private void cadastraEditora() {
		Editora edit = new Editora();
		
		System.out.println("Nome: ");
		edit.setNome(in.nextLine());
		System.out.println("CNPJ: ");
		edit.setCnpj(in.nextLine());
		
		try {
			sistema.insereEditora(edit);
		} catch (Exception e) {
			System.out.println("CNPJ já inserido");
			return;
		}
	}
	
	private void atualizaEditora() {
		int select;
		
		System.out.println();
		this.listarEditoras();
		System.out.println();
		
		System.out.println("ID da Editora: ");
		Editora editmod;
		try {
			editmod = sistema.selecionarEditora(in.nextInt());
		} catch (Exception e) {
			System.out.println("ID não existente.");
			return;
		}
		in.nextLine();

		System.out.println("Modificar nome? (1 - Sim)");
		select = in.nextInt();
		in.nextLine();
		if(select == 1) {
			System.out.println("Novo nome:");
			editmod.setNome(in.nextLine());
		}
		
		System.out.println("Modificar CNPJ? (1 - Sim)");
		select = in.nextInt();
		in.nextLine();
		if(select == 1) {
			System.out.println("Novo CNPJ:");
			editmod.setCnpj(in.nextLine());
		}
		
		sistema.atualizaEditora(editmod);
	}
	
	private void deletaEditora() {
		int confirm;
		
		System.out.println();
		this.listarEditoras();
		System.out.println();
		
		System.out.println("ID da Editora: ");
		int idedit = in.nextInt();
		in.nextLine();
		try {
			System.out.println(sistema.selecionarEditora(idedit).toString());
		} catch (Exception e) {
			System.out.println("ID não existente.");
			return;
		}
		System.out.println("Confirmar? (1 - Sim)");
		confirm = in.nextInt();
		in.nextLine();
		if(confirm == 1)
			sistema.deletarEditora(idedit);
	}
	
	public void cadastraColecao() {
		Colecao col = new Colecao();
		
		System.out.println("Nome:");
		col.setNome(in.nextLine());
		
		sistema.insereColecao(col);
	}
	
	public void atualizaColecao() {
		int select;
		
		System.out.println();
		this.listarColecoes();
		System.out.println();
		
		System.out.println("ID da Coleção: ");
		Colecao colmod;
		try {
			colmod = sistema.selecionarColecao(in.nextInt());
		} catch (Exception e) {
			System.out.println("ID não existente.");
			return;
		}
		in.nextLine();

		System.out.println("Modificar nome? (1 - Sim)");
		select = in.nextInt();
		in.nextLine();
		if(select == 1) {
			System.out.println("Novo nome:");
			colmod.setNome(in.nextLine());
		}
		
		sistema.atualizaColecao(colmod);
	}
	
	public void deletaColecao() {
		int confirm;
		
		System.out.println();
		this.listarColecoes();
		System.out.println();
		
		System.out.println("ID da Coleção: ");
		int idcol = in.nextInt();
		in.nextLine();
		try {
			System.out.println(sistema.selecionarColecao(idcol).toString());
		} catch (Exception e) {
			System.out.println("ID não existente.");
			return;
		}
		System.out.println("Confirmar? (1 - Sim)");
		confirm = in.nextInt();
		in.nextLine();
		if(confirm == 1)
			sistema.deletarColecao(idcol);		
	}
	
	public void cadastraLivro() {
		int option;
		System.out.println("1 - Cadastrar Novo Livro\n2 - Cadastrar Novos Exemplares");
		option = in.nextInt();
		in.nextLine();
		
		switch(option) {
		case 1:
			Livro liv = new Livro();
			
			System.out.println("Título:");
			liv.setTitulo(in.nextLine());
			System.out.println("Autor:");
			liv.setAutor(in.nextLine());
			System.out.println("ISBN:");
			liv.setIsbn(in.nextLine());
			
			try {
				sistema.insereLivro(liv);
			} catch (Exception e) {
				System.out.println("ISBN já cadastrado");
				return;
			}
			
			System.out.println("Editoras Cadastradas:");
			this.listarEditoras();
			System.out.println();
			
			System.out.println("Número de Editoras:");
			int limit = in.nextInt();
			in.nextLine();
			ArrayList<Editora> lista = new ArrayList<Editora>();
			for(int i = 0; i < limit; i++) {
				System.out.println("ID da Editora:");
				try {
					lista.add(sistema.selecionarEditora(in.nextInt()));
				} catch (Exception e) {
					System.out.println("ID não existente.");
					return;
				}
				in.nextLine();
			}
			
			liv.setEditoras(lista);
			try {
				liv.setIdLiv(sistema.selecionarLivroIsbn(liv.getIsbn()).getIdLiv());
			} catch (Exception e2) {
				System.out.println("ISBN não existente.");
				return;
			}
			sistema.inserePublicado(liv);
			
			int continuar;
			System.out.println("Deseja cadastrar exemplares? (1 - Sim)");
			continuar = in.nextInt();
			in.nextLine();
			if(continuar == 1) {
				int count;
				System.out.println("Quantos exemplares?");
				count = in.nextInt();
				in.nextLine();
				for(int i=0; i<count; i++) {
					Exemplar ex = new Exemplar();
					Livro livEx;
					try {
						livEx = sistema.selecionarLivroIsbn(liv.getIsbn());
					} catch (Exception e) {
						System.out.println("ISBN não encontrado.");
						return;
					}
					
					ex.setIdLiv(livEx.getIdLiv());
					ex.setTitulo(livEx.getTitulo());
					ex.setAutor(livEx.getAutor());
					ex.setIsbn(livEx.getIsbn());
					ex.setEditoras(livEx.getEditoras());
					
					System.out.println("Coleções Cadastradas:\n");
					this.listarColecoes();
					System.out.println();
					
					System.out.println("ID da Coleção do Exemplar: ");
					try {
						ex.setCol(sistema.selecionarColecao(in.nextInt()));
					} catch (Exception e1) {
						System.out.println("ID não existente.");
						return;
					}
					in.nextLine();
					
					System.out.println("Status Cadastrados:\n");
					this.listarStatus();
					System.out.println();
					
					System.out.println("ID do Status do Exemplar: ");
					try {
						ex.setStat(sistema.selecionarStatus(in.nextInt()));
					} catch (Exception e) {
						System.out.println("ID não existente.");
						return;
					}
					in.nextLine();	
					sistema.insereExemplar(ex);
				}
			}
			break;
		case 2:
			System.out.println("Livros Cadastrados:");
			this.listarLivros();
			System.out.println();
			
			System.out.println("ID do Livro: ");
			Livro livex;
			try {
				livex = sistema.selecionarLivro(in.nextInt());
			} catch (Exception e) {
				System.out.println("ID não existente");
				return;
			}
			in.nextLine();
			
			int count;
			System.out.println("Quantos exemplares?");
			count = in.nextInt();
			in.nextLine();
			for(int i=0; i<count; i++) {
				Exemplar ex = new Exemplar();
				
				ex.setIdLiv(livex.getIdLiv());
				ex.setTitulo(livex.getTitulo());
				ex.setAutor(livex.getAutor());
				ex.setIsbn(livex.getIsbn());
				ex.setEditoras(livex.getEditoras());
				
				System.out.println("Coleções Cadastradas:\n");
				this.listarColecoes();
				System.out.println();
				
				System.out.println("ID da Coleção do Exemplar: ");
				try {
					ex.setCol(sistema.selecionarColecao(in.nextInt()));
					in.nextLine();
				} catch (Exception e1) {
					System.out.println("ID não existente.");
					return;
				}
				
				System.out.println("Status Cadastrados:\n");
				this.listarStatus();
				System.out.println();
				
				System.out.println("ID do Status do Exemplar: ");
				try {
					ex.setStat(sistema.selecionarStatus(in.nextInt()));
					in.nextLine();	
				} catch (Exception e) {
					System.out.println("ID não existente.");
					return;
				}
				
				sistema.insereExemplar(ex);
			}
			break;
		}
	}
	
	public void atualizaLivro() {
		int option;
		int select;
		
		System.out.println();
		System.out.println("1 - Modificar Livro\n2 - Modificar Exemplar");
		option = in.nextInt();
		in.nextLine();
		
		switch(option) {
		case 1:
			System.out.println();
			this.listarLivros();
			System.out.println();
			System.out.println("ID do Livro:");
			Livro livmod;
			try {
				livmod = sistema.selecionarLivro(in.nextInt());
				in.nextLine();
			} catch (Exception e) {
				System.out.println("ID não existente");
				return;
			}
			
			System.out.println("Modificar título? (1 - Sim)");
			select = in.nextInt();
			in.nextLine();
			if(select == 1) {
				System.out.println("Novo título:");
				livmod.setTitulo(in.nextLine());
			}
			
			System.out.println("Modificar Autor? (1 - Sim)");
			select = in.nextInt();
			in.nextLine();
			if(select == 1) {
				System.out.println("Novo autor:");
				livmod.setAutor(in.nextLine());
			}
			
			System.out.println("Modificar ISBN? (1 - Sim)");
			select = in.nextInt();
			in.nextLine();
			if(select == 1) {
				System.out.println("Novo ISBN:");
				livmod.setIsbn(in.nextLine());
			}
			
			sistema.atualizaLivro(livmod);
			break;
		case 2:
			System.out.println("Lista de Livros:");
			this.listarLivros();
			System.out.println();
			System.out.println("ID do Livro:");
			Livro livref;
			try {
				livref = sistema.selecionarLivro(in.nextInt());
				in.nextLine();
			} catch (Exception e) {
				System.out.println("ID não existente");
				return;
			}
			
			System.out.println("Lista de Exemplares do Livro " + livref.getTitulo() + " :");
			this.listarExemplares(livref.getIdLiv());
			System.out.println();
			
			System.out.println("ID do Exemplar:");
			Exemplar exmod;
			try {
				exmod = sistema.selecionarExemplar(livref.getIdLiv(), in.nextInt());
				in.nextLine();
			} catch (Exception e) {
				System.out.println("ID não existente");
				return;
			}
			
			System.out.println("Modificar Status? (1 - Sim)");
			select = in.nextInt();
			in.nextLine();
			if(select == 1) {
				System.out.println();
				System.out.println("Status Cadastrados:");
				this.listarStatus();
				
				System.out.println("ID do novo Status: ");
				try {
					exmod.setStat(sistema.selecionarStatus(in.nextInt()));
				} catch (Exception e) {
					System.out.println("ID não existente.");
					return;
				}
				in.nextLine();
			}
			
			System.out.println("Modificar Coleção? (1 - Sim)");
			select = in.nextInt();
			in.nextLine();
			if(select == 1) {
				System.out.println();
				System.out.println("Coleções Cadastradas:");
				this.listarColecoes();
				
				System.out.println("ID da nova Coleção: ");
				try {
					exmod.setCol(sistema.selecionarColecao(in.nextInt()));
				} catch (Exception e) {
					System.out.println("ID não existente.");
					return;
				}
				in.nextLine();
			}
			
			sistema.atualizaExemplar(exmod);
			break;
		default:
			break;
		}
	}
	
	public void deletaLivro() {
		int option;
		int confirm;
		
		System.out.println();
		System.out.println("1 - Deletar Livro\n2 - Deletar Exemplar");
		option = in.nextInt();
		in.nextLine();
		
		switch(option) {
		case 1:
			System.out.println();
			this.listarLivros();
			System.out.println();
			
			System.out.println("ID do Livro: ");
			int idliv = in.nextInt();
			in.nextLine();
			try {
				System.out.println(sistema.selecionarLivro(idliv).toString());
			} catch (Exception e1) {
				System.out.println("ID não existente");
				return;
			}
			System.out.println("Confirmar? (1 - Sim)");
			confirm = in.nextInt();
			in.nextLine();
			if(confirm == 1) {
				Livro liv = new Livro();
				try {
					liv = sistema.selecionarLivro(idliv);
				} catch (Exception e1) {
					System.out.println("ID não existente.");
					return;
				}
				for(Editora edi : liv.getEditoras()) {
					sistema.deletarPublicado(liv.getIdLiv(), edi.getIdEdi());
				}
				for(Exemplar ex : sistema.getExemplares()) {
					if(liv.getIdLiv() == ex.getIdLiv())
						sistema.deletarExemplar(idliv, ex.getIdEx());
				}
				sistema.deletarLivro(idliv);	
			}
			break;
		case 2:
			System.out.println("Lista de Livros:");
			this.listarLivros();
			System.out.println();
			System.out.println("ID do Livro:");
			Livro livref;
			try {
				livref = sistema.selecionarLivro(in.nextInt());
			} catch (Exception e) {
				System.out.println("ID não existente");
				return;
			}
			in.nextLine();
			
			System.out.println("Lista de Exemplares do Livro " + livref.getTitulo() + " :");
			this.listarExemplares(livref.getIdLiv());
			System.out.println();
			
			System.out.println("ID do Exemplar:");
			int idex = in.nextInt();
			in.nextLine();
			try {
				System.out.println(sistema.selecionarExemplar(livref.getIdLiv(), idex).toString());
			} catch (Exception e) {
				System.out.println("ID não existente");
				return;
			}
			System.out.println("Confirmar? (1 - Sim)");
			confirm = in.nextInt();
			in.nextLine();
			if(confirm == 1)
				sistema.deletarExemplar(livref.getIdLiv(), idex);
		}
	}
	
	public void cadastraStatus() {
		Status status = new Status();
		
		System.out.println("Nome:");
		status.setNome(in.nextLine());
		
		sistema.insereStatus(status);
	}
	
	public void atualizaStatus() {
		int select;
		
		System.out.println();
		this.listarStatus();
		System.out.println();
		
		System.out.println("ID do Status: ");
		Status statusmod;
		try {
			statusmod = sistema.selecionarStatus(in.nextInt());
		} catch (Exception e) {
			System.out.println("ID não existente.");
			return;
		}
		in.nextLine();

		System.out.println("Modificar nome? (1 - Sim)");
		select = in.nextInt();
		in.nextLine();
		if(select == 1) {
			System.out.println("Novo nome:");
			statusmod.setNome(in.nextLine());
		}
		
		sistema.atualizaStatus(statusmod);
	}
	
	public void deletaStatus() {
		int confirm;
		
		System.out.println();
		this.listarStatus();
		System.out.println();
		
		System.out.println("ID do Status: ");
		int idstatus = in.nextInt();
		in.nextLine();
		try {
			System.out.println(sistema.selecionarStatus(idstatus).toString());
		} catch (Exception e) {
			System.out.println("ID não existente.");
			return;
		}
		System.out.println("Confirmar? (1 - Sim)");
		confirm = in.nextInt();
		in.nextLine();
		if(confirm == 1) {
			try {
				sistema.deletarStatus(idstatus);
			} catch (Exception e) {
				System.out.println("Status vinculado a algum livro");
				return;
			}	
		}
	}
	
	public void cadastraCategoria() {
		Categoria cat = new Categoria();
		
		System.out.println("Nome: ");
		cat.setNome(in.nextLine());
		System.out.println("Tempo de Empréstimo: ");
		System.out.println("Anos:");
		int ano = in.nextInt();
		System.out.println("Meses:");
		int mes = in.nextInt();
		System.out.println("Dias:");
		int dia = in.nextInt();
		System.out.println("Horas:");
		int horas = in.nextInt();
		System.out.println("Minutos:");
		int minutos = in.nextInt();
		System.out.println("Segundos:");
		int segundos = in.nextInt();
		PGInterval tempo = new PGInterval(ano,mes,dia,horas,minutos,segundos);
		cat.setTempo(tempo);
		
		sistema.insereCategoria(cat);
	}
	
	public void atualizaCategoria() {
		int select;
		
		System.out.println();
		this.listarCategorias();
		System.out.println();
		
		System.out.println("ID da Categoria: ");
		Categoria catmod;
		try {
			catmod = sistema.selecionarCategoria(in.nextInt());
		} catch (Exception e) {
			System.out.println("ID não existente.");
			return;
		}
		in.nextLine();

		System.out.println("Modificar nome? (1 - Sim)");
		select = in.nextInt();
		in.nextLine();
		if(select == 1) {
			System.out.println("Novo nome:");
			catmod.setNome(in.nextLine());
		}
		
		System.out.println("Modificar tempo de empréstimo? (1 - Sim)");
		select = in.nextInt();
		in.nextLine();
		if(select == 1) {
			System.out.println("Novo tempo:");
			System.out.println("Anos:");
			int ano = in.nextInt();
			System.out.println("Meses:");
			int mes = in.nextInt();
			System.out.println("Dias:");
			int dia = in.nextInt();
			System.out.println("Horas:");
			int horas = in.nextInt();
			System.out.println("Minutos:");
			int minutos = in.nextInt();
			System.out.println("Segundos:");
			int segundos = in.nextInt();
			PGInterval tempo = new PGInterval(ano,mes,dia,horas,minutos,segundos);
			catmod.setTempo(tempo);
		}
		
		sistema.atualizaCategoria(catmod);
	}
	
	public void deletaCategoria() {
		int confirm;
		
		System.out.println();
		this.listarCategorias();
		System.out.println();
		
		System.out.println("ID da Categoria: ");
		int idcat = in.nextInt();
		in.nextLine();
		try {
			System.out.println(sistema.selecionarCategoria(idcat).toString());
		} catch (Exception e) {
			System.out.println("ID não existente.");
			return;
		}
		System.out.println("Confirmar? (1 - Sim)");
		confirm = in.nextInt();
		in.nextLine();
		if(confirm == 1) {
			try {
				sistema.deletarCategoria(idcat);
			} catch (Exception e) {
				System.out.println("Categoria vinculada a algum usuário");
				return;
			}
		}
	}
	
	public void realizarEmprestimo() {
		Emprestimo emp = new Emprestimo();
		int reserva;
		
		System.out.println("Lista de Usuário:");
		this.listarUsuarios();
		System.out.println();
		
		System.out.println("ID do Usuário:");
		try {
			emp.setUser(sistema.selecionarUsuario(in.nextInt()));
		} catch (Exception e1) {
			System.out.println("ID não existente.");
			return;
		}
		in.nextLine();
		
		System.out.println("Lista de Bibliotecários:");
		this.listarBibliotecario();
		System.out.println();
		
		System.out.println("ID do Bibliotecário:");
		try {
			emp.setBib(sistema.selecionarBibliotecario(in.nextInt()));
		} catch (Exception e1) {
			System.out.println("ID não existente.");
			return;
		}
		in.nextLine();
		
		System.out.println("Lista de Livros:");
		this.listarLivros();
		System.out.println();
		
		System.out.println("ID do Livro:");
		int idliv = in.nextInt();
		in.nextLine();
		try {
			System.out.println("Lista de Exemplares de " + sistema.selecionarLivro(idliv) + ":");
		} catch (Exception e) {
			System.out.println("ID não existente");
			return;
		}
		if(sistema.listarExDisp(idliv).isEmpty()) {
			System.out.println("Sem exemplares disponíveis, escolher um exemplar para agendar:");
			this.listarEmpLiv(idliv);
			reserva = 1;
		}
		else { 
			this.listarExemplaresDisp(idliv);
			System.out.println(); 
			reserva = 0;
		}
		
		if(reserva == 0) {
			System.out.println("ID do Exemplar:");
			try {
				emp.setEx(sistema.selecionarExemplar(idliv, in.nextInt()));
			} catch (Exception e) {
				System.out.println("ID não existente");
				return;
			}
			in.nextLine();
			
			emp.setRenov(0);
			
			Date data = new Date();
			emp.setDiaEmprestimo(data);	
			emp.setDiaEntrega(null);
			
			try {
				sistema.insereEmprestimo(emp);
			} catch (Exception e) {
				System.out.println("Usuário possui livro atrasado");
				return;
			}
			
			for(Emprestimo emprestimo : sistema.getEmprestimos()) {
				java.sql.Date compareDate = new java.sql.Date(emp.getDiaEmprestimo().getTime());
				if(emprestimo.getEx().getIdEx() == emp.getEx().getIdEx() && emprestimo.getUser().getIdUser() == emp.getUser().getIdUser() && emprestimo.getBib().getIdBib() == emp.getBib().getIdBib() && compareDate.compareTo(emp.getDiaEmprestimo()) == 0) {
					for(Status stat : sistema.getStatus()) {
						if(stat.getNome().equals("Emprestado")) {
							emp.getEx().setStat(stat);
							sistema.atualizaExemplar(emp.getEx());
						}
					}
				}
			}
		} else if (reserva == 1) {
			System.out.println("ID do Emprestimo:");
			int idemp = in.nextInt();
			in.nextLine();
			try {
				emp.setEx(sistema.selecionarEmprestimo(idemp).getEx());
			} catch (Exception e) {
				System.out.println("ID não existente");
				return;
			}
			
			emp.setRenov(0);
			
			Date data;
			try {
				data = sistema.selecionarEmprestimo(idemp).limiteEntrega();
			} catch (Exception e1) {
				System.out.println("ID não existente.");
				return;
			}
			emp.setDiaEmprestimo(data);	
			emp.setDiaEntrega(null);
			
			try {
				sistema.insereEmprestimo(emp);
			} catch (Exception e) {
				System.out.println("Usuário possui livro atrasado");
				return;
			}
			
			for(Emprestimo emprestimo : sistema.getEmprestimos()) {
				java.sql.Date compareDate = new java.sql.Date(emp.getDiaEmprestimo().getTime());
				if(emprestimo.getEx().getIdEx() == emp.getEx().getIdEx() && emprestimo.getUser().getIdUser() == emp.getUser().getIdUser() && emprestimo.getBib().getIdBib() == emp.getBib().getIdBib() && compareDate.compareTo(emp.getDiaEmprestimo()) == 0) {
					for(Status stat : sistema.getStatus()) {
						if(stat.getNome().equals("Reservado")) {
							emp.getEx().setStat(stat);
							sistema.atualizaExemplar(emp.getEx());
						}
					}
				}
			}
		}
		
	}
	
	public void realizarEntrega() {
		int optiondelivery;
		int available = 1;
		System.out.println("Selecionar Empréstimo:\n1 - Por Usuário\n2 - Por Livro\n3 - Por Empréstimo");
		System.out.println();
		
		optiondelivery = in.nextInt();
		in.nextLine();
		switch(optiondelivery) {
		case 1:
			System.out.println("Lista de Usuários com Empréstimo:");
			this.listarUserEmp();
			System.out.println();
			
			System.out.println("ID do Usuário:");
			this.listarUserEnt(in.nextInt());
			in.nextLine();
			
			System.out.println("ID do Empréstimo:");
			Emprestimo empuser;
			try {
				empuser = sistema.selecionarEmprestimo(in.nextInt());
			} catch (Exception e) {
				System.out.println("ID não existente.");
				return;
			}
			in.nextLine();
			
			if(!empuser.getUser().getCat().getNome().equals("Professor") && !empuser.getUser().getCat().getNome().equals("Professor Pós-Grad")) {
				Date dateuser = new Date();
				if(dateuser.compareTo(empuser.limiteEntrega()) == 1) {
					long diffMili = Math.abs(empuser.limiteEntrega().getTime() - dateuser.getTime());
					long diff = TimeUnit.DAYS.convert(diffMili, TimeUnit.MILLISECONDS);
					
					System.out.println("Valor da Multa: R$0,50/dia");
					System.out.println("Total da Multa:" + diff*0.5 + ".");
					System.out.println("Confirmar? (1 - Sim)");
					int confirm;
					confirm = in.nextInt();
					in.nextLine();
					if(confirm == 1) {
						empuser.setDiaEntrega(dateuser);
					} else {
						empuser.setDiaEntrega(null);
					}
				} else {
					empuser.setDiaEntrega(dateuser);
				}
			} else {
				Date dateuser = new Date();
				empuser.setDiaEntrega(dateuser);
			}
			
			try {
				sistema.atualizaEmprestimo(empuser);
			} catch (Exception e2) {
				System.out.println("Limite de Renovações");
				return;
			}
			
			for(Emprestimo emprestimo : sistema.getEmprestimos()) {
				if(emprestimo.getEx().getIdEx() == empuser.getEx().getIdEx() && emprestimo.getEx().getIdLiv() == empuser.getEx().getIdLiv() && emprestimo.getDiaEntrega() == null) {
					available = 0;
				}
			}
			
			if(available == 1) {
				for(Status stat : sistema.getStatus()) {
					if(stat.getNome().equals("Disponível")) {
						empuser.getEx().setStat(stat);
						sistema.atualizaExemplar(empuser.getEx());
					}
				}
			}
			break;
		case 2:
			System.out.println("Lista de Livros com Empréstimo:");
			this.listarLivEmp();
			System.out.println();
			
			System.out.println("ID do Livro:");
			this.listarLivEnt(in.nextInt());
			in.nextLine();
			
			System.out.println("ID do Empréstimo:");
			Emprestimo empliv;
			try {
				empliv = sistema.selecionarEmprestimo(in.nextInt());
			} catch (Exception e) {
				System.out.println("ID não existente.");
				return;
			}
			in.nextLine();
			
			if(empliv.getUser().getCat().getNome() != "Professor" && empliv.getUser().getCat().getNome() != "Professor Pós-Grad") {
				Date dateliv = new Date();
				if(dateliv.compareTo(empliv.limiteEntrega()) == 1) {
					long diffMili = Math.abs(empliv.limiteEntrega().getTime() - dateliv.getTime());
					long diff = TimeUnit.DAYS.convert(diffMili, TimeUnit.MILLISECONDS);
					
					System.out.println("Valor da Multa: R$0,50/dia");
					System.out.println("Total da Multa:" + diff*0.5 + ".");
					System.out.println("Confirmar? (1 - Sim)");
					int confirm;
					confirm = in.nextInt();
					in.nextLine();
					if(confirm == 1) {
						empliv.setDiaEntrega(dateliv);
					} else {
						empliv.setDiaEntrega(null);
					}
				} else {
					empliv.setDiaEntrega(dateliv);
				}
			} else {
				Date dateliv = new Date();
				empliv.setDiaEntrega(dateliv);
			}
			
			for(Emprestimo emprestimo : sistema.getEmprestimos()) {
				if(emprestimo.getEx().getIdEx() == empliv.getEx().getIdEx() && emprestimo.getEx().getIdLiv() == empliv.getEx().getIdLiv() && emprestimo.getDiaEntrega() == null) {
					available = 0;
				}
			}
			
			if(available == 1) {
				for(Status stat : sistema.getStatus()) {
					if(stat.getNome().equals("Disponível")) {
						empliv.getEx().setStat(stat);
						sistema.atualizaExemplar(empliv.getEx());
					}
				}
			}
	
			try {
				sistema.atualizaEmprestimo(empliv);
			} catch (Exception e1) {
				System.out.println("Limite de Renovações");
				return;
			}
			break;
		case 3:
			System.out.println("Lista de Empréstimos:");
			this.listarEmp();
			System.out.println();
			
			System.out.println("ID do Empréstimo:");
			Emprestimo emp;
			try {
				emp = sistema.selecionarEmprestimo(in.nextInt());
			} catch (Exception e) {
				System.out.println("ID não existente.");
				return;
			}
			in.nextLine();
			
			if(emp.getUser().getCat().getNome() != "Professor" && emp.getUser().getCat().getNome() != "Professor Pós-Grad") {
				Date date = new Date();
				if(date.compareTo(emp.limiteEntrega()) == 1) {
					long diffMili = Math.abs(emp.limiteEntrega().getTime() - date.getTime());
					long diff = TimeUnit.DAYS.convert(diffMili, TimeUnit.MILLISECONDS);
					
					System.out.println("Valor da Multa: R$0,50/dia");
					System.out.println("Total da Multa:" + diff*0.5 + ".");
					System.out.println("Confirmar? (1 - Sim)");
					int confirm;
					confirm = in.nextInt();
					in.nextLine();
					if(confirm == 1) {
						emp.setDiaEntrega(date);
					} else {
						emp.setDiaEntrega(null);
					}
				} else {
					emp.setDiaEntrega(date);
				}
			} else {
				Date date = new Date();
				emp.setDiaEntrega(date);
			}
			
			for(Emprestimo emprestimo : sistema.getEmprestimos()) {
				if(emprestimo.getEx().getIdEx() == emp.getEx().getIdEx() && emprestimo.getEx().getIdLiv() == emp.getEx().getIdLiv() && emprestimo.getDiaEntrega() == null) {
					available = 0;
				}
			}
			
			if(available == 1) {
				for(Status stat : sistema.getStatus()) {
					if(stat.getNome().equals("Disponível")) {
						emp.getEx().setStat(stat);
						sistema.atualizaExemplar(emp.getEx());
					}
				}
			}
			
			try {
				sistema.atualizaEmprestimo(emp);
			} catch (Exception e) {
				System.out.println("Limite de Renovações");
				return;
			}
			break;
		default:
			break;
		}
	}
	
	public void renovarEmprestimo() {
		int optionrenov;
		System.out.println("Selecionar Empréstimo:\n1 - Por Usuário\n2 - Por Livro\n3 - Por Empréstimo");
		System.out.println();
		
		optionrenov = in.nextInt();
		in.nextLine();
		switch(optionrenov) {
		case 1:
			System.out.println("Lista de Usuários com Empréstimo:");
			this.listarUserEmp();
			System.out.println();
			
			System.out.println("ID do Usuário:");
			this.listarUserEnt(in.nextInt());
			in.nextLine();
			
			System.out.println("ID do Empréstimo:");
			Emprestimo empuser;
			try {
				empuser = sistema.selecionarEmprestimo(in.nextInt());
			} catch (Exception e) {
				System.out.println("ID não existente.");
				return;
			}
			in.nextLine();
			
			empuser.toString();
			
			empuser.setRenov(empuser.getRenov() + 1);
			try {
				sistema.atualizaEmprestimo(empuser);
			} catch (Exception e2) {
				System.out.println("Limite de Renovações");
				return;
			}
			break;
		case 2:
			System.out.println("Lista de Livros com Empréstimo:");
			this.listarLivEmp();
			System.out.println();
			
			System.out.println("ID do Livro:");
			this.listarLivEnt(in.nextInt());
			in.nextLine();
			
			System.out.println("ID do Empréstimo:");
			Emprestimo empliv;
			try {
				empliv = sistema.selecionarEmprestimo(in.nextInt());
			} catch (Exception e) {
				System.out.println("ID não existente.");
				return;
			}
			in.nextLine();
			
			empliv.setRenov(empliv.getRenov() + 1);
			try {
				sistema.atualizaEmprestimo(empliv);
			} catch (Exception e1) {
				System.out.println("Limite de Renovações");
				return;
			}
			break;
		case 3:
			System.out.println("Lista de Empréstimos:");
			this.listarEmpNotEnt();
			System.out.println();
			
			System.out.println("ID do Empréstimo:");
			Emprestimo emp;
			try {
				emp = sistema.selecionarEmprestimo(in.nextInt());
			} catch (Exception e) {
				System.out.println("ID não existente.");
				return;
			}
			in.nextLine();
			
			emp.setRenov(emp.getRenov() + 1);
			try {
				sistema.atualizaEmprestimo(emp);
			} catch (Exception e) {
				System.out.println("Limite de Renovações");
				return;
			}
			break;
		default:
			break;
		}
	}
	
	public void removerEmprestimo() {
		int optionremove;
		int confirm;
		System.out.println("Selecionar Empréstimo:\n1 - Por Usuário\n2 - Por Livro\n3 - Por Empréstimo");
		System.out.println();
		
		optionremove = in.nextInt();
		in.nextLine();
		switch(optionremove) {
		case 1:
			System.out.println("Lista de Usuários com Empréstimo:");
			this.listarUserEmp();
			System.out.println();
			
			System.out.println("ID do Usuário:");
			this.listarUserEnt(in.nextInt());
			in.nextLine();
			
			System.out.println("ID do Empréstimo:");
			Emprestimo empuser;
			try {
				empuser = sistema.selecionarEmprestimo(in.nextInt());
			} catch (Exception e) {
				System.out.println("ID não existente.");
				return;
			}
			in.nextLine();
			
			empuser.toString();
			System.out.println("Confirmar? (1 - Sim)");
			confirm = in.nextInt();
			in.nextLine();
			
			if(confirm == 1) {
				sistema.deletarEmprestimo(empuser.getIdEmp());
			}
			break;
		case 2:
			System.out.println("Lista de Livros com Empréstimo:");
			this.listarLivEmp();
			System.out.println();
			
			System.out.println("ID do Livro:");
			this.listarLivEnt(in.nextInt());
			in.nextLine();
			
			System.out.println("ID do Empréstimo:");
			Emprestimo empliv;
			try {
				empliv = sistema.selecionarEmprestimo(in.nextInt());
			} catch (Exception e) {
				System.out.println("ID não existente.");
				return;
			}
			in.nextLine();
			
			empliv.toString();
			System.out.println("Confirmar? (1 - Sim)");
			confirm = in.nextInt();
			in.nextLine();
			
			if(confirm == 1) {
				sistema.deletarEmprestimo(empliv.getIdEmp());
			}
			break;
		case 3:
			System.out.println("Lista de Empréstimos:");
			this.listarEmp();
			System.out.println();
			
			System.out.println("ID do Empréstimo:");
			Emprestimo emp;
			try {
				emp = sistema.selecionarEmprestimo(in.nextInt());
			} catch (Exception e) {
				System.out.println("ID não existente.");
				return;
			}
			in.nextLine();
			
			emp.toString();
			System.out.println("Confirmar? (1 - Sim)");
			confirm = in.nextInt();
			in.nextLine();
			
			if(confirm == 1) {
				sistema.deletarEmprestimo(emp.getIdEmp());
			}
			break;
		default:
			break;
		}
	}
	
	public void listarUnis() {
		for(int i = 0; i > sistema.getUnis().size(); i++) {
			System.out.println(i+1 + ":" + sistema.getUnis().get(i).toString() + "\n");
		}
	}
	
	public void listarCategorias() {
		for (Categoria cat : sistema.getCategorias()) {
			System.out.println(cat.toString());
		}
	}
	
	public void listarUsuarios() {
		for(Usuario user : sistema.getUsuarios()) {
			System.out.println(user.toStringList());
		}
	}
	
	public void listarUsuariosCat(int id) {
		for(Usuario user : sistema.listarUserCat(id)) {
			System.out.println(user.toStringList());
		}
	}
	
	public void listarBibliotecario() {
		for(Bibliotecario bib : sistema.getBibliotecarios()) {
			System.out.println(bib.toStringList());
		}
	}
	
	public void listarAssistente() {
		for(Assistente assist : sistema.getAssistentes()) {
			System.out.println(assist.toStringList());
		}
	}
	
	public void listarPessoasOnly() {
		for(Pessoa pessoa : sistema.selecionarPessoaOnly()) {
			System.out.println(pessoa.toStringList());
		}
	}
	
	public void listarPessoasNotUser() {
		for(Pessoa pessoa : sistema.selecionarPessoaNotUser()) {
			System.out.println(pessoa.toStringList());
		}
	}
	
	public void listarPessoasNotFunc() {
		for(Pessoa pessoa : sistema.selecionarPessoaNotFunc()) {
			System.out.println(pessoa.toStringList());
		}
	}
	
	public void listarFunc() {
		for(Funcionario func : sistema.getFuncionario()) {
			System.out.println(func.toStringList());
		}
	}
	
	public void listarFuncSemCargo() {
		for(Funcionario func : sistema.selecionarFuncSemCargo()) {
			System.out.println(func.toStringList());
		}
	}
	
	public void listarEditoras() {
		for(Editora edit : sistema.getEditoras()) {
			System.out.println(edit.toString());
		}
	}
	
	public void listarColecoes() {
		for(Colecao col : sistema.getColecoes()) {
			System.out.println(col.toString());
		}
	}
	
	public void listarStatus() {
		for(Status status : sistema.getStatus()) {
			System.out.println(status.toString());
		}
	}
	
	public void listarLivros() {
		for(Livro liv : sistema.getLivros()) {
			System.out.println(liv.toStringList());
		}
	}
	
	public void listarExemplares(int id_liv) {
		for(Exemplar ex : sistema.getExemplares()) {
			if(ex.getIdLiv() == id_liv)
				System.out.println(ex.toStringList());
		}
	}
	
	public void listarExemplaresDisp(int id_liv) {
		for(Exemplar ex : sistema.listarExDisp(id_liv)) {
			System.out.println(ex.toStringList());
		}
	}
	
	public void listarUserEmp() {
		for(int num : sistema.listarUserEmp()) {
			try {
				System.out.println(sistema.selecionarUsuario(num).toStringList());
			} catch (Exception e) {
				System.out.println("ID não existente.");
				return;
			}
		}
	}
	
	public void listarLivEmp() {
		for(int num : sistema.listarLivEmp()) {
			try {
				System.out.println(sistema.selecionarLivro(num).toStringList());
			} catch (Exception e) {
				System.out.println("ID não existente");
				return;
			}
		}
	}
	
	public void listarUserEnt(int id) {
		for(Emprestimo emp : sistema.selecionarEmprestimoIdUser(id)) {
			System.out.println(emp.toString());
		}
	}
	
	public void listarLivEnt(int id) {
		for(Emprestimo emp : sistema.selecionarEmprestimoIdLiv(id)) {
			System.out.println(emp.toString());
		}
	}
	
	public void listarEmp() {
		for(Emprestimo emp : sistema.getEmprestimos()) {
			System.out.println(emp.toString());
		}
	}
	
	public void listarEmpNotEnt() {
		for(Emprestimo emp : sistema.listarEmpNotEnt()) {
			System.out.println(emp.toString());
		}
	}
	
	public void listarEmpUser(int id) {
		for(Emprestimo emp : sistema.listarEmpUser(id)) {
			System.out.println(emp.toString());
		}
	}
	
	public void listarEmpLiv(int id) {
		for(Emprestimo emp : sistema.listarEmpLiv(id)) {
			if(emp.getDiaEntrega() == null)
				System.out.println(emp.toString());
		}
	}
	
	public void listarEmpLivTotal(int id) {
		for(Emprestimo emp : sistema.listarEmpLiv(id)) {
			System.out.println(emp.toString());
		}
	}
}
