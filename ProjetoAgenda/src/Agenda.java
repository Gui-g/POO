
public class Agenda {

	private Contato[] contatos;
	private int quantidadeContato = 0;

	public Agenda() {
		this.contatos = new Contato[15];
	}

	public Contato[] getContatos() {
		return contatos;
	}

	public void setContatos(Contato[] contatos) {
		this.contatos = contatos;
	}
	
	public int getQuantContatos() {
		return this.quantidadeContato;
	}
	
	public void setContato(Contato novoContato) {
		contatos[quantidadeContato] = new Contato();
		insereContato(novoContato.getTelefone(), novoContato.getNome(), novoContato.getEmail(), novoContato.getEndereco());
	}
	
	public Contato getContato(int pos) {
		return this.contatos[pos];
	}
	
	public void insereContato(int telefone, String nome, String email, String endereco) {
		contatos[quantidadeContato].setTelefone(telefone);
		contatos[quantidadeContato].setEndereco(endereco);
		contatos[quantidadeContato].setNome(nome);
		contatos[quantidadeContato].setEmail(email);
		quantidadeContato++;
	}
	
	public Contato consultaContato(int telefone) {
		for(int i=0; i<quantidadeContato; i++) {
			if(telefone == contatos[i].getTelefone())
				return contatos[i];
		}
		return null;
	}
	
	public void removeContato(int telefone) {
		int rm = 16;
		for(int i=0; i<quantidadeContato; i++) {
			if(telefone == contatos[i].getTelefone()){
				rm = i;
			}
		}
		
		for(int i=0; i<quantidadeContato-1;i++) {
			if(i >= rm) {
				contatos[i].setTelefone(contatos[i+1].getTelefone());
				contatos[i].setNome(contatos[i+1].getNome());
				contatos[i].setEndereco(contatos[i+1].getEndereco());
				contatos[i].setEmail(contatos[i+1].getEmail());
			}
		}
		
		if(rm < 15) {
			contatos[quantidadeContato] = null;
			quantidadeContato--;
		}
	}
	
}
