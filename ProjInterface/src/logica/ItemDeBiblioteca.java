package logica;

public interface ItemDeBiblioteca {

	public boolean estaEmprestado();
	public boolean empresta();
	public void devolve();
	public String localizacao();
	public String titulo();	
}
