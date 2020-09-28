package mongodb;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.nin;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.currentDate;
import static com.mongodb.client.model.Updates.set;

import java.util.ArrayList;
import java.util.Arrays;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;

import dados.Pessoa;

public class PessoaDAOMongo {

	private static PessoaDAOMongo instance = null;
	private static MongoCollection<Document> collection;
	
	public static PessoaDAOMongo getInstance() {
		
		if (instance == null) 
			instance = new PessoaDAOMongo();
		
		return instance;
	}
	
	private PessoaDAOMongo() {
		MongoDatabase conn = ConexaoMongo.getConexao();
		try {
			collection = conn.getCollection("Pessoas");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void insert(Pessoa pessoa) {
		try {
			Document document = new Document("nome", pessoa.getNome())
		               .append("cpf", pessoa.getCpf())
		               .append("data_nascimento", pessoa.getDtNasc())
		               .append("sexo", pessoa.getSexo())
		               .append("endereco", pessoa.getEndereco());
			collection.insertOne(document);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void update(Pessoa pessoa) {
		try {
			collection.updateOne(
	                eq("_id", pessoa.getIdPesMongo()),
	                combine(set("nome", pessoa.getNome()), set("sexo", pessoa.getSexo()), set("endereco", pessoa.getEndereco()),currentDate("lastModified")));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void delete(ObjectId id) {
		try {
			collection.deleteOne(eq("_id", id));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Pessoa select(ObjectId id) {
		Pessoa pessoa = new Pessoa();
		
		try {			
			Document newPessoa = collection.find(eq("_id", id))
								.first();
			if(newPessoa != null) {
				pessoa.setNome(newPessoa.getString("nome"));
				pessoa.setCpf(newPessoa.getString("cpf"));
				pessoa.setDtNasc(newPessoa.getDate("data_nascimento"));
				pessoa.setSexo(newPessoa.getString("sexo").charAt(0));
				pessoa.setEndereco(newPessoa.getString("endereco"));
				pessoa.setIdPesMongo(newPessoa.getObjectId("_id"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return pessoa;
	}
	
	public ArrayList<Pessoa> all() {
		ArrayList<Pessoa> pessoas = new ArrayList<Pessoa>();
		
		try {			
			MongoIterable<Document> newPes = collection.find();
			for(Document pes : newPes) {
				Pessoa pessoa = new Pessoa();
				
				pessoa.setNome(pes.getString("nome"));
				pessoa.setCpf(pes.getString("cpf"));
				pessoa.setDtNasc(pes.getDate("data_nascimento"));
				pessoa.setSexo(pes.getString("sexo").charAt(0));
				pessoa.setEndereco(pes.getString("endereco"));
				pessoa.setIdPesMongo(pes.getObjectId("_id"));
				
				pessoas.add(pessoa);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return pessoas;
	}
	
	//sqlselectcpf = conn.prepareStatement("select * from pessoas where cpf = ?");
	public Pessoa selectCpf(String cpf) {
		Pessoa pessoa = new Pessoa();
		
		try {
			Document newPessoa = collection.aggregate(Arrays.asList(
					new Document("$match", new Document("cpf", cpf))))
					.first();
			if(newPessoa != null) {
				pessoa.setNome(newPessoa.getString("nome"));
				pessoa.setCpf(newPessoa.getString("cpf"));
				pessoa.setDtNasc(newPessoa.getDate("data_nascimento"));
				pessoa.setSexo(newPessoa.getString("sexo").charAt(0));
				pessoa.setEndereco(newPessoa.getString("endereco"));
				pessoa.setIdPesMongo(newPessoa.getObjectId("_id"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pessoa;
	}
	
	//sqlselectonly = conn.prepareStatement("select id_pes from pessoas where id_pes not in (select id_pes from usuarios) and id_pes not in (select id_pes from pessoas)");
	public ArrayList<Pessoa> selectOnly() {
		ArrayList<Pessoa> pessoas = new ArrayList<Pessoa>();
		
		try {
			ArrayList<ObjectId> ids = FuncionarioDAOMongo.getInstance().pesIds();
			ids.addAll(UsuarioDAOMongo.getInstance().pesIds());
			
			MongoIterable<Document> pess = collection.find(nin("_id", ids));
			for(Document pes : pess) {
				Pessoa pessoa = new Pessoa();
				
				pessoa.setNome(pes.getString("nome"));
				pessoa.setCpf(pes.getString("cpf"));
				pessoa.setDtNasc(pes.getDate("data_nascimento"));
				pessoa.setSexo(pes.getString("sexo").charAt(0));
				pessoa.setEndereco(pes.getString("endereco"));
				pessoa.setIdPesMongo(pes.getObjectId("_id"));
				
				pessoas.add(pessoa);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return pessoas;
	}
	
	//sqlselectnotuser = conn.prepareStatement("select id_pes from pessoas where id_pes not in (select id_pes from usuarios)");
	public ArrayList<Pessoa> selectNotUser() {
		ArrayList<Pessoa> pessoas = new ArrayList<Pessoa>();
		
		try {
			ArrayList<ObjectId> ids = UsuarioDAOMongo.getInstance().pesIds();
			
			MongoIterable<Document> pess = collection.find(nin("_id", ids));
			for(Document pes : pess) {
				Pessoa pessoa = new Pessoa();
				
				pessoa.setNome(pes.getString("nome"));
				pessoa.setCpf(pes.getString("cpf"));
				pessoa.setDtNasc(pes.getDate("data_nascimento"));
				pessoa.setSexo(pes.getString("sexo").charAt(0));
				pessoa.setEndereco(pes.getString("endereco"));
				pessoa.setIdPesMongo(pes.getObjectId("_id"));
				
				pessoas.add(pessoa);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return pessoas;
	}
	
	//sqlselectnotfunc = conn.prepareStatement("select id_pes from pessoas where id_pes not in (select id_pes from pessoas)");
	public ArrayList<Pessoa> selectNotFunc() {
		ArrayList<Pessoa> pessoas = new ArrayList<Pessoa>();
		
		try {
			ArrayList<ObjectId> ids = FuncionarioDAOMongo.getInstance().pesIds();
			
			MongoIterable<Document> pess = collection.find(nin("_id", ids));
			for(Document pes : pess) {
				Pessoa pessoa = new Pessoa();
				
				pessoa.setNome(pes.getString("nome"));
				pessoa.setCpf(pes.getString("cpf"));
				pessoa.setDtNasc(pes.getDate("data_nascimento"));
				pessoa.setSexo(pes.getString("sexo").charAt(0));
				pessoa.setEndereco(pes.getString("endereco"));
				pessoa.setIdPesMongo(pes.getObjectId("_id"));
				
				pessoas.add(pessoa);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return pessoas;
	}
	
}
