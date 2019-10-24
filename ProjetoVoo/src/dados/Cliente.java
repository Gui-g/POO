package dados;
import java.util.ArrayList;

public class Cliente {

	private int cpf;
	private String nome;
	private String endereco;
	private int telefone;
	private ArrayList<Reserva> reservas;
	
	public Cliente() {
		this.reservas = new ArrayList<Reserva>();
	}
	
	public void setCpf(int cpf) {
		this.cpf = cpf;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	public void setTelefone(int telefone) {
		this.telefone = telefone;
	}
	
	public void setReservas(ArrayList<Reserva> reservas) {
		this.reservas = reservas;
	}
	
	public int getCpf() {
		return this.cpf;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public String getEndereco() {
		return this.endereco;
	}
	
	public int getTelefone() {
		return this.telefone;
	}
	
	public Reserva getReserva(int num){
		return reservas.get(num);
	}
	
	public void reservarIda(Reserva reserva) {
		this.reservas.add(reserva);
	}
	
	public void reservarVolta(Reserva ida, Reserva volta) {
		ida.setVolta(volta);
	}
	
	public void reservarTrecho(Reserva reserva, Trecho trecho) {
		reserva.reservarTrecho(trecho);
	}
	
	public void reservarPoltrona(Reserva reserva, Trecho trecho, int poltrona) {
		reserva.reservarPoltrona(trecho, poltrona);
	}

	public ArrayList<Reserva> getReservas() {
		return reservas;
	}
	
}
