package exceptions;

public class AgendaVaziaException extends Exception {

	/**
    *
    */
   private static final long serialVersionUID = 2L;

   public AgendaVaziaException() {

   }
   public AgendaVaziaException(String mensagem){
       super(mensagem);
   }
	
}
