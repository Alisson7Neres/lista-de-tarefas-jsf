package bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import dao.UsuarioDAO;
import jakarta.annotation.PostConstruct;
import model.Usuario;

@ViewScoped
@ManagedBean(name = "usuarioBean")
public class UsuarioBean {
	
	Usuario usuario = new Usuario();
	private UsuarioDAO<Usuario> dao = new UsuarioDAO<Usuario>();
	private List<Usuario> usuarios = new ArrayList<Usuario>();

	public String salvar() {
		usuario = dao.atualizar(usuario);
		listar();
		return "";
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
	
	@PostConstruct
	public void listar() {
		usuarios = dao.listarUsuario(Usuario.class);
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public UsuarioDAO<Usuario> getDao() {
		return dao;
	}

	public void setDao(UsuarioDAO<Usuario> dao) {
		this.dao = dao;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
	
}
