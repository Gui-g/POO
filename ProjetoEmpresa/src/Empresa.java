import java.util.ArrayList;

public class Empresa {

	private String nome;
	private Endereco endereco;
	private ArrayList<Funcionario> funcionarios = new ArrayList<Funcionario>();
	private ArrayList<Cargo> cargos = new ArrayList<Cargo>();
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void setEndereco(Endereco end) {
		this.endereco = end;
	}
	
	public void setFuncionario(Funcionario func) {
		this.funcionarios.add(func);
	}
	
	public void setCargo(Cargo cargo) {
		this.cargos.add(cargo);
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public Endereco getEndereco() {
		return this.endereco;
	}
	
	public Funcionario getFuncionario(int pos) {
		return this.funcionarios.get(pos);
	}
	
	public Cargo getCargo(int pos) {
		return this.cargos.get(pos);
	}
	
	public double mediaSalFunc() {
		double media = 0;
		for(Funcionario funcionario : funcionarios)
			media += funcionario.salLiquido();
		media /= funcionarios.size();
		
		return media;
	}
	
	public ArrayList<Funcionario> listaFunc(){
		return this.funcionarios;
	}
	
	public ArrayList<Cargo> listaCargo(){
		return this.cargos;
	}
	
}
