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

import dados.Status;

public class StatusDAOMongo {

		private static StatusDAOMongo instance = null;
		private static MongoCollection<Document> collection;
		
		public static StatusDAOMongo getInstance() {
			
			if (instance == null) 
				instance = new StatusDAOMongo();
			
			return instance;
		}
		
		private StatusDAOMongo() {
			MongoDatabase conn = ConexaoMongo.getConexao();
			try {
				collection = conn.getCollection("Status");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		public void insert(Status status) {
			try {
				Document document = new Document()
			               .append("nome", status.getNome());
				collection.insertOne(document);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		public void update(Status status) {
			try {
				collection.updateOne(
		                eq("_id", status.getIdStMongo()),
		                combine(set("nome", status.getNome()), currentDate("lastModified")));
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
		
		public Status select(ObjectId id) {
			Status status = new Status();
			
			try {
				Document newStatus = collection.find(eq("_id", id))
						.first();
				if(newStatus != null) {
					status.setIdStMongo(newStatus.getObjectId("_id"));
					status.setNome(newStatus.getString("nome"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return status;
		}
		
		public ArrayList<Status> all() {
			ArrayList<Status> statusList = new ArrayList<Status>();
			
			try {			
				MongoIterable<Document> newSt = collection.find();
				for(Document st : newSt) {
					Status status = new Status();

					status.setIdStMongo(st.getObjectId("_id"));
					status.setNome(st.getString("nome"));
					
					statusList.add(status);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return statusList;
		}
		
}

