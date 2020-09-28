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

import dados.Universidade;

public class UniversidadeDAOMongo {

	private static UniversidadeDAOMongo instance = null;
	private static MongoCollection<Document> collection;
	
	public static UniversidadeDAOMongo getInstance() {
		
		if (instance == null) 
			instance = new UniversidadeDAOMongo();
		
		return instance;
	}
	
	private UniversidadeDAOMongo() {
		MongoDatabase conn = ConexaoMongo.getConexao();
		try {
			collection = conn.getCollection("Universidade");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void insert(Universidade universidade) {
		try {
			Document document = new Document("nome", universidade.getNome())
		               .append("sigla", universidade.getSigla());
			collection.insertOne(document);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void update(Universidade universidade) {
		try {
			collection.updateOne(
	                eq("_id", universidade.getIdUniMongo()),
	                combine(set("nome", universidade.getNome()), set("sigla", universidade.getSigla()), currentDate("lastModified")));
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
	
	public Universidade select(ObjectId id) {
		Universidade universidade = new Universidade();
		
		try {
			Document newUni = collection.find(eq("_id", id))
					.first();
			if(newUni != null) {
				universidade.setIdUniMongo(newUni.getObjectId("_id"));
				universidade.setNome(newUni.getString("nome"));
				universidade.setSigla(newUni.getString("sigla"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return universidade;
	}
	
	public ArrayList<Universidade> all() {
		ArrayList<Universidade> universidades = new ArrayList<Universidade>();
		
		try {
			MongoIterable<Document> newUni = collection.find();
			for(Document uni : newUni) {
				Universidade universidade = new Universidade();
				
				universidade.setIdUniMongo(uni.getObjectId("_id"));
				universidade.setNome(uni.getString("nome"));
				universidade.setSigla(uni.getString("sigla"));
				universidades.add(universidade);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return universidades;
	}
	
}

