package negocio;

import dados.Pessoa;
import exceptions.*;

public class GerenciaPessoas {

    private int quantPessoas;
    private Nodo head;
    private Nodo tail;

    public GerenciaPessoas(){
        this.head = null;
        this.tail = null;
        quantPessoas = 0;
    }

    public int getQuantPessoas() {
        return quantPessoas;
    }

    public void setQuantPessoas(int quantPessoas) {
        this.quantPessoas = quantPessoas;
    }

    public Nodo getHead() {
        return head;
    }

    public void setHead(Nodo head) {
        this.head = head;
    }

    public Nodo getTail() {
        return tail;
    }

    public void setTail(Nodo tail) {
        this.tail = tail;
    }

    public void inserirFinalLista(Pessoa pessoa){
        if(head == null){
        	Nodo aux = new Nodo();
        	aux.setPessoa(pessoa);
            head = aux;
            tail = aux;
            quantPessoas++;
        }
        else{
            Nodo aux = new Nodo();
            aux.setPessoa(pessoa);
            tail.setProximo(aux);
            tail = aux;
            quantPessoas++; 
        }
    }

    public void inserirNaPosicao(Pessoa pessoa, int pos) throws PosicaoInvalidaException{

        Nodo aux = new Nodo();

        if(pos < 0 || pos > quantPessoas) {
            throw new PosicaoInvalidaException("Posição Invalida");
        }
        
        if(pos == 0){
            aux.setPessoa(pessoa);
            aux.setProximo(head);
            head = aux;
            quantPessoas++;
        }
        else if(pos == quantPessoas){
            inserirFinalLista(pessoa);
        }
        else{
            Nodo novo = new Nodo();
            novo.setPessoa(pessoa);
            aux = head;
            
            for(int i=0;i<pos-1;i++)
                aux = aux.getProximo();
            
            novo.setProximo(aux.getProximo());
            aux.setProximo(novo);
            quantPessoas++;
        }
    }
    
    public void excluirNaPosicao(int pos) throws PosicaoInvalidaException, ListaVaziaException{

        Nodo aux = new Nodo();

        if (quantPessoas == 0)
            throw new ListaVaziaException("Lista Vazia");
        if(pos < 0 || quantPessoas <= pos)
            throw new PosicaoInvalidaException("Posição Invalida");
        
        if(pos == 0){
            head = head.getProximo();
            quantPessoas--;
        }
        else if(pos == quantPessoas-1){
            aux = head;
            while(aux.getProximo() != tail)
                aux = aux.getProximo();
            tail = aux;
            tail.setProximo(null);
            quantPessoas--;
        }
        else{
            aux = head;
            for(int i=0; i<pos-1; i++)
                aux = aux.getProximo();
            aux.setProximo(aux.getProximo().getProximo());
            quantPessoas--;
        }
    }

    public void excluirPorNome(String nome) throws InformacaoNaoEncontradaException, ListaVaziaException{

        Nodo aux = new Nodo();
        Nodo delet = new Nodo();
        boolean existe = false;

        if(quantPessoas == 0)
            throw new ListaVaziaException("Lista Vazia");

        delet = head;
        while(delet != null){
           if(delet.getPessoa().getNome().equals(nome)){
               existe = true;
               break;
            }
           else {
        	   delet = delet.getProximo();
           }
        }

        if(!existe)
            throw new InformacaoNaoEncontradaException("Nome não encontrado");
        
        if(delet == head){
            head = head.getProximo();
            quantPessoas--;
        }
        else if(delet == tail){
            aux = head;
            while(aux.getProximo() != tail){
                aux = aux.getProximo();
            }
            tail = aux;
            aux.setProximo(null);
            quantPessoas--;
        }
        else{
            aux = head;
            while(aux.getProximo() != delet)
                aux = aux.getProximo();
            aux.setProximo(aux.getProximo().getProximo());
            quantPessoas--;
        }
    }

    public Pessoa pegarNaPosicao(int pos) throws PosicaoInvalidaException, ListaVaziaException{

        Nodo aux = new Nodo();

        if (quantPessoas == 0)
            throw new ListaVaziaException("Lista Vazia");
        if(pos < 0 || quantPessoas <= pos)
            throw new PosicaoInvalidaException("Posição Invalida");

        if(pos == 0){
            return head.getPessoa();
        }
        else if(pos == quantPessoas-1){
            return tail.getPessoa();
        }
        else{
            aux = head;
            for(int i=0; i<pos; i++)
                aux = aux.getProximo();
            return aux.getPessoa();
        }
    }

    public Pessoa pegarPorNome(String nome) throws InformacaoNaoEncontradaException, ListaVaziaException{

        Nodo busca = new Nodo();
        boolean existe = false;

        if(quantPessoas == 0)
            throw new ListaVaziaException("Lista Vazia");

        busca = head;
        while(busca != null){
           if(busca.getPessoa().getNome().equals(nome)){
               existe = true;
               return busca.getPessoa();
            }
            busca = busca.getProximo();
        }

        if(!existe)
            throw new InformacaoNaoEncontradaException("Nome não encontrado");
        
        return null;
    }
    
    public void imprimirTudo() {
    	Nodo busca = head;
    	while(busca != null){
    		System.out.println(busca.getPessoa().getId());
    		busca = busca.getProximo();
    	}
    }

}