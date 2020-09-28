package negocio;

import java.util.ArrayList;

import org.bson.types.ObjectId;

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
import mongodb.AssistenteDAOMongo;
import mongodb.BibliotecarioDAOMongo;
import mongodb.CategoriaDAOMongo;
import mongodb.ColecaoDAOMongo;
import mongodb.EditoraDAOMongo;
import mongodb.EmprestimoDAOMongo;
import mongodb.ExemplarDAOMongo;
import mongodb.FuncionarioDAOMongo;
import mongodb.LivroDAOMongo;
import mongodb.PessoaDAOMongo;
import mongodb.StatusDAOMongo;
import mongodb.UniversidadeDAOMongo;
import mongodb.UsuarioDAOMongo;

public class SistemaMongo {
	private UsuarioDAOMongo uDao;
	private BibliotecarioDAOMongo bDao;
	private AssistenteDAOMongo aDao;
	private PessoaDAOMongo pDao;
	private FuncionarioDAOMongo fDao;
	private EditoraDAOMongo eDao;
	private UniversidadeDAOMongo uniDao;
	private LivroDAOMongo livroDao;
	private CategoriaDAOMongo catDao;
	private StatusDAOMongo stDao;
	private ColecaoDAOMongo colDao;
	private ExemplarDAOMongo exDao;
	private EmprestimoDAOMongo empDao;
	
	public SistemaMongo() {
	}

	public ArrayList<Livro> getLivros() {
		ArrayList<Livro> livros = LivroDAOMongo.getInstance().all();
		return livros;
	}
	public ArrayList<Exemplar> getExemplares() {
		ArrayList<Exemplar> exemplares = ExemplarDAOMongo.getInstance().all();
		return exemplares;
	}
	public ArrayList<Usuario> getUsuarios() {
		ArrayList<Usuario> usuarios = UsuarioDAOMongo.getInstance().all();
		return usuarios;
	}
	public ArrayList<Bibliotecario> getBibliotecarios() {
		ArrayList<Bibliotecario> bibliotecarios = BibliotecarioDAOMongo.getInstance().all();
		return bibliotecarios;
	}
	public ArrayList<Assistente> getAssistentes() {
		ArrayList<Assistente> assistentes = AssistenteDAOMongo.getInstance().all();
		return assistentes;
	}
	public ArrayList<Emprestimo> getEmprestimos() {
		ArrayList<Emprestimo> emprestimos = EmprestimoDAOMongo.getInstance().all();
		return emprestimos;
	}
	public ArrayList<Colecao> getColecoes() {
		ArrayList<Colecao> colecoes = ColecaoDAOMongo.getInstance().all();
		return colecoes;
	}
	public ArrayList<Editora> getEditoras() {
		ArrayList<Editora> editoras = EditoraDAOMongo.getInstance().all();
		return editoras;
	}
	public ArrayList<Status> getStatus() {
		ArrayList<Status> status = StatusDAOMongo.getInstance().all();
		return status;
	}
	public ArrayList<Universidade> getUnis() {
		ArrayList<Universidade> unis = UniversidadeDAOMongo.getInstance().all();
		return unis;
	}
	public ArrayList<Categoria> getCategorias() {
		ArrayList<Categoria> categorias = CategoriaDAOMongo.getInstance().all();
		return categorias;
	}
	public ArrayList<Pessoa> getPessoas() {
		ArrayList<Pessoa> pessoas = PessoaDAOMongo.getInstance().all();
		return pessoas;
	}
	public ArrayList<Funcionario> getFuncionario() {
		ArrayList<Funcionario> funcionarios = FuncionarioDAOMongo.getInstance().all();
		return funcionarios;
	}

	public void insereUsuario(Usuario user) {
		uDao = UsuarioDAOMongo.getInstance();
		
		uDao.insert(user);
	}
	public void atualizaUsuario(Usuario user) {
		uDao = UsuarioDAOMongo.getInstance();
		
		uDao.update(user);
	}
	public void deletarUsuario(ObjectId id) {
		uDao = UsuarioDAOMongo.getInstance();
		
		uDao.delete(id);
	}
	public Usuario selecionarUsuario(ObjectId id) throws Exception {
		uDao = UsuarioDAOMongo.getInstance();
		
		Usuario user = uDao.select(id);
		return user;
	}
	public Usuario selecionarUsuarioIdPes(ObjectId id) {
		uDao = UsuarioDAOMongo.getInstance();
		
		Usuario user = uDao.selectIdPes(id);
		return user;
	}
	public ArrayList<Usuario> listarUserCat(ObjectId id) {
		uDao = UsuarioDAOMongo.getInstance();
		
		ArrayList<Usuario> users = uDao.selectIdCat(id);
		return users;
	}
	
	public void insereBibliotecario(Bibliotecario bib) {
		bDao = BibliotecarioDAOMongo.getInstance();
		
		bDao.insert(bib);
	}
	public void deletarBibliotecario(ObjectId id) {
		bDao = BibliotecarioDAOMongo.getInstance();
		
		bDao.delete(id);
	}
	public Bibliotecario selecionarBibliotecario(ObjectId id) throws Exception {
		bDao = BibliotecarioDAOMongo.getInstance();
		
		
		Bibliotecario bib = bDao.select(id);
		return bib;
	}
	public Bibliotecario selecionarBibliotecarioIdFunc(ObjectId id) throws Exception {
		bDao = BibliotecarioDAOMongo.getInstance();
		
		Bibliotecario bib = bDao.selectIdFunc(id);
		return bib;
	}
	public void insereAssistente(Assistente assist) {
		aDao = AssistenteDAOMongo.getInstance();
		
		aDao.insert(assist);
	}
	public void atualizaAssistente(Assistente assist) {
		aDao = AssistenteDAOMongo.getInstance();
		
		aDao.update(assist);
	}
	public void deletarAssistente(ObjectId id) {
		aDao = AssistenteDAOMongo.getInstance();
		
		aDao.delete(id);
	}
	public Assistente selecionarAssistente(ObjectId id) throws Exception {
		aDao = AssistenteDAOMongo.getInstance();
		
		Assistente assist = aDao.select(id);
		return assist;
	}
	public Assistente selecionarAssistenteIdFunc(ObjectId id) throws Exception {
		aDao = AssistenteDAOMongo.getInstance();
		
		Assistente assist = aDao.selectIdFunc(id);
		return assist;
	}
	
	public void inserePessoa(Pessoa pessoa) throws Exception {
		pDao = PessoaDAOMongo.getInstance();
		
		for(Pessoa pessoaCpf : pDao.all()) {
			if(pessoaCpf.getCpf().equals(pessoa.getCpf()))
				throw new Exception();
		}
		
		pDao.insert(pessoa);
	}
	public void atualizaPessoa(Pessoa pessoa) {
		pDao = PessoaDAOMongo.getInstance();
		
		pDao.update(pessoa);
	}
	public void deletarPessoa(ObjectId id) {
		pDao = PessoaDAOMongo.getInstance();
		
		pDao.delete(id);
	}
	public Pessoa selecionarPessoa(ObjectId id) {
		pDao = PessoaDAOMongo.getInstance();
		
		Pessoa pessoa = pDao.select(id);
		return pessoa;
	}
	public Pessoa selecionarPessoaCpf(String cpf) throws Exception {
		pDao = PessoaDAOMongo.getInstance();
		
		Pessoa pessoa = pDao.selectCpf(cpf);
		return pessoa;
	}
	public ArrayList<Pessoa> selecionarPessoaOnly() {
		pDao = PessoaDAOMongo.getInstance();
		
		ArrayList<Pessoa> pessoas = pDao.selectOnly();
		return pessoas;
	}
	public ArrayList<Pessoa> selecionarPessoaNotUser() {
		pDao = PessoaDAOMongo.getInstance();
		
		ArrayList<Pessoa> pessoas = pDao.selectNotUser();
		return pessoas;
	}
	public ArrayList<Pessoa> selecionarPessoaNotFunc() {
		pDao = PessoaDAOMongo.getInstance();
		
		ArrayList<Pessoa> pessoas = pDao.selectNotFunc();
		return pessoas;
	}
	
	public void insereFuncionario(Funcionario func) {
		fDao = FuncionarioDAOMongo.getInstance();
		
		fDao.insert(func);
	}
	public void atualizaFuncionario(Funcionario func) {
		fDao = FuncionarioDAOMongo.getInstance();
		
		fDao.update(func);
	}
	public void deletarFuncionario(ObjectId id) {
		fDao = FuncionarioDAOMongo.getInstance();
		
		fDao.delete(id);
	}
	public Funcionario selecionarFuncionario(ObjectId id) {
		fDao = FuncionarioDAOMongo.getInstance();
		
		Funcionario func = fDao.select(id);
		return func;
	}
	public Funcionario selecionarFuncionarioId(ObjectId id) {
		fDao = FuncionarioDAOMongo.getInstance();
		
		Funcionario func = fDao.selectIdPes(id);
		return func;
	}
	public ArrayList<Funcionario> selecionarFuncSemCargo() {
		fDao = FuncionarioDAOMongo.getInstance();
		
		ArrayList<Funcionario> funcionarios = fDao.selectFuncNotAssigned();
		return funcionarios;
	}
	
	public void insereEditora(Editora edi) throws Exception {
		eDao = EditoraDAOMongo.getInstance();
		
		eDao.insert(edi);
	}
	public void atualizaEditora(Editora edi) {
		eDao = EditoraDAOMongo.getInstance();
		
		eDao.update(edi);
	}
	public void deletarEditora(ObjectId id) {
		eDao = EditoraDAOMongo.getInstance();
		
		eDao.delete(id);
	}
	public Editora selecionarEditora(ObjectId id) throws Exception {
		eDao = EditoraDAOMongo.getInstance();
		
		Editora edi = eDao.select(id);
		return edi;
	}
	
	public void insereUniversidade(Universidade uni) {
		uniDao = UniversidadeDAOMongo.getInstance();
		
		uniDao.insert(uni);
	}
	public void atualizaUniversidade(Universidade uni) {
		uniDao = UniversidadeDAOMongo.getInstance();
		
		uniDao.update(uni);
	}
	public void deletarUniversidade(ObjectId id) throws Exception {
		uniDao = UniversidadeDAOMongo.getInstance();
		
		uniDao.delete(id);
	}
	public Universidade selecionarUniversidade(ObjectId id) throws Exception {
		uniDao = UniversidadeDAOMongo.getInstance();
		
		Universidade universidade = uniDao.select(id);
		return universidade;
	}
	
	public void insereLivro(Livro livro) throws Exception {
		livroDao = LivroDAOMongo.getInstance();
		
		for(Livro liv : LivroDAOMongo.getInstance().all()) {
			if(liv.getIsbn().equals(livro.getIsbn())) {
				throw new Exception();
			}
		}
		
		livroDao.insert(livro);
	}
	public void atualizaLivro(Livro livro) {
		livroDao = LivroDAOMongo.getInstance();
		
		livroDao.update(livro);
	}
	public void deletarLivro(ObjectId id) {
		livroDao = LivroDAOMongo.getInstance();
		
		livroDao.delete(id);
	}
	public Livro selecionarLivro(ObjectId id) throws Exception {
		livroDao = LivroDAOMongo.getInstance();
		
		Livro livro = livroDao.select(id);
		return livro;
	}

	public Livro selecionarLivroIsbn(String isbn) throws Exception {
		livroDao = LivroDAOMongo.getInstance();
		boolean valido = false;
		for (Livro liv : LivroDAOMongo.getInstance().all()) {
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
		exDao = ExemplarDAOMongo.getInstance();
		
		exDao.insert(ex);
	}
	public void atualizaExemplar(Exemplar ex) {
		exDao = ExemplarDAOMongo.getInstance();
		
		exDao.update(ex);
	}
	public void deletarExemplar(ObjectId id) {
		exDao = ExemplarDAOMongo.getInstance();
		
		exDao.delete(id);
	}
	public Exemplar selecionarExemplar(ObjectId id_ex) throws Exception {
		exDao = ExemplarDAOMongo.getInstance();
		
		Exemplar ex = exDao.select(id_ex);
		return ex;
	}
	public ArrayList<Exemplar> listarExDisp(ObjectId id) {
		exDao = ExemplarDAOMongo.getInstance();
		ArrayList<Exemplar> exemplares = new ArrayList<Exemplar>();
		
		for(Exemplar ex : this.getExemplares()) {
			if(!ex.getCol().getNome().equals("Reserva") && !ex.getStat().getNome().equals("Emprestado") && !ex.getStat().getNome().equals("Reservado") && ex.getIdLivMongo().equals(id))
				exemplares.add(ex);
		}
		
		return exemplares;
	}
	public ArrayList<Exemplar> listarExEmp(ObjectId id) {
		exDao = ExemplarDAOMongo.getInstance();
		ArrayList<Exemplar> exemplares = new ArrayList<Exemplar>();
		
		for(Exemplar ex : this.getExemplares()) {
			if(ex.getStat().getNome().equals("Emprestado") || ex.getStat().getNome().equals("Reservado") && ex.getIdLivMongo().equals(id))
				exemplares.add(ex);
		}
		
		return exemplares;
	}

	public void insereCategoria(Categoria cat) {
		catDao = CategoriaDAOMongo.getInstance();
		
		catDao.insert(cat);
	}
	public void atualizaCategoria(Categoria cat) {
		catDao = CategoriaDAOMongo.getInstance();
		
		catDao.update(cat);
	}
	public void deletarCategoria(ObjectId id) throws Exception {
		catDao = CategoriaDAOMongo.getInstance();
		
		catDao.delete(id);
	}
	public Categoria selecionarCategoria(ObjectId id) throws Exception {
		catDao = CategoriaDAOMongo.getInstance();
		
		Categoria cat = catDao.select(id);
		return cat;
	}
	
	public void insereStatus(Status status) {
		stDao = StatusDAOMongo.getInstance();
		
		stDao.insert(status);
	}
	public void atualizaStatus(Status status) {
		stDao = StatusDAOMongo.getInstance();
		
		stDao.update(status);
	}
	public void deletarStatus(ObjectId id) throws Exception {
		stDao = StatusDAOMongo.getInstance();
		
		stDao.delete(id);
	}
	public Status selecionarStatus(ObjectId id) throws Exception {
		stDao = StatusDAOMongo.getInstance();
	
		Status status = stDao.select(id);
		return status;
	}
	
	public void insereColecao(Colecao col) {
		colDao = ColecaoDAOMongo.getInstance();
		
		colDao.insert(col);
	}
	public void atualizaColecao(Colecao col) {
		colDao = ColecaoDAOMongo.getInstance();
		
		colDao.update(col);
	}
	public void deletarColecao(ObjectId id) {
		colDao = ColecaoDAOMongo.getInstance();
		
		colDao.delete(id);
	}
	public Colecao selecionarColecao(ObjectId id) throws Exception {
		colDao = ColecaoDAOMongo.getInstance();
		
		Colecao col = colDao.select(id);
		return col;
	}

	public void insereEmprestimo(Emprestimo emprestimo) throws Exception {
		empDao = EmprestimoDAOMongo.getInstance();
		
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
		empDao = EmprestimoDAOMongo.getInstance();
		
		if(emprestimo.getRenov() > 3)
			throw new Exception();
		
		empDao.update(emprestimo);
	}
	public void deletarEmprestimo(ObjectId id) {
		empDao = EmprestimoDAOMongo.getInstance();
		
		empDao.delete(id);
	}
	public Emprestimo selecionarEmprestimo(ObjectId id) throws Exception {
		empDao = EmprestimoDAOMongo.getInstance();

		Emprestimo emprestimo = empDao.select(id);
		return emprestimo;
	}
	public ArrayList<Emprestimo> selecionarEmprestimoIdUser(ObjectId id) {
		empDao = EmprestimoDAOMongo.getInstance();
		
		ArrayList<Emprestimo> emprestimo = empDao.selectIdUser(id);
		return emprestimo;
	}
	public ArrayList<Emprestimo> selecionarEmprestimoIdLiv(ObjectId id) {
		empDao = EmprestimoDAOMongo.getInstance();
		
		ArrayList<Emprestimo> emprestimo = empDao.selectIdLiv(id);
		return emprestimo;
	}
	public ArrayList<Emprestimo> listarEmpUser(ObjectId id) {
		empDao = EmprestimoDAOMongo.getInstance();
		
		ArrayList<Emprestimo> emprestimo = empDao.allEmpUser(id);
		return emprestimo;
	}
	public ArrayList<Emprestimo> listarEmpLiv(ObjectId id) {
		empDao = EmprestimoDAOMongo.getInstance();
		
		ArrayList<Emprestimo> emprestimo = empDao.allEmpLiv(id);
		
		return emprestimo;
	}
	public ArrayList<ObjectId> listarUserEmp() {
		empDao = EmprestimoDAOMongo.getInstance();
		
		ArrayList<ObjectId> emprestimo = empDao.selectIdUser();
		return emprestimo;
	}
	public ArrayList<ObjectId> listarLivEmp() {
		empDao = EmprestimoDAOMongo.getInstance();
		
		ArrayList<ObjectId> emprestimo = empDao.selectidLiv();
		return emprestimo;
	}
	public ArrayList<Emprestimo> listarEmpNotEnt() {
		empDao = EmprestimoDAOMongo.getInstance();
		
		ArrayList<Emprestimo> emprestimo = empDao.allNotEnt();
		return emprestimo;
	}
}
