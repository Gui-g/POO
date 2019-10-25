package apresentacao;

import java.util.ArrayList;
import java.util.Scanner;

import dados.*;
import logica.ItemDeBiblioteca;

public class Biblioteca {

	private static String nome;
	private static Scanner in = new Scanner(System.in);
	private static ArrayList<ItemDeBiblioteca> itens = new ArrayList<ItemDeBiblioteca>();
	
	public static void main(String[] args) {
		 boolean menu = true;
	        while (menu) {
	            System.out.println(
	                    "\n\n1. cadastrar CD\n2. cadastrar Livro\n3. emprestar Item\n4. devolver Item\n5. mostrar Localizacao\n6. mostrar Detalhes\n0. Sair");
	            int opt = in.nextInt();
	            in.nextLine();
	            switch (opt) {
	            case 1:
	                catalogarCD();
	                break;
	            case 2:
	                catalogarLivro();
	                break;
	            case 3:
	                System.out.println("Titulo para empresar:");
	                emprestarItem(in.nextLine());
	                break;
	            case 4:
	                System.out.println("Titulo para devolver:");
	                devolverItem(in.nextLine());
	                break;
	            case 5:
	                System.out.println("Titulo para localizar:");
	                mostrarLocalizacao(in.nextLine());
	                break;
	            case 6:
	                System.out.println("Titulo mostrar detalhes:");
	                mostrarDetalhesItem(in.nextLine());
	                break;
	            case 0:
	            	menu = false;
	            	break;
	            default:
	            	break;
	            }
	        }
	}

	public static String getNome() {
		return nome;
	}

	public static void setNome(String nome) {
		Biblioteca.nome = nome;
	}
	
	public static void catalogarLivro() {
        System.out.println("Titulo:");
        String titulo = in.nextLine();
        System.out.println("Localização:");
        String localizacao = (in.nextLine());
        Livro aux = new Livro(titulo, localizacao);
        System.out.println("Autor:");
        aux.setAutor(in.nextLine());
        System.out.println("Número de paginas:");
        aux.setNumeroPaginas(in.nextInt());
        in.nextLine();
        System.out.println("Ano de edição:");
        aux.setAnoEdicao(in.nextInt());
        in.nextLine();
        itens.add(aux);
    }

    public static void catalogarCD() {
        System.out.println("Titulo:");
        String titulo = in.nextLine();
        System.out.println("Localização:");
        String localizacao = (in.nextLine());
        CD aux = new CD(titulo, localizacao);
        System.out.println("Nome do artista:");
        aux.setNomeArtista(in.nextLine());
        System.out.println("Duração:");
        aux.setDuracao(in.nextInt());
        in.nextLine();
        System.out.println("Número de musicas:");
        aux.setNumeroMusicas(in.nextInt());
        in.nextLine();
        System.out.println("Ano de gravação:");
        aux.setAnoGravacao(in.nextInt());
        in.nextLine();
        itens.add(aux);
    }

    public static void emprestarItem(String titulo) {
        for (ItemDeBiblioteca item : itens) {
            if (item.titulo().equals(titulo)) {
                item.empresta();
                break;
            }
        }
    }

    public static void devolverItem(String titulo) {
        for (ItemDeBiblioteca item : itens) {
            if (item.titulo().equals(titulo)) {
                item.devolve();
                break;
            }
        }
    }

    public static void mostrarLocalizacao(String titulo) {
        for (ItemDeBiblioteca item : itens) {
            if (item.titulo().equals(titulo)) {
                System.out.println(item.localizacao());
            }
        }
    }

    public static void mostrarDetalhesItem(String titulo) {
        for (ItemDeBiblioteca item : itens) {
            if (item.titulo().equals(titulo)) {
                if (item instanceof Livro) {
                    System.out.println(((Livro) item).toString());
                }
                if (item instanceof CD) {
                    System.out.println(((CD) item).toString());
                }
            }
        }
    }

}
