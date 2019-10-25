package persistencia;

import java.io.IOException;
import java.util.ArrayList;

public interface ManipularArquivo<T> {

	public void abreArquivo(int opt) throws IOException;
	public void fechaArquivo();
	public ArrayList<T> carregaDadosArquivoTexto();
	public ArrayList<T> carregaDadosArquivoBinario();
	public void gravaDadosArquivoTexto(ArrayList<T> dados) throws IOException;
	public void gravaDadosArquivoBinario(ArrayList<T> dados) throws IOException;
	public String toLinhaArquivo(T objeto);
	public T toObjeto(String linhaArquivo);
	
}
