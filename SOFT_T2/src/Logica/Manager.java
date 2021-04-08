package Logica;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import Dados.*;

public class Manager {

	ArrayList<Rotas> routeList;
	
	public Manager() {
		routeList = new ArrayList<Rotas>();
	}
	
	public Rotas routeFinder(String start, String end) {
		
		for(Rotas rota : routeList) {
			if(Collections.indexOfSubList(rota.getNextStop(), Arrays.asList(start, end)) >= 0) {
				return rota;
			}
		}
		
		return null;
	}
	
	public LocalTime routeTime(Rotas rota, String start, LocalTime current) {
		int position = 0;
		
		for(String next : rota.getNextStop()) {
			if(next.equals(start))
				position = rota.getNextStop().indexOf(next);
		}
		
		for(int i=position;i<rota.getSecondsNextStop().size();i++) {
			current.plusSeconds(rota.getSecondsNextStop().get(i));
		}
		
		return current;
	}
	
}
