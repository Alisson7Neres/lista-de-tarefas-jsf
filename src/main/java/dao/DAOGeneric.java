package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import connection.JPAUtil;

public class DAOGeneric<E> {

	@SuppressWarnings({ "unused" })
	private static EntityManager entityManager = JPAUtil.getEntityManager();

	public void salvar(E usuario) {

		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		entityManager.persist(usuario);
		entityTransaction.commit();
	}

	public E atualizar(E usuario) {

		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		E retorno = entityManager.merge(usuario);
		entityTransaction.commit();

		return retorno;
	}

	public void deletarPorId(E usuario) {

		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();

		Object id = JPAUtil.getPrimaryKey(usuario);
		entityManager.createQuery("delete from " + usuario.getClass().getCanonicalName() + " where id = " + id)
				.executeUpdate();

		entityTransaction.commit();
		entityManager.close();
	}

	public List<E> listarUsuario(Class<E> entity) {
		
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		
		@SuppressWarnings("unchecked")
		List<E> retorno = entityManager.createQuery("from " + entity.getName()).getResultList();

		entityTransaction.commit();
		return retorno;
	}
	
public List<E> listarUsuarioTelefone(Class<E> entity) {
		
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		
		@SuppressWarnings("unchecked")
		List<E> retorno = entityManager.createQuery("from " + entity.getName()).getResultList();

		entityTransaction.commit();
		return retorno;
	}

public E pesquisar(int id, Class<E> entidade) {
	entityManager.clear();
	E e = (E) entityManager.createQuery("from " + entidade.getSimpleName() + " where id = " + id).getSingleResult();
	
	return e;
}
	
	public E findById(int id, Class<E> entidade) {
		entityManager.clear();
		E e = (E) entityManager.createQuery("from " + entidade.getSimpleName() + " where id = " + id).getSingleResult();
		return e;
	}
	
	
	public static EntityManager getEntityManager() {
		return entityManager;
	}
}
