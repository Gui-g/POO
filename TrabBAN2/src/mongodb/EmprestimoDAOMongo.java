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

import dados.Emprestimo;

public class EmprestimoDAOMongo {

		private static EmprestimoDAOMongo instance = null;
		private static MongoCollection<Document> collection;
		
		public static EmprestimoDAOMongo getInstance() {
			
			if (instance == null) 
				instance = new EmprestimoDAOMongo();
			
			return instance;
		}
		
		private EmprestimoDAOMongo() {
			MongoDatabase conn = ConexaoMongo.getConexao();
			try {
				collection = conn.getCollection("Emprestimos");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		public void insert(Emprestimo emprestimo) {
			try {
				Document document = new Document("id_ex", emprestimo.getEx().getIdExMongo())
			               .append("id_liv", emprestimo.getEx().getIdLivMongo())
			               .append("id_user", emprestimo.getUser().getIdUserMongo())
			               .append("id_bib", emprestimo.getBib().getIdBibMongo())
			               .append("data_emp", emprestimo.getDiaEmprestimo())
			               .append("data_ent", emprestimo.getDiaEntrega())
			               .append("renov", emprestimo.getRenov());
				collection.insertOne(document);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		public void update(Emprestimo emprestimo) {
			try {
				collection.updateOne(
		                eq("_id", emprestimo.getIdEmpMongo()),
		                combine(set("data_ent", emprestimo.getDiaEntrega()), set("renov", emprestimo.getRenov()), currentDate("lastModified")));
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
		
		public Emprestimo select(ObjectId id) {
			Emprestimo emprestimo = new Emprestimo();
			
			try {			
				Document newEmprestimo = collection.find(eq("_id", id))
									.first();
				if(newEmprestimo != null) {
					emprestimo.setIdEmpMongo(newEmprestimo.getObjectId("_id"));
					emprestimo.setDiaEmprestimo(newEmprestimo.getDate("data_emp"));
					emprestimo.setBib(BibliotecarioDAOMongo.getInstance().select(newEmprestimo.getObjectId("id_bib")));
					emprestimo.setEx(ExemplarDAOMongo.getInstance().select(newEmprestimo.getObjectId("id_ex")));
					emprestimo.setUser(UsuarioDAOMongo.getInstance().select(newEmprestimo.getObjectId("id_user")));
					emprestimo.setRenov(newEmprestimo.getInteger("renov"));
					emprestimo.setDiaEntrega(newEmprestimo.getDate("data_ent"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return emprestimo;
		}
		
		//("select id_emp from emprestimos where id_user = ? and data_ent is null");
		public ArrayList<Emprestimo> selectIdUser(ObjectId idUser) {
			ArrayList<Emprestimo> emprestimos = new ArrayList<Emprestimo>();
			
			try {
				MongoIterable<Document> newEmprestimo = collection.aggregate(Arrays.asList(
						new Document("$match", new Document("id_user", idUser)),
						new Document("$match", new Document("data_ent", null))));
				for(Document emp : newEmprestimo) {
					Emprestimo emprestimo = new Emprestimo();
					
					emprestimo.setIdEmpMongo(emp.getObjectId("_id"));
					emprestimo.setDiaEmprestimo(emp.getDate("data_emp"));
					emprestimo.setBib(BibliotecarioDAOMongo.getInstance().select(emp.getObjectId("id_bib")));
					emprestimo.setEx(ExemplarDAOMongo.getInstance().select(emp.getObjectId("id_ex")));
					emprestimo.setUser(UsuarioDAOMongo.getInstance().select(emp.getObjectId("id_user")));
					emprestimo.setRenov(emp.getInteger("renov"));
					emprestimo.setDiaEntrega(emp.getDate("data_ent"));
					
					emprestimos.add(emprestimo);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return emprestimos;
		}
		
		//("select id_emp from emprestimos where id_liv = ? and data_ent is null");
		public ArrayList<Emprestimo> selectIdLiv(ObjectId idLiv) {
			ArrayList<Emprestimo> emprestimos = new ArrayList<Emprestimo>();
			
			try {
				MongoIterable<Document> newEmprestimo = collection.aggregate(Arrays.asList(
						new Document("$match", new Document("id_liv", idLiv)),
						new Document("$match", new Document("data_ent", null))));
				for(Document emp : newEmprestimo) {
					Emprestimo emprestimo = new Emprestimo();
					
					emprestimo.setIdEmpMongo(emp.getObjectId("_id"));
					emprestimo.setDiaEmprestimo(emp.getDate("data_emp"));
					emprestimo.setBib(BibliotecarioDAOMongo.getInstance().select(emp.getObjectId("id_bib")));
					emprestimo.setEx(ExemplarDAOMongo.getInstance().select(emp.getObjectId("id_ex")));
					emprestimo.setUser(UsuarioDAOMongo.getInstance().select(emp.getObjectId("id_user")));
					emprestimo.setRenov(emp.getInteger("renov"));
					emprestimo.setDiaEntrega(emp.getDate("data_ent"));
					
					emprestimos.add(emprestimo);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return emprestimos;
		}
		
		public ArrayList<Emprestimo> all() {
			ArrayList<Emprestimo> emprestimos = new ArrayList<Emprestimo>();
			
			try {			
				MongoIterable<Document> newEmp = collection.find();
				for(Document emp : newEmp) {
					Emprestimo emprestimo = new Emprestimo();
					
					emprestimo.setIdEmpMongo(emp.getObjectId("_id"));
					emprestimo.setDiaEmprestimo(emp.getDate("data_emp"));
					emprestimo.setBib(BibliotecarioDAOMongo.getInstance().select(emp.getObjectId("id_bib")));
					emprestimo.setEx(ExemplarDAOMongo.getInstance().select(emp.getObjectId("id_ex")));
					emprestimo.setUser(UsuarioDAOMongo.getInstance().select(emp.getObjectId("id_user")));
					emprestimo.setRenov(emp.getInteger("renov"));
					emprestimo.setDiaEntrega(emp.getDate("data_ent"));
					
					emprestimos.add(emprestimo);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return emprestimos;
		}
		
		//("select id_emp from emprestimos where data_ent is null");
		public ArrayList<Emprestimo> allNotEnt() {
			ArrayList<Emprestimo> emprestimos = new ArrayList<Emprestimo>();
			
			try {
				MongoIterable<Document> newEmprestimo = collection.aggregate(Arrays.asList(
						new Document("$match", new Document("data_ent", null))));
				for(Document emp : newEmprestimo) {
					Emprestimo emprestimo = new Emprestimo();
					
					emprestimo.setIdEmpMongo(emp.getObjectId("_id"));
					emprestimo.setDiaEmprestimo(emp.getDate("data_emp"));
					emprestimo.setBib(BibliotecarioDAOMongo.getInstance().select(emp.getObjectId("id_bib")));
					emprestimo.setEx(ExemplarDAOMongo.getInstance().select(emp.getObjectId("id_ex")));
					emprestimo.setUser(UsuarioDAOMongo.getInstance().select(emp.getObjectId("id_user")));
					emprestimo.setRenov(emp.getInteger("renov"));
					emprestimo.setDiaEntrega(emp.getDate("data_ent"));
					
					emprestimos.add(emprestimo);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return emprestimos;
		}
		

		//("select distinct id_user from emprestimos where data_ent is null");
		public ArrayList<ObjectId> selectIdUser() {
			ArrayList<ObjectId> emprestimos = new ArrayList<ObjectId>();
			
			try {
				MongoIterable<ObjectId> emprestimo = collection.distinct("id_user", eq("data_ent", null), ObjectId.class);
				for(ObjectId emp : emprestimo) {
					emprestimos.add(emp);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return emprestimos;
		}
		
		//("select id_emp from emprestimos where id_user = ?");
		public ArrayList<Emprestimo> allEmpUser(ObjectId idUser) {
			ArrayList<Emprestimo> emprestimos = new ArrayList<Emprestimo>();
			
			try {
				MongoIterable<Document> newEmprestimo = collection.aggregate(Arrays.asList(
						new Document("$match", new Document("id_user", idUser))));
				for(Document emp : newEmprestimo) {
					Emprestimo emprestimo = new Emprestimo();
					
					emprestimo.setIdEmpMongo(emp.getObjectId("_id"));
					emprestimo.setDiaEmprestimo(emp.getDate("data_emp"));
					emprestimo.setBib(BibliotecarioDAOMongo.getInstance().select(emp.getObjectId("id_bib")));
					emprestimo.setEx(ExemplarDAOMongo.getInstance().select(emp.getObjectId("id_ex")));
					emprestimo.setUser(UsuarioDAOMongo.getInstance().select(emp.getObjectId("id_user")));
					emprestimo.setRenov(emp.getInteger("renov"));
					emprestimo.setDiaEntrega(emp.getDate("data_ent"));
					
					emprestimos.add(emprestimo);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return emprestimos;
		}
		
		//("select id_emp from emprestimos where id_liv = ?");
		public ArrayList<Emprestimo> allEmpLiv(ObjectId idLiv) {
			ArrayList<Emprestimo> emprestimos = new ArrayList<Emprestimo>();
			
			try {
				MongoIterable<Document> newEmprestimo = collection.aggregate(Arrays.asList(
						new Document("$match", new Document("id_liv", idLiv))));
				for(Document emp : newEmprestimo) {
					Emprestimo emprestimo = new Emprestimo();
					
					emprestimo.setIdEmpMongo(emp.getObjectId("_id"));
					emprestimo.setDiaEmprestimo(emp.getDate("data_emp"));
					emprestimo.setBib(BibliotecarioDAOMongo.getInstance().select(emp.getObjectId("id_bib")));
					emprestimo.setEx(ExemplarDAOMongo.getInstance().select(emp.getObjectId("id_ex")));
					emprestimo.setUser(UsuarioDAOMongo.getInstance().select(emp.getObjectId("id_user")));
					emprestimo.setRenov(emp.getInteger("renov"));
					emprestimo.setDiaEntrega(emp.getDate("data_ent"));
					
					emprestimos.add(emprestimo);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return emprestimos;
		}
		
		//("select distinct id_liv from emprestimos where data_ent is null");
		public ArrayList<ObjectId> selectidLiv() {
			ArrayList<ObjectId> emprestimos = new ArrayList<ObjectId>();
			
			try {
				MongoIterable<ObjectId> emprestimo = collection.distinct("id_liv", eq("data_ent", null), ObjectId.class);
				for(ObjectId emp : emprestimo) {
					emprestimos.add(emp);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return emprestimos;
		}
		
	}
