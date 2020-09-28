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

import dados.Colecao;

public class ColecaoDAOMongo {

		private static ColecaoDAOMongo instance = null;
		private static MongoCollection<Document> collection;
		
		public static ColecaoDAOMongo getInstance() {
			
			if (instance == null) 
				instance = new ColecaoDAOMongo();
			
			return instance;
		}
		
		private ColecaoDAOMongo() {
			MongoDatabase conn = ConexaoMongo.getConexao();
			try {
				collection = conn.getCollection("Colecoes");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		public void insert(Colecao colecao) {
			try {
				Document document = new Document()
			               .append("nome", colecao.getNome());
				collection.insertOne(document);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		public void update(Colecao colecao) {
			try {
				collection.updateOne(
		                eq("_id", colecao.getIdColMongo()),
		                combine(set("nome", colecao.getNome()), currentDate("lastModified")));
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
		
		public Colecao select(ObjectId id) {
			Colecao colecao = new Colecao();
			
			try {
				Document newCol = collection.find(eq("_id", id))
						.first();
				if(newCol != null) {
					colecao.setIdColMongo(newCol.getObjectId("_id"));
					colecao.setNome(newCol.getString("nome"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return colecao;
		}
		
		public ArrayList<Colecao> all() {
			ArrayList<Colecao> colecoes = new ArrayList<Colecao>();
			
			try {
				MongoIterable<Document> newCol = collection.find();
				for(Document col : newCol) {
					Colecao colecao = new Colecao();
					
					colecao.setIdColMongo(col.getObjectId("_id"));
					colecao.setNome(col.getString("nome"));
					
					colecoes.add(colecao);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return colecoes;
		}
		
	}

