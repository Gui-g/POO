package negocio;

import java.util.ArrayList;

import dados.*;
import persistencia.BemDAO;
import persistencia.ContrachequeDAO;
import persistencia.ContribuinteDAO;
import persistencia.DependenteDAO;
import persistencia.NotaFiscalDAO;
import persistencia.PessoaJuridicaDAO;

public class Sistema {
	
	private ArrayList<Contribuinte> contribuintes;
	private ArrayList<PessoaJuridica> pjs;
	private PessoaJuridicaDAO pjDao;
	private DependenteDAO dDao;
	private NotaFiscalDAO nfDao;
	private ContrachequeDAO chDao;
	private ContribuinteDAO cDao;
	private BemDAO bDao;
	
	public Sistema() {
		contribuintes = ContribuinteDAO.getInstance().selectAll();
		pjs = PessoaJuridicaDAO.getInstance().selectAll();
	}
	
	public ArrayList<Contribuinte> getContribuintes() {
		return contribuintes;
	}
	public void setContribuintes(ArrayList<Contribuinte> contribuintes) {
		this.contribuintes = contribuintes;
	}
	public ArrayList<PessoaJuridica> getPjs() {
		return pjs;
	}
	public void setPjs(ArrayList<PessoaJuridica> pjs) {
		this.pjs = pjs;
	}
	
	public void insereNotaFiscal(NotaFiscal nota, int contribuinte, int pj) {
		nfDao = NotaFiscalDAO.getInstance();
		nfDao.insert(nota, contribuintes.get(contribuinte).getId(), pjs.get(pj).getId());
	}
	
	public void insereContracheque(Contracheque cheque, int contribuinte, int pj) {
		chDao = ContrachequeDAO.getInstance();
		chDao.insert(cheque, contribuintes.get(contribuinte).getId(), pjs.get(pj).getId());
	}
	
	public void deletaNotaFiscal(int ID) {
		NotaFiscalDAO.getInstance().delete(ID);
	}
	
	public void deletaContracheque(int ID) {
		ContrachequeDAO.getInstance().delete(ID);
	}
	
	public void inserirContribuinte(Contribuinte contribuinte) throws Exception {
		
		if(ContribuinteDAO.getInstance().selectAll().isEmpty())
			ContribuinteDAO.getInstance().restart();
		
		cDao = ContribuinteDAO.getInstance();		
		
		for(Contribuinte pessoa : ContribuinteDAO.getInstance().selectAll()) {
			if(contribuinte.getCpf().equals(pessoa.getCpf()))
				throw new Exception();
		}
		
		for(Contribuinte pessoa : ContribuinteDAO.getInstance().selectAll()) {
			for(Dependente dep : DependenteDAO.getInstance().selectId(pessoa.getId())) {
				if(dep.getCpf().equals(contribuinte.getCpf()))
					throw new Exception();
			}
		}
		
		cDao.insert(contribuinte);
		
		contribuintes = ContribuinteDAO.getInstance().selectAll();
	}
	
	public void atualizaContribuinte(Contribuinte contribuinte) {
		cDao = ContribuinteDAO.getInstance();
		cDao.update(contribuinte);
		
		contribuintes = ContribuinteDAO.getInstance().selectAll();
	}
	
	public void deletaContribuinte(int ID) {
		ContribuinteDAO.getInstance().delete(ID);
		
		contribuintes = ContribuinteDAO.getInstance().selectAll();
	}
	
	public void inserirDependente(Dependente dep, int id) throws Exception {		
		dDao = DependenteDAO.getInstance();
		dDao.insert(dep, id);
		
		for(Contribuinte pessoa : ContribuinteDAO.getInstance().selectAll()) {
			if(dep.getCpf().equals(pessoa.getCpf()))
				throw new Exception();
		}
		
		for(Contribuinte pessoa : ContribuinteDAO.getInstance().selectAll()) {
			for(Dependente dependente : DependenteDAO.getInstance().selectId(pessoa.getId())) {
				if(dep.getCpf().equals(dependente.getCpf()))
					throw new Exception();
			}
		}
	}
	
	public void atualizarDependente(Dependente dependente) {
		dDao = DependenteDAO.getInstance();
		dDao.update(dependente);
	}
	
	public void deletarDependente(int ID) {		
		DependenteDAO.getInstance().delete(ID);
	}
	
	public void inserirPessoaJuridica(PessoaJuridica pj) throws Exception {
		pjDao = PessoaJuridicaDAO.getInstance();
		
		if(PessoaJuridicaDAO.getInstance().selectAll().isEmpty())
			PessoaJuridicaDAO.getInstance().restart();
		
		for(PessoaJuridica pessoa : PessoaJuridicaDAO.getInstance().selectAll()) {
			if(pj.getCnpj().equals(pessoa.getCnpj()))
				throw new Exception();
		}
		
		pjDao.insert(pj);
		
		pjs = PessoaJuridicaDAO.getInstance().selectAll();
	}
	
	public void atualizaPessoaJuridica(PessoaJuridica pj) {
		pjDao = PessoaJuridicaDAO.getInstance();
		pjDao.update(pj);
		
		pjs = PessoaJuridicaDAO.getInstance().selectAll();
	}
	
	public void deletaPessoaJuridica(int ID) {
		
		NotaFiscalDAO.getInstance().deletePj(ID);
		ContrachequeDAO.getInstance().deletePj(ID);
		PessoaJuridicaDAO.getInstance().delete(ID);
		
		pjs = PessoaJuridicaDAO.getInstance().selectAll();
	}
	
	public void inserirBem(Bem bem, int id) {
		bDao = BemDAO.getInstance();
		bDao.insert(bem, id);
	}
	
	public void atualizarBem(Bem bem) {
		bDao = BemDAO.getInstance();
		bDao.update(bem);
	}
	
	public void deletarBem(int ID) {
		BemDAO.getInstance().delete(ID);
	}
	
	public ArrayList<Dependente> buscaDependente(int ID) {
		dDao = DependenteDAO.getInstance();
		ArrayList<Dependente> dependentes = dDao.selectId(ID);
		return dependentes;
	}
	
	public ArrayList<NotaFiscal> buscaNotaFiscal(int ID) {
		nfDao = NotaFiscalDAO.getInstance();
		ArrayList<NotaFiscal> notas = nfDao.selectId(ID);
		return notas;
	}
	
	public ArrayList<NotaFiscal> buscaNotaFiscalPj(int ID) {
		nfDao = NotaFiscalDAO.getInstance();
		ArrayList<NotaFiscal> notas = nfDao.selectPj(ID);
		return notas;
	}
	
	public ArrayList<Contracheque> buscaContracheque(int ID) {
		chDao = ContrachequeDAO.getInstance();
		ArrayList<Contracheque> cheques = chDao.selectId(ID);
		return cheques;
	}
	
	public ArrayList<Contracheque> buscaContrachequePJ(int ID) {
		chDao = ContrachequeDAO.getInstance();
		ArrayList<Contracheque> cheques = chDao.selectPj(ID);
		return cheques;
	}
	
	public ArrayList<Bem> buscaBem(int ID) {
		bDao = BemDAO.getInstance();
		ArrayList<Bem> bens = bDao.selectId(ID);
		return bens;
	}
}
