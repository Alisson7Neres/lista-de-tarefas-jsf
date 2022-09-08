package bean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import dao.DAOGeneric;
import dao.TelefoneDAO;
import dao.UsuarioDAO;
import model.Telefone;
import model.Usuario;

@ManagedBean(name = "usuarioBean")
@ViewScoped
@SessionScoped
public class UsuarioBean {
	
	private Usuario usuario = new Usuario();
	private UsuarioDAO<Usuario> usuarioDAO = new UsuarioDAO<Usuario>();
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
	
	public String logar() throws IOException {

		// Por segurança, quando finalizar.jsf for carregada, ela irá verificar se tem usuário na sessão
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
			    .getExternalContext().getSession(true);

		Usuario usuarioLogado = (Usuario) session.getAttribute("usuario");
		if (usuarioLogado != null) {
			//usuario = dao.pesquisar(usuario.getId_usuario(), Usuario.class);
			return "finalizar.jsf";
		}
		dao.logar(usuario.getEmail(), usuario.getSenha());
		return "index.jsf";
	}
	
	public String logout() {
		dao.logout(usuario);
		return "index.jsf";
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
	
	public UsuarioDAO<Usuario> getUsuarioDAO() {
		return usuarioDAO;
	}
	
}
