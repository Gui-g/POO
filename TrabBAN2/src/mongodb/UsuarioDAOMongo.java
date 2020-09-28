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

import dados.Usuario;

public class UsuarioDAOMongo {

	private static UsuarioDAOMongo instance = null;
	private static MongoCollection<Document> collection;
	
	public static UsuarioDAOMongo getInstance() {
		
		if (instance == null) 
			instance = new UsuarioDAOMongo();
		
		return instance;
	}
	
	private UsuarioDAOMongo() {
		MongoDatabase conn = ConexaoMongo.getConexao();
		try {
			collection = conn.getCollection("Usuarios");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void insert(Usuario usuario) {
		try {
			Document document = new Document("id_pes", usuario.getIdPesMongo())
		               .append("id_uni", usuario.getUni().getIdUniMongo())
		               .append("id_cat", usuario.getCat().getIdCatMongo());
			collection.insertOne(document);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void update(Usuario usuario) {
		try {
			collection.updateOne(
	                eq("_id", usuario.getIdUserMongo()),
	                combine(set("id_uni", usuario.getUni().getIdUniMongo()), set("id_cat", usuario.getCat().getIdCatMongo()), currentDate("lastModified")));
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
	
	public Usuario select(ObjectId id) {
		Usuario usuario = new Usuario();
		
		try {
			Document newUser = collection.find(eq("_id", id))
					.first();
			if(newUser != null) {
				usuario.setIdUserMongo(newUser.getObjectId("_id"));
				
				usuario.setUni(UniversidadeDAOMongo.getInstance().select(newUser.getObjectId("id_uni")));
				usuario.setCat(CategoriaDAOMongo.getInstance().select(newUser.getObjectId("id_cat")));
				usuario.setNome(PessoaDAOMongo.getInstance().select(newUser.getObjectId("id_pes")).getNome());
				usuario.setCpf(PessoaDAOMongo.getInstance().select(newUser.getObjectId("id_pes")).getCpf());
				usuario.setDtNasc(PessoaDAOMongo.getInstance().select(newUser.getObjectId("id_pes")).getDtNasc());
				usuario.setSexo(PessoaDAOMongo.getInstance().select(newUser.getObjectId("id_pes")).getSexo());
				usuario.setEndereco(PessoaDAOMongo.getInstance().select(newUser.getObjectId("id_pes")).getEndereco());
				usuario.setIdPesMongo(newUser.getObjectId("id_pes"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return usuario;
	}
	
	public ArrayList<Usuario> all() {
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
		
		try {
			MongoIterable<Document> newUser = collection.find();
			for(Document user : newUser) {
				Usuario usuario = new Usuario();
				
				usuario.setUni(UniversidadeDAOMongo.getInstance().select(user.getObjectId("id_uni")));
				usuario.setCat(CategoriaDAOMongo.getInstance().select(user.getObjectId("id_cat")));
				usuario.setNome(PessoaDAOMongo.getInstance().select(user.getObjectId("id_pes")).getNome());
				usuario.setCpf(PessoaDAOMongo.getInstance().select(user.getObjectId("id_pes")).getCpf());
				usuario.setDtNasc(PessoaDAOMongo.getInstance().select(user.getObjectId("id_pes")).getDtNasc());
				usuario.setSexo(PessoaDAOMongo.getInstance().select(user.getObjectId("id_pes")).getSexo());
				usuario.setEndereco(PessoaDAOMongo.getInstance().select(user.getObjectId("id_pes")).getEndereco());
				usuario.setIdPesMongo(user.getObjectId("id_pes"));
				usuario.setIdUserMongo(user.getObjectId("_id"));
				
				usuarios.add(usuario);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return usuarios;
	}
	
	//("select * from usuarios where id_pes = ?");
	public Usuario selectIdPes(ObjectId idPes) {
		Usuario usuario = new Usuario();
		
		try {
			Document newUser = collection.aggregate(Arrays.asList(
					new Document("$match", new Document("id_pes", idPes))))
					.first();
			if(newUser != null) {
				usuario.setUni(UniversidadeDAOMongo.getInstance().select(newUser.getObjectId("id_uni")));
				usuario.setCat(CategoriaDAOMongo.getInstance().select(newUser.getObjectId("id_cat")));
				usuario.setNome(PessoaDAOMongo.getInstance().select(newUser.getObjectId("id_pes")).getNome());
				usuario.setCpf(PessoaDAOMongo.getInstance().select(newUser.getObjectId("id_pes")).getCpf());
				usuario.setDtNasc(PessoaDAOMongo.getInstance().select(newUser.getObjectId("id_pes")).getDtNasc());
				usuario.setSexo(PessoaDAOMongo.getInstance().select(newUser.getObjectId("id_pes")).getSexo());
				usuario.setEndereco(PessoaDAOMongo.getInstance().select(newUser.getObjectId("id_pes")).getEndereco());
				usuario.setIdPesMongo(newUser.getObjectId("id_pes"));
				usuario.setIdUserMongo(newUser.getObjectId("_id"));
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return usuario;
	}
	
	//("select id_user from usuarios where id_cat = ?");
	public ArrayList<Usuario> selectIdCat(ObjectId idCat) {
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
		
		try {
			MongoIterable<Document> newUser = collection.find(eq("id_cat", idCat));
			for(Document user : newUser) {
				Usuario usuario = new Usuario();
				
				usuario.setUni(UniversidadeDAOMongo.getInstance().select(user.getObjectId("id_uni")));
				usuario.setCat(CategoriaDAOMongo.getInstance().select(user.getObjectId("id_cat")));
				usuario.setNome(PessoaDAOMongo.getInstance().select(user.getObjectId("id_pes")).getNome());
				usuario.setCpf(PessoaDAOMongo.getInstance().select(user.getObjectId("id_pes")).getCpf());
				usuario.setDtNasc(PessoaDAOMongo.getInstance().select(user.getObjectId("id_pes")).getDtNasc());
				usuario.setSexo(PessoaDAOMongo.getInstance().select(user.getObjectId("id_pes")).getSexo());
				usuario.setEndereco(PessoaDAOMongo.getInstance().select(user.getObjectId("id_pes")).getEndereco());
				usuario.setIdPesMongo(user.getObjectId("id_pes"));
				usuario.setIdUserMongo(user.getObjectId("_id"));
				
				usuarios.add(usuario);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return usuarios;
	}
	
	public ArrayList<ObjectId> pesIds() {
		ArrayList<ObjectId> ids = new ArrayList<ObjectId>();
		
		try {
			MongoIterable<Document> pessoas = collection.find()
					.projection(fields(include("id_pes")));
			for(Document pes : pessoas) {
				ids.add(pes.getObjectId("id_pes"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ids;
	}
}