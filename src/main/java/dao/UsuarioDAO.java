package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import connection.JPAUtil;

public class UsuarioDAO<E> {

	@SuppressWarnings({ "unused" })
	private static EntityManager entityManager = JPAUtil.getEntityManager();

	public void Salvar(E usuario) {

		EntityManager entityManager = JPAUtil.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		entityTransaction.commit();
		entityManager.close();
	}

	public E atualizar(E usuario) {

		EntityManager entityManager = JPAUtil.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		E retorno = entityManager.merge(usuario);
		entityTransaction.commit();

		return retorno;
	}

	public void deletarPorId(E usuario) {

		EntityManager entityManager = JPAUtil.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();

		Object id = JPAUtil.getPrimaryKey(usuario);
		entityManager.createQuery("delete from " + usuario.getClass().getCanonicalName() + " where id = " + id)
				.executeUpdate();

		entityTransaction.commit();
		entityManager.close();
	}

	public List<E> listarUsuario(Class<E> usuario) {

		EntityManager entityManager = JPAUtil.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		@SuppressWarnings("unchecked")
		List<E> retorno = entityManager.createQuery("from " + usuario.getName()).getResultList();

		entityTransaction.commit();
		entityManager.close();

		return retorno;

	}
}
