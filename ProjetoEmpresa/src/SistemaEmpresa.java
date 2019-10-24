import java.util.Scanner;

public class SistemaEmpresa {

	Scanner in = new Scanner(System.in);
	
	private Empresa empresa = new Empresa();
	
	public Empresa getEmpresa() {
		return this.empresa;
	}
	
	public void cadastrarEmpresa() {
		System.out.println("Nome da Empresa:");
		empresa.setNome(in.nextLine());
	}
	
	public void cadastrarEnderecoEmpresa() {
		System.out.println("Endereço da Empresa " + empresa.getNome());
		
		Endereco aux = new Endereco();
		System.out.println("CEP:");
		aux.setCep(in.nextLine());
		System.out.println("Nome da Rua:");
		aux.setRua(in.nextLine());
		System.out.println("Numero:");
		aux.setNum(in.nextInt());
		in.nextLine();
		System.out.println("Nome do Bairro:");
		aux.setBairro(in.nextLine());
		System.out.println("Nome da Cidade:");
		aux.setCidade(in.nextLine());
		System.out.println("Nome do Estado:");
		aux.setBairro(in.nextLine());
		
		empresa.setEndereco(aux);
	}
	
	public void cadastrarEnderecoFuncionario(int pos) {
		System.out.println("Endereço do Funcionario " + empresa.getFuncionario(pos).getNome());
		
		Endereco aux = new Endereco();
		in.nextLine();
		System.out.println("CEP:");
		aux.setCep(in.nextLine());
		System.out.println("Nome da Rua:");
		aux.setRua(in.nextLine());
		System.out.println("Numero:");
		aux.setNum(in.nextInt());
		in.nextLine();
		System.out.println("Nome do Bairro:");
		aux.setBairro(in.nextLine());
		System.out.println("Nome da Cidade:");
		aux.setCidade(in.nextLine());
		System.out.println("Nome do Estado:");
		aux.setBairro(in.nextLine());
		
		empresa.getFuncionario(pos).setEndereco(aux);
	}
	
	public void cadastrarFuncionario() {
		Funcionario aux = new Funcionario();
		
		System.out.println("Nome do Funcionario:");
		aux.setNome(in.next());
		System.out.println("Numero de Filhos:");
		aux.setNumFilho(in.nextInt());
		
		empresa.setFuncionario(aux);
	}
	
	public void cadastrarCargo() {
		Cargo aux = new Cargo();
		
		System.out.println("Nome do cargo:");
		aux.setNome(in.nextLine());
		System.out.println("Descrição:");
		aux.setDesc(in.nextLine());
		System.out.println("Salário Base:");
		aux.setSalBase(in.nextDouble());
		in.nextLine();
		System.out.println("Valor Hora Extra:");
		aux.setValHoraExtra(in.nextDouble());
		in.nextLine();
		System.out.println("Valor por Filho:");
		aux.setValFilho(in.nextDouble());
		in.nextLine();
		
		empresa.setCargo(aux);
	}
	
	public void defCargo(int posFunc, int posCargo) {
		empresa.getFuncionario(posFunc).setCargo(empresa.getCargo(posCargo));
	}
	
	public void showSalarioLiquidoFuncionario(int pos) {
		System.out.println("Salário Líquido do Funcionário " + empresa.getFuncionario(pos).getNome() + " = " + empresa.getFuncionario(pos).salLiquido());
	}
	
	public void showSalarioBrutoFuncionario(int pos) {
		System.out.println("Salário Bruto do Funcionário " + empresa.getFuncionario(pos).getNome() + " = " + empresa.getFuncionario(pos).salBruto());
	}
	
	public void showDescontoFuncionario(int pos) {
		System.out.println("Descontos do Funcionário " + empresa.getFuncionario(pos).getNome() + " = " + empresa.getFuncionario(pos).salDesconto());
	}
	
	public void showValorHoraExtraFuncionario(int pos) {
		System.out.println("Valor Hora Extra do Funcionário " + empresa.getFuncionario(pos).getNome() + " = " + empresa.getFuncionario(pos).getCargo().getValHoraExtra());
	}
	
	public void showBenefícioFuncionario(int pos) {
		System.out.println("Benefícios do Funcionário " + empresa.getFuncionario(pos).getNome() + " = " + empresa.getFuncionario(pos).salAcrescimo());
	}
	
}
