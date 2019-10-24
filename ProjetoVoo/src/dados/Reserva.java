package dados;
import java.util.ArrayList;
import java.util.Date;
import java.time.LocalTime;

public class Reserva {

	private int numreserva;
	private Date datavoo;
	private LocalTime horavoo;
	private float preco;
	private String classevoo;
	private boolean idaevolta;
	private Reserva volta;
	private ArrayList<Trecho> trechos;
	private Cidade origem;
	private Cidade destino;
	
	public Reserva(boolean idaevolta, ArrayList<Trecho> trechos) {
		this.volta = null;
		this.trechos = trechos;
	}

	public Reserva(boolean idaevolta) {
		this.idaevolta = idaevolta;
		this.volta = null;
		this.trechos = new ArrayList<Trecho>();
	}
	
	@Override
	public String toString() {
		return "Reserva [numreserva=" + numreserva + ", datavoo=" + datavoo + ", horavoo=" + horavoo + ", preco="
				+ preco + ", classevoo=" + classevoo + ", idaevolta=" + idaevolta + ", volta=" + volta + ", trechos="
				+ trechos + ", origem=" + origem + ", destino=" + destino + "]";
	}

	public void reservarTrecho(Trecho trecho) {
		trechos.add(trecho);
	}
	
	public void reservarPoltrona(Trecho trecho, int poltrona) {
		trecho.setNumpoltrona(poltrona);
	}

	public int getNumreserva() {
		return numreserva;
	}

	public void setNumreserva(int numreserva) {
		this.numreserva = numreserva;
	}

	public Date getDatavoo() {
		return datavoo;
	}

	public void setDatavoo(Date datavoo) {
		this.datavoo = datavoo;
	}

	public LocalTime getHoravoo() {
		return horavoo;
	}

	public void setHoravoo(LocalTime horavoo) {
		this.horavoo = horavoo;
	}

	public float getPreco() {
		return preco;
	}

	public void setPreco(float preco) {
		this.preco = preco;
	}

	public String getClassevoo() {
		return classevoo;
	}

	public void setClassevoo(String classevoo) {
		this.classevoo = classevoo;
	}

	public boolean isIdaevolta() {
		return idaevolta;
	}

	public void setIdaevolta(boolean idaevolta) {
		this.idaevolta = idaevolta;
	}

	public Reserva getVolta() {
		return volta;
	}

	public void setVolta(Reserva volta) {
		this.volta = volta;
	}

	public ArrayList<Trecho> getTrechos() {
		return trechos;
	}

	public void setTrechos(ArrayList<Trecho> trechos) {
		this.trechos = trechos;
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
	
}
