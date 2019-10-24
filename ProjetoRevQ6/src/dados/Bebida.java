package dados;

public class Bebida extends Produto {

	private int quantidadeML;
	private boolean alcoolica;
	
	public Bebida() {
		alcoolica = false;
	}
	
	public int getQuantidadeML() {
		return quantidadeML;
	}
	public void setQuantidadeML(int quantidadeML) {
		this.quantidadeML = quantidadeML;
	}
	public boolean isAlcoolica() {
		return alcoolica;
	}
	public void setAlcoolica(boolean alcoolica) {
		this.alcoolica = alcoolica;
	}
	@Override
	public String toString() {
		if(alcoolica)
			return "[Nome = " + getNome() + ", Preço = R$" + getPreco() + ", Quantidade ML = " + quantidadeML + ", Alcoolica = " + "Sim" + "]";
		else
			return "[Nome = " + getNome() + ", Preço = R$" + getPreco() + ", Quantidade ML = " + quantidadeML + ", Alcoolica = " + "Não" + "]";
	}
	
	
	
}
