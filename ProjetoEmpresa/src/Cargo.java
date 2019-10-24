
public class Cargo {

	private String nome;
	private String description;
	private double salbase;
	private double valhoraextra;
	private double valfilho;
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void setDesc(String desc) {
		this.description = desc;
	}
	
	public void setSalBase(double sal) {
		this.salbase = sal;
	}
	
	public void setValHoraExtra(double valhora) {
		this.valhoraextra = valhora;
	}
	
	public void setValFilho(double valfil) {
		this.valfilho = valfil;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public String getDesc() {
		return this.description;
	}
	
	public double getSalBase() {
		return this.salbase;
	}
	
	public double getValHoraExtra() {
		return this.valhoraextra;
	}
	
	public double getValFilho() {
		return this.valfilho;
	}
	
}
