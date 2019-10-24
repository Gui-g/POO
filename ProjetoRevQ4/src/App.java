
public class App {
	
	public static void main(String[] args) {
		System.out.println("Quadrado:");
		Quadrado quad = new Quadrado(2);
		System.out.println("Área = " + quad.getArea());
		System.out.println("Cubo:");
		Cubo cubo = new Cubo(2);
		System.out.println("Área = " + cubo.getArea());
	}
	
}
