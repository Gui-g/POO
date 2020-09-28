package mongodb;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.currentDate;
import static com.mongodb.client.model.Updates.set;

import java.util.ArrayList;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;

import dados.Exemplar;

public class ExemplarDAOMongo {

		private static ExemplarDAOMongo instance = null;
		private static MongoCollection<Document> collection;
		
		public static ExemplarDAOMongo getInstance() {
			
			if (instance == null) 
				instance = new ExemplarDAOMongo();
			
			return instance;
		}
		
		private ExemplarDAOMongo() {
			MongoDatabase conn = ConexaoMongo.getConexao();
			try {
				collection = conn.getCollection("Exemplares");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		public void insert(Exemplar exemplar) {
			try {
				Document document = new Document("id_status", exemplar.getStat().getIdStMongo())
			               .append("id_col", exemplar.getCol().getIdColMongo())
			               .append("id_liv", exemplar.getIdLivMongo());
				collection.insertOne(document);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		public void update(Exemplar exemplar) {
			try {
				collection.updateOne(
		                eq("_id", exemplar.getIdExMongo()),
		                combine(set("id_status", exemplar.getStat().getIdStMongo()), set("id_col", exemplar.getCol().getIdColMongo()), currentDate("lastModified")));
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
		
		public Exemplar select(ObjectId id_ex) {
			Exemplar exemplar = new Exemplar();
			
			try {			
				Document newExemplar = collection.find(eq("_id", id_ex))
									.first();
				if(newExemplar != null) {
					exemplar.setIdExMongo(newExemplar.getObjectId("_id"));
					exemplar.setStat(StatusDAOMongo.getInstance().select(newExemplar.getObjectId("id_status")));
					exemplar.setCol(ColecaoDAOMongo.getInstance().select(newExemplar.getObjectId("id_col")));
					
					exemplar.setIdLivMongo(newExemplar.getObjectId("id_liv"));
					exemplar.setTitulo(LivroDAOMongo.getInstance().select(newExemplar.getObjectId("id_liv")).getTitulo());
					exemplar.setAutor(LivroDAOMongo.getInstance().select(newExemplar.getObjectId("id_liv")).getAutor());
					exemplar.setIsbn(LivroDAOMongo.getInstance().select(newExemplar.getObjectId("id_liv")).getIsbn());
					exemplar.setEditoras(LivroDAOMongo.getInstance().select(newExemplar.getObjectId("id_liv")).getEditoras());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return exemplar;
		}
		
		public ArrayList<Exemplar> all() {
			ArrayList<Exemplar> exemplares = new ArrayList<Exemplar>();
			
			try {			
				MongoIterable<Document> newEx = collection.find();
				for(Document ex : newEx) {
					Exemplar exemplar = new Exemplar();
					
					exemplar.setIdExMongo(ex.getObjectId("_id"));
					exemplar.setStat(StatusDAOMongo.getInstance().select(ex.getObjectId("id_status")));
					exemplar.setCol(ColecaoDAOMongo.getInstance().select(ex.getObjectId("id_col")));
					
					exemplar.setIdLivMongo(ex.getObjectId("id_liv"));
					exemplar.setTitulo(LivroDAOMongo.getInstance().select(ex.getObjectId("id_liv")).getTitulo());
					exemplar.setAutor(LivroDAOMongo.getInstance().select(ex.getObjectId("id_liv")).getAutor());
					exemplar.setIsbn(LivroDAOMongo.getInstance().select(ex.getObjectId("id_liv")).getIsbn());
					exemplar.setEditoras(LivroDAOMongo.getInstance().select(ex.getObjectId("id_liv")).getEditoras());
					
					exemplares.add(exemplar);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return exemplares;
		}
		
	}
