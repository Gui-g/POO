import java.util.Scanner;

public class User {
	Scanner in = new Scanner(System.in);
	SistemaEmpresa sistema = new SistemaEmpresa();
	
	public void user() {
		System.out.println("Cadastrar uma empresa:");
		System.out.println();
		sistema.cadastrarEmpresa();
		System.out.println();
		sistema.cadastrarEnderecoEmpresa();
		
		while(true) {
			System.out.println();
			System.out.println("O que deseja fazer:\n1. Criar Cargos\n2. Gerenciar Funcionarios\n3. Ver Empresa\nOutro. Sair");
			System.out.println();
			int opt = in.nextInt();
			in.nextLine();
			int opt2;
			if(opt == 1) {
				while(true){
					System.out.println();
					System.out.println("O que deseja fazer:\n1. Criar Novo Cargo\nOutro. Sair");
					System.out.println();
					opt2 = in.nextInt();
					in.nextLine();
					if(opt2 == 1) 
						sistema.cadastrarCargo();
					else
						break;
				}
			}
			else if(opt == 2) {
				while(true) {
					System.out.println();
					System.out.println("O que deseja fazer:\n1. Cadastrar Novo Funcionários\n2. Cadastrar Endereço de Funcionário\n3. Definir Cargo de Funcionário\n4. Checar detalhes salariais do Funcionário\nOutro. Sair");
					System.out.println();
					opt2 = in.nextInt();
					in.nextLine();
					if(opt2 == 1) 
						sistema.cadastrarFuncionario();
					else if(opt2 == 2){
							if(sistema.getEmpresa().listaFunc().isEmpty() == true)
								System.out.println("Cadastre Funcionários");
							else {
								System.out.println("Lista de Funcionários:");
								for(int i=0; i<sistema.getEmpresa().listaFunc().size(); i++)
									System.out.println(i+1 + " - " + sistema.getEmpresa().getFuncionario(i).getNome());
								System.out.println("Escolha a posição do funcionário:");
								System.out.println();
								int funcpos = in.nextInt();
								in.nextLine();
								
								sistema.cadastrarEnderecoFuncionario(funcpos-1);
							}
					}
					else if(opt2 == 3) {
						while(true) {
							System.out.println();
							System.out.println("O que deseja fazer:\n1. Dar cargo a novo funcionários\nOutro. Sair");
							System.out.println();
							int opt3 = in.nextInt();
							in.nextLine();
							if(opt3 == 1) {
								if(sistema.getEmpresa().listaCargo().isEmpty() == true)
									System.out.println("Cadastre Cargos");
								else if(sistema.getEmpresa().listaFunc().isEmpty() == true)
									System.out.println("Cadastre Funcionários");
								else {
									System.out.println("Lista de Funcionários:");
									for(int i=0; i<sistema.getEmpresa().listaFunc().size(); i++)
										System.out.println(i+1 + " - " + sistema.getEmpresa().getFuncionario(i).getNome());
									System.out.println("Escolha a posição do funcionário:");
									System.out.println();
									int funcpos = in.nextInt();
									in.nextLine();
									if(funcpos-1 > sistema.getEmpresa().listaFunc().size())
										System.out.println("Funcionário inválido");
									else {
										System.out.println("Lista de Cargos:");
										for(int i=0; i<sistema.getEmpresa().listaCargo().size(); i++)
											System.out.println(i+1 + " - " + sistema.getEmpresa().getCargo(i).getNome());
										System.out.println("Escolhe a posição do cargo que quer associar:");
										System.out.println();
										int cargopos = in.nextInt();
										in.nextLine();
										if(cargopos-1 > sistema.getEmpresa().listaCargo().size())
											System.out.println("Cargo inválido");
										else 
											sistema.defCargo(funcpos-1, cargopos-1);
									}
								}
							}
							else
								break;
						}
					}
					else if (opt2 == 4) {
						System.out.println("Escolha um funcionário:\n");
						for(int i=0; i<sistema.getEmpresa().listaFunc().size(); i++)
							System.out.println(i+1 + " - " + sistema.getEmpresa().getFuncionario(i).getNome());
						System.out.println("Escolha a posição do funcionário:");
						int funcpos = in.nextInt();
						if(funcpos-1 > sistema.getEmpresa().listaFunc().size())
							System.out.println("Funcionário inválido");
						else {
							while(true) {
								System.out.println();
								System.out.println("O que deseja ver:\n1. Salário Líquido\n2. Salário Bruto\n3. Valor Hora Extra\n4. Valor Beneficios\n5. Valor Desconto\nOutro. Sair");
								System.out.println();
								int opt4 = in.nextInt();
								in.nextLine();
								if(opt4 == 1) 
									sistema.showSalarioLiquidoFuncionario(funcpos-1);
								else if (opt4 == 2)
									sistema.showSalarioBrutoFuncionario(funcpos-1);
								else if (opt4 == 3)
									sistema.showValorHoraExtraFuncionario(funcpos-1);
								else if (opt4 == 4)
									sistema.showBenefícioFuncionario(funcpos-1);
								else if (opt4 == 5)
									sistema.showDescontoFuncionario(funcpos-1);
								else
									break;
							}
						}
					}
					else
						break;
				}
			}
			else if(opt == 3) {
				System.out.println("Empresa: " + sistema.getEmpresa().getNome());
				
				System.out.println("Funcionários cadastrados:");
				for(int i=0; i<sistema.getEmpresa().listaFunc().size(); i++)
					System.out.println(i+1 + " - " + sistema.getEmpresa().getFuncionario(i).getNome());
				
				System.out.println("Cargos cadastrados:");
				for(int i=0; i<sistema.getEmpresa().listaCargo().size(); i++)
					System.out.println(i+1 + " - " + sistema.getEmpresa().getCargo(i).getNome());
				
			}
			else
				break;
		}
	}
}
