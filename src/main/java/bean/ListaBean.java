package bean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import dao.DAOGeneric;
import dao.UsuarioDAO;
import model.Lista;
import model.Usuario;

@ManagedBean(name = "listaBean")
@ViewScoped
public class ListaBean {
	
	private Lista lista = new Lista();
	private DAOGeneric<Lista> dao = new DAOGeneric<Lista>();
	private Usuario usuario = new Usuario();
	private UsuarioDAO<Usuario> usuarioDAO = new UsuarioDAO<Usuario>();
	private DAOGeneric<Usuario> daoGeneric = new DAOGeneric<Usuario>();
	
	@PostConstruct
	public void init() {
		String coduserbean = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("coduserbean");
		usuario = usuarioDAO.pesquisar(Integer.parseInt(coduserbean), Usuario.class);
	}
	
	
	
	public String salvar() {
		if (!lista.getData().isEmpty() && !lista.getHora().isEmpty()) {
		lista.setUsuario(usuario);
		dao.salvar(lista);
		return "finalizar.jsf";
		} else 
			return "bem-vindo.jsf";
	}
	
	public void deletarPorId() {
		dao.deletarPorId(lista);
		usuario = usuarioDAO.pesquisar(usuario.getId_usuario(), Usuario.class);
		if(true) {
			Lista lista = new Lista();
		}
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public UsuarioDAO<Usuario> getUsuarioDAO() {
		return usuarioDAO;
	}

	public void setUsuarioDAO(UsuarioDAO<Usuario> usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}

	public DAOGeneric<Usuario> getDaoGeneric() {
		return daoGeneric;
	}

	public void setDaoGeneric(DAOGeneric<Usuario> daoGeneric) {
		this.daoGeneric = daoGeneric;
	}

	public Lista getLista() {
		return lista;
	}

	public void setLista(Lista lista) {
		this.lista = lista;
	}

	public DAOGeneric<Lista> getDao() {
		return dao;
	}

	public void setDao(DAOGeneric<Lista> dao) {
		this.dao = dao;
	}

	
}
