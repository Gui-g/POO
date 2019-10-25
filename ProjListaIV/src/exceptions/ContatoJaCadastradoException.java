package exceptions;

public class ContatoJaCadastradoException extends Exception {

	/**
    *
    */
   private static final long serialVersionUID = 3L;

   public ContatoJaCadastradoException() {

   }
   public ContatoJaCadastradoException(String mensagem){
       super(mensagem);
   }
	
}
