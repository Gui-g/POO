package dados;

import java.time.LocalTime;
import java.util.Date;

public class Trecho {

	private int numtrecho;
	private int duracao;
	private Date datapartida;
	private Date datachegada;
	private LocalTime horapartida;
	private LocalTime horachegada;
	private String classevoo;
	private int numpoltrona;
	private Trecho proximotrecho;
	
	public Trecho() {
		this.proximotrecho = null;
	}
	
	public Trecho(Trecho proximotrecho) {
		this.proximotrecho = proximotrecho;
	}
	
	public int getNumtrecho() {
		return numtrecho;
	}
	public void setNumtrecho(int numtrecho) {
		this.numtrecho = numtrecho;
	}
	public int getDuracao() {
		return duracao;
	}
	public void setDuracao(int duracao) {
		this.duracao = duracao;
	}
	public Date getDatapartida() {
		return datapartida;
	}
	public void setDatapartida(Date datapartida) {
		this.datapartida = datapartida;
	}
	public Date getDatachegada() {
		return datachegada;
	}
	public void setDatachegada(Date datachegada) {
		this.datachegada = datachegada;
	}
	public LocalTime getHorapartida() {
		return horapartida;
	}
	public void setHorapartida(LocalTime horapartida) {
		this.horapartida = horapartida;
	}
	public LocalTime getHorachegada() {
		return horachegada;
	}
	public void setHorachegada(LocalTime horachegada) {
		this.horachegada = horachegada;
	}
	public String getClassevoo() {
		return classevoo;
	}
	public void setClassevoo(String classevoo) {
		this.classevoo = classevoo;
	}
	public int getNumpoltrona() {
		return numpoltrona;
	}
	public void setNumpoltrona(int numpoltrona) {
		this.numpoltrona = numpoltrona;
	}
	public Trecho getProximotrecho() {
		return proximotrecho;
	}
	public void setProximotrecho(Trecho proximotrecho) {
		this.proximotrecho = proximotrecho;
	}
	
}
