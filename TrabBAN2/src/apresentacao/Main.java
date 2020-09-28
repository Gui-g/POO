package apresentacao;

import java.text.ParseException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
	public static void main(String[] args) {
		
		Logger mongoLogger = Logger.getLogger( "org.mongodb.driver" );
		mongoLogger.setLevel(Level.SEVERE);
		
		Scanner in = new Scanner(System.in);
		Menu principal;
		MenuMongo principalMongo;
		System.out.println("1 - PGAdmin\n2 - MongoDB");
		int option = in.nextInt();
		switch(option) {
		case 1:
			try {
				principal = new Menu();
				principal.createMenu();
			} catch (ParseException e) {
				e.printStackTrace();
			};
			break;
		case 2:
			try {
				principalMongo = new MenuMongo();
				principalMongo.createMenu();
			} catch (ParseException e) {
				e.printStackTrace();
			};
			break;
		default:
			break;
		}
		
		in.close();
	}
}
