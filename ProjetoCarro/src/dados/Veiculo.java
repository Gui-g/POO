package dados;

import java.util.Date;

public class Veiculo {

	private int anoFabricacao;
	private String modelo;
	private String numPlaca;
	private String cor;
	private String numChassi;
	private float quilometragem;
	private String marca;
	private int numMarchas;
	private float valorVeiculo;
	private Date dataEntrada;
	private Date dataVenda;
	private boolean vendido;
	
	public Veiculo() {
		this.vendido = false;
	}

	public int getAnoFabricacao() {
		return anoFabricacao;
	}

	public void setAnoFabricacao(int anoFabricacao) {
		this.anoFabricacao = anoFabricacao;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getNumPlaca() {
		return numPlaca;
	}

	public void setNumPlaca(String numPlaca) {
		this.numPlaca = numPlaca;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public String getNumChassi() {
		return numChassi;
	}

	public void setNumChassi(String numChassi) {
		this.numChassi = numChassi;
	}

	public float getQuilometragem() {
		return quilometragem;
	}

	public void setQuilometragem(float quilometragem) {
		this.quilometragem = quilometragem;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public int getNumMarchas() {
		return numMarchas;
	}

	public void setNumMarchas(int numMarchas) {
		this.numMarchas = numMarchas;
	}

	public float getValorVeiculo() {
		return valorVeiculo;
	}

	public void setValorVeiculo(float valorVeiculo) {
		this.valorVeiculo = valorVeiculo;
	}

	public Date getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(Date dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public Date getDataVenda() {
		return dataVenda;
	}

	public void setDataVenda(Date dataVenda) {
		this.dataVenda = dataVenda;
	}

	public boolean isVendido() {
		return vendido;
	}

	public void setVendido(boolean vendido) {
		this.vendido = vendido;
	}

	
	public String toString2() {
		StringBuilder builder = new StringBuilder();
		builder.append("[Ano de Fabricação =");
		builder.append(anoFabricacao);
		builder.append(", Modelo =");
		builder.append(modelo);
		builder.append(", Número da Placa =");
		builder.append(numPlaca);
		builder.append(", Cor =");
		builder.append(cor);
		builder.append(", Número do Chassi=");
		builder.append(numChassi);
		builder.append(", Quilometragem =");
		builder.append(quilometragem);
		builder.append(", Marca =");
		builder.append(marca);
		builder.append(", Número de Marchas =");
		builder.append(numMarchas);
		builder.append(", Valor do Veiculo = R$");
		builder.append(valorVeiculo);
		builder.append(", Data de Entrada =");
		builder.append(dataEntrada);
		builder.append("]");
		return builder.toString();
	}

	public String toString3() {
		return "[Número Placa = " + numPlaca + ", Ano de Fabricação = " + anoFabricacao + ", Modelo = " + modelo + ", Cor = " + cor + ", Marca =" + marca
				+ ", Valor do Veiculo = R$" + valorVeiculo + "]";
	}
	
	public String toString4() {
		if(vendido) {
			return "[Número Placa = " + numPlaca + ", Ano de Fabricação = " + anoFabricacao + ", Modelo = " + modelo + ", Cor = " + cor + ", Marca =" + marca
					+ ", Valor do Veiculo = R$" + valorVeiculo + ", Vendido = " + "Sim" + ", Data de Venda = " + dataVenda + "]";
		}
		else {
			return "[Número Placa = " + numPlaca + ", Ano de Fabricação = " + anoFabricacao + ", Modelo = " + modelo + ", Cor = " + cor + ", Marca =" + marca
					+ ", Valor do Veiculo = R$" + valorVeiculo + ", Vendido = " + "Não]";
		}
	}
	
	
	
	
	
}
