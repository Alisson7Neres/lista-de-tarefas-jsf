package bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import dao.DAOGeneric;
import jakarta.annotation.PostConstruct;
import model.Usuario;

@ManagedBean(name = "usuarioBean")
@ViewScoped
public class UsuarioBean {
	
	Usuario usuario = new Usuario();
	private DAOGeneric<Usuario> dao = new DAOGeneric<Usuario>();
	private List<Usuario> usuarios = new ArrayList<Usuario>();

	public String salvar() {
		usuario = dao.atualizar(usuario);
		listar();
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
	
	
}
