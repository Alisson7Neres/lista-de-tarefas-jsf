package dao;

import java.io.IOException;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.servlet.http.HttpSession;

import connection.JPAUtil;
import model.Usuario;

public class DAOGeneric<E> {

	protected static EntityManager entityManager = JPAUtil.getEntityManager();

	public void salvar(E usuario) throws IOException {
		
		try {
			

		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		entityManager.persist(usuario);
		entityTransaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", e.getCause().getMessage()));
			FacesContext.getCurrentInstance().getExternalContext().redirect("error.jsf");
		}
	}

	public E atualizar(E usuario) {

		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		E retorno = entityManager.merge(usuario);
		entityTransaction.commit();
		
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		session.removeAttribute("usuario");
		session.invalidate();

		return retorno;
	}

	public void deletarPorId(E usuario) {

		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();

		Object id = JPAUtil.getPrimaryKey(usuario);
		entityManager.createQuery("delete from " + usuario.getClass().getCanonicalName() + " where id = " + id)
				.executeUpdate();

		entityTransaction.commit();
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
		@SuppressWarnings("unchecked")
		E e = (E) entityManager.createQuery("from " + entidade.getSimpleName() + " where id = " + id).getSingleResult();

		return e;
	}

	public static EntityManager getEntityManager() {
		return entityManager;
	}

	public E pesquisarUser(int id, Class<E> entidade) {
		E e = (E) entityManager.find(entidade, id);

		return e;
	}

	public int logar(String email, String senha) throws IOException {
		try {

			// Query que irá verificar o login
			Usuario usuario = (Usuario) entityManager
					.createQuery("select u from Usuario u where u.email =:email and u.senha = :senha ")
					.setParameter("email", email).setParameter("senha", senha).getSingleResult();

			// Iniciando a sessão
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);

			session.setAttribute("usuario", usuario);

			// Se não tiver nenhum usuário na sessão, irá retornar a tela inicial
			if (usuario == null) {
				FacesContext.getCurrentInstance().getExternalContext().redirect("index.jsf");
			}
			FacesContext.getCurrentInstance().getExternalContext().redirect("bem-vindo.jsf");

			return usuario.getId_usuario();
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().getExternalContext().redirect("index.jsf");
		}
		return 0;
	}

	public void logout(Usuario usuario) {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		session.removeAttribute("usuario");
		session.invalidate();
	}

}
