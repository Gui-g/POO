package Dados;
import java.time.LocalTime;
import java.util.ArrayList;

public class Rotas {
	private LocalTime start;
	private ArrayList<String> nextStop;
	private ArrayList<Integer> secondsNextStop;
	
	public Rotas() {
		this.nextStop = new ArrayList<String>();
		this.secondsNextStop = new ArrayList<Integer>();
	}
	
	public LocalTime getStart() {
		return start;
	}
	public void setStart(LocalTime start) {
		this.start = start;
	}

	public ArrayList<String> getNextStop() {
		return nextStop;
	}

	public void setNextStop(ArrayList<String> nextStop) {
		this.nextStop = nextStop;
	}

	public ArrayList<Integer> getSecondsNextStop() {
		return secondsNextStop;
	}

	public void setSecondsNextStop(ArrayList<Integer> secondsNextStop) {
		this.secondsNextStop = secondsNextStop;
	}
}
