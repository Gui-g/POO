package exceptions;

public class PosicaoInvalidaException extends Exception{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public PosicaoInvalidaException() {

    }
    public PosicaoInvalidaException(String mensagem){
        super(mensagem);
    }

}