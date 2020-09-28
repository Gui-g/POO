package mongodb;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.nin;
import static com.mongodb.client.model.Projections.fields;
import static com.mongodb.client.model.Projections.include;
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

import dados.Funcionario;

public class FuncionarioDAOMongo {

			private static FuncionarioDAOMongo instance = null;
			private static MongoCollection<Document> collection;
			
			public static FuncionarioDAOMongo getInstance() {
				
				if (instance == null) 
					instance = new FuncionarioDAOMongo();
				
				return instance;
			}
			
			private FuncionarioDAOMongo() {
				MongoDatabase conn = ConexaoMongo.getConexao();
				try {
					collection = conn.getCollection("Funcionarios");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			public void insert(Funcionario funcionario) {
				try {
					Document document = new Document("id_pes", funcionario.getIdPesMongo())
				               .append("salario", funcionario.getSalario())
				               .append("ctps", funcionario.getCtps())
				               .append("turno", funcionario.getTurno());
					collection.insertOne(document);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			public void update(Funcionario funcionario) {
				try {
					collection.updateOne(
			                eq("_id", funcionario.getIdFuncMongo()),
			                combine(set("salario", funcionario.getSalario()), set("ctps", funcionario.getCtps()), set("turno", funcionario.getTurno()), currentDate("lastModified")));
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
			
			public Funcionario select(ObjectId id) {
				Funcionario funcionario = new Funcionario();
				
				try {
					Document newFunc = collection.find(eq("_id", id))
							.first();
					if(newFunc != null) {
						funcionario.setIdFuncMongo(newFunc.getObjectId("_id"));
						funcionario.setIdPesMongo(newFunc.getObjectId("id_pes"));
						
						funcionario.setNome(PessoaDAOMongo.getInstance().select(newFunc.getObjectId("id_pes")).getNome());
						funcionario.setCpf(PessoaDAOMongo.getInstance().select(newFunc.getObjectId("id_pes")).getCpf());
						funcionario.setDtNasc(PessoaDAOMongo.getInstance().select(newFunc.getObjectId("id_pes")).getDtNasc());
						funcionario.setSexo(PessoaDAOMongo.getInstance().select(newFunc.getObjectId("id_pes")).getSexo());
						funcionario.setEndereco(PessoaDAOMongo.getInstance().select(newFunc.getObjectId("id_pes")).getEndereco());
						
						funcionario.setSalario(newFunc.getDouble("salario"));
						funcionario.setCtps(newFunc.getString("ctps"));
						funcionario.setTurno(newFunc.getString("turno").charAt(0));
						
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				return funcionario;
			}		
			
			public ArrayList<Funcionario> all() {
				ArrayList<Funcionario> funcionarios = new ArrayList<Funcionario>();
				
				try {
					MongoIterable<Document> newFunc = collection.find();
					for(Document func : newFunc) {
						Funcionario funcionario = new Funcionario();
						
						funcionario.setIdFuncMongo(func.getObjectId("_id"));
						funcionario.setIdPesMongo(func.getObjectId("id_pes"));
						
						funcionario.setNome(PessoaDAOMongo.getInstance().select(func.getObjectId("id_pes")).getNome());
						funcionario.setCpf(PessoaDAOMongo.getInstance().select(func.getObjectId("id_pes")).getCpf());
						funcionario.setDtNasc(PessoaDAOMongo.getInstance().select(func.getObjectId("id_pes")).getDtNasc());
						funcionario.setSexo(PessoaDAOMongo.getInstance().select(func.getObjectId("id_pes")).getSexo());
						funcionario.setEndereco(PessoaDAOMongo.getInstance().select(func.getObjectId("id_pes")).getEndereco());
						
						funcionario.setSalario(func.getDouble("salario"));
						funcionario.setCtps(func.getString("ctps"));
						funcionario.setTurno(func.getString("turno").charAt(0));
						
						funcionarios.add(funcionario);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				return funcionarios;
			}
			
		//"select * from funcionarios where id_pes = ?"
			public Funcionario selectIdPes(ObjectId idPes) {
				Funcionario funcionario = new Funcionario();
				
				try {
					Document newFunc = collection.aggregate(Arrays.asList(
							new Document("$match", new Document("id_pes", idPes))))
							.first();
					if(newFunc != null) {
						funcionario.setIdFuncMongo(newFunc.getObjectId("_id"));
						funcionario.setIdPesMongo(newFunc.getObjectId("id_pes"));
						
						funcionario.setNome(PessoaDAOMongo.getInstance().select(newFunc.getObjectId("id_pes")).getNome());
						funcionario.setCpf(PessoaDAOMongo.getInstance().select(newFunc.getObjectId("id_pes")).getCpf());
						funcionario.setDtNasc(PessoaDAOMongo.getInstance().select(newFunc.getObjectId("id_pes")).getDtNasc());
						funcionario.setSexo(PessoaDAOMongo.getInstance().select(newFunc.getObjectId("id_pes")).getSexo());
						funcionario.setEndereco(PessoaDAOMongo.getInstance().select(newFunc.getObjectId("id_pes")).getEndereco());
						
						funcionario.setSalario(newFunc.getDouble("salario"));
						funcionario.setCtps(newFunc.getString("ctps"));
						funcionario.setTurno(newFunc.getString("turno").charAt(0));
						
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				return funcionario;
			}	
			
		//"select id_func from funcionarios where id_func not in (select id_func from bibliotecarios) and id_func not in (select id_func from assistentes)"
		public ArrayList<Funcionario> selectFuncNotAssigned() {
			ArrayList<Funcionario> funcionarios = new ArrayList<Funcionario>();
			
			try {
				ArrayList<ObjectId> ids = BibliotecarioDAOMongo.getInstance().funcIds();
				ids.addAll(AssistenteDAOMongo.getInstance().funcIds());
				
				MongoIterable<Document> funcs = collection.find(nin("_id", ids));
				for(Document func : funcs) {
					Funcionario funcionario = new Funcionario();
					
					funcionario.setIdFuncMongo(func.getObjectId("_id"));
					funcionario.setIdPesMongo(func.getObjectId("id_pes"));
					
					funcionario.setNome(PessoaDAOMongo.getInstance().select(func.getObjectId("id_pes")).getNome());
					funcionario.setCpf(PessoaDAOMongo.getInstance().select(func.getObjectId("id_pes")).getCpf());
					funcionario.setDtNasc(PessoaDAOMongo.getInstance().select(func.getObjectId("id_pes")).getDtNasc());
					funcionario.setSexo(PessoaDAOMongo.getInstance().select(func.getObjectId("id_pes")).getSexo());
					funcionario.setEndereco(PessoaDAOMongo.getInstance().select(func.getObjectId("id_pes")).getEndereco());
					
					funcionario.setSalario(func.getDouble("salario"));
					funcionario.setCtps(func.getString("ctps"));
					funcionario.setTurno(func.getString("turno").charAt(0));
					
					funcionarios.add(funcionario);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return funcionarios;
		}
		
		public ArrayList<ObjectId> pesIds() {
			ArrayList<ObjectId> ids = new ArrayList<ObjectId>();
			
			try {
				MongoIterable<Document> pessoas = collection.find()
						.projection(fields(include("id_pes")));
				for(Document pes : pessoas) {
					ids.add(pes.getObjectId("id_pes"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return ids;
		}
}

