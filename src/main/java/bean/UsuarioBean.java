package bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import dao.DAOGeneric;
import dao.TelefoneDAO;
import javax.annotation.PostConstruct;
import model.Telefone;
import model.Usuario;

@ManagedBean(name = "usuarioBean")
@ViewScoped
public class UsuarioBean {
	
	private Usuario usuario = new Usuario();
	//private UsuarioDAO usuarioDAO = new UsuarioDAO();
	private DAOGeneric<Usuario> dao = new DAOGeneric<Usuario>();
	private TelefoneDAO<Telefone> telefoneDAO = new TelefoneDAO<Telefone>();
	private List<Usuario> usuarios = new ArrayList<Usuario>();
	
	@PostConstruct
	public void init() {
		usuarios = dao.listarUsuario(Usuario.class);
	}

	public String salvar() {
		dao.salvar(usuario);
		usuarios.add(usuario);
		return "finalizar.xhtml";
	}
	
	public String novo() {
		usuario = new Usuario();
		listar();
		return "";
	}
	
	public String deletar() {
		dao.deletarPorId(usuario);
		if(true) {
			usuario = new Usuario();
			listar();
		}
		return "";
	}
	
	public void listar() {
		usuarios = dao.listarUsuario(Usuario.class);
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public DAOGeneric<Usuario> getDao() {
		return dao;
	}

	public void setDao(DAOGeneric<Usuario> dao) {
		this.dao = dao;
	}

	public List<Usuario> getUsuarios() {
		usuarios = dao.listarUsuario(Usuario.class);
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
	public TelefoneDAO<Telefone> getTelefoneDAO() {
		return telefoneDAO;
	}
	
	public void setTelefoneDAO(TelefoneDAO<Telefone> telefoneDAO) {
		this.telefoneDAO = telefoneDAO;
	}
	
}
