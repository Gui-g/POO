package apresentacao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.bson.types.ObjectId;
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
import negocio.SistemaMongo;

public class MenuMongo {	
	private Scanner in = new Scanner(System.in);
	private SistemaMongo sistema;
	
	public MenuMongo() {
		sistema = new SistemaMongo();
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
							for(int i = 0; i < sistema.getCategorias().size(); i++) {
								System.out.println(i+1 + " " + sistema.getCategorias().get(i).toString());
							}
							
							System.out.println("Categoria:");
							this.listarUsuariosCat(sistema.getCategorias().get(in.nextInt()-1).getIdCatMongo());
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
								this.listarExemplares(liv.getIdLivMongo());
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
						System.out.println("Usuário Nº:");
						
						this.listarEmpUser(sistema.getUsuarios().get(in.nextInt()-1).getIdUserMongo());
						in.nextLine();
						System.out.println();
						break;
					case 3:
						this.listarLivros();
						System.out.println("Livro Nº:");
						
						this.listarEmpLivTotal(sistema.getLivros().get(in.nextInt()-1).getIdLivMongo());
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
			System.out.println("Universidade Nº:");
			try {
				user.setUni(sistema.selecionarUniversidade(sistema.getUnis().get(in.nextInt()-1).getIdUniMongo()));
			} catch (Exception e3) {
				System.out.println("ID não existente.");
				return;
			}
			in.nextLine();
			
			this.listarCategorias();
			System.out.println("Categoria Nº:");
			try {
				user.setCat(sistema.selecionarCategoria(sistema.getCategorias().get(in.nextInt()-1).getIdCatMongo()));
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
				user.setIdPesMongo(sistema.selecionarPessoaCpf(pessoa.getCpf()).getIdPesMongo());
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
					func.setIdPesMongo(sistema.selecionarPessoaCpf(pessoa.getCpf()).getIdPesMongo());
				} catch (Exception e1) {
					System.out.println("CPF não cadastrado");
					return;
				}
				sistema.insereFuncionario(func);
				
				bib.setIdFuncMongo(sistema.selecionarFuncionarioId(func.getIdPesMongo()).getIdFuncMongo());
				sistema.insereBibliotecario(bib);
				break;
			case 2:
				Assistente assist = new Assistente();
				
				this.listarBibliotecario();
				System.out.println("Supervisor Nº:");
				try {
					assist.setSupervisor(sistema.selecionarBibliotecario(sistema.getBibliotecarios().get(in.nextInt()-1).getIdBibMongo()));
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
					func.setIdPesMongo(sistema.selecionarPessoaCpf(pessoa.getCpf()).getIdPesMongo());
				} catch (Exception e1) {
					System.out.println("CPF não cadastrado");
					return;
				}
				sistema.insereFuncionario(func);
				
				assist.setIdFuncMongo(sistema.selecionarFuncionarioId(func.getIdPesMongo()).getIdFuncMongo());
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
					func.setIdPesMongo(sistema.selecionarPessoaCpf(pessoa.getCpf()).getIdPesMongo());
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
			System.out.println("Selecionar usuario Nº: ");
			int user = in.nextInt();
			in.nextLine();
			Usuario usermod;
			try {
				usermod = sistema.selecionarUsuario(sistema.getUsuarios().get(user-1).getIdUserMongo());
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
				
				System.out.println("Nova universidade Nº:");
				try {
					usermod.setUni(sistema.selecionarUniversidade(sistema.getUnis().get(in.nextInt()-1).getIdUniMongo()));
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
				
				
				System.out.println("Nova categoria Nº:");
				try {
					usermod.setCat(sistema.selecionarCategoria(sistema.getCategorias().get(in.nextInt()-1).getIdCatMongo()));
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
				System.out.println("Selecionar Bibliotecario:");
				int idbib = in.nextInt();
				in.nextLine();
				Bibliotecario bibmod;
				try {
					bibmod = sistema.selecionarBibliotecario(sistema.getBibliotecarios().get(idbib-1).getIdBibMongo());
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
				System.out.println("Selecionar Assistente Nº:");
				int idassist = in.nextInt();
				in.nextLine();
				Assistente assistmod;
				try {
					assistmod = sistema.selecionarAssistente(sistema.getAssistentes().get(idassist-1).getIdAssistMongo());
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
					
					System.out.println("Novo supervisor:");
					try {
						assistmod.setSupervisor(sistema.selecionarBibliotecario(sistema.getBibliotecarios().get(in.nextInt()-1).getIdBibMongo()));
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
				
				System.out.println("Usuário: ");
				int iduser = in.nextInt();
				try {
					System.out.println(sistema.selecionarUsuario(sistema.getUsuarios().get(iduser-1).getIdUserMongo()).toString());
				} catch (Exception e) {
					System.out.println("ID não existente.");
					return;
				}
				System.out.println("Confirmar? (1 - Sim)");
				confirm = in.nextInt();
				in.nextLine();
				if(confirm == 1)
					sistema.deletarUsuario(sistema.getUsuarios().get(iduser-1).getIdUserMongo());
				break;
			case 2:
				System.out.println();
				this.listarBibliotecario();
				System.out.println();
				
				System.out.println("ID do Bibliotecário: ");
				Bibliotecario bib;
				try {
					bib = sistema.selecionarBibliotecario(sistema.getBibliotecarios().get(in.nextInt()-1).getIdBibMongo());
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
					sistema.deletarBibliotecario(bib.getIdBibMongo());
					sistema.deletarFuncionario(bib.getIdFuncMongo());
				}
				break;
			case 3:
				System.out.println();
				this.listarAssistente();
				System.out.println();
				
				System.out.println("ID do Assistente: ");
				Assistente assist;
				try {
					assist = sistema.selecionarAssistente(sistema.getAssistentes().get(in.nextInt()-1).getIdAssistMongo());
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
					sistema.deletarAssistente(assist.getIdAssistMongo());
					sistema.deletarFuncionario(assist.getIdFuncMongo());
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
					user = sistema.selecionarUsuario(sistema.getUsuarios().get(in.nextInt()-1).getIdPesMongo());
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
					sistema.deletarUsuario(user.getIdUserMongo());
					sistema.deletarPessoa(user.getIdPesMongo());
				}
				break;
			case 2:
				System.out.println();
				this.listarBibliotecario();
				System.out.println();
				
				System.out.println("ID do Bibliotecário: ");
				Bibliotecario bib;
				try {
					bib = sistema.selecionarBibliotecario(sistema.getBibliotecarios().get(in.nextInt()-1).getIdBibMongo());
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
					sistema.deletarBibliotecario(bib.getIdBibMongo());
					sistema.deletarFuncionario(bib.getIdFuncMongo());
					sistema.deletarPessoa(bib.getIdPesMongo());
				}
				break;
			case 3:
				System.out.println();
				this.listarAssistente();
				System.out.println();
				
				System.out.println("ID do Assistente: ");
				Assistente assist;
				try {
					assist = sistema.selecionarAssistente(sistema.getAssistentes().get(in.nextInt()-1).getIdAssistMongo());
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
					sistema.deletarAssistente(assist.getIdAssistMongo());
					sistema.deletarFuncionario(assist.getIdFuncMongo());
					sistema.deletarPessoa(assist.getIdPesMongo());
				}
				break;
			case 4:
				System.out.println();
				this.listarPessoasOnly();
				System.out.println();
				
				System.out.println("Pessoa: ");
				int idpes = in.nextInt();
				in.nextLine();
				try {
					System.out.println(sistema.selecionarPessoa(sistema.selecionarPessoaOnly().get(idpes-1).getIdPesMongo()).toString());
				} catch (Exception e) {
					System.out.println("ID não existente.");
					return;
				}
				System.out.println("Confirmar? (1 - Sim)");
				confirm = in.nextInt();
				in.nextLine();
				if(confirm == 1) {
					sistema.deletarPessoa(sistema.getPessoas().get(idpes-1).getIdPesMongo());
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
			
			System.out.println("Pessoa Nº: ");
			Pessoa pessoaUser;
			try {
				pessoaUser = sistema.selecionarPessoa(sistema.selecionarPessoaNotUser().get(in.nextInt()-1).getIdPesMongo());
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
			user.setIdPesMongo(pessoaUser.getIdPesMongo());
			user.setSexo(pessoaUser.getSexo());
			
			this.listarUnis();
			System.out.println("Universidade Nº:");
			try {
				user.setUni(sistema.selecionarUniversidade(sistema.getUnis().get(in.nextInt()-1).getIdUniMongo()));
			} catch (Exception e3) {
				System.out.println("ID não existente.");
				return;
			}
			in.nextLine();
			
			this.listarCategorias();
			System.out.println("Categoria Nº:");
			try {
				user.setCat(sistema.selecionarCategoria(sistema.getCategorias().get(in.nextInt()-1).getIdCatMongo()));
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
			
			System.out.println("Pessoa Nº: ");
			Pessoa pessoaFunc;
			try {
				pessoaFunc = sistema.selecionarPessoa(sistema.selecionarPessoaNotFunc().get(in.nextInt()-1).getIdPesMongo());
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
			func.setIdPesMongo(pessoaFunc.getIdPesMongo());
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
			
			System.out.println("Funcionario Nº: ");
			Funcionario funcBib;
			try {
				funcBib = sistema.selecionarFuncionario(sistema.selecionarFuncSemCargo().get(in.nextInt()-1).getIdFuncMongo());
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
			bib.setIdPesMongo(funcBib.getIdPesMongo());
			bib.setSexo(funcBib.getSexo());
			
			bib.setCtps(funcBib.getCtps());
			bib.setSalario(funcBib.getSalario());
			bib.setTurno(funcBib.getTurno());
			bib.setIdFuncMongo(funcBib.getIdFuncMongo());
			
			sistema.insereBibliotecario(bib);
			break;
		case 4:
			System.out.println();
			this.listarFuncSemCargo();
			System.out.println();
			
			System.out.println("Funcionario Nº: ");
			Funcionario funcAssist;
			try {
				funcAssist = sistema.selecionarFuncionario(sistema.selecionarFuncSemCargo().get(in.nextInt()-1).getIdFuncMongo());
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
			assist.setIdPesMongo(funcAssist.getIdPesMongo());
			assist.setSexo(funcAssist.getSexo());
			
			assist.setCtps(funcAssist.getCtps());
			assist.setSalario(funcAssist.getSalario());
			assist.setTurno(funcAssist.getTurno());
			assist.setIdFuncMongo(funcAssist.getIdFuncMongo());
			
			this.listarBibliotecario();
			System.out.println("Supervisor Nº:");
			try {
				assist.setSupervisor(sistema.selecionarBibliotecario(sistema.getBibliotecarios().get(in.nextInt()-1).getIdBibMongo()));
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
		int option = in.nextInt();
		in.nextLine();
		try {
			unimod = sistema.selecionarUniversidade(sistema.getUnis().get(option-1).getIdUniMongo());
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
			uni = sistema.selecionarUniversidade(sistema.getUnis().get(in.nextInt()-1).getIdUniMongo());
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
				sistema.deletarUniversidade(uni.getIdUniMongo());
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
		
		System.out.println("Editora Nº: ");
		Editora editmod;
		try {
			editmod = sistema.selecionarEditora(sistema.getEditoras().get(in.nextInt()-1).getIdEdiMongo());
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
		
		System.out.println("Editora Nº: ");
		int idedit = in.nextInt();
		in.nextLine();
		try {
			System.out.println(sistema.selecionarEditora(sistema.getEditoras().get(idedit-1).getIdEdiMongo()).toString());
		} catch (Exception e) {
			System.out.println("ID não existente.");
			return;
		}
		System.out.println("Confirmar? (1 - Sim)");
		confirm = in.nextInt();
		in.nextLine();
		if(confirm == 1)
			sistema.deletarEditora(sistema.getEditoras().get(idedit-1).getIdEdiMongo());
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
		
		System.out.println("Coleção Nº: ");
		Colecao colmod;
		try {
			colmod = sistema.selecionarColecao(sistema.getColecoes().get(in.nextInt()-1).getIdColMongo());
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
		
		System.out.println("Coleção Nº: ");
		int idcol = in.nextInt();
		in.nextLine();
		try {
			System.out.println(sistema.getColecoes().get(idcol-1).getIdColMongo().toString());
		} catch (Exception e) {
			System.out.println("ID não existente.");
			return;
		}
		System.out.println("Confirmar? (1 - Sim)");
		confirm = in.nextInt();
		in.nextLine();
		if(confirm == 1)
			sistema.deletarColecao(sistema.getColecoes().get(idcol-1).getIdColMongo());		
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
			
			System.out.println("Editoras Cadastradas:");
			this.listarEditoras();
			System.out.println();
			
			System.out.println("Número de Editoras:");
			int limit = in.nextInt();
			in.nextLine();
			ArrayList<Editora> lista = new ArrayList<Editora>();
			for(int i = 0; i < limit; i++) {
				System.out.println("Editora Nº:");
				try {
					lista.add(sistema.selecionarEditora(sistema.getEditoras().get(in.nextInt()-1).getIdEdiMongo()));
				} catch (Exception e) {
					System.out.println("ID não existente.");
					return;
				}
				in.nextLine();
			}
			
			liv.setEditoras(lista);
			
			try {
				sistema.insereLivro(liv);
			} catch (Exception e) {
				System.out.println("ISBN já cadastrado");
				return;
			}
		
			try {
				liv.setIdLivMongo(sistema.selecionarLivroIsbn(liv.getIsbn()).getIdLivMongo());
			} catch (Exception e2) {
				System.out.println("ISBN não existente.");
				return;
			}
			
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
					
					ex.setIdLivMongo(livEx.getIdLivMongo());
					ex.setTitulo(livEx.getTitulo());
					ex.setAutor(livEx.getAutor());
					ex.setIsbn(livEx.getIsbn());
					ex.setEditoras(livEx.getEditoras());
					
					System.out.println("Coleções Cadastradas:\n");
					this.listarColecoes();
					System.out.println();
					
					System.out.println("Coleção do Exemplar Nº: ");
					try {
						ex.setCol(sistema.selecionarColecao(sistema.getColecoes().get(in.nextInt()-1).getIdColMongo()));
					} catch (Exception e1) {
						System.out.println("ID não existente.");
						return;
					}
					in.nextLine();
					
					try {
						ex.setStat(sistema.selecionarStatus(sistema.getStatus().get(0).getIdStMongo()));
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
			
			System.out.println("Livro Nº: ");
			Livro livex;
			try {
				livex = sistema.selecionarLivro(sistema.getLivros().get(in.nextInt()-1).getIdLivMongo());
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
				
				ex.setIdLivMongo(livex.getIdLivMongo());
				ex.setTitulo(livex.getTitulo());
				ex.setAutor(livex.getAutor());
				ex.setIsbn(livex.getIsbn());
				ex.setEditoras(livex.getEditoras());
				
				System.out.println("Coleções Cadastradas:\n");
				this.listarColecoes();
				System.out.println();
				
				System.out.println("Coleção do Exemplar Nº: ");
				try {
					ex.setCol(sistema.selecionarColecao(sistema.getColecoes().get(in.nextInt()-1).getIdColMongo()));
					in.nextLine();
				} catch (Exception e1) {
					System.out.println("ID não existente.");
					return;
				}
				
				try {
					ex.setStat(sistema.selecionarStatus(sistema.getStatus().get(0).getIdStMongo()));
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
			System.out.println("Livro Nº:");
			Livro livmod;
			try {
				livmod = sistema.selecionarLivro(sistema.getLivros().get(in.nextInt()-1).getIdLivMongo());
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
			System.out.println("Livro Nº:");
			Livro livref;
			try {
				livref = sistema.selecionarLivro(sistema.getLivros().get(in.nextInt()-1).getIdLivMongo());
				in.nextLine();
			} catch (Exception e) {
				System.out.println("ID não existente");
				return;
			}
			
			System.out.println("Lista de Exemplares do Livro " + livref.getTitulo() + " :");
			this.listarExemplares(livref.getIdLivMongo());
			System.out.println();
			
			System.out.println("Exemplar Nº:");
			Exemplar exmod;
			try {
				exmod = sistema.selecionarExemplar(sistema.getExemplares().get(in.nextInt()-1).getIdExMongo());
				in.nextLine();
			} catch (Exception e) {
				System.out.println("ID não existente");
				return;
			}
			
			System.out.println("Modificar Coleção? (1 - Sim)");
			select = in.nextInt();
			in.nextLine();
			if(select == 1) {
				System.out.println();
				System.out.println("Coleções Cadastradas:");
				this.listarColecoes();
				
				System.out.println("Nova Coleção Nº: ");
				try {
					exmod.setCol(sistema.selecionarColecao(sistema.getColecoes().get(in.nextInt()-1).getIdColMongo()));
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
				System.out.println(sistema.selecionarLivro(sistema.getLivros().get(idliv-1).getIdLivMongo()).toString());
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
					liv = sistema.selecionarLivro(sistema.getLivros().get(idliv-1).getIdLivMongo());
				} catch (Exception e1) {
					System.out.println("ID não existente.");
					return;
				}
				for(Exemplar ex : sistema.getExemplares()) {
					if(liv.getIdLivMongo().equals(ex.getIdLivMongo()))
						sistema.deletarExemplar(ex.getIdExMongo());
				}
				sistema.deletarLivro(sistema.getLivros().get(idliv-1).getIdLivMongo());	
			}
			break;
		case 2:
			System.out.println("Lista de Livros:");
			this.listarLivros();
			System.out.println();
			System.out.println("Livro Nº:");
			Livro livref;
			try {
				livref = sistema.selecionarLivro(sistema.getLivros().get(in.nextInt()-1).getIdLivMongo());
			} catch (Exception e) {
				System.out.println("ID não existente");
				return;
			}
			in.nextLine();
			
			System.out.println("Lista de Exemplares do Livro " + livref.getTitulo() + " :");
			this.listarExemplares(livref.getIdLivMongo());
			System.out.println();
			
			System.out.println("Exemplar Nº:");
			int idex = in.nextInt();
			in.nextLine();
			try {
				System.out.println(sistema.selecionarExemplar(sistema.getExemplares().get(idex-1).getIdExMongo()).toString());
			} catch (Exception e) {
				System.out.println("ID não existente");
				return;
			}
			System.out.println("Confirmar? (1 - Sim)");
			confirm = in.nextInt();
			in.nextLine();
			if(confirm == 1)
				sistema.deletarExemplar(sistema.getExemplares().get(idex-1).getIdExMongo());
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
		
		System.out.println("Status Nº: ");
		Status statusmod;
		try {
			statusmod = sistema.selecionarStatus(sistema.getStatus().get(in.nextInt()-1).getIdStMongo());
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
		
		System.out.println("Status Nº: ");
		int idstatus = in.nextInt();
		in.nextLine();
		try {
			System.out.println(sistema.selecionarStatus(sistema.getStatus().get(idstatus-1).getIdStMongo()).toString());
		} catch (Exception e) {
			System.out.println("ID não existente.");
			return;
		}
		System.out.println("Confirmar? (1 - Sim)");
		confirm = in.nextInt();
		in.nextLine();
		if(confirm == 1) {
			try {
				sistema.deletarStatus(sistema.getStatus().get(idstatus-1).getIdStMongo());
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
		
		System.out.println("Categoria Nº: ");
		Categoria catmod;
		int option = in.nextInt();
		in.nextLine();
		try {
			catmod = sistema.selecionarCategoria(sistema.getCategorias().get(option-1).getIdCatMongo());
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
		
		System.out.println("Categoria Nº: ");
		int idcat = in.nextInt();
		in.nextLine();
		try {
			System.out.println(sistema.selecionarCategoria(sistema.getCategorias().get(idcat-1).getIdCatMongo()).toString());
		} catch (Exception e) {
			System.out.println("ID não existente.");
			return;
		}
		System.out.println("Confirmar? (1 - Sim)");
		confirm = in.nextInt();
		in.nextLine();
		if(confirm == 1) {
			try {
				sistema.deletarCategoria(sistema.getCategorias().get(idcat-1).getIdCatMongo());
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
		
		System.out.println("Usuário Nº:");
		try {
			emp.setUser(sistema.selecionarUsuario(sistema.getUsuarios().get(in.nextInt()-1).getIdUserMongo()));
		} catch (Exception e1) {
			System.out.println("ID não existente.");
			return;
		}
		in.nextLine();
		
		System.out.println("Lista de Bibliotecários:");
		this.listarBibliotecario();
		System.out.println();
		
		System.out.println("Bibliotecário Nº:");
		try {
			emp.setBib(sistema.selecionarBibliotecario(sistema.getBibliotecarios().get(in.nextInt()-1).getIdBibMongo()));
		} catch (Exception e1) {
			System.out.println("ID não existente.");
			return;
		}
		in.nextLine();
		
		System.out.println("Lista de Livros:");
		this.listarLivros();
		System.out.println();
		
		System.out.println("Livro Nº:");
		int idliv = in.nextInt();
		in.nextLine();
		try {
			System.out.println("Lista de Exemplares de " + sistema.selecionarLivro(sistema.getLivros().get(idliv-1).getIdLivMongo()).getTitulo() + ":");
		} catch (Exception e) {
			System.out.println("ID não existente");
			return;
		}
		if(sistema.listarExDisp(sistema.getLivros().get(idliv-1).getIdLivMongo()).isEmpty()) {
			System.out.println("Sem exemplares disponíveis, escolher um exemplar para agendar:");
			this.listarEmpLiv(sistema.getLivros().get(idliv-1).getIdLivMongo());
			reserva = 1;
		}
		else { 
			this.listarExemplaresDisp(sistema.getLivros().get(idliv-1).getIdLivMongo());
			System.out.println(); 
			reserva = 0;
		}
		
		if(reserva == 0) {
			System.out.println("Exemplar Nº:");
			try {
				emp.setEx(sistema.selecionarExemplar(sistema.listarExDisp(sistema.getLivros().get(idliv-1).getIdLivMongo()).get(in.nextInt()-1).getIdExMongo()));
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
				if(emprestimo.getEx().getIdExMongo().equals(emp.getEx().getIdExMongo()) && emprestimo.getUser().getIdUserMongo().equals(emp.getUser().getIdUserMongo()) && emprestimo.getBib().getIdBibMongo().equals(emp.getBib().getIdBibMongo()) && compareDate.compareTo(emp.getDiaEmprestimo()) == 0) {
					for(Status stat : sistema.getStatus()) {
						if(stat.getNome().equals("Emprestado")) {
							emp.getEx().setStat(stat);
							sistema.atualizaExemplar(emp.getEx());
						}
					}
				}
			}
		} else if (reserva == 1) {
			System.out.println("Emprestimo Nº: ");
			int idemp = in.nextInt();
			in.nextLine();
			try {
				emp.setEx(sistema.selecionarEmprestimo(sistema.listarEmpLiv(sistema.getLivros().get(idliv-1).getIdLivMongo()).get(idemp-1).getIdEmpMongo()).getEx());
			} catch (Exception e) {
				System.out.println("ID não existente");
				return;
			}
			
			emp.setRenov(0);
			
			Date data;
			try {
				data = sistema.selecionarEmprestimo(sistema.listarEmpLiv(sistema.getLivros().get(idliv-1).getIdLivMongo()).get(idemp-1).getIdEmpMongo()).limiteEntrega();
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
				if(emprestimo.getEx().getIdExMongo().equals(emp.getEx().getIdExMongo()) && emprestimo.getUser().getIdUserMongo().equals(emp.getUser().getIdUserMongo()) && emprestimo.getBib().getIdBibMongo().equals(emp.getBib().getIdBibMongo()) && compareDate.compareTo(emp.getDiaEmprestimo()) == 0) {
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
			
			System.out.println("Usuário Nº:");
			int opt = in.nextInt();
			this.listarUserEnt(sistema.listarUserEmp().get(opt-1));
			in.nextLine();
			
			System.out.println("Empréstimo Nº:");
			Emprestimo empuser;
			try {
				empuser = sistema.selecionarEmprestimo(sistema.selecionarEmprestimoIdUser(sistema.listarUserEmp().get(opt-1)).get(in.nextInt()-1).getIdEmpMongo());
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
				if(emprestimo.getEx().getIdExMongo().equals(empuser.getEx().getIdExMongo()) && emprestimo.getDiaEntrega() == null) {
					System.out.println(emprestimo.toString());
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
			
			System.out.println("Livro Nº:");
			int opt1 = in.nextInt();
			this.listarLivEnt(sistema.listarLivEmp().get(opt1-1));
			in.nextLine();
			
			System.out.println("Empréstimo Nº:");
			Emprestimo empliv;
			try {
				empliv = sistema.selecionarEmprestimo(sistema.selecionarEmprestimoIdLiv(sistema.listarLivEmp().get(opt1-1)).get(in.nextInt()-1).getIdEmpMongo());
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
			
			try {
				sistema.atualizaEmprestimo(empliv);
			} catch (Exception e1) {
				System.out.println("Limite de Renovações");
				return;
			}
			
			for(Emprestimo emprestimo : sistema.getEmprestimos()) {
				if(emprestimo.getEx().getIdExMongo().equals(empliv.getEx().getIdExMongo()) && emprestimo.getDiaEntrega() == null) {
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
			break;
		case 3:
			System.out.println("Lista de Empréstimos:");
			this.listarEmpNotEnt();
			System.out.println();
			
			System.out.println("Empréstimo Nº: ");
			Emprestimo emp;
			try {
				emp = sistema.selecionarEmprestimo(sistema.listarEmpNotEnt().get(in.nextInt()-1).getIdEmpMongo());
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
			
			try {
				sistema.atualizaEmprestimo(emp);
			} catch (Exception e) {
				System.out.println("Limite de Renovações");
				return;
			}
			
			for(Emprestimo emprestimo : sistema.getEmprestimos()) {
				if(emprestimo.getEx().getIdExMongo().equals(emp.getEx().getIdExMongo()) && emprestimo.getDiaEntrega() == null) {
					available = 0;
				}
			}
			
			System.out.println(available);
			if(available == 1) {
				for(Status stat : sistema.getStatus()) {
					if(stat.getNome().equals("Disponível")) {
						emp.getEx().setStat(stat);
						sistema.atualizaExemplar(emp.getEx());
					}
				}
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
			
			System.out.println("Usuário Nº:");
			int opt = in.nextInt();
			this.listarUserEnt(sistema.listarUserEmp().get(opt-1));
			in.nextLine();
			
			System.out.println("ID do Empréstimo:");
			Emprestimo empuser;
			try {
				empuser = sistema.selecionarEmprestimo(sistema.selecionarEmprestimoIdUser(sistema.listarUserEmp().get(opt-1)).get(in.nextInt()-1).getIdEmpMongo());
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
			int opt1 = in.nextInt();
			this.listarLivEnt(sistema.listarLivEmp().get(opt1-1));
			in.nextLine();
			
			System.out.println("ID do Empréstimo:");
			Emprestimo empliv;
			try {
				empliv = sistema.selecionarEmprestimo(sistema.selecionarEmprestimoIdLiv(sistema.listarLivEmp().get(opt1-1)).get(in.nextInt()-1).getIdEmpMongo());
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
				emp = sistema.selecionarEmprestimo(sistema.listarEmpNotEnt().get(in.nextInt()-1).getIdEmpMongo());
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
			
			System.out.println("Usuário Nº:");
			int opt = in.nextInt();
			this.listarUserEnt(sistema.listarUserEmp().get(opt-1));
			in.nextLine();
			
			System.out.println("Empréstimo Nº:");
			Emprestimo empuser;
			try {
				empuser = sistema.selecionarEmprestimo(sistema.selecionarEmprestimoIdUser(sistema.listarUserEmp().get(opt-1)).get(in.nextInt()-1).getIdEmpMongo());
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
				sistema.deletarEmprestimo(empuser.getIdEmpMongo());
			}
			break;
		case 2:
			System.out.println("Lista de Livros com Empréstimo:");
			this.listarLivEmp();
			System.out.println();
			
			System.out.println("Livro Nº:");
			int opt1 = in.nextInt();
			this.listarLivEnt(sistema.listarLivEmp().get(opt1-1));
			in.nextLine();
			
			System.out.println("Empréstimo Nº:");
			Emprestimo empliv;
			try {
				empliv = sistema.selecionarEmprestimo(sistema.selecionarEmprestimoIdLiv(sistema.listarLivEmp().get(opt1-1)).get(in.nextInt()-1).getIdEmpMongo());
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
				sistema.deletarEmprestimo(empliv.getIdEmpMongo());
			}
			break;
		case 3:
			System.out.println("Lista de Empréstimos:");
			this.listarEmp();
			System.out.println();
			
			System.out.println("ID do Empréstimo:");
			Emprestimo emp;
			try {
				emp = sistema.selecionarEmprestimo(sistema.getEmprestimos().get(in.nextInt()-1).getIdEmpMongo());
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
				sistema.deletarEmprestimo(emp.getIdEmpMongo());
			}
			break;
		default:
			break;
		}
	}
	
	public void listarUnis() {
		for(int i = 0; i < sistema.getUnis().size(); i++) {
			System.out.println(i+1 + ":" + sistema.getUnis().get(i).toString() + "\n");
		}
	}
	
	public void listarCategorias() {
		for(int i = 0; i < sistema.getCategorias().size(); i++) {
			System.out.println(i+1 + ":" + sistema.getCategorias().get(i).toString() + "\n");
		}
	}
	
	public void listarUsuarios() {
		for(int i = 0; i < sistema.getUsuarios().size(); i++) {
			System.out.println(i+1 + ":" + sistema.getUsuarios().get(i).toString() + "\n");
		}
	}
	
	public void listarUsuariosCat(ObjectId id) {
		for(int i = 0; i < sistema.listarUserCat(id).size(); i++) {
			System.out.println(i+1 + ":" + sistema.listarUserCat(id).get(i).toString() + "\n");
		}
	}
	
	public void listarBibliotecario() {
		for(int i = 0; i < sistema.getBibliotecarios().size(); i++) {
			System.out.println(i+1 + ":" + sistema.getBibliotecarios().get(i).toString() + "\n");
		}
	}
	
	public void listarAssistente() {
		for(int i = 0; i < sistema.getAssistentes().size(); i++) {
			System.out.println(i+1 + ":" + sistema.getAssistentes().get(i).toString() + "\n");
		}
	}
	
	public void listarPessoasOnly() {
		for(int i = 0; i < sistema.selecionarPessoaOnly().size(); i++) {
			System.out.println(i+1 + ":" + sistema.selecionarPessoaOnly().get(i).toString() + "\n");
		}
	}
	
	public void listarPessoasNotUser() {
		for(int i = 0; i < sistema.selecionarPessoaNotUser().size(); i++) {
			System.out.println(i+1 + ":" + sistema.selecionarPessoaNotUser().get(i).toString() + "\n");
		}
	}
	
	public void listarPessoasNotFunc() {
		for(int i = 0; i < sistema.selecionarPessoaNotFunc().size(); i++) {
			System.out.println(i+1 + ":" + sistema.selecionarPessoaNotFunc().get(i).toString() + "\n");
		}
	}
	
	public void listarFunc() {
		for(int i = 0; i < sistema.getFuncionario().size(); i++) {
			System.out.println(i+1 + ":" + sistema.getFuncionario().get(i).toString() + "\n");
		}
	}
	
	public void listarFuncSemCargo() {
		for(int i = 0; i < sistema.selecionarFuncSemCargo().size(); i++) {
			System.out.println(i+1 + ":" + sistema.selecionarFuncSemCargo().get(i).toString() + "\n");
		}
	}
	
	public void listarEditoras() {
		for(int i = 0; i < sistema.getEditoras().size(); i++) {
			System.out.println(i+1 + ":" + sistema.getEditoras().get(i).toString() + "\n");
		}
	}
	
	public void listarColecoes() {
		for(int i = 0; i < sistema.getColecoes().size(); i++) {
			System.out.println(i+1 + ":" + sistema.getColecoes().get(i).toString() + "\n");
		}
	}
	
	public void listarStatus() {
		for(int i = 0; i < sistema.getStatus().size(); i++) {
			System.out.println(i+1 + ":" + sistema.getStatus().get(i).toString() + "\n");
		}
	}
	
	public void listarLivros() {
		for(int i = 0; i < sistema.getLivros().size(); i++) {
			System.out.println(i+1 + ":" + sistema.getLivros().get(i).toString() + "\n");
		}
	}
	
	public void listarExemplares(ObjectId id_liv) {
		for(int i = 0; i < sistema.getExemplares().size(); i++) {
			if(sistema.getExemplares().get(i).getIdLivMongo().equals(id_liv)) {
				System.out.println(i+1 + ":" + sistema.getExemplares().get(i).toString() + "\n");
			}
		}
	}
	
	public void listarExemplaresDisp(ObjectId id_liv) {
		for(int i = 0; i < sistema.listarExDisp(id_liv).size(); i++) {
			System.out.println(i+1 + ":" + sistema.listarExDisp(id_liv).get(i).toString() + "\n");
		}
	}
	
	public void listarUserEmp() {
		for(int i = 0; i < sistema.listarUserEmp().size(); i++) {
			try {
				System.out.println(i+1 + ":" + sistema.selecionarUsuario(sistema.listarUserEmp().get(i)).toString() + "\n");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void listarLivEmp() {
		for(int i = 0; i < sistema.listarLivEmp().size(); i++) {
			try {
				System.out.println(i+1 + ":" + sistema.selecionarLivro(sistema.listarLivEmp().get(i)).toString() + "\n");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void listarUserEnt(ObjectId id) {
		for(int i = 0; i < sistema.selecionarEmprestimoIdUser(id).size(); i++) {
			System.out.println(i+1 + ":" + sistema.selecionarEmprestimoIdUser(id).get(i).toString() + "\n");
		}
	}
	
	public void listarLivEnt(ObjectId id) {
		for(int i = 0; i < sistema.selecionarEmprestimoIdLiv(id).size(); i++) {
			System.out.println(i+1 + ":" + sistema.selecionarEmprestimoIdLiv(id).get(i).toString() + "\n");
		}
	}
	
	public void listarEmp() {
		for(int i = 0; i < sistema.getEmprestimos().size(); i++) {
			System.out.println(i+1 + ":" + sistema.getEmprestimos().get(i).toString() + "\n");
		}
	}
	
	public void listarEmpNotEnt() {
		for(int i = 0; i < sistema.listarEmpNotEnt().size(); i++) {
			System.out.println(i+1 + ":" + sistema.listarEmpNotEnt().get(i).toString() + "\n");
		}
	}
	
	public void listarEmpUser(ObjectId id) {
		for(int i = 0; i < sistema.listarEmpUser(id).size(); i++) {
			System.out.println(i+1 + ":" + sistema.listarEmpUser(id).get(i).toString() + "\n");
		}
	}
	
	public void listarEmpLiv(ObjectId id) {
		for(int i = 0; i < sistema.listarEmpLiv(id).size(); i++) {
			if(sistema.listarEmpLiv(id).get(i).getDiaEntrega() == null) {
				System.out.println(i+1 + ":" + sistema.listarEmpLiv(id).get(i).toString() + "\n");
			}
		}
	}
	
	public void listarEmpLivTotal(ObjectId id) {
		for(int i = 0; i < sistema.listarEmpLiv(id).size(); i++) {
			System.out.println(i+1 + ":" + sistema.listarEmpLiv(id).get(i).toString() + "\n");
		}
	}
}
