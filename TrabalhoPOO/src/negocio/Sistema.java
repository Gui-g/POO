package negocio;

import java.util.ArrayList;

import dados.*;
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
	
	public void insereDocumento(Documento documento, int contribuinte, int pj) {
		
		if(documento instanceof NotaFiscal ) {
			nfDao = NotaFiscalDAO.getInstance();
			nfDao.insert((NotaFiscal) documento, contribuintes.get(contribuinte).getId(), pjs.get(pj).getId());
		}
		else {
			chDao = ContrachequeDAO.getInstance();
			chDao.insert((Contracheque) documento, contribuintes.get(contribuinte).getId(), pjs.get(pj).getId());
		}
		
		contribuintes.get(contribuinte).getDocumentos().add(documento);
		pjs.get(pj).getDocumentos().add(documento);
	}
	
	public void inserirContribuinte(Contribuinte contribuinte) {
		cDao = ContribuinteDAO.getInstance();
		cDao.insert(contribuinte);
		
		contribuintes.add(contribuinte);
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
	
	public void inserirDependente(Dependente dep, int index) {		
		dDao = DependenteDAO.getInstance();
		dDao.insert(dep, contribuintes.get(index).getId());
		
		contribuintes.get(index).getDependentes().add(dep);
	}
	
	public void inserirPessoaJuridica(PessoaJuridica pj) {
		pjDao = PessoaJuridicaDAO.getInstance();
		pjDao.insert(pj);
		
		pjs.add(pj);
	}
	
	public void atualizaPessoaJuridica(PessoaJuridica pj) {
		pjDao = PessoaJuridicaDAO.getInstance();
		pjDao.update(pj);
		
		pjs = PessoaJuridicaDAO.getInstance().selectAll();
	}
	
	public void deletaPessoaJuridica(int ID) {
		PessoaJuridicaDAO.getInstance().delete(ID);
		
		pjs = PessoaJuridicaDAO.getInstance().selectAll();
	}
}
