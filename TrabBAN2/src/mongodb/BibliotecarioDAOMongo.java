package mongodb;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Projections.fields;
import static com.mongodb.client.model.Projections.include;

import java.util.ArrayList;
import java.util.Arrays;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;

import dados.Bibliotecario;

public class BibliotecarioDAOMongo {

			private static BibliotecarioDAOMongo instance = null;
			private static MongoCollection<Document> collection;
			
			public static BibliotecarioDAOMongo getInstance() {
				
				if (instance == null) 
					instance = new BibliotecarioDAOMongo();
				
				return instance;
			}
			
			private BibliotecarioDAOMongo() {
				MongoDatabase conn = ConexaoMongo.getConexao();
				try {
					collection = conn.getCollection("Bibliotecarios");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			public void insert(Bibliotecario bibliotecario) {
				try {
					Document document = new Document("id_func", bibliotecario.getIdFuncMongo());
					collection.insertOne(document);
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
			
			public Bibliotecario select(ObjectId id) {
				Bibliotecario bibliotecario = new Bibliotecario();
				
				try {
					Document newBib = collection.find(eq("_id", id))
							.first();
					if(newBib != null) {
						bibliotecario.setIdBibMongo(newBib.getObjectId("_id"));
						bibliotecario.setIdFuncMongo(newBib.getObjectId("id_func"));
						
						bibliotecario.setNome(FuncionarioDAOMongo.getInstance().select(newBib.getObjectId("id_func")).getNome());
						bibliotecario.setCpf(FuncionarioDAOMongo.getInstance().select(newBib.getObjectId("id_func")).getCpf());
						bibliotecario.setDtNasc(FuncionarioDAOMongo.getInstance().select(newBib.getObjectId("id_func")).getDtNasc());
						bibliotecario.setSexo(FuncionarioDAOMongo.getInstance().select(newBib.getObjectId("id_func")).getSexo());
						bibliotecario.setEndereco(FuncionarioDAOMongo.getInstance().select(newBib.getObjectId("id_func")).getEndereco());
						
						bibliotecario.setSalario(FuncionarioDAOMongo.getInstance().select(newBib.getObjectId("id_func")).getSalario());
						bibliotecario.setCtps(FuncionarioDAOMongo.getInstance().select(newBib.getObjectId("id_func")).getCtps());
						bibliotecario.setTurno(FuncionarioDAOMongo.getInstance().select(newBib.getObjectId("id_func")).getTurno());
						
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				return bibliotecario;
			}			
			
			public ArrayList<Bibliotecario> all() {
				ArrayList<Bibliotecario> bibliotecarios = new ArrayList<Bibliotecario>();
				
				try {
					MongoIterable<Document> newBib = collection.find();
					for(Document bibs : newBib) {
						Bibliotecario bibliotecario = new Bibliotecario();
						
						bibliotecario.setIdBibMongo(bibs.getObjectId("_id"));
						bibliotecario.setIdFuncMongo(bibs.getObjectId("id_func"));
						
						bibliotecario.setNome(FuncionarioDAOMongo.getInstance().select(bibs.getObjectId("id_func")).getNome());
						bibliotecario.setCpf(FuncionarioDAOMongo.getInstance().select(bibs.getObjectId("id_func")).getCpf());
						bibliotecario.setDtNasc(FuncionarioDAOMongo.getInstance().select(bibs.getObjectId("id_func")).getDtNasc());
						bibliotecario.setSexo(FuncionarioDAOMongo.getInstance().select(bibs.getObjectId("id_func")).getSexo());
						bibliotecario.setEndereco(FuncionarioDAOMongo.getInstance().select(bibs.getObjectId("id_func")).getEndereco());
						
						bibliotecario.setSalario(FuncionarioDAOMongo.getInstance().select(bibs.getObjectId("id_func")).getSalario());
						bibliotecario.setCtps(FuncionarioDAOMongo.getInstance().select(bibs.getObjectId("id_func")).getCtps());
						bibliotecario.setTurno(FuncionarioDAOMongo.getInstance().select(bibs.getObjectId("id_func")).getTurno());
						
						bibliotecarios.add(bibliotecario);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				return bibliotecarios;
			}
			
			//select * from bibliotecarios where id_func = ?
			public Bibliotecario selectIdFunc(ObjectId idFunc) {
				Bibliotecario bibliotecario = new Bibliotecario();
				
				try {
					Document newBib = collection.aggregate(Arrays.asList(
							new Document("$match", new Document("id_func", idFunc))))
							.first();
					if(newBib != null) {
						bibliotecario.setIdBibMongo(newBib.getObjectId("_id"));
						bibliotecario.setIdFuncMongo(newBib.getObjectId("id_func"));
						
						bibliotecario.setNome(FuncionarioDAOMongo.getInstance().select(newBib.getObjectId("id_func")).getNome());
						bibliotecario.setCpf(FuncionarioDAOMongo.getInstance().select(newBib.getObjectId("id_func")).getCpf());
						bibliotecario.setDtNasc(FuncionarioDAOMongo.getInstance().select(newBib.getObjectId("id_func")).getDtNasc());
						bibliotecario.setSexo(FuncionarioDAOMongo.getInstance().select(newBib.getObjectId("id_func")).getSexo());
						bibliotecario.setEndereco(FuncionarioDAOMongo.getInstance().select(newBib.getObjectId("id_func")).getEndereco());
						
						bibliotecario.setSalario(FuncionarioDAOMongo.getInstance().select(newBib.getObjectId("id_func")).getSalario());
						bibliotecario.setCtps(FuncionarioDAOMongo.getInstance().select(newBib.getObjectId("id_func")).getCtps());
						bibliotecario.setTurno(FuncionarioDAOMongo.getInstance().select(newBib.getObjectId("id_func")).getTurno());
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				return bibliotecario;
			}
			
			public ArrayList<ObjectId> funcIds() {
				ArrayList<ObjectId> ids = new ArrayList<ObjectId>();
				
				try {
					MongoIterable<Document> bibs = collection.find()
							.projection(fields(include("id_func")));
					for(Document bib : bibs) {
						ids.add(bib.getObjectId("id_func"));
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				return ids;
			}
		}

