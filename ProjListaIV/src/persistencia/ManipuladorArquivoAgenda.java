package persistencia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import dados.*;

public class ManipuladorArquivoAgenda implements ManipularArquivo<Contato> {

	private String caminho;
	private FileReader reader;
	private FileInputStream fis;
	private BufferedReader br;
	
	public String getCaminho() {
		return caminho;
	}
	/*
	public ManipuladorArquivoAgenda (String caminho) {
		this.caminho = caminho;
	}*/

	@Override
	public void abreArquivo(int opt) throws IOException {
		switch(opt) {
			case 1:
				reader = new FileReader("agenda.txt");
			case 2:
				fis = new FileInputStream("agenda.bin");
		}
	}

	@Override
	public void fechaArquivo() {
		try {
			reader.close();
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<Contato> carregaDadosArquivoTexto() {
		ArrayList<Contato> contatos = new ArrayList<Contato>();
		
		try {
			abreArquivo(1);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		br = new BufferedReader(reader);
		
		String dados;
		Contato contatoAux = new Contato();
		
		try {
			while((dados = br.readLine()) != null) {
				contatoAux = toObjeto(dados);
				contatos.add(contatoAux);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return contatos;
	}

	@Override
	public ArrayList<Contato> carregaDadosArquivoBinario() {
		ArrayList<Contato> contatos = new ArrayList<Contato>();
		
		try {
			abreArquivo(2);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		int singleCharInt;
		char singleChar;
		String obj = "a,1";
		Contato contatoAux = new Contato();
		
		try {
			while((singleCharInt = fis.read()) != -1) {
				if(singleCharInt >= 48 && singleCharInt <= 57 || singleCharInt >= 97 && singleCharInt <= 122) {
					singleChar = (char) singleCharInt;
					obj += singleChar;
				}
				else {
					contatoAux = toObjeto(obj);
					contatos.add(contatoAux);
					obj = "";
					singleChar = (char) singleCharInt;
					obj += singleChar;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return contatos;
	}

	@Override
	public void gravaDadosArquivoTexto(ArrayList<Contato> dados) throws IOException {
		String info;
		BufferedWriter writer = new BufferedWriter(new FileWriter("agenda.txt"));
		
		for(Contato contato : dados) {
			info = toLinhaArquivo(contato);
			writer.write(info + "\n");
		}
		writer.close();
	}

	@Override
	public void gravaDadosArquivoBinario(ArrayList<Contato> dados) throws IOException {
		String info;
		OutputStream oS = new FileOutputStream("agenda.bin");
	    
		for(Contato contato : dados) {
			info = toLinhaArquivo(contato);
			byte[] str = info.getBytes();
			oS.write(str);
		}
		
	    oS.close();
	}

	@Override
	public String toLinhaArquivo(Contato objeto) {
		return objeto.toString();
	}

	@Override
	public Contato toObjeto(String linhaArquivo) {
		String[] atributos = linhaArquivo.split(",");
		Contato novo = new Contato();
		novo.setNome(atributos[0]);
		novo.setTelefone(Integer.parseInt(atributos[1]));
		
		return novo;
	}
	
}
