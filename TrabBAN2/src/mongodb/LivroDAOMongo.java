package mongodb;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.currentDate;
import static com.mongodb.client.model.Updates.set;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;

import dados.Editora;
import dados.Livro;

public class LivroDAOMongo {

		private static LivroDAOMongo instance = null;
		private static MongoCollection<Document> collection;
		
		public static LivroDAOMongo getInstance() {
			
			if (instance == null) 
				instance = new LivroDAOMongo();
			
			return instance;
		}
		
		private LivroDAOMongo() {
			MongoDatabase conn = ConexaoMongo.getConexao();
			try {
				collection = conn.getCollection("Livros");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		public void insert(Livro livro) {
            Stream<ObjectId> ids = livro.getEditoras().stream().map(Editora::getIdEdiMongo);
            List<ObjectId> listIds = ids.collect(Collectors.toList());
			try {				
				Document document = new Document()
			               .append("titulo", livro.getTitulo())
			               .append("autor", livro.getAutor())
			               .append("isbn", livro.getIsbn())
			               .append("editoras", listIds);
				collection.insertOne(document);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		public void update(Livro livro) {
			Stream<ObjectId> ids = livro.getEditoras().stream().map(Editora::getIdEdiMongo);
            List<ObjectId> listIds = ids.collect(Collectors.toList());
			try {
				collection.updateOne(
		                eq("_id", livro.getIdLivMongo()),
		                combine(set("titulo", livro.getTitulo()), set("autor", livro.getAutor()), set("isbn", livro.getIsbn()), set("editoras", listIds), currentDate("lastModified")));
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
		
		public Livro select(ObjectId id) {
			Livro livro = new Livro();
			
			try {
				Document newLiv = collection.aggregate(Arrays.asList(
						new Document("$match", new Document("_id", id)),
						new Document("$lookup", new Document("from", "editora")
								.append("as", "editora")
								.append("localField", "editoras")
								.append("foreignField", "_id")))).first();
				if(newLiv != null) {
					livro.setIdLivMongo(newLiv.getObjectId("_id"));
					livro.setTitulo(newLiv.getString("titulo"));
					livro.setAutor(newLiv.getString("autor"));
					livro.setIsbn(newLiv.getString("isbn"));
					List<ObjectId> editoras = (List<ObjectId>) newLiv.get("editoras");
					for(int i = 0; i < editoras.size(); i++) {
						livro.getEditoras().add(EditoraDAOMongo.getInstance().select(editoras.get(i)));
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return livro;
		}
		
		public ArrayList<Livro> all() {
			ArrayList<Livro> livros = new ArrayList<Livro>();
			
			try {
				MongoIterable<Document> newLivro = collection.find();
				for(Document liv : newLivro) {
					Livro livro = new Livro();
					
					livro.setIdLivMongo(liv.getObjectId("_id"));
					livro.setTitulo(liv.getString("titulo"));
					livro.setAutor(liv.getString("autor"));
					livro.setIsbn(liv.getString("isbn"));
					livro.setEditoras(new ArrayList<Editora>());
					List<ObjectId> editoras = (List<ObjectId>) liv.get("editoras");
					for(int i = 0; i < editoras.size(); i++) {
						livro.getEditoras().add(EditoraDAOMongo.getInstance().select(editoras.get(i)));
					}
					livros.add(livro);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return livros;
		}
		
		//select * from livros where isbn = ?
		public Livro selectIsbn(String isbn) {
			Livro livro = new Livro();
			
			try {
				Document newLiv = collection.aggregate(Arrays.asList(
						new Document("$match", new Document("isbn", isbn))))
						.first();
				if(newLiv != null) {
					livro.setIdLivMongo(newLiv.getObjectId("_id"));
					livro.setTitulo(newLiv.getString("titulo"));
					livro.setAutor(newLiv.getString("autor"));
					livro.setIsbn(newLiv.getString("isbn"));
					List<ObjectId> editoras = (List<ObjectId>) newLiv.get("editoras");
					for(int i = 0; i < editoras.size(); i++) {
						livro.getEditoras().add(EditoraDAOMongo.getInstance().select(editoras.get(i)));
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return livro;
		}
		
	}

