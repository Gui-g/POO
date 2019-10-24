
package exceptions;

public class ListaVaziaException extends Exception{


    /**
     *
     */
    private static final long serialVersionUID = 2L;

    public ListaVaziaException() {

    }
    public ListaVaziaException(String mensagem){
        super(mensagem);
    }
}