public class App {

	public static void main(String[] args) {
		Turma turma = new Turma();	
		
		turma.setTurma();
		turma.printTurma();
		turma.setMedia();
		System.out.println("Media da turma: " + turma.getMedia());
	}
}
