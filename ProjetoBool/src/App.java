public class App {
	
	public static void main(String[] args) {
		
		int[] numeros = {1,0,1};
		E operacao = new E(numeros);
		
		System.out.println(operacao.num());
		
		boolean[] mentira = {false};
		boolean[] meiaverdade = {true,false};
		OU or1 = new OU(meiaverdade);
		OU or2 = new OU(mentira);
		OU or3 = new OU(new boolean[] {or1.bool(), or2.bool()});
		
		if(or3.bool())
			System.out.println("X");
		
	}
	
}
