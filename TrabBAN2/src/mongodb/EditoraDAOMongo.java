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

import dados.Editora;

public class EditoraDAOMongo {

		private static EditoraDAOMongo instance = null;
		private static MongoCollection<Document> collection;
		
		public static EditoraDAOMongo getInstance() {
			
			if (instance == null) 
				instance = new EditoraDAOMongo();
			
			return instance;
		}
		
		private EditoraDAOMongo() {
			MongoDatabase conn = ConexaoMongo.getConexao();
			try {
				collection = conn.getCollection("Editoras");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		public void insert(Editora editora) {
			try {
				Document document = new Document()
			               .append("nome", editora.getNome())
			               .append("cnpj", editora.getCnpj());
				collection.insertOne(document);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		public void update(Editora editora) {
			try {
				collection.updateOne(
		                eq("_id", editora.getIdEdiMongo()),
		                combine(set("nome", editora.getNome()), set("cnpj", editora.getCnpj()), currentDate("lastModified")));
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
		
		public Editora select(ObjectId id) {
			Editora editora = new Editora();
			
			try {
				Document newEdit = collection.find(eq("_id", id))
						.first();
				if(newEdit != null) {
					editora.setIdEdiMongo(newEdit.getObjectId("_id"));
					editora.setNome(newEdit.getString("nome"));
					editora.setCnpj(newEdit.getString("cnpj"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return editora;
		}
		
		public ArrayList<Editora> all() {
			ArrayList<Editora> editoras = new ArrayList<Editora>();
			
			try {
				MongoIterable<Document> newEdit = collection.find();
				for(Document edi : newEdit) {
					Editora editora = new Editora();
					
					editora.setIdEdiMongo(edi.getObjectId("_id"));
					editora.setNome(edi.getString("nome"));
					editora.setCnpj(edi.getString("cnpj"));
					
					editoras.add(editora);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return editoras;
		}
		
	}

