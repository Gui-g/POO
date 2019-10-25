package exceptions;

public class ContatoNaoEncontradoException extends Exception {
	
	/**
    *
    */
   private static final long serialVersionUID = 3L;

   public ContatoNaoEncontradoException() {

   }
   public ContatoNaoEncontradoException(String mensagem){
       super(mensagem);
   }

}
