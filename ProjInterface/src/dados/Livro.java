package dados;

import logica.ItemDeBiblioteca;

public class Livro implements ItemDeBiblioteca{

	private String titulo;
	private String autor;
	private int numeroPaginas;
	private int anoEdicao;
	private String localizacao;
	private boolean estaEmprestado;
	
	public Livro(String titulo, String localizacao) {
		this.titulo = titulo;
		this.localizacao = localizacao;
		estaEmprestado = false;
	}
	
	public Livro() {
		estaEmprestado = false;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public int getNumeroPaginas() {
		return numeroPaginas;
	}

	public void setNumeroPaginas(int numeroPaginas) {
		this.numeroPaginas = numeroPaginas;
	}

	public int getAnoEdicao() {
		return anoEdicao;
	}

	public void setAnoEdicao(int anoEdicao) {
		this.anoEdicao = anoEdicao;
	}

	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
	}

	public void setEstaEmprestado(boolean estaEmprestado) {
		this.estaEmprestado = estaEmprestado;
	}

	@Override
	public boolean estaEmprestado(){
        return this.estaEmprestado;
    }

	@Override
    public boolean empresta(){
        if(this.estaEmprestado){
            return false;
        }
        this.estaEmprestado = true;
        return true;
    }

	@Override
    public void devolve(){
        this.estaEmprestado = false;
    }

	@Override
    public String localizacao(){
        return this.localizacao;
    }

	@Override
    public String titulo(){
        return this.titulo;
    }

	@Override
	public String toString() {
		return "Livro [Titulo = " + titulo + ", Autor = " + autor + ", Número de Páginas = " + numeroPaginas + ", Ano Edição = "
				+ anoEdicao + ", Localização = " + localizacao + ", Esta Emprestado = " + (estaEmprestado ? "Sim" : "Não") + "]";
	}
	
	
	
}
