package dados;

import logica.ItemDeBiblioteca;

public class CD implements ItemDeBiblioteca{

	private String titulo;
	private String nomeArtista;
	private int duracao;
	private int numeroMusicas;
	private int anoGravacao;
	private String localizacao;
	private boolean estaEmprestado;
	
	public CD(String titulo, String localizacao) {
		this.titulo = titulo;
		this.localizacao = localizacao;
		estaEmprestado = false;
	}
	
	public CD() {
		estaEmprestado = false;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getNomeArtista() {
		return nomeArtista;
	}

	public void setNomeArtista(String nomeArtista) {
		this.nomeArtista = nomeArtista;
	}

	public int getDuracao() {
		return duracao;
	}

	public void setDuracao(int duracao) {
		this.duracao = duracao;
	}

	public int getNumeroMusicas() {
		return numeroMusicas;
	}

	public void setNumeroMusicas(int numeroMusicas) {
		this.numeroMusicas = numeroMusicas;
	}

	public int getAnoGravacao() {
		return anoGravacao;
	}

	public void setAnoGravacao(int anoGravacao) {
		this.anoGravacao = anoGravacao;
	}

	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
	}

	public void setEstaEmprestado(boolean estaEmprestado) {
		this.estaEmprestado = estaEmprestado;
	}

	@Override
	public boolean estaEmprestado() {
		return this.estaEmprestado;
	}

	@Override
	public boolean empresta() {
		if(this.estaEmprestado){
            return false;
        }
        this.estaEmprestado = true;
        return true;
	}

	@Override
	public void devolve() {
		this.estaEmprestado = false;
	}

	@Override
	public String localizacao() {
		return this.localizacao;
	}

	@Override
	public String titulo() {
		return this.titulo;
	}

	@Override
	public String toString() {
		return "CD [Titulo = " + titulo + ", Nome do Artista = " + nomeArtista + ", Dura��o = " + duracao + ", N�mero de M�sicas = "
				+ numeroMusicas + ", Ano de Grava��o = " + anoGravacao + ", Localiza��o = " + localizacao + ", Est� Emprestado = "
				+ (estaEmprestado ? "Sim" : "N�o") + "]";
	}
	
	
	
}
