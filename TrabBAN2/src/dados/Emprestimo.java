package dados;

import java.util.Date;

import org.bson.types.ObjectId;
import org.postgresql.util.PGInterval;

public class Emprestimo {

	private int idEmp;
	private ObjectId idEmpMongo;
	private Date diaEntrega;
	private Date diaEmprestimo;
	private int renov;
	private Exemplar ex;
	private Usuario user;
	private Bibliotecario bib;
	
	public Emprestimo() {
		this.diaEntrega = null;
		this.renov = 0;
	}

	public int getIdEmp() {
		return idEmp;
	}

	public void setIdEmp(int idEmp) {
		this.idEmp = idEmp;
	}

	public Date getDiaEntrega() {
		return diaEntrega;
	}

	public void setDiaEntrega(Date diaEntrega) {
		this.diaEntrega = diaEntrega;
	}

	public Date getDiaEmprestimo() {
		return diaEmprestimo;
	}

	public void setDiaEmprestimo(Date diaEmprestimo) {
		this.diaEmprestimo = diaEmprestimo;
	}

	public int getRenov() {
		return renov;
	}

	public void setRenov(int renov) {
		this.renov = renov;
	}

	public Exemplar getEx() {
		return ex;
	}

	public void setEx(Exemplar ex) {
		this.ex = ex;
	}

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}

	public Bibliotecario getBib() {
		return bib;
	}

	public void setBib(Bibliotecario bib) {
		this.bib = bib;
	}
	
	public Date limiteEntrega() {
		Date finalDia = (Date) diaEmprestimo.clone();
		PGInterval tempo = user.getCat().getTempo();
		tempo.setYears(tempo.getYears()*(this.renov+1));
		tempo.setMonths(tempo.getMonths()*(this.renov+1));
		tempo.setDays(tempo.getDays()*(this.renov+1));
		tempo.setHours(tempo.getHours()*(this.renov+1));
		tempo.setMinutes(tempo.getMinutes()*(this.renov+1));
		tempo.setSeconds(tempo.getSeconds()*(this.renov+1));
		tempo.add(finalDia);
		return finalDia;
	}

	@Override
	public String toString() {
		if(diaEntrega == null) {
		return "[id: " + idEmp + "\nPrazo Entrega: " + limiteEntrega() + "\nDia Emprestimo:" + diaEmprestimo
				+ "\nRenovações: " + renov + ", Livro: " + ex.getTitulo() + ", Id exemplar: " + ex.getIdEx() + "\nUsuario: " + user.getNome() + ", CPF: " + user.getCpf() + "\nBibliotecário: " + bib.getNome() + "]";
		} else {
			return "[id: " + idEmp + "\nDia Emprestimo:" + diaEmprestimo + "\nDia Entrega: " + diaEntrega
					+ "\nRenovações: " + renov + ", Livro: " + ex.getTitulo() + ", Id exemplar: " + ex.getIdEx() + "\nUsuario: " + user.getNome() + ", CPF: " + user.getCpf() + "\nBibliotecário: " + bib.getNome() + "]";
		}
	}

	public ObjectId getIdEmpMongo() {
		return idEmpMongo;
	}

	public void setIdEmpMongo(ObjectId idEmpMongo) {
		this.idEmpMongo = idEmpMongo;
	}
	
	
	
}
