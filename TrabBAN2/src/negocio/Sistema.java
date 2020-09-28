package negocio;

import java.util.ArrayList;

import dados.Assistente;
import dados.Bibliotecario;
import dados.Categoria;
import dados.Colecao;
import dados.Editora;
import dados.Emprestimo;
import dados.Exemplar;
import dados.Funcionario;
import dados.Livro;
import dados.Pessoa;
import dados.Status;
import dados.Universidade;
import dados.Usuario;
import pgadmin.AssistenteDAO;
import pgadmin.BibliotecarioDAO;
import pgadmin.CategoriaDAO;
import pgadmin.ColecaoDAO;
import pgadmin.EditoraDAO;
import pgadmin.EmprestimoDAO;
import pgadmin.ExemplarDAO;
import pgadmin.FuncionarioDAO;
import pgadmin.LivroDAO;
import pgadmin.PessoaDAO;
import pgadmin.PublicadoDAO;
import pgadmin.StatusDAO;
import pgadmin.UniversidadeDAO;
import pgadmin.UsuarioDAO;

public class Sistema {

	private UsuarioDAO uDao;
	private BibliotecarioDAO bDao;
	private AssistenteDAO aDao;
	private PessoaDAO pDao;
	private FuncionarioDAO fDao;
	private EditoraDAO eDao;
	private UniversidadeDAO uniDao;
	private LivroDAO livroDao;
	private ExemplarDAO exDao;
	private CategoriaDAO catDao;
	private StatusDAO stDao;
	private ColecaoDAO colDao;
	private EmprestimoDAO empDao;
	private PublicadoDAO pubDao;
	
	public Sistema() {
	}

	public ArrayList<Livro> getLivros() {
		ArrayList<Livro> livros = LivroDAO.getInstance().all();
		return livros;
	}
	public ArrayList<Exemplar> getExemplares() {
		ArrayList<Exemplar> exemplares = ExemplarDAO.getInstance().all();
		return exemplares;
	}
	public ArrayList<Usuario> getUsuarios() {
		ArrayList<Usuario> usuarios = UsuarioDAO.getInstance().all();
		return usuarios;
	}
	public ArrayList<Bibliotecario> getBibliotecarios() {
		ArrayList<Bibliotecario> bibliotecarios = BibliotecarioDAO.getInstance().all();
		return bibliotecarios;
	}
	public ArrayList<Assistente> getAssistentes() {
		ArrayList<Assistente> assistentes = AssistenteDAO.getInstance().all();
		return assistentes;
	}
	public ArrayList<Emprestimo> getEmprestimos() {
		ArrayList<Emprestimo> emprestimos = EmprestimoDAO.getInstance().all();
		return emprestimos;
	}
	public ArrayList<Colecao> getColecoes() {
		ArrayList<Colecao> colecoes = ColecaoDAO.getInstance().all();
		return colecoes;
	}
	public ArrayList<Editora> getEditoras() {
		ArrayList<Editora> editoras = EditoraDAO.getInstance().all();
		return editoras;
	}
	public ArrayList<Status> getStatus() {
		ArrayList<Status> status = StatusDAO.getInstance().all();
		return status;
	}
	public ArrayList<Universidade> getUnis() {
		ArrayList<Universidade> unis = UniversidadeDAO.getInstance().all();
		return unis;
	}
	public ArrayList<Categoria> getCategorias() {
		ArrayList<Categoria> categorias = CategoriaDAO.getInstance().all();
		return categorias;
	}
	public ArrayList<Pessoa> getPessoas() {
		ArrayList<Pessoa> pessoas = PessoaDAO.getInstance().all();
		return pessoas;
	}
	public ArrayList<Funcionario> getFuncionario() {
		ArrayList<Funcionario> funcionarios = FuncionarioDAO.getInstance().all();
		return funcionarios;
	}

	public void insereUsuario(Usuario user) {
		uDao = UsuarioDAO.getInstance();
		
		uDao.insert(user);
	}
	public void atualizaUsuario(Usuario user) {
		uDao = UsuarioDAO.getInstance();
		
		uDao.update(user);
	}
	public void deletarUsuario(int id) {
		uDao = UsuarioDAO.getInstance();
		
		uDao.delete(id);
	}
	public Usuario selecionarUsuario(int id) throws Exception {
		uDao = UsuarioDAO.getInstance();
		boolean valido = false;
		for (Usuario user : uDao.all()) {
			if(user.getIdUser() == id) {
				valido = true;
			}
		}
		
		if(valido) {
			Usuario user = uDao.select(id);
			return user;
		} else {
			throw new Exception();
		}
	}
	public Usuario selecionarUsuarioIdPes(int id) throws Exception {
		uDao = UsuarioDAO.getInstance();
		boolean valido = false;
		for (Usuario user : uDao.all()) {
			if(user.getIdPes() == id) {
				valido = true;
			}
		}
		
		if(valido) {
			Usuario user = uDao.selectPes(id);
			return user;
		} else {
			throw new Exception();
		}
	}
	public ArrayList<Usuario> listarUserCat(int id) {
		uDao = UsuarioDAO.getInstance();
		
		ArrayList<Usuario> users = uDao.allCat(id);
		return users;
	}
	
	public void insereBibliotecario(Bibliotecario bib) {
		bDao = BibliotecarioDAO.getInstance();
		
		bDao.insert(bib);
	}
	public void deletarBibliotecario(int id) {
		bDao = BibliotecarioDAO.getInstance();
		
		bDao.delete(id);
	}
	public Bibliotecario selecionarBibliotecario(int id) throws Exception {
		bDao = BibliotecarioDAO.getInstance();
		boolean valido = false;
		for (Bibliotecario bib : bDao.all()) {
			if(bib.getIdBib() == id) {
				valido = true;
			}
		}
		
		if(valido) {
			Bibliotecario bib = bDao.select(id);
			return bib;
		} else {
			throw new Exception();
		}
	}
	public Bibliotecario selecionarBibliotecarioIdFunc(int id) throws Exception {
		bDao = BibliotecarioDAO.getInstance();
		boolean valido = false;
		for (Bibliotecario bib : bDao.all()) {
			if(bib.getIdFunc() == id) {
				valido = true;
			}
		}
		
		if(valido) {
			Bibliotecario bib = bDao.selectIdFunc(id);
			return bib;
		} else {
			throw new Exception();
		}
	}
	
	public void insereAssistente(Assistente assist) {
		aDao = AssistenteDAO.getInstance();
		
		aDao.insert(assist);
	}
	public void atualizaAssistente(Assistente assist) {
		aDao = AssistenteDAO.getInstance();
		
		aDao.update(assist);
	}
	public void deletarAssistente(int id) {
		aDao = AssistenteDAO.getInstance();
		
		aDao.delete(id);
	}
	public Assistente selecionarAssistente(int id) throws Exception {
		aDao = AssistenteDAO.getInstance();
		boolean valido = false;
		for (Assistente assist : aDao.all()) {
			if(assist.getIdAssist() == id) {
				valido = true;
			}
		}
		
		if(valido) {
			Assistente assist = aDao.select(id);
			return assist;
		} else {
			throw new Exception();
		}
	}
	public Assistente selecionarAssistenteIdFunc(int id) throws Exception {
		aDao = AssistenteDAO.getInstance();
		boolean valido = false;
		for (Assistente assist : aDao.all()) {
			if(assist.getIdAssist() == id) {
				valido = true;
			}
		}
		
		if(valido) {
			Assistente assist = aDao.selectIdFunc(id);
			return assist;
		} else {
			throw new Exception();
		}
	}
	
	public void inserePessoa(Pessoa pessoa) throws Exception {
		pDao = PessoaDAO.getInstance();
		
		for(Pessoa pessoaCpf : pDao.all()) {
			if(pessoaCpf.getCpf().equals(pessoa.getCpf()))
				throw new Exception();
		}
		
		pDao.insert(pessoa);
	}
	public void atualizaPessoa(Pessoa pessoa) {
		pDao = PessoaDAO.getInstance();
		
		pDao.update(pessoa);
	}
	public void deletarPessoa(int id) {
		pDao = PessoaDAO.getInstance();
		
		pDao.delete(id);
	}
	public Pessoa selecionarPessoa(int id) throws Exception {
		pDao = PessoaDAO.getInstance();
		boolean valido = false;
		for (Pessoa pessoa : pDao.all()) {
			if(pessoa.getIdPes() == id) {
				valido = true;
			}
		}
		
		if(valido) {
			Pessoa pessoa = pDao.select(id);
			return pessoa;
		} else {
			throw new Exception();
		}
	}
	public Pessoa selecionarPessoaCpf(String cpf) throws Exception {
		pDao = PessoaDAO.getInstance();
		boolean valido = false;
		for (Pessoa pessoa : pDao.all()) {
			if(pessoa.getCpf().equals(cpf)) {
				valido = true;
			}
		}
		
		if(valido) {
			Pessoa pessoa = pDao.selectcpf(cpf);
			return pessoa;
		} else {
			throw new Exception();
		}		
	}
	public ArrayList<Pessoa> selecionarPessoaOnly() {
		pDao = PessoaDAO.getInstance();
		
		ArrayList<Pessoa> pessoas = pDao.selectOnly();
		return pessoas;
	}
	public ArrayList<Pessoa> selecionarPessoaNotUser() {
		pDao = PessoaDAO.getInstance();
		
		ArrayList<Pessoa> pessoas = pDao.selectNotUser();
		return pessoas;
	}
	public ArrayList<Pessoa> selecionarPessoaNotFunc() {
		pDao = PessoaDAO.getInstance();
		
		ArrayList<Pessoa> pessoas = pDao.selectNotFunc();
		return pessoas;
	}
	
	public void insereFuncionario(Funcionario func) {
		fDao = FuncionarioDAO.getInstance();
		
		fDao.insert(func);
	}
	public void atualizaFuncionario(Funcionario func) {
		fDao = FuncionarioDAO.getInstance();
		
		fDao.update(func);
	}
	public void deletarFuncionario(int id) {
		fDao = FuncionarioDAO.getInstance();
		
		fDao.delete(id);
	}
	public Funcionario selecionarFuncionario(int id) throws Exception {
		fDao = FuncionarioDAO.getInstance();
		boolean valido = false;
		for (Funcionario func : fDao.all()) {
			if(func.getIdFunc() == id) {
				valido = true;
			}
		}
		
		if(valido) {
			Funcionario func = fDao.select(id);
			return func;
		} else {
			throw new Exception();
		}
	}
	public Funcionario selecionarFuncionarioId(int id) {
		fDao = FuncionarioDAO.getInstance();
		
		Funcionario func = fDao.selectIdPes(id);
		return func;
	}
	public ArrayList<Funcionario> selecionarFuncSemCargo() {
		fDao = FuncionarioDAO.getInstance();
		
		ArrayList<Funcionario> funcionarios = fDao.selectFuncNotAssign();
		return funcionarios;
	}
	
	public void insereEditora(Editora edi) throws Exception {
		eDao = EditoraDAO.getInstance();
		
		for(Editora editCnpj : eDao.all()) {
			if(editCnpj.getCnpj().equals(edi.getCnpj()))
				throw new Exception();
		}
		
		eDao.insert(edi);
	}
	public void atualizaEditora(Editora edi) {
		eDao = EditoraDAO.getInstance();
		
		eDao.update(edi);
	}
	public void deletarEditora(int id) {
		eDao = EditoraDAO.getInstance();
		
		eDao.delete(id);
	}
	public Editora selecionarEditora(int id) throws Exception {
		eDao = EditoraDAO.getInstance();
		boolean valido = false;
		for (Editora edit : eDao.all()) {
			if(edit.getIdEdi() == id) {
				valido = true;
			}
		}
		
		if(valido) {
			Editora edi = eDao.select(id);
			return edi;
		} else {
			throw new Exception();
		}
	}
	
	public void insereUniversidade(Universidade uni) {
		uniDao = UniversidadeDAO.getInstance();
		
		uniDao.insert(uni);
	}
	public void atualizaUniversidade(Universidade uni) {
		uniDao = UniversidadeDAO.getInstance();
		
		uniDao.update(uni);
	}
	public void deletarUniversidade(int id) throws Exception {
		uniDao = UniversidadeDAO.getInstance();
		
		for(Usuario user : this.getUsuarios()) {
			if(user.getUni().getIdUni() == id)
				throw new Exception();
		}
		
		uniDao.delete(id);
	}
	public Universidade selecionarUniversidade(int id) throws Exception {
		uniDao = UniversidadeDAO.getInstance();
		boolean valido = false;
		for (Universidade uni : UniversidadeDAO.getInstance().all()) {
			if(uni.getIdUni() == id) {
				valido = true;
			}
		}
		
		if(valido) {
			Universidade universidade = uniDao.select(id);
			return universidade;
		} else {
			throw new Exception();
		}
	}
	
	public void insereLivro(Livro livro) throws Exception {
		livroDao = LivroDAO.getInstance();
		
		for(Livro liv : LivroDAO.getInstance().all()) {
			if(liv.getIsbn().equals(livro.getIsbn())) {
				throw new Exception();
			}
		}
		
		livroDao.insert(livro);
	}
	public void atualizaLivro(Livro livro) {
		livroDao = LivroDAO.getInstance();
		
		livroDao.update(livro);
	}
	public void deletarLivro(int id) {
		livroDao = LivroDAO.getInstance();
		
		livroDao.delete(id);
	}
	public Livro selecionarLivro(int id) throws Exception {
		livroDao = LivroDAO.getInstance();
		boolean valido = false;
		for (Livro liv : LivroDAO.getInstance().all()) {
			if(liv.getIdLiv() == id) {
				valido = true;
			}
		}
		
		if(valido) {
			Livro livro = livroDao.select(id);
			return livro;
		} else {
			throw new Exception();
		}
	}
	
	public Livro selecionarLivroIsbn(String isbn) throws Exception {
		livroDao = LivroDAO.getInstance();
		boolean valido = false;
		for (Livro liv : LivroDAO.getInstance().all()) {
			if(liv.getIsbn().equals(isbn)) {
				valido = true;
			}
		}
		
		if(valido) {
			Livro livro = livroDao.selectIsbn(isbn);
			return livro;
		} else {
			throw new Exception();
		}
	}
	
	public void insereExemplar(Exemplar ex) {
		exDao = ExemplarDAO.getInstance();
		
		exDao.insert(ex);
	}
	public void atualizaExemplar(Exemplar ex) {
		exDao = ExemplarDAO.getInstance();
		
		exDao.update(ex);
	}
	public void deletarExemplar(int id_liv, int id_ex) {
		exDao = ExemplarDAO.getInstance();
		
		exDao.delete(id_liv, id_ex);
	}
	public Exemplar selecionarExemplar(int id_liv, int id_ex) throws Exception {
		exDao = ExemplarDAO.getInstance();
		boolean valido = false;
		for (Exemplar ex : ExemplarDAO.getInstance().all()) {
			if(ex.getIdLiv() == id_liv && ex.getIdEx() == id_ex) {
				valido = true;
			}
		}
		
		if(valido) {
			Exemplar ex = exDao.select(id_liv, id_ex);
			return ex;
		} else {
			throw new Exception();
		}
	}
	public ArrayList<Exemplar> listarExDisp(int id) {
		exDao = ExemplarDAO.getInstance();
		ArrayList<Exemplar> exemplares = new ArrayList<Exemplar>();
		
		for(Exemplar ex : this.getExemplares()) {
			if(!ex.getCol().getNome().equals("Reserva") && !ex.getStat().getNome().equals("Emprestado") && !ex.getStat().getNome().equals("Reservado") && ex.getIdLiv() == id)
				exemplares.add(ex);
		}
		
		return exemplares;
	}
	public ArrayList<Exemplar> listarExEmp(int id) {
		exDao = ExemplarDAO.getInstance();
		ArrayList<Exemplar> exemplares = new ArrayList<Exemplar>();
		
		for(Exemplar ex : this.getExemplares()) {
			if(ex.getStat().getNome().equals("Emprestado") || ex.getStat().getNome().equals("Reservado") && ex.getIdLiv() == id)
				exemplares.add(ex);
		}
		
		return exemplares;
	}
	
	public void insereCategoria(Categoria cat) {
		catDao = CategoriaDAO.getInstance();
		
		catDao.insert(cat);
	}
	public void atualizaCategoria(Categoria cat) {
		catDao = CategoriaDAO.getInstance();
		
		catDao.update(cat);
	}
	public void deletarCategoria(int id) throws Exception {
		catDao = CategoriaDAO.getInstance();
		
		for(Usuario user : this.getUsuarios()) {
			if(user.getCat().getIdCat() == id)
				throw new Exception();
		}
		
		catDao.delete(id);
	}
	public Categoria selecionarCategoria(int id) throws Exception {
		catDao = CategoriaDAO.getInstance();
		boolean valido = false;
		for (Categoria cat : CategoriaDAO.getInstance().all()) {
			if(cat.getIdCat() == id) {
				valido = true;
			}
		}
		
		if(valido) {
			Categoria cat = catDao.select(id);
			return cat;
		} else {
			throw new Exception();
		}
	}
	
	public void insereStatus(Status status) {
		stDao = StatusDAO.getInstance();
		
		stDao.insert(status);
	}
	public void atualizaStatus(Status status) {
		stDao = StatusDAO.getInstance();
		
		stDao.update(status);
	}
	public void deletarStatus(int id) throws Exception {
		stDao = StatusDAO.getInstance();
		
		for(Exemplar ex : this.getExemplares()) {
			if(ex.getStat().getIdSt() == id)
				throw new Exception();
		}
		
		stDao.delete(id);
	}
	public Status selecionarStatus(int id) throws Exception {
		stDao = StatusDAO.getInstance();
		boolean valido = false;
		for (Status sts : StatusDAO.getInstance().all()) {
			if(sts.getIdSt() == id) {
				valido = true;
			}
		}
		
		if(valido) {
			Status status = stDao.select(id);
			return status;
		} else {
			throw new Exception();
		}
	}
	
	public void insereColecao(Colecao col) {
		colDao = ColecaoDAO.getInstance();
		
		colDao.insert(col);
	}
	public void atualizaColecao(Colecao col) {
		colDao = ColecaoDAO.getInstance();
		
		colDao.update(col);
	}
	public void deletarColecao(int id) {
		colDao = ColecaoDAO.getInstance();
		
		colDao.delete(id);
	}
	public Colecao selecionarColecao(int id) throws Exception {
		colDao = ColecaoDAO.getInstance();
		boolean valido = false;
		for (Colecao col : ColecaoDAO.getInstance().all()) {
			if(col.getIdCol() == id) {
				valido = true;
			}
		}
		
		if(valido) {
			Colecao col = colDao.select(id);
			return col;
		} else {
			throw new Exception();
		}
	}
	
	public void insereEmprestimo(Emprestimo emprestimo) throws Exception {
		empDao = EmprestimoDAO.getInstance();
		
		for(Emprestimo emp : this.getEmprestimos()) {
			if(emp.getUser().getIdUser() == emprestimo.getUser().getIdUser()) {
				if(emp.getDiaEntrega() == null) {
					if(emprestimo.getDiaEmprestimo().compareTo(emp.limiteEntrega()) == 1)
						throw new Exception();
				}
			}
		}
		
		empDao.insert(emprestimo);
	}
	public void atualizaEmprestimo(Emprestimo emprestimo) throws Exception {
		empDao = EmprestimoDAO.getInstance();
		
		if(emprestimo.getRenov() > 3)
			throw new Exception();
		
		empDao.update(emprestimo);
	}
	public void deletarEmprestimo(int id) {
		empDao = EmprestimoDAO.getInstance();
		
		empDao.delete(id);
	}
	public Emprestimo selecionarEmprestimo(int id) throws Exception {
		empDao = EmprestimoDAO.getInstance();
		boolean valido = false;
		for (Emprestimo emp : EmprestimoDAO.getInstance().all()) {
			if(emp.getIdEmp() == id) {
				valido = true;
			}
		}
		
		if(valido) {
			Emprestimo emprestimo = empDao.select(id);
			return emprestimo;
		} else {
			throw new Exception();
		}
	}
	public ArrayList<Emprestimo> selecionarEmprestimoIdUser(int id) {
		empDao = EmprestimoDAO.getInstance();
		
		ArrayList<Emprestimo> emprestimo = empDao.selectIdUser(id);
		return emprestimo;
	}
	public ArrayList<Emprestimo> selecionarEmprestimoIdLiv(int id) {
		empDao = EmprestimoDAO.getInstance();
		
		ArrayList<Emprestimo> emprestimo = empDao.selectIdLiv(id);
		return emprestimo;
	}
	public ArrayList<Emprestimo> listarEmpUser(int id) {
		empDao = EmprestimoDAO.getInstance();
		
		ArrayList<Emprestimo> emprestimo = empDao.allEmpUser(id);
		return emprestimo;
	}
	public ArrayList<Emprestimo> listarEmpLiv(int id) {
		empDao = EmprestimoDAO.getInstance();
		
		ArrayList<Emprestimo> emprestimo = empDao.allEmpLiv(id);
		
		return emprestimo;
	}
	public ArrayList<Integer> listarUserEmp() {
		empDao = EmprestimoDAO.getInstance();
		
		ArrayList<Integer> emprestimo = empDao.selectIdUser();
		return emprestimo;
	}
	public ArrayList<Integer> listarLivEmp() {
		empDao = EmprestimoDAO.getInstance();
		
		ArrayList<Integer> emprestimo = empDao.selectidLiv();
		return emprestimo;
	}
	public ArrayList<Emprestimo> listarEmpNotEnt() {
		empDao = EmprestimoDAO.getInstance();
		
		ArrayList<Emprestimo> emprestimo = empDao.allNotEnt();
		return emprestimo;
	}
	
	public void inserePublicado(Livro liv) {
		pubDao = PublicadoDAO.getInstance();
		
		pubDao.insert(liv);
	}
	public void deletarPublicado(int id_liv, int id_edi) {
		pubDao = PublicadoDAO.getInstance();
		
		pubDao.delete(id_liv, id_edi);
	}
	public ArrayList<Editora> selecionarPublicado(int id) {
		pubDao = PublicadoDAO.getInstance();
		
		ArrayList<Editora> publicado = pubDao.selectEdi(id);
		return publicado;
	}

}
