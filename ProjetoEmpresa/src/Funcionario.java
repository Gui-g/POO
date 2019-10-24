
public class Funcionario {
	
	private String nome;
	private Endereco endereco;
	private Cargo cargo;
	private int numhoraextra;
	private int numfilho;
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void setEndereco(Endereco end) {
		this.endereco = end;
	}
	
	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}
	
	public void setNumHoraExtra(int numhora) {
		this.numhoraextra = numhora;
	}
	
	public void setNumFilho(int numfil) {
		this.numfilho = numfil;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public Endereco getEndereco() {
		return this.endereco;
	}
	
	public Cargo getCargo() {
		return this.cargo;
	}
	
	public int setNumHoraExtra() {
		return this.numhoraextra;
	}
	
	public int setNumFilho() {
		return this.numfilho;
	}
	
	public double salAcrescimo() {
		return this.numfilho*this.cargo.getValFilho() + this.numhoraextra*this.cargo.getValHoraExtra();
	}
	
	public double INSS() {
		return this.cargo.getSalBase()*0.11;
	}
	
	public double RI() {
		if(this.cargo.getSalBase() < 1372.81)
			return 0*this.cargo.getSalBase();
		else if (this.cargo.getSalBase() > 2743.25)
			return 0.275*this.cargo.getSalBase();
		else
			return 0.15*this.cargo.getSalBase();
	}
	
	public double salDesconto() {
		return INSS() + RI();
	}
	
	public double salLiquido() {
		return this.cargo.getSalBase() + salAcrescimo() - salDesconto();
	}
	
	public double salBruto() {
		return this.cargo.getSalBase() + salAcrescimo();
	}
	
}
