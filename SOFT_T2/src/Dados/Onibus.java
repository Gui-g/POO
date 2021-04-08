package Dados;
import java.time.LocalTime;

public class Onibus {
	public int id;
	public String nextStop;
	public Rotas route;
	public LocalTime nextStopPred;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNextStop() {
		return nextStop;
	}
	public void setNextStop(String nextStop) {
		this.nextStop = nextStop;
	}
	public Rotas getRoute() {
		return route;
	}
	public void setRoute(Rotas route) {
		this.route = route;
	}
	public LocalTime getNextStopPred() {
		return nextStopPred;
	}
	public void setNextStopPred(LocalTime nextStopPred) {
		this.nextStopPred = nextStopPred;
	}
	
	public void changePred(int offset, String type) {
		switch(type) {
			case "hour":
				this.nextStopPred.plusHours(offset);
			case "minute":
				this.nextStopPred.plusMinutes(offset);
			case "second":
				this.nextStopPred.plusSeconds(offset);
			default:
		}
	}
}
