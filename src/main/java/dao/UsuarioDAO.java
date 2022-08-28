package dao;

import java.util.List;

import javax.persistence.Query;

import model.Usuario;

public class UsuarioDAO extends DAOGeneric<Usuario>{

	public List<Usuario> findById(int id) {
		Query query = super.getEntityManager().createQuery
				("from Usuario where id =  " + id);
		System.out.println(id);
		return query.getResultList();
	}
	
}
