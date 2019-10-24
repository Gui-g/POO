package dados;

public class Viagem {

	private Onibus onibus;
	private Cidade origem;
	private Cidade destino;
	private Viagem volta;
	private int codigo;
	private boolean idaVolta;
	private float preco;
	
	public Viagem() {
		this.volta = null;
	}

	public Onibus getOnibus() {
		return onibus;
	}
	public void setOnibus(Onibus onibus) {
		this.onibus = onibus;
	}
	public Cidade getOrigem() {
		return origem;
	}
	public void setOrigem(Cidade origem) {
		this.origem = origem;
	}
	public Cidade getDestino() {
		return destino;
	}
	public void setDestino(Cidade destino) {
		this.destino = destino;
	}
	public Viagem getVolta() {
		return volta;
	}
	public void setVolta(Viagem volta) {
		this.volta = volta;
	}
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public boolean isIdaVolta() {
		return idaVolta;
	}

	public void setIdaVolta(boolean idaVolta) {
		this.idaVolta = idaVolta;
	}

	public float getPreco() {
		return preco;
	}

	public void setPreco(float preco) {
		this.preco = preco;
	}

	@Override
	public String toString() {
		if(idaVolta)
			return "[Codigo = " + codigo + ", Origem = " + origem.getNome() + ", Destino = " + destino.getNome() + ", idaVolta = " + "Sim"
				+ ", Preço = R$" + preco + "]";
		else
			return "[Codigo = " + codigo + ", Origem = " + origem.getNome() + ", Destino = " + destino.getNome() + ", idaVolta = " + "Não"
					+ ", Preço = R$" + preco + "]";
	}
	
	
	
}
