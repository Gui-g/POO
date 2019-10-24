package dados;

public class Comida extends Produto {

	private int numeroCalorias;
	private int peso;
	
	public int getNumeroCalorias() {
		return numeroCalorias;
	}
	public void setNumeroCalorias(int numeroCalorias) {
		this.numeroCalorias = numeroCalorias;
	}
	public int getPeso() {
		return peso;
	}
	public void setPeso(int peso) {
		this.peso = peso;
	}
	
	@Override
	public String toString() {
		return "[Nome = " + getNome() + ", Preço = R$" + getPreco() + ", Número Calorias = " + numeroCalorias + ", Peso = " + peso + "]";
	}
	
	
	
}
