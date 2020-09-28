package mongodb;

import static com.mongodb.client.model.Filters.eq;
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

import dados.Assistente;

public class AssistenteDAOMongo {

			private static AssistenteDAOMongo instance = null;
			private static MongoCollection<Document> collection;
			
			public static AssistenteDAOMongo getInstance() {
				
				if (instance == null) 
					instance = new AssistenteDAOMongo();
				
				return instance;
			}
			
			private AssistenteDAOMongo() {
				MongoDatabase conn = ConexaoMongo.getConexao();
				try {
					collection = conn.getCollection("Assistentes");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			public void insert(Assistente assistente) {
				try {
					Document document = new Document("id_func", assistente.getIdFuncMongo())
				               .append("id_bib", assistente.getSupervisor().getIdBibMongo());
					collection.insertOne(document);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			public void update(Assistente assistente) {
				try {
					collection.updateOne(
			                eq("_id", assistente.getIdAssistMongo()),
			                combine(set("id_bib", assistente.getSupervisor().getIdBibMongo()), currentDate("lastModified")));
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
			
			public Assistente select(ObjectId id) {
				Assistente assistente = new Assistente();
				
				try {
					Document newAssist = collection.find(eq("_id", id))
							.first();
					if(newAssist != null) {
						assistente.setIdAssistMongo(newAssist.getObjectId("_id"));
						assistente.setIdFuncMongo(newAssist.getObjectId("id_func"));
						
						assistente.setNome(FuncionarioDAOMongo.getInstance().select(newAssist.getObjectId("id_func")).getNome());
						assistente.setCpf(FuncionarioDAOMongo.getInstance().select(newAssist.getObjectId("id_func")).getCpf());
						assistente.setDtNasc(FuncionarioDAOMongo.getInstance().select(newAssist.getObjectId("id_func")).getDtNasc());
						assistente.setSexo(FuncionarioDAOMongo.getInstance().select(newAssist.getObjectId("id_func")).getSexo());
						assistente.setEndereco(FuncionarioDAOMongo.getInstance().select(newAssist.getObjectId("id_func")).getEndereco());
						
						assistente.setSalario(FuncionarioDAOMongo.getInstance().select(newAssist.getObjectId("id_func")).getSalario());
						assistente.setCtps(FuncionarioDAOMongo.getInstance().select(newAssist.getObjectId("id_func")).getCtps());
						assistente.setTurno(FuncionarioDAOMongo.getInstance().select(newAssist.getObjectId("id_func")).getTurno());
						
						assistente.setSupervisor(BibliotecarioDAOMongo.getInstance().select(newAssist.getObjectId("id_bib")));
						
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				return assistente;
			}			
			
			public ArrayList<Assistente> all() {
				ArrayList<Assistente> assistentes = new ArrayList<Assistente>();
				
				try {
					MongoIterable<Document> newAssist = collection.find();
					for(Document assist : newAssist) {
						Assistente assistente = new Assistente();
						
						assistente.setIdAssistMongo(assist.getObjectId("_id"));
						assistente.setIdFuncMongo(assist.getObjectId("id_func"));
						
						assistente.setNome(FuncionarioDAOMongo.getInstance().select(assist.getObjectId("id_func")).getNome());
						assistente.setCpf(FuncionarioDAOMongo.getInstance().select(assist.getObjectId("id_func")).getCpf());
						assistente.setDtNasc(FuncionarioDAOMongo.getInstance().select(assist.getObjectId("id_func")).getDtNasc());
						assistente.setSexo(FuncionarioDAOMongo.getInstance().select(assist.getObjectId("id_func")).getSexo());
						assistente.setEndereco(FuncionarioDAOMongo.getInstance().select(assist.getObjectId("id_func")).getEndereco());
						
						assistente.setSalario(FuncionarioDAOMongo.getInstance().select(assist.getObjectId("id_func")).getSalario());
						assistente.setCtps(FuncionarioDAOMongo.getInstance().select(assist.getObjectId("id_func")).getCtps());
						assistente.setTurno(FuncionarioDAOMongo.getInstance().select(assist.getObjectId("id_func")).getTurno());
						
						assistente.setSupervisor(BibliotecarioDAOMongo.getInstance().select(assist.getObjectId("id_bib")));
						
						assistentes.add(assistente);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				return assistentes;
			}
			
			//select * from assistentes where id_func = ?
			public Assistente selectIdFunc(ObjectId idFunc) {
				Assistente assistente = new Assistente();
				
				try {
					Document newAssist = collection.aggregate(Arrays.asList(
							new Document("$match", new Document("id_func", idFunc))))
							.first();
					if(newAssist != null) {
						assistente.setIdAssistMongo(newAssist.getObjectId("_id"));
						assistente.setIdFuncMongo(newAssist.getObjectId("id_func"));
						
						assistente.setNome(FuncionarioDAOMongo.getInstance().select(newAssist.getObjectId("id_func")).getNome());
						assistente.setCpf(FuncionarioDAOMongo.getInstance().select(newAssist.getObjectId("id_func")).getCpf());
						assistente.setDtNasc(FuncionarioDAOMongo.getInstance().select(newAssist.getObjectId("id_func")).getDtNasc());
						assistente.setSexo(FuncionarioDAOMongo.getInstance().select(newAssist.getObjectId("id_func")).getSexo());
						assistente.setEndereco(FuncionarioDAOMongo.getInstance().select(newAssist.getObjectId("id_func")).getEndereco());
						
						assistente.setSalario(FuncionarioDAOMongo.getInstance().select(newAssist.getObjectId("id_func")).getSalario());
						assistente.setCtps(FuncionarioDAOMongo.getInstance().select(newAssist.getObjectId("id_func")).getCtps());
						assistente.setTurno(FuncionarioDAOMongo.getInstance().select(newAssist.getObjectId("id_func")).getTurno());
						
						assistente.setSupervisor(BibliotecarioDAOMongo.getInstance().select(newAssist.getObjectId("id_bib")));
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				return assistente;
			}	
			
			public ArrayList<ObjectId> funcIds() {
				ArrayList<ObjectId> ids = new ArrayList<ObjectId>();
				
				try {
					MongoIterable<Document> assist = collection.find()
							.projection(fields(include("id_func")));
					for(Document assistente : assist) {
						ids.add(assistente.getObjectId("id_func"));
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				return ids;
			}
}

