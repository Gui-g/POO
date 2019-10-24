
public class Quadrado {

	private double medidaLados;

	public double getMedidaLados() {
		return medidaLados;
	}

	public void setMedidaLados(double medidaLados) {
		this.medidaLados = medidaLados;
	}
	
	public Quadrado(double medidaLados) {
		this.medidaLados = medidaLados;
	}
	
	public double getArea() {
		return medidaLados*medidaLados;
	}
	
}
