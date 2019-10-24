package apresentacao;

import java.util.Scanner;
import dados.*;
import exceptions.InformacaoNaoEncontradaException;
import exceptions.ListaVaziaException;
import exceptions.PosicaoInvalidaException;
import negocio.*;

public class Principal {

    private static Scanner in = new Scanner(System.in);
    private static GerenciaPessoas gerencia = new GerenciaPessoas();

    public static void main(String[] args) throws Exception {
        boolean menu = true;
        int opt;
        int subopt;
        int subopt2;
        
        while(menu) {
        	System.out.println();
        	System.out.println("1 - Cadastrar Pessoa\n2 - Imprimir ID\n3 - Excluir Pessoa\n0 - Sair");
        	System.out.println();
        	opt = in.nextInt();
        	in.nextLine();
        	switch(opt) {
        		case 1:
        			System.out.println("1 - Cadastrar PF\n2 - Cadastrar PJ");
        			subopt = in.nextInt();
        			in.nextLine();
        			switch(subopt) {
        				case 1:
        					System.out.println("Cadastrar em posição específica? 1 (Sim)");
        					subopt2 = in.nextInt();
        					in.nextLine();
        					if(subopt2 == 1)
        						cadastrarPFnaPosicao();
        					else
        						cadastrarPF();
        					break;
        				case 2:
        					System.out.println("Cadastrar em posição específica? 1 (Sim)");
        					subopt2 = in.nextInt();
        					in.nextLine();
        					if(subopt2 == 1)
        						cadastrarPJnaPosicao();
        					else
        						cadastrarPJ();
        					break;
        				default:
        					break;
        			}
        			break;
        		case 2:
        			System.out.println("1 - Por Nome\n2 - Por Posição");
        			subopt = in.nextInt();
        			in.nextLine();
        			switch(subopt) {
        				case 1:
        					imprimirIdPessoaPorNome();
        					break;
        				case 2:
        					imprimirIdPessoanaPosicao();
        					break;
        				default:
        					break;
        			}
        			break;
        		case 3:
        			System.out.println("1 - Por Nome\n2 - Por Posição");
        			subopt = in.nextInt();
        			in.nextLine();
        			switch(subopt) {
        				case 1:
        					excluirPessoaPorNome();
        					break;
        				case 2:
        					excluirPessoaNaPosicao();
        					break;
        				default:
        					break;
        			}
        			break;
        		case 4:
        			gerencia.imprimirTudo();
        			break;
        		case 0:
        			menu = false;
        			break;
        		default:
        			break;
        	}
        }
    }

    public static void cadastrarPF() {
        PF pessoa = new PF();
        String nome;

        System.out.println("CPF:");
        pessoa.setCpf(in.nextLine());
        System.out.println("Nome:");
        nome = in.nextLine();
        pessoa.setNome(nome);

        gerencia.inserirFinalLista(pessoa);
    }

    public static void cadastrarPJ() {
        PJ pessoa = new PJ();
        String nome;

        System.out.println("CNPJ:");
        pessoa.setCnpj(in.nextLine());
        System.out.println("Nome:");
        nome = in.nextLine();
        pessoa.setNome(nome);

        gerencia.inserirFinalLista(pessoa);
    }

    public static void cadastrarPFnaPosicao() throws Exception {
        PF pessoa = new PF();
        String nome;
        int pos;

        System.out.println("CPF:");
        pessoa.setCpf(in.nextLine());
        System.out.println("Nome:");
        nome = in.nextLine();
        pessoa.setNome(nome);

        System.out.println("Posição:");
        pos = in.nextInt();
        in.nextLine();

        gerencia.inserirNaPosicao(pessoa, pos);
    }

    public static void cadastrarPJnaPosicao() throws Exception {
        PJ pessoa = new PJ();
        String nome;
        int pos;

        System.out.println("CNPJ:");
        pessoa.setCnpj(in.nextLine());
        System.out.println("Nome:");
        nome = in.nextLine();
        pessoa.setNome(nome);

        System.out.println("Posição:");
        pos = in.nextInt();
        in.nextLine();

        gerencia.inserirNaPosicao(pessoa, pos);
    }

    public static void imprimirIdPessoaPorNome() {
        String nome;

        System.out.println("Nome:");
        nome = in.nextLine();

        try {
            System.out.println(gerencia.pegarPorNome(nome).getId());
        } catch (InformacaoNaoEncontradaException e) {
        	System.out.println("Informação não encontrada");
        } catch (ListaVaziaException e) {
        	System.out.println("Lista Vazia");
        }
    }

    public static void imprimirIdPessoanaPosicao() {
        int pos;

        System.out.println("Posição:");
        pos = in.nextInt();
        in.nextLine();

        try {
            System.out.println(gerencia.pegarNaPosicao(pos).getId());
        } catch (ListaVaziaException e) {
        	System.out.println("Lista Vazia");
        } catch (PosicaoInvalidaException e) {
        	System.out.println("Posição Invalida");
        }
    }

    public static void excluirPessoaNaPosicao(){
        int pos;

        System.out.println("Posição:");
        pos = in.nextInt();
        in.nextLine();

        try {
            gerencia.excluirNaPosicao(pos);
        } catch (ListaVaziaException e) {
        	System.out.println("Lista Vazia");
        } catch (PosicaoInvalidaException e) {
        	System.out.println("Posição Invalida");
        }
    }

    public static void excluirPessoaPorNome(){
        String nome;

        System.out.println("Nome:");
        nome = in.nextLine();

        try {
            gerencia.excluirPorNome(nome);
        } catch (InformacaoNaoEncontradaException e) {
        	System.out.println("Informação não encontrada");
        } catch (ListaVaziaException e) {
        	System.out.println("Lista Vazia");
        }
    }

}