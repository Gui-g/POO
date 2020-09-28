package mongodb;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.currentDate;
import static com.mongodb.client.model.Updates.set;

import java.util.ArrayList;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.postgresql.util.PGInterval;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;

import dados.Categoria;

public class CategoriaDAOMongo {

		private static CategoriaDAOMongo instance = null;
		private static MongoCollection<Document> collection;
		
		public static CategoriaDAOMongo getInstance() {
			
			if (instance == null) 
				instance = new CategoriaDAOMongo();
			
			return instance;
		}
		
		private CategoriaDAOMongo() {
			MongoDatabase conn = ConexaoMongo.getConexao();
			try {
				collection = conn.getCollection("Categoria");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		public void insert(Categoria categoria) {
			
			try {
				Document document = new Document()
			               .append("nome", categoria.getNome())
			               .append("tempo", new Document("ano", categoria.getTempo().getYears())
			            		   .append("mes", categoria.getTempo().getMonths())
			            		   .append("dia", categoria.getTempo().getDays())
			            		   .append("hora", categoria.getTempo().getHours())
			            		   .append("minuto", categoria.getTempo().getMinutes())
			            		   .append("segundo", categoria.getTempo().getSeconds()));
				collection.insertOne(document);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		public void update(Categoria categoria) {
			try {
				collection.updateOne(
		                eq("_id", categoria.getIdCatMongo()),
		                combine(set("nome", categoria.getNome()), set("tempo", new Document("ano", categoria.getTempo().getYears())
			            		   .append("mes", categoria.getTempo().getMonths())
			            		   .append("dia", categoria.getTempo().getDays())
			            		   .append("hora", categoria.getTempo().getHours())
			            		   .append("minuto", categoria.getTempo().getMinutes())
			            		   .append("segundo", categoria.getTempo().getSeconds())),currentDate("lastModified")));
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
		
		public Categoria select(ObjectId id) {
			Categoria categoria = new Categoria();
			
			try {
				Document newCat = collection.find(eq("_id", id))
						.first();
				if(newCat != null) {
					categoria.setIdCatMongo(newCat.getObjectId("_id"));
					categoria.setNome(newCat.getString("nome"));
					Document tempo = newCat.get("tempo", Document.class);
					categoria.setTempo(new PGInterval(tempo.getInteger("ano"), tempo.getInteger("mes"),
							tempo.getInteger("dia"), tempo.getInteger("hora"), tempo.getInteger("minuto"), tempo.getDouble("segundo")));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return categoria;
		}
		
		public ArrayList<Categoria> all() {
			ArrayList<Categoria> categorias = new ArrayList<Categoria>();
			
			try {
				MongoIterable<Document> newCat = collection.find();
				for(Document cat : newCat) {
					Categoria categoria = new Categoria();
					
					categoria.setIdCatMongo(cat.getObjectId("_id"));
					categoria.setNome(cat.getString("nome"));
					Document tempo = cat.get("tempo", Document.class);
					categoria.setTempo(new PGInterval(tempo.getInteger("ano"), tempo.getInteger("mes"),
							tempo.getInteger("dia"), tempo.getInteger("hora"), tempo.getInteger("minuto"), (int) Math.round(tempo.getDouble("segundo"))));
				
					categorias.add(categoria);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return categorias;
		}
		
	}

